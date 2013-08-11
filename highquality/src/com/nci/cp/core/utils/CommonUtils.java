package com.nci.cp.core.utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.nci.cp.ds.tree.DefaultTreeModel;
import com.nci.cp.ds.tree.ITreeModel;
import com.nci.cp.ds.tree.TreeNode;

/**
 * The class is common util 
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2010-12-25 
 */
public final class CommonUtils {
     public static String formatDate(Date source,SimpleDateFormat format) {
    	 return format.format(source);
     }
     public static String formatDate(Timestamp source,SimpleDateFormat format) {
    	 return format.format(source);
     }
     public static void setRequestParameter(String key,Object value) {
    	 HttpServletRequest req=ServletActionContext.getRequest();
    	
    	 req.setAttribute(key, value);
     }
     public static void setSessionParameter(String key,Object value) {
    	 HttpSession session =ServletActionContext.getRequest().getSession();
    	 session.setAttribute(key, value);
     }
     public static void saveFile(File file,String location,String fileName) throws IOException  {
    	    File target = new File(location,fileName);            
	        FileUtils.copyFile(file, target);  
     }
     public static String dateStr(Date date){
    	 SimpleDateFormat format = new SimpleDateFormat("yyyyMMddmmssSSS");
 		
    	 return format.format(date);
     }
     public static String dateStr(Date date,String formate){
    	 SimpleDateFormat format = new SimpleDateFormat(formate);
    	 return format.format(date);
     }
     public static long convertStrToLong(String num) {
    	 try {
    		 long l = Long.parseLong(num);
    		 return l;    		 
    	 }catch (Exception ex) {
    		 
    	 }
    	 return 0;
     }
    
     public static ITreeModel listToTree(List<TreeNode> nodeList) throws Exception{
    	    List<TreeNode> nodes = nodeList;
			//trieval legal node list
			List<TreeNode> legalnodes = new ArrayList();
			//trieval parent of node
			Map  selectedNodes = new HashMap();
			//find root of tree
			for (TreeNode n:nodes) {
				if ((n.getParentid()==-1)||(n.getParentid()==0)){
					legalnodes.add(n);
					selectedNodes.put("\""+n.getNodeid()+"\"", n);
					nodes.remove(n);
					break;
				}
			}
			
			if (legalnodes.size()>0) {
			
			boolean consel = true;
			while (consel) {
				int max=nodes.size();
				consel = false;
				List del = new ArrayList();
				for (int i=0;i<max;i++) {
					TreeNode td = (TreeNode)nodes.get(i);
					String pid="\""+td.getParentid()+"\"";
					if (selectedNodes.containsKey(pid)){
						legalnodes.add(td);
						selectedNodes.put("\""+td.getNodeid()+"\"", td);
						del.add(td);						
						consel=true;
					}					
					
				}
				//delete legal nodes in the inital list
				for(int i=0;i<del.size();i++) {
					nodes.remove(del.get(i));
				}
				
			}
			
			}else 
				throw new Exception("no root node in the list");
			
			TreeNode root =(TreeNode)legalnodes.get(0);
			root.setParentid(-1);
			ITreeModel tree = new DefaultTreeModel(root);
			legalnodes.remove(0);
			for(int i=0;i<legalnodes.size();i++) {
				TreeNode node = (TreeNode)legalnodes.get(i);
				tree.addNode(node.getParentid(), node);
			}
			
    	 return tree;
     }
     
     public static void copyAttrs(Object src ,Object dest) throws Exception{
    	 try {
 			BeanUtils.copyProperties(dest,src);

 		} catch (IllegalAccessException e) {
 			e.printStackTrace();
 			throw new Exception(e.getMessage());
 		} catch (InvocationTargetException e) {
 			e.printStackTrace();
 			throw new Exception(e.getMessage());
 		}
     }
     
}
