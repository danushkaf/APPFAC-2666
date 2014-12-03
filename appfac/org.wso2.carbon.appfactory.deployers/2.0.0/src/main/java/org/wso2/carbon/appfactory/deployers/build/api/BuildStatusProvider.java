package org.wso2.carbon.appfactory.deployers.build.api;

import java.util.Map;

public interface BuildStatusProvider {

	public Map<String, String> getLastBuildInformation(String applicationId, String version, String userName, String repoFrom) throws BuildStatusProviderException;

    public String getLastSuccessfulBuildId(String applicationId, String version, String userName, String repoFrom) throws BuildStatusProviderException;
}
