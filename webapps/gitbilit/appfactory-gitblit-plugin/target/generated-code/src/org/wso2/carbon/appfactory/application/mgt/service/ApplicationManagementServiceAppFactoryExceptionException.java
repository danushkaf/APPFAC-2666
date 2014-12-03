
/**
 * ApplicationManagementServiceAppFactoryExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1-wso2v10  Built on : Sep 04, 2013 (02:02:54 UTC)
 */

package org.wso2.carbon.appfactory.application.mgt.service;

public class ApplicationManagementServiceAppFactoryExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1417436651652L;
    
    private org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceAppFactoryException faultMessage;

    
        public ApplicationManagementServiceAppFactoryExceptionException() {
            super("ApplicationManagementServiceAppFactoryExceptionException");
        }

        public ApplicationManagementServiceAppFactoryExceptionException(java.lang.String s) {
           super(s);
        }

        public ApplicationManagementServiceAppFactoryExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public ApplicationManagementServiceAppFactoryExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceAppFactoryException msg){
       faultMessage = msg;
    }
    
    public org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceAppFactoryException getFaultMessage(){
       return faultMessage;
    }
}
    