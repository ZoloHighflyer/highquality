package com.nci.cp.core.web.struts.dispatcher;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.StrutsResultSupport;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.nci.cp.web.uicomponent.context.IUIContext;
import com.nci.cp.web.uicomponent.xmlparser.IComponentParser;
import com.nci.cp.web.uicomponent.xmlparser.InputComponentParser;
import com.nci.cp.web.uicomponent.xmlparser.JSComponentParser;
import com.nci.cp.web.uicomponent.xmlparser.LinkComponentParser;
import com.opensymphony.xwork2.ActionInvocation;

/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-1-29 
 */
public class XMLResultView extends StrutsResultSupport {
	private IUIContext uicontext;
	

	public IUIContext getUicontext() {
		return uicontext;
	}
	public void setUicontext(IUIContext uicontext) {
		this.uicontext = uicontext;
	}
	@Override
	protected void doExecute(String finalLocation, ActionInvocation invocation)
			throws Exception {		
	
		//HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=GBK");
		
        try {          
            Writer writer = new OutputStreamWriter(response.getOutputStream());           
            writer.write( uicontext.parse(finalLocation));
            // always flush the writer (we used to only flush it if this was a jspWriter, but someone asked
            // to do it all the time (WW-829). Since Velocity support is being deprecated, we'll oblige :)
            writer.flush();
            writer.close();
        } catch (Exception e) {
          //  LOG.error("Unable to render Velocity Template, '" + finalLocation + "'", e);
            throw e;
        }

	}
	

}
