package com.nci.cp.core.sysmgt.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nci.cp.core.exception.DaoException;
import com.nci.cp.core.exception.ServiceException;
import com.nci.cp.core.model.BaseVo;
import com.nci.cp.core.sysmgt.dao.IDepartmentDao;
import com.nci.cp.core.sysmgt.dao.IFuncDao;
import com.nci.cp.core.sysmgt.dao.IGroupDao;
import com.nci.cp.core.sysmgt.dao.IOperationDao;
import com.nci.cp.core.sysmgt.dao.IRoleDao;
import com.nci.cp.core.sysmgt.dao.IRoleNodeDao;
import com.nci.cp.core.sysmgt.dao.IUserDao;
import com.nci.cp.core.sysmgt.dao.MenuDao;
import com.nci.cp.core.sysmgt.dao.MenuItemDao;
import com.nci.cp.core.sysmgt.model.Department;
import com.nci.cp.core.sysmgt.model.Func;
import com.nci.cp.core.sysmgt.model.Group;
import com.nci.cp.core.sysmgt.model.IUserInfo;
import com.nci.cp.core.sysmgt.model.Operation;
import com.nci.cp.core.sysmgt.model.Role;
import com.nci.cp.core.sysmgt.model.RoleNode;
import com.nci.cp.core.sysmgt.model.User;
import com.nci.cp.core.sysmgt.vo.DepartmentVo;
import com.nci.cp.core.sysmgt.vo.FuncVo;
import com.nci.cp.core.sysmgt.vo.GroupVo;
import com.nci.cp.core.sysmgt.vo.RoleVo;
import com.nci.cp.core.sysmgt.vo.UserVo;
import com.nci.cp.ds.tree.TreeNode;

/**
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-5-18 
 */
