package com.nci.cp.teaminfo.km.model;


import com.nci.cp.core.model.AbstractEntity;

/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2010-12-26 
 */
public class KnowledgeRecord  extends AbstractEntity{

	protected String title;
	
	protected String content;

	protected String owner;	
		
	protected KnowledgeCategory infoCategory;	
	
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public KnowledgeCategory getInfoCategory() {
		return infoCategory;
	}

	public void setInfoCategory(KnowledgeCategory infoCategory) {
		this.infoCategory = infoCategory;
	}	

}
