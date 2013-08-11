package com.nci.cp.web.uicomponent.factory;

import com.nci.cp.web.uicomponent.IUIComponent;

/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-2-1 
 */
public final class ComponentFactory {
    public static IUIComponent generateJsUIComponent(String type,String content) {    	     
    	return JSComponentFactory.generateComponent(type,content);
    }
}
