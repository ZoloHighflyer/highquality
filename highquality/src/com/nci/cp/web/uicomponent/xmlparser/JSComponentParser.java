package com.nci.cp.web.uicomponent.xmlparser;

import org.dom4j.Element;

import com.nci.cp.web.uicomponent.IUIComponent;
import com.nci.cp.web.uicomponent.JSUIComponent;
import com.nci.cp.web.uicomponent.context.IComponentParserLib;
import com.nci.cp.web.uicomponent.factory.JSComponentFactory;


/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-2-1 
 */
public class JSComponentParser extends AbstractComponentParser {
    
	
	public IUIComponent parse(Element ele,IComponentParserLib icpLib) {
		String type= ele.attributeValue("type");
		
		if (JSUIComponent.TYPE_REL.equals(type)) {					
		  return	JSComponentFactory.generateComponent(JSUIComponent.TYPE_REL,ele.attributeValue("src"));
		}
		
		return JSComponentFactory.generateComponent(JSUIComponent.TYPE_SCRIPT,ele.getStringValue());
	}
	

     
}
