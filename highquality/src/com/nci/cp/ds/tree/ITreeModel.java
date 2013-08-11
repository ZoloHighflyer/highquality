package com.nci.cp.ds.tree;

import java.util.List;

/**
 * @target  the interface is for tree node.
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.2
 * @date    2011-9-20 
 */
public interface ITreeModel {
	public TreeNode getRoot();
	public List     getChildrenOfNode(long nodeid);
	public TreeNode getNode(long nodeid) ;
	public List<TreeNode> getNodesOfLevel(int level);
	public void     addNode(long parentid,TreeNode node);
	public void     addNode(long parentid,TreeNode node,int order);
	public void     remove(long nodeid);
	public void     empty();
	public boolean  isContain(long nodeid);
	public List<TreeNode> toList();
	public void     traversal();
}
