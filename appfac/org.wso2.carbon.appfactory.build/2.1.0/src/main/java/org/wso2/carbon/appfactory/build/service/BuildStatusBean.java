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

/**
 * This is a bean class for holding the build status information
 */
public class BuildStatusBean {

    private String applicationId;

    private String version;

    private String buildId;

    private boolean isBuildSuccessful;

    private String logMsg;

    private String artifactType;

    private String userName;

    private String repoFrom;

    private String triggeredUser;

    public String getTriggeredUser() {
        return triggeredUser;
    }

    public void setTriggeredUser(String triggeredBy) {
        this.triggeredUser = triggeredBy;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRepoFrom() {
        return repoFrom;
    }

    public void setRepoFrom(String repoFrom) {
        this.repoFrom = repoFrom;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isBuildSuccessful() {
        return isBuildSuccessful;
    }

    public void setBuildSuccessful(boolean buildSuccessful) {
        isBuildSuccessful = buildSuccessful;
    }

    public String getBuildId() {
        return buildId;
    }

    public void setBuildId(String buildId) {
        this.buildId = buildId;
    }

    public String getLogMsg() {
        return logMsg;
    }

    public void setLogMsg(String logMsg) {
        this.logMsg = logMsg;
    }

    public String getArtifactType() {
        return artifactType;
    }

    public void setArtifactType(String artifactType) {
        this.artifactType = artifactType;
    }
}
