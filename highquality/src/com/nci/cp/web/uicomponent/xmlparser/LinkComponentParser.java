package com.nci.cp.web.uicomponent.xmlparser;

import org.dom4j.Element;

import com.nci.cp.web.uicomponent.IUIComponent;
import com.nci.cp.web.uicomponent.context.IComponentParserLib;
import com.nci.cp.web.uicomponent.factory.LinkImportComponentFactory;

/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-2-3 
 */
public class LinkComponentParser extends AbstractComponentParser {

	
	public IUIComponent parse(Element ele,IComponentParserLib icpLib) {
		String rel  = ele.attributeValue("rel");
		if (rel==null) rel="";
		String href = ele.attributeValue("href");
		if (href==null) href="";
		String type = ele.attributeValue("type");
		if (type==null) type="";
		
		
		// TODO Auto-generated method stub
		return LinkImportComponentFactory.generateComponent(href, rel, type);
	}

}
