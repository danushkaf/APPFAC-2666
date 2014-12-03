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
import org.wso2.carbon.appfactory.common.AppFactoryException;
import org.wso2.carbon.appfactory.common.util.AppFactoryUtil;
import org.wso2.carbon.appfactory.s4.integration.DeployerInfo;
import org.wso2.carbon.appfactory.s4.integration.RepositoryProvider;
import org.wso2.carbon.appfactory.s4.integration.StratosRestService;


import java.io.File;

/**
 * This class creates a GIT repository in s4 gitblit for the built artifacts and
 * subscribes to the stratos cartridges for "subscription upon deployment" type
 * apps.
 */
public class StratosDeployer {

    private static final Log log = LogFactory.getLog(StratosDeployer.class);

    private final String ENVIRONMENT = "ApplicationDeployment.DeploymentStage";
    /**
     * This methods creates a repo in S4 gitblit and subscribes to the Stratos
     *
     * @param stage
     *            stage of the application
     * @param appType
     *            application type
     * @param tenantID
     *            tenant ID
     * @param applicationID
     *            application ID
     * @param tenantDomain
     *            tenant domain
     * @return the repository URL of the application artifacts
     * @throws AppFactoryException
     */

    public String createSubscription(DeployerInfo deployerInfo, String stage,
                                     String appType,String username, int tenantID, String applicationID,
                                     String tenantDomain) throws AppFactoryException {

        AppFactoryConfiguration configuration = AppFactoryUtil.getAppfactoryConfiguration();
        //get the stratos server url for each stage
        String serverURL = configuration.getFirstProperty(ENVIRONMENT + "." + stage + "." + "TenantMgtUrl");

        StratosRestService restService = new StratosRestService(serverURL,username, "");
        deployerInfo.setAlias(applicationID + tenantDomain.replace(".", "dot"));
        String repoUrl = "";

        try {
            if (!restService.isAlreadySubscribed(applicationID
                    + tenantDomain.replace(".", "dot"))) {
                RepositoryProvider repoProvider = (RepositoryProvider) deployerInfo
                        .getRepoProvider().newInstance();
                repoProvider.setBaseUrl(deployerInfo.getBaseURL());
                repoProvider.setAdminUsername(deployerInfo.getAdminUserName());
                repoProvider.setAdminPassword(deployerInfo.getAdminPassword());
                repoProvider.setRepoName(generateRepoUrlFromTemplate(
                        deployerInfo.getRepoPattern(), deployerInfo.getAlias(),
                        tenantID, stage, applicationID));
                repoUrl = repoProvider.createRepository();
                deployerInfo.setRepoURL(repoUrl);

                log.info("***************************repo url 1:" + repoUrl
                        + "******************");

                restService.subscribe(deployerInfo.getCartridgeType(),
                        deployerInfo.getAlias(), deployerInfo.getRepoURL(),
                        true, deployerInfo.getAdminUserName(),
                        deployerInfo.getAdminPassword(),
                        deployerInfo.getDataCartridgeType(),
                        deployerInfo.getDataCartridgeAlias(),
                        deployerInfo.getAutoscalePolicy(),
                        deployerInfo.getDeploymentPolicy());
            }

        } catch (Exception e) {
            String msg = "Unable to create repository";
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
    public static String generateRepoUrlFromTemplate(String patternStage,
                                                     String patternAlias, int tenantId, String stage, String appName) {
        String s = patternStage.replace("{@stage}", stage) + File.separator
                + Integer.toString(tenantId) + File.separator
                + patternAlias.replace("{@appName}", appName);
        log.info("generated repo URL: " + s);
        return s;

    }

}
