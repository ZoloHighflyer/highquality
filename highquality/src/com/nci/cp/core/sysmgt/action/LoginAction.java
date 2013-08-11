package com.nci.cp.core.sysmgt.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.nci.cp.core.exception.ServiceException;
import com.nci.cp.core.sysmgt.model.User;
import com.nci.cp.core.sysmgt.service.ISysManagement;
import com.nci.cp.core.utils.Contants;
import com.nci.cp.core.utils.WebUtils;
import com.nci.cp.core.web.struts.AbstractStrutsAction;
import com.opensymphony.xwork2.ActionContext;
/**
 * The interface for user.
 * @company BlueCreation Workspace
 * @author  yanfeng 
 * @version 0.1
 * @date    2011-08-04 
 */
public class LoginAction extends AbstractStrutsAction {
	private static Log log = LogFactory.getLog(LoginAction.class);
	private ISysManagement sysMgmt;
	private User user;

	public User getUser() {

		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setSysMgmt(ISysManagement sysMgmt) {
		this.sysMgmt = sysMgmt;
	}

	/**
	 * @return
	 * @throws ServiceException
	 * 有三种情况是调用此方法：
	 * 1）Session的存储用户信息变量的值为空
	 * 2）用户提交认证信息
	 * Case 1)
	 * 在Session的变量值为空，有两种可能：一是用户的会话过期或服务器关闭/重启；二是用户没登录就直接访问地址。
	 * 由两种情况的结果一样，从现象没法区别两种情况的原因，在分析原因跳转提示一致：会话无效请重新登录。
	 * 
	 * Case 2)
	 * 用户提交认证信息是跳过过滤器，与Case 1)区别的是User对象不为空。在这种情况下只处理提交的用户信息，如果
	 * 用户的认证不通过则返回用户名密码的错误，如果通过就跳转到登录成功的系统页面。
	 * 
	 */
	public String login() throws ServiceException{
		if (user==null) {
			System.out.println("============== go to input!");
			return INPUT;
		}
		try{
			user = (User)sysMgmt.authentication(user);
			if(user!=null){				
				WebUtils.setSessionAttribute(Contants.LOGON_USER_KEY, user);
				return SUCCESS;
			}
			HttpServletRequest request=ServletActionContext.getRequest();
			request.setAttribute("errormessage", "用户名与密码不符，请重新输入！");
			WebUtils.setRequestAttribute("errormessage", "用户名与密码不符，请重新输入！");
			log.debug("========================= user login message is error ");
			return ERROR;

		}catch (ServiceException e) {
			log.error("LoginAction.class login() fail,as ! "+e.getMessage());
			throw new ServiceException("LoginAction.class login() fail! error info=== "+e.getMessage()); 
		}
	}
	
	/**
	 * 用户退出
	 */
	public void logout() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		session.invalidate(); // invalid() 没有失效
		// session. = null; 
		try {
			response.sendRedirect("login.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
