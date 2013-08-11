package com.nci.cp.core.sysmgt.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nci.cp.core.exception.ServiceException;
import com.nci.cp.core.sysmgt.model.Role;
import com.nci.cp.core.sysmgt.service.IRoleService;
import com.nci.cp.core.sysmgt.service.IServiceResult;
import com.nci.cp.core.sysmgt.service.ISysManagement;
import com.nci.cp.core.sysmgt.vo.RoleVo;
import com.nci.cp.core.web.AbstractAction;
import com.nci.cp.core.web.IAction;
import com.nci.cp.ds.paging.Pagination;
import com.nci.cp.ds.tree.TreeNode;

/**
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-8-29 
 */
public class RoleManagerAction extends AbstractAction {
	
	private static Log log = LogFactory.getLog(RoleManagerAction.class);
	
	private ISysManagement sysmgmtService;
	
	private RoleVo roleVo;
	
	public Pagination pagi;
	
	public List<RoleVo> roleVos;

	public void setSysmgmtService(ISysManagement sysmgmtService) {
		this.sysmgmtService = sysmgmtService;
	}
    
	public RoleVo getRoleVo() {
		return roleVo;
	}

	public void setRoleVo(RoleVo roleVo) {
		this.roleVo = roleVo;
	}

	/**
	 * 获取角色列表
	 * @return
	 * @throws Exception
	 */
	public String rolesMain() throws Exception {
		if (pagi == null) {
			pagi = new Pagination();
		}
		roleVos = sysmgmtService.findAllRoleVos(pagi);
		return SUCCESS;
	}             

//	@Deprecated
//    public List getRoleVos() throws Exception {    	
//		return sysmgmtService.findAllRoleVos();
//	}
    
    public String addRole() throws ServiceException {
    	IServiceResult isr = sysmgmtService.obtainMenuTree();
    	roleVo = new RoleVo();
    	roleVo.setNode((TreeNode)isr.getResultData());
		return SUCCESS;
	}
    
    @Override
	public String initedit() throws Exception {
    	/**
    	 * 使用结果封装的方式调用服务
    	 * 
    	 * 
    	 */
    	//调用取得角色的菜单树服务
    	IServiceResult isr =sysmgmtService.obtainMenuTree(roleVo);
    	//调用取得总的菜单树服务
    	IServiceResult israll=sysmgmtService.obtainMenuTree();
    	RoleVo rvo = new RoleVo();
    	rvo.setId(roleVo.getId());
    	rvo.setAction(IRoleService.ACTION_FIND_ROLE_BY_ID);
    	//调用取得角色对象服务
    	IServiceResult isrrole =  sysmgmtService.useServiceByAction(rvo);
    	//如果调用的三个服务都成功，都进入处理业务
		if ((isr.isSuccess())&&(israll.isSuccess())&&(isrrole.isSuccess())) {
			//取得角色菜单结点列表
			List nodes = (List)isr.getResultData();
			//
			roleVo.setNode((TreeNode)israll.getResultData());
			selectedTreeNodes(roleVo.getNode(),nodes);
	
			Role role =(Role)isrrole.getResultData();
			roleVo.setId(role.getId());
			roleVo.setName(role.getName());
			roleVo.setDescr(role.getDescr());
			
		}
		return super.initedit();
	}

	public String saveRole() throws Exception{
		
    	if (roleVo.getAction().equals(IAction.ACTION_SAVE)) {    		
    	  roleVo.setAction(IRoleService.ACTION_CREATE_ROLE);		
		  IServiceResult sr =this.sysmgmtService.useServiceByAction(roleVo);
    	}else if(roleVo.getAction().equals(IAction.ACTION_EDIT)){
    	  roleVo.setAction(IRoleService.ACTION_UPDATE_ROLE);		
  		  IServiceResult sr =this.sysmgmtService.useServiceByAction(roleVo);    		
    	}
    	return SUCCESS;
    }
    private void selectedTreeNodes(TreeNode treenode,List selected) {
        if (isContainTreeNode(selected,treenode))
        	treenode.setSelected(true);

    	if (treenode.childrenSize()>0) {
    		for(int i=0;i<treenode.childrenSize();i++) {
    			TreeNode node = (TreeNode)treenode.getNode(i);
    			selectedTreeNodes(node,selected);
    		}
    		
    	}
    }
    
    @Override
	public String delete() throws Exception {
    	roleVo.setAction(IRoleService.ACTION_DELETE_ROLE);
    	IServiceResult sr =this.sysmgmtService.useServiceByAction(roleVo);
		return super.delete();
	}

	private boolean isContainTreeNode(List selected,TreeNode node) {
    	for(int i=0;i<selected.size();i++) {
    		TreeNode td = (TreeNode)selected.get(i);
    		//System.out.println("================ td.node");
    		if ((td.getNodeid()==node.getNodeid())&&(td.getParentid()==node.getParentid())) 
    		  	return true;
    	}
    	return false;
    }

	public Pagination getPagi() {
		return pagi;
	}

	public void setPagi(Pagination pagi) {
		this.pagi = pagi;
	}

	public List<RoleVo> getRoleVos() {
		return roleVos;
	}

	public void setRoleVos(List<RoleVo> roleVos) {
		this.roleVos = roleVos;
	}
   
}
