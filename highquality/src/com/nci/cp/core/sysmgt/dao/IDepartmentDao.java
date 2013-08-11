package com.nci.cp.core.sysmgt.dao;

import java.util.List;

import com.nci.cp.core.dao.IDao;
import com.nci.cp.core.exception.DaoException;
import com.nci.cp.core.sysmgt.model.Department;

/**
 * @target  the interface is for department dao operations.
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-8-16 
 */
public interface IDepartmentDao extends IDao {
	
	public List findAllDepartments() throws DaoException;

	/**
	 * @return
	 * @throws DaoException
	 */
	public List findThreeLayersDepartments() throws DaoException;
	
	/**
	 * @return
	 * @throws DaoException
	 */
	public Department findRoot() throws DaoException;
	
	
	/**
	 * @param func
	 * @return List
	 * @throws DaoException
	 */
	public List<Department> findChildrenOfDepartment(Department dep) throws DaoException;	
	
	/**
	 * @param func
	 * @param funcs
	 * @throws DaoException
	 */
	public void findAllChildrenOfDepartment(Department dep,List deps) throws DaoException;
}
