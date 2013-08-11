/**
 * 
 */
package com.nci.cp.article.dao;

import com.nci.cp.article.model.Label;
import com.nci.cp.core.dao.AbstractDao;

/**
 * The object is to storage Label info.
 * @author  Shenq 
 * @version 0.1
 * @date    2013-07-16
 */
public class LabelDao extends AbstractDao implements ILabelDao {

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getDaoModel() {
		return Label.class;
	}

}
