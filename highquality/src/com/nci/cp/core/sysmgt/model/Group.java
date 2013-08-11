package com.nci.cp.core.sysmgt.model;

import com.nci.cp.core.model.AbstractEntity;
/**
 * The interface for group.
 * @company BlueCreation Workspace
 * @author  yanfeng 
 * @version 0.1
 * @date    2011-08-04 
 */
public class Group extends AbstractEntity {
	private String name;
	private String descr;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}

    
   
}
