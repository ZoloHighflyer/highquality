package com.nci.cp.core.sysmgt.model;


import com.nci.cp.core.model.AbstractEntity;
/**
 * @target  the object is to storage func info.
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-5-18 
 */
public class Func extends AbstractEntity {
       	
	protected long           parfuncno;
	
	protected String         funcname;
	
	protected String         funcaction;
	
	protected String         descr;
	
       
	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	public java.util.Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}

    	

	public long getParfuncno() {
		return parfuncno;
	}

	public void setParfuncno(long parfuncno) {
		this.parfuncno = parfuncno;
	}

	public String getFuncname() {
		return funcname;
	}

	public void setFuncname(String funcname) {
		this.funcname = funcname;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getFuncaction() {
		return funcaction;
	}

	public void setFuncaction(String funcaction) {
		this.funcaction = funcaction;
	}
	
	
	
	
}
