package com.nci.cp.teaminfo.km.vo;

import com.nci.cp.core.model.BaseVo;


public class KnowledgeCategoryVo extends BaseVo {
    protected Long id=null;    
    protected String name; 
    protected String owner;
    protected long   parcategoryno;
    protected String parCategoryName;
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
	public long getParcategoryno() {
		return parcategoryno;
	}
	public void setParcategoryno(long parcategoryno) {
		this.parcategoryno = parcategoryno;
	}
	public String getParCategoryName() {
		return parCategoryName;
	}
	public void setParCategoryName(String parCategoryName) {
		this.parCategoryName = parCategoryName;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	
	
}
