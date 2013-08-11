package com.nci.cp.ds.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    protected long    nodeid;    
    protected String  nodename;
    protected long    parentid;
    protected int     level=0;
    protected Object  data;
    protected boolean selected=false;
    protected List   children=new ArrayList();
	
	
    
	public long getNodeid() {
		return nodeid;
	}
	public void setNodeid(long nodeid) {
		this.nodeid = nodeid;
	}
	public long getParentid() {
		return parentid;
	}
	public void setParentid(long parentid) {
		this.parentid = parentid;
	}
	public String getNodename() {
		return nodename;
	}
	public void setNodename(String nodename) {
		this.nodename = nodename;
	}	
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public void addNode(TreeNode node) {
		children.add(node);
	}
	public TreeNode getNode(int order) {
		return (TreeNode)children.get(order);
	}
    public int childrenSize() {
    	return children.size();
    }
	public List getChildren() {
		return children;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public boolean equals(Object obj) {
		TreeNode n = (TreeNode)obj;
		if ((this.nodeid==n.getNodeid())&&(this.parentid==n.getParentid())) 
			return true;
		
		return false;
	}
    
}
