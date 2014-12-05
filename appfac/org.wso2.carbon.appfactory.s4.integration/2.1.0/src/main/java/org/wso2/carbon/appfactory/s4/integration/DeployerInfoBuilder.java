/*
 * Copyright 2005-2011 WSO2, Inc. (http://wso2.com)
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

package org.wso2.carbon.appfactory.s4.integration;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.appfactory.common.AppFactoryConfiguration;
import org.wso2.carbon.appfactory.common.AppFactoryConstants;
import org.wso2.carbon.appfactory.common.AppFactoryException;
import org.wso2.carbon.appfactory.common.util.AppFactoryUtil;
import org.wso2.carbon.appfactory.s4.integration.internal.ServiceReferenceHolder;

import java.util.HashMap;
import java.util.Map;

/**
 * This client is used to subscribe to cartridges for production application deployment.
 * This client does 2 things.
 * 1. Create git repositories for subscriptions
 * 2. Subscribe to cartridges using the created git repo
 */
public class DeployerInfoBuilder {
    private static final Log log = LogFactory.getLog(DeployerInfoBuilder.class);


    private static final String DEPLOYER_APPLICATION_TYPE = ".Deployer.ApplicationType";
    /**
     * Application deployment property prefix
     */
    private static final String APPLICATION_DEPLOYMENT_DEPLOYMENT_STAGE = "ApplicationDeployment.DeploymentStage.";
    private static final String APPLICATION_DEPLOYMENT_DEPLOYMENT_STAGES =
            "ApplicationDeployment" +
                    ".DeploymentStage";

    //    Holds to cartridge information of each stage
    private static Map<String, Map<String, DeployerInfo>> deployerMap = new HashMap<String, Map<String, DeployerInfo>>();
    private static DeployerInfo deployerSingle=new DeployerInfo();
    //  private String stage;


    public void build() {
        //TODO 
        if (deployerMap.isEmpty()) {
            try {
                AppFactoryConfiguration configuration = AppFactoryUtil.getAppfactoryConfiguration();
            	//Get all Stages
            	String stages[]=configuration.getProperties(APPLICATION_DEPLOYMENT_DEPLOYMENT_STAGES);


                for (String stage : stages) {
                    String[] appType = configuration.getProperties(APPLICATION_DEPLOYMENT_DEPLOYMENT_STAGE + stage +
                            DEPLOYER_APPLICATION_TYPE);

                    for (String type : appType) {
                        try {
                            initDeployerMap(configuration, stage, type);
                        } catch (AppFactoryException e) {
                           // We don't break the loop here.
                            String msg = "Unable to read subscription properties from configuration";
                            log.error(msg, e);
                        }
                    }
                }
            } catch (AppFactoryException e) {
                String msg = "Unable to load app factory configuration!";
                log.error(msg, e);
                throw new RuntimeException(msg, e);
            }
        }

    }

