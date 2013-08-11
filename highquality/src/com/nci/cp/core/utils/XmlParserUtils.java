package com.nci.cp.core.utils;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-2-7 
 */
public final class XmlParserUtils {
    public static Document trieveDocument(String file) throws Exception{
        SAXReader saxReader = new SAXReader();
		
		try {
			Document document = saxReader.read(file);
			return document;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
        return null;
    }
    public static Element findElementByName(Element parentElement,String name) {
    	List list = parentElement.elements();
    	for(int i =0;i<list.size();i++) 
    		if (name.equals(((Element)list.get(i)).getName()))
			           return (Element)list.get(i);
    	
    	return null;
    }
    
    public static  Element findElementByAttr(Element rootNode,String attr,String value ) {
		List nodes =  rootNode.elements();		
		
		for(int i=0;i<nodes.size();i++)		{			
			String eleid = ((Element)nodes.get(i)).attributeValue(attr);		
			if (value.equals(eleid))	{				
				return  (Element)nodes.get(i);				
			}			
		}
		return null;
	}
    
  
}
