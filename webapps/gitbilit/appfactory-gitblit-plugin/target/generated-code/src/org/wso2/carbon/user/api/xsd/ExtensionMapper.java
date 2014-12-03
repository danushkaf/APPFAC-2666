
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1-wso2v10  Built on : Dec 01, 2014 (02:37:38 IST)
 */

        
            package org.wso2.carbon.user.api.xsd;
        
            /**
            *  ExtensionMapper class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://common.appfactory.carbon.wso2.org/xsd".equals(namespaceURI) &&
                  "AppFactoryException".equals(typeName)){
                   
                            return  org.wso2.carbon.appfactory.common.xsd.AppFactoryException.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://service.mgt.application.appfactory.carbon.wso2.org/xsd".equals(namespaceURI) &&
                  "UserApplications".equals(typeName)){
                   
                            return  org.wso2.carbon.appfactory.application.mgt.service.xsd.UserApplications.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://service.mgt.application.appfactory.carbon.wso2.org/xsd".equals(namespaceURI) &&
                  "ApplicationInfoBean".equals(typeName)){
                   
                            return  org.wso2.carbon.appfactory.application.mgt.service.xsd.ApplicationInfoBean.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://service.mgt.application.appfactory.carbon.wso2.org/xsd".equals(namespaceURI) &&
                  "UserRoleCount".equals(typeName)){
                   
                            return  org.wso2.carbon.appfactory.application.mgt.service.xsd.UserRoleCount.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://service.mgt.application.appfactory.carbon.wso2.org/xsd".equals(namespaceURI) &&
                  "UserInfoBean".equals(typeName)){
                   
                            return  org.wso2.carbon.appfactory.application.mgt.service.xsd.UserInfoBean.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://deploy.core.appfactory.carbon.wso2.org/xsd".equals(namespaceURI) &&
                  "Artifact".equals(typeName)){
                   
                            return  org.wso2.carbon.appfactory.core.deploy.xsd.Artifact.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://api.user.carbon.wso2.org/xsd".equals(namespaceURI) &&
                  "UserStoreException".equals(typeName)){
                   
                            return  org.wso2.carbon.user.api.xsd.UserStoreException.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://service.mgt.application.appfactory.carbon.wso2.org/xsd".equals(namespaceURI) &&
                  "ApplicationManagementException".equals(typeName)){
                   
                            return  org.wso2.carbon.appfactory.application.mgt.service.xsd.ApplicationManagementException.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    