package com.nci.cp.core.sysmgt.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nci.cp.core.exception.DaoException;
import com.nci.cp.core.exception.ServiceException;
import com.nci.cp.core.sysmgt.dao.IFuncDao;
import com.nci.cp.core.sysmgt.model.Func;
import com.nci.cp.core.sysmgt.vo.FuncVo;
import com.nci.cp.core.utils.CommonUtils;
import com.nci.cp.ds.tree.ITreeModel;
import com.nci.cp.ds.tree.TreeNode;
/**
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-9-17 
 */
public class FuncService implements IFuncService {
	private static Log log = LogFactory.getLog(FuncService.class);
	private IFuncDao          funcDao;
	
	public void setFuncDao(IFuncDao funcDao) {
		this.funcDao = funcDao;
	}
   
	public Long createFunc(FuncVo funcVo) throws ServiceException {
		Func f = FuncServiceUtils.voToBo(funcVo);
		try {
			f = (Func)funcDao.createEntity(f);
		} catch (DaoException e) {
			e.printStackTrace();
			log.error(this.getClass().getName()+" createFunc(FuncVo funcVo) fail! "+e.getMessage());
			throw new ServiceException(this.getClass().getName()+" createFunc(FuncVo funcVo) fail!");
		}
		
		return f.getId();
	}

	public boolean deleteFunc(Long funcId) throws ServiceException {
		List fchildren =null;
		/*try {
			 fchildren = findAllChildrenOfFunc(func);
		} catch (ServiceException e) {
		    e.printStackTrace();
		    log.error(this.getClass().getName()+" deleteFuncAndChildren fail! "+e.getMessage());
		    return false;
		}		
		if ((fchildren!=null)&&(fchildren.size()>0)){
			try {
				return funcDao.deleteEntities(fchildren);
			}catch(DaoException ex) {
				ex.printStackTrace();
			    log.error(this.getClass().getName()+" deleteFuncAndChildren fail! "+ex.getMessage());
			}
			
		}*/
	    return false;
	}
    
	public boolean deleteFuncs(List funcs) throws ServiceException {
		try {
			return funcDao.deleteEntities(funcs);
		} catch (DaoException e) {
			e.printStackTrace();
			log.error(this.getClass().getName()+" deleteFuncs(List funcs) fail! "+e.getMessage());
			throw new ServiceException(this.getClass().getName()+" deleteFuncs(List funcs) fail!");
		}
	
	}

	public List<FuncVo> findAllFuncs() throws ServiceException {
		List<FuncVo> funcVos = new ArrayList<FuncVo>();
		try {
			List<?> funcs = funcDao.findAllEntities();
			for(int i=0;i<funcs.size();i++) {
				FuncVo funcVo = new FuncVo();
				Func f = (Func)funcs.get(i);
				funcVo=FuncServiceUtils.boToVo(f);
				funcVos.add(funcVo);
			}
		} catch (DaoException e) {			
			e.printStackTrace();	
			log.error(this.getClass().getName()+" findAllFuncs() fail! "+e.getMessage());
			throw new ServiceException(this.getClass().getName()+" findAllFuncs() fail!");
		}catch (ServiceException e) {
			e.printStackTrace();	
			log.error(this.getClass().getName()+" findAllFuncs() fail! "+e.getMessage());
			throw new ServiceException(this.getClass().getName()+" findAllFuncs() fail!");
		}
		return funcVos;
	}

	public Func findFuncById(Long funcId) throws ServiceException {
		Func f = new Func();
		f.setId(funcId);
		try {
			f=(Func)funcDao.findEntityById(f);
			return f;
		} catch (DaoException e) {
			e.printStackTrace();
			log.error(this.getClass().getName()+" findFuncById(Long funcId) fail! "+e.getMessage());
			throw new ServiceException(this.getClass().getName()+" findFuncById(Long funcId) fail!");
		}
	
	}

	public Long updateFunc(FuncVo funcVo) throws ServiceException {
		Func f = FuncServiceUtils.voToBo(funcVo);
		try {
			f=(Func)funcDao.updateEntity(f);
			return f.getId();
		} catch (DaoException e) {
			e.printStackTrace();
			log.error(this.getClass().getName()+" updateFunc(FuncVo funcVo) fail! "+e.getMessage());
			throw new ServiceException(this.getClass().getName()+" updateFunc(FuncVo funcVo) fail!");
		}
		
	}
	
	
	
	public void findAllChildrenOfFunc(Func func, List funcs)
			throws ServiceException {
		try {
			funcDao.findAllChildrenOfFunc(func, funcs);
		} catch (DaoException e) {
			e.printStackTrace();
			log.error(this.getClass().getName()+" findAllChildrenOfFunc(Func func, List funcs) fail! "+e.getMessage());
			throw new ServiceException(this.getClass().getName()+" findAllChildrenOfFunc(Func func, List funcs) fail!");
		}
		
	}

	public ITreeModel findFuncTree() throws ServiceException {
		List<?> funcVos = findAllFuncs();
		if (funcVos.size()>0) {			
			List<TreeNode> nodes = funcVoToNodes(funcVos);	
			try {
				return CommonUtils.listToTree(nodes);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
   
	private List<TreeNode> funcVoToNodes(List funcVos) {
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		for(int i=0;i<funcVos.size();i++) {
			TreeNode node = new TreeNode();
			FuncVo funcVo = (FuncVo)funcVos.get(i);
			node.setNodeid(funcVo.getId().intValue());
			node.setParentid(funcVo.getParfuncno());
			node.setNodename(funcVo.getFuncname());
			node.setData(funcVo.getFuncaction());
			nodes.add(node);
		}
		return nodes;
	}
}
