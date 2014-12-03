

/**
 * RepositoryAuthenticationService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1-wso2v10  Built on : Sep 04, 2013 (02:02:54 UTC)
 */

    package org.wso2.carbon.appfactory.repository.mgt.service;

    /*
     *  RepositoryAuthenticationService java interface
     */

    public interface RepositoryAuthenticationService {
          

        /**
          * Auto generated method signature
          * 
                    * @param hasAccess0
                
         */

         
                     public boolean hasAccess(

                        java.lang.String username1,java.lang.String applicationId2,java.lang.String repoAction3,java.lang.String fullRepoName4)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param hasAccess0
            
          */
        public void starthasAccess(

            java.lang.String username1,java.lang.String applicationId2,java.lang.String repoAction3,java.lang.String fullRepoName4,

            final org.wso2.carbon.appfactory.repository.mgt.service.RepositoryAuthenticationServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    