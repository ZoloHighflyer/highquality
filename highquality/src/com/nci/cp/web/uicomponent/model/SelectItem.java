package com.nci.cp.web.uicomponent.model;

/**
 * @target  this object is to use store data for selectcomponent.
 * @company BlueCreation Studio
 * @author OliverChan
 * @version 0.1
 * @date 2011-5-10
 */
public class SelectItem {
	protected String key = "";
	protected String value = "";
	protected boolean selected = false;
    
	public SelectItem(String key, String value,boolean selected) {
		super();
		this.key = key;
		this.selected = selected;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
