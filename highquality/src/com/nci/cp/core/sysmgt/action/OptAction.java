package com.nci.cp.core.sysmgt.action;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nci.cp.core.exception.ServiceException;
import com.nci.cp.core.sysmgt.model.Func;
import com.nci.cp.core.sysmgt.model.Operation;
import com.nci.cp.core.sysmgt.service.ISysManagement;
import com.nci.cp.core.sysmgt.vo.FuncVo;
import com.nci.cp.core.sysmgt.vo.OperationVo;
import com.nci.cp.core.web.AbstractAction;
import com.nci.cp.core.web.IAction;
/**
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-8-19 
 */
public class OptAction extends AbstractAction {
	private static Log log = LogFactory.getLog(OptAction.class);
	private ISysManagement sysmgmtService;
	
	private OperationVo optVo;

	public void setSysmgmtService(ISysManagement sysmgmtService) {
		this.sysmgmtService = sysmgmtService;
	}
	
	
	public OperationVo getOptVo() {
		return optVo;
	}


	public void setOptVo(OperationVo optVo) {
		this.optVo = optVo;
	}


	public String addOpt() throws ServiceException {
		
		FuncVo funcVo = sysmgmtService.findFuncById(optVo.getFuncId().longValue());
		optVo.setFuncId(funcVo.getId());
		optVo.setFuncname(funcVo.getFuncname());

		
		//WebUtils.setRequestAttribute("treeList", list);
		return SUCCESS;
	}
    
	
	
	@Override
	public String initedit() throws Exception {
		Operation op = new Operation();
		op.setId(optVo.getId());
		op=sysmgmtService.findOperationById(optVo.getId().longValue());
		
		FuncVo funcVo=sysmgmtService.findFuncById(op.getFuncId().longValue());
		try {
			BeanUtils.copyProperties(optVo, op);
			optVo.setFuncId(funcVo.getId());
			optVo.setFuncname(funcVo.getFuncname());
		} catch (IllegalAccessException e) {			
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			
			e.printStackTrace();
		}
		return super.initedit();
	}
    
	public String saveOpt() throws ServiceException {
		Operation op = new Operation();
		try {//this.getClass().getName()+
			BeanUtils.copyProperties(op, optVo);
			if ((optVo != null )&& (optVo.getAction()!=null)&&(optVo.getAction().equals(IAction.ACTION_EDIT))) {
			    op=sysmgmtService.updateOperation(op);
			}else {
				op=sysmgmtService.createOperation(op);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {			
			e.printStackTrace();
		}catch (ServiceException e){
			e.printStackTrace();
			log.error(this.getClass().getName()+" saveOpt() fail! "+e.getMessage());
			throw new ServiceException(this.getClass().getName()+" saveOpt() fail!");	
		}
		
		msg="success";
		
        
		return SUCCESS;
	}

	@Override
	public String delete() throws Exception {	
		
		if (optVo.getId() != null) {
			try {
				Operation op = new Operation();
				op.setId(optVo.getId());
				op = sysmgmtService.findOperationById(optVo.getId().longValue());
				optVo.setFuncId(op.getFuncId());
			    sysmgmtService.deleteOperation(op.getId().longValue());
			    
			    msg="success";
			    
			} catch (Exception e) {
				log.error(this.getClass().getName()+" delete() fail!"
						+ e.getMessage());
				e.printStackTrace();
				throw new ServiceException(
						this.getClass().getName()+" delete() fail!");
			}
		}
		return super.delete();

	}
	
}
