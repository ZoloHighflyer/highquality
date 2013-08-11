package com.nci.cp.core.sysmgt.vo;

import java.util.ArrayList;
import java.util.List;

import com.nci.cp.core.model.BaseVo;
/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-8-24 
 */
public class GroupVo extends BaseVo {
	protected Long           id;
	private   String         name;
	private   String         descr;
	private   List           users = new ArrayList();
	private   List           roles ;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public List getUsers() {
		return users;
	}
	public void setUsers(List users) {
		this.users = users;
	}
	public List getRoles() {
		return roles;
	}
	public void setRoles(List roles) {
		this.roles = roles;
	}
	
	
}
