package com.nci.cp.web.uicomponent.context;

import com.nci.cp.web.uicomponent.IUIComponent;
import com.nci.cp.web.uicomponent.xmlparser.IComponentParser;


/**
 * @target
 * @company BlueCreation Studio
 * @author OliverChan
 * @version 0.1
 * @date 2011-2-5
 */
public interface IComponentParserLib {
	/**
	 * add a new parser to component lib
	 * @param key
	 * @param iparser            
	 */
	public void addParser(String key, IComponentParser iparser);

	/**
	 * remove a parser from lib with key.
	 * @param key
	 */
	public void deleteParser(String key);
	/**
	 * parse the tag form lib
	 * @param tag
	 * @return
	 */
	public IComponentParser findComponent(String key);

}
