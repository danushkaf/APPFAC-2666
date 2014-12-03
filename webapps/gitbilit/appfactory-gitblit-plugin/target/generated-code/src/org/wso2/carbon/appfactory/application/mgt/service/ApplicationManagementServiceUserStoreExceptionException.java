
/**
 * ApplicationManagementServiceUserStoreExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1-wso2v10  Built on : Sep 04, 2013 (02:02:54 UTC)
 */

package org.wso2.carbon.appfactory.application.mgt.service;

public class ApplicationManagementServiceUserStoreExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1417436651666L;
    
    private org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceUserStoreException faultMessage;

    
        public ApplicationManagementServiceUserStoreExceptionException() {
            super("ApplicationManagementServiceUserStoreExceptionException");
        }

        public ApplicationManagementServiceUserStoreExceptionException(java.lang.String s) {
           super(s);
        }

        public ApplicationManagementServiceUserStoreExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public ApplicationManagementServiceUserStoreExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceUserStoreException msg){
       faultMessage = msg;
    }
    
    public org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceUserStoreException getFaultMessage(){
       return faultMessage;
    }
}
    