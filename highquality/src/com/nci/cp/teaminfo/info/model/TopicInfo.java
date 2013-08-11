package com.nci.cp.teaminfo.info.model;

import java.util.Set;
import java.util.TreeSet;

import com.nci.cp.core.model.AbstractEntity;

public class TopicInfo extends AbstractEntity{

	/**
	 * @uml.annotations for <code>infoRecords</code>
	 *                  collection_type="com.nci.cp.teaminfo.model.InfoRecord"
	 */
	protected Set infoRecords=new TreeSet() ;

	protected Integer state;

	protected Long owner;
	
	protected String title;
	
	protected String curTitle;

	protected String curContent;

	
	public Integer getState() {
		return state;
	}

	public Long getOwner() {
		return owner;
	}

	public String getTitle() {
		return title;
	}

	public String getCurTitle() {
		return curTitle;
	}

	public String getCurContent() {
		return curContent;
	}

	
	public void setState(Integer state) {
		this.state = state;
	}

	public void setOwner(Long owner) {
		this.owner = owner;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setCurTitle(String curTitle) {
		this.curTitle = curTitle;
	}

	public void setCurContent(String curContent) {
		this.curContent = curContent;
	}

	public Set getInfoRecords() {
		return infoRecords;
	}

	public void setInfoRecords(Set infoRecords) {
		this.infoRecords = infoRecords;
	}

	
    
	
	
}
