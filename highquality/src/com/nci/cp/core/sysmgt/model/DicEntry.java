package com.nci.cp.core.sysmgt.model;

import com.nci.cp.core.model.AbstractEntity;
/**
 * the class is to store dic node
 * @company BlueCreation Workspace
 * @author OliverChan
 * @version 0.1
 * @date 2011-11-03
 */
public class DicEntry extends AbstractEntity {
    protected String name;
    protected int identify;
    protected DicType dicType;
	
	public DicType getDicType() {
		return dicType;
	}
	public void setDicType(DicType dicType) {
		this.dicType = dicType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIdentify() {
		return identify;
	}
	public void setIdentify(int identify) {
		this.identify = identify;
	}
	
    
}
