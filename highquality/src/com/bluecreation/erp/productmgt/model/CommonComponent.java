package com.bluecreation.erp.productmgt.model;

import com.nci.cp.core.model.AbstractEntity;

public  class CommonComponent extends AbstractEntity {

	protected String name;
	protected String specification;
	protected String material;
	protected String color;
	protected float price;
	protected String mark;	

	public CommonComponent() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}
    
	

}