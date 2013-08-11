package com.nci.cp.web.uicomponent.factory;

import java.util.List;

import com.nci.cp.web.uicomponent.IUIComponent;
import com.nci.cp.web.uicomponent.SelectComponent;
import com.nci.cp.web.uicomponent.model.SelectItem;

/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-5-10 
 */
public class SelectComponentFactory {
	public static IUIComponent generateComponent(String label ,String id,String name,String cssstyle,List<SelectItem> selectlist) {
		IUIComponent sec = new SelectComponent(label,id,name,cssstyle,selectlist);	
		return sec;
	}
}
