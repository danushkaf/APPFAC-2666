package org.wso2.carbon.appfactory.eventing.social;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mozilla.javascript.NativeObject;
import org.wso2.carbon.appfactory.common.AppFactoryConfiguration;
import org.wso2.carbon.appfactory.common.AppFactoryException;
import org.wso2.carbon.appfactory.common.util.AppFactoryUtil;
import org.wso2.carbon.appfactory.eventing.AppFactoryEventException;
import org.wso2.carbon.appfactory.eventing.Event;
import org.wso2.carbon.appfactory.eventing.EventDispatcher;
import org.wso2.carbon.appfactory.eventing.internal.ServiceHolder;
import org.wso2.carbon.appfactory.eventing.utils.EventingConstants;
import org.wso2.carbon.base.MultitenantConstants;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.social.core.service.SocialActivityService;

public class SocialActivityEventDispatcher implements EventDispatcher {
    Log log = LogFactory.getLog(SocialActivityEventDispatcher.class);
    private SocialActivityService socialActivityService;
    public static String PROP_PORT = "port";
    public static String PROP_HOST = "host";
    public static String PROP_USERNAME = "username";
    public static String PROP_PASSWORD = "password";

    public SocialActivityEventDispatcher() {
        if (socialActivityService == null) {
            socialActivityService = ServiceHolder.getSocialActivityService();
            socialActivityService.configPublisher(getConfigObject());
        }
    }

    private NativeObject getConfigObject() {
        NativeObject config = new NativeObject();
        try {
            AppFactoryConfiguration configuration = AppFactoryUtil.getAppfactoryConfiguration();
            config.put(PROP_PORT, config, configuration.getFirstProperty(EventingConstants.NOTIFICATION_CONFIG_PORT));
            config.put(PROP_HOST, config, configuration.getFirstProperty(EventingConstants.NOTIFICATION_CONFIG_HOST));
            config.put(PROP_USERNAME, config, configuration.getFirstProperty(EventingConstants.NOTIFICATION_CONFIG_CONNECTION_USER));
            config.put(PROP_PASSWORD, config, configuration.getFirstProperty(EventingConstants.NOTIFICATION_CONFIG_CONNECTION_PSWD));
        } catch (AppFactoryException e) {
            log.error("Failed to get Notification configuration from appfactory.xml and continue with default values.", e);
        }
        return config;
    }

    @Override
    public void dispatchEvent(Event event) throws AppFactoryEventException {
        PrivilegedCarbonContext privilegedCarbonContext = PrivilegedCarbonContext.getThreadLocalCarbonContext();
        privilegedCarbonContext.setTenantDomain(MultitenantConstants.SUPER_TENANT_DOMAIN_NAME);
        privilegedCarbonContext.setTenantId(MultitenantConstants.SUPER_TENANT_ID);
        SocialActivityBuilder socialActivityBuilder = new SocialActivityBuilder(event);
        NativeObject activity = socialActivityBuilder.buildActivity();
        socialActivityService.publish(activity);
    }
}
