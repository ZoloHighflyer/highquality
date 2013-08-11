package com.nci.cp.core.sysmgt.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.nci.cp.core.sysmgt.model.IUserInfo;
import com.nci.cp.core.sysmgt.service.ISysManagement;
import com.nci.cp.core.utils.Contants;
import com.nci.cp.core.utils.WebUtils;
import com.nci.cp.core.web.AbstractAction;
/**
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-9-14 
 */
public class UserMenuAction extends AbstractAction {
	
	private IUserInfo user;
	
	private ISysManagement sysmgmtService;
	
	private Logger logger = Logger.getLogger(UserMenuAction.class);
	
    public void setSysmgmtService(ISysManagement sysmgmtService) {
		this.sysmgmtService = sysmgmtService;
	}

	public String usermenu() throws Exception {
    	user = (IUserInfo)WebUtils.getSessionAttribute(Contants.LOGON_USER_KEY);
    	return SUCCESS;
    }
	
	public List getUserNodes()  throws Exception {
		//通过用户的标识，查找用户关联的菜单
		List nodes = sysmgmtService.findMenusOfUser(user);
		logger.info("nodes size: " + nodes.size());
		return nodes;
	}
}
