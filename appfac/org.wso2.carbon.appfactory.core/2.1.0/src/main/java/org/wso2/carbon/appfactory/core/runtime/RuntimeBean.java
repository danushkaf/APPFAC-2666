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

/**
 * Bean class to store application runtime data
 */
public class RuntimeBean {

	private String runtimeName;
	private String DeployerClassName;
	private String repositoryURLPattern;

	private String aliasPrefix;
	private String cartridgeTypePrefix;
	private String deploymentPolicy;
	private String autoscalePolicy;
    private String repoURL;
    private String dataCartridgeType;
	private String dataCartridgeAlias;
	private String subscribeOnDeployment;

	public RuntimeBean(){
	}

	public String getRuntimeName() {
		return runtimeName;
	}

	public void setRuntimeName(String runtimeName) {
		this.runtimeName = runtimeName;
	}

	public String getDeployerClassName() {
		return DeployerClassName;
	}

	public void setDeployerClassName(String deployerClassName) {
		DeployerClassName = deployerClassName;
	}

	public String getRepositoryURLPattern() {
		return repositoryURLPattern;
	}

	public void setRepositoryURLPattern(String repositoryURLPattern) {
		this.repositoryURLPattern = repositoryURLPattern;
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

	public String getSubscribeOnDeployment() {
		return subscribeOnDeployment;
	}

	public void setSubscribeOnDeployment(String subscribeOnDeployment) {
		this.subscribeOnDeployment = subscribeOnDeployment;
	}

}
