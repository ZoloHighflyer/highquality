package com.nci.cp.core.service;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nci.cp.core.dao.IDao;
import com.nci.cp.core.exception.DaoException;
import com.nci.cp.core.exception.ServiceException;
import com.nci.cp.core.model.IdEntity;

/**
 * Common Platform Team
 * @author Oliver Chan
 * @since  0.1
 */
public abstract class AbstractService implements IBaseService {
	private static Log log = LogFactory.getLog(AbstractService.class);
       
	public abstract IDao getBaseDao();

	public IdEntity create(IdEntity entity) throws ServiceException{
		try {
			entity=getBaseDao().createEntity(entity);
			return entity;
		}catch (DaoException de){
			de.printStackTrace();
			log.error("AbstractService.class create fail,when create "+entity.toString());
			throw new ServiceException("AbstractService.class can not create entity!");				
		}		
	}

	public boolean delete(IdEntity entity) throws ServiceException{
		try {
			return getBaseDao().deleteEntity(entity);
		}catch (DaoException de){
			de.printStackTrace();
			log.error("AbstractService.class delete fail,when delete "+entity.toString());
			throw new ServiceException("AbstractService.class can not delete entity!");				
		}
		
	}

	public IdEntity find(IdEntity entity) throws ServiceException{
		
		try {
			return getBaseDao().findEntityById(entity);
		} catch (DaoException de) {
			de.printStackTrace();
			log.error("AbstractService.class find fail,when find "+entity.toString());
			throw new ServiceException("AbstractService.class can not find entity!");		
		}
		
	}

	public IdEntity update(IdEntity entity) throws ServiceException{
		try {
			entity=getBaseDao().updateEntity(entity);
			return entity;
		} catch (DaoException de) {
			de.printStackTrace();
			log.error("AbstractService.class update fail,when update "+entity.toString());
			throw new ServiceException("AbstractService.class can not update entity!");	
		}		
	}

	public IdEntity updateOrSave(IdEntity entity) throws ServiceException {
		return update(entity);
	}
    
}
