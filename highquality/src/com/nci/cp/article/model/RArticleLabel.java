package com.nci.cp.article.model;

import com.nci.cp.core.model.AbstractEntity;
/**
 * @author Shenq
 * 
 * @version 1.0
 *
 */

@SuppressWarnings("serial")
public class RArticleLabel extends AbstractEntity{

	private String articleId;
	
	private String labelId;

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getLabelId() {
		return labelId;
	}

	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}
	
}
