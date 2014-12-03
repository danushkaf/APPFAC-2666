/*
 *
 *  * Copyright 2005-2014 WSO2, Inc. (http://wso2.com)
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

/**
 * Bean class to store application type data
 */
public class RuntimeBean {

	private String runtime;
	private String className;
	private String endpoint;
	private String providerClass;
	private String baseURL;
	private String urlPattern;
	private String adminUserName;
	private String adminPassword;
	private String aliasPrefix;
	private String cartridgeTypePrefix;
	private String deploymentPolicy;
	private String autoscalePolicy;
    private String repoURL;
    private String dataCartridgeType;
	private String dataCartridgeAlias;
	private boolean subscribeOnDeployment;

	public RuntimeBean(){
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getProviderClass() {
		return providerClass;
	}

	public void setProviderClass(String providerClass) {
		this.providerClass = providerClass;
	}

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	public String getUrlPattern() {
		return urlPattern;
	}

	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern;
	}

	public String getAdminUserName() {
		return adminUserName;
	}

	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getAliasPrefix() {
		return aliasPrefix;
	}

	public void setAliasPrefix(String aliasPrefix) {
		this.aliasPrefix = aliasPrefix;
	}

	public String getCartridgeTypePrefix() {
		return cartridgeTypePrefix;
	}

	public void setCartridgeTypePrefix(String cartridgeTypePrefix) {
		this.cartridgeTypePrefix = cartridgeTypePrefix;
	}

	public String getDeploymentPolicy() {
		return deploymentPolicy;
	}

	public void setDeploymentPolicy(String deploymentPolicy) {
		this.deploymentPolicy = deploymentPolicy;
	}

	public String getAutoscalePolicy() {
		return autoscalePolicy;
	}

	public void setAutoscalePolicy(String autoscalePolicy) {
		this.autoscalePolicy = autoscalePolicy;
	}

	public String getRepoURL() {
		return repoURL;
	}

	public void setRepoURL(String repoURL) {
		this.repoURL = repoURL;
	}

	public String getDataCartridgeType() {
		return dataCartridgeType;
	}

	public void setDataCartridgeType(String dataCartridgeType) {
		this.dataCartridgeType = dataCartridgeType;
	}

	public String getDataCartridgeAlias() {
		return dataCartridgeAlias;
	}

	public void setDataCartridgeAlias(String dataCartridgeAlias) {
		this.dataCartridgeAlias = dataCartridgeAlias;
	}

	public boolean isSubscribeOnDeployment() {
		return subscribeOnDeployment;
	}

	public void setSubscribeOnDeployment(boolean subscribeOnDeployment) {
		this.subscribeOnDeployment = subscribeOnDeployment;
	}

}
