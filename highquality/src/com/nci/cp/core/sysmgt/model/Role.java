package com.nci.cp.core.sysmgt.model;

import java.util.HashSet;
import java.util.Set;

import com.nci.cp.core.model.AbstractEntity;
/**
 * The object is to storage role info.
 * @company BlueCreation Workspace
 * @author  Oliver Chan 
 * @version 0.1
 * @date    2011-08-28 
 */
public class Role extends AbstractEntity {
	
	protected String name;
	protected String descr;
    protected java.util.Date createDate;	
	protected java.util.Date modifyDate; 
	protected Set<RoleNode> roleNodes = new HashSet<RoleNode>();
	
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
	
	public java.util.Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	public java.util.Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Set<RoleNode> getRoleNodes() {
		return roleNodes;
	}
	public void setRoleNodes(Set<RoleNode> roleNodes) {
		this.roleNodes = roleNodes;
	}
	public void add(RoleNode rNode) {
		
		roleNodes.add(rNode);
	}
}
