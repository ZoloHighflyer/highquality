package com.nci.cp.core.sysmgt.model;

import java.util.HashSet;
import java.util.Set;

import com.nci.cp.core.model.AbstractEntity;

/**
 * the class is to store dic type
 * @company BlueCreation Workspace
 * @author OliverChan
 * @version 0.1
 * @date 2011-11-03
 */
public class DicType extends AbstractEntity {
	protected String identifyCode;
	protected String name;
	protected Set    entries =new HashSet();
	public String getIdentifyCode() {
		return identifyCode;
	}

	public void setIdentifyCode(String identifyCode) {
		this.identifyCode = identifyCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getEntries() {
		return entries;
	}

	public void setEntries(Set entries) {
		this.entries = entries;
	}
    
}
