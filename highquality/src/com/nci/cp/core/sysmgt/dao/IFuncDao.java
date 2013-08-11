package com.nci.cp.core.sysmgt.dao;

import java.util.List;

import com.nci.cp.core.dao.IDao;
import com.nci.cp.core.exception.DaoException;
import com.nci.cp.core.sysmgt.model.Func;
/**
 * @target  the interface is for func dao operations.
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-8-16 
 */
public interface IFuncDao extends IDao {
	public List<Func> findAllFuncs() throws DaoException;

	/**
	 * @return
	 * @throws DaoException
	 */
	public List findThreeLayersFuncs() throws DaoException;
	
	/**
	 * @return
	 * @throws DaoException
	 */
	public Func findRoot() throws DaoException;
	
	
	/**
	 * @param func
	 * @return List
	 * @throws DaoException
	 */
	public List<Func> findChildrenOfFunc(Func func) throws DaoException;	
	
	/**
	 * @param func
	 * @param funcs
	 * @throws DaoException
	 */
	public void findAllChildrenOfFunc(Func func,List funcs) throws DaoException;
}
