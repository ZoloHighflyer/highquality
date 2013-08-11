package com.nci.cp.article.model;

import com.nci.cp.core.model.AbstractEntity;

@SuppressWarnings("serial")
public class Label extends AbstractEntity{

	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
