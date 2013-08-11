package com.sunbow.erp.productlib.model;

import java.util.Set;
import java.util.TreeSet;

import com.nci.cp.core.model.AbstractEntity;

public class ProductClassify extends AbstractEntity {
	protected Set products=new TreeSet() ;
    //所有者
	protected String owner;
	//分类编号
	protected String categoryNo;
	//分类名称
	protected String name; 
    //分类的父编号
	protected long   parCategoryNo;


	public Set getProducts() {
		return products;
	}

	public void setProducts(Set products) {
		this.products = products;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
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

	public String getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}
	
	
}
