package org.wso2.carbon.issue.tracker.util;

import org.apache.log4j.Logger;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.user.api.UserRealmService;
import org.wso2.carbon.user.api.UserStoreException;

public class TenantUtils {

    private static Logger logger = Logger.getLogger(TenantUtils.class);

    /**
     * returns the tenant id given the tenant domain
     *
     * @param tenantDomain the tenant domain
     * @return the tenant id
     * @throws IssueTrackerException an error while accessing the user store.
     */
    public static int getTenantId(String tenantDomain) throws UserStoreException {
        UserRealmService realmService =
                (UserRealmService) PrivilegedCarbonContext.getThreadLocalCarbonContext()
                        .getOSGiService(UserRealmService.class);

        int tenantId = 0;
        try {
            tenantId = realmService.getTenantManager().getTenantId(tenantDomain);
        } catch (UserStoreException e) {
            String msg = "Error occured while retrieving tenant id for :" + tenantDomain;
            logger.error(msg, e);
            throw e;
        }

        return tenantId;
    }


    public static String getTenantDomain(int tenantId) throws UserStoreException {
        UserRealmService realmService =
                (UserRealmService) PrivilegedCarbonContext.getThreadLocalCarbonContext()
                        .getOSGiService(UserRealmService.class);

        String tenantDomain;
        try {
            tenantDomain = realmService.getTenantManager().getDomain(tenantId);
        } catch (UserStoreException e) {
            String msg = "Error occured while retrieving tenant domain for :" + tenantId;
            logger.error(msg, e);
            throw e;
        }

        return tenantDomain;
    }

    /**
     * returns the users of the tenant domain
     *
     * @param tenantDomain the tenant domain
     * @return users of the tenant
     * @throws IssueTrackerException an error while accessing the user store.
     */
    public static String[] getUsersOfTenant(String tenantDomain) throws UserStoreException {
        UserRealmService realmService =
                (UserRealmService) PrivilegedCarbonContext.getThreadLocalCarbonContext()
                        .getOSGiService(UserRealmService.class);

        int tenantId = 0;
        String[] userNames;
        try {
            tenantId = realmService.getTenantManager().getTenantId(tenantDomain);
            userNames = realmService.getTenantUserRealm(tenantId).getUserStoreManager().listUsers("*",Integer.MAX_VALUE);

        } catch (UserStoreException e) {
            String msg = "Error occured while retrieving tenant id for :" + tenantDomain;
            logger.error(msg, e);
            throw e;
        }

        return userNames;
    }

    /**
     * returns the users of the given appkey
     *
     * @param applicationKey the application unique identifier
     * @return users list belogs to an application
     * @throws IssueTrackerException an error while accessing the user store.
     */
    public static String[] getUsersOftheApplication(String applicationKey, String tenantDomain)
	                                                                 throws UserStoreException {
	String applicationRole =  "app_" + applicationKey;
        UserRealmService realmService =
                (UserRealmService) PrivilegedCarbonContext.getThreadLocalCarbonContext()
                        .getOSGiService(UserRealmService.class);

        int tenantId = 0;
        String[] userNames;
        try {
            
            tenantId = realmService.getTenantManager().getTenantId(tenantDomain);
            userNames = realmService.getTenantUserRealm(tenantId).getUserStoreManager().getUserListOfRole(applicationRole);

        } catch (UserStoreException e) {
            String msg = "Error occured while retrieving users of :" + applicationKey;
            logger.error(msg, e);
            throw e;
        }
       return userNames;
    }

}
