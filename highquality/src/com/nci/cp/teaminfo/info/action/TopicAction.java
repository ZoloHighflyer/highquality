package com.nci.cp.teaminfo.info.action;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import ognl.Ognl;
import ognl.OgnlException;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.nci.cp.core.web.action.DefaultStrutsAction;
import com.nci.cp.teaminfo.info.vo.TopicInfoVo;

public class TopicAction extends DefaultStrutsAction {
	public String markid;
	public String beanid;
	public String serviceName;
	

	public String getMarkid() {
		return markid;
	}

	public void setMarkid(String markid) {
		this.markid = markid;
	}
    
	public String getBeanid() {
		return beanid;
	}

	public void setBeanid(String beanid) {
		this.beanid = beanid;
	}
    
	
	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	@Override
	public String execute() throws Exception {
		WebApplicationContext wac=WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
	    System.out.println("========================="+wac.getBean(beanid).getClass().getName());
	    
	    Method[] ms= wac.getBean(beanid).getClass().getDeclaredMethods();
	   
	    for(int i=0;i<ms.length;i++) {
	    	if (serviceName.equals(ms[i].getName())) {
	    		System.out.println("==================method: "+ms[i].getName());
	    		ms[i].invoke(wac.getBean(beanid),mappingObject());
	    	}
	    	
	    }
		
		
		
		return SUCCESS;
	}
	
    /**
     * mapping the object instance thought config file and Request parameters.
     * @return mappingObject
     */
    private Object mappingObject() {
    	HttpServletRequest req=ServletActionContext.getRequest();
		Map map = req.getParameterMap();
    	Map context = new HashMap();
		context.put(markid, super.retrieveModelInstanse());

		Set set=map.keySet();
		Iterator it=set.iterator();
		while(it.hasNext()) {
			String key = (String)it.next();
			if (map.get(key)!=null) {
			  try {
				   Ognl.setValue("#"+key, context,new Object(), req.getParameter(key));
			  } catch (OgnlException e) {
				   e.printStackTrace();
			  }  			
			}
		}
		
    	return context.get(markid);
    }
	
}
