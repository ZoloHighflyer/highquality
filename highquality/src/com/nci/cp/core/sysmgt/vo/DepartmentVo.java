package com.nci.cp.core.sysmgt.vo;

import com.nci.cp.core.model.BaseVo;
/**
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-8-20 
 */
public class DepartmentVo extends BaseVo {
	
	protected Long           id;
	protected Long           pardepno;
	protected String         depname;
	protected String         descr;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Long getPardepno() {
		return pardepno;
	}
	public void setPardepno(Long pardepno) {
		this.pardepno = pardepno;
	}
	
	
	
}
