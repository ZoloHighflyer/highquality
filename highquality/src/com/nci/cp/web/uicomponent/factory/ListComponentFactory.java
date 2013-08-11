package com.nci.cp.web.uicomponent.factory;

import java.util.List;

import com.nci.cp.web.uicomponent.IUIComponent;
import com.nci.cp.web.uicomponent.ListComponent;

/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-2-22 
 */
public class ListComponentFactory {
	public static IUIComponent generateComponent(String id,String name,String cssstyle,List head,List data) {
		IUIComponent table = new ListComponent(id,name,cssstyle,head,data);	
		return table;
	}
}
