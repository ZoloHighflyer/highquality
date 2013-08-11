package com.nci.cp.core.sysmgt.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nci.cp.core.exception.DaoException;
import com.nci.cp.core.exception.ServiceException;
import com.nci.cp.core.sysmgt.model.Func;
import com.nci.cp.core.sysmgt.service.IFuncService;
import com.nci.cp.core.sysmgt.service.ISysManagement;
import com.nci.cp.core.sysmgt.vo.FuncVo;
import com.nci.cp.core.utils.WebUtils;
import com.nci.cp.core.web.AbstractAction;
import com.nci.cp.core.web.IAction;
/**
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-8-19 
 */
public class FuncManageAction extends AbstractAction {
	private static Log log = LogFactory.getLog(FuncManageAction.class);
	private ISysManagement sysmgmtService;
	
	private FuncVo funcVo;
	

	

	public FuncVo getFuncVo() {
		return funcVo;
	}

	public void setFuncVo(FuncVo funcVo) {
		this.funcVo = funcVo;
	}
			
	
	public void setSysmgmtService(ISysManagement sysmgmtService) {
		this.sysmgmtService = sysmgmtService;
	}

	public String funcMain() throws ServiceException {
		List list = null;
		//list = funcService.findFuncTreeLevel(IFuncService.FUNC_SERVICE_SECOND);
		if ((msg!=null)&&(msg.equals("success"))&&(funcVo!=null)) {
			msg ="selected";
		   	funcVo = sysmgmtService.findFuncById(funcVo.getId());			
		}
		   list = sysmgmtService.findAllFuncs();
		   WebUtils.setRequestAttribute("treeList", list);
		
		return SUCCESS;
	}

	@Override
	public String delete() throws Exception {
		if (funcVo.getId() != null) {
			try {
				sysmgmtService.deleteFuncAndChildren(funcVo.getId().longValue())	;
			} catch (Exception e) {
				log.error(this.getClass().getName()+" showFunc() fail!"
						+ e.getMessage());
				e.printStackTrace();
				throw new ServiceException(
						this.getClass().getName()+" showFunc() fail!");
			}
		}
		return super.delete();
	}

	public String showFunc() throws ServiceException {
		if (funcVo.getId() != null) {
			try {
				funcVo = sysmgmtService.findFuncById(funcVo.getId());	
				WebUtils.setSessionAttribute(IAction.CURRENT_SELECTED_NODE,	funcVo);
							
			} catch (ServiceException e) {
				log.error(this.getClass().getName()+" showFunc() fail!"
						+ e.getMessage());
				e.printStackTrace();
				throw new ServiceException(
						this.getClass().getName()+" showFunc() fail!");
			}
		}else {
			log	.error(this.getClass().getName()+" showFunc() can not get func entity!");
		}
		if (funcVo!=null) {
			
			try {
				List opts = sysmgmtService.findOptsByFuncId(funcVo);
				funcVo.setOpts(opts);
			
			}catch (ServiceException  e) {
				e.printStackTrace();
				log.error(this.getClass().getName()+" showFunc() fail! "+e.getMessage());
				throw new ServiceException(this.getClass().getName()+" showFunc() fail!");	
			}catch (Exception  e) {
				e.printStackTrace();
				log.error(this.getClass().getName()+" showFunc() fail! "+e.getMessage());
				throw new ServiceException(this.getClass().getName()+" showFunc() fail!");	
			}
			
		}
		return SUCCESS;

	}
	public String addFunc() throws ServiceException {
        if (funcVo.getId()!=null) {
          funcVo =  sysmgmtService.findFuncById(funcVo.getId());
        }
      
		return SUCCESS;
	}
  

	public String saveFunc() throws ServiceException {
		Long funcVoId = null;
		if ((funcVo != null )&& (funcVo.getAction()!=null)&&(funcVo.getAction().equals(IAction.ACTION_EDIT))) {
			funcVoId=sysmgmtService.updateFunc(funcVo);			
		} else {
			funcVoId=sysmgmtService.createFunc(funcVo);			
		}		
		
		funcVo =sysmgmtService.findFuncById(funcVoId);
		msg="selected";
		WebUtils.getServletRequest().setAttribute(IAction.ACTION_MESSAGE, "success");		
		
		return SUCCESS;
	}

	@Override
	public String initedit() throws Exception {
		if(funcVo.getId() != null){
			funcVo=sysmgmtService.findFuncById(funcVo.getId());			
		}
		
		
		return super.initedit();
	}	
	
}
