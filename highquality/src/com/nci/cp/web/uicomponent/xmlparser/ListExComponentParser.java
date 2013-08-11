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
import com.nci.cp.teaminfo.info.vo.TopicInfoVo;
import com.nci.cp.web.uicomponent.IUIComponent;
import com.nci.cp.web.uicomponent.context.IComponentParserLib;
import com.nci.cp.web.uicomponent.factory.ListComponentFactory;
/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-4-27
 */
public class ListExComponentParser extends AbstractComponentParser {
	private static Log log = LogFactory.getLog(ListExComponentParser.class);
	protected static String LIST_ID_TAG = "id";
	protected static String LIST_NAME_TAG = "name";
	protected static String LIST_CSSSTYLE_TAG = "cssstyle";
	protected static String LIST_HEAD_TAG = "header";
	protected static String LIST_DATA_TAG = "list";
	protected static String LIST_VAR_TAG = "var";
	protected static String LIST_KEY_TAG = "key";
	protected static String LIST_COL_NAME_TAG  = "name";
	protected static String LIST_COL_VALUE_TAG = "value";
	
	public IUIComponent parse(Element ele, IComponentParserLib icpLib) {
		String id         = ele.attributeValue(LIST_ID_TAG);
		String name       = ele.attributeValue(LIST_NAME_TAG);
		String liststr    = ele.attributeValue(LIST_DATA_TAG);
		String cssstyle   = ele.attributeValue(LIST_CSSSTYLE_TAG);
		String var        = ele.attributeValue(LIST_VAR_TAG);
		String key        = ele.attributeValue(LIST_KEY_TAG);
		
		
		//trieve all cols config,and put then into a list
		List colslist    = ele.elements();
		List colcfgs     = new ArrayList();
		List headerlist  = new ArrayList(32);
		for (int i=0;i<colslist.size();i++) {
			Element ecol = (Element)colslist.get(i);			
			colcfgs.add(new ColData(ecol.attributeValue(LIST_COL_NAME_TAG),
					                ecol.attributeValue(LIST_COL_VALUE_TAG)));
			
			headerlist.add(ecol.attributeValue(LIST_COL_NAME_TAG));
		}	
		
		/*		
		List   headerlist = new ArrayList(32);		
		if (header!=null){
			String[] hl =header.split(",");			
			if ((hl!=null)&&(hl.length>0)) {
				int al = hl.length;
				for (int i=0;i<al;i++ )
					headerlist.add(hl[i]);				
			}			
		}*/
		
		
		
		List datalist  = null;
		List valuelist = new ArrayList(32);
		//change the list data to ColData object ,which view can explain on the page.
		if ((liststr != null) && (liststr.indexOf("#") != -1)&&(var!=null)&&(key.indexOf("#")!=-1)) {
			ContextData cd = (ContextData) WebUtils
					.getRequestAttribute("ccpcontext");
			//get the value from req or session
			if (cd != null) {
				if (cd.SCOPE_REQUEST.equals(cd.getScope())) {
					datalist = (List) WebUtils.getRequestAttribute(cd.getKey());
				} else if (cd.SCOPE_SESSION.equals(cd.getScope())) {
					datalist = (List) WebUtils.getSessionAttribute(cd.getKey());
				}
						}
			if (datalist!=null) {
				Object o = null;
				
			  for (int i=0;i<datalist.size();i++) {
				o = datalist.get(i);
				
				List colsl = new ArrayList();
				colsl.add(trieveValueFromOgnl(var,key,o));
				for (int j=0;j<colcfgs.size();j++) {
					ColData c = (ColData)colcfgs.get(j);
					colsl.add(trieveValueFromOgnl(var,c.getValue(),o));
				}
				
				valuelist.add(colsl);
			  }
			}
			
		}
		return ListComponentFactory.generateComponent(id,name,cssstyle,headerlist, valuelist);
	}
	
	/**
	 * the method use ognl trieve value from key in o
	 * @param var
	 * @param key
	 * @param o
	 * @return 
	 */
	private Object trieveValueFromOgnl(String var,String key,Object o) {
		Object value = null;
		Map context = new HashMap();
		context.put(var, o);
		try {
			value =Ognl.getValue(key, context, new Object());
			return value;
			
		} catch (OgnlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * the object store data in column
	 * 
	 */
	public class ColData {
		private String name=null;
		private String value=null;
		
		public ColData() {
			super();
		}
		public ColData(String name, String value) {
			super();
			this.name = name;
			this.value = value;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		
	}
  
}

