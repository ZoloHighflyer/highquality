package com.nci.cp.core.sysmgt.model;

import java.util.ArrayList;
import java.util.List;

import com.nci.cp.core.model.AbstractEntity;

/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-5-18 
 */
public class Menu extends AbstractEntity {
	protected String viewid;
	protected String name;
	protected List   menuitems= new ArrayList();
	public String getViewid() {
		return viewid;
	}

	public void setViewid(String viewid) {
		this.viewid = viewid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List getMenuitems() {
		return menuitems;
	}

	public void setMenuitems(List menuitems) {
		this.menuitems = menuitems;
	}
    
}
