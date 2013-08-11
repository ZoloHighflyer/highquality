package com.nci.cp.core.sysmgt.model;

import com.nci.cp.core.model.AbstractEntity;

/**
 * @target
 * @company BlueCreation Studio
 * @author OliverChan
 * @version 0.1
 * @date 2011-5-18
 */
public class MenuItem extends AbstractEntity {
	protected String name;
	protected String jsmethod;
	protected Menu menu;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJsmethod() {
		return jsmethod;
	}

	public void setJsmethod(String jsmethod) {
		this.jsmethod = jsmethod;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

}
