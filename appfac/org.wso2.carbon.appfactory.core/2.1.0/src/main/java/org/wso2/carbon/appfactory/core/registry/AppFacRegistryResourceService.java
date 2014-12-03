/*
*  Copyright (c) 2014, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*  WSO2 Inc. licenses this file to you under the Apache License,
*  Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License.
*  You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/
package org.wso2.carbon.appfactory.core.registry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.CarbonConstants;
import org.wso2.carbon.appfactory.common.AppFactoryException;
import org.wso2.carbon.appfactory.core.dto.Dependency;
import org.wso2.carbon.appfactory.core.internal.ServiceHolder;
import org.wso2.carbon.context.CarbonContext;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.registry.api.GhostResource;
import org.wso2.carbon.registry.core.Collection;
import org.wso2.carbon.registry.core.Registry;
import org.wso2.carbon.registry.core.RegistryConstants;
import org.wso2.carbon.registry.core.Resource;
import org.wso2.carbon.registry.core.caching.RegistryCacheKey;
import org.wso2.carbon.registry.core.config.DataBaseConfiguration;
import org.wso2.carbon.registry.core.config.Mount;
import org.wso2.carbon.registry.core.config.RemoteConfiguration;
import org.wso2.carbon.registry.core.exceptions.RegistryException;
import org.wso2.carbon.registry.core.service.RegistryService;
import org.wso2.carbon.registry.core.session.UserRegistry;
import org.wso2.carbon.registry.core.utils.RegistryUtils;

import javax.cache.Cache;

/**
 * Service to enable CRUD operation to user registry
 */
public class AppFacRegistryResourceService {

    private static final Log log = LogFactory.getLog(AppFacRegistryResourceService.class);

    /**
     * Return all the resources under given resource path
     *
     * @param resourcePath resource path
     * @return array of Dependency objects
     * @throws AppFactoryException
     */
    public Dependency[] getAllResources(String resourcePath) throws AppFactoryException {
        Dependency[] dependencies = new Dependency[0];

        try {
            UserRegistry userRegistry = getUserRegistry();

            if (userRegistry.resourceExists(resourcePath)) {
                Resource dependencyParent = userRegistry.get(resourcePath);

                if (dependencyParent instanceof Collection) {
                    Collection collection = (Collection) dependencyParent;
                    String[] children = collection.getChildren();

                    if (children == null) {
                        log.warn("No resources were found as dependencies");
                        return dependencies;
                    }

                    dependencies = new Dependency[children.length];

                    for (int i = 0; i < children.length; i++) {
                        String childPath = children[i];
                        Resource child = userRegistry.get(childPath);

                        Dependency element = new Dependency();
                        element.setName(RegistryUtils.getResourceName(child.getPath()));
                        element.setDescription(child.getDescription());
                        element.setValue(getResourceContent(child));
                        element.setMediaType(child.getMediaType());

                        dependencies[i] = element;
                    }
                } else {
                    log.warn("No resources were found as dependencies");
                }
            }

        } catch (RegistryException e) {
            String msg = "Unable to get the dependencies from registry";
            log.error(msg, e);
            throw new AppFactoryException(msg, e);
        }
        return dependencies;
    }

    /**
     * Retrieves resource value from a given registry location
     *
     * @param resourcePath resource path
     * @return value
     * @throws AppFactoryException
     */
    public String getResourceValue(String resourcePath) throws AppFactoryException {
        String value = null;
        try {
            UserRegistry userRegistry = getUserRegistry();
            if (userRegistry.resourceExists(resourcePath)) {
                Resource resource = userRegistry.get(resourcePath);
                value = getResourceContent(resource);
            }
            return value;
        } catch (RegistryException e) {
            String msg = "Error occurred while retrieving dependency value from " + resourcePath;
            log.error(msg, e);
            throw new AppFactoryException(msg, e);
        }
    }

    /**
     * Get UserRegistry
     *
     * @return UserRegistry object
     * @throws AppFactoryException
     */
    private UserRegistry getUserRegistry() throws AppFactoryException {
        try {
            RegistryService registryService = ServiceHolder.getRegistryService();
            return registryService.getRegistry(
                CarbonConstants.REGISTRY_SYSTEM_USERNAME,
                CarbonContext.getThreadLocalCarbonContext().getTenantId());
        } catch (RegistryException e) {
            String msg = "Unable to get the registry";
            log.error(msg, e);
            throw new AppFactoryException(msg, e);
        }
    }

    /**
     * Get resource content based on content type
     *
     * @param resource resource
     * @return content string
     * @throws AppFactoryException
     */
    private String getResourceContent(Resource resource) throws AppFactoryException {
        try {
            if (resource.getContent() != null) {
                if (resource.getContent() instanceof String) {
                    return (String) resource.getContent();
                } else if (resource.getContent() instanceof byte[]) {
                    return new String((byte[]) resource.getContent());
                }
            }
        } catch (RegistryException e) {
            String msg = "Unable to read the resource content";
            log.error(msg, e);
            throw new AppFactoryException(msg, e);
        }
        return null;
    }

    /**
     * Remove the cache of the given path from registry
     *
     * @param resourcePath the resource path to be cleared from the cache
     * @throws AppFactoryException
     */
    public void removeRegistryCache(String resourcePath) throws AppFactoryException {
        Registry registry = getUserRegistry();
        int tenantId = PrivilegedCarbonContext.getThreadLocalCarbonContext().getTenantId();
        Cache<RegistryCacheKey, GhostResource> cache =
                RegistryUtils.getResourceCache(RegistryConstants.REGISTRY_CACHE_BACKED_ID);
        RegistryCacheKey cacheKey;

        if (registry.getRegistryContext().getRemoteInstances().size() > 0) {
            for (Mount mount : registry.getRegistryContext().getMounts()) {
                if (resourcePath.startsWith(mount.getPath())) {
                    for (RemoteConfiguration configuration : registry.getRegistryContext().getRemoteInstances()) {
                        DataBaseConfiguration databaseConfiguration = registry.getRegistryContext().getDBConfig(
                                configuration.getDbConfig());
                        String connectionId = (databaseConfiguration.getUserName() != null
                                ? databaseConfiguration.getUserName().split(
                                "@")[0] : databaseConfiguration.getUserName()) + "@" + databaseConfiguration.getDbUrl();
                        cacheKey = RegistryUtils.buildRegistryCacheKey(connectionId, tenantId, resourcePath);

                        if (cache.containsKey(cacheKey)) {
                            cache.remove(cacheKey);
                            if (log.isDebugEnabled()) {
                                log.debug("Cache cleared for resource path " + resourcePath);
                            }
                        }
                    }
                }
            }
        }
    }
}




