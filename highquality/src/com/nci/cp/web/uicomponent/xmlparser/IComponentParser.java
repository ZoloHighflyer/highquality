package com.nci.cp.web.uicomponent.xmlparser;

import org.dom4j.Element;

import com.nci.cp.web.uicomponent.IUIComponent;
import com.nci.cp.web.uicomponent.context.IComponentParserLib;


/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-2-1 
 */
public interface IComponentParser {
        public IUIComponent parse(Element ele,IComponentParserLib icpLib) ;
}
