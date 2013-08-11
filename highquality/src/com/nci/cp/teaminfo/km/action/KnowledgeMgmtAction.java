package com.nci.cp.teaminfo.km.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nci.cp.core.model.OptMessage;
import com.nci.cp.core.sysmgt.model.IUserInfo;
import com.nci.cp.core.utils.WebUtils;
import com.nci.cp.core.web.AbstractAction;
import com.nci.cp.core.web.IAction;
import com.nci.cp.ds.paging.Pagination;
import com.nci.cp.teaminfo.km.model.KnowledgeRecord;
import com.nci.cp.teaminfo.km.service.IKnowledgeFacede;
import com.nci.cp.teaminfo.km.vo.KnowledgeCategoryVo;
import com.nci.cp.teaminfo.km.vo.KnowledgeRecordVo;

public class KnowledgeMgmtAction extends AbstractAction {
	private static Log log = LogFactory.getLog(KnowledgeMgmtAction.class);
    private  IKnowledgeFacede   knowledgeFacede;
    private  KnowledgeRecordVo  knowledgeRecordVo;
    private  KnowledgeCategoryVo  knowledgeCategoryVo;
    private  List               queryResults;
	
    
	public void setKnowledgeFacede(IKnowledgeFacede knowledgeFacede) {
		this.knowledgeFacede = knowledgeFacede;
	}

	public KnowledgeRecordVo getKnowledgeRecordVo() {
		return knowledgeRecordVo;
	}

	public void setKnowledgeRecordVo(KnowledgeRecordVo knowledgeRecordVo) {
		this.knowledgeRecordVo = knowledgeRecordVo;
	}
    
	public KnowledgeCategoryVo getKnowledgeCategoryVo() {
		return knowledgeCategoryVo;
	}

	public void setKnowledgeCategoryVo(KnowledgeCategoryVo knowledgeCategoryVo) {
		this.knowledgeCategoryVo = knowledgeCategoryVo;
	}

	public List getQueryResults() {
		return queryResults;
	}

	public void setQueryResults(List queryResults) {
		this.queryResults = queryResults;
	}
/*
	public String knowledgeRecordsMgmt()throws Exception {
		if (knowledgeCategoryVo!=null) {
			msg ="selected";
			knowledgeCategoryVo=knowledgeFacede.findKnowledgeCategoryVoById(knowledgeCategoryVo.getId());
		}
    	List list = knowledgeFacede.findAllCategoryVos((String)WebUtils.getCurrentUser().getProperty(IUserInfo.USER_ID));
		WebUtils.setRequestAttribute("treeList", list);
		return SUCCESS;
	}
*/  
	public String knowledgeRecordsMgmt()throws Exception {
		if (knowledgeCategoryVo==null)  {
			knowledgeCategoryVo = new KnowledgeCategoryVo();
			knowledgeCategoryVo.setPage(knowledgeFacede.findKnowledgeRecordVosByCategoryWithPage(null, null,(String)WebUtils.getCurrentUser().getProperty(IUserInfo.USER_ID)));
		}
		/*Pagination p=knowledgeFacede.findKnowledgeRecordVosByCategoryWithPage(null, null,(String)WebUtils.getCurrentUser().getProperty(IUserInfo.USER_ID));
    	if ((p.getList()!=null)&&(p.getList().size()>0)){
    		for(int i=0;i<p.getList().size();i++) {
    			KnowledgeRecordVo kr =(KnowledgeRecordVo)p.getList().get(i);
    			log.info("===================kr name: "+kr.getTitle());
    			
    		}
    		
    	}*/
		List list = knowledgeFacede.findAllCategoryVos((String)WebUtils.getCurrentUser().getProperty(IUserInfo.USER_ID));
		WebUtils.setRequestAttribute("treeList", list);
		return SUCCESS;
	}
	public String viewKnowledgeRecords()throws Exception {
		log.info("================= records");
		if (knowledgeCategoryVo==null)  {
			knowledgeCategoryVo= new KnowledgeCategoryVo();
			knowledgeCategoryVo.setPage(knowledgeFacede.findKnowledgeRecordVosByCategoryWithPage(null, null,(String)WebUtils.getCurrentUser().getProperty(IUserInfo.USER_ID)));
		}else {			
			knowledgeCategoryVo.setPage(knowledgeFacede.findKnowledgeRecordVosByCategoryWithPage(knowledgeCategoryVo.getPage(),knowledgeCategoryVo.getId(),(String)WebUtils.getCurrentUser().getProperty(IUserInfo.USER_ID)));
		}		
		return SUCCESS;
	}
	public String addRecord() throws Exception {	
		List list = knowledgeFacede.findAllCategoryVos((String)WebUtils.getCurrentUser().getProperty(IUserInfo.USER_ID));
		WebUtils.setRequestAttribute("treeList", list);
		return SUCCESS;
	}
	public String initeditRecord() throws Exception {
		knowledgeRecordVo=knowledgeFacede.findKnowledgeRecordVoById(knowledgeRecordVo.getId());
		if (knowledgeRecordVo.getKcid()!=null)
		  knowledgeRecordVo.setKcpath(knowledgeFacede.getCategoryPath(knowledgeRecordVo.getKcid()));
	//	log.info("========record path: "+knowledgeFacede.getCategoryPath(knowledgeRecordVo.getKcid()));
		return SUCCESS;
	}
    public String saveRecord() throws Exception {
    	knowledgeRecordVo.setOwner((String)WebUtils.getCurrentUser().getProperty(IUserInfo.USER_ID));
    	
    	if ((knowledgeRecordVo.getAction()!=null)&&(knowledgeRecordVo.getAction().equals(IAction.ACTION_EDIT))) {
    		knowledgeFacede.updateKnowledgeRecordVo(knowledgeRecordVo);
		}else {	
			knowledgeFacede.createKnowledgeRecordVo(knowledgeRecordVo);
		}
    	   	
    	return SUCCESS;
    }
    public String viewRecord() throws Exception {
    	
    	knowledgeRecordVo=knowledgeFacede.findKnowledgeRecordVoById(knowledgeRecordVo.getId());
    	if (knowledgeRecordVo.getKcid()!=null)
  		  knowledgeRecordVo.setKcpath(knowledgeFacede.getCategoryPath(knowledgeRecordVo.getKcid()));
    	return SUCCESS;
    }
    public String delRecord() throws Exception {
    	knowledgeFacede.deleteKnowledgeRcord(knowledgeRecordVo.getId());
    	return SUCCESS;
    }
    
