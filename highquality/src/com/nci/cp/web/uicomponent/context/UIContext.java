package com.nci.cp.web.uicomponent.context;


import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.nci.cp.core.utils.WebUtils;
import com.nci.cp.core.utils.XmlParserUtils;
import com.nci.cp.web.uicomponent.xmlparser.IComponentParser;



/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-2-5 
 */
public class UIContext implements IUIContext {
    public IComponentParserLib icpLib;	
    
	public IComponentParserLib getIcpLib() {
		return icpLib;
	}

	public void setIcpLib(IComponentParserLib icpLib) {
		this.icpLib = icpLib;
	}


	public String parse(String viewid) throws Exception{
		Document xmlDoc = null;
		try {		
			xmlDoc=XmlParserUtils.trieveDocument(this.getClass().getResource("/")+"bccfg.xml");			 
			if (xmlDoc==null) throw new Exception("Can not get the xml file!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
	        
            StringBuffer sb = new StringBuffer();            
            Element viewidele = XmlParserUtils.findElementByAttr(xmlDoc.getRootElement(), "id",viewid);
			if(viewidele==null) {
				throw new Exception("the root node is null!"); 
			}
			Element head       = XmlParserUtils.findElementByName(viewidele,"head"); 
            Element contentele = XmlParserUtils.findElementByName(viewidele,"content"); 
			
            sb.append("<HTML>");
              sb.append("<HEAD>");
                sb.append("<TITLE>"+head.attributeValue("title")+"</TITLE>"); 
                  if (head!=null)
                   parsetags(head,sb);
              sb.append("</HEAD>");
              sb.append("<BODY>");
                 
                 if ("topicmanagement".equals(viewid)) {
                	 sb.append("<a href=\"addtpview.action\">增加主题</a>" +
                	 		"&nbsp;&nbsp;&nbsp;&nbsp;" +
                	 		"<a href=\"javascript:updateopt();\">编辑主题</a>" +
                	 		"&nbsp;&nbsp;&nbsp;&nbsp;" +
                	 		"<a href=\"javascript:delopt();\">删除主题</a><br>");
                 }else if("blunchmanagement".equals(viewid)){
                	 sb.append("<a href=\"addbrview.action\">增加订餐</a>"+
                			 "&nbsp;&nbsp;&nbsp;&nbsp;" +
                 	 		 "<a href=\"javascript:updateopt();\">编辑订餐</a>"+
                 	 		 "&nbsp;&nbsp;&nbsp;&nbsp;" +
         	 		         "<a href=\"javascript:delopt();\">删除订餐</a><br>");
                 }
                 
                 
                 if (contentele!=null)
                  parsetags(contentele,sb);             
              sb.append("</BODY>");
           
            sb.append("</HTML>");
            WebUtils.setRequestAttribute("ccpcontext", null);
            return sb.toString();
        } catch (Exception e) {
          //  LOG.error("Unable to render Velocity Template, '" + finalLocation + "'", e);
            throw e;
        }
	
	}
	
	
	public  void parsetags(Element ele,StringBuffer sb) {
		List list = ele.elements();
		
		for(int i =0;i<list.size();i++) {
			Element e = (Element)list.get(i);
			
			IComponentParser icp = icpLib.findComponent(e.getName());
			
			if (icp!=null) {
				sb.append(icp.parse(e,icpLib).render());
			}
		}
		
		
	}
	private Document initConfig(String file){
		return null;
	}
}
