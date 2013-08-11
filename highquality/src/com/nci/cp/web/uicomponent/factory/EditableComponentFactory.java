package com.nci.cp.web.uicomponent.factory;

import java.util.List;

import com.nci.cp.web.uicomponent.EditableComponent;
import com.nci.cp.web.uicomponent.IUIComponent;


/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-6-19 
 */
public class EditableComponentFactory {
	public static IUIComponent generateComponent(String label ,String id,String name,String cssstyle,String action,String itemname,String actionname) {
		IUIComponent ec = new EditableComponent(id,name,label,cssstyle,action,itemname,actionname);	
		return ec;
	}
}
