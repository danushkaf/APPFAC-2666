
/**
 * RepositoryManagementServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1-wso2v10  Built on : Sep 04, 2013 (02:02:54 UTC)
 */

    package org.wso2.carbon.appfactory.svn.repository.mgt.service;

    /**
     *  RepositoryManagementServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class RepositoryManagementServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public RepositoryManagementServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public RepositoryManagementServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
               // No methods generated for meps other than in-out
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for getURL method
            * override this method for handling normal response from getURL operation
            */
           public void receiveResultgetURL(
                    java.lang.String result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getURL operation
           */
            public void receiveErrorgetURL(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for hasAccess method
            * override this method for handling normal response from hasAccess operation
            */
           public void receiveResulthasAccess(
                    boolean result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from hasAccess operation
           */
            public void receiveErrorhasAccess(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for getAdminUsername method
            * override this method for handling normal response from getAdminUsername operation
            */
           public void receiveResultgetAdminUsername(
                    java.lang.String result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getAdminUsername operation
           */
            public void receiveErrorgetAdminUsername(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for createApplicationCheckoutDirectory method
            * override this method for handling normal response from createApplicationCheckoutDirectory operation
            */
           public void receiveResultcreateApplicationCheckoutDirectory(
                    java.io.xsd.File result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from createApplicationCheckoutDirectory operation
           */
            public void receiveErrorcreateApplicationCheckoutDirectory(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for createRepository method
            * override this method for handling normal response from createRepository operation
            */
           public void receiveResultcreateRepository(
                    java.lang.String result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from createRepository operation
           */
            public void receiveErrorcreateRepository(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for checkoutApplication method
            * override this method for handling normal response from checkoutApplication operation
            */
           public void receiveResultcheckoutApplication(
                    java.lang.String result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from checkoutApplication operation
           */
            public void receiveErrorcheckoutApplication(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getRepositoryManager method
            * override this method for handling normal response from getRepositoryManager operation
            */
           public void receiveResultgetRepositoryManager(
                    org.wso2.carbon.appfactory.svn.repository.mgt.xsd.RepositoryManager result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getRepositoryManager operation
           */
            public void receiveErrorgetRepositoryManager(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                


    }
    