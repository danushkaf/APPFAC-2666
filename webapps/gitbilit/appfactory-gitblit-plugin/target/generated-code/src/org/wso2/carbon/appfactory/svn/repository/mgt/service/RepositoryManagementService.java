

/**
 * RepositoryManagementService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1-wso2v10  Built on : Sep 04, 2013 (02:02:54 UTC)
 */

    package org.wso2.carbon.appfactory.svn.repository.mgt.service;

    /*
     *  RepositoryManagementService java interface
     */

    public interface RepositoryManagementService {
          
       /**
         * Auto generated method signature for Asynchronous Invocations
         * 
         */
        public void  cleanApplicationDir(
         java.lang.String applicationPath16

        ) throws java.rmi.RemoteException
        
        ;

        
       /**
         * Auto generated method signature for Asynchronous Invocations
         * 
         */
        public void  svnMove(
         java.lang.String sourceUrl18,java.lang.String destinationUrl19,java.lang.String commitMessage20,java.lang.String svnRevision21

        ) throws java.rmi.RemoteException
        
        ;

        

        /**
          * Auto generated method signature
          * 
                    * @param getURL22
                
             * @throws org.wso2.carbon.appfactory.svn.repository.mgt.service.RepositoryManagementServiceRepositoryMgtExceptionException : 
         */

         
                     public java.lang.String getURL(

                        java.lang.String applicationKey23)
                        throws java.rmi.RemoteException
             
          ,org.wso2.carbon.appfactory.svn.repository.mgt.service.RepositoryManagementServiceRepositoryMgtExceptionException;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param getURL22
            
          */
        public void startgetURL(

            java.lang.String applicationKey23,

            final org.wso2.carbon.appfactory.svn.repository.mgt.service.RepositoryManagementServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param hasAccess26
                
             * @throws org.wso2.carbon.appfactory.svn.repository.mgt.service.RepositoryManagementServiceRepositoryMgtExceptionException : 
         */

         
                     public boolean hasAccess(

                        java.lang.String username27,java.lang.String password28,java.lang.String applicationId29)
                        throws java.rmi.RemoteException
             
          ,org.wso2.carbon.appfactory.svn.repository.mgt.service.RepositoryManagementServiceRepositoryMgtExceptionException;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param hasAccess26
            
          */
        public void starthasAccess(

            java.lang.String username27,java.lang.String password28,java.lang.String applicationId29,

            final org.wso2.carbon.appfactory.svn.repository.mgt.service.RepositoryManagementServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     
       /**
         * Auto generated method signature for Asynchronous Invocations
         * 
                 * @throws org.wso2.carbon.appfactory.svn.repository.mgt.service.RepositoryManagementServiceSCMManagerExceptionsException : 
         */
        public void  initSVNClient(
         

        ) throws java.rmi.RemoteException
        
        
               ,org.wso2.carbon.appfactory.svn.repository.mgt.service.RepositoryManagementServiceSCMManagerExceptionsException;

        

        /**
          * Auto generated method signature
          * 
                    * @param getAdminUsername33
                
         */

         
                     public java.lang.String getAdminUsername(

                        java.lang.String applicationId34)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param getAdminUsername33
            
          */
        public void startgetAdminUsername(

            java.lang.String applicationId34,

            final org.wso2.carbon.appfactory.svn.repository.mgt.service.RepositoryManagementServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     
       /**
         * Auto generated method signature for Asynchronous Invocations
         * 
         */
        public void  svnCopy(
         java.lang.String sourceUrl38,java.lang.String destinationUrl39,java.lang.String commitMessage40,java.lang.String svnRevision41

        ) throws java.rmi.RemoteException
        
        ;

        
       /**
         * Auto generated method signature for Asynchronous Invocations
         * 
         */
        public void  createDirectory(
         java.lang.String url43,java.lang.String commitMessage44

        ) throws java.rmi.RemoteException
        
        ;

        

        /**
          * Auto generated method signature
          * 
                    * @param createApplicationCheckoutDirectory45
                
             * @throws org.wso2.carbon.appfactory.svn.repository.mgt.service.RepositoryManagementServiceSCMManagerExceptionsException : 
         */

         
                     public java.io.xsd.File createApplicationCheckoutDirectory(

                        java.lang.String applicationName46)
                        throws java.rmi.RemoteException
             
          ,org.wso2.carbon.appfactory.svn.repository.mgt.service.RepositoryManagementServiceSCMManagerExceptionsException;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param createApplicationCheckoutDirectory45
            
          */
        public void startcreateApplicationCheckoutDirectory(

            java.lang.String applicationName46,

            final org.wso2.carbon.appfactory.svn.repository.mgt.service.RepositoryManagementServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param createRepository49
                
             * @throws org.wso2.carbon.appfactory.svn.repository.mgt.service.RepositoryManagementServiceRepositoryMgtExceptionException : 
         */

         
                     public java.lang.String createRepository(

                        java.lang.String applicationKey50)
                        throws java.rmi.RemoteException
             
          ,org.wso2.carbon.appfactory.svn.repository.mgt.service.RepositoryManagementServiceRepositoryMgtExceptionException;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param createRepository49
            
          */
        public void startcreateRepository(

            java.lang.String applicationKey50,

            final org.wso2.carbon.appfactory.svn.repository.mgt.service.RepositoryManagementServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param checkoutApplication53
                
             * @throws org.wso2.carbon.appfactory.svn.repository.mgt.service.RepositoryManagementServiceSCMManagerExceptionsException : 
         */

         
                     public java.lang.String checkoutApplication(

                        java.lang.String applicationSvnUrl54,java.lang.String applicationId55,java.lang.String svnRevision56)
                        throws java.rmi.RemoteException
             
          ,org.wso2.carbon.appfactory.svn.repository.mgt.service.RepositoryManagementServiceSCMManagerExceptionsException;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param checkoutApplication53
            
          */
        public void startcheckoutApplication(

            java.lang.String applicationSvnUrl54,java.lang.String applicationId55,java.lang.String svnRevision56,

            final org.wso2.carbon.appfactory.svn.repository.mgt.service.RepositoryManagementServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param getRepositoryManager59
                
         */

         
                     public org.wso2.carbon.appfactory.svn.repository.mgt.xsd.RepositoryManager getRepositoryManager(

                        )
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param getRepositoryManager59
            
          */
        public void startgetRepositoryManager(

            

            final org.wso2.carbon.appfactory.svn.repository.mgt.service.RepositoryManagementServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     
       /**
         * Auto generated method signature for Asynchronous Invocations
         * 
         */
        public void  setRepositoryManager(
         org.wso2.carbon.appfactory.svn.repository.mgt.xsd.RepositoryManager repositoryManager63

        ) throws java.rmi.RemoteException
        
        ;

        

        
       //
       }
    