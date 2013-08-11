package com.nci.cp.web.uicomponent.xmlparser;

import java.util.List;

import org.dom4j.Element;
import com.nci.cp.web.uicomponent.IUIComponent;
import com.nci.cp.web.uicomponent.context.IComponentParserLib;
import com.nci.cp.web.uicomponent.factory.FormComponentFactory;

/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-2-4 
 */
public class FormComponentParser extends AbstractComponentParser {
	
	protected static String  INPUT_ID_TAG  ="id";
	protected static String  INPUT_NAME_TAG="name";
	protected static String  INPUT_ACTION_TAG="action";
	protected static String  INPUT_METHOD_TAG="method";

	public IUIComponent parse(Element ele,IComponentParserLib icpLib) {
		String id   = ele.attributeValue(INPUT_ID_TAG);
		String name = ele.attributeValue(INPUT_NAME_TAG);
		String action= ele.attributeValue(INPUT_ACTION_TAG);
		String method =ele.attributeValue(INPUT_METHOD_TAG);
		
        StringBuffer sb = new StringBuffer();
        
        
		List list = ele.elements();
		
		for(int i =0;i<list.size();i++) {
			Element e = (Element)list.get(i);
			
			IComponentParser icp = icpLib.findComponent(e.getName());
			
			if (icp!=null) {
				if (i!=list.size()-1) {
				   sb.append(icp.parse(e,icpLib).render()+"<br>");
				}else{
				   sb.append(icp.parse(e,icpLib).render());
				}
			}
		}
		
		return FormComponentFactory.generateComponent(action, id, method, name, sb.toString());
	}

	
}
