package com.nci.cp.core.sysmgt.model;

import com.nci.cp.core.model.AbstractEntity;
/**
 * @target  the object is to storage department info.
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-8-19 
 */
public class Department extends AbstractEntity {
    protected java.util.Date createDate;  	
	protected java.util.Date modifyDate; 
	protected Long           pardepno;
	protected String         depname;
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
	public Long getPardepno() {
		return pardepno;
	}
	public void setPardepno(Long pardepno) {
		this.pardepno = pardepno;
	}
	public String getDepname() {
		return depname;
	}
	public void setDepname(String depname) {
		this.depname = depname;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	
}
