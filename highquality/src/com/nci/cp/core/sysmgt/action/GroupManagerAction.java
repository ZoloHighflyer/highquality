package com.nci.cp.core.sysmgt.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nci.cp.core.exception.ServiceException;
import com.nci.cp.core.sysmgt.service.ISysManagement;
import com.nci.cp.core.sysmgt.vo.GroupVo;
import com.nci.cp.core.sysmgt.vo.RoleVo;
import com.nci.cp.core.sysmgt.vo.UserVo;
import com.nci.cp.core.utils.WebUtils;
import com.nci.cp.core.web.AbstractAction;
import com.nci.cp.core.web.IAction;
import com.nci.cp.ds.paging.Pagination;
/**
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-8-24 
 */
public class GroupManagerAction extends AbstractAction {

	private static Log log = LogFactory.getLog(GroupManagerAction.class);

	private GroupVo  groupVo;

	private ISysManagement sysmgmtService;

	private Pagination pagi;

	private List<GroupVo> groupVos;

	public void setSysmgmtService(ISysManagement sysmgmtService) {
		this.sysmgmtService = sysmgmtService;
	}

	public GroupVo getGroupVo() {
		return groupVo;
	}

	public void setGroupVo(GroupVo groupVo) {
		this.groupVo = groupVo;
	}

	/**
	 * 获取用户组列表
	 * @return
	 * @throws ServiceException
	 */
	public String groupsMain() throws ServiceException {	
		if (pagi == null) {
			pagi = new Pagination();
		}
		groupVos = sysmgmtService.findGroupVosByPage(pagi);
		return SUCCESS;
	}

//	public List getGroupVos() throws ServiceException {
//		return sysmgmtService.findGroupVos();
//	}
	
	public String addGroup() throws ServiceException {	        
		List users =  sysmgmtService.findUsers();
		RoleVo rv = new RoleVo();		   
		groupVo = new GroupVo();			
		groupVo.setRoles(sysmgmtService.findAllRoleVos());	       
		groupVo.setUsers(users);
		return SUCCESS;
	}
	
	public String saveGroup() throws Exception{
		List us = new ArrayList();
		List users = groupVo.getUsers();
		us.addAll(users);           

		groupVo.setUsers(new ArrayList());
		for (int i=0;i<us.size();i++) {
			String userid=(String)us.get(i);
			UserVo userVo = new UserVo();			  
			userVo.setId(new Long(Long.parseLong(userid)));				 
			groupVo.getUsers().add(userVo);

		}
		List rs = new ArrayList();
		List roles = groupVo.getRoles();
		if (roles!=null)
			rs.addAll(roles);

		groupVo.setRoles(new ArrayList());
		for (int i=0;i<rs.size();i++) {
			String roleid = (String)rs.get(i);
			RoleVo rolevo = new RoleVo();
			rolevo.setId(new Long(Long.parseLong(roleid)));
			groupVo.getRoles().add(rolevo);
		}



		groupVo = sysmgmtService.createOrUpdateGroup(groupVo);	
		msg="selected";
		WebUtils.getServletRequest().setAttribute(IAction.ACTION_MESSAGE, "success");
		return SUCCESS;
	}
	
	public String initedit() throws Exception {
		if(groupVo.getId() != null){
			groupVo=sysmgmtService.findGroupById(groupVo);
			List allusers = sysmgmtService.findUsers();
			List seusers = groupVo.getUsers();
			Map  semap = new HashMap();
			for (int i=0;i<seusers.size();i++) 
				semap.put(((UserVo)seusers.get(i)).getId().toString(),seusers.get(i));
			for(int i=0;i<allusers.size();i++) {
				if (semap.containsKey(((UserVo)allusers.get(i)).getId().toString())) {
					UserVo uvo = (UserVo)allusers.get(i);
					uvo.setSelected(true);
				}
			}
			groupVo.setUsers(allusers);

			RoleVo rv = new RoleVo();
			//find all rolevo

			List allrolevos = this.sysmgmtService.findAllRoleVos();
			Map selrolesmap  = new HashMap();
			List selectedroles = groupVo.getRoles();
			for(int i=0;i<selectedroles.size();i++) 
				selrolesmap.put(((RoleVo)selectedroles.get(i)).getId().toString(), selectedroles.get(i));

			for (int i=0;i<allrolevos.size();i++)
				if (selrolesmap.containsKey(((RoleVo)allrolevos.get(i)).getId().toString())) 
					((RoleVo)allrolevos.get(i)).setSelected(true);



			groupVo.setRoles(allrolevos);
		}


		return super.initedit();
	}	
	public String delete() throws Exception {
		if (groupVo.getId() != null) {
			sysmgmtService.deleteGroup(groupVo);
		}
		return super.delete();
	}

	public Pagination getPagi() {
		return pagi;
	}

	public void setPagi(Pagination pagi) {
		this.pagi = pagi;
	}

	public List<GroupVo> getGroupVos() {
		return groupVos;
	}

	public void setGroupVos(List<GroupVo> groupVos) {
		this.groupVos = groupVos;
	}

}
