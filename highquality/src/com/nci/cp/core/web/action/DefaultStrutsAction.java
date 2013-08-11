package com.nci.cp.core.web.action;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import ognl.NullHandler;
import ognl.OgnlException;
import ognl.OgnlRuntime;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.nci.cp.core.utils.CommonUtils;
import com.nci.cp.core.web.ContextData;
import com.nci.cp.core.web.struts.AbstractStrutsAction;
import com.opensymphony.xwork2.util.OgnlUtil;

/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-2-7 
 */
public class DefaultStrutsAction  extends AbstractStrutsAction {
	private static Log log = LogFactory.getLog(DefaultStrutsAction.class);
	protected static String SCOPE_SESSION="SCOPE_SESSION";
	protected static String SCOPE_REQUEST="SCOPE_REQUEST";
	protected Class clzName = null;
	protected String markid;
	protected String beanid;
	protected String serviceName;
	protected boolean rtRS=false;
	protected String scope;
	protected boolean input=false;
	
  
	public Class getClzName() {
		return clzName;
	}
 
	public void setClzName(Class clzName) {
		this.clzName = clzName;
	}
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
	
	
	
	public boolean isRtRS() {
		return rtRS;
	}

	public void setRtRS(boolean rtRS) {
		this.rtRS = rtRS;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
    
	
	public boolean isInput() {
		return input;
	}

	public void setInput(boolean input) {
		this.input = input;
	}

	public Object retrieveModelInstanse() {
		Object obj = null;
		try {
			obj = clzName.newInstance();		
			return obj;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String execute() throws Exception {
		WebApplicationContext wac=WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
	    Method[] ms= wac.getBean(beanid).getClass().getDeclaredMethods();		
	    for(int i=0;i<ms.length;i++) {
	    	if (serviceName.equals(ms[i].getName())) {    		
	    		try {
	    			log.debug("before run: "+beanid+"."+serviceName);
	    			Object rst = null;
	    			if (input) {
	    			  log.debug("before run: "+beanid+"."+serviceName+" input handle!");
	    			  rst=ms[i].invoke(wac.getBean(beanid),mappingObject());
	    			}else {
	    			  log.debug("before run: "+beanid+"."+serviceName+" no input handle!");
	    		      rst = ms[i].invoke(wac.getBean(beanid));
	    		      log.debug("return result: "+rst.getClass().getName());
	    			}
	    			if (rtRS)  setScopeParameter(scope,markid,rst);
	    			log.debug("after run: "+beanid+"."+serviceName);
				} catch (Exception e) {
					e.printStackTrace();
				}
	    	}	    	
	    }
				
		return super.execute();
	}
	
	private void setScopeParameter(String scope,String key,Object value) {
		ContextData cd = new ContextData();
		cd.setIset(true);
		cd.setKey(markid);
		if (SCOPE_REQUEST.equalsIgnoreCase(scope)) {
			CommonUtils.setRequestParameter(key, value);
			cd.setScope(SCOPE_REQUEST);		
		}else if (SCOPE_SESSION.equalsIgnoreCase(scope)) {
			CommonUtils.setSessionParameter(key, value);
			cd.setScope(SCOPE_SESSION);		
		}
		
		CommonUtils.setRequestParameter("ccpcontext", cd);
	}
    /**
     * mapping the object instance thought config file and Request parameters.
     * @return mappingObject
     */
    private Object mappingObject() throws Exception {
    	HttpServletRequest req=ServletActionContext.getRequest();
    	
		Map map = req.getParameterMap();
    	Map context = new HashMap();
    	OgnlRuntime.setNullHandler(this.clzName, new NullHandler(){
    		public Object nullMethodResult(Map map,Object o,String s,Object[] objects){
    			return null;
    		}
    		public Object nullPropertyValue(Map map,Object o,Object o1){
    			String methodName = o1.toString();
    			String getter = "set"+methodName.substring(0,1).toUpperCase()+methodName.substring(1);
    			Method[] methods = o.getClass().getDeclaredMethods();
    			for (Method method:methods) {
    				String name = method.getName();
    				if (!getter.equals(name)||(method.getParameterTypes().length!=1)) {
    					continue;
    				}else {
    					Class clazz = method.getParameterTypes()[0];
    					try {
    						Object param = clazz.newInstance();
    						method.invoke(o, new Object[]{param});
    						return param;
    					}catch(Exception e) {
    						throw new RuntimeException(e);
    					}
    				}
    			}
    			return null;
    		}
    	});
		context.put(markid, this.retrieveModelInstanse());
		           //data,vo

		Set set=map.keySet();
		Iterator it=set.iterator();
		while(it.hasNext()) {
			String key = (String)it.next();
	
			if (map.get(key)!=null) {
			  try {
				   //set req value to the backgroud bean
				   OgnlUtil.setValue("#"+key, context,new Object(), req.getParameter(key));
				                 //#data.title 
				   
			  } catch (OgnlException e) {
				   e.printStackTrace();
			  } catch (Exception ex) 	{
				  ex.printStackTrace();
			  }
			}
		}
		
    	return context.get(markid);
    }
    
    public String  initpage() {
    	log.info(this.getClzName()+" enter initpage()");
    	log.info(this.getClzName()+" exit  initpage()");
    	return SUCCESS;
    }

}