    public String queryRecords() throws Exception {    	
    	/*queryResults =knowledgeFacede.queryKnowledgeRcordVos(knowledgeRecordVo.getQuery());
    	if ((queryResults!=null)&&(queryResults.size()==0)) queryResults=null;*/
    	//
    	return SUCCESS;
    }
    
    public String mgmtCategory() throws Exception { 
    	if ((msg!=null)&&(msg.equals("success"))&&(knowledgeCategoryVo!=null)) {
			msg ="selected";
			knowledgeCategoryVo=knowledgeFacede.findKnowledgeCategoryVoById(knowledgeCategoryVo.getId());
		}
    	List list = knowledgeFacede.findAllCategoryVos((String)WebUtils.getCurrentUser().getProperty(IUserInfo.USER_ID));
		WebUtils.setRequestAttribute("treeList", list);
    	return SUCCESS;
    }
    public String categoryTree() throws Exception { 
    	
    	List list = knowledgeFacede.findAllCategoryVos((String)WebUtils.getCurrentUser().getProperty(IUserInfo.USER_ID));
		WebUtils.setRequestAttribute("treeList", list);
    	return SUCCESS;
    }
    public String addCategory()throws Exception { 
    	if (knowledgeCategoryVo.getId()!=null) {
       		if (knowledgeCategoryVo.getId()!=-1) {
    			knowledgeCategoryVo=knowledgeFacede.findKnowledgeCategoryVoById(knowledgeCategoryVo.getId());    			
    		}
    		
    	//	knowledgeCategoryVo =  sysmgmtService.findFuncById(funcVo.getId());
          }
    	return SUCCESS;
    }
    public String initEditCategory()throws Exception { 
    	if(knowledgeCategoryVo.getId() != null){
    		knowledgeCategoryVo=knowledgeFacede.findKnowledgeCategoryVoById(knowledgeCategoryVo.getId());	
    		if (knowledgeCategoryVo.getParcategoryno()!=-1){
    			KnowledgeCategoryVo kcParentVo = knowledgeFacede.findKnowledgeCategoryVoById(knowledgeCategoryVo.getParcategoryno());
        		knowledgeCategoryVo.setParCategoryName(kcParentVo.getName());
    		}
    		
		}
    	return SUCCESS;
    }
    public String saveCategory() throws Exception { 
    	
    	knowledgeCategoryVo.setOwner((String)WebUtils.getCurrentUser().getProperty(IUserInfo.USER_ID));
    	if ((knowledgeCategoryVo != null )&& (knowledgeCategoryVo.getAction()!=null)&&(knowledgeCategoryVo.getAction().equals(IAction.ACTION_EDIT))) {
    		knowledgeCategoryVo=knowledgeFacede.updateCategory(knowledgeCategoryVo);		
		} else {
			knowledgeCategoryVo=knowledgeFacede.saveCategory(knowledgeCategoryVo);		
		}
    	
    	msg="selected";
		WebUtils.getServletRequest().setAttribute(IAction.ACTION_MESSAGE, "success");	
    	return SUCCESS;
    }
    public String deleteCategory() throws Exception { 
    	if(knowledgeCategoryVo.getId() != null){
    		OptMessage msg=knowledgeFacede.deleteKnowledgeCategory(knowledgeCategoryVo.getId());
    		WebUtils.setRequestAttribute("msg", msg);
    		if (msg.isSuccessful()) {
    			return SUCCESS;
    	    }else {
    	    	return ERROR;
    	    }
    	}
    	return SUCCESS;
    }
    
}
