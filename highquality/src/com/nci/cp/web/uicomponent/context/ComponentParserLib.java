package com.nci.cp.web.uicomponent.context;

import java.util.HashMap;
import java.util.Map;

import com.nci.cp.web.uicomponent.xmlparser.EditableComponentParser;
import com.nci.cp.web.uicomponent.xmlparser.FormComponentParser;
import com.nci.cp.web.uicomponent.xmlparser.IComponentParser;
import com.nci.cp.web.uicomponent.xmlparser.InputComponentParser;
import com.nci.cp.web.uicomponent.xmlparser.JSComponentParser;
import com.nci.cp.web.uicomponent.xmlparser.LinkComponentParser;
import com.nci.cp.web.uicomponent.xmlparser.ListExComponentParser;
import com.nci.cp.web.uicomponent.xmlparser.SelectComponentParser;

/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-2-5 
 */
public class ComponentParserLib implements IComponentParserLib {
    protected Map  componentLib = new HashMap(32);
    
	public ComponentParserLib() {
		super();
		IComponentParser icparser = new JSComponentParser();
		componentLib.put("uicomponent-jsscript", icparser);
		IComponentParser lcparser = new LinkComponentParser();
		componentLib.put("link", lcparser);
	    IComponentParser iiparser = new InputComponentParser();
	    componentLib.put("uicomponent-input",iiparser);
	    IComponentParser ifc = new FormComponentParser();
	    componentLib.put("uicomponent-form", ifc);
	    IComponentParser icl = new ListExComponentParser();
	    componentLib.put("uicomponent-list", icl);
	    IComponentParser slc = new SelectComponentParser();
	    componentLib.put("uicomponent-select", slc);
	    IComponentParser ec = new EditableComponentParser();
	    componentLib.put("uicomponent-editable", ec);
	}


	public void addParser(String key, IComponentParser iparser) {
		componentLib.put(key, iparser);
	}

	
	public void deleteParser(String key) {
	    componentLib.remove(key);
	}


	public IComponentParser findComponent(String key) {
		
		return (IComponentParser)componentLib.get(key);
	}

}
