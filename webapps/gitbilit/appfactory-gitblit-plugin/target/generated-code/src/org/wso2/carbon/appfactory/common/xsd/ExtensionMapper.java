
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1-wso2v10  Built on : Dec 01, 2014 (02:37:38 IST)
 */

        
            package org.wso2.carbon.appfactory.common.xsd;
        
            /**
            *  ExtensionMapper class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://mgt.repository.svn.appfactory.carbon.wso2.org/xsd".equals(namespaceURI) &&
                  "RepositoryManager".equals(typeName)){
                   
                            return  org.wso2.carbon.appfactory.svn.repository.mgt.xsd.RepositoryManager.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://common.appfactory.carbon.wso2.org/xsd".equals(namespaceURI) &&
                  "AppFactoryConfiguration".equals(typeName)){
                   
                            return  org.wso2.carbon.appfactory.common.xsd.AppFactoryConfiguration.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://io.java/xsd".equals(namespaceURI) &&
                  "File".equals(typeName)){
                   
                            return  java.io.xsd.File.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://impl.mgt.repository.svn.appfactory.carbon.wso2.org/xsd".equals(namespaceURI) &&
                  "SCMManagerExceptions".equals(typeName)){
                   
                            return  org.wso2.carbon.appfactory.svn.repository.mgt.impl.xsd.SCMManagerExceptions.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://mgt.repository.svn.appfactory.carbon.wso2.org/xsd".equals(namespaceURI) &&
                  "RepositoryMgtException".equals(typeName)){
                   
                            return  org.wso2.carbon.appfactory.svn.repository.mgt.xsd.RepositoryMgtException.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    