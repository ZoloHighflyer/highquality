package com.nci.cp.core.sysmgt.action;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nci.cp.core.exception.ServiceException;
import com.nci.cp.core.sysmgt.model.Func;
import com.nci.cp.core.sysmgt.service.ISysManagement;
import com.nci.cp.core.sysmgt.vo.DepartmentVo;
import com.nci.cp.core.sysmgt.vo.FuncVo;
import com.nci.cp.core.utils.WebUtils;
import com.nci.cp.core.web.AbstractAction;
import com.nci.cp.core.web.IAction;
/**
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-8-20 
 */
public class DepManageAction extends AbstractAction {
	private static Log log = LogFactory.getLog(DepManageAction.class);
	private ISysManagement sysmgmtService;
	private DepartmentVo   depVo;
	public void setSysmgmtService(ISysManagement sysmgmtService) {
		this.sysmgmtService = sysmgmtService;
	}
	public void setDepVo(DepartmentVo depVo) {
		this.depVo = depVo;
	}
	
	public DepartmentVo getDepVo() {
		return depVo;
	}
	public String depsMain() throws ServiceException {
		List list = null;
		//list = funcService.findFuncTreeLevel(IFuncService.FUNC_SERVICE_SECOND);
		if ((msg!=null)&&(msg.equals("success"))&&(depVo!=null)&&(depVo.getId()!=null)) {
			msg ="selected";
		   	depVo = sysmgmtService.findDepById(depVo);			
		}
		   list = sysmgmtService.findDeps();
		   WebUtils.setRequestAttribute("treeList", list);
		
		return SUCCESS;
	}
	public String showDep() throws ServiceException {
		if (depVo.getId() != null) {
			try {
				depVo = sysmgmtService.findDepById(depVo);
				WebUtils.setRequestAttribute(IAction.CURRENT_SELECTED_NODE,	depVo);
							
			} catch (ServiceException e) {
				log.error(this.getClass().getName()+" showDep() fail!"
						+ e.getMessage());
				e.printStackTrace();
				throw new ServiceException(
						this.getClass().getName()+" showDep() fail!");
			}
		} 
		return SUCCESS;
	}
	public String addDep() throws ServiceException {
		if (depVo.getId()!=null) {
			try {
	          depVo =  sysmgmtService.findDepById(depVo);
	          return SUCCESS;
	        }catch (ServiceException e) {	        	
				log.error(this.getClass().getName()+" addDep() fail!"
						+ e.getMessage());
				e.printStackTrace();
				throw new ServiceException(
						this.getClass().getName()+" addDep() fail!");
			}
		} else{
			throw new ServiceException(
					this.getClass().getName()+" addDep() depDep.id is null !");
		}
		
	}
	public String saveDep() throws ServiceException{
		depVo = sysmgmtService.createOrUpdateDepartment(depVo);	
		msg="selected";
		WebUtils.getServletRequest().setAttribute(IAction.ACTION_MESSAGE, "success");
		return SUCCESS;
	}
	public String initedit() throws Exception {
		if(depVo.getId() != null){
			depVo =  sysmgmtService.findDepById(depVo);
		}
		
		return super.initedit();
	}	
	public String delete() throws Exception {
		
			try {				
				if (sysmgmtService.deleteDep(depVo)) {
					return super.delete();
				}
			} catch (Exception e) {
				log.error(this.getClass().getName()+" showFunc() fail!"
						+ e.getMessage());
				e.printStackTrace();
				throw new ServiceException(
						this.getClass().getName()+" showFunc() fail!");
			}
		  return ERROR;
		
	}
}
