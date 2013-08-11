package com.nci.cp.core.sysmgt.service;

import java.util.List;

import com.nci.cp.core.exception.DaoException;
import com.nci.cp.core.exception.ServiceException;
import com.nci.cp.core.sysmgt.model.Func;
import com.nci.cp.core.sysmgt.model.Operation;

/**
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-9-29 
 */
public interface IOptService {
	public boolean deleteOperation(long optId) throws ServiceException ;
	
	public Operation createOperation(Operation opt) throws ServiceException;
	
	public Operation updateOperation(Operation opt) throws ServiceException;
	
	public List findOptsByFuncId(Long funcid) throws ServiceException;
	
	public boolean deleteOptOfFuncs(List<Func> funcs) throws ServiceException;
	
	public Operation findOperationById(long optId) throws ServiceException;
}
