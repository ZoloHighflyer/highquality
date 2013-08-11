package com.nci.cp.teaminfo.info.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * The object is the view object of TopicInfo object.
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2010-12-25 
 */
public class TopicInfoVo {
    protected String id = null; 	
	protected String createTime;	
	protected String modifyTime;
	protected String state;
	protected String owner;	
	protected String title;
	protected String curTitle;
	protected String curContent;
	protected List<InfoRecordVo>   inforecords=new  ArrayList(8);
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCurTitle() {
		return curTitle;
	}
	public void setCurTitle(String curTitle) {
		this.curTitle = curTitle;
	}
	public String getCurContent() {
		return curContent;
	}
	public void setCurContent(String curContent) {
		this.curContent = curContent;
	}
	public List getInforecords() {
		return inforecords;
	}
	public void setInforecords(List inforecords) {
		this.inforecords = inforecords;
	}
	
}
