/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fmat.arquitectura.mvc.config;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 *
 * @author user
 */
public class XmlConverterToClass {
    private String nameClass;
     private XStream xStream;
    
    public XmlConverterToClass(String nameClass){
        this.nameClass = nameClass;
        xStream = new XStream(new DomDriver());
    }
    
    public Object converter(String xmlContent) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        Object classInUse= Class.forName(nameClass).newInstance();
        classInUse = xStream.fromXML(xmlContent);
        return classInUse;
       
    }
}
