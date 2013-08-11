package com.nci.cp.core.sysmgt.model;

import com.nci.cp.core.model.AbstractEntity;
/**
 * @target  the object is to storage operation info.
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-8-19 
 */
public class Operation extends AbstractEntity {
    protected java.util.Date createDate;
	protected java.util.Date modifyDate;       
	private String name;
	private String optmethod;
	private String descr;
	private Long   funcId;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOptmethod() {
		return optmethod;
	}
	public void setOptmethod(String optmethod) {
		this.optmethod = optmethod;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Long getFuncId() {
		return funcId;
	}
	public void setFuncId(Long funcId) {
		this.funcId = funcId;
	}
	
	
}
