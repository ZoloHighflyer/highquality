package com.nci.cp.web.uicomponent.factory;

import com.nci.cp.web.uicomponent.IUIComponent;
import com.nci.cp.web.uicomponent.InputComponent;

/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-2-3 
 */
public  class InputComponentFactory {
    public static IUIComponent generateComponent(String type,String id,String name,String value,String size){
    	IUIComponent inputc = new InputComponent(type,id,name,value,size);
    	return inputc;
    }
    public static IUIComponent generateComponent(String label,String type,String id,String name,String value,String size){
    	IUIComponent inputc = new InputComponent(label,type,id,name,value,size);
    	return inputc;
    }
    
}
