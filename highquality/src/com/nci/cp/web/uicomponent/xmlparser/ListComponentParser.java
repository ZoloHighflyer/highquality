package com.nci.cp.web.uicomponent.xmlparser;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

import com.nci.cp.core.utils.WebUtils;
import com.nci.cp.core.web.ContextData;
import com.nci.cp.web.uicomponent.IUIComponent;
import com.nci.cp.web.uicomponent.context.IComponentParserLib;
import com.nci.cp.web.uicomponent.factory.ListComponentFactory;
/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-4-25
 */
public class ListComponentParser extends AbstractComponentParser {
	protected static String  LIST_ID_TAG  ="id";
	protected static String  LIST_NAME_TAG="name";
	protected static String  LIST_CSSSTYLE_TAG="cssstyle";
	protected static String  LIST_HEAD_TAG="header";
	protected static String  LIST_DATA_TAG="list";
	public IUIComponent parse(Element ele, IComponentParserLib icpLib) {
		String id         = ele.attributeValue(LIST_ID_TAG);
		String name       = ele.attributeValue(LIST_NAME_TAG);
		String header     = ele.attributeValue(LIST_HEAD_TAG);
		String liststr    = ele.attributeValue(LIST_DATA_TAG);
		String cssstyle   = ele.attributeValue(LIST_CSSSTYLE_TAG);
		List   headerlist = new ArrayList(32);
		
		if (header!=null){
			String[] hl =header.split(",");
			
			if ((hl!=null)&&(hl.length>0)) {
				int al = hl.length;
				for (int i=0;i<al;i++ )
					headerlist.add(hl[i]);				
			}
			
		}
		
		List datalist  = null;
		if ((liststr!=null)&&(liststr.indexOf("#")!=-1)) {
		ContextData cd =(ContextData)WebUtils.getRequestAttribute("ccpcontext");
		
		if (cd!=null) {
			if (cd.SCOPE_REQUEST.equals(cd.getScope())){
				datalist = (List)WebUtils.getRequestAttribute(cd.getKey());
			}else if (cd.SCOPE_SESSION.equals(cd.getScope())){
				datalist = (List)WebUtils.getSessionAttribute(cd.getKey());
			}
		}
		}
		return ListComponentFactory.generateComponent(id,name,cssstyle,headerlist, datalist);
	}

}
