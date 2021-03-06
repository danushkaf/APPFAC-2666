package org.wso2.carbon.appfactory.nonbuild.utility;


import org.apache.commons.lang.StringUtils;
import org.wso2.carbon.appfactory.common.AppFactoryException;
import org.wso2.carbon.appfactory.core.apptype.ApplicationTypeManager;
import org.wso2.carbon.appfactory.core.deploy.Artifact;
import org.wso2.carbon.appfactory.core.governance.RxtManager;
import org.wso2.carbon.appfactory.deployers.util.DeployerUtil;

public class ApplicationBuildUtility {

	public ApplicationBuildUtility() {
		
	}
	
	public static Artifact getArtifactDetail(String applicationId,
            String version,String tenantDomain){
		RxtManager rxtManager = new RxtManager();
		Artifact artifact = null ;
		try {
			artifact = rxtManager.getAppVersionDetailArtifact(applicationId, version, tenantDomain);
		} catch (AppFactoryException e) {
			
		}
		return artifact ;
	}
	
	public static String getAppFactoryHome() throws AppFactoryException {
        try {
			String carbonHome = System.getProperty("carbon.home");
			return carbonHome ;
		} catch (Exception e) {
			throw new AppFactoryException(e);
		}
    }
	
	public static String getS2GitRepositoryProviderProperty(String stage, String propertyName, String appType)  //TO DO , This method is not used so we can remove this.
    		throws AppFactoryException{
			    String repoProperty="";
    	/*String repoProperty = DeployerUtil.getAppFactoryConfigurationProperty("ApplicationDeployment.DeploymentStage." + stage +
    				".Deployer.ApplicationTypeBean." + appType + ".RepositoryProvider.Property." + propertyName);
		
    	if ( StringUtils.isBlank(repoProperty)){
    	    repoProperty = DeployerUtil.getAppFactoryConfigurationProperty("ApplicationDeployment.DeploymentStage." + stage + 
					".Deployer.ApplicationTypeBean.*.RepositoryProvider.Property." + propertyName);
    	}*/
    	
		return repoProperty;
	}
	public static String getServerDeploymentPathPerApp(String appType) throws AppFactoryException{ //TO DO , This method is not used so we can remove this.
		return ApplicationTypeManager.getInstance().getApplicationTypeBean(appType).getServerDeploymentPath();
	} 
	
	
	public static String getSourceRepositoryProviderProperty(String propertyName) 
    		throws AppFactoryException{ //TO DO , This method is not used so we can remove this.
		String repoProperty="";
    	//String repoProperty = DeployerUtil.getAppFactoryConfigurationProperty("RepositoryProviderConfig.RepositoryProviderConfig."+propertyName);
		return repoProperty;
	}

}
