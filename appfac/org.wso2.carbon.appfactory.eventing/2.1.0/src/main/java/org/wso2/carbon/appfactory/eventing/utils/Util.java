package org.wso2.carbon.appfactory.eventing.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.appfactory.common.AppFactoryConfiguration;
import org.wso2.carbon.appfactory.common.AppFactoryException;
import org.wso2.carbon.appfactory.common.util.AppFactoryUtil;
import org.wso2.carbon.appfactory.eventing.AppFactoryEventException;
import org.wso2.carbon.appfactory.eventing.jms.AndesAdminServiceClient;
import org.wso2.carbon.context.CarbonContext;
import org.wso2.carbon.user.api.RealmConfiguration;
import org.wso2.carbon.user.api.UserRealm;
import org.wso2.carbon.user.api.UserStoreException;

public class Util {
    private static Log log = LogFactory.getLog(Util.class);

    public static String getTCPConnectionURL() throws AppFactoryEventException {
        String currentUser = CarbonContext.getThreadLocalCarbonContext().getUsername();
        String tenantDomain = CarbonContext.getThreadLocalCarbonContext().getTenantDomain();
        if (tenantDomain != null && !tenantDomain.equals(EventingConstants.CARBON_SUPER)) {
            currentUser = currentUser + "@" + tenantDomain;
        }
        try {
            AppFactoryConfiguration appFactoryConfiguration = AppFactoryUtil.getAppfactoryConfiguration();
            String messageBrokerServerUrl = appFactoryConfiguration.getFirstProperty(EventingConstants.NOTIFICATION_SERVER_URL);
            AndesAdminServiceClient client = new AndesAdminServiceClient(messageBrokerServerUrl);
            AppFactoryUtil.setAuthHeaders(client.getStub()._getServiceClient(), currentUser);
            String accessToken = client.getAccessToken();
            String connectionUrl = appFactoryConfiguration.getFirstProperty(EventingConstants.TCP_CONNECTION_URL);
            connectionUrl = connectionUrl.replace(EventingConstants.CONNECTION_USER, getCurrentUser()).
                    replace(EventingConstants.ACCESS_TOKEN, accessToken);
            return connectionUrl;
        } catch (AppFactoryException e) {
            String error = "Failed to get tcp connection URL to message broker due to " + e.getMessage();
            log.error(error, e);
            throw new AppFactoryEventException(error, e);
        }
    }

    private static String getCurrentUser() {
        String userName = "";
        if (CarbonContext.getThreadLocalCarbonContext().getTenantId() != 0) {
            userName = CarbonContext.getThreadLocalCarbonContext().getUsername() + "!"
                    + CarbonContext.getThreadLocalCarbonContext().getTenantDomain();
        } else {
            userName = CarbonContext.getThreadLocalCarbonContext().getUsername();
        }
        return userName.trim();
    }

    public static String getUniqueSubscriptionId(String topic, String subscriberId) {
        String tenantDomain = CarbonContext.getThreadLocalCarbonContext().getTenantDomain();
        // in tenant mode, subscriptionId should start with tenant domain.
        return tenantDomain + "/" + topic.concat("_").concat(subscriberId);
    }


    public static String getSender() {
            return getSender(null);
    }


    /**
     * @param recievedUserName username of the user who triggered the event
     * @return sender's user name
     */
    public static String getSender(String recievedUserName){
        String sender = null;
        String userName = CarbonContext.getThreadLocalCarbonContext().getUsername();
        String tenantDomain = CarbonContext.getThreadLocalCarbonContext().getTenantDomain();
        if (StringUtils.isNotBlank(userName)) {
            sender = userName + "@" + tenantDomain;

        } else if (StringUtils.isNotBlank(recievedUserName)) {

            if(recievedUserName.contains("@")){
                String[] splits = recievedUserName.split("@"); // we will extract
                // only the username from received user name.
                sender = splits[0] + "@" + tenantDomain;
            }

        } else { // As the last resort we will use tenant admin to send the message

            UserRealm realm = CarbonContext.getThreadLocalCarbonContext().getUserRealm();
            if (realm != null) {

                try {
                    RealmConfiguration configuration = realm.getRealmConfiguration();
                    sender = configuration.getAdminUserName() + "@" + tenantDomain;
                } catch (UserStoreException e) {
                    log.error("unable to retrieve the realm configuration", e);
                }

            }
        }
        return sender;
    }

    public static String deploymentCorrelationKey(String applicationId, String stage, String version, String tenantDomain){
        return applicationId + stage + version + tenantDomain;
    }


}
