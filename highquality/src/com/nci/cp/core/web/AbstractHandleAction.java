package com.nci.cp.core.web;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-2-11 
 */
public abstract class AbstractHandleAction extends ActionSupport {
    protected Class clazz;

	public Class getClazz() {
		return clazz;
	}

	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}
    public abstract Object getNewInstance();
}
