/*
 *
 *  * Copyright 2014 WSO2, Inc. (http://wso2.com)
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.wso2.carbon.appfactory.core.runtime;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.appfactory.common.AppFactoryConfigurationBuilder;
import org.wso2.carbon.appfactory.common.AppFactoryConstants;
import org.wso2.carbon.appfactory.common.AppFactoryException;
import org.wso2.carbon.appfactory.common.beans.RuntimeBean;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Manage Application type information
 * Singleton class
 */
public class RuntimeManager {
	private static final Log log = LogFactory.getLog(RuntimeManager.class);
	private Map<String, RuntimeBean> runtimeBeanMap = new HashMap<String, RuntimeBean>();
	private static final RuntimeManager runtimeManager = new RuntimeManager();

	/**
	 * Constructor
	 */
	private RuntimeManager() {
	}

	/**
	 * Getter for the runtime bean for the specific runtime
	 *
	 * @param applicationRuntime The type of the application. Ex:- appserver,tomcat
	 * @return RuntimeBean
	 */
	public RuntimeBean getRuntimeBean(String applicationRuntime) {
		return getRuntimeBeanMap().get(applicationRuntime);
	}

	/**
	 * Returns the current instance
	 *
	 * @return RuntimeManager instance
	 * @throws org.wso2.carbon.appfactory.common.AppFactoryException
	 */
	public static RuntimeManager getInstance() throws AppFactoryException {
		return runtimeManager;
	}

	/**
	 * Add new runtime from a runtime.xml
	 *
	 * @param file the runtime configuration file
	 * @throws org.wso2.carbon.appfactory.common.AppFactoryException
	 */
	public void addAppRuntime(File file) throws AppFactoryException {
		Map<String, String> appRuntimeConfig = new AppFactoryConfigurationBuilder(file
				                                                                          .getAbsolutePath())
				.loadConfigurationFile();
		if (appRuntimeConfig == null) {
			throw new AppFactoryException(
					"Configuration is null. Check the runtime.xml and try again.");
		}
		initAppRuntimeConfig(appRuntimeConfig);
	}

	/**
	 * Initialize the bean from the configuration
	 *
	 * @param config map of name value pairs from the content of the runtime.xml
	 * @throws org.wso2.carbon.appfactory.common.AppFactoryException
	 */
	private void initAppRuntimeConfig(Map<String, String> config) throws AppFactoryException {

		String runtimeName = config.get(AppFactoryConstants.RUNTIME);
		Properties properties = new Properties();

		for (String key : config.keySet()) {
			if (config.get(key) != null) {
				properties.put(key, config.get(key));
			} else {
				log.warn("Property is not available in runtime configuration : " + key);
			}
		}

		RuntimeBean applicationRuntimeBean;
		try {

			applicationRuntimeBean = new RuntimeBean();
			applicationRuntimeBean.setRuntimeName(runtimeName);
			applicationRuntimeBean.setDeployerClassName(
					properties.getProperty(AppFactoryConstants.RUNTIME_DEPLOYER_CLASSNAME));
			applicationRuntimeBean.setPaasRepositoryURLPattern(
					properties.getProperty(AppFactoryConstants.RUNTIME_REPOSITORY_URL_PATTERN));
			applicationRuntimeBean.setAliasPrefix(
					properties.getProperty(AppFactoryConstants.RUNTIME_ALIAS_PREFIX));
			applicationRuntimeBean.setCartridgeTypePrefix(
					properties.getProperty(AppFactoryConstants.RUNTIME_CARTRIDGE_TYPE_PREFIX));
			applicationRuntimeBean.setDeploymentPolicy(
					properties.getProperty(AppFactoryConstants.RUNTIME_DEPLOYMENT_POLICY));
			applicationRuntimeBean.setAutoscalePolicy(
					properties.getProperty(AppFactoryConstants.RUNTIME_AUTOSCALE_POLICY));
			applicationRuntimeBean.setRepoURL(
					properties.getProperty(AppFactoryConstants.RUNTIME_REPO_URL));
			applicationRuntimeBean.setDataCartridgeType(
					properties.getProperty(AppFactoryConstants.RUNTIME_DATA_CARTRIDGE_TYPE));
			applicationRuntimeBean.setDataCartridgeAlias(
					properties.getProperty(AppFactoryConstants.RUNTIME_DATA_CARTRIDGE_ALIAS));
			if (properties.get(AppFactoryConstants.RUNTIME_SUBSCRIBE_ON_DEPLOYMENT) != null) {
				applicationRuntimeBean.setSubscribeOnDeployment(
						Boolean.valueOf(properties.getProperty(AppFactoryConstants.RUNTIME_SUBSCRIBE_ON_DEPLOYMENT)));
			} else {
				applicationRuntimeBean.setSubscribeOnDeployment(false);
			}

			runtimeManager.getRuntimeBeanMap().put(runtimeName, applicationRuntimeBean);
		} catch (NullPointerException e) {
			String msg = "Exception occurred while reading the xml";
			log.error(msg, e);
			throw new AppFactoryException(msg, e);
		} catch (Exception e) {
			String msg = "Exception occurred while reading the xml";
			log.error(msg, e);
			throw new AppFactoryException(msg, e);
		}

	}

	public Map<String, RuntimeBean> getRuntimeBeanMap() {
		return runtimeBeanMap;
	}

	public void setRuntimeBeanMap(
			Map<String, RuntimeBean> runtimeBeanMap) {
		this.runtimeBeanMap = runtimeBeanMap;
	}
}
