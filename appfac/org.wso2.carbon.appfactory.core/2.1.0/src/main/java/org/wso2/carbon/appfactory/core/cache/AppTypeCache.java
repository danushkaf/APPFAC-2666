package org.wso2.carbon.appfactory.core.cache;

import java.util.HashMap;
import java.util.Map;

public class AppTypeCache {
    private static AppTypeCache appTypeCache = new AppTypeCache();
    private Map<String, Map<String, String>> tenantCache;

    private AppTypeCache() {
        tenantCache = new HashMap<String, Map<String, String>>();
    }

    public static AppTypeCache getAppTypeCache() {
        return appTypeCache;
    }

    public void addToCache(String tenantDomain, String appId, String appType) {
        Map<String, String> tenantAppTypeCache = tenantCache.get(tenantDomain);
        if (tenantAppTypeCache != null) {
            tenantAppTypeCache.put(appId, appType);
        } else {
            tenantAppTypeCache = new HashMap<String, String>();
            tenantAppTypeCache.put(appId, appType);
            tenantCache.put(tenantDomain, tenantAppTypeCache);
        }
    }

    public void clearCacheForAppId(String tenantDomain, String appId) {
        Map<String, String> tenantAppTypeCache = tenantCache.get(tenantDomain);
        if (tenantAppTypeCache != null) {
            tenantAppTypeCache.remove(appId);
        }
    }

    public void clearCacheForTenantDomain(String tenatDomain) {
        tenantCache.remove(tenatDomain);
    }

    public String getAppType(String tenantDomain, String appId) {
        Map<String, String> tenantAppTypeCache = tenantCache.get(tenantDomain);
        if (tenantAppTypeCache != null) {
            return tenantAppTypeCache.get(appId);
        }
        return null;
    }
}
