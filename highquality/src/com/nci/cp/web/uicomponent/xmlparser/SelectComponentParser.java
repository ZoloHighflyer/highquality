package com.nci.cp.web.uicomponent.xmlparser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.nci.cp.web.uicomponent.factory.SelectComponentFactory;
import com.nci.cp.web.uicomponent.xmlparser.ListExComponentParser.ColData;
/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-5-10
 */
public class SelectComponentParser extends AbstractComponentParser {
	private static Log log = LogFactory.getLog(SelectComponentParser.class);
	protected static String ID_TAG = "id";
	protected static String NAME_TAG = "name";
	protected static String LABEL_TAG = "label";
	protected static String CSSSTYLE_TAG = "cssstyle";
	protected static String DATA_TAG = "list";
	public IUIComponent parse(Element ele, IComponentParserLib icpLib) {
		log.info("SelectComponentParser.parse  begin");
		String id         = ele.attributeValue(ID_TAG);
		String name       = ele.attributeValue(NAME_TAG);
		String label      = ele.attributeValue(LABEL_TAG);
		String liststr    = ele.attributeValue(DATA_TAG);
		String cssstyle   = ele.attributeValue(CSSSTYLE_TAG);
		Object data = null;
		Object dm   = null;
		log.debug("liststr: "+liststr);
		if ((liststr != null) && (liststr.indexOf("#") != -1)) {
			
			ContextData cd = (ContextData) WebUtils
			.getRequestAttribute("ccpcontext");
			
			
	        //get the value from req or session
	        if (cd != null) {
		       if (cd.SCOPE_REQUEST.equals(cd.getScope())) {
			      data = WebUtils.getRequestAttribute(cd.getKey());
			      log.debug("from request get value ContextData.");		       
		       } else if (cd.SCOPE_SESSION.equals(cd.getScope())) {
			      data = WebUtils.getSessionAttribute(cd.getKey());
			      log.debug("from session get value ContextData.");
		       }
		       
		       
				Map context = new HashMap();
				context.put(cd.getKey(), data);
				try {
					dm =Ognl.getValue(liststr, context, new Object());
										
				} catch (OgnlException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }else {
	        	log.debug("get context data is null! ");
	        }
		    
		}
		 log.info("SelectComponentParser.parse  end");  
	        

		return SelectComponentFactory.generateComponent(label,id, name, cssstyle, (List)dm);
	}

}
