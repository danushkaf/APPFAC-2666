

/**
 * ApplicationManagementService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1-wso2v10  Built on : Sep 04, 2013 (02:02:54 UTC)
 */

    package org.wso2.carbon.appfactory.application.mgt.service;

    /*
     *  ApplicationManagementService java interface
     */

    public interface ApplicationManagementService {
          

        /**
          * Auto generated method signature
          * 
                    * @param getUserListOfRole63
                
             * @throws org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException : 
         */

         
                     public org.wso2.carbon.appfactory.application.mgt.service.xsd.UserRoleCount[] getUserListOfRole(

                        java.lang.String roleName64)
                        throws java.rmi.RemoteException
             
          ,org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param getUserListOfRole63
            
          */
        public void startgetUserListOfRole(

            java.lang.String roleName64,

            final org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param getAllCreatedApplications67
                
             * @throws org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException : 
         */

         
                     public java.lang.String[] getAllCreatedApplications(

                        )
                        throws java.rmi.RemoteException
             
          ,org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param getAllCreatedApplications67
            
          */
        public void startgetAllCreatedApplications(

            

            final org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param sendMail70
                
             * @throws org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException : 
         */

         
                     public boolean sendMail(

                        java.lang.String domainName71,java.lang.String applicationId72,java.lang.String userName73,java.lang.String[] roles74,java.lang.String config75)
                        throws java.rmi.RemoteException
             
          ,org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param sendMail70
            
          */
        public void startsendMail(

            java.lang.String domainName71,java.lang.String applicationId72,java.lang.String userName73,java.lang.String[] roles74,java.lang.String config75,

            final org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param updateRolesOfUserForApplication78
                
             * @throws org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException : 
         */

         
                     public boolean updateRolesOfUserForApplication(

                        java.lang.String applicationId79,java.lang.String userName80,java.lang.String[] rolesToDelete81,java.lang.String[] newRoles82)
                        throws java.rmi.RemoteException
             
          ,org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param updateRolesOfUserForApplication78
            
          */
        public void startupdateRolesOfUserForApplication(

            java.lang.String applicationId79,java.lang.String userName80,java.lang.String[] rolesToDelete81,java.lang.String[] newRoles82,

            final org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param getStage85
                
             * @throws org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException : 
         */

         
                     public java.lang.String getStage(

                        java.lang.String applicationId86,java.lang.String version87)
                        throws java.rmi.RemoteException
             
          ,org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param getStage85
            
          */
        public void startgetStage(

            java.lang.String applicationId86,java.lang.String version87,

            final org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     
       /**
         * Auto generated method signature for Asynchronous Invocations
         * 
                 * @throws org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException : 
         */
        public void  publishApplicationVersionCreation(
         java.lang.String domainName91,java.lang.String applicationId92,java.lang.String sourceVersion93,java.lang.String targetVersion94

        ) throws java.rmi.RemoteException
        
        
               ,org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException;

        

        /**
          * Auto generated method signature
          * 
                    * @param getAllVersionsOfApplication95
                
             * @throws org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceAppFactoryExceptionException : 
         */

         
                     public org.wso2.carbon.appfactory.core.deploy.xsd.Artifact[] getAllVersionsOfApplication(

                        java.lang.String applicationId96)
                        throws java.rmi.RemoteException
             
          ,org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceAppFactoryExceptionException;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param getAllVersionsOfApplication95
            
          */
        public void startgetAllVersionsOfApplication(

            java.lang.String applicationId96,

            final org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param getUsersOfApplication99
                
             * @throws org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException : 
         */

         
                     public java.lang.String[] getUsersOfApplication(

                        java.lang.String applicationId100)
                        throws java.rmi.RemoteException
             
          ,org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param getUsersOfApplication99
            
          */
        public void startgetUsersOfApplication(

            java.lang.String applicationId100,

            final org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param isApplicationIdAvailable103
                
             * @throws org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException : 
         */

         
                     public boolean isApplicationIdAvailable(

                        java.lang.String applicationKey104)
                        throws java.rmi.RemoteException
             
          ,org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param isApplicationIdAvailable103
            
          */
        public void startisApplicationIdAvailable(

            java.lang.String applicationKey104,

            final org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param removeUserFromApplication107
                
             * @throws org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException : 
         */

         
                     public boolean removeUserFromApplication(

                        java.lang.String domainName108,java.lang.String applicationId109,java.lang.String userName110)
                        throws java.rmi.RemoteException
             
          ,org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param removeUserFromApplication107
            
          */
        public void startremoveUserFromApplication(

            java.lang.String domainName108,java.lang.String applicationId109,java.lang.String userName110,

            final org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param checkSystemStatus113
                
             * @throws org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException : 
         */

         
                     public boolean checkSystemStatus(

                        java.lang.String applicationSystemId114)
                        throws java.rmi.RemoteException
             
          ,org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param checkSystemStatus113
            
          */
        public void startcheckSystemStatus(

            java.lang.String applicationSystemId114,

            final org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param createDefaultRoles117
                
             * @throws org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException : 
         */

         
                     public boolean createDefaultRoles(

                        java.lang.String domainName118,java.lang.String applicationId119,java.lang.String appOwner120)
                        throws java.rmi.RemoteException
             
          ,org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param createDefaultRoles117
            
          */
        public void startcreateDefaultRoles(

            java.lang.String domainName118,java.lang.String applicationId119,java.lang.String appOwner120,

            final org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     
       /**
         * Auto generated method signature for Asynchronous Invocations
         * 
                 * @throws org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException : 
         */
        public void  publishSetApplicationAutoBuild(
         java.lang.String applicationId124,java.lang.String stage125,java.lang.String version126,boolean isAutoBuildable127

        ) throws java.rmi.RemoteException
        
        
               ,org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException;

        

        /**
          * Auto generated method signature
          * 
                    * @param getUserInfoBean128
                
             * @throws org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException : 
         */

         
                     public org.wso2.carbon.appfactory.application.mgt.service.xsd.UserInfoBean getUserInfoBean(

                        java.lang.String userName129)
                        throws java.rmi.RemoteException
             
          ,org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param getUserInfoBean128
            
          */
        public void startgetUserInfoBean(

            java.lang.String userName129,

            final org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param getBasicApplicationInfo132
                
             * @throws org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceAppFactoryExceptionException : 
         */

         
                     public org.wso2.carbon.appfactory.application.mgt.service.xsd.ApplicationInfoBean getBasicApplicationInfo(

                        java.lang.String domainName133,java.lang.String applicationKey134)
                        throws java.rmi.RemoteException
             
          ,org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceAppFactoryExceptionException;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param getBasicApplicationInfo132
            
          */
        public void startgetBasicApplicationInfo(

            java.lang.String domainName133,java.lang.String applicationKey134,

            final org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param addUserToApplication137
                
             * @throws org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException : 
         */

         
                     public boolean addUserToApplication(

                        java.lang.String domainName138,java.lang.String applicationId139,java.lang.String userName140,java.lang.String[] roles141)
                        throws java.rmi.RemoteException
             
          ,org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param addUserToApplication137
            
          */
        public void startaddUserToApplication(

            java.lang.String domainName138,java.lang.String applicationId139,java.lang.String userName140,java.lang.String[] roles141,

            final org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param updateUserOfApplication144
                
             * @throws org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceUserStoreExceptionException : 
             * @throws org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException : 
         */

         
                     public boolean updateUserOfApplication(

                        java.lang.String domainName145,java.lang.String applicationId146,java.lang.String userName147,java.lang.String[] rolesToDelete148,java.lang.String[] rolesToAdd149)
                        throws java.rmi.RemoteException
             
          ,org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceUserStoreExceptionException
          ,org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param updateUserOfApplication144
            
          */
        public void startupdateUserOfApplication(

            java.lang.String domainName145,java.lang.String applicationId146,java.lang.String userName147,java.lang.String[] rolesToDelete148,java.lang.String[] rolesToAdd149,

            final org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param revokeApplication152
                
             * @throws org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException : 
         */

         
                     public boolean revokeApplication(

                        java.lang.String domainName153,java.lang.String applicationId154)
                        throws java.rmi.RemoteException
             
          ,org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param revokeApplication152
            
          */
        public void startrevokeApplication(

            java.lang.String domainName153,java.lang.String applicationId154,

            final org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     
       /**
         * Auto generated method signature for Asynchronous Invocations
         * 
                 * @throws org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException : 
         */
        public void  publishApplicationCreation(
         java.lang.String domainName158,java.lang.String applicationId159

        ) throws java.rmi.RemoteException
        
        
               ,org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException;

        

        /**
          * Auto generated method signature
          * 
                    * @param getApplicationsOfUser160
                
             * @throws org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException : 
         */

         
                     public org.wso2.carbon.appfactory.application.mgt.service.xsd.UserApplications[] getApplicationsOfUser(

                        java.lang.String roleName161)
                        throws java.rmi.RemoteException
             
          ,org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param getApplicationsOfUser160
            
          */
        public void startgetApplicationsOfUser(

            java.lang.String roleName161,

            final org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     
       /**
         * Auto generated method signature for Asynchronous Invocations
         * 
                 * @throws org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException : 
         */
        public void  publishApplicationAutoDeploymentChange(
         java.lang.String domainName165,java.lang.String applicationId166,java.lang.String previousVersion167,java.lang.String nextVersion168,java.lang.String versionStage169

        ) throws java.rmi.RemoteException
        
        
               ,org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException;

        
       /**
         * Auto generated method signature for Asynchronous Invocations
         * 
                 * @throws org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException : 
         */
        public void  publishSetApplicationAutoDeploy(
         java.lang.String applicationId171,java.lang.String stage172,java.lang.String version173,boolean isAutoDeployable174

        ) throws java.rmi.RemoteException
        
        
               ,org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException;

        

        /**
          * Auto generated method signature
          * 
                    * @param getFullyQualifiedDbUsername175
                
         */

         
                     public java.lang.String getFullyQualifiedDbUsername(

                        java.lang.String username176,java.lang.String applicationKey177)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param getFullyQualifiedDbUsername175
            
          */
        public void startgetFullyQualifiedDbUsername(

            java.lang.String username176,java.lang.String applicationKey177,

            final org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param createApplication180
                
             * @throws org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException : 
         */

         
                     public void createApplication(

                        java.lang.String applicationName181,java.lang.String applicationKey182,java.lang.String applicationDescription183,java.lang.String applicationType184,java.lang.String repositoryType185,java.lang.String userName186)
                        throws java.rmi.RemoteException
             
          ,org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param createApplication180
            
          */
        public void startcreateApplication(

            java.lang.String applicationName181,java.lang.String applicationKey182,java.lang.String applicationDescription183,java.lang.String applicationType184,java.lang.String repositoryType185,java.lang.String userName186,

            final org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param getUserInfo188
                
             * @throws org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException : 
         */

         
                     public org.wso2.carbon.appfactory.application.mgt.service.xsd.UserInfoBean[] getUserInfo(

                        java.lang.String applicationId189)
                        throws java.rmi.RemoteException
             
          ,org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param getUserInfo188
            
          */
        public void startgetUserInfo(

            java.lang.String applicationId189,

            final org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param getAllApplications192
                
             * @throws org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException : 
         */

         
                     public java.lang.String[] getAllApplications(

                        java.lang.String domainName193,java.lang.String userName194)
                        throws java.rmi.RemoteException
             
          ,org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param getAllApplications192
            
          */
        public void startgetAllApplications(

            java.lang.String domainName193,java.lang.String userName194,

            final org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param getRolesOfUserPerApplication197
                
             * @throws org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException : 
         */

         
                     public java.lang.String[] getRolesOfUserPerApplication(

                        java.lang.String appId198,java.lang.String userName199)
                        throws java.rmi.RemoteException
             
          ,org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementExceptionException;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param getRolesOfUserPerApplication197
            
          */
        public void startgetRolesOfUserPerApplication(

            java.lang.String appId198,java.lang.String userName199,

            final org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    