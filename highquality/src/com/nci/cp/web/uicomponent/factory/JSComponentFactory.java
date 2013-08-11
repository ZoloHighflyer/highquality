package com.nci.cp.web.uicomponent.factory;

import com.nci.cp.web.uicomponent.IUIComponent;
import com.nci.cp.web.uicomponent.JSUIComponent;

/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-2-1 
 */
public  class JSComponentFactory  {
    
	
	public static IUIComponent generateComponent(String type,String content) {
		if (type.equals(JSUIComponent.TYPE_REL)) {		   
		   JSUIComponent js = new JSUIComponent(JSUIComponent.TYPE_REL,content);
		   return js;
		}else  {
			JSUIComponent js = new JSUIComponent(JSUIComponent.TYPE_SCRIPT,content);
			   return js;
		}
		
	}

}
