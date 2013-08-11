package com.sunbow.website.view.model;

import java.util.Set;
import java.util.TreeSet;

import com.nci.cp.core.model.AbstractEntity;

public class SiteView extends AbstractEntity {
	
	protected Set viewproducts=new TreeSet() ;   
	//分类编号
	protected String viewId;
	//分类名称
	protected String name; 
	//备注
    protected String mark;
	

	public Set getViewproducts() {
		return viewproducts;
	}

	public void setViewproducts(Set viewproducts) {
		this.viewproducts = viewproducts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getViewId() {
		return viewId;
	}

	public void setViewId(String viewId) {
		this.viewId = viewId;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	
}
