package com.nci.cp.core.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.nci.cp.core.sysmgt.model.IUserInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * Common Platform Team
 * 
 * @author Oliver Chan
 * @since 0.1
 */
public final class WebUtils {
	public static HttpServletRequest getServletRequest() {
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest req = (HttpServletRequest) ctx
				.get(ServletActionContext.HTTP_REQUEST);
		

		return req;
	}

	public static HttpSession getServletSession() {
		HttpServletRequest req = getServletRequest();
		return req.getSession();
	}

	public static void setRequestAttribute(String key, Object value) {
		getServletRequest().setAttribute(key, value);
	}

	public static Object getRequestAttribute(String key) {
		return getServletRequest().getAttribute(key);
	}

	public static void setSessionAttribute(String key, Object value) {
		getServletSession().setAttribute(key, value);
	}

	public static Object getSessionAttribute(String key) {
		return getServletSession().getAttribute(key);
	}
	public static IUserInfo getCurrentUser() {
		IUserInfo usr=(IUserInfo)WebUtils.getSessionAttribute(Contants.LOGON_USER_KEY);
		
		return usr;
	}
}
