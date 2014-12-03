package org.wso2.carbon.appfactory.jenkins.util;

import org.wso2.carbon.appfactory.common.AppFactoryConstants;

public class JenkinsUtility {
	public static String getJobName(String applicationId, String version) {
        // Job name will be '<ApplicationId>-<version>-default'
        return applicationId.concat("-").concat(version).concat("-").concat("default");
    }
	
	public static String getJobName(String applicationId, String version,String userName, String repoFrom) {
        // Job name will be '<ApplicationId>-<version>-default'
		if(AppFactoryConstants.FORK_REPOSITORY.equals(repoFrom)){
			return applicationId.concat("-").concat(version).concat("-").concat("default").concat("-").concat(userName);
        }
		return applicationId.concat("-").concat(version).concat("-").concat("default");
    }
	
	public static String getApplicationId(String jobName) {
        // Job name will be '<ApplicationId>-<version>-default'
		String[] jobValues = jobName.split("-");
        return jobValues[0];
    }
	public static String getVersion(String jobName) {
        // Job name will be '<ApplicationId>-<version>-default'
		String[] jobValues = jobName.split("-");
        return jobValues[1];
    }
}
