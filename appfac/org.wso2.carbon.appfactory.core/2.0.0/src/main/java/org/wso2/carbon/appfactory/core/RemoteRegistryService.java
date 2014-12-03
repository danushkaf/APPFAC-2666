/*
 * Copyright 2005-2013 WSO2, Inc. (http://wso2.com)
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
package org.wso2.carbon.appfactory.core;

import org.wso2.carbon.appfactory.core.dto.Dependency;
import org.wso2.carbon.appfactory.core.registry.AppFacResource;
import org.wso2.carbon.appfactory.common.AppFactoryException;

public interface RemoteRegistryService {
	

	/**
	 * 
	 * @param serverURL
	 * @param cookie
	 * @param applicationId
	 * @param appFactoryResources
	 * @return
	 * @throws AppFactoryException
	 */
	public abstract boolean addOrUpdateResources(String serverURL, String cookie, String applicationId, AppFacResource[] appFactoryResources) throws AppFactoryException;
	
	
	/**
	 * 
	 * @param serverURL
	 * @param cookie
	 * @param applicationId
	 * @param appFactoryResources
	 * @return
	 * @throws AppFactoryException
	 */
	public abstract boolean addOrUpdateResource(String serverURL, String cookie, String applicationId, AppFacResource appFactoryResources) throws AppFactoryException;

	/**
	 * Retrieves resource value from a given registry location
	 * 
	 * @param serverURL
	 *            registry residing server url
	 * @param cookie
	 *            session cookie
	 * @param resourcePath
	 *            relative path to the registry resource i.e except
	 *            "/_system/governance"
	 * @return value
	 * @throws AppFactoryException
	 */
	public abstract String getRegistyResourceValue(String serverURL, String cookie,
	                                               String resourcePath) throws AppFactoryException;


	/**
	 * Add new resource or update exisiting resource of a given registry
	 * location
	 * 
	 * @param serverURL
	 *            registry residing server url
	 * @param cookie
	 *            session cookie
	 * @param resourcePath
	 *            relative path to the registry resource i.e except
	 *            "/_system/governance"
	 * 
	 * @return array of string values
	 * @throws AppFactoryException
	 */
	public abstract boolean putRegistryResource(String serverURL, String cookie,
	                                            String resourcePath, String value,
	                                            String description, String mediaType)
	                                                                                 throws AppFactoryException;

    /**
     * 
     * @param serverURL
     * @param cookie
     * @param appId
     * @param name
     * @param value
     * @param description
     * @param mediaType
     * @param isCollection whether this is a collection or a property
     * @return
     * @throws AppFactoryException
     */
    public abstract boolean putRegistryProperty(String serverURL, String cookie,
	                                            String appId, String name, String value,
	                                            String description, String mediaType, boolean isCollection)
	                                                                                 throws AppFactoryException;/**

	/**
	 * Update description of a given registry resource
	 * location
	 * 
	 * @param serverURL
	 *            registry residing server url
	 * @param cookie
	 *            session cookie
	 * @param resourcePath
	 *            relative path to the registry resource i.e except
	 *            "/_system/governance"
	 * @param description
	 *            description
	 * @return array of string values
	 * @throws AppFactoryException
	 */
	public abstract boolean updateRegistryResourceDescription(String serverURL, String cookie,
	                                                          String resourcePath,
	                                                          String description)
	                                                                             throws AppFactoryException;

	/**
	 * Delete resource from a given registry location
	 * 
	 * @param serverURL
	 *            registry residing server url
	 * @param cookie
	 *            session cookie
	 * @param resourcePath
	 *            relative path to the registry resource i.e except
	 *            "/_system/governance"
	 * @return success or failure
	 * @throws AppFactoryException
	 */
	public abstract boolean deleteRegistryResource(String serverURL, String cookie,
	                                               String resourcePath) throws AppFactoryException;

	/**
	 * Check if the given resource is exists in the registry
	 * 
	 * @param serverURL
	 *            registry residing server url
	 * @param cookie
	 *            session cookie
	 * @param resourcePath
	 *            relative path to the registry resource i.e except
	 *            "/_system/governance"
	 * @return existance as true or false
	 * @throws AppFactoryException
	 */
	public abstract boolean resourceExists(String serverURL, String cookie, String resourcePath)
	                                                                                            throws AppFactoryException;

	/**
	 * Return all the resources under given resource path
	 * 
	 * @param serverURL
	 *            registry residing server url
	 * @param cookie
	 *            session cookie
	 * @param resourcePath
	 *            relative path to the registry resource i.e except
	 *            "/_system/governance"
	 * @return array of Dependency objects
	 * @throws AppFactoryException
	 */
	public abstract Dependency[] getAllRegistryResources(String serverURL, String cookie,
	                                                     String resourcePath)
	                                                                         throws AppFactoryException;

	/**
	 * Return the resource of given resource path
	 * 
	 * @param serverURL
	 *            registry residing server url
	 * @param cookie
	 *            session cookie
	 * @param resourcePath
	 *            relative path to the registry resource i.e except
	 *            "/_system/governance"
	 * @return array of Dependency objects
	 * @throws AppFactoryException
	 */
	public Dependency getRegistryResource(String serverURL, String cookie, String resourcePath)
	                                                                                           throws AppFactoryException;
	
	/**
	 *  Copies resources exists in source stage to target stage.
	 * @param sourceServerUrl Url of source server
	 * @param sourcePath registry path in the source registry
	 * @param sourceServerCookie the cookie to log in to source server
	 * @param destServerUrl Url of target server
	 * @param destPath registry path in the destination registry
	 * @param destServerCookie the cookie to log in to destination server
	 * @throws AppFactoryException an error
	 */
	public abstract void copyNonExistingResources(String sourceServerUrl, String sourcePath, String sourceServerCookie,
                                                 String destServerUrl, String destPath, String destServerCookie, String appId) throws AppFactoryException;

}