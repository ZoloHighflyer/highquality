package com.nci.cp.core.sysmgt.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nci.cp.core.exception.DaoException;
import com.nci.cp.core.exception.ServiceException;
import com.nci.cp.core.model.BaseVo;
import com.nci.cp.core.sysmgt.dao.IFuncDao;
import com.nci.cp.core.sysmgt.dao.IOperationDao;
import com.nci.cp.core.sysmgt.dao.IRoleDao;
import com.nci.cp.core.sysmgt.dao.IRoleNodeDao;
import com.nci.cp.core.sysmgt.model.Func;
import com.nci.cp.core.sysmgt.model.Operation;
import com.nci.cp.core.sysmgt.model.Role;
import com.nci.cp.core.sysmgt.model.RoleNode;
import com.nci.cp.core.sysmgt.vo.RoleVo;
import com.nci.cp.core.utils.CommonUtils;
import com.nci.cp.ds.paging.Pagination;
/**
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-8-29 
 */
public class CopyOfRoleService implements IRoleService {
	private static Log log = LogFactory.getLog(CopyOfRoleService.class);
    private IRoleDao roleDao;
    private IRoleNodeDao roleNodeDao;
    private IFuncDao          funcDao;
    private IOperationDao     optDao;
	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}
    
	public void setRoleNodeDao(IRoleNodeDao roleNodeDao) {
		this.roleNodeDao = roleNodeDao;
	}
    
	public void setFuncDao(IFuncDao funcDao) {
		this.funcDao = funcDao;
	}
    
	public void setOptDao(IOperationDao optDao) {
		this.optDao = optDao;
	}

	public IServiceResult useServiceByAction(BaseVo bsvo) throws ServiceException {
		
		if (IRoleService.ACTION_FIND_ALL_ROLES.equals(bsvo.getAction())) {
			return findAllRoles(bsvo);
		}else if (IRoleService.ACTION_CREATE_ROLE.equals(bsvo.getAction())) {
			return createRole(bsvo);
		}else if (IRoleService.ACTION_FIND_ROLE_BY_ID.equals(bsvo.getAction())) {
			return findRoleById(bsvo);
		}else if (IRoleService.ACTION_UPDATE_ROLE.equals(bsvo.getAction())) {
			return updateRole(bsvo);
		}else if (IRoleService.ACTION_DELETE_ROLE.equals(bsvo.getAction())) {
			return deleteRole(bsvo);
		}
		
		DefaultServiceResult defsrv = new DefaultServiceResult();
		defsrv.setStatus(IServiceResult.FAIL_STATUS);
		defsrv.setMessage("there is no the action method!");
		return defsrv ;
	}
    
	public IServiceResult findAllRoles(BaseVo bsvo)throws ServiceException{
		DefaultServiceResult defsrv = new DefaultServiceResult();
		try {
			List roles=roleDao.findAllEntities();
			List rolevos = new ArrayList();
			
			if ((roles!=null)&&(roles.size()>0)) {
				for(int i=0;i<roles.size();i++) {
					Role role = (Role)roles.get(i);
					RoleVo rolevo  = new RoleVo();
					rolevo.setId(role.getId());
					rolevo.setName(role.getName());
					rolevo.setDescr(role.getDescr());
					rolevos.add(rolevo);
				}
				
			}
			defsrv.setData(rolevos);
			defsrv.setMessage(IServiceResult.SUCCESS_MESSAGE);
			defsrv.setStatus(IServiceResult.SUCCESS_STATUS);
			
		} catch (DaoException e) {			
			e.printStackTrace();
			log.error(this.getClass().getName()+" findAllRoles(BaseVo bsvo) fail! "+e.getMessage());
			throw new ServiceException(this.getClass().getName()+" findAllRoles(BaseVo bsvo) fail!");
		}
		return defsrv;
	}
	public IServiceResult findRoleById(BaseVo bsvo)throws ServiceException{
		DefaultServiceResult defsrv = new DefaultServiceResult();
		RoleVo roleVo =(RoleVo)bsvo;
		Role role = new Role();
		role.setId(roleVo.getId());
		try {
			role=(Role)roleDao.findEntityById(role);			
			if (role==null) {
				log.error(this.getClass().getName()+" findRoleById(BaseVo bsvo) fail! can not find role by id: "+role.getId());
				throw new ServiceException(this.getClass().getName()+" findRoleById(BaseVo bsvo) fail! can not find role by id: "+role.getId());
			}
				
			defsrv.setData(role);
			defsrv.setMessage(IServiceResult.SUCCESS_MESSAGE);
			defsrv.setStatus(IServiceResult.SUCCESS_STATUS);
		} catch (DaoException e) {			
			e.printStackTrace();
			log.error(this.getClass().getName()+" findRoleById(BaseVo bsvo) fail! "+e.getMessage());
			throw new ServiceException(this.getClass().getName()+" findRoleById(BaseVo bsvo) fail!");
		}
		
		return defsrv;
	}
 	private RoleNode funcToRoleNode(Func f,Integer nodeType) {
 		RoleNode node = new RoleNode();
 		node.setNodeId(f.getId());
		node.setName(f.getFuncname());
		node.setNodeType(nodeType);
		node.setParentNo(f.getParfuncno());
 		return node;
 	}
	private RoleNode optToRoelNode(Operation op,Integer nodeType) {
		RoleNode node  = new RoleNode();
		node.setNodeId(op.getId());
		node.setName(op.getName());
		node.setNodeType(nodeType);
		node.setParentNo(op.getFuncId());
		return node;
	}
	public IServiceResult createRole(BaseVo bsvo) throws ServiceException{
		log.info(this.getClass().getName()+" enter createRole(BaseVo bsvo)");
		RoleVo roleVo = (RoleVo)bsvo;
		Role role = new Role();
		role.setName(roleVo.getName());
		role.setDescr(roleVo.getDescr());
		
		
	    try {
			Func rootf = funcDao.findRoot();			
			role.getRoleNodes().add(funcToRoleNode(rootf,RoleNode.ROLE_NODE_FUNC_TYPE));
		} catch (DaoException e1) {			
			e1.printStackTrace();
		}
		
		if ((roleVo.getRoleNodes()!=null)&&(roleVo.getRoleNodes().size()>0)) {
			for(int i=0;i<roleVo.getRoleNodes().size();i++){
				String optnode = ((String)roleVo.getRoleNodes().get(i)).trim();
				
				if ((optnode.startsWith("2#"))||(optnode.startsWith("3#"))){
				   optnode = optnode.substring(2);
				   long funcid =CommonUtils.convertStrToLong(optnode);
				   if (funcid>0) {
					   Func f = new Func();
					   f.setId(new Long(funcid));
					   
					   try {
						   f =  (Func)funcDao.findEntityById(f);
						   role.add(funcToRoleNode(f,RoleNode.ROLE_NODE_FUNC_TYPE));
					   } catch (DaoException e) {
						  e.printStackTrace();
						  log.error(this.getClass().getName()+" createRole(BaseVo bsvo) fail! "+e.getMessage());
					   }					   
				   }		    
					
				}else if (optnode.startsWith("4#")){
					 optnode = optnode.substring(2);
					   long optid =CommonUtils.convertStrToLong(optnode);
					   if (optid>0) {
						   Operation op = new Operation();
						   op.setId(new Long(optid));
						   
						   try {
							   op =  (Operation)optDao.findEntityById(op);
							   role.add(optToRoelNode(op,RoleNode.ROLE_NODE_OPERATION_TYPE));
						   } catch (DaoException e) {
							  e.printStackTrace();
							  log.error(this.getClass().getName()+" createRole(BaseVo bsvo) fail! "+e.getMessage());
						   }					   
					   }	
				}
			}
		}
		
		try {
			role=(Role)roleDao.createEntity(role);
		} catch (DaoException e) {			
			e.printStackTrace();
			log.error(this.getClass().getName()+" createRole(BaseVo bsvo) fail! "+e.getMessage());
			throw new ServiceException(this.getClass().getName()+" createRole(BaseVo bsvo) fail!");
		}
		DefaultServiceResult defsrv = new DefaultServiceResult();
		defsrv.setData(role);
		defsrv.setMessage(IServiceResult.SUCCESS_MESSAGE);
		defsrv.setStatus(IServiceResult.SUCCESS_STATUS);
		return defsrv ;
	}
	
    public IServiceResult updateRole(BaseVo bsvo) throws ServiceException{
    	log.info(this.getClass().getName()+" enter updateRole(BaseVo bsvo)");
		RoleVo roleVo = (RoleVo)bsvo;
		Role role = new Role();
		role.setId(roleVo.getId());
		try {
			role = (Role)roleDao.findEntityById(role);
			if (role==null) {
				log.error(this.getClass().getName()+" createRole(BaseVo bsvo) fail! role can not find by id: "+role.getId());
				throw new ServiceException(this.getClass().getName()+" createRole(BaseVo bsvo) fail!");
			}
		} catch (DaoException e) {			
			e.printStackTrace();
			log.error(this.getClass().getName()+" createRole(BaseVo bsvo) fail! "+e.getMessage());
			throw new ServiceException(this.getClass().getName()+" createRole(BaseVo bsvo) fail! role can not find by id: "+role.getId());
		}
		
		role.setName(roleVo.getName());
		role.setDescr(roleVo.getDescr());
		role.getRoleNodes().clear();
		Set updatenodes = new HashSet();
		if ((roleVo.getRoleNodes()!=null)&&(roleVo.getRoleNodes().size()>0)) {
			for(int i=0;i<roleVo.getRoleNodes().size();i++){
				String optnode = ((String)roleVo.getRoleNodes().get(i)).trim();
				
				if ((optnode.startsWith("2#"))||(optnode.startsWith("3#"))){
				   optnode = optnode.substring(2);
				   long funcid =CommonUtils.convertStrToLong(optnode);
				   if (funcid>0) {
					   Func f = new Func();
					   f.setId(new Long(funcid));
					   
					   try {
						   f =  (Func)funcDao.findEntityById(f);
						   updatenodes.add(funcToRoleNode(f,RoleNode.ROLE_NODE_FUNC_TYPE));
					   } catch (DaoException e) {
						  e.printStackTrace();
						  log.error(this.getClass().getName()+" createRole(BaseVo bsvo) fail! "+e.getMessage());
					   }					   
				   }		    
					
				}else if (optnode.startsWith("4#")){
					 optnode = optnode.substring(2);
					   long optid =CommonUtils.convertStrToLong(optnode);
					   if (optid>0) {
						   Operation op = new Operation();
						   op.setId(new Long(optid));
						   
						   try {
							   op =  (Operation)optDao.findEntityById(op);
							   updatenodes.add(optToRoelNode(op,RoleNode.ROLE_NODE_OPERATION_TYPE));
						   } catch (DaoException e) {
							  e.printStackTrace();
							  log.error(this.getClass().getName()+" createRole(BaseVo bsvo) fail! "+e.getMessage());
						   }					   
					   }	
				}
			}
		}
		
		try {
			role.setRoleNodes(updatenodes);
			role=(Role)roleDao.updateEntity(role);
		    roleNodeDao.deleteRoleNodesByRoleId(role);
			
		} catch (DaoException e) {			
			e.printStackTrace();
			log.error(this.getClass().getName()+" createRole(BaseVo bsvo) fail! "+e.getMessage());
			throw new ServiceException(this.getClass().getName()+" createRole(BaseVo bsvo) fail!");
		}
		
		
		DefaultServiceResult defsrv = new DefaultServiceResult();
		defsrv.setData(role);
		defsrv.setMessage(IServiceResult.SUCCESS_MESSAGE);
		defsrv.setStatus(IServiceResult.SUCCESS_STATUS);
		return defsrv ;
	}
	
	public IServiceResult deleteRole(BaseVo bsvo) throws ServiceException{
		RoleVo roleVo = (RoleVo)bsvo;
		Role role = new Role();
		role.setId(roleVo.getId());
		try {
			role=(Role)roleDao.findEntityById(role);
			roleDao.deleteEntity(role);
		} catch (DaoException e) {
			e.printStackTrace();
			log.error(this.getClass().getName()+" createRole(BaseVo bsvo) fail! "+e.getMessage());
			throw new ServiceException(this.getClass().getName()+" createRole(BaseVo bsvo) fail!");
		}
		DefaultServiceResult defsrv = new DefaultServiceResult();
		defsrv.setData(role);
		defsrv.setMessage(IServiceResult.SUCCESS_MESSAGE);
		defsrv.setStatus(IServiceResult.SUCCESS_STATUS);
		return defsrv;
	}
	
	private Role voToBo(RoleVo roleVo)  throws ServiceException {
		try {
			Role role = new Role();
			BeanUtils.copyProperties(role, roleVo);
			
			return  role ;
		} catch (IllegalAccessException e) {				
			e.printStackTrace();
			log.error(this.getClass().getName()+" voToBo(RoleVo roleVo) fail! "+e.getMessage());
			throw new ServiceException(this.getClass().getName()+" voToBo(RoleVo roleVo) fail!");	
		} catch (InvocationTargetException e) {				
			e.printStackTrace();
			log.error(this.getClass().getName()+" voToBo(RoleVo roleVo) fail! "+e.getMessage());
			throw new ServiceException(this.getClass().getName()+" voToBo(RoleVo roleVo) fail!");
		} 
		
	}

	public List<RoleVo> findAllRoleVos() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<RoleVo> findAllRoleVos(Pagination pagi) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public RoleVo findRoleById(long roleid) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
