package com.nci.cp.core.service;

import com.nci.cp.core.exception.ServiceException;
import com.nci.cp.core.model.IdEntity;

/**
 * Common Platform Team
 * 
 * @author Oliver Chan
 * @since 0.1
 */
public interface IBaseService extends IService {
	/**
	 * @param entity
	 * @return
	 * @throws ServiceException
	 */
	public IdEntity create(IdEntity entity) throws ServiceException;

	/**
	 * @param entity
	 * @return
	 * @throws ServiceException
	 */
	public IdEntity update(IdEntity entity) throws ServiceException;
    
	/**
	 * @param entity
	 * @return
	 * @throws ServiceException
	 */
	public IdEntity updateOrSave(IdEntity entity) throws ServiceException;
	
	/**
	 * @param entity
	 * @return
	 * @throws ServiceException
	 */
	public boolean delete(IdEntity entity) throws ServiceException;

	/**
	 * @param entity
	 * @return
	 * @throws ServiceException
	 */
	public IdEntity find(IdEntity entity) throws ServiceException;
}
