/*
 * Copyright 2005-2014 WSO2, Inc. (http://wso2.com)
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */

package org.wso2.carbon.appfactory.build.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.appfactory.common.AppFactoryConstants;
import org.wso2.carbon.appfactory.common.AppFactoryException;
import org.wso2.carbon.appfactory.core.build.DefaultBuildDriverListener;
import org.wso2.carbon.appfactory.core.util.AppFactoryCoreUtil;
import org.wso2.carbon.appfactory.eventing.AppFactoryEventException;
import org.wso2.carbon.appfactory.eventing.Event;
import org.wso2.carbon.appfactory.eventing.EventNotifier;
import org.wso2.carbon.appfactory.eventing.builder.utils.ContinousIntegrationEventBuilderUtil;
import org.wso2.carbon.appfactory.eventing.utils.EventingConstants;
import org.wso2.carbon.appfactory.jenkins.build.internal.ServiceContainer;
import org.wso2.carbon.appfactory.utilities.project.ProjectUtils;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.registry.core.service.TenantRegistryLoader;
import org.wso2.carbon.user.api.UserStoreException;

/**
 * This service class is used to receive the build status
 */
public class BuildStatusReceiverService {

    private static Log log = LogFactory.getLog(BuildStatusReceiverService.class);

    /**
     * This is to be called after a completion of a build
     * This service runs on super tenant mode. Hence we are passing the tenant domain as a parameter too.
     *
     * @param buildStatus  status of the build
     * @param tenantDomain tenant domain
     */
    public void onBuildCompletion(BuildStatusBean buildStatus, String tenantDomain) throws AppFactoryException {

        // Getting the tenant ID to set in tenant flow.
        int tenantId;
        try {
            tenantId = ServiceContainer.getRealmService().getTenantManager().getTenantId(tenantDomain);
        } catch (UserStoreException e) {
            String msg = "Unable to get the tenant Id for domain : " + tenantDomain;
            log.error(msg, e);
            throw new AppFactoryException(msg, e);
        }

        try {
            //Starting the tenant flow.
            PrivilegedCarbonContext.startTenantFlow();
            PrivilegedCarbonContext.getThreadLocalCarbonContext().setTenantId(tenantId, true);

            //We need to load the tenant registry as well. Otherwise there will be an error from the user registry
            TenantRegistryLoader tenantRegistryLoader = ServiceContainer.getTenantRegistryLoader();
            tenantRegistryLoader.loadTenantRegistry(tenantId);

            if (log.isDebugEnabled()) {
                log.debug(
                        "Build initiated by " + buildStatus.getUserName() + ", for " + buildStatus.getApplicationId() +
                        " in " + buildStatus.getRepoFrom() + " repository " + " is completed with buildId " +
                        buildStatus.getBuildId());
            }

            DefaultBuildDriverListener listener = new DefaultBuildDriverListener();

            boolean isFreestyle;
            try {
                isFreestyle = AppFactoryCoreUtil.isFreestyleNonBuilableProject(ProjectUtils.getApplicationInfo(
                        buildStatus.getApplicationId(), tenantDomain).getType());
            } catch (AppFactoryException e) {
                String msg = "Error occurred while determining whether the application is freestyle";
                log.error(msg, e);
                throw new AppFactoryException(msg, e);
            }

            if (buildStatus.isBuildSuccessful()) {
                listener.onBuildSuccessful(buildStatus.getApplicationId(), buildStatus.getVersion(), null,
                                           buildStatus.getUserName(), buildStatus.getRepoFrom(),
                                           buildStatus.getBuildId(), tenantDomain);
            } else {
                listener.onBuildFailure(buildStatus.getApplicationId(), buildStatus.getVersion(), null,
                                        buildStatus.getUserName(), buildStatus.getRepoFrom(), buildStatus.getBuildId(),
                                        buildStatus.getLogMsg(), tenantDomain);
            }

            // freestyle apps can't be built by user. So no need to send notification to the user.
            // ( they are built internally for deployment purposes )
            if (!isFreestyle) {
                addWallNotification(buildStatus);
            }

        } finally {
            PrivilegedCarbonContext.endTenantFlow();
        }
    }

    private void addWallNotification(BuildStatusBean buildStatusBean) throws AppFactoryException {

        String correlationKey = buildStatusBean.getApplicationId() + "-" + buildStatusBean.getTriggeredUser() + "-" +
                                buildStatusBean.getRepoFrom() + "-" + buildStatusBean.getVersion();
        try {
            String repoType;
            if (buildStatusBean.getRepoFrom().equals(EventingConstants.ORIGINAL_REPO_FORM)) {
                repoType = AppFactoryConstants.ORIGINAL_REPOSITORY;
            } else {
                repoType = AppFactoryConstants.FORK_REPOSITORY;
            }

            String infoMessage;
            if (buildStatusBean.isBuildSuccessful()) {
                infoMessage = buildStatusBean.getVersion() + " of " + repoType + " built successfully by " +
                              buildStatusBean.getTriggeredUser().split("@")[0];
            } else {
                infoMessage = buildStatusBean.getVersion() + " " + repoType + " build failed";
            }
            String description = "Build ID : " + buildStatusBean.getBuildId();

            EventNotifier.getInstance().notify(ContinousIntegrationEventBuilderUtil.
                    buildContinuousIntegrationEvent(buildStatusBean.getApplicationId(), buildStatusBean.getRepoFrom()
                            , infoMessage, description, Event.Category.ERROR,
                                                    buildStatusBean.getTriggeredUser(), correlationKey));

        } catch (AppFactoryEventException e) {
            String msg = "Failed to notify Branch build success event";
            log.error(msg, e);
            throw new AppFactoryException(msg, e);
        }
    }
}
