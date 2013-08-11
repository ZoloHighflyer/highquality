package com.nci.cp.teaminfo.km.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.nci.cp.core.model.BaseVo;
import com.nci.cp.core.utils.CommonUtils;

public class KnowledgeRecordVo extends BaseVo {
	protected  long   id;
	protected String title;	
	protected String content;
	protected Long   kcid;
	protected String kcpath;
	protected Date   createDate;
	protected String owner;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreateDateString() {	
		if (createDate==null) return "";
		return CommonUtils.formatDate(createDate,new SimpleDateFormat("yyyy-MM-dd"));
	}
	
	
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public Long getKcid() {
		return kcid;
	}
	public void setKcid(Long kcid) {
		this.kcid = kcid;
	}
	public String getKcpath() {
		return kcpath;
	}
	public void setKcpath(String kcpath) {
		this.kcpath = kcpath;
	}
	
}
