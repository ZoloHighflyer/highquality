package com.nci.cp.core.web;

import com.nci.cp.core.sysmgt.model.IUserInfo;
import com.nci.cp.core.utils.Contants;
import com.nci.cp.core.utils.WebUtils;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Common Platform Team * 
 * @author Oliver Chan
 * @since 0.1
 */
public abstract class AbstractAction extends ActionSupport implements IAction {
	protected String action = null;
	protected String msg    = null;
	
    public IUserInfo getCurUser() {
    	return (IUserInfo)WebUtils.getSessionAttribute(Contants.LOGON_USER_KEY);
    }
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}	
	
    
	public String initadd() throws Exception{
		return SUCCESS;
	}

	public String save() throws Exception{
		return SUCCESS;
	}

	public String initedit() throws Exception{
		return SUCCESS;
	}

	public String delete() throws Exception{
		return SUCCESS;
	}

	public String list() throws Exception{
		return SUCCESS;
	}

	public String view() throws Exception{
		return SUCCESS;
	}

}
