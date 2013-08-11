package com.nci.cp.core.sysmgt.model;
/**
 * The interface for user.
 * @company BlueCreation Workspace
 * @author  yanfeng 
 * @version 0.1
 * @date    2011-08-04 
 */
public interface IUserInfo {
	public static String USER_ID = "userid";
	public static String USER_NAME = "username";
	public static String USER_PASS = "password";
	public static String USER_ROLE = "role";
	Object getProperty(String propertyName);

}
