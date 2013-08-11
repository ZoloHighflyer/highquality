package com.nci.cp.core.sysmgt.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nci.cp.core.exception.DaoException;
import com.nci.cp.core.exception.ServiceException;
import com.nci.cp.core.sysmgt.dao.IOperationDao;
import com.nci.cp.core.sysmgt.model.Func;
import com.nci.cp.core.sysmgt.model.Operation;
/**
 * @target  the interface is for operation .
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-9-29 
 */
public class OptService implements IOptService {
	private static Log log = LogFactory.getLog(OptService.class);
    private IOperationDao optDao;
    
	public void setOptDao(IOperationDao optDao) {
		this.optDao = optDao;
	}
	public boolean deleteOperation(long optId) throws ServiceException {
		try {
			Operation opt = findOperationById(new Long(optId));
			return optDao.deleteEntity(opt);
		} catch (DaoException e) {
			 e.printStackTrace();
			 log.error(this.getClass().getName()+" deleteOperation(Operation opt)fail! "+e.getMessage());
			 throw new ServiceException(this.getClass().getName()+" deleteOperation(Operation opt) fail!");	
		}
	
	}
	public Operation createOperation(Operation opt) throws ServiceException {

		try {
			return (Operation)optDao.createEntity(opt);
		} catch (DaoException e) {
			 e.printStackTrace();
			 log.error(this.getClass().getName()+" createOperation(Operation opt) fail! "+e.getMessage());
			 throw new ServiceException(this.getClass().getName()+" createOperation(Operation opt) fail!");	
		}

	}
	public boolean deleteOptOfFuncs(List<Func> funcs) throws ServiceException {
		
		try {
			return optDao.deleteOptOfFuncs(funcs);
		} catch (DaoException e) {		
			e.printStackTrace();
			log.error(this.getClass().getName()+" deleteOptOfFuncs(List<Func> funcs) fail! "+e.getMessage());
		    throw new ServiceException(this.getClass().getName()+" deleteOptOfFuncs(List<Func> funcs) fail!");
		}
	}
	public Operation updateOperation(Operation opt) throws ServiceException {
		try {
			Operation option = (Operation)optDao.updateEntity(opt);
			return option;
		} catch (DaoException e) {			
			 e.printStackTrace();
			 log.error(this.getClass().getName()+" updateOperation(Operation opt)fail! "+e.getMessage());
			 throw new ServiceException(this.getClass().getName()+" updateOperation(Operation opt) fail!");	
		}
	}
	public List findOptsByFuncId(Long funcid) throws ServiceException {
		
		try {
			return optDao.findOptsByFuncId(funcid);
		} catch (DaoException e) {
			e.printStackTrace();
			log.error(this.getClass().getName()+" findOptsByFuncId(Long funcid) fail! "+e.getMessage());
		    throw new ServiceException(this.getClass().getName()+" findOptsByFuncId(Long funcid) fail!");
		}
	}

	public Operation findOperationById(long optId) throws ServiceException {
		Operation opt = new Operation();
		opt.setId(new Long(optId));
		try {
			return (Operation)optDao.findEntityById(opt);
		} catch (DaoException e) {
			e.printStackTrace();
			log.error(this.getClass().getName()+" findOperationById(long optId) fail! "+e.getMessage());
		    throw new ServiceException(this.getClass().getName()+" findOperationById(long optId) fail!");
		}
	}
    
}
