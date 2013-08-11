package com.nci.cp.teaminfo.info.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nci.cp.core.dao.AbstractDao;
import com.nci.cp.core.exception.DaoException;
import com.nci.cp.teaminfo.info.model.TopicInfo;
/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-2-1 
 */
public class TopicInfoDao extends AbstractDao implements ITopicInfoDao {
	private static Log log = LogFactory.getLog(TopicInfoDao.class);
	@Override
	protected Class getDaoModel() {
		// TODO Auto-generated method stub
		return TopicInfo.class;
	}
	public List<TopicInfo> findValidTopics() throws DaoException {			
		return super.findEntityByNamedQuery("findValidTopics");
	}

}
