package com.nci.cp.core.web;

import com.nci.cp.core.sysmgt.model.IUserInfo;

/**
 * @company BlueCreation Workspace
 * @author  yanfeng 
 * @version 0.1
 * @date    2011-8-11 
 */
public interface IUserInfoValidation {
	
	boolean validate(IUserInfo userInfo);
}
