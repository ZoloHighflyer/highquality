package com.nci.cp.core.web;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * 类说明 过滤器和设置用户提交编码（UTF-8）
 * @author 吴庆龙
 * @version 1.0
 * 创建时间：2009-12-16
 * 修改时间：
 * 修改人：
 */
public class SetCharacterEncodingFilter implements Filter {

	public SetCharacterEncodingFilter() {
		encoding = null;
		filterConfig = null;
		ignore = true;
	}

	public void destroy() {
		encoding = null;
		filterConfig = null;
		login_Uri = null;
	}
	/**
	 * 设置提交时的编码格式（UTF-8）
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (ignore || request.getCharacterEncoding() == null) {
			String encoding = selectEncoding(request);
			if (encoding != null)
				request.setCharacterEncoding(encoding);
		}
		 sessionChecker(request,response,chain);
	}
	/**
	 * 一个过滤器,过滤提交的信息
	 * @param request  ServletRequest
	 * @param response ServletResponse
	 * @param chain FilterChain
	 * @throws IOException IO异常
	 * @throws ServletException  servlet异常
	 */
	protected void sessionChecker(ServletRequest request,
			ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		String requestURI = httpRequest.getRequestURI();
		//设置只过滤action、jsp、frameset,其他的都不进行过滤
		if (!requestURI.endsWith("action") && !requestURI.endsWith("jsp")
				&& !requestURI.endsWith("frameset")) {
			chain.doFilter(httpRequest, httpResponse);
		} else {
			//是登录页面时直接提交到登录页面
			if (requestURI.endsWith("/login.jsp")
					|| requestURI.endsWith("/")) {
				chain.doFilter(httpRequest, httpResponse);
				return;
			} else {
				//访问的是loginAction时直接提交
				if(requestURI.endsWith("loginAction.action")){
					chain.doFilter(httpRequest, httpResponse);
					return;
				}		
				//如果服务器重启过，则要求用户重新登录
				if (session == null) {
					httpResponse.sendRedirect(httpResponse
							.encodeRedirectURL(login_Uri));
				} else {
					//登录成功时设置登录时间到session里
					if (session.getAttribute("loginTime") != null) {
						Date loginTime = (Date) session
								.getAttribute("loginTime");
						Date contextStartTime = (Date) session
								.getServletContext().getAttribute(
										"contextStartTime");
						//登录时间小于上下文开始时间,要求用户重新登录
						if (loginTime.before(contextStartTime)) {
							session.setAttribute("loginTime", null);
							httpResponse.sendRedirect(httpResponse
									.encodeRedirectURL(login_Uri));
						} else {
							chain.doFilter(httpRequest, httpResponse);
							return;
						}
					} else {
						//session过期
						httpResponse.sendRedirect(httpResponse
								.encodeRedirectURL(login_Uri));
					}
				}
			}
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.login_Uri = filterConfig.getInitParameter("targetURI");
		this.filterConfig = filterConfig;
		encoding = filterConfig.getInitParameter("encoding");
		String value = filterConfig.getInitParameter("ignore");
		if (value == null)
			ignore = true;
		else if (value.equalsIgnoreCase("true"))
			ignore = true;
		else if (value.equalsIgnoreCase("yes"))
			ignore = true;
		else
			ignore = false;
	}

	protected String selectEncoding(ServletRequest request) {
		return encoding;
	}

	protected String encoding;
	protected FilterConfig filterConfig;
	protected boolean ignore;
	// 获取错误信息,用户登录页面
	protected String login_Uri = "";

	public boolean test(int node) {
		// TODO Auto-generated method stub
		return false;
	}
}