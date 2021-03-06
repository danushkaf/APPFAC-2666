package org.wso2.carbon.appfactory.deployers;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.plexus.util.FileUtils;
import org.wso2.carbon.appfactory.common.AppFactoryConstants;
import org.wso2.carbon.appfactory.common.AppFactoryException;
import org.wso2.carbon.appfactory.deployers.clients.AppfactoryRepositoryClient;
import org.wso2.carbon.appfactory.deployers.util.DeployerUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public abstract class AbstractStratosDeployer extends AbstractDeployer {

    private static final Log log = LogFactory
            .getLog(AbstractStratosDeployer.class);

    protected void deploy(String artifactType, File[] artifactsToDeploy,
                          Map<String, String[]> parameters, Boolean notify)
            throws AppFactoryException {

        String applicatonId = DeployerUtil.getParameter(parameters,AppFactoryConstants.APPLICATION_ID);
        String version = DeployerUtil.getParameter(parameters,AppFactoryConstants.APPLICATION_VERSION);
        String deployStage = DeployerUtil.getParameter(parameters,AppFactoryConstants.DEPLOY_STAGE);
        String serverDeploymentPath = DeployerUtil.getParameter(parameters,AppFactoryConstants.SERVER_DEPLOYMENT_PATHS);
        String tenantDomain = getTenantDomain();

        String condition = applicatonId + "-" + version + "-" + deployStage
                + "-" + tenantDomain;
        synchronized (condition.intern()) {

            File artifactToDeploy = artifactsToDeploy[0];
            String fileName = artifactToDeploy.getName();
            addToGitRepo(fileName, artifactToDeploy, parameters,
                         artifactType, serverDeploymentPath, null);

            if (notify) {
                postDeploymentNoifier("", applicatonId, version, artifactType,
                        deployStage, tenantDomain);
            }

        }
    }

    private void addToGitRepo(String fileName, File artifacts, Map metadata,
                              String appTypeName, String serverDeploymentPath,
                              String relativePathFragment) throws AppFactoryException {

        // subscribeOnDeployment is true or not
        boolean subscribeOnDeployment = Boolean.parseBoolean(
                DeployerUtil.getParameterValue(metadata, AppFactoryConstants.RUNTIME_SUBSCRIBE_ON_DEPLOYMENT));
        String applicationId = DeployerUtil.getParameterValue(metadata,
                AppFactoryConstants.APPLICATION_ID);

        int tenantId = getTenantID();
        String gitRepoUrl = generateRepoUrl(applicationId, metadata, tenantId,
                appTypeName, subscribeOnDeployment);
        String stageName = DeployerUtil.getParameterValue(metadata,
                AppFactoryConstants.DEPLOY_STAGE);
        String username=((String[])metadata.get("tenantUserName"))[0];
        // if subscribeOnDeployment is true create a git repo per application
        if (subscribeOnDeployment) {
            if (log.isDebugEnabled()) {
                log.debug("SubscribeOnDeployment is true");
            }
            String url = SubscriptionHandler.getInstance().createSubscription(metadata, stageName, username,
                                                                tenantId, applicationId, getTenantDomain());
        } else {
            if (log.isDebugEnabled()) {
                log.debug("SubscribeOnDeployment is false");
            }
        }
        String applicationAdmin = getAdminUserName();
        String defaultPassword = getAdminPassword();

        // Create the temporary directory first. without this we can't proceed
        File tempApptypeDirectory = new File(tempPath + File.separator
                + stageName + File.separator + appTypeName); // <tempdir>/war ,
        // <tempdir>/jaxrs,
        if (!tempApptypeDirectory.exists()) {
            if (!tempApptypeDirectory.mkdirs()) {
                String msg = "Unable to create temp directory : "
                        + tempApptypeDirectory.getAbsolutePath();
                handleException(msg);
            }
        }

        String appTypeDirectory = null;
        //
        if (serverDeploymentPath != null) {
            appTypeDirectory = tempApptypeDirectory.getAbsolutePath()
                    + File.separator + serverDeploymentPath; // tempdir/<war>webapps
            // ,tempdir/jaggery/jaggeryapps,
            // //tempdir/esb/synapse-config
        } else {
            appTypeDirectory = tempApptypeDirectory.getAbsolutePath();
        }

        String deployableFileName = null;

        if (StringUtils.isBlank(relativePathFragment)) {
            deployableFileName = appTypeDirectory + File.separator + fileName; // tempdir/webapps/myapp.war
            // ,
            // tempdir/jappgeryapps/myapp.war
        } else {
            deployableFileName = appTypeDirectory + File.separator
                    + relativePathFragment + File.separator + fileName; // <tempdir>/synapse-config/proxy-services/MyProxy.xml
        }

        if (log.isDebugEnabled()) {
            log.debug("Deployable file name to be git push:"
                    + deployableFileName);
        }

        AppfactoryRepositoryClient repositoryClient = new AppfactoryRepositoryClient(
                "git");
        try {
            repositoryClient.init(applicationAdmin, defaultPassword);

            File appTypeFile = new File(appTypeDirectory);

            if (tempApptypeDirectory.isDirectory()
                    && tempApptypeDirectory.list().length > 0) {
                // This means that there are files in the given location. We
                // check whether they are previous checkouts
                if (!repositoryClient.update(gitRepoUrl, appTypeFile)) {
                    // This means that the update has failed. So we clean this
                    // directory.
                    File[] filesToDelete = tempApptypeDirectory.listFiles();

                    if (filesToDelete != null) {
                        for (File fileToDelete : filesToDelete) {
                            try {
                                if (fileToDelete.isDirectory()) {
                                    FileUtils.deleteDirectory(fileToDelete);
                                } else {
                                    FileUtils.forceDelete(fileToDelete);
                                }
                            } catch (IOException e) {
                                // We ignore any exception here.
                            }
                        }
                    }

                    // Now we take the checkout
                    repositoryClient.checkOut(gitRepoUrl, tempApptypeDirectory);
                }
            } else {
                // this means no files exists, so we straight away check out the
                // repo
                repositoryClient.checkOut(gitRepoUrl, tempApptypeDirectory);
            }

            File deployableFile = new File(deployableFileName);
            if (!deployableFile.getParentFile().exists()) {
                log.debug("deployableFile.getParentFile() doesn't exist: "
                        + deployableFile.getParentFile());
                if (!deployableFile.getParentFile().mkdirs()) {
                    String msg = "Unable to create parent path of the deployable file "
                            + deployableFile.getAbsolutePath();
                    // unable to create <tempdir>/war/webapps,
                    // <tempdir>/jaggery/jaggeryapps
                    // or <tempdir>/esb/synapse-config/default/proxy-services
                    handleException(msg);
                }
            }

            // If there is a file in repo, we delete it first
            if (deployableFile.exists()) {
                repositoryClient.remove(gitRepoUrl, deployableFile,
                        "Removing the old file to add the new one");
                // Checking and removing the local file
                if (deployableFile.exists()) {
                    deployableFile.delete();
                }
                // repositoryClient.checkIn(gitRepoUrl, applicationTempLocation,
                // "Removing the old file to add the new one");

                try {
                    deployableFile = new File(deployableFileName);
                    // check weather directory exists.
                    if (!deployableFile.getParentFile().isDirectory()) {
                        log.debug("parent directory : "
                                + deployableFile.getParentFile()
                                .getAbsolutePath()
                                + " doesn't exits creating again");
                        if (!deployableFile.getParentFile().mkdirs()) {
                            throw new IOException("Unable to re-create "
                                    + deployableFile.getParentFile()
                                    .getAbsolutePath());
                        }

                    }

                    if(artifacts.isFile()){
                        if (!deployableFile.createNewFile()) {
                            throw new IOException(
                                    "unable re-create the target file : "
                                            + deployableFile.getAbsolutePath());
                        }
                        if (deployableFile.canWrite()) {
                            log.debug("Successfully re-created a writable file : "
                                    + deployableFileName);
                        } else {
                            String errorMsg = "re-created file is not writable: "
                                    + deployableFileName;
                            log.error(errorMsg);
                            throw new IOException(errorMsg);
                        }
                    }

                } catch (IOException e) {
                    log.error(
                            "Unable to create the new file after deleting the old: "
                                    + deployableFile.getAbsolutePath(), e);
                    throw new AppFactoryException(e);
                }
            }

            copyFilesToGit(artifacts, deployableFile);

            if (repositoryClient.add(gitRepoUrl, new File(deployableFileName))) {
                if (!repositoryClient.checkIn(gitRepoUrl, tempApptypeDirectory,
                        "Adding the artifact to the repo")) {
                    String msg = "Unable to commit files to git";
                    handleException(msg);
                }
            } else {
                String msg = "Unable to add files to git";
                handleException(msg);
            }
        } catch (AppFactoryException e) {
            String msg = "Unable to copy files to git location";
            handleException(msg, e);
        }
    }

    private void copyFilesToGit(File sourceFile, File destinationFile)
            throws AppFactoryException {
        try {
            if (sourceFile.isFile()) {
                FileUtils.copyFile(sourceFile, destinationFile);
            } else if(sourceFile.isDirectory()){
                FileUtils.copyDirectory(sourceFile, destinationFile);
            }

        } catch (FileNotFoundException e) {
            log.error(e);
            throw new AppFactoryException(e);
        } catch (IOException e) {
            log.error(e);
            throw new AppFactoryException(e);
        }
    }

    protected String generateRepoUrl(String applicationId, Map metadata,
                                     int tenantId, String appType, boolean subscribeOnDeployment)
            throws AppFactoryException {
        String paasRepositoryURLPattern = DeployerUtil.getParameter(metadata,
                                                                    AppFactoryConstants.PAAS_REPOSITORY_URL_PATTERN);
        String stage = DeployerUtil.getParameterValue(metadata,AppFactoryConstants.DEPLOY_STAGE);
        String baseUrl = getBaseRepoUrl();
        String gitRepoUrl = "";
        if (subscribeOnDeployment) {
            gitRepoUrl = baseUrl + "git/" + paasRepositoryURLPattern + File.separator
                    + tenantId + File.separator + applicationId + tenantDomain.replace(".", "dot") + ".git";
        } else {
            gitRepoUrl = baseUrl + "git/" + paasRepositoryURLPattern + File.separator + tenantId + ".git";
        }
        gitRepoUrl = gitRepoUrl.replace("{@stage}", stage);
        if (log.isDebugEnabled()) {
            log.debug("Git URL : " + gitRepoUrl);
        }
        return gitRepoUrl;
    }

    @Override
    public void unDeployArtifact(Map<String, String[]> requestParameters)
            throws Exception {
        // To change body of implemented methods use File | Settings | File
        // Templates.
    }

    protected abstract String getBaseRepoUrl()
            throws AppFactoryException;

    protected abstract String getAdminUserName()
            throws AppFactoryException;

    protected abstract String getAdminPassword()
            throws AppFactoryException;

}
