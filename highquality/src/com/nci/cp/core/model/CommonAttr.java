package com.nci.cp.core.model;

abstract public class CommonAttr extends AbstractEntity {
	private Long   entityExtId=null;
	private String name;
	
	

	public Long getEntityExtId() {
		return entityExtId;
	}

	public void setEntityExtId(Long entityExtId) {
		this.entityExtId = entityExtId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
