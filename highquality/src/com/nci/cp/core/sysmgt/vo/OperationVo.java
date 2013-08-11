package com.nci.cp.core.sysmgt.vo;

import com.nci.cp.core.model.BaseVo;

/**
 * Common Platform Team
 * @author Oliver Chan
 * @since  0.1
 */
public class OperationVo extends BaseVo {
	private Long   id;
	private String name;
	private String optmethod;
	private String descr;
	private Long   funcId;
	private String funcname;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getFuncname() {
		return funcname;
	}
	public void setFuncname(String funcname) {
		this.funcname = funcname;
	}
    

	
	
   
}
