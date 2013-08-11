package com.nci.cp.ds.tree;

import java.util.ArrayList;
import java.util.List;

public  class TraversalNode extends Traversal{
	protected List nodes = new ArrayList();
    public TraversalNode() {    	
		super();		
	}	
    
	@Override
	public void invoke(TreeNode curNode) {
		nodes.add(curNode);
		
	}

	public Object getData() {
		return nodes;
	}
    

}