    private void initDeployerMap(AppFactoryConfiguration configuration, String stage, String appType)
            throws AppFactoryException {
        Map<String, DeployerInfo> typeMap = new HashMap<String, DeployerInfo>();
        if (deployerMap.containsKey(stage)) {
            typeMap = deployerMap.get(stage);
        }

        DeployerInfo deployerInfo = new DeployerInfo();



        String endpoint = configuration.getFirstProperty(
                APPLICATION_DEPLOYMENT_DEPLOYMENT_STAGE + stage + DEPLOYER_APPLICATION_TYPE + "." + appType +
                        ".Endpoint");


//        No endpoint has been defined. This is not the S2 Deployer
        if (endpoint == null || AppFactoryConstants.EMPTY_STRING.equals(endpoint)) {
            return;
        }
        deployerInfo.setEndpoint(endpoint);

        String deploymentPolicy = configuration.getFirstProperty(
                APPLICATION_DEPLOYMENT_DEPLOYMENT_STAGE + stage + DEPLOYER_APPLICATION_TYPE + "." + appType +
                        ".Properties.Property.deploymentPolicy");

        String autoscalePolicy = configuration.getFirstProperty(
                APPLICATION_DEPLOYMENT_DEPLOYMENT_STAGE + stage + DEPLOYER_APPLICATION_TYPE + "." + appType +
                        ".Properties.Property.autoscalePolicy");



        deployerInfo.setAlias(configuration.getFirstProperty(
                APPLICATION_DEPLOYMENT_DEPLOYMENT_STAGE + stage + DEPLOYER_APPLICATION_TYPE + "." + appType +
                        ".Properties.Property.alias"));

        deployerInfo.setCartridgeType(configuration.getFirstProperty(
                APPLICATION_DEPLOYMENT_DEPLOYMENT_STAGE + stage + DEPLOYER_APPLICATION_TYPE + "." + appType +
                        ".Properties.Property.cartridgeType"));

        deployerInfo.setRepoURL(configuration.getFirstProperty(
                APPLICATION_DEPLOYMENT_DEPLOYMENT_STAGE + stage + DEPLOYER_APPLICATION_TYPE + "." + appType +
                        ".Properties.Property.repoURL"));

        deployerInfo.setDataCartridgeType(configuration.getFirstProperty(
                APPLICATION_DEPLOYMENT_DEPLOYMENT_STAGE + stage + DEPLOYER_APPLICATION_TYPE + "." + appType +
                        ".Properties.Property.dataCartridgeType"));

       deployerInfo.setDataCartridgeAlias(configuration.getFirstProperty(
                APPLICATION_DEPLOYMENT_DEPLOYMENT_STAGE + stage + DEPLOYER_APPLICATION_TYPE + "." + appType +
                        ".Properties.Property.dataCartridgeAlias"));

        deployerInfo.setEndpoint(configuration.getFirstProperty(
                APPLICATION_DEPLOYMENT_DEPLOYMENT_STAGE + stage + DEPLOYER_APPLICATION_TYPE + "." + appType +
                        ".Endpoint"));


        String className = configuration.getFirstProperty(
                APPLICATION_DEPLOYMENT_DEPLOYMENT_STAGE + stage + DEPLOYER_APPLICATION_TYPE + "." + appType +
                        ".RepositoryProvider.Property.Class");
        deployerInfo.setClassName(className);

        try {
            ClassLoader loader = getClass().getClassLoader();
            Class<?> repoProvider = Class.forName(className, true, loader);
            deployerInfo.setRepoProvider(repoProvider);
        } catch (ClassNotFoundException e) {
            String msg = "Unable to load repository provider class";
            log.error(msg, e);
            throw new AppFactoryException(msg, e);
        }

        deployerInfo.setBaseURL(configuration.getFirstProperty(
                APPLICATION_DEPLOYMENT_DEPLOYMENT_STAGE + stage + DEPLOYER_APPLICATION_TYPE + "." + appType +
                        ".RepositoryProvider.Property.BaseURL"));


        deployerInfo.setAdminUserName(configuration.getFirstProperty(
                APPLICATION_DEPLOYMENT_DEPLOYMENT_STAGE + stage + DEPLOYER_APPLICATION_TYPE + "." + appType +
                        ".RepositoryProvider.Property.AdminUserName"));


        deployerInfo.setAdminPassword(configuration.getFirstProperty(
                APPLICATION_DEPLOYMENT_DEPLOYMENT_STAGE + stage + DEPLOYER_APPLICATION_TYPE + "." + appType +
                        ".RepositoryProvider.Property.AdminPassword"));


        deployerInfo.setRepoPattern(configuration.getFirstProperty(
                APPLICATION_DEPLOYMENT_DEPLOYMENT_STAGE + stage + DEPLOYER_APPLICATION_TYPE + "." + appType +
                        ".RepositoryProvider.Property.URLPattern"));

        deployerInfo.setSubscribeOnDeployment(configuration.getFirstProperty(APPLICATION_DEPLOYMENT_DEPLOYMENT_STAGE + stage + DEPLOYER_APPLICATION_TYPE + "." + appType +
                ".Properties.Property.subscribeOnDeployment"));


        deployerInfo.setAppType(appType);

        typeMap.put(appType, deployerInfo);
        deployerMap.put(stage, typeMap);
    }


    public Map<String, DeployerInfo> getDeployerInfo(String stage) {
        return deployerMap.get(stage);
    }

//getting deployer information for a particular app type

    public DeployerInfo getDeployerPerApp(String stage, String deployerType){
        return deployerMap.get(stage).get(deployerType);
    }

}
