package com.nci.cp.web.uicomponent.xmlparser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Element;

import com.nci.cp.web.uicomponent.IUIComponent;
import com.nci.cp.web.uicomponent.context.IComponentParserLib;
import com.nci.cp.web.uicomponent.factory.EditableComponentFactory;

/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-7-11
 */
public class EditableComponentParser extends AbstractComponentParser {
	private static Log log = LogFactory.getLog(EditableComponentParser.class);
	protected String  INPUT_ACTION_TAG="action";
	protected String  INPUT_ITEMNAME_TAG="itemname";
	protected String  INPUT_ACTIONNAME_TAG="actionname";
	public IUIComponent parse(Element ele, IComponentParserLib icpLib) {
		String id         = ele.attributeValue(INPUT_ID_TAG);
		String name       = ele.attributeValue(INPUT_NAME_TAG);
		String label      = ele.attributeValue(INPUT_LABEL_TAG);
		String css        = ele.attributeValue(INPUT_CSSSTYLE_TAG);
		String action     = ele.attributeValue(INPUT_ACTION_TAG);
		String itemname   = ele.attributeValue(INPUT_ITEMNAME_TAG);
		String actionname   = ele.attributeValue(INPUT_ACTIONNAME_TAG);
		return EditableComponentFactory.generateComponent(label, id, name, css,action,itemname,actionname);
	}

}
