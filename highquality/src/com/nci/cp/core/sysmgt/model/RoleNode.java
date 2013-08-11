package com.nci.cp.core.sysmgt.model;

import com.nci.cp.core.model.AbstractEntity;
/**
 * The object is to storage role info.
 * @company BlueCreation Workspace
 * @author  Oliver Chan 
 * @version 0.1
 * @date    2011-08-28 
 */
public class RoleNode extends AbstractEntity {
	
	public  static Integer   ROLE_NODE_FUNC_TYPE=0;     //左边菜单类型
	public  static Integer   ROLE_NODE_OPERATION_TYPE=1;//增删改查的操作类型
    protected  Integer nodeType;
    protected  String  name;
    protected  Long    parentNo;
    protected  Long    nodeId;
    protected  Role    role;
    protected java.util.Date createDate; 	
	protected java.util.Date modifyDate; 
	
	public Integer getNodeType() {
		return nodeType;
	}
	public void setNodeType(Integer nodeType) {
		this.nodeType = nodeType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getParentNo() {
		return parentNo;
	}
	public void setParentNo(Long parentNo) {
		this.parentNo = parentNo;
	}
	public Long getNodeId() {
		return nodeId;
	}
	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
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
	
    
    
}
