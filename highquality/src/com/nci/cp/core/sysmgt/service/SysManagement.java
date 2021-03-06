package com.nci.cp.core.sysmgt.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nci.cp.core.exception.DaoException;
import com.nci.cp.core.exception.ServiceException;
import com.nci.cp.core.model.BaseVo;
import com.nci.cp.core.model.IdEntity;
import com.nci.cp.core.sysmgt.dao.IDepartmentDao;
import com.nci.cp.core.sysmgt.dao.IGroupDao;
import com.nci.cp.core.sysmgt.dao.IRoleDao;
import com.nci.cp.core.sysmgt.dao.IRoleNodeDao;
import com.nci.cp.core.sysmgt.dao.IUserDao;
import com.nci.cp.core.sysmgt.model.Department;
import com.nci.cp.core.sysmgt.model.Func;
import com.nci.cp.core.sysmgt.model.Group;
import com.nci.cp.core.sysmgt.model.IUserInfo;
import com.nci.cp.core.sysmgt.model.Operation;
import com.nci.cp.core.sysmgt.model.OrgVO_Corp;
import com.nci.cp.core.sysmgt.model.OrgVO_Department;
import com.nci.cp.core.sysmgt.model.Role;
import com.nci.cp.core.sysmgt.model.RoleNode;
import com.nci.cp.core.sysmgt.model.User;
import com.nci.cp.core.sysmgt.vo.DepartmentVo;
import com.nci.cp.core.sysmgt.vo.FuncVo;
import com.nci.cp.core.sysmgt.vo.GroupVo;
import com.nci.cp.core.sysmgt.vo.RoleVo;
import com.nci.cp.core.sysmgt.vo.UserVo;
import com.nci.cp.ds.paging.Pagination;
import com.nci.cp.ds.tree.ITreeModel;
import com.nci.cp.ds.tree.TreeNode;

/**
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-5-18 
 */
public class SysManagement implements ISysManagement {
	
	private static Log log = LogFactory.getLog(SysManagement.class);

