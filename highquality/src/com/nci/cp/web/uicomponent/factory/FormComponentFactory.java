package com.nci.cp.web.uicomponent.factory;

import com.nci.cp.web.uicomponent.FormComponent;
import com.nci.cp.web.uicomponent.IUIComponent;

/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-2-4 
 */
public final class FormComponentFactory {
    public static IUIComponent generateComponent(String action, String id, String method, String name,String content) {
    	IUIComponent ic = new FormComponent(action, id, method, name,content);
    	return ic;
    }
}
