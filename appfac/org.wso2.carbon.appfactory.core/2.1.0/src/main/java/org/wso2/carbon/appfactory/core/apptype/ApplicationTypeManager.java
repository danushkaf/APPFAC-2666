/*
 * Copyright 2005-2013 WSO2, Inc. (http://wso2.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.carbon.appfactory.core.apptype;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.appfactory.common.AppFactoryConfigurationBuilder;
import org.wso2.carbon.appfactory.common.AppFactoryConstants;
import org.wso2.carbon.appfactory.common.AppFactoryException;
import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Manage Application type information
 * Singleton class
 */
public class ApplicationTypeManager {
	private static final Log log = LogFactory.getLog(ApplicationTypeManager.class);
	private Map<String, ApplicationTypeBean> applicationTypeBeanMap = new HashMap<String, ApplicationTypeBean>();
	private static final ApplicationTypeManager applicationTypeManager = new ApplicationTypeManager();

	/**
	 * Constructor
	 */
	private ApplicationTypeManager() {
	}

	/**
	 * Getter for the application type bean for the specific type
	 * @param applicationType The type of the application. Ex:- war,jaxrs, jaxws
	 * @return ApplicationTypeBean
	 */
	public ApplicationTypeBean getApplicationTypeBean(String applicationType) {
		return getApplicationTypeBeanMap().get(applicationType);
	}

	/**
	 * Returns the current instance
	 * @return ApplicationTypeBean
	 * @throws AppFactoryException
	 */
	public static ApplicationTypeManager getInstance() throws AppFactoryException {
		return applicationTypeManager;
	}

	/**
	 * Add new app type from a apptype.xml
	 *
	 * @param apptype     apptype configuration file
	 * @param buildConfig build job configuration file
	 * @throws AppFactoryException
	 */
	public void addAppType(File apptype, File buildConfig) throws AppFactoryException, FileNotFoundException {

		Map<String, String> appTypeConfig = new AppFactoryConfigurationBuilder(apptype.getAbsolutePath())
				.loadConfigurationFile();
		OMElement buildTemplate;
		StAXOMBuilder builder;
		try {
			if (!buildConfig.exists()) {
				//get the default config
				if (log.isDebugEnabled()) {
					log.debug("getting the default jenkins-config since custom config is not available for apptype:" +
					          apptype.getName());
				}
				InputStream inputStream =
						this.getClass().getResourceAsStream(File.separator + AppFactoryConstants.JENKINS_JOB_CONFIG);
				builder = new StAXOMBuilder(inputStream);
				buildTemplate = builder.getDocumentElement();
			} else {
				if (log.isDebugEnabled()) {
					log.debug("getting the custom jenkins-config since it is provided for apptype:"
					          + apptype.getName());
				}
				InputStream inputStream = new FileInputStream(buildConfig);
				builder = new StAXOMBuilder(inputStream);
				buildTemplate = builder.getDocumentElement();
			}
		} catch (XMLStreamException e) {
			String msg = "Error while reading apptype: " + apptype.getName();
			log.error(msg, e);
			throw new AppFactoryException(msg, e);
		}
		initAppTypeFromConfig(appTypeConfig, buildTemplate);
	}

