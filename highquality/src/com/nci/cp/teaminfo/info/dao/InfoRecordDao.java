package com.nci.cp.teaminfo.info.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nci.cp.core.dao.AbstractDao;
import com.nci.cp.teaminfo.info.model.InfoRecord;
/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-2-1 
 */
public class InfoRecordDao extends AbstractDao implements IInfoRecordDao {
	private static Log log = LogFactory.getLog(InfoRecordDao.class);
	@Override
	protected Class getDaoModel() {
		// TODO Auto-generated method stub
		return InfoRecord.class;
	}
    
	
}
