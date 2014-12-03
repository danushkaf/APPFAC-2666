
/**
 * RepositoryManagementServiceRepositoryMgtExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1-wso2v10  Built on : Sep 04, 2013 (02:02:54 UTC)
 */

package org.wso2.carbon.appfactory.svn.repository.mgt.service;

public class RepositoryManagementServiceRepositoryMgtExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1417436649255L;
    
    private org.wso2.carbon.appfactory.svn.repository.mgt.service.RepositoryManagementServiceRepositoryMgtException faultMessage;

    
        public RepositoryManagementServiceRepositoryMgtExceptionException() {
            super("RepositoryManagementServiceRepositoryMgtExceptionException");
        }

        public RepositoryManagementServiceRepositoryMgtExceptionException(java.lang.String s) {
           super(s);
        }

        public RepositoryManagementServiceRepositoryMgtExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public RepositoryManagementServiceRepositoryMgtExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(org.wso2.carbon.appfactory.svn.repository.mgt.service.RepositoryManagementServiceRepositoryMgtException msg){
       faultMessage = msg;
    }
    
    public org.wso2.carbon.appfactory.svn.repository.mgt.service.RepositoryManagementServiceRepositoryMgtException getFaultMessage(){
       return faultMessage;
    }
}
    