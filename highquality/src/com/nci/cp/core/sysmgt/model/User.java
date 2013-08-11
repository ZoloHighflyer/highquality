package com.nci.cp.core.sysmgt.model;

import java.sql.Date;

import com.nci.cp.core.model.AbstractEntity;
/**
 * The interface for user.
 * @company BlueCreation Workspace
 * @author  yanfeng 
 * @version 0.1
 * @date    2011-08-04 
 */
public class User extends AbstractEntity implements IUserInfo {
	
	private String username;
	
	private String password;
	
	private String userid;
	
	public User() {
		super();		
	}

	/**
	 * @param userId
	 * @param username
	 * @param password
	 */
	public User(String userid, String username, String password) {
		this.userid = userid;
		this.username = username;
		this.password = password;
	}	
    
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	/**
	 * @param propertyName the propertyName 
	 * @return the propertyValue
	 */
	public Object getProperty(String propertyName){	
		try {
			return this.getClass().getDeclaredField(propertyName).get(this);
		} catch (IllegalArgumentException e) {			
			e.printStackTrace();
		} catch (IllegalAccessException e) {			
			e.printStackTrace();
		} catch (NoSuchFieldException e) {			
			e.printStackTrace();
		}		
		return null;		
	}
	/**
	 * @param propertyName the propertyName
	 * @param propertyValue the propertyValue
	 */
	public void setProperty(String propertyName, Object propertyValue){
		
		try {
			this.getClass().getDeclaredField(propertyName).set(this, propertyValue);
		} catch (IllegalArgumentException e) {			
			e.printStackTrace();
		} catch (SecurityException e) {			
			e.printStackTrace();
		} catch (IllegalAccessException e) {		
			e.printStackTrace();
		} catch (NoSuchFieldException e) {			
			e.printStackTrace();
		}
	}
	public static void main(String args[]){
		User u = new User();
		u.setProperty("username", "yanfeng==");
		System.out.println("===== "+u.getProperty("username"));
	}
   
}
