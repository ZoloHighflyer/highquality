package com.nci.cp.core.sysmgt.service;

import java.util.List;

import com.nci.cp.core.exception.ServiceException;
import com.nci.cp.core.service.IService;
import com.nci.cp.core.sysmgt.model.Func;
import com.nci.cp.core.sysmgt.vo.FuncVo;
import com.nci.cp.ds.tree.ITreeModel;

/**
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-9-17 
 */
public interface IFuncService extends IService {
	
	public Func findFuncById(Long funcId)throws ServiceException;
	
	public List<FuncVo> findAllFuncs() throws ServiceException;
	
	public Long createFunc(FuncVo funcVo) throws ServiceException;

	public Long updateFunc(FuncVo funcVo) throws ServiceException;

	public boolean deleteFunc(Long funcId) throws ServiceException;
	
	public boolean deleteFuncs(List funcs) throws ServiceException;
	
	public ITreeModel  findFuncTree() throws ServiceException;
	
	public void findAllChildrenOfFunc(Func func,List funcs) throws ServiceException;
	
	
	
}
