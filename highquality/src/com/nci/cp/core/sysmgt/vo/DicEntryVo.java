package com.nci.cp.core.sysmgt.vo;

import com.nci.cp.core.model.BaseVo;

public class DicEntryVo extends BaseVo {
	protected long   id;
	protected String name;
	protected int    identify;
    protected DicTypeVo dicTypeVo;
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
	public int getIdentify() {
		return identify;
	}
	public void setIdentify(int identify) {
		this.identify = identify;
	}
	public DicTypeVo getDicTypeVo() {
		return dicTypeVo;
	}
	public void setDicTypeVo(DicTypeVo dicTypeVo) {
		this.dicTypeVo = dicTypeVo;
	}
    
    
}
