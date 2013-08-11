package com.nci.cp.article.dao;

import com.nci.cp.article.model.Article;
import com.nci.cp.core.dao.AbstractDao;

/**
 * The object is to storage Article info.
 * @author  Shenq 
 * @version 0.1
 * @date    2013-07-16
 */
public class ArticleDao extends AbstractDao implements IArticleDao{

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getDaoModel() {
		return Article.class;
	}

}
