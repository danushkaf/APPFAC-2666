
/**
 * File.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1-wso2v10  Built on : Dec 01, 2014 (02:37:38 IST)
 */

            
                package java.io.xsd;
            

            /**
            *  File bean class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class File
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = File
                Namespace URI = http://io.java/xsd
                Namespace Prefix = ns1
                */
            

                        /**
                        * field for Absolute
                        */

                        
                                    protected boolean localAbsolute ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAbsoluteTracker = false ;

                           public boolean isAbsoluteSpecified(){
                               return localAbsoluteTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return boolean
                           */
                           public  boolean getAbsolute(){
                               return localAbsolute;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Absolute
                               */
                               public void setAbsolute(boolean param){
                            
                                       // setting primitive attribute tracker to true
                                       localAbsoluteTracker =
                                       true;
                                   
                                            this.localAbsolute=param;
                                    

                               }
                            

                        /**
                        * field for AbsoluteFile
                        */

                        
                                    protected java.io.xsd.File localAbsoluteFile ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAbsoluteFileTracker = false ;

                           public boolean isAbsoluteFileSpecified(){
                               return localAbsoluteFileTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.io.xsd.File
                           */
                           public  java.io.xsd.File getAbsoluteFile(){
                               return localAbsoluteFile;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AbsoluteFile
                               */
                               public void setAbsoluteFile(java.io.xsd.File param){
                            localAbsoluteFileTracker = true;
                                   
                                            this.localAbsoluteFile=param;
                                    

                               }
                            

                        /**
                        * field for AbsolutePath
                        */

                        
                                    protected java.lang.String localAbsolutePath ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAbsolutePathTracker = false ;

                           public boolean isAbsolutePathSpecified(){
                               return localAbsolutePathTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAbsolutePath(){
                               return localAbsolutePath;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AbsolutePath
                               */
                               public void setAbsolutePath(java.lang.String param){
                            localAbsolutePathTracker = true;
                                   
                                            this.localAbsolutePath=param;
                                    

                               }
                            

                        /**
                        * field for CanonicalFile
                        */

                        
                                    protected java.io.xsd.File localCanonicalFile ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCanonicalFileTracker = false ;

                           public boolean isCanonicalFileSpecified(){
                               return localCanonicalFileTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.io.xsd.File
                           */
                           public  java.io.xsd.File getCanonicalFile(){
                               return localCanonicalFile;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CanonicalFile
                               */
                               public void setCanonicalFile(java.io.xsd.File param){
                            localCanonicalFileTracker = true;
                                   
                                            this.localCanonicalFile=param;
                                    

                               }
                            

                        /**
                        * field for CanonicalPath
                        */

                        
                                    protected java.lang.String localCanonicalPath ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCanonicalPathTracker = false ;

                           public boolean isCanonicalPathSpecified(){
                               return localCanonicalPathTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCanonicalPath(){
                               return localCanonicalPath;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CanonicalPath
                               */
                               public void setCanonicalPath(java.lang.String param){
                            localCanonicalPathTracker = true;
                                   
                                            this.localCanonicalPath=param;
                                    

                               }
                            

                        /**
                        * field for Directory
                        */

                        
                                    protected boolean localDirectory ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localDirectoryTracker = false ;

                           public boolean isDirectorySpecified(){
                               return localDirectoryTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return boolean
                           */
                           public  boolean getDirectory(){
                               return localDirectory;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Directory
                               */
                               public void setDirectory(boolean param){
                            
                                       // setting primitive attribute tracker to true
                                       localDirectoryTracker =
                                       true;
                                   
                                            this.localDirectory=param;
                                    

                               }
                            

                        /**
                        * field for File
                        */

                        
                                    protected boolean localFile ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localFileTracker = false ;

                           public boolean isFileSpecified(){
                               return localFileTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return boolean
                           */
                           public  boolean getFile(){
                               return localFile;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param File
                               */
                               public void setFile(boolean param){
                            
                                       // setting primitive attribute tracker to true
                                       localFileTracker =
                                       true;
                                   
                                            this.localFile=param;
                                    

                               }
                            

                        /**
                        * field for FreeSpace
                        */

                        
                                    protected long localFreeSpace ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localFreeSpaceTracker = false ;

                           public boolean isFreeSpaceSpecified(){
                               return localFreeSpaceTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return long
                           */
                           public  long getFreeSpace(){
                               return localFreeSpace;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param FreeSpace
                               */
                               public void setFreeSpace(long param){
                            
                                       // setting primitive attribute tracker to true
                                       localFreeSpaceTracker =
                                       param != java.lang.Long.MIN_VALUE;
                                   
                                            this.localFreeSpace=param;
                                    

                               }
                            

                        /**
                        * field for Hidden
                        */

                        
                                    protected boolean localHidden ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHiddenTracker = false ;

                           public boolean isHiddenSpecified(){
                               return localHiddenTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return boolean
                           */
                           public  boolean getHidden(){
                               return localHidden;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Hidden
                               */
                               public void setHidden(boolean param){
                            
                                       // setting primitive attribute tracker to true
                                       localHiddenTracker =
                                       true;
                                   
                                            this.localHidden=param;
                                    

                               }
                            

                        /**
                        * field for Name
                        */

                        
                                    protected java.lang.String localName ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNameTracker = false ;

                           public boolean isNameSpecified(){
                               return localNameTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getName(){
                               return localName;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Name
                               */
                               public void setName(java.lang.String param){
                            localNameTracker = true;
                                   
                                            this.localName=param;
                                    

                               }
                            

                        /**
                        * field for Parent
                        */

                        
                                    protected java.lang.String localParent ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localParentTracker = false ;

                           public boolean isParentSpecified(){
                               return localParentTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getParent(){
                               return localParent;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Parent
                               */
                               public void setParent(java.lang.String param){
                            localParentTracker = true;
                                   
                                            this.localParent=param;
                                    

                               }
                            

                        /**
                        * field for ParentFile
                        */

                        
                                    protected java.io.xsd.File localParentFile ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localParentFileTracker = false ;

                           public boolean isParentFileSpecified(){
                               return localParentFileTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.io.xsd.File
                           */
                           public  java.io.xsd.File getParentFile(){
                               return localParentFile;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ParentFile
                               */
                               public void setParentFile(java.io.xsd.File param){
                            localParentFileTracker = true;
                                   
                                            this.localParentFile=param;
                                    

                               }
                            

                        /**
                        * field for Path
                        */

                        
                                    protected java.lang.String localPath ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localPathTracker = false ;

                           public boolean isPathSpecified(){
                               return localPathTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getPath(){
                               return localPath;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Path
                               */
                               public void setPath(java.lang.String param){
                            localPathTracker = true;
                                   
                                            this.localPath=param;
                                    

                               }
                            

                        /**
                        * field for TotalSpace
                        */

                        
                                    protected long localTotalSpace ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTotalSpaceTracker = false ;

                           public boolean isTotalSpaceSpecified(){
                               return localTotalSpaceTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return long
                           */
                           public  long getTotalSpace(){
                               return localTotalSpace;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TotalSpace
                               */
                               public void setTotalSpace(long param){
                            
                                       // setting primitive attribute tracker to true
                                       localTotalSpaceTracker =
                                       param != java.lang.Long.MIN_VALUE;
                                   
                                            this.localTotalSpace=param;
                                    

                               }
                            

                        /**
                        * field for UsableSpace
                        */

                        
                                    protected long localUsableSpace ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localUsableSpaceTracker = false ;

                           public boolean isUsableSpaceSpecified(){
                               return localUsableSpaceTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return long
                           */
                           public  long getUsableSpace(){
                               return localUsableSpace;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param UsableSpace
                               */
                               public void setUsableSpace(long param){
                            
                                       // setting primitive attribute tracker to true
                                       localUsableSpaceTracker =
                                       param != java.lang.Long.MIN_VALUE;
                                   
                                            this.localUsableSpace=param;
                                    

                               }
                            

     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName);
               return factory.createOMElement(dataSource,parentQName);
            
        }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       javax.xml.stream.XMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               javax.xml.stream.XMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();
                    writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://io.java/xsd");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":File",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "File",
                           xmlWriter);
                   }

               
                   }
                if (localAbsoluteTracker){
                                    namespace = "http://io.java/xsd";
                                    writeStartElement(null, namespace, "absolute", xmlWriter);
                             
                                               if (false) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("absolute cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAbsolute));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAbsoluteFileTracker){
                                    if (localAbsoluteFile==null){

                                        writeStartElement(null, "http://io.java/xsd", "absoluteFile", xmlWriter);

                                       // write the nil attribute
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                      xmlWriter.writeEndElement();
                                    }else{
                                     localAbsoluteFile.serialize(new javax.xml.namespace.QName("http://io.java/xsd","absoluteFile"),
                                        xmlWriter);
                                    }
                                } if (localAbsolutePathTracker){
                                    namespace = "http://io.java/xsd";
                                    writeStartElement(null, namespace, "absolutePath", xmlWriter);
                             

                                          if (localAbsolutePath==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAbsolutePath);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCanonicalFileTracker){
                                    if (localCanonicalFile==null){

                                        writeStartElement(null, "http://io.java/xsd", "canonicalFile", xmlWriter);

                                       // write the nil attribute
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                      xmlWriter.writeEndElement();
                                    }else{
                                     localCanonicalFile.serialize(new javax.xml.namespace.QName("http://io.java/xsd","canonicalFile"),
                                        xmlWriter);
                                    }
                                } if (localCanonicalPathTracker){
                                    namespace = "http://io.java/xsd";
                                    writeStartElement(null, namespace, "canonicalPath", xmlWriter);
                             

                                          if (localCanonicalPath==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCanonicalPath);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localDirectoryTracker){
                                    namespace = "http://io.java/xsd";
                                    writeStartElement(null, namespace, "directory", xmlWriter);
                             
                                               if (false) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("directory cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDirectory));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localFileTracker){
                                    namespace = "http://io.java/xsd";
                                    writeStartElement(null, namespace, "file", xmlWriter);
                             
                                               if (false) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("file cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFile));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localFreeSpaceTracker){
                                    namespace = "http://io.java/xsd";
                                    writeStartElement(null, namespace, "freeSpace", xmlWriter);
                             
                                               if (localFreeSpace==java.lang.Long.MIN_VALUE) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("freeSpace cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFreeSpace));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHiddenTracker){
                                    namespace = "http://io.java/xsd";
                                    writeStartElement(null, namespace, "hidden", xmlWriter);
                             
                                               if (false) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("hidden cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHidden));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNameTracker){
                                    namespace = "http://io.java/xsd";
                                    writeStartElement(null, namespace, "name", xmlWriter);
                             

                                          if (localName==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localName);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localParentTracker){
                                    namespace = "http://io.java/xsd";
                                    writeStartElement(null, namespace, "parent", xmlWriter);
                             

                                          if (localParent==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localParent);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localParentFileTracker){
                                    if (localParentFile==null){

                                        writeStartElement(null, "http://io.java/xsd", "parentFile", xmlWriter);

                                       // write the nil attribute
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                      xmlWriter.writeEndElement();
                                    }else{
                                     localParentFile.serialize(new javax.xml.namespace.QName("http://io.java/xsd","parentFile"),
                                        xmlWriter);
                                    }
                                } if (localPathTracker){
                                    namespace = "http://io.java/xsd";
                                    writeStartElement(null, namespace, "path", xmlWriter);
                             

                                          if (localPath==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localPath);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTotalSpaceTracker){
                                    namespace = "http://io.java/xsd";
                                    writeStartElement(null, namespace, "totalSpace", xmlWriter);
                             
                                               if (localTotalSpace==java.lang.Long.MIN_VALUE) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("totalSpace cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTotalSpace));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localUsableSpaceTracker){
                                    namespace = "http://io.java/xsd";
                                    writeStartElement(null, namespace, "usableSpace", xmlWriter);
                             
                                               if (localUsableSpace==java.lang.Long.MIN_VALUE) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("usableSpace cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localUsableSpace));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             }
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://io.java/xsd")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                                       javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }
        
        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace,attName,attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName,attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace,attName,attValue);
            }
        }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localAbsoluteTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://io.java/xsd",
                                                                      "absolute"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAbsolute));
                            } if (localAbsoluteFileTracker){
                            elementList.add(new javax.xml.namespace.QName("http://io.java/xsd",
                                                                      "absoluteFile"));
                            
                            
                                    elementList.add(localAbsoluteFile==null?null:
                                    localAbsoluteFile);
                                } if (localAbsolutePathTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://io.java/xsd",
                                                                      "absolutePath"));
                                 
                                         elementList.add(localAbsolutePath==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAbsolutePath));
                                    } if (localCanonicalFileTracker){
                            elementList.add(new javax.xml.namespace.QName("http://io.java/xsd",
                                                                      "canonicalFile"));
                            
                            
                                    elementList.add(localCanonicalFile==null?null:
                                    localCanonicalFile);
                                } if (localCanonicalPathTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://io.java/xsd",
                                                                      "canonicalPath"));
                                 
                                         elementList.add(localCanonicalPath==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCanonicalPath));
                                    } if (localDirectoryTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://io.java/xsd",
                                                                      "directory"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDirectory));
                            } if (localFileTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://io.java/xsd",
                                                                      "file"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFile));
                            } if (localFreeSpaceTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://io.java/xsd",
                                                                      "freeSpace"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFreeSpace));
                            } if (localHiddenTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://io.java/xsd",
                                                                      "hidden"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHidden));
                            } if (localNameTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://io.java/xsd",
                                                                      "name"));
                                 
                                         elementList.add(localName==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localName));
                                    } if (localParentTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://io.java/xsd",
                                                                      "parent"));
                                 
                                         elementList.add(localParent==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localParent));
                                    } if (localParentFileTracker){
                            elementList.add(new javax.xml.namespace.QName("http://io.java/xsd",
                                                                      "parentFile"));
                            
                            
                                    elementList.add(localParentFile==null?null:
                                    localParentFile);
                                } if (localPathTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://io.java/xsd",
                                                                      "path"));
                                 
                                         elementList.add(localPath==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPath));
                                    } if (localTotalSpaceTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://io.java/xsd",
                                                                      "totalSpace"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTotalSpace));
                            } if (localUsableSpaceTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://io.java/xsd",
                                                                      "usableSpace"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localUsableSpace));
                            }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static File parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            File object =
                new File();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"File".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (File)org.wso2.carbon.appfactory.common.xsd.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://io.java/xsd","absolute").equals(reader.getName())){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"absolute" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAbsolute(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToBoolean(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://io.java/xsd","absoluteFile").equals(reader.getName())){
                                
                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                          object.setAbsoluteFile(null);
                                          reader.next();
                                            
                                            reader.next();
                                          
                                      }else{
                                    
                                                object.setAbsoluteFile(java.io.xsd.File.Factory.parse(reader));
                                              
                                        reader.next();
                                    }
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://io.java/xsd","absolutePath").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAbsolutePath(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://io.java/xsd","canonicalFile").equals(reader.getName())){
                                
                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                          object.setCanonicalFile(null);
                                          reader.next();
                                            
                                            reader.next();
                                          
                                      }else{
                                    
                                                object.setCanonicalFile(java.io.xsd.File.Factory.parse(reader));
                                              
                                        reader.next();
                                    }
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://io.java/xsd","canonicalPath").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCanonicalPath(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://io.java/xsd","directory").equals(reader.getName())){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"directory" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setDirectory(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToBoolean(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://io.java/xsd","file").equals(reader.getName())){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"file" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setFile(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToBoolean(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://io.java/xsd","freeSpace").equals(reader.getName())){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"freeSpace" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setFreeSpace(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToLong(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setFreeSpace(java.lang.Long.MIN_VALUE);
                                           
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://io.java/xsd","hidden").equals(reader.getName())){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"hidden" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHidden(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToBoolean(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://io.java/xsd","name").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setName(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://io.java/xsd","parent").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setParent(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://io.java/xsd","parentFile").equals(reader.getName())){
                                
                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                          object.setParentFile(null);
                                          reader.next();
                                            
                                            reader.next();
                                          
                                      }else{
                                    
                                                object.setParentFile(java.io.xsd.File.Factory.parse(reader));
                                              
                                        reader.next();
                                    }
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://io.java/xsd","path").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setPath(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://io.java/xsd","totalSpace").equals(reader.getName())){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"totalSpace" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTotalSpace(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToLong(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setTotalSpace(java.lang.Long.MIN_VALUE);
                                           
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://io.java/xsd","usableSpace").equals(reader.getName())){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"usableSpace" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setUsableSpace(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToLong(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setUsableSpace(java.lang.Long.MIN_VALUE);
                                           
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
    