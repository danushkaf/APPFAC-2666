/*
 * Copyright (c) 2005-2011, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.carbon.appfactory.deployers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.appfactory.common.AppFactoryConfiguration;
import org.wso2.carbon.appfactory.common.AppFactoryConstants;
import org.wso2.carbon.appfactory.common.AppFactoryException;
import org.wso2.carbon.appfactory.common.util.AppFactoryUtil;
import org.wso2.carbon.appfactory.deployers.util.DeployerUtil;
import org.wso2.carbon.appfactory.s4.integration.RepositoryProvider;
import org.wso2.carbon.appfactory.s4.integration.StratosRestService;

import java.io.File;
import java.util.Map;

/**
 * This class creates a GIT repository in s4 gitblit for the built artifacts and
 * subscribes to the stratos cartridges for "subscription upon deployment" type
 * apps.
 */
public class SubscriptionHandler {

    private static final Log log = LogFactory.getLog(SubscriptionHandler.class);

    private static final SubscriptionHandler subscriptionHandler = new SubscriptionHandler();

    private final String ENVIRONMENT = "ApplicationDeployment.DeploymentStage";

    /**
     * Constructor
     */
    private SubscriptionHandler() {
    }

    /**
     * Returns an instance of the subscription handler
     * @return instance of the subscription handler
     */
    public static SubscriptionHandler getInstance(){
        return subscriptionHandler;
    }

    /**
     * This methods creates a repo in S4 gitblit and subscribes to the Stratos
     *
     * @param deployerInfo      Metadata map passed for deployment
     * @param stage             stage of the application
     * @param username          username for current user
     * @param tenantID          tenant ID of the current tenant
     * @param applicationID     application ID of the current application
     * @param tenantDomain      tenant domain for current tenant
     * @return                  repo url for the subscription
     * @throws AppFactoryException {@link AppFactoryException} When subscription fails
     */
    public String createSubscription(Map deployerInfo, String stage, String username, int tenantID,
                                     String applicationID, String tenantDomain) throws AppFactoryException {

        AppFactoryConfiguration configuration = AppFactoryUtil.getAppfactoryConfiguration();
        //get the stratos server url for each stage
        String serverURL = configuration.getFirstProperty(
                ENVIRONMENT + AppFactoryConstants.DOT + stage + AppFactoryConstants.DOT + "TenantMgtUrl");

        StratosRestService restService = new StratosRestService(serverURL,username, "");
        deployerInfo.put(AppFactoryConstants.RUNTIME_ALIAS_PREFIX, applicationID + tenantDomain
                .replace(AppFactoryConstants.DOT, AppFactoryConstants.DOT_WORD));
        String repoUrl = "";
        String className = AppFactoryUtil.getAppfactoryConfiguration().getFirstProperty(
                AppFactoryConstants.PASS_ARTIFACT_STORAGE_REPOSITORY_PROVIDER_PROVIDER_CLASS);
        String adminUserName = AppFactoryUtil.getAppfactoryConfiguration().
                getFirstProperty(AppFactoryConstants.PASS_ARTIFACT_STORAGE_REPOSITORY_PROVIDER_ADMIN_USERNAME);
        String adminPassword = AppFactoryUtil.getAppfactoryConfiguration().
                getFirstProperty(AppFactoryConstants.PASS_ARTIFACT_STORAGE_REPOSITORY_PROVIDER_ADMIN_PASSWORD);
        String baseURL = AppFactoryUtil.getAppfactoryConfiguration().
                getFirstProperty(AppFactoryConstants.PASS_ARTIFACT_STORAGE_REPOSITORY_PROVIDER_BASE_URL);

        try {
            if (!restService.isAlreadySubscribed(applicationID + tenantDomain.replace(AppFactoryConstants.DOT,
                                                                                      AppFactoryConstants.DOT_WORD))) {
                ClassLoader loader = getClass().getClassLoader();
                Class<?> repoProviderClass = Class.forName(className, true, loader);
                RepositoryProvider repoProvider = (RepositoryProvider) repoProviderClass.newInstance();
                repoProvider.setBaseUrl(baseURL);
                repoProvider.setAdminUsername(adminUserName);
                repoProvider.setAdminPassword(adminPassword);

                String repoURLforDeployer = DeployerUtil.getParameterValue(
                        deployerInfo, AppFactoryConstants.RUNTIME_REPO_URL);
                String aliasPrefix = DeployerUtil.getParameterValue(
                        deployerInfo, AppFactoryConstants.RUNTIME_ALIAS_PREFIX);
                repoProvider.setRepoName(generateRepoUrlFromTemplate(repoURLforDeployer,aliasPrefix,
                        tenantID, stage, applicationID));
                repoUrl = repoProvider.createRepository();
                deployerInfo.put(AppFactoryConstants.RUNTIME_REPO_PROVIDER_URL, repoUrl);

                String cartridgeTypePrefix = DeployerUtil.getParameterValue(
                        deployerInfo,AppFactoryConstants.RUNTIME_CARTRIDGE_TYPE_PREFIX);
                String dataCartridgeType = DeployerUtil.getParameterValue(
                        deployerInfo, AppFactoryConstants.RUNTIME_DATA_CARTRIDGE_TYPE);
                String dataCartridgeAlias = DeployerUtil.getParameterValue(
                        deployerInfo, AppFactoryConstants.RUNTIME_DATA_CARTRIDGE_ALIAS);
                String autoScalePolicy = DeployerUtil.getParameterValue(
                        deployerInfo, AppFactoryConstants.RUNTIME_DATA_CARTRIDGE_ALIAS);
                String deploymentPolicy = DeployerUtil.getParameterValue(
                        deployerInfo, AppFactoryConstants.RUNTIME_DEPLOYMENT_POLICY);
                restService.subscribe(
                        cartridgeTypePrefix,
                        aliasPrefix,
                        repoUrl,
                        true, adminUserName,adminPassword,
                        dataCartridgeType,
                        dataCartridgeAlias,
                        autoScalePolicy,
                        deploymentPolicy);
            }

        } catch (ClassNotFoundException e) {
            String msg = "Cannot find the class definition for PAAS repository provider";
            throw new AppFactoryException(msg, e);
        } catch (InstantiationException e) {
            String msg = "Error while instantiating the repository provider";
            throw new AppFactoryException(msg, e);
        } catch (IllegalAccessException e) {
            String msg = "Error while instantiating the repository provider";
            throw new AppFactoryException(msg, e);
        }
        return repoUrl;
    }

    /**
     *
     * @param patternStage
     *            stage string pattern
     * @param patternAlias
     *            alias string pattern
     * @param tenantId
     *            tenant ID
     * @param stage
     *            stage of the application
     * @param appName
     *            application ID
     * @return generated repository URL e.g. Development/12/myApplication
     */
    public String generateRepoUrlFromTemplate(String patternStage,
                                                     String patternAlias, int tenantId, String stage, String appName) {
        String s = patternStage.replace("{@stage}", stage) + File.separator
                + Integer.toString(tenantId) + File.separator
                + patternAlias.replace("{@appName}", appName);
        log.info("generated repo URL: " + s);
        return s;

    }

}
