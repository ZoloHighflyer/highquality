package com.sunbow.website.view.vo;

import java.util.ArrayList;
import java.util.List;

import com.nci.cp.core.model.BaseVo;

public class ViewVo extends BaseVo {
	protected long id;

    protected String viewId;
	//分类名称
	protected String name; 
	//备注
    protected String mark;
    
    protected String productids;
    
    protected String modifyDate;
    
    protected List   products=new ArrayList();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getViewId() {
		return viewId;
	}

	public void setViewId(String viewId) {
		this.viewId = viewId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getProductids() {
		return productids;
	}

	public void setProductids(String productids) {
		this.productids = productids;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public List getProducts() {
		return products;
	}

	public void setProducts(List products) {
		this.products = products;
	}
        
}
