package com.sunbow.website.view.vo;

import com.nci.cp.core.model.BaseVo;

public class SelectItemVo extends BaseVo {
    protected long id;
    protected String name;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
}