	/**
	 * Initialize the bean from the configuration
	 *
	 * @param config map of name value pairs from the content of the apptype.xml
	 * @param buildJob OMElement of the build job
	 * @throws AppFactoryException
	 */
	private void initAppTypeFromConfig(Map<String, String> config, OMElement buildJob) throws AppFactoryException {

		String type = config.get(AppFactoryConstants.APPLICATION_TYPE_CONFIG);
		Properties properties = new Properties();

		for (Map.Entry entry : config.entrySet()){
			if(entry.getValue() != null) {
				properties.put(entry.getKey(), entry.getValue());
			} else {
				log.warn("Property is not available in apptype configuration : " + entry.getKey());
			}
		}

		ApplicationTypeBean applicationTypeBean;
		String className = (String) properties.get("ProcessorClassName");
		try {
			Class<ApplicationTypeManager> clazz =
					(Class<ApplicationTypeManager>) applicationTypeManager.getClass()
					                                       .getClassLoader()
					                                       .loadClass(className);
			Constructor constructor = clazz.getConstructor();
			ApplicationTypeProcessor applicationTypeProcessor =
					(ApplicationTypeProcessor) constructor.newInstance();
			applicationTypeBean = new ApplicationTypeBean();
			applicationTypeBean.setApplicationTypeName(type);
			if(properties.getProperty("Buildable") != null){
				applicationTypeBean.setBuildable(Boolean.parseBoolean(properties.get("Buildable").toString()));
			}
			applicationTypeBean.setRuntimes(properties.getProperty("Runtimes"));
			applicationTypeBean.setBuildJobTemplate(properties.get("BuildJobTemplate").toString());
			applicationTypeBean.setComment(properties.get("Comment").toString());
			applicationTypeBean.setDescription(properties.get("Description").toString());
			applicationTypeBean.setDisplayName(properties.get("DisplayName").toString());
			applicationTypeBean.setEnabled(properties.get("Enable").toString());
			applicationTypeBean.setExtension(properties.get("Extension").toString());
			if(properties.get("IsUploadableAppType") != null){
				applicationTypeBean.setIsUploadableAppType(Boolean.parseBoolean(properties.get("IsUploadableAppType").toString()));
			}
			applicationTypeBean.setServerDeploymentPath(properties.get("ServerDeploymentPaths").toString());
			applicationTypeBean.setLaunchURLPattern(properties.get("LaunchURLPattern").toString());
            if(properties.get("isAllowDomainMapping") != null){
                applicationTypeBean.setIsAllowDomainMapping(Boolean.parseBoolean(properties.get("isAllowDomainMapping")
                                                                                         .toString()));
            }
			applicationTypeBean.setJenkinsJobConfig(buildJob);
            // We set the order here. This is used when displaying the apps in the UI
            // If there are no values for this, we give Integer.MAX_VALUE as the display order.
            // If there is an error in parsing the integer value, we should not stop the deployment.
            // Hence assigning Integer.MAX_VALUE if there were any exceptions
            if (properties.get("DisplayOrder") != null) {
                try {
                    applicationTypeBean.setDisplayOrder(Integer.parseInt(properties.get("DisplayOrder").toString()));
                } catch (NumberFormatException e) {
                    String msg = "Error in parsing the display order for apptype " + type;
                    log.error(msg, e);
                    applicationTypeBean.setDisplayOrder(Integer.MAX_VALUE);
                }
            } else {
                applicationTypeBean.setDisplayOrder(Integer.MAX_VALUE);
            }
            applicationTypeBean.setProperties(properties);
			applicationTypeProcessor.setProperties(properties);
			applicationTypeBean.setProcessor(applicationTypeProcessor);
			applicationTypeManager.getApplicationTypeBeanMap().put(type, applicationTypeBean);
		} catch (ClassNotFoundException e) {
			String msg = "Processor class " + className + " not found";
			log.error(msg, e);
			throw new AppFactoryException(msg, e);
		} catch (NoSuchMethodException e) {
			String msg = "Processor class " + className + " not contains no-argument constructor";
			log.error(msg, e);
			throw new AppFactoryException(msg, e);
		} catch (InvocationTargetException e) {
			String msg = "Error in invoking constructor of Processor " + className;
			log.error(msg, e);
			throw new AppFactoryException(msg, e);
		} catch (InstantiationException e) {
			String msg = "Error in creating Processor object of " + className;
			log.error(msg, e);
			throw new AppFactoryException(msg, e);
		} catch (IllegalAccessException e) {
			String msg = "Error in creating Processor object " + className;
			log.error(msg, e);
			throw new AppFactoryException(msg, e);
		}

	}

	public Map<String, ApplicationTypeBean> getApplicationTypeBeanMap() {
		return applicationTypeBeanMap;
	}

	public void setApplicationTypeBeanMap(
			Map<String, ApplicationTypeBean> applicationTypeBeanMap) {
		this.applicationTypeBeanMap = applicationTypeBeanMap;
	}
}
