package com.nci.cp.web.uicomponent.xmlparser;

import java.util.HashMap;
import java.util.Map;

import ognl.Ognl;
import ognl.OgnlException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Element;

import com.nci.cp.core.utils.WebUtils;
import com.nci.cp.core.web.ContextData;
import com.nci.cp.web.uicomponent.IUIComponent;
import com.nci.cp.web.uicomponent.context.IComponentParserLib;
import com.nci.cp.web.uicomponent.factory.InputComponentFactory;

/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-2-3 
 */
public class InputComponentParser extends AbstractComponentParser {
	private static Log log = LogFactory.getLog(InputComponentParser.class);
	protected static String  INPUT_TYPE_TAG="type";
	protected static String  INPUT_LABEL_TAG="label";
	protected static String  INPUT_ID_TAG  ="id";
	protected static String  INPUT_NAME_TAG="name";
	protected static String  INPUT_VALUE_TAG="value";
	protected static String  INPUT_SIZE_TAG="size";

	
	public IUIComponent  parse(Element ele,IComponentParserLib icpLib) {
		String label= ele.attributeValue(INPUT_LABEL_TAG);
		String type = ele.attributeValue(INPUT_TYPE_TAG);
		String id   = ele.attributeValue(INPUT_ID_TAG);
		String name = ele.attributeValue(INPUT_NAME_TAG);
		String value =ele.attributeValue(INPUT_VALUE_TAG);
		String size = ele.attributeValue(INPUT_SIZE_TAG);
		
		
		if ((value!=null)&&(value.indexOf("#")!=-1)) {
			
			ContextData cd =(ContextData)WebUtils.getRequestAttribute("ccpcontext");
			Object o=null;
			if (cd!=null) {
				if (cd.SCOPE_REQUEST.equals(cd.getScope())){
					o = WebUtils.getRequestAttribute(cd.getKey());
				}else if (cd.SCOPE_SESSION.equals(cd.getScope())){
					o = WebUtils.getSessionAttribute(cd.getKey());
				}
								
				
				Map context = new HashMap();
				context.put(cd.getKey(), o);
				try {
					value = (String)Ognl.getValue(value, context, new Object());
					
				} catch (OgnlException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		
		if (label==null) 
			return InputComponentFactory.generateComponent(type, id, name,value, size);
		return InputComponentFactory.generateComponent(label,type, id, name,value, size);
	}

}
