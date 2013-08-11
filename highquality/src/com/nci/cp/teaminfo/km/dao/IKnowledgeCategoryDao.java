package com.nci.cp.teaminfo.km.dao;

import java.util.List;

import com.nci.cp.core.dao.IDao;
import com.nci.cp.core.exception.DaoException;
import com.nci.cp.teaminfo.km.model.KnowledgeCategory;

public interface IKnowledgeCategoryDao extends IDao{
	    
	public List findAllCategories(String owner) throws DaoException;
	
	public long updateKnowledgeCategoryInfo(KnowledgeCategory kc) throws DaoException;
	
	public boolean isExistSubCategory(long kcid) throws DaoException ;
}