	private IUserDao          userDao;
	private IGroupDao         groupDao;
	private IDepartmentDao    depDao;
	private IRoleDao          roleDao;
	private IRoleNodeDao      roleNodeDao;
	private IFuncService      funcService;
	private IRoleService      roleService; 
	private IOptService       optService;


	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}


	public void setGroupDao(IGroupDao groupDao) {
		this.groupDao = groupDao;
	}
	public void setDepDao(IDepartmentDao depDao) {
		this.depDao = depDao;
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

	public void setFuncService(IFuncService funcService) {
		this.funcService = funcService;
	}


	public void setOptService(IOptService optService) {
		this.optService = optService;
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

	public Long createFunc(FuncVo funcVo) throws ServiceException {
		return funcService.createFunc(funcVo);
	}

	public List<FuncVo> findAllFuncs() throws ServiceException {		
		return funcService.findAllFuncs();
	}
	
	public FuncVo findFuncById(Long funcId) throws ServiceException {		
		return FuncServiceUtils.boToVo(funcService.findFuncById(funcId));
	}
	
	public ITreeModel findFuncTree() throws ServiceException {
		return funcService.findFuncTree();
	}
	
	public Long updateFunc(FuncVo funcVo) throws ServiceException {
		return funcService.updateFunc(funcVo);
	}
	
	public List<Func> findAllChildrenOfFunc(Func func) throws ServiceException {
		List children = new ArrayList();
		func=funcService.findFuncById(func.getId());			
		children.add(func);		
		funcService.findAllChildrenOfFunc(func, children);
		return children;
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
				if (optService.deleteOptOfFuncs(fchildren)){
					return  funcService.deleteFuncs(fchildren);
				}
				return false;
			}catch (ServiceException e) {
				e.printStackTrace();
				log.error(this.getClass().getName()+" deleteFuncAndChildren fail! "+e.getMessage());
				throw new ServiceException(this.getClass().getName()+" deleteFuncAndChildren(Func func) fail!");
			}	


		}
		return false;
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

	public List findOptsByFuncId(FuncVo funcVo) throws ServiceException {	
		return optService.findOptsByFuncId(funcVo.getId());		

	}
	public Operation findOperationById(long optId) throws ServiceException {
		Operation option = optService.findOperationById(optId);
		return option;
	}
	
	public Operation updateOperation(Operation opt) throws ServiceException {	
		return optService.updateOperation(opt);

	}
	
	public boolean deleteOperation(long optId) throws ServiceException {
		return optService.deleteOperation(optId);

	}
	
	public Operation createOperation(Operation opt) throws ServiceException {
		return optService.createOperation(opt);

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

	/* (non-Javadoc)
	 * @see com.nci.cp.core.sysmgt.service.ISysManagement#findUsers()
	 */
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
	
	public List findUsersByPage(Pagination page) throws ServiceException {
		try {
			List uservos = new ArrayList();
			// 分页查询 start
			Pagination pg = userDao.findEntitiesByPage(page);
			List<User> users = pg.getList();
			page.setTotalCount(pg.getTotalCount());
			// 分页查询 end
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
		} catch (Exception e) {
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
				// 设置创建时间
				user.setCreateDate(new Date());
				// 设置修改时间
				user.setModifyDate(new Date());
				user = (User)userDao.createEntity(user);
			} else {
				// 设置修改时间
				user.setModifyDate(new Date());
				user = (User)userDao.updateEntity(user);
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
	
	/* (non-Javadoc)
	 * @see com.nci.cp.core.sysmgt.service.ISysManagement#findGroupVos()
	 */
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

	/* (non-Javadoc)
	 * @see com.nci.cp.core.sysmgt.service.ISysManagement#findGroupVosByPage(com.nci.cp.ds.paging.Pagination)
	 */
	public List findGroupVosByPage(Pagination pagi) throws ServiceException {
		try {
			List groupvos = new ArrayList();
			//List<Group> groups = groupDao.findAllEntities();
			Pagination pg = groupDao.findEntitiesByPage(pagi);
			List<Group> groups = pg.getList();
			pagi.setTotalCount(pg.getTotalCount());
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
		} catch (Exception e) {
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

		DefaultServiceResult defsrv = new DefaultServiceResult();
		defsrv.setData(rolenodes);
		defsrv.setMessage(IServiceResult.SUCCESS_MESSAGE);
		defsrv.setStatus(IServiceResult.SUCCESS_STATUS);
		return defsrv ;
	}
   //取得功能树
	public IServiceResult obtainMenuTree() throws ServiceException {
        //
		ITreeModel tree = funcService.findFuncTree();
		List<TreeNode> level2 = tree.getNodesOfLevel(2);

		for(int i=0;i<level2.size();i++) {
			TreeNode node = (TreeNode)level2.get(i);
			List opts;

			opts = optService.findOptsByFuncId(node.getNodeid());
			if ((opts!=null)&&(opts.size()>0)) {
				for(int j=0;j<opts.size();j++) {
					Operation op = (Operation)opts.get(j);
					TreeNode cNd = new TreeNode();
					cNd.setParentid(op.getFuncId().longValue());
					cNd.setNodeid(op.getId().longValue());
					cNd.setNodename(op.getName());
					cNd.setLevel(3);
					tree.addNode(node.getNodeid(), cNd);
				}
			} 


		}

		DefaultServiceResult defsrv = new DefaultServiceResult();
		defsrv.setData(tree.getRoot());
		defsrv.setMessage(IServiceResult.SUCCESS_MESSAGE);
		defsrv.setStatus(IServiceResult.SUCCESS_STATUS);
		return defsrv ;

	}


	public IServiceResult obtainMenuRoot() throws ServiceException{
		TreeNode cNode  = funcService.findFuncTree().getRoot();
		DefaultServiceResult defsrv = new DefaultServiceResult();


		defsrv.setData(cNode);
		defsrv.setMessage(IServiceResult.SUCCESS_MESSAGE);
		defsrv.setStatus(IServiceResult.SUCCESS_STATUS);
		return defsrv ;
	}
	public IServiceResult useServiceByAction(BaseVo bsvo)throws ServiceException {	
		return roleService.useServiceByAction(bsvo);
	}
	
	public List findMenusOfUser(IUserInfo userinfo) throws ServiceException {
		/**
		 * 通过用户的id查找显示的菜单
		 */
		//角色列表
		List<Role> roles = null;
		//页面显示的树结点列表
		List<TreeNode> treenodes = new ArrayList<TreeNode>();
		
		Map<String, TreeNode> existNds  = new HashMap<String, TreeNode>();
		
		try {
			log.info(this.getClass().getName()+" use userinfo id: "+userinfo.getProperty(IUserInfo.USER_ID));
			//根据用户的Id，查找角色，得到角色列表
			roles = userDao.findRolesOfUser(userinfo);
			log.info(" findMenusOfUser(IUserInfo userinfo)  roles size: " + roles.size());
		} catch (DaoException e) {
			e.printStackTrace();
			log.error(this.getClass().getName()+" findMenusOfUser(IUserInfo userinfo) fail! "+e.getMessage());
			throw new ServiceException(this.getClass().getName()+" findMenusOfUser(IUserInfo userinfo) fail!");
		}

		if (roles.size() > 0) {
			for(int i = 0; i < roles.size(); i++) {
				Role role = (Role)roles.get(i);  
				log.info("当前处理的角色名称为：" + role.getName() + "(" + role.getId() + ")");
				//取得当前用户的角色列表
				Set<RoleNode> nds = role.getRoleNodes();
				log.info("user nodes is not null,then retrieve all nodes. nodes: "+nds.size());
				if (nds.size() > 0) {
					//取得功能树所有结点
					ITreeModel tree =  funcService.findFuncTree();
					if (tree==null) {
						log.error("======the root of func tree is null ");
						throw new ServiceException("the tree is null");
					}else{
						log.info("=======the root of func tree is not null ");
					}
					TreeNode rootnd = funcService.findFuncTree().getRoot();
					log.info("func tree root node is : "+rootnd.getNodename());
					//把根放到用户树菜单里
					if (!existNds.containsKey("\"" + rootnd.getNodeid() + "\"")){
						existNds.put("\"" + rootnd.getNodeid() + "\"", rootnd);
						treenodes.add(rootnd);  
					}

					Iterator<RoleNode> it  = nds.iterator();
					while(it.hasNext()) {
						RoleNode rd = (RoleNode)it.next();            		
						if ((!rd.getNodeType().equals(RoleNode.ROLE_NODE_OPERATION_TYPE))
								&&(!existNds.containsKey("\"" + rd.getNodeId().longValue() + "\""))) {            		   

							Func func = funcService.findFuncById(rd.getNodeId());
							if (func==null) continue;
							TreeNode td = new TreeNode();
							td.setNodeid(rd.getNodeId());
							td.setData(func.getFuncaction());
							if (rd.getParentNo().longValue() == 0){
								td.setParentid(-1);
							}else{
								td.setParentid(rd.getParentNo());
							}
							log.info("加入的菜单为" + func.getFuncname() + "(" + rd.getNodeId() + ")");
							td.setNodename(func.getFuncname());
							treenodes.add(td);
							existNds.put("\"" + td.getNodeid() + "\"", td);            		 
						}
					}
				}        	  
			}

		}

		return treenodes;
	}


	/* (non-Javadoc)
	 * @see com.nci.cp.core.sysmgt.service.IRoleService#findAllRoleVos()
	 */
	public List<RoleVo> findAllRoleVos() throws ServiceException {
		return roleService.findAllRoleVos();
	}
	
	/* (non-Javadoc)
	 * @see com.nci.cp.core.sysmgt.service.IRoleService#findAllRoleVos(com.nci.cp.ds.paging.Pagination)
	 */
	public List<RoleVo> findAllRoleVos(Pagination pagi) throws ServiceException {
		return roleService.findAllRoleVos(pagi);
	}

	public RoleVo findRoleById(long roleid) throws ServiceException{
		return roleService.findRoleById(roleid);
	}
	
	/* (non-Javadoc)
	 * @see com.nci.cp.core.sysmgt.service.ISysManagement#modifyPassWord(com.nci.cp.core.sysmgt.vo.UserVo, java.lang.String)
	 */
	public String modifyPassWord(UserVo userVo, String newPassWord) throws ServiceException {
		String checkedPassWord = userVo.getPassword();
		User user = new User();
		try {
			BeanUtils.copyProperties(user, userVo);
			// 验证该用户密码
			user = (User) userDao.findEntityById(user);
			String oldPassWord = user.getPassword();
			if (oldPassWord.equals(checkedPassWord)) { // 如果校验通过，则更新密码
				user.setPassword(newPassWord);
				IdEntity idEntity = userDao.updateEntity(user);
				return String.valueOf(idEntity.getEntityId());
			} else { // 如果不通过
				return "false";
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(this.getClass().getName()+" modifyPassWord(UserVo userVo, String newPassWord) fail! "+e.getMessage());
			throw new ServiceException(this.getClass().getName()+" modifyPassWord(UserVo userVo, String newPassWord) fail!");
		} 
	}

	
	/* (non-Javadoc)
	 * @see com.nci.cp.core.sysmgt.service.IRoleService#findAllRoleVos()
	 */
	
	
	/*public List<User> getAllUser()  throws ServiceException {
		try {
			List urs = userDao.findAllEntities(); 
			if ((urs!=null)&&(urs.size()>0))
				return (User)urs.get(0) ;
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}*/
	public Collection fndAllDep(String orgBCFL) throws ServiceException{
		return userDao.fndAllDep(orgBCFL);
	}
	
	 public OrgVO_Department fndDepartment(String code) {
		 return userDao.fndDepartment(code);
	 }
	 public OrgVO_Corp findCorpByCode(String corpCode) {
		   return userDao.findCorpByCode(corpCode);
	 }
	 
	   public OrgVO_Corp saveCorp(OrgVO_Corp orgVO_Corp) {
		   return userDao.saveCorp(orgVO_Corp);

	   }
}
