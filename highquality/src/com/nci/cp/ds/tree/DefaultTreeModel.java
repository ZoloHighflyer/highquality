package com.nci.cp.ds.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @target  the interface is for tree implement.
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.2
 * @date    2011-9-21 
 */
public class DefaultTreeModel implements ITreeModel {
    protected TreeNode root=null;    
    protected Map      nodes = new HashMap();
	
	public DefaultTreeModel(TreeNode root) {
		super();
		this.root = root;
	}


	public List getChildrenOfNode(long nodeid) {
		TreeNode curNode = getNode(nodeid);
		if (curNode!=null) 
			return curNode.getChildren();
		return new ArrayList();
	}

	public TreeNode getRoot() {
			return root;
	}
    

	public TreeNode getNode(long nodeid) {
		List nodes = new ArrayList();
		search(root, nodes, nodeid);
		if(nodes.size()>0) {
			TreeNode curNode = (TreeNode)nodes.get(0);
			return curNode;
		}
		return null;
	}
	
	public void empty() {
		this.root=null;		
	}


	public void addNode(long parentid, TreeNode node) {
		TreeNode parNode = getNode(parentid);		
		if (parNode!=null) {
			node.setParentid(parNode.getNodeid());
			node.setLevel(parNode.getLevel()+1);
			parNode.getChildren().add(node);
		}		
		
	}
    

	public void addNode(long parentid, TreeNode node, int order) {
		TreeNode parNode = getNode(parentid);		
		if (parNode!=null) {
			node.setParentid(parNode.getNodeid());
			node.setLevel(parNode.getLevel()+1);
			parNode.getChildren().add(order,node);
		}				
	}


	public boolean isContain(long nodeid) {
		List nodes = new ArrayList();
		search(root, nodes, nodeid);
		if (nodes.size()>0) 
			 return true;
		return false;
	}
    
	

	public List<TreeNode> getNodesOfLevel(int level) {
		List<TreeNode> nodes  = new ArrayList<TreeNode>();
		searchNodesOfLevel(getRoot(), nodes, level);
		return nodes;
	}
    
	private void searchNodesOfLevel(TreeNode parent,List<TreeNode> list,int level) {	
		if (parent.getLevel()==level) {
			list.add(parent);
			
		}
		if (parent.childrenSize()>0) {
    		for(int i=0;i<parent.childrenSize();i++) {
    			TreeNode node = (TreeNode)parent.getNode(i);    			
    			searchNodesOfLevel(node,list,level);
    		}
    		
    	}
	}

	public void remove(long nodeid) {
	    TreeNode node = getNode(nodeid);		
		if (node!=null) {
			if (node.getParentid()!=-1) {
				TreeNode pNode = getNode(node.getParentid());
				List children = pNode.getChildren();
				for(int i=0;i<children.size();i++) {
					TreeNode child = (TreeNode)children.get(i);
					if (child.getNodeid()==nodeid) {
						children.remove(i);
						break;
					}						
				}
			}else {
				empty();
			}
		}		
	}
	
   
	
	public List<TreeNode> toList() {
		ITraversal tr  = new TraversalNode();
		tr.traversal(getRoot());
		return (List)tr.getData();
	}


	public void traversal() {
		List nodes = toList();
		for (int i=0;i<nodes.size();i++){
			 TreeNode td  = (TreeNode)nodes.get(i);
			 System.out.println("==== node id: "+td.getNodeid());
		}		
	}
    
    

	private void search(TreeNode parent,List<TreeNode> list,long nodeid) {	
		if (parent.getNodeid()==nodeid) {
			list.add(parent);
			return;
		}
		if (parent.childrenSize()>0) {
    		for(int i=0;i<parent.childrenSize();i++) {
    			TreeNode node = (TreeNode)parent.getNode(i);    			
    			search(node,list,nodeid);
    		}
    		
    	}
	}

}
