package com.nci.cp.teaminfo.info.model;


import java.lang.String;

import com.nci.cp.core.model.AbstractEntity;

/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2010-12-26 
 */
public class InfoRecord  extends AbstractEntity{
	   
	protected String title;

	protected String content;

	protected Long owner;
	
		
	protected TopicInfo topicinfo=new TopicInfo();
	

	
	
	
	public Long getOwner() {
		return owner;
	}

	public void setOwner(Long owner) {
		this.owner = owner;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public TopicInfo getTopicinfo() {
		return topicinfo;
	}

	public void setTopicinfo(TopicInfo topicinfo) {
		this.topicinfo = topicinfo;
	}
	
	

}
