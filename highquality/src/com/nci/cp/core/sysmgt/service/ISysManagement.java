package com.nci.cp.core.sysmgt.service;

import java.util.Collection;
import java.util.List;

import com.nci.cp.core.exception.ServiceException;
import com.nci.cp.core.model.BaseVo;
import com.nci.cp.core.service.IService;
import com.nci.cp.core.sysmgt.model.Func;
import com.nci.cp.core.sysmgt.model.IUserInfo;
import com.nci.cp.core.sysmgt.model.Operation;
import com.nci.cp.core.sysmgt.model.OrgVO_Corp;
import com.nci.cp.core.sysmgt.model.OrgVO_Department;
import com.nci.cp.core.sysmgt.vo.DepartmentVo;
import com.nci.cp.core.sysmgt.vo.FuncVo;
import com.nci.cp.core.sysmgt.vo.GroupVo;
import com.nci.cp.core.sysmgt.vo.UserVo;
import com.nci.cp.ds.paging.Pagination;
import com.nci.cp.ds.tree.ITreeModel;

/**
 * The interface is system management service interface.
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-5-18 
 */
public interface ISysManagement extends IService, IRoleService {
	public static String FUNC_SERVICE_ROOT = "root";
	public static String FUNC_SERVICE_SECOND = "secondlevel";

	
    public FuncVo findFuncById(Long funcId)throws ServiceException;
	
	public List<FuncVo> findAllFuncs() throws ServiceException;
	
	public Long createFunc(FuncVo funcVo) throws ServiceException;

	public Long updateFunc(FuncVo funcVo) throws ServiceException;

	public ITreeModel  findFuncTree() throws ServiceException;
	
	/**
	 * @param usrinfo
	 * @return
	 * @throws ServiceException
	 */
	public IUserInfo authentication(IUserInfo usrinfo) throws ServiceException;	

	public List<Func> findAllChildrenOfFunc(Func func) throws ServiceException;	

	public boolean deleteFuncAndChildren(long funcid) throws ServiceException;
	
	public List findOptsByFuncId(FuncVo funcVo) throws ServiceException ;
	
	public Operation createOperation(Operation opt) throws ServiceException;
	
	public Operation findOperationById(long optId) throws ServiceException;
	
	public Operation updateOperation(Operation opt) throws ServiceException;
	
	public boolean deleteOperation(long optId) throws ServiceException;
    
    public DepartmentVo findDepById(DepartmentVo depVo)throws ServiceException;
	
	public List findDeps() throws ServiceException;
	
	public DepartmentVo createOrUpdateDepartment(DepartmentVo depVo)throws  ServiceException;
	
	public boolean deleteDep(DepartmentVo depVo) throws ServiceException;
	
	public List findUsers() throws ServiceException;
	
	public UserVo findUserById(UserVo userVo)throws ServiceException;
	
	public UserVo createOrUpdateUser(UserVo userVo) throws ServiceException;
	
	public boolean deleteUser(UserVo userVo) throws ServiceException;
    
	/**
	 * 查询所有用户组集合
	 * @return
	 * @throws ServiceException
	 */
	public List findGroupVos() throws ServiceException;
	
	/**
	 * 分页查询用户组集合
	 * @param pagi
	 * @return
	 * @throws ServiceException
	 */
	public List findGroupVosByPage(Pagination pagi) throws ServiceException;
	
	public GroupVo findGroupById(GroupVo groupVo)throws ServiceException;
	
	public GroupVo createOrUpdateGroup(GroupVo groupVo) throws ServiceException;
	
	public boolean deleteGroup(GroupVo groupVo) throws ServiceException;
	
	public IServiceResult obtainMenuTree() throws ServiceException;
	
	public IServiceResult obtainMenuTree(BaseVo bsvo) throws ServiceException;
	
	public IServiceResult obtainMenuRoot() throws ServiceException;
	
	public List findMenusOfUser(IUserInfo userinfo) throws ServiceException ;
	/** role operation *//*
    public List findRoleVos() throws ServiceException;
	
	public GroupVo findRoleById(RoleVo RoleVo)throws ServiceException;
	
	public GroupVo createOrUpdateGroup(GroupVo groupVo) throws ServiceException;
	
	public boolean deleteGroup(GroupVo groupVo) throws ServiceException;*/
	
	public List findUsersByPage(Pagination page) throws ServiceException;
	
	/**
	 * 修改用户密码
	 * 修改成功返回实体id，不成功返回"false"
	 * @param userVo
	 * @param newPassWord
	 * @return
	 * @throws ServiceException
	 */
	public String modifyPassWord(UserVo userVo, String newPassWord) throws ServiceException;
	public Collection fndAllDep(String orgBCFL) throws ServiceException;

	public OrgVO_Department fndDepartment(String code) ;
	 public OrgVO_Corp findCorpByCode(String corpCode) ;
	   public OrgVO_Corp saveCorp(OrgVO_Corp orgVO_Corp) ;
}
