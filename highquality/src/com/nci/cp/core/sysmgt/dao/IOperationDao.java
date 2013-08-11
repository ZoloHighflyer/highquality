package com.nci.cp.core.sysmgt.dao;

import java.util.List;

import com.nci.cp.core.dao.IDao;
import com.nci.cp.core.exception.DaoException;
import com.nci.cp.core.exception.ServiceException;
import com.nci.cp.core.sysmgt.model.Func;
import com.nci.cp.core.sysmgt.model.Operation;
/**
 * @target  the interface is for operation .
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-9-29 
 */
public interface IOperationDao extends IDao {
	
	
	public List findOptsByFuncId(Long funcid) throws DaoException;
	
	public boolean deleteOptOfFuncs(List<Func> funcs) throws DaoException;
}
