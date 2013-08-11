package com.nci.cp.core.sysmgt.service;

import java.util.List;

import com.nci.cp.core.exception.ServiceException;
import com.nci.cp.core.model.BaseVo;
import com.nci.cp.core.service.IService;
import com.nci.cp.core.sysmgt.vo.RoleVo;
import com.nci.cp.ds.paging.Pagination;
/**
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-8-29 
 */
public interface IRoleService extends IService {

	public static String ACTION_FIND_ALL_ROLES="_find_all_roles_action";

	public static String ACTION_CREATE_ROLE="_create_role_action";

	public static String ACTION_UPDATE_ROLE="_update_role_action";

	public static String ACTION_DELETE_ROLE="_delete_role_action";

	public static String ACTION_FIND_ROLE_BY_ID="_find_role_by_id";

	public IServiceResult useServiceByAction(BaseVo bsvo) throws ServiceException ;

	/**
	 * 获取所有角色
	 * @return
	 * @throws ServiceException
	 */
	public List<RoleVo> findAllRoleVos() throws ServiceException;
	
	/**
	 * 分页获取所有角色
	 * @param pagi
	 * @return
	 * @throws ServiceException
	 */
	public List<RoleVo> findAllRoleVos(Pagination pagi) throws ServiceException;

	public RoleVo findRoleById(long roleid) throws ServiceException;


}
