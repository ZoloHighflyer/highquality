package com.nci.cp.core.sysmgt.vo;

import java.util.List;

import com.nci.cp.core.model.DefaultVo;
import com.nci.cp.ds.tree.TreeNode;
/**
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-8-29 
 */
public class RoleVo extends DefaultVo {	
	protected Long     id;
	protected String   name;
	protected String   descr;
	protected TreeNode node;
	protected List     roleNodes;
	protected boolean  selected=false;
	
	
	public RoleVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
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
	public TreeNode getNode() {
		return node;
	}
	public void setNode(TreeNode node) {
		this.node = node;
	}
	public List getRoleNodes() {
		return roleNodes;
	}
	public void setRoleNodes(List roleNodes) {
		this.roleNodes = roleNodes;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
    

	
}
