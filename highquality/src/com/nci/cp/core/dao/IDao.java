package com.nci.cp.core.dao;

import java.util.List;

import com.nci.cp.core.exception.DaoException;
import com.nci.cp.core.model.IdEntity;
import com.nci.cp.ds.paging.Pagination;

/**
 * Common Platform Team
 * 
 * @author Oliver Chan
 * @since 0.1
 */
public interface IDao {
	/**
	 * The method is to create new entity.
	 * 
	 * @param entity
	 * @return
	 * @throws DaoException
	 */
	public IdEntity createEntity(IdEntity entity) throws DaoException;

	/**
	 * The method is to delete existing entity.
	 * 
	 * @param entity
	 * @return
	 * @throws DaoException
	 */
	public boolean deleteEntity(IdEntity entity) throws DaoException;

	/**
	 * The method is to update entity.
	 * 
	 * @param entity
	 * @return
	 * @throws DaoException
	 */
	public IdEntity updateEntity(IdEntity entity) throws DaoException;
    
	/**
	 * the method update or save entity
	 * 
	 * @param entity
	 * @return
	 * @throws DaoException
	 */
	public IdEntity updateOrSaveEntity(IdEntity entity) throws DaoException;
	
	/**
	 * The method is to find entity by id.
	 * 
	 * @param entity
	 * @return
	 * @throws DaoException
	 */
	public IdEntity findEntityById(IdEntity entity) throws DaoException;

	/**
	 * The method is to return all model object.
	 * 
	 * @return List
	 * @throws DaoException
	 */

	public List findAllEntities() throws DaoException;

	/**
	 * @param namequery
	 * @return
	 * @throws DaoException
	 */
	public List findEntityByNamedQuery(String namequery)
			throws DaoException;
	
	
	/**
	 * @param query
	 * @return
	 * @throws DaoException
	 */
	public List<IdEntity> find(String query) throws DaoException;
	
	/**
	 * @param entities
	 * @return boolean
	 * @throws DaoException
	 */
	public boolean deleteEntities(List<IdEntity> entities) throws DaoException;
	
	public Pagination findEntitiesByPage(final Pagination page);
	
	public Pagination findEntitiesByPage(final String hsql,final Pagination page);

}