public class CopyOfSysManagement {
	private static Log log = LogFactory.getLog(CopyOfSysManagement.class);
    private MenuDao           menuDao;
    private MenuItemDao       menuitemDao;
    private IUserDao          userDao;
    private IGroupDao         groupDao;
    private IFuncDao          funcDao;
    private IOperationDao     optDao;
    private IDepartmentDao    depDao;
    private IRoleDao          roleDao;
    private IRoleNodeDao      roleNodeDao;
    private IRoleService      roleService;
   

	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}
	public void setMenuitemDao(MenuItemDao menuitemDao) {
		this.menuitemDao = menuitemDao;
	}
    
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	
	public void setFuncDao(IFuncDao funcDao) {
		this.funcDao = funcDao;
	}
	
    
	public void setGroupDao(IGroupDao groupDao) {
		this.groupDao = groupDao;
	}
	public void setDepDao(IDepartmentDao depDao) {
		this.depDao = depDao;
	}
	public void setOptDao(IOperationDao optDao) {
		this.optDao = optDao;
	}
	
	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
	
	public void setRoleNodeDao(IRoleNodeDao roleNodeDao) {
		this.roleNodeDao = roleNodeDao;
	}
	
	
	
	
	public Long createFunc(FuncVo funcVo) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	public FuncVo findFuncById(Long funcId) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	public Long updateFunc(FuncVo funcVo) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	public User authUser(User u) throws ServiceException {
		try {
			List urs = userDao.authenticationUser(u); 
			if ((urs!=null)&&(urs.size()>0))
			   return (User)urs.get(0) ;
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public Func findFuncById(Func func)throws ServiceException{
		try {
			Func f=(Func)funcDao.findEntityById(func);
			return f;
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List findAllFuncs()throws ServiceException{
		try {
			List funcs =funcDao.findAllEntities();
			return funcs;
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List findFuncs() throws ServiceException {
		try {
	    	return funcDao.findAllFuncs();
		} catch (DaoException e) {
			e.printStackTrace();
			log.error(this.getClass().getName()+" findFuncs() fail! "+e.getMessage());
			throw new ServiceException(this.getClass().getName()+" findFuncs() fail!");	
		}
	}
	
	public List<Func> findAllChildrenOfFunc(Func func) throws ServiceException {
		List children = new ArrayList();
		try {
			func = (Func)funcDao.findEntityById(func);
			children.add(func);
		} catch (DaoException e) {
			e.printStackTrace();
			log.error(this.getClass().getName()+" findAllChildrenOfFunc  load func fail! "+e.getMessage());
			throw new ServiceException(this.getClass().getName()+" findAllChildrenOfFunc) fail!");	
		}
		
		try {
			funcDao.findAllChildrenOfFunc(func, children);
		} catch (DaoException e) {
			e.printStackTrace();
			log.error(this.getClass().getName()+" findAllChildrenOfFunc load children of func fail! "+e.getMessage());
			throw new ServiceException(this.getClass().getName()+" findAllChildrenOfFunc) load children of func fail!");
		}
		return children;
	}

	
	public List findFuncTree() throws ServiceException {
		List reFuncs = null;
		try {
			List allFuncs = findFuncs();
			if ((allFuncs!=null)&&(allFuncs.size()>0)) {
				reFuncs = new ArrayList();
				for (int i=0;i<allFuncs.size();i++) {
					Func f =(Func)allFuncs.get(i);
					if(f.getParfuncno()==-1)  
					    	reFuncs.add(f);			
							
				}
				
			}
	    	return reFuncs;
		} catch (ServiceException e) {
			e.printStackTrace();
			log.error(this.getClass().getName()+" findFuncTree() fail! "+e.getMessage());
			throw new ServiceException(this.getClass().getName()+" findFuncTree() fail!");	
		}
	}

	public List findThreeLayerFuncs() throws ServiceException {
		
		try {
			List l=funcDao.findThreeLayersFuncs();
			return l;
		} catch (DaoException e) {
			e.printStackTrace();
			log.error(this.getClass().getName()+" findThreeLayerFuncs() fail! "+e.getMessage());
			throw new ServiceException(this.getClass().getName()+" findThreeLayerFuncs() fail!");	
		}
	}

	public List findFuncTreeLevel(String mark) throws ServiceException {
		List reList = new ArrayList();
		
	    if(mark.equals(ISysManagement.FUNC_SERVICE_SECOND)) {
	    	return findSecondTreeLevel();
	    }else if (mark.equals(ISysManagement.FUNC_SERVICE_ROOT)){
	    	try {
				Func f = findRoot();
				if (f!=null) {
					List root = new ArrayList();
					return root;
				}
			} catch (DaoException e) {
				e.printStackTrace();
				log.error(this.getClass().getName()+" findFuncTreeLevel(ISysManagement.FUNC_SERVICE_ROOT) fail! "+e.getMessage());
				throw new ServiceException(this.getClass().getName()+" findFuncTreeLevel(ISysManagement.FUNC_SERVICE_ROOT) fail! ");
			}
	    	
	    	return null;
	    }
	    	
		return null;
	}
	
	/**
	 * @return 根结点
	 * @throws DaoException
	 */
	public Func findRoot() throws DaoException {
		
		return funcDao.findRoot();
	}
	
	/**
	 * 查找功能树的第二层所有节点
	 * @return 结点列表
	 * @throws ServiceException
	 */
	private List findSecondTreeLevel() throws ServiceException {
		List reFuncs = null;
		try {
			List allFuncs = findFuncs();
			if ((allFuncs!=null)&&(allFuncs.size()>0)) {
				reFuncs = new ArrayList();
				for (int i=0;i<allFuncs.size();i++) {
					Func f =(Func)allFuncs.get(i);
					//如果结点的父结点ID是0，则当前结点是第二层的结点
					if(f.getParfuncno()==-1)  
					    	reFuncs.add(f);			
							
				}
				
			}
	    	return reFuncs;
		} catch (ServiceException e) {
			e.printStackTrace();
			log.error(this.getClass().getName()+" findSecondTreeLevel() fail! "+e.getMessage());
			throw new ServiceException(this.getClass().getName()+" findSecondTreeLevel() fail!");	
		}
	}
	
	public boolean deleteFuncAndChildren(long funcid) throws ServiceException{
		List fchildren =null;
		try {
			 Func func = new Func();
			 func.setId(new Long(funcid));
			 fchildren = findAllChildrenOfFunc(func);
		} catch (ServiceException e) {
		    e.printStackTrace();
		    log.error(this.getClass().getName()+" deleteFuncAndChildren fail! "+e.getMessage());
		    return false;
		}		
		if ((fchildren!=null)&&(fchildren.size()>0)){
			try {
				if (optDao.deleteOptOfFuncs(fchildren)){
				  return funcDao.deleteEntities(fchildren);
				}
				return false;
			}catch(DaoException ex) {
				ex.printStackTrace();
			    log.error(this.getClass().getName()+" deleteFuncAndChildren fail! "+ex.getMessage());
			    throw new ServiceException(this.getClass().getName()+" deleteFuncAndChildren(Func func) fail!");
			}
			
		}
	    return false;
	}
	public Func createFunc(Func func) throws DaoException {
		return (Func)funcDao.createEntity(func);
	}
	public Func updateFunc(Func func) throws DaoException {
		return (Func)funcDao.updateEntity(func);
	}
	public IUserInfo authentication(IUserInfo usrinfo) throws ServiceException {
		try {
			List urs = userDao.authenticationUser(usrinfo); 
			if ((urs!=null)&&(urs.size()>0))
			   return (User)urs.get(0) ;
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public List findAllMenus() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	public List findOptsByFuncId(FuncVo funcVo) throws ServiceException {
		try {
			return optDao.findOptsByFuncId(funcVo.getId());
		} catch (DaoException e) {
		   e.printStackTrace();	
		   log.error(this.getClass().getName()+" findOptsByFuncId(Func func) fail! "+e.getMessage());
		   throw new ServiceException(this.getClass().getName()+" findOptsByFuncId(Func func) fail!");	
		}
   
	}
	public Operation findOperationById(Operation opt) throws ServiceException {
		try {
			Operation option = (Operation)optDao.findEntityById(opt);
			return option;
		} catch (DaoException e) {			
			e.printStackTrace();
			 log.error(this.getClass().getName()+" findOperationById fail! "+e.getMessage());
			 throw new ServiceException(this.getClass().getName()+" findOperationById fail!");	
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
	public boolean deleteOperation(Operation opt) throws ServiceException {
		try {
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
	public DepartmentVo findDepById(DepartmentVo depVo)throws ServiceException{
		try {
			Department dep = new Department();
			dep.setId(depVo.getId());
			Department d=null;
			Department dp = null;
		
			 d=(Department)depDao.findEntityById(dep);
		
			
			if (d!=null) {
				try {
					DepartmentVo depmtvo = new DepartmentVo();
					BeanUtils.copyProperties(depmtvo, d);
					return  depmtvo;
				} catch (IllegalAccessException e) {				
					e.printStackTrace();
					log.error(this.getClass().getName()+" findDepById fail! "+e.getMessage());
					throw new ServiceException(this.getClass().getName()+" findDepById opt) fail!");	
				} catch (InvocationTargetException e) {				
					e.printStackTrace();
					log.error(this.getClass().getName()+" findDepById fail! "+e.getMessage());
					throw new ServiceException(this.getClass().getName()+" findDepById opt) fail!");
				} 
			}
			
			}catch (DaoException e) {
				e.printStackTrace();
				log.error(this.getClass().getName()+" findDepById fail! "+e.getMessage());
				throw new ServiceException(this.getClass().getName()+" findDepById opt) fail!");
			}
		  return null;
	}
	public List findDeps() throws ServiceException {
		try {
	    	return depDao.findAllDepartments();
		} catch (DaoException e) {
			e.printStackTrace();
			log.error(this.getClass().getName()+" findDeps() fail! "+e.getMessage());
			throw new ServiceException(this.getClass().getName()+" findDeps() fail!");	
		}
	}
	public DepartmentVo createOrUpdateDepartment(DepartmentVo depVo) throws ServiceException {
		Department dep = new Department();
		try {
			BeanUtils.copyProperties(dep, depVo);
			dep.setPardepno(depVo.getPardepno());
			if (depVo.getId()==null) {			
			   dep=(Department)depDao.createEntity(dep);
			}else {
			   dep=(Department)depDao.updateEntity(dep);
			}
			BeanUtils.copyProperties(depVo, dep);
			
			return depVo;
		} catch (IllegalAccessException e) {				
			e.printStackTrace();
			log.error(this.getClass().getName()+" createOrUpdateDepartment(DepartmentVo depVo) fail! "+e.getMessage());
			throw new ServiceException(this.getClass().getName()+" createOrUpdateDepartment(DepartmentVo depVo) fail!");	
		} catch (InvocationTargetException e) {				
			e.printStackTrace();
			log.error(this.getClass().getName()+" createOrUpdateDepartment(DepartmentVo depVo) fail! "+e.getMessage());
			throw new ServiceException(this.getClass().getName()+" createOrUpdateDepartment(DepartmentVo depVo) fail!");
		} 	catch (DaoException e) {
			 e.printStackTrace();
			 log.error(this.getClass().getName()+" createOrUpdateDepartment(DepartmentVo depVo) fail! "+e.getMessage());
			 throw new ServiceException(this.getClass().getName()+" createOrUpdateDepartment(DepartmentVo depVo) fail!");
		}catch (Exception e) {
			 e.printStackTrace();
			 log.error(this.getClass().getName()+" createOrUpdateDepartment(DepartmentVo depVo) fail! "+e.getMessage());
			 throw new ServiceException(this.getClass().getName()+" createOrUpdateDepartment(DepartmentVo depVo) fail!");
		}
	   }
		public boolean deleteDep(DepartmentVo depVo) throws ServiceException {
			
			try {
				Department dep = new Department();
				dep.setId(depVo.getId());
				
				return depDao.deleteEntity(dep);
			} catch (DaoException e) {
				 e.printStackTrace();
				 log.error(this.getClass().getName()+" deleteDep(DepartmentVo depVo) fail! "+e.getMessage());
				 throw new ServiceException(this.getClass().getName()+" deleteDep(DepartmentVo depVo) fail!");	
			}
		  
		}
		public List findUsers() throws ServiceException {
			try {
				List uservos = new ArrayList();
				List<User> users = userDao.findAllEntities();
				if ((users!=null)&&(users.size()>0)) {
					for (User u : users) {
					 UserVo uv = new UserVo();
					 uv.setId(u.getId());
					 uv.setUsername(u.getUsername());
					 uv.setUserid(u.getUserid());
					 uservos.add(uv);
					}
				}
		    	return uservos;
			} catch (DaoException e) {
				e.printStackTrace();
				log.error(this.getClass().getName()+" findUsers() fail! "+e.getMessage());
				throw new ServiceException(this.getClass().getName()+" findUsers() fail!");	
			}
		}
		public UserVo createOrUpdateUser(UserVo userVo) throws ServiceException {
			User user = new User();
			try {
				BeanUtils.copyProperties(user, userVo);

				if (userVo.getId()==null) {			
				   user=(User)userDao.createEntity(user);
				}else {
					user=(User)userDao.updateEntity(user);
				}
				BeanUtils.copyProperties(userVo, user);
				
				return userVo;
			} catch (IllegalAccessException e) {				
				e.printStackTrace();
				log.error(this.getClass().getName()+" createOrUpdateUser(UserVo userVo) fail! "+e.getMessage());
				throw new ServiceException(this.getClass().getName()+" createOrUpdateUser(UserVo userVo) fail!");	
			} catch (InvocationTargetException e) {				
				e.printStackTrace();
				log.error(this.getClass().getName()+" createOrUpdateUser(UserVo userVo) fail! "+e.getMessage());
				throw new ServiceException(this.getClass().getName()+" createOrUpdateUser(UserVo userVo) fail!");
			} 	catch (DaoException e) {
				 e.printStackTrace();
				 log.error(this.getClass().getName()+" createOrUpdateUser(UserVo userVo) fail! "+e.getMessage());
				 throw new ServiceException(this.getClass().getName()+" createOrUpdateUser(UserVo userVo) fail!");
			}catch (Exception e) {
				 e.printStackTrace();
				 log.error(this.getClass().getName()+" createOrUpdateUser(UserVo userVo) fail! "+e.getMessage());
				 throw new ServiceException(this.getClass().getName()+" createOrUpdateUser(UserVo userVo) fail!");
			}
		 }
        public boolean deleteUser(UserVo userVo) throws ServiceException {			
			try {
				User user = new User();
				user.setId(userVo.getId());
				
				return userDao.deleteEntity(user);
			} catch (DaoException e) {
				 e.printStackTrace();
				 log.error(this.getClass().getName()+" deleteUser(UserVo userVo) fail! "+e.getMessage());
				 throw new ServiceException(this.getClass().getName()+" deleteUser(UserVo userVo) fail!");	
			}
		  
		}
        public UserVo findUserById(UserVo userVo)throws ServiceException{
    		try {
    			User user = new User();
    			user.setId(userVo.getId());    		
    			user=(User)userDao.findEntityById(user);    		
    			
    			if (user!=null) {
    				try {
    					UserVo uservo = new UserVo();
    					BeanUtils.copyProperties(uservo, user);
    					return  uservo;
    				} catch (IllegalAccessException e) {				
    					e.printStackTrace();
    					log.error(this.getClass().getName()+" findUserById(UserVo userVo) fail! "+e.getMessage());
    					throw new ServiceException(this.getClass().getName()+" findUserById(UserVo userVo) fail!");	
    				} catch (InvocationTargetException e) {				
    					e.printStackTrace();
    					log.error(this.getClass().getName()+" findUserById(UserVo userVo) fail! "+e.getMessage());
    					throw new ServiceException(this.getClass().getName()+" findUserById(UserVo userVo) fail!");
    				} 
    			}
    			
    			}catch (DaoException e) {
    				e.printStackTrace();
    				log.error(this.getClass().getName()+" findUserById(UserVo userVo) fail! "+e.getMessage());
    				throw new ServiceException(this.getClass().getName()+" findUserById(UserVo userVo) fail!");
    			}
    		  return null;
    	}
        public List findGroupVos() throws ServiceException {
			try {
				List groupvos = new ArrayList();
				List<Group> groups = groupDao.findAllEntities();
				if ((groups!=null)&&(groups.size()>0)) {
					for (Group g : groups) {
					 GroupVo gv = new GroupVo();
					 gv.setId(g.getId());
					 gv.setName(g.getName());
					 gv.setDescr(g.getDescr());
					 groupvos.add(gv);
					}
				}
		    	return groupvos;
			} catch (DaoException e) {
				e.printStackTrace();
				log.error(this.getClass().getName()+" findGroups() fail! "+e.getMessage());
				throw new ServiceException(this.getClass().getName()+" findGroups() fail!");	
			}
		}
		public GroupVo createOrUpdateGroup(GroupVo groupVo) throws ServiceException {
			Group group = new Group();
			try {
				BeanUtils.copyProperties(group, groupVo);

				if (groupVo.getId()==null) {
					 List uservos = groupVo.getUsers();
					 List users = new ArrayList();
					 group=(Group)groupDao.createEntity(group);
					 if (uservos.size()>0) {
						 for(int i=0;i<uservos.size();i++) {
							 User u = new User();
							 UserVo uvo = (UserVo)uservos.get(i);
							 u.setId(uvo.getId());
							 users.add(u);
						 }
						 groupDao.createUsersOfGroup(group, users);
					 }				   
				    List rolevos = groupVo.getRoles();
				    List roles   = new ArrayList();
				    if (rolevos.size()>0) {
				    	for(int i=0;i<rolevos.size();i++) {
				    		Role r = new Role();
				    		RoleVo rvo = (RoleVo)rolevos.get(i);
				    		r.setId(rvo.getId());
				    		roles.add(r);
				    	}
				    	groupDao.createRolesOfGroup(group, roles);
				    }
					 
					 
				}else {
					 List uservos = groupVo.getUsers();
					 List users = new ArrayList();
					 
					 if (uservos.size()>0) {
						 for(int i=0;i<uservos.size();i++) {
							 User u = new User();
							 UserVo uvo = (UserVo)uservos.get(i);
							 u.setId(uvo.getId());
							 users.add(u);
						 }
						
					 }
					 
					
				     groupDao.updateGroup(group, users);
				     
				     List rolevos = groupVo.getRoles();
				     List roles = new ArrayList();
				     if (rolevos.size()>0){
				    	 for(int i=0;i<rolevos.size();i++){
				    		 Role r = new Role();
				    		 RoleVo rvo = (RoleVo)rolevos.get(i);
				    		 r.setId(rvo.getId());
				    		 roles.add(r);
				    	 }
				     }
				      
				     groupDao.updateRolesOfGroup(group, roles);
				   
				}
				BeanUtils.copyProperties(groupVo, group);
				
				return groupVo;
			} catch (IllegalAccessException e) {				
				e.printStackTrace();
				log.error(this.getClass().getName()+" createOrUpdateGroup(GroupVo groupVo) fail! "+e.getMessage());
				throw new ServiceException(this.getClass().getName()+" createOrUpdateGroup(GroupVo groupVo) fail!");	
			} catch (InvocationTargetException e) {				
				e.printStackTrace();
				log.error(this.getClass().getName()+" createOrUpdateGroup(GroupVo groupVo) fail! "+e.getMessage());
				throw new ServiceException(this.getClass().getName()+" createOrUpdateGroup(GroupVo groupVo) fail!");
			} 	catch (DaoException e) {
				 e.printStackTrace();
				 log.error(this.getClass().getName()+" createOrUpdateGroup(GroupVo groupVo) fail! "+e.getMessage());
				 throw new ServiceException(this.getClass().getName()+" createOrUpdateGroup(GroupVo groupVo) fail!");
			}catch (Exception e) {
				 e.printStackTrace();
				 log.error(this.getClass().getName()+" createOrUpdateGroup(GroupVo groupVo) fail! "+e.getMessage());
				 throw new ServiceException(this.getClass().getName()+" createOrUpdateGroup(GroupVo groupVo) fail!");
			}
		 }
        public boolean deleteGroup(GroupVo groupVo) throws ServiceException {
			
			try {
				Group group = new Group();
				group.setId(groupVo.getId());
				
				return groupDao.deleteGroup(group);
			} catch (DaoException e) {
				 e.printStackTrace();
				 log.error(this.getClass().getName()+" deleteGroup(GroupVo groupVo) fail! "+e.getMessage());
				 throw new ServiceException(this.getClass().getName()+" deleteGroup(GroupVo groupVo) fail!");	
			}
		  
		}
        public GroupVo findGroupById(GroupVo groupVo)throws ServiceException{
    		try {
    			Group group = new Group();
    			group.setId(groupVo.getId());    		
    			group=(Group)groupDao.findEntityById(group);
    		    List users = groupDao.findUserOfGroup(group);
    		    List roles = groupDao.findRolesOfGroup(group);
    			
    			if (group!=null) {
    				try {
    					GroupVo groupvo = new GroupVo();
    					BeanUtils.copyProperties(groupvo, group);
    					groupvo.setUsers(new ArrayList());
    					if ((users!=null)&&(users.size()>0)) {    					
    						for(int i=0;i<users.size();i++) {
    						   	User u = (User)users.get(i);
    							UserVo uvo  = new UserVo();
    							uvo.setId(u.getId());
    							uvo.setUserid(u.getUserid());
    							uvo.setUsername(u.getUsername());    					
    							groupvo.getUsers().add(uvo);
    						}    						
    					}else {
    						log.warn(this.getClass().getName()+"findGroupById(GroupVo groupVo) users is not null");
    					}
    					groupvo.setRoles(new ArrayList());
    					if ((roles!=null)&&(roles.size()>0)) {
    						for (int i=0;i<roles.size();i++) {
    						    Role r = (Role)roles.get(i);
    						    RoleVo rvo = new RoleVo();
    						    rvo.setId(r.getId());
    						    rvo.setName(r.getName());
    						    rvo.setDescr(r.getDescr());
    						    groupvo.getRoles().add(rvo);
    						}
    					}
    					return  groupvo;
    				} catch (IllegalAccessException e) {				
    					e.printStackTrace();
    					log.error(this.getClass().getName()+" findGroupById(GroupVo groupVo) fail! "+e.getMessage());
    					throw new ServiceException(this.getClass().getName()+" findGroupById(GroupVo groupVo) fail!");	
    				} catch (InvocationTargetException e) {				
    					e.printStackTrace();
    					log.error(this.getClass().getName()+" findGroupById(GroupVo groupVo) fail! "+e.getMessage());
    					throw new ServiceException(this.getClass().getName()+" findGroupById(GroupVo groupVo) fail!");
    				} 
    			}
    			
    			}catch (DaoException e) {
    				e.printStackTrace();
    				log.error(this.getClass().getName()+" findGroupById(GroupVo groupVo) fail! "+e.getMessage());
    				throw new ServiceException(this.getClass().getName()+" findGroupById(GroupVo groupVo) fail!");
    			}
    		  return null;
    	}
        
        public IServiceResult obtainMenuTree(BaseVo bsvo) throws ServiceException {
        	List rolenodes = new ArrayList();
        	RoleVo roleVo = (RoleVo)bsvo;
        	Role role = new Role();
        	role.setId(roleVo.getId());
        	try {
				role = (Role)roleDao.findEntityById(role);
			} catch (DaoException e) {
				
				e.printStackTrace();
			}
        	Set nds = role.getRoleNodes();
        	Iterator it  = nds.iterator();
        	while(it.hasNext()) {
        		RoleNode rd =(RoleNode)it.next();
        		TreeNode td = new TreeNode();
        		
        		td.setNodeid(rd.getNodeId());
        		if (rd.getParentNo().longValue()==0){
        			td.setParentid(-1);
        		}else{
        			td.setParentid(rd.getParentNo());
        		}
        		td.setNodename(rd.getName());
        		rolenodes.add(td);
        	}
        /*	Set rns  = role.getRoleNodes();
			TreeNode root = findRootFromSet(rns);*/
        	DefaultServiceResult defsrv = new DefaultServiceResult();
			defsrv.setData(rolenodes);
			defsrv.setMessage(IServiceResult.SUCCESS_MESSAGE);
			defsrv.setStatus(IServiceResult.SUCCESS_STATUS);
			return defsrv ;
        }
      /*  private TreeNode findRootFromSet(Set nodes) {
        	TreeNode root = new TreeNode();
        	Iterator it  = nodes.iterator();
			while(it.hasNext()) {
				RoleNode rn = (RoleNode)it.next();
				if (rn.getParentNo().longValue()==0) {
					root.setLevel(0);
					root.setNodeid(rn.getNodeId());
					root.setNodename(rn.getName());
					root.setParentid(-1);
					nodes.remove(rn);	
				}							
			}
        	return root;
        }
        private void findChildrenOfNode(Set nodes,TreeNode root) { 
        	TreeNode node = null;
        	Iterator it  = nodes.iterator();
        	while(it.hasNext()) {
				RoleNode rn = (RoleNode)it.next();
				if (rn.getParentNo().longValue()==root.getNodeid()) {
					node = new TreeNode();
					node.setLevel(root.getLevel()+1);
					node.setNodeid(rn.getNodeId());
					node.setNodename(rn.getName());
					node.setParentid(root.getParentid());
					root.addNode(node);
					nodes.remove(rn);	
				}							
			} 
        	if (node!=null)   	{
        		if (node.childrenSize()>0) {
        			for (int i=0;i<node.childrenSize();i++) {
        				TreeNode td  = node.getNode(i);
        				findChildrenOfNode(nodes,td);
        			}
        		}
        	}
        		
        }*/
        public IServiceResult obtainMenuTree() throws ServiceException {
        	Func froot = null;
        	TreeNode tree = null;
        	try {
				 froot=funcDao.findRoot();
			} catch (DaoException e) {
				e.printStackTrace();
				log.error(this.getClass().getName()+" obtainMenuTree() fail! "+e.getMessage());
				throw new ServiceException(this.getClass().getName()+" obtainMenuTree() fail!");
			}
			if (froot!=null) {
				tree = funcToTreeNode(froot);
				tree.setLevel(0);
				try {
					obtainNode(tree);
				} catch (DaoException e) {
					e.printStackTrace();
					log.error(this.getClass().getName()+" obtainMenuTree() fail! "+e.getMessage());
					throw new ServiceException(this.getClass().getName()+" obtainMenuTree() fail!");
				}
			//	printtree(tree);
			}
			DefaultServiceResult defsrv = new DefaultServiceResult();
			defsrv.setData(tree);
			defsrv.setMessage(IServiceResult.SUCCESS_MESSAGE);
			defsrv.setStatus(IServiceResult.SUCCESS_STATUS);
			return defsrv ;
			
			
			
			
			
        	/*if (froot!=null) {
        		tree = new TreeNode();
        		tree.setLevel(0);
        		tree.setParentid(-1);
        		tree.setNodeid(froot.getId().intValue());
        		List secondlevel = null;
        		try {
					 secondlevel =  funcDao.findChildrenOfFunc(froot);
				} catch (DaoException e) {
					e.printStackTrace();
					log.error(this.getClass().getName()+" obtainMenuTree() fail! "+e.getMessage());
    				throw new ServiceException(this.getClass().getName()+" obtainMenuTree() fail!");
				}
				if ((secondlevel!=null)&&(secondlevel.size()>0)) {
					for(int i=0;i<secondlevel.size();i++) {
						Func twol = (Func)secondlevel.get(i);
						TreeNode tn = new TreeNode();
						tn.setParentid(tree.getNodeid());
						tn.setLevel(1);
						tn.setNodeid(twol.getId().intValue());
						tn.setNodename(twol.getFuncname());						
					}
				 }
        	}*/
			
        	
        }
        private void obtainNode(TreeNode parent) throws DaoException {
        	if (parent.getLevel()==2){
        		Long fid = new Long(parent.getNodeid());
        		List opts = optDao.findOptsByFuncId(fid);
        		if ((opts!=null)&&(opts.size()>0)) {
        			for(int i=0;i<opts.size();i++) {
        			  Operation op = (Operation)opts.get(i);
        			  TreeNode cNd = new TreeNode();
        			  cNd.setParentid(parent.getNodeid());
        			  cNd.setNodeid(op.getId().intValue());
        			  cNd.setNodename(op.getName());
        			  cNd.setLevel(parent.getLevel()+1);
        			  parent.addNode(cNd);
        			}
        		}
        		return;
        	}else {
        		Func pfunc = new Func();
        		pfunc.setId(new Long(parent.getNodeid()));
        		pfunc = (Func)funcDao.findEntityById(pfunc);
        		List children = funcDao.findChildrenOfFunc(pfunc);
        		if ((children!=null)&&(children.size()>0)) {
        			for(int i=0;i<children.size();i++) {
        				Func cfunc = (Func)children.get(i);
        				cfunc = (Func)funcDao.findEntityById(cfunc);
        				TreeNode cNode = new TreeNode();
        				cNode.setParentid(parent.getNodeid());
        				cNode.setNodeid(cfunc.getId().intValue());
        				cNode.setNodename(cfunc.getFuncname());
        				cNode.setLevel(parent.getLevel()+1);
        				parent.addNode(cNode);
        				obtainNode(cNode);
        			}         		
        		}        	
        	}        	
        }
        private TreeNode funcToTreeNode(Func f) {        	
			TreeNode cNode = new TreeNode();
			cNode.setParentid(f.getParfuncno());
			cNode.setNodeid(f.getId().intValue());
			cNode.setNodename(f.getFuncname());
        	return cNode;
        }
        private void printtree(TreeNode root) {
        	printnode(root);
        	if (root.childrenSize()>0) {
        		for(int i=0;i<root.childrenSize();i++) {
        			TreeNode node = (TreeNode)root.getNode(i);
        			printtree(node);
        		}
        		
        	}
        }
        private void printnode(TreeNode node) {
        	System.out.println("=============node level: "+node.getLevel());
			System.out.println("=============node id   : "+node.getNodeid());
			System.out.println("=============node name : "+node.getNodename());
			System.out.println("=============node chilldren num : "+node.childrenSize());
        }
        public IServiceResult obtainMenuRoot() throws ServiceException{
        	TreeNode cNode  = new TreeNode();
        	DefaultServiceResult defsrv = new DefaultServiceResult();
        	try {
        		Func funcRoot  = funcDao.findRoot();
				 if (funcRoot==null) {
					 defsrv.setStatus(IServiceResult.FAIL_STATUS);
					 defsrv.setMessage("the func root is null");
					 return defsrv ;	 
				 }
					
 				cNode.setParentid(funcRoot.getParfuncno());
 				cNode.setNodeid(funcRoot.getId().intValue());
 				cNode.setNodename(funcRoot.getFuncname());
 				cNode.setLevel(0);
			} catch (DaoException e) {				
				e.printStackTrace();
				log.error(this.getClass().getName()+" obtainMenuRoot() fail! "+e.getMessage());
				throw new ServiceException(this.getClass().getName()+" obtainMenuRoot() fail!");
			}
			
			defsrv.setData(cNode);
			defsrv.setMessage(IServiceResult.SUCCESS_MESSAGE);
			defsrv.setStatus(IServiceResult.SUCCESS_STATUS);
			return defsrv ;
        }
		public IServiceResult useServiceByAction(BaseVo bsvo)
				throws ServiceException {	
			
			return roleService.useServiceByAction(bsvo);
		}
        public List findMenusOfUser(IUserInfo userinfo) throws ServiceException {
        	List roles =null;
        	List treenodes = new ArrayList();
        	try {
				roles=userDao.findRolesOfUser(userinfo);
				log.info(" findMenusOfUser(IUserInfo userinfo)  roles size: "+roles.size());
			} catch (DaoException e) {
				e.printStackTrace();
				log.error(this.getClass().getName()+" findMenusOfUser(IUserInfo userinfo) fail! "+e.getMessage());
				throw new ServiceException(this.getClass().getName()+" findMenusOfUser(IUserInfo userinfo) fail!");
			}
			RoleVo roleVo  = new RoleVo();
        	if (roles.size()>0) {
        		Role role = (Role)roles.get(0);        		
        		roleVo.setId(role.getId());
        		try {
    				role = (Role)roleDao.findEntityById(role);
    			} catch (DaoException e) {    				
    				e.printStackTrace();
    			}
            	Set nds = role.getRoleNodes();
            	if (nds.size()>0) {
            		try {
						Func rootf = funcDao.findRoot();
						if (rootf!=null) {
							TreeNode root = new TreeNode();
							root.setNodeid(rootf.getId());
							root.setNodename(rootf.getFuncname());
							root.setParentid(rootf.getParfuncno());
							treenodes.add(root);
						}
					} catch (DaoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            	}
            	Iterator it  = nds.iterator();
            	while(it.hasNext()) {
            		RoleNode rd =(RoleNode)it.next();            		
            		if (!rd.getNodeType().equals(RoleNode.ROLE_NODE_OPERATION_TYPE)) {
            		   TreeNode td = new TreeNode();
            		   Func func = new Func();
            		   func.setId(rd.getNodeId());
            		   try {
						  func = (Func)funcDao.findEntityById(func);
						  td.setNodeid(rd.getNodeId());
						  td.setData(func.getFuncaction());
	            		  if (rd.getParentNo().longValue()==0){
	            			  td.setParentid(-1);
	            		  }else{
	            			td.setParentid(rd.getParentNo());
	            		   }
	            		   td.setNodename(rd.getName());
	            		   treenodes.add(td);
					    } catch (DaoException e) {
						
						e.printStackTrace();
					    }
            		 
            		}
            	}
        	}
        	
        	return treenodes;
        }
}
