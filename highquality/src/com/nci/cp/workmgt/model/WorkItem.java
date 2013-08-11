package com.nci.cp.workmgt.model;

import java.util.Date;

import org.apache.commons.digester.Digester;

import com.nci.cp.core.model.AbstractEntity;

public class WorkItem extends AbstractEntity {
	public String workTitle;
    public String workContent;
    public Date   startDate;
    public Date   endDate;
    public int    curState; //0为未开始 1工作中 2结束 3暂停 4延迟
    public Long   principal;
	public String getWorkContent() {
		return workContent;
	}
	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		Digester digester = new Digester();
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getCurState() {
		return curState;
	}
	public void setCurState(int curState) {
		this.curState = curState;
	}
	public String getWorkTitle() {
		return workTitle;
	}
	public void setWorkTitle(String workTitle) {
		this.workTitle = workTitle;
	}
	public Long getPrincipal() {
		return principal;
	}
	public void setPrincipal(Long principal) {
		this.principal = principal;
	}
    
    
}
