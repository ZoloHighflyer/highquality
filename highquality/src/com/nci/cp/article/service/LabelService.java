package com.nci.cp.article.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nci.cp.article.dao.ILabelDao;

public class LabelService {

	private static Log log = LogFactory.getLog(ArticleService.class);

	private ILabelDao labelDao;
	
	public ILabelDao getLabelDao() {
		return labelDao;
	}

	public void setLabelDao(ILabelDao labelDao) {
		this.labelDao = labelDao;
	}
	
}
