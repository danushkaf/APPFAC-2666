/*
 * Copyright 2004,2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wso2.carbon.appfactory.ext.jndi.Context;

import org.apache.naming.SelectorContext;
import org.wso2.carbon.context.CarbonContext;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NamingException;
import java.util.Hashtable;

/**
 * Base Selector Context represents the JVM level initial Context
 */
public class CarbonServerSelectorContext extends SelectorContext {
    //Carbon server level Context
    private Context carbonInitialContext;

    public CarbonServerSelectorContext(Hashtable<String, Object> env, boolean initialContext,
                                       Context carbonInitialContext) {
        super(env, initialContext);
        this.carbonInitialContext = carbonInitialContext;
    }


    public Object lookup(Name name) throws NamingException {

        //If lookup request is for tenant sub context
        //return the tenantSelectorContext
        if (isSubTenantRequest(name)) {
            return getTenantCarbonJNDIContext(name);
        }

        //Overrides lookup and lookupLink methods
        //Fist looking up in tomcat level JNDI context
        //If it is not available in tomcat level JNDI context
        //then perform lookup in carbon JNDI context
        try {
            return super.lookup(name);
        } catch (NamingException ex) {

            return carbonInitialContext.lookup(name);
        }
    }

    public Object lookup(String name) throws NamingException {

        if (isSubTenantRequest(name)) {
            return getTenantCarbonJNDIContext(name);
        }

        try {
            return super.lookup(name);
        } catch (NamingException ex) {
            return carbonInitialContext.lookup(name);
        }
    }

    public Object lookupLink(Name name) throws NamingException {

        if (isSubTenantRequest(name)) {
            return getTenantCarbonJNDIContext(name);
        }

        try {
            return super.lookupLink(name);
        } catch (NamingException ex) {
            return carbonInitialContext.lookup(name);
        }
    }

    public Object lookupLink(String name) throws NamingException {


        if (isSubTenantRequest(name)) {
            return getTenantCarbonJNDIContext(name);
        }

        try {
            return super.lookupLink(name);
        } catch (NamingException ex) {
            return carbonInitialContext.lookup(name);
        }
    }

    private boolean isSubTenantRequest(Name name) {
        return isSubTenantRequest(name.get(0));
    }

    // Check weather the look up request is for tenant sub context
    private boolean isSubTenantRequest(String name) {

        int tID = CarbonContext.getCurrentContext().getTenantId();
        return (tID > 0) && name.equals(String.valueOf(tID));

    }

    private Object getTenantCarbonJNDIContext(Name name) throws NamingException {
        return getTenantCarbonJNDIContext(name.get(0));
    }


    private Object getTenantCarbonJNDIContext(String name) throws NamingException {
        Context tenantSubContext = (Context) carbonInitialContext.lookup(name);
        return new TenantSelectorContext(env, initialContext, tenantSubContext);


    }


}
