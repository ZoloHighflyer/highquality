package com.nci.cp.teaminfo.info.dao;

import java.util.List;

import com.nci.cp.core.dao.IDao;
import com.nci.cp.core.exception.DaoException;
import com.nci.cp.core.model.IdEntity;
/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-2-1 
 */
public interface ITopicInfoDao extends IDao {	
	public List<? extends IdEntity> findValidTopics() throws DaoException;

}
