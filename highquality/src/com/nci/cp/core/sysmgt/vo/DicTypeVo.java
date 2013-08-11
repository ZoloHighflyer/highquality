package com.nci.cp.core.sysmgt.vo;

import com.nci.cp.core.model.BaseVo;

public class DicTypeVo extends BaseVo {
	protected long   id;
	protected String identifyCode;
	protected String name;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIdentifyCode() {
		return identifyCode;
	}
	public void setIdentifyCode(String identifyCode) {
		this.identifyCode = identifyCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
