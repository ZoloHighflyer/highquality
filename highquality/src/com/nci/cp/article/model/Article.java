/**
 * 
 */
package com.nci.cp.article.model;

import java.util.Date;

import com.nci.cp.core.model.AbstractEntity;

/**
 * @author Shenq
 * 
 * @version 1.0
 *
 */
@SuppressWarnings("serial")
public class Article  extends AbstractEntity{
	
	
	
	private String title;
	
	private String content;
	
	private boolean is_deleted;
	
	private Date deleteDate;

	private String brief;
	
	private String titlePic;

	
	/*
	 * Getters and Setters
	 */

	
	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
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

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}
	
	public String getTitlePic() {
		return titlePic;
	}

	public void setTitlePic(String titlePic) {
		this.titlePic = titlePic;
	}
}
