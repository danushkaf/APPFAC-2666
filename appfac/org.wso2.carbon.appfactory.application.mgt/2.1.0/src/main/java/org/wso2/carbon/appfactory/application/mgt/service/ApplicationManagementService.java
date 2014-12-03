/*
 * Copyright 2005-2011 WSO2, Inc. (http://wso2.com)
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

package org.wso2.carbon.appfactory.application.mgt.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.CarbonConstants;
import org.wso2.carbon.appfactory.application.mgt.service.applicationqueue.ApplicationCreator;
import org.wso2.carbon.appfactory.application.mgt.util.UserApplicationCache;
import org.wso2.carbon.appfactory.application.mgt.util.Util;
import org.wso2.carbon.appfactory.common.bam.BamDataPublisher;
import org.wso2.carbon.appfactory.common.AppFactoryConstants;
import org.wso2.carbon.appfactory.common.AppFactoryException;
import org.wso2.carbon.appfactory.common.util.AppFactoryUtil;
import org.wso2.carbon.appfactory.core.ApplicationEventsHandler;
import org.wso2.carbon.appfactory.core.cache.AppTypeCache;
import org.wso2.carbon.appfactory.core.dao.JDBCApplicationDAO;
import org.wso2.carbon.appfactory.core.deploy.Artifact;
import org.wso2.carbon.appfactory.core.dto.*;
import org.wso2.carbon.appfactory.core.governance.RxtManager;
import org.wso2.carbon.appfactory.core.internal.ServiceHolder;
import org.wso2.carbon.appfactory.core.queue.AppFactoryQueueException;
import org.wso2.carbon.appfactory.core.util.AppFactoryCoreUtil;
import org.wso2.carbon.appfactory.core.util.CommonUtil;
import org.wso2.carbon.appfactory.core.util.Constants;
import org.wso2.carbon.appfactory.eventing.AppFactoryEventException;
import org.wso2.carbon.appfactory.eventing.Event;

import org.wso2.carbon.appfactory.eventing.EventNotifier;
import org.wso2.carbon.appfactory.eventing.builder.utils.AppCreationEventBuilderUtil;
import org.wso2.carbon.appfactory.eventing.builder.utils.ContinousIntegrationEventBuilderUtil;
import org.wso2.carbon.appfactory.jenkins.build.JenkinsCISystemDriver;
import org.wso2.carbon.appfactory.utilities.project.ProjectUtils;
import org.wso2.carbon.appfactory.utilities.services.EmailSenderService;
import org.wso2.carbon.context.CarbonContext;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.core.AbstractAdmin;
import org.wso2.carbon.governance.api.exception.GovernanceException;
import org.wso2.carbon.governance.api.generic.GenericArtifactManager;
import org.wso2.carbon.governance.api.generic.dataobjects.GenericArtifact;
import org.wso2.carbon.registry.core.ActionConstants;
import org.wso2.carbon.registry.core.RegistryConstants;
import org.wso2.carbon.registry.core.exceptions.RegistryException;
import org.wso2.carbon.registry.core.service.RegistryService;
import org.wso2.carbon.registry.core.session.UserRegistry;
import org.wso2.carbon.user.api.*;
import org.wso2.carbon.user.core.service.RealmService;
import org.wso2.carbon.appfactory.tenant.mgt.beans.UserInfoBean;

import java.util.*;

public class ApplicationManagementService extends AbstractAdmin {

    private static Log log = LogFactory.getLog(ApplicationManagementService.class);

    public static String EMAIL_CLAIM_URI = "http://wso2.org/claims/emailaddress";
    public static String FIRST_NAME_CLAIM_URI = "http://wso2.org/claims/givenname";
    public static String LAST_NAME_CLAIM_URI = "http://wso2.org/claims/lastname";

    public static UserApplicationCache userApplicationCache = UserApplicationCache.getUserApplicationCache();
    public static JDBCApplicationDAO applicationDAO=JDBCApplicationDAO.getInstance();

    /**
     * This createApplication method is used for the create an application. When
     * call this method, it put to the queue. TODO:Make it work in cluster and
     * MT environment
     *
     * @param applicationName
     *            Application name.
     * @param applicationKey
     *            Key for the Application. This should be unique.
     * @param applicationDescription
     *            Description of the application.
     * @param applicationType
     *            Type of the application. ex: war, jaxrs, jaxws ...
     * @param repositoryType
     *            Type of the repository that should use. ex: svn, git
     * @param userName
     *            Logged-in user name.
     */
    public void createApplication(String applicationName, String applicationKey, String applicationDescription,
                                  String applicationType, String repositoryType,
                                  String userName) throws ApplicationManagementException {

        ApplicationInfoBean applicationInfoBean = new ApplicationInfoBean();
        applicationInfoBean.setName(applicationName);
        applicationInfoBean.setApplicationKey(applicationKey);
        applicationInfoBean.setDescription(applicationDescription);
        applicationInfoBean.setApplicationType(applicationType);
        applicationInfoBean.setRepositoryType(repositoryType);
        applicationInfoBean.setOwnerUserName(userName);

        try {
            ApplicationCreator applicationCreator = ApplicationCreator.getInstance();
            applicationCreator.getExecutionEngine().getSynchQueue().put(applicationInfoBean);

            BamDataPublisher publisher = BamDataPublisher.getInstance();
            String tenantId = "" + Util.getRealmService().getBootstrapRealmConfiguration().getTenantId();
            //TODO: Check if we need to put the repo accessability into this also
            publisher.PublishAppCreationEvent(applicationName, applicationKey, applicationDescription, applicationType,
                                              repositoryType, System.currentTimeMillis(), tenantId, userName);

        } catch (AppFactoryQueueException e) {
            String errorMsg = "Error occured when adding an application in to queue, " + e.getMessage();
            log.error(errorMsg, e);
            throw new ApplicationManagementException(errorMsg, e);
        } catch (AppFactoryException e) {
            String msg = "Unable to publish data to BAM";
            log.error(msg, e);
            throw new ApplicationManagementException(msg, e);
        }
    }

    /**
     * Checks if the given application key already exists in the database.
     * This check is done against the AppFactory database
     *
     * @param applicationKey the application key to be checked
     * @return false if application key exists
     * @throws ApplicationManagementException
     */
    public boolean isApplicationIdAvailable(String applicationKey) throws ApplicationManagementException {
        try {
            // We need to return the opposite of what the DAO returns.
            // The DAO will return true if the given application key exists
            // Therefore we need to return false since the application id is not available
            return !applicationDAO.isApplicationKeyExists(applicationKey);
        } catch (AppFactoryException e) {
            String msg = "Error while validating application key :  " + applicationKey;
            log.error(msg);
            throw new ApplicationManagementException(msg, e);
        }
    }

    /**
     * Checks if the provided application name is already available for the given tenant
     *
     * @param applicationName the application name to be checked
     * @return false if application name exists
     * @throws ApplicationManagementException
     */
    public boolean isApplicationNameAvailable(String applicationName) throws ApplicationManagementException {
        try {
            // We need to return the opposite of what the DAO returns.
            // The DAO will return true if the given application name exists
            // Therefore we need to return false since the application name is not available
            return !applicationDAO.isApplicationNameExists(applicationName);
        } catch (AppFactoryException e) {
            String msg = "Error while validating application name :  " + applicationName;
            log.error(msg);
            throw new ApplicationManagementException(msg, e);
        }
    }

	/**
	 * Gets all the applications for tenant
	 * @return Application array
	 * @throws AppFactoryException
	 */
    public Application[] getAllApplications() throws AppFactoryException {
        String tenantDomain = getTenantDomain();
	    List<Application> applications;
	    try {
		    applications = ProjectUtils.getAllApplicationInfo(tenantDomain);
	    } catch (AppFactoryException e) {
		    String msg = "Error while getting applications";
		    log.info(msg, e);
		    throw new AppFactoryException(msg, e);
	    }
	    return applications.toArray(new Application[applications.size()]);
    }


    public String getStage(String applicationId, String version) throws ApplicationManagementException {
        try {
            // Getting the tenant domain
            String tenantDomain = CarbonContext.getThreadLocalCarbonContext().getTenantDomain();

            return new RxtManager().getStage(applicationId, version, tenantDomain);
        } catch (AppFactoryException e) {
            String msg = "Unable to get stage for " + applicationId + "and version : " + version;
            log.error(msg, e);
            throw new ApplicationManagementException(msg, e);
        }
    }

    //Todo:remove domainName and userName after updating bpel
    public void publishApplicationCreation(String domainName, String userName, String applicationId, String applicationType)
            throws ApplicationManagementException {
        // New application is created successfully so now time to clear realm in
        // cache to reload
        // the new realm with updated permissions

        clearRealmCache(applicationId);
        domainName = CarbonContext.getThreadLocalCarbonContext().getTenantDomain();
        userName = CarbonContext.getThreadLocalCarbonContext().getUsername() +"@"+domainName;
        if (log.isDebugEnabled()) {
            log.debug("Application creation is started by user:" + userName + " in tenant domain:" + domainName);
        }
        Iterator<ApplicationEventsHandler> appEventListeners = Util.getApplicationEventsListeners().iterator();
        ApplicationEventsHandler listener = null;
        Application application = null;
        try {
            // creates role for application in the ldap to add users of the
            // application

            // Please see the comment in line:244
            // Application application1=new Application();
            // application1.setId(applicationId);
            // application1.setType(applicationType);
            // applicationDAO.addApplication(application1);

            createApplicationRole(applicationId, userName, domainName);
            addRegistryWritePermissionToApp(applicationId, domainName);
            application = ProjectUtils.getApplicationInfo(applicationId, domainName);
            if (application == null) {
                String errorMsg = String.format("Unable to load application information for id %s", applicationId);
                throw new ApplicationManagementException(errorMsg);
            }

            // IMO, we should only add application information to AppFactory DB only if the above condition fails.
            // If there are information in the registry, then only we should add them to DB
            // Hence moving that code here.
            applicationDAO.addApplication(application);

            boolean isUploadableAppType = AppFactoryCoreUtil.isUplodableAppType(application.getType());

            while (appEventListeners.hasNext()) {
                try {
                    listener = appEventListeners.next();
                    listener.onCreation(application, userName, domainName, isUploadableAppType);
                } catch (Throwable e) {
                    String error = "Error while executing onCreation method of ApplicationEventsListener : " + listener + " due to " + e.getMessage();
                    log.error(error, e);
                    this.deleteApplication(application, userName, domainName);
                    try {
                        String errorMessage = "Error while creating the app " + applicationId;
                        if(error.contains("JenkinsApplicationEventsListener")){
                            errorMessage =  "Error occurred while creating the Jenkins space for the app " + applicationId;
                        }

                        EventNotifier.getInstance().notify(AppCreationEventBuilderUtil.buildApplicationCreationEvent(
                                                                                                          "Application creation failed for " + applicationId,
                                                                                                          errorMessage.concat(". Therefore application will be rollbacked."), Event.Category.ERROR));

                    } catch (AppFactoryEventException e1) {
                        log.error("Failed to notify application creation failed events", e1);
                        // do not throw again.
                    }
                    break;
                }
            }
            ProjectUtils.updateApplicationCreationStatus(applicationId, Constants.ApplicationCreationStatus.COMPLETED);
        } catch (AppFactoryException ex) {
            String errorMsg = "Unable to load registry rxt for application " + applicationId + " due to : " + ex.getMessage();
            log.error(errorMsg, ex);
            if (application != null) {
                try {
                    this.deleteApplication(application, userName, domainName);
                } catch (AppFactoryException e) {
                    log.error("Failed to delete the application on roll back.", e);
                    // ignore throwing again
                }
            }
            try {
                String errorDescription = "Unable to load registry for application " + applicationId + ". Therefore application will be rollbacked.";
                EventNotifier.getInstance().notify(AppCreationEventBuilderUtil.buildApplicationCreationEvent(
                                                                                                  "Application creation failed for " + applicationId,
                                                                                                  errorDescription, Event.Category.ERROR));
            } catch (AppFactoryEventException e1) {
                log.error("Failed to notify application creation failed events",e1);
                // do not throw again.
            }
            throw new ApplicationManagementException(errorMsg, ex);
        } catch (UserStoreException e) {
            String errorMsg = "Unable to add application role to the userstore: " + e.getMessage();
            log.error(errorMsg, e);
            if (application != null) {
                try {
                    this.deleteApplication(application, userName, domainName);
                } catch (AppFactoryException e2) {
                    log.error("Failed to delete the application on roll back.", e2);
                    // ignore throwing again
                }
            }
            try {
                //   if (errorMsg.con)
                EventNotifier.getInstance().notify(AppCreationEventBuilderUtil.buildApplicationCreationEvent(
                                                                                                  "Application creation failed for " + applicationId,
                                                                                                  errorMsg.concat("Therefore application will be rollback."), Event.Category.ERROR));
            } catch (AppFactoryEventException e1) {
                log.error("Failed to notify application creation failed events",e1);
                // do not throw again.
            }
            throw new ApplicationManagementException(errorMsg, e);
        }
    }

    public void publishApplicationVersionCreation(String domainName, String applicationId, String sourceVersion,
                                                  String targetVersion) throws ApplicationManagementException {
        try {

            // Getting the tenant ID from the CarbonContext since this is called
            // as a SOAP service.
            CarbonContext threadLocalCarbonContext = CarbonContext.getThreadLocalCarbonContext();
            domainName = threadLocalCarbonContext.getTenantDomain();
            String userName = threadLocalCarbonContext.getUsername();

            Version version=new Version(targetVersion);
            applicationDAO.addVersion(applicationId,version);
            Iterator<ApplicationEventsHandler> appEventListeners = Util.getApplicationEventsListeners().iterator();

            Application application = ProjectUtils.getApplicationInfo(applicationId, domainName);
            String applicationType = AppFactoryCoreUtil.getApplicationType(applicationId, domainName);

            Version[] versions = ProjectUtils.getVersions(applicationId, domainName);

            // find the versions.
            Version source = null;
            Version target = null;
            for (Version v : versions) {
                if (v.getId().equals(sourceVersion)) {
                    source = v;
                }

                if (v.getId().equals(targetVersion)) {
                    target = v;
                }

                if (source != null && target != null) {
                    // both version are found. no need to traverse more
                    break;
                }
            }

            ApplicationEventsHandler listener = null ;
            while (appEventListeners.hasNext()) {
                try {
                    listener = appEventListeners.next();
                    listener.onVersionCreation(application, source, target, domainName, userName);
                } catch (Throwable e) {
                    log.error("Error while executing onVersionCreation method of ApplicationEventsListener : " + listener, e);
                }
            }

        } catch (AppFactoryException ex) {
            String errorMsg = "Unable to publish version creation due to " + ex.getMessage();
            log.error(errorMsg, ex);
            throw new ApplicationManagementException(errorMsg, ex);
        } catch (RegistryException e) {
            log.error(e);
            throw new ApplicationManagementException(e);
        }
    }






    public void publishForkRepository(String applicationId, String type, String version, String userName, String[] forkedUser) throws ApplicationManagementException {
        try {

            // Getting the tenant ID from the CarbonContext since this is called
            // as a SOAP service.
            CarbonContext threadLocalCarbonContext = CarbonContext.getThreadLocalCarbonContext();

            String domainName = threadLocalCarbonContext.getTenantDomain();

            Iterator<ApplicationEventsHandler> appEventListeners = Util.getApplicationEventsListeners().iterator();

            Application application = ProjectUtils.getApplicationInfo(applicationId, domainName);
            String applicationType = AppFactoryCoreUtil.getApplicationType(applicationId, domainName);

            ApplicationEventsHandler listener = null ;
            while (appEventListeners.hasNext()) {
                try {
                    listener = appEventListeners.next();
                    listener.onFork(application, userName, domainName, version,forkedUser);
                } catch (Throwable e) {
                    log.error("Error while executing onFork method of ApplicationEventsHandler : " + listener, e);
                }
            }


        } catch (AppFactoryException ex) {
            String errorMsg = "Unable to publish onForking due to " + ex.getMessage();
            log.error(errorMsg, ex);
            throw new ApplicationManagementException(errorMsg, ex);
        } catch (RegistryException e) {
            log.error(e);
            throw new ApplicationManagementException(e);
        }
    }





    /**
     * Service method to make the application related to given {@code applicationId} auto build.
     *
     * @param applicationId
     * @param stage
     * @param version
     * @param isAutoBuildable
     * @throws ApplicationManagementException
     */
    public void publishSetApplicationAutoBuild(String applicationId, String stage, String version,
                                               boolean isAutoBuildable) throws ApplicationManagementException {
        log.info("Auto build change event recieved for : " + applicationId + " " + " Version : " + version +
                 " stage :" + stage + " isAutoBuildable :" + isAutoBuildable);

        // Getting the tenant domain
        String tenantDomain = CarbonContext.getThreadLocalCarbonContext().getTenantDomain();

        updateRxtWithBuildStatus(applicationId, stage, version, isAutoBuildable, tenantDomain);

        try {
            JenkinsCISystemDriver jenkinsCISystemDriver =
                    (JenkinsCISystemDriver) Util.getContinuousIntegrationSystemDriver();
            // TODO this from configuration
            int pollingPeriod = 6;

            jenkinsCISystemDriver.setJobAutoBuildable(applicationId, version, isAutoBuildable, pollingPeriod,
                                                      tenantDomain);

            // Removing App version cache related code
            // Clear the cache
            // AppVersionCache.getAppVersionCache().clearCacheForAppId(applicationId);

            log.info("Application : " + applicationId + " successfully configured for auto building " + isAutoBuildable);
        } catch (AppFactoryException e) {
            String msg = "Error occured while updating jenkins configuration";
            log.error(msg, e);
            throw new ApplicationManagementException(msg);
        }

    }

    /**
     * Service method to make the application related to given {@code applicationId} auto deploy.
     *
     * @param applicationId
     * @param stage
     * @param version
     * @param isAutoDeployable
     * @throws ApplicationManagementException
     */
    public void publishSetApplicationAutoDeploy(String applicationId, String stage, String version,
                                                boolean isAutoDeployable) throws ApplicationManagementException {
        log.info("Auto deploy change event recieved for : " + applicationId + " " + " Version : " + version +
                 " stage :" + stage + " isAutoBuildable :" + isAutoDeployable);

        // Getting the tenant domain
        String tenantDomain = CarbonContext.getThreadLocalCarbonContext().getTenantDomain();

        updateRxtWithDeplymentStatus(applicationId, stage, version, isAutoDeployable, tenantDomain);
        try {
            String applicationType = AppFactoryCoreUtil.getApplicationType(applicationId, tenantDomain);
            boolean appIsBuildable = AppFactoryCoreUtil.isBuildable(applicationType);

            if (appIsBuildable) {
                JenkinsCISystemDriver jenkinsCISystemDriver =
                        (JenkinsCISystemDriver) Util.getContinuousIntegrationSystemDriver();

                jenkinsCISystemDriver.setJobAutoDeployable(applicationId, version, isAutoDeployable, tenantDomain);
            }
            // Removing app version cache related code
            // Clear the cache
            // AppVersionCache.getAppVersionCache().clearCacheForAppId(applicationId);

            log.info("Application : " + applicationId + " sccessfully configured for auto deploy " + isAutoDeployable);
        } catch (AppFactoryException e) {
            String msg = "Error occured while updating jenkins configuration";
            log.error(msg, e);
            throw new ApplicationManagementException(msg);
        } catch (RegistryException e) {
            String msg = "Error occured while reading regstry";
            log.error(msg, e);
            throw new ApplicationManagementException(msg);
        }
    }

    /**
     * Updates the rxt registry with given auto build information.
     *
     * @param applicationId
     * @param stage
     * @param version
     * @param isAutoBuildable
     * @throws ApplicationManagementException
     */
    private void updateRxtWithBuildStatus(String applicationId, String stage, String version, boolean isAutoBuildable,
                                          String tenantDomain) throws ApplicationManagementException {
        RxtManager rxtManager = new RxtManager();
        try {
            rxtManager.updateAppVersionRxt(applicationId, version, "appversion_isAutoBuild",
                                           String.valueOf(isAutoBuildable), tenantDomain);
            log.debug(" Rtx updated successfully for : " + applicationId + " " + " Version : " + version + " stage :" +
                    stage + " isAutoBuildable :" + isAutoBuildable);

        } catch (AppFactoryException e) {
            String msg = "Error occured while updating the rxt with auto-build status";
            log.error(msg, e);
            throw new ApplicationManagementException(msg);
        }
    }

    /**
     * Updating Rxt value when do the  promote action
     *
     * @param applicationId
     * @param stage
     * @param version
     * @param action
     * @throws ApplicationManagementException
     */
    public void updateRxtWithPromoteState(String applicationId, String stage, String version, String action, String state) throws ApplicationManagementException {
        String tenantDomain = CarbonContext.getThreadLocalCarbonContext().getTenantDomain();
        if(action==null || !action.equals("Promote")){
            return ;
        }
        try {
            applicationDAO.updatePromoteStatusOfVersion(applicationId,version,state);
            log.debug(" Updated successfully for : " + applicationId + " " + " Version : " +
                    version + " stage :" +
                    stage + " Promote is Pending state");

        } catch (AppFactoryException e) {
            String msg = "Error occurred while updating with promote status";
            log.error(msg, e);
            throw new ApplicationManagementException(msg);
        }
    }

    /**
     * Updates the rxt registry with given auto deploy information.
     *
     * @param applicationId
     * @param stage
     * @param version
     * @param isAutoDeployable
     * @throws ApplicationManagementException
     */
    private void updateRxtWithDeplymentStatus(String applicationId, String stage, String version,
                                              boolean isAutoDeployable, String tenantDomain)
                                                      throws ApplicationManagementException {
        RxtManager rxtManager = new RxtManager();
        try {
            rxtManager.updateAppVersionRxt(applicationId, version, "appversion_isAutoDeploy",
                                           String.valueOf(isAutoDeployable), tenantDomain);
            log.debug(" Rtx updated successfully for : " + applicationId + " " + " Version : " + version + " stage :" +
                    stage + " isAutoDeployable :" + isAutoDeployable);

        } catch (AppFactoryException e) {
            String msg = "Error occured while updating the rxt with auto-build status";
            log.error(msg, e);
            throw new ApplicationManagementException(msg);
        }
    }

    public String addArtifact(String key, String info, String lifecycleAttribute) throws AppFactoryException {
        return new RxtManager().addArtifact(key, info, lifecycleAttribute);
    }

    private void clearRealmCache(String applicationKey) throws ApplicationManagementException {
        RealmService realmService = Util.getRealmService();
        int tenantID;
        try {
            tenantID = Util.getRealmService().getTenantManager().getTenantId(applicationKey);
            realmService.clearCachedUserRealm(tenantID);
        } catch (UserStoreException e) {
            String errorMsg =
                    "Unable to clear user realm cache for tenant id  " + applicationKey + " due to : " +
                            e.getMessage();
            log.error(errorMsg, e);
            throw new ApplicationManagementException(errorMsg, e);
        }
    }


    public Application getApplication(String applicationId) throws ApplicationManagementException {
        String domainName = getTenantDomain();
        try {
            return ProjectUtils.getApplicationInfo(applicationId, domainName);
        } catch (AppFactoryException e) {
            String message = "Failed to read application info for " + applicationId + " in tenant " + domainName;
            log.error(message);
            throw new ApplicationManagementException(message, e);
        }
    }

    public boolean deleteApplication(Application application, String userName, String domainName) throws AppFactoryException, ApplicationManagementException {
        boolean completedSuccessfully = true;

        String applicationId = application.getId();

        Iterator<ApplicationEventsHandler> appEventListeners = Util.getApplicationEventsListeners().iterator();
        UserInfoBean[] userList = new ApplicationUserManagementService().getUsersOftheApplication(applicationId);
        ApplicationEventsHandler listener;
        while (appEventListeners.hasNext()) {
            listener = appEventListeners.next();
            try {
                if (listener.hasExecuted(application, userName, domainName)) {
                    listener.onDeletion(application, userName, domainName);
                }
            } catch (AppFactoryException e) {
                log.error("Error in calling onDeletion method of ApplicationEventsListener : " + listener, e);
            }

        }

        try{
            //TODO check existence of this
            removeApplicationRoles(applicationId, userName, domainName);
        } catch (UserStoreException e) {
            log.error("Error while removing the application roles from LDAP for application " + applicationId, e);
        }

        try {
            removeAppFromRegistry(applicationId, domainName);
        } catch (UserStoreException e) {
            log.error("Error while deleting the application resource from registry for application " + applicationId, e);
        } catch (RegistryException e) {
            log.error("Error while deleting the application resource from registry for application " + applicationId, e);
        }
        applicationDAO.deleteApplication(applicationId);
        //clear applicationtype from cache
        AppTypeCache appTypeCache = AppTypeCache.getAppTypeCache();
        appTypeCache.clearCacheForAppId(domainName, applicationId);

        String adminEmail = AppFactoryUtil.getAdminEmail();
        new EmailSenderService().sendMail(adminEmail, "application-rollback-notice-email.xml", createUserParams(application));

        String title = "Application " + applicationId + " is deleted successfully";
        String messageDescription = "Deleted by: " + userName;
        try {

            for (UserInfoBean anUserList : userList) {
                EventNotifier.getInstance().notify(AppCreationEventBuilderUtil.buildApplicationDeletionEvent(anUserList.getUserName().concat("@").concat(domainName), title, messageDescription, userName, Event.Category.INFO));
            }

        } catch (AppFactoryEventException e) {
            log.error("Failed to notify application deletion event " + e.getMessage(), e);
        }

        return completedSuccessfully;

    }

    private String[][] createUserParams(Application application) {
        String[][] userParams = new String[4][2];
        userParams[0][0] = "adminUserName";
        userParams[0][1] = AppFactoryUtil.getAdminUsername();
        userParams[1][0] = "applicationName";
        userParams[1][1] = application.getName();
        userParams[2][0] = "applicationKey";
        userParams[2][1] = application.getId();
        userParams[3][0] = "tenantDomain";
        userParams[3][1] = CarbonContext.getThreadLocalCarbonContext().getTenantDomain();
        return userParams;
    }

    /**
     * Creates the role for the application in the ldap.It appends the
     * APP_ROLE_PREFIX to the appkey and construct the ldap role name
     *
     * @param applicationKey
     * @param appOwner
     * @param tenantDomain
     * @return
     * @throws UserStoreException
     */
    private boolean createApplicationRole(String applicationKey, String appOwner, String tenantDomain)
            throws UserStoreException {
        RealmService realmService = Util.getRealmService();
        TenantManager tenantManager = realmService.getTenantManager();
        int tenantId = tenantManager.getTenantId(tenantDomain);
        PrivilegedCarbonContext threadLocalCarbonContext = null;
        try {
            PrivilegedCarbonContext.startTenantFlow();
            threadLocalCarbonContext = PrivilegedCarbonContext.getThreadLocalCarbonContext();
            threadLocalCarbonContext.setTenantId(tenantId, true);
            UserStoreManager userStoreManager = realmService.getTenantUserRealm(tenantId).getUserStoreManager();
            userStoreManager.addRole(AppFactoryUtil.getRoleNameForApplication(applicationKey),
                                     new String[] { appOwner.split("@")[0] },
                                     new org.wso2.carbon.user.core.Permission[] { new org.wso2.carbon.user.core.Permission(
                                                                                                                           AppFactoryConstants.PER_APP_ROLE_PERMISSION,
                                                                                                                           CarbonConstants.UI_PERMISSION_ACTION) },
                                                                                                                           false);

        } finally {
            PrivilegedCarbonContext.endTenantFlow();
        }

        try {
            // Publish user add event to BAM
            Application app = ProjectUtils.getApplicationInfo(applicationKey, tenantDomain);
            String applicationName = app.getName();

            BamDataPublisher publisher = BamDataPublisher.getInstance();
            publisher.PublishUserUpdateEvent(applicationName, applicationKey, System.currentTimeMillis(),
                                             "" + tenantId, appOwner.split("@")[0], AppFactoryConstants.BAM_ADD_DATA);
        } catch (AppFactoryException e) {
            String message = "Failed to publish user add event to bam on application " + applicationKey;
            log.error(message);
            // TODO: throw exception
        }

        return true;
    }

    /**
     * This method can be used to remove the application related roles added to LDAP
     * @param applicationKey
     * @param appOwner
     * @param tenantDomain
     * @return
     * @throws UserStoreException
     */
    private boolean removeApplicationRoles(String applicationKey, String appOwner, String tenantDomain) throws UserStoreException {
        RealmService realmService = Util.getRealmService();
        TenantManager tenantManager = realmService.getTenantManager();
        PrivilegedCarbonContext threadLocalCarbonContext = null;
        try {
            int tenantId = tenantManager.getTenantId(tenantDomain);
            PrivilegedCarbonContext.startTenantFlow();
            threadLocalCarbonContext = PrivilegedCarbonContext.getThreadLocalCarbonContext();
            threadLocalCarbonContext.setTenantId(tenantId, true);
            UserStoreManager userStoreManager = realmService.getTenantUserRealm(tenantId).getUserStoreManager();
            userStoreManager.deleteRole(AppFactoryUtil.getRoleNameForApplication(applicationKey));
        }  finally {
            PrivilegedCarbonContext.endTenantFlow();
        }

        // TODO publish to bam

        return true;
    }

    private void addRegistryWritePermissionToApp(String applicationKey, String tenantDomain) throws UserStoreException {
        TenantManager tenantManager = Util.getRealmService().getTenantManager();
        int tenantId = tenantManager.getTenantId(tenantDomain);
        PrivilegedCarbonContext threadLocalCarbonContext = null;
        try {
            PrivilegedCarbonContext.startTenantFlow();
            threadLocalCarbonContext = PrivilegedCarbonContext.getThreadLocalCarbonContext();
            threadLocalCarbonContext.setTenantId(tenantId, true);
            String roleName = AppFactoryUtil.getRoleNameForApplication(applicationKey);

            AuthorizationManager authMan =
                    Util.getRealmService().getTenantUserRealm(tenantId)
                    .getAuthorizationManager();
            authMan.authorizeRole(roleName, RegistryConstants.GOVERNANCE_REGISTRY_BASE_PATH +
                    AppFactoryConstants.REGISTRY_APPLICATION_PATH + "/" + applicationKey,
                    ActionConstants.PUT);

        } finally {
            PrivilegedCarbonContext.endTenantFlow();
        }

    }

    private void removeAppFromRegistry(String applicationId, String tenantDomain) throws AppFactoryException, UserStoreException, RegistryException {
        TenantManager tenantManager = Util.getRealmService().getTenantManager();

        PrivilegedCarbonContext threadLocalCarbonContext = null;
        try {
            int tenantId = tenantManager.getTenantId(tenantDomain);
            PrivilegedCarbonContext.startTenantFlow();
            threadLocalCarbonContext = PrivilegedCarbonContext.getThreadLocalCarbonContext();
            threadLocalCarbonContext.setTenantId(tenantId, true);

            String resourcePath = AppFactoryConstants.REGISTRY_APPLICATION_PATH + "/" + applicationId;
            // removing all the permissions given to the resource
            AuthorizationManager authMan =
                    Util.getRealmService().getTenantUserRealm(tenantId)
                    .getAuthorizationManager();
            authMan.clearResourceAuthorizations(resourcePath);

            // deleting the resource for the applicaiton
            RegistryService registryService = ServiceHolder.getRegistryService();
            UserRegistry userRegistry = registryService.getGovernanceSystemRegistry(tenantId);

            if (userRegistry.resourceExists(resourcePath)) {
                userRegistry.delete(resourcePath);
            }

        } finally {
            PrivilegedCarbonContext.endTenantFlow();
        }
    }

    public String[] getAllCreatedApplications() throws ApplicationManagementException {
        String apps[] = new String[0];
        List<String> list = new ArrayList<String>();
        TenantManager manager = Util.getRealmService().getTenantManager();
        try {
            Tenant[] tenants = manager.getAllTenants();

            for (Tenant tenant : tenants) {
                list.add(tenant.getDomain());
            }

        } catch (UserStoreException e) {
            String msg = "Error while getting all applications";
            log.error(msg, e);
            throw new ApplicationManagementException(msg, e);
        }
        if (!list.isEmpty()) {
            apps = list.toArray(new String[list.size()]);
        }
        return apps;
    }

    public Artifact[] getAllVersionsOfApplication(String domainName, String applicationId) throws AppFactoryException {

        // Commenting out all App version cache related code

        // AppVersionCache cache = AppVersionCache.getAppVersionCache();
        // Artifact[] artifacts = cache.getAppVersions(applicationId);
        Artifact[] artifacts;
        // if (artifacts != null) {
        // if (log.isDebugEnabled()) {
        // log.debug("*** Retrieved all versions from cache " + applicationId);
        // }
        // return artifacts;
        // }
        RxtManager rxtManager = new RxtManager();
        try {
            List<Artifact> artifactsList = rxtManager.getAppVersionRxtForApplication(domainName, applicationId);
            artifacts = artifactsList.toArray(new Artifact[artifactsList.size()]);
            // cache.addToCache(applicationId, artifacts);
            if (log.isDebugEnabled()) {
                log.debug("*** Added all versions to cache " + applicationId);
            }
            return artifacts;
        } catch (AppFactoryException e) {
            log.error("Error while retrieving artifat information from rxt");
            throw new AppFactoryException(e.getMessage());
        } catch (RegistryException e) {
            log.error("Error while retrieving artifat information from rxt");
            throw new AppFactoryException(e.getMessage());
        }
    }

    /***
     * This method returns the build and deploy status
     *
     * @param applicationId
     *            application to check the build and deploy status
     * @param tenantDomain
     *            tenant domain that application belongs to
     * @param version
     *            version of the application to check
     * @return return last build id, build status [successful/unsuccessful] and
     *         last deployed build id
     */
    public BuildandDeployStatus getBuildandDelpoyedStatus(String applicationId,
                                                          String tenantDomain, String version) {

        try {

            BuildStatus buildStatus = applicationDAO.getBuildStatus(applicationId, version, false, null);
            DeployStatus deployStatus;

            deployStatus = applicationDAO.getDeployStatus(applicationId, version,
                    getStage(applicationId, version),
                    false,
                    null);

            BuildandDeployStatus buildandDeployStatus = new BuildandDeployStatus(buildStatus
                    .getLastBuildId(), buildStatus.getLastBuildStatus(),
                    deployStatus.getLastDeployedId());
            return buildandDeployStatus;

        } catch (AppFactoryException e) {
            log.error("Error while retrieving Build and Deploy status");
        } catch (ApplicationManagementException e) {
            log.error("Error while retrieving stage from rxt");
        }
        return null;
    }

    /**
     * Retrieve an array of repouser artifacts from the registry
     * @param domainName
     * @param applicationId
     * @param userName
     * @return array of repouser rxt artifacts
     * @throws AppFactoryException
     */
    public Artifact[] getAllVersionsOfApplicationPerUser(String domainName, String applicationId, String userName) throws  AppFactoryException{
        Artifact[] artifacts;
        RxtManager rxtManager = new RxtManager();
        try {
            List<Artifact> artifactsList = rxtManager.getRepoUserRxtForApplicationOfUser(domainName, applicationId, userName);
            artifacts = artifactsList.toArray(new Artifact[artifactsList.size()]);
            return artifacts;
        } catch (AppFactoryException e) {
            log.error("Error while retrieving artifact information from rxt");
            throw new AppFactoryException(e.getMessage());
        } catch (RegistryException e) {
            log.error("Error while retrieving artifact information from rxt");
            throw new AppFactoryException(e.getMessage());
        }

    }

    /**
     * update rxt on application deployment success/failure
     *
     * @param applicationId
     * @param version
     * @param tenantDomain
     * @param artifactLastModifiedTime
     */
    public void updateApplicationDeploymentSuccessStatus(String applicationId, String version,
                                                         String stage, String tenantDomain,
                                                         long artifactLastModifiedTime) throws AppFactoryException, AppFactoryEventException {
        long artifactLastModifiedTimeFromDB;

        DeployStatus deployStatus;
        try {
            deployStatus = applicationDAO.getDeployStatus(applicationId, version, stage,
                    false, null);
        } catch (AppFactoryException e) {
            String errorMsg = String.format("Unable to load the application deploy information " +
                    "for application id: %s",
                    applicationId);
            log.error(errorMsg, e);
            throw new AppFactoryEventException(e.getMessage());
        }
        artifactLastModifiedTimeFromDB = deployStatus.getLastDeployedTime();

        if (artifactLastModifiedTimeFromDB == 0) {
            artifactLastModifiedTimeFromDB = -1;
        }

        if (artifactLastModifiedTimeFromDB != 0 && artifactLastModifiedTimeFromDB < artifactLastModifiedTime) {
            String msg = version + " deployed in " + stage + " stage";

            try {
                String correlationKey = org.wso2.carbon.appfactory.eventing.utils.Util.deploymentCorrelationKey
                        (applicationId, stage, version, tenantDomain);
                EventNotifier.getInstance().notify(ContinousIntegrationEventBuilderUtil.buildObtainWarDeploymentStatusEvent(applicationId,
                        tenantDomain, msg, "", Event.Category.INFO, correlationKey));
            } catch (AppFactoryEventException e) {
                log.error("Failed to notify the Application deployment success event ", e);
            }

            try {
                deployStatus.setLastDeployedStatus("Success");
                deployStatus.setLastDeployedTime(artifactLastModifiedTime);

                applicationDAO.updateLastDeployStatus(applicationId, version, stage, false, null,
                        deployStatus);
            } catch (AppFactoryException e) {
                GenericArtifact application = null;
                try {
                    application = CommonUtil.getApplicationArtifact(applicationId, tenantDomain);
                } catch (AppFactoryException ex) {
                    String message = "Error while validating application key :  " + applicationId;
                    log.error(message);
                }
                if(application == null){
                    log.warn("Application is not available for application key " + applicationId);
                }else{
                    log.error("Error while updating db");
                    throw new AppFactoryException(e.getMessage());
                }
            }
        }


    }

    public String getApplicationStatus(String applicationId, String version, String stage,
                                       String tenantDomain) throws AppFactoryException {
        try {
            return applicationDAO.getDeployStatus(applicationId, version, stage, false, null).getLastDeployedStatus();
        } catch (AppFactoryException e) {
            log.error("Error while retrieving application state");
            throw new AppFactoryException(e.getMessage());
        }
    }

    public String getApplicationUrl(String applicationId, String version, String stage,
                                    String tenantDomain) throws AppFactoryException {
        return AppFactoryCoreUtil.getApplicationUrl(applicationId, version, stage, tenantDomain);

    }

}
