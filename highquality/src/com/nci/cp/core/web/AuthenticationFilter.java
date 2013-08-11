package com.nci.cp.core.web;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nci.cp.core.utils.Contants;

/**
 * @company BlueCreation Workspace
 * @author  oliver chan 
 * @version 0.1
 * @date    2011-8-11 
 */
public class AuthenticationFilter implements Filter {

private static Log log = LogFactory.getLog(AuthenticationFilter.class);
	
private Set extensions;
private Set ignoreurls;
private Set<String> filterpaths;
private String loginurl;

public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
   HttpServletRequest httpreq = (HttpServletRequest) request;
    HttpServletResponse httpres = (HttpServletResponse) response;  

   // System.out.println("================filter paths begin==========");
   Iterator it= filterpaths.iterator();   
    while( it.hasNext()){
    	String a=(String)it.next();
    //	System.out.println("======"+a);
    }
   // System.out.println("================filter paths end==========");
    
    String servletPath = httpreq.getServletPath();
 //  System.out.println("====================== current patht: "+servletPath);
    
    String expath=servletPath.substring(servletPath.lastIndexOf("/"));
    String accesspath =servletPath.substring(0,servletPath.lastIndexOf("/")).toLowerCase();
    
  //  System.out.println("accesspath spt: "+accesspath);
  //  System.out.println("ha spt: "+expath);
    String extension = expath.substring(expath.lastIndexOf('.') + 1).toLowerCase();
  //  System.out.println("extension spt: "+extension);
  //  System.out.println("===========session: "+httpreq.getSession().getAttribute(Contants.LOGON_USER_KEY));
    
    String filterPath=servletPath;
    /*if (ignoreurls.contains(servletPath) || extensions.contains(extension)) {
    	chain.doFilter(httpreq, httpres);
   
    }*/
    log.debug("========================= cur path: "+servletPath );
    //如果当前的访问路径是忽略路径
    if (ignoreurls.contains(servletPath)) {
    	log.debug("========================= ignoreurls" );
    	chain.doFilter(httpreq, httpres);
    	return;
    //如果当前的访问路径是控制路径
    }else if (validateAccess(servletPath)) {
    	log.debug("========================= validateAccess" );
      	  if (httpreq.getSession().getAttribute(Contants.LOGON_USER_KEY) == null) { 
      		//System.out.println("user do not be auth ");
              //取得应用的根地址         		
       		 String context =httpreq.getContextPath();
       		  //取得当前访问地址
       	     String redirecturl =httpreq.getRequestURL().toString();     	   
       	     //删除当前访问地址的应用根地址后面的地址
       	     redirecturl=redirecturl.substring(0,redirecturl.indexOf(context)+context.length());      	
       	    //跳转到认证地址
             httpres.sendRedirect(redirecturl+loginurl);            
            
          }        
      	 
    }
   chain.doFilter(httpreq, httpres);
   return;
}

public boolean validateAccess(String accessPath) {
	for(String s:filterpaths){	    	
    	if (accessPath.indexOf(s)!=-1){
    		return true;
    	}
    }
	return false;
}
public void init(FilterConfig filterConfig) throws ServletException {
    extensions = new HashSet();
    ignoreurls = new HashSet();
    filterpaths= new HashSet();
    loginurl = filterConfig.getInitParameter("loginurl"); 
  
    String ignoreurlst = filterConfig.getInitParameter("ignoreurls"); 
    StringTokenizer urls = new StringTokenizer(ignoreurlst, ",");
    while (urls.hasMoreTokens()) {
    	ignoreurls.add(urls.nextToken().toLowerCase());
    }
    
    String ignoreExtensions = filterConfig.getInitParameter("ignoreExtensions");
    StringTokenizer st = new StringTokenizer(ignoreExtensions, ",");
    while (st.hasMoreTokens()) {
        extensions.add(st.nextToken().toLowerCase());
    }
    String filterpathstr = filterConfig.getInitParameter("filterPaths");
    StringTokenizer filterpstr = new StringTokenizer(filterpathstr, ",");
    while (filterpstr.hasMoreTokens()) {
    	filterpaths.add(filterpstr.nextToken().toLowerCase());
    }
        
    
}

public void destroy() {
}

}
