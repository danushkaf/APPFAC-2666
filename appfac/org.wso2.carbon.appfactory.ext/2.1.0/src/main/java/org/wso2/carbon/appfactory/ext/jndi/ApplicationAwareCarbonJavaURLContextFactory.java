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
package org.wso2.carbon.appfactory.ext.jndi;

import org.apache.naming.ContextBindings;
import org.apache.naming.NamingContext;
import org.apache.naming.java.javaURLContextFactory;
import org.wso2.carbon.appfactory.ext.jndi.Context.CarbonServerSelectorContext;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NamingException;
import java.util.Hashtable;

/**
 * Default javaURLContextFactory implementation used to achieve Application aware JNDI look up.
 * To enable this add this class in carbon.xml as follows.
 * <JNDI>
 * <DefaultInitialContextFactory>
 * org.wso2.carbon.appfactory.ext.jndi.ApplicationAwareCarbonJavaURLContextFactory
 * </DefaultInitialContextFactory>
 * </JNDI>
 */
public class ApplicationAwareCarbonJavaURLContextFactory extends javaURLContextFactory {
    public Object getObjectInstance(Object o,
                                    Name name,
                                    Context context,
                                    Hashtable<?, ?> hashtable) throws NamingException {
        return null;
    }

    public Context getInitialContext(Hashtable hashtable) throws NamingException {
        // We check wether the intiCtx request is coming from webapps
        if (ContextBindings.isClassLoaderBound()) {
            //return {@class CarbonServerSelectorContext} which will route the request to correct
            // tenant
            return new CarbonServerSelectorContext(hashtable, false, initialContext);
        }
        if (initialContext == null) {
            initialContext = new NamingContext(hashtable, MAIN);
        }
        return initialContext;
    }
}
