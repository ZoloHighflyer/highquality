package com.nci.cp.ds.tree;

import java.util.List;

public abstract class Traversal implements ITraversal {

	public Traversal() {
		super();
		
	}
	public void traversal(TreeNode node) {
		invoke(node);
		if (node.childrenSize()>0) {
			List children = node.getChildren();
			for(int i=0;i<children.size();i++) {
				TreeNode child = (TreeNode)children.get(i);
				traversal(child);
			}
			
		}

	}
    public abstract void invoke(TreeNode curNode);
}
