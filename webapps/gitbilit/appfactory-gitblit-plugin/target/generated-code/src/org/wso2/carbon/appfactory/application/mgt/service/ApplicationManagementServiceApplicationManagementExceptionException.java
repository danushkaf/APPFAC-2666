
/**
 * ApplicationManagementServiceApplicationManagementExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1-wso2v10  Built on : Sep 04, 2013 (02:02:54 UTC)
 */

package org.wso2.carbon.appfactory.application.mgt.service;

public class ApplicationManagementServiceApplicationManagementExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1417436651686L;
    
    private org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementException faultMessage;

    
        public ApplicationManagementServiceApplicationManagementExceptionException() {
            super("ApplicationManagementServiceApplicationManagementExceptionException");
        }

        public ApplicationManagementServiceApplicationManagementExceptionException(java.lang.String s) {
           super(s);
        }

        public ApplicationManagementServiceApplicationManagementExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public ApplicationManagementServiceApplicationManagementExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementException msg){
       faultMessage = msg;
    }
    
    public org.wso2.carbon.appfactory.application.mgt.service.ApplicationManagementServiceApplicationManagementException getFaultMessage(){
       return faultMessage;
    }
}
    