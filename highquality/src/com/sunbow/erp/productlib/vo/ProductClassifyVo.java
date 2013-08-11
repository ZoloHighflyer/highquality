package com.sunbow.erp.productlib.vo;

import com.nci.cp.core.model.BaseVo;

public class ProductClassifyVo extends BaseVo {
	protected Long id=null; 
	protected String categoryNo;

	protected String name;

	protected long parCategoryNo;
	
	protected String parCategoryName;
    
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getParCategoryNo() {
		return parCategoryNo;
	}

	public void setParCategoryNo(long parCategoryNo) {
		this.parCategoryNo = parCategoryNo;
	}

	public String getParCategoryName() {
		return parCategoryName;
	}

	public void setParCategoryName(String parCategoryName) {
		this.parCategoryName = parCategoryName;
	}
	
}
