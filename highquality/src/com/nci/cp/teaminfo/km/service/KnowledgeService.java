package com.nci.cp.teaminfo.km.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nci.cp.core.model.DefaultEntity;
import com.nci.cp.core.model.OptMessage;
import com.nci.cp.ds.paging.Pagination;
import com.nci.cp.teaminfo.km.dao.IKnowledgeCategoryDao;
import com.nci.cp.teaminfo.km.dao.IKnowledgeRecordDao;
import com.nci.cp.teaminfo.km.model.KnowledgeCategory;
import com.nci.cp.teaminfo.km.model.KnowledgeRecord;

public class KnowledgeService implements IKnowledgeService {
	private static Log log = LogFactory.getLog(KnowledgeService.class);
    private IKnowledgeCategoryDao knowledgeCategoryDao;
    private IKnowledgeRecordDao knowledgeRecordDao;
	
	public void setKnowledgeRecordDao(IKnowledgeRecordDao knowledgeRecordDao) {
		this.knowledgeRecordDao = knowledgeRecordDao;
	}
	
	public void setKnowledgeCategoryDao(IKnowledgeCategoryDao knowledgeCategoryDao) {
		this.knowledgeCategoryDao = knowledgeCategoryDao;
	}
	public Pagination findKnowledgeRcordsByPage(Pagination page) throws Exception {
		Pagination p=knowledgeRecordDao.findRecordsByPage(page);
		return p;
	}
	public Pagination findKnowledgeRcordsByCategoryWithPage(Pagination page,Long kcid,String owner) throws Exception {
		
		KnowledgeCategory kc=null;
		if (kcid!=null) {
		   kc=(KnowledgeCategory)knowledgeCategoryDao.findEntityById(new DefaultEntity(kcid));
		}else{
		   List kcList = knowledgeCategoryDao.findAllCategories(owner);
		   if (kcList.size()>0) {
			   kc=(KnowledgeCategory)kcList.get(0);
		   }else{
			   throw new Exception("no kc exist with owner.");
		   }
		}
		
		Pagination p=null;
		if (kc!=null) {				   
		   p=knowledgeRecordDao.findRecordsByCategoryWithPage(page, kc.getId(),owner);	
		}
		return p;
	}

	public void createKnowledgeRcord(KnowledgeRecord kr) throws Exception {
			knowledgeRecordDao.createEntity(kr);
		
	}
    
	public void updateKnowledgeRcord(KnowledgeRecord kr) throws Exception {		
		
		knowledgeRecordDao.updateEntity(kr);
		
	}

	public KnowledgeRecord findKnowledgeRecordById(long krid) throws Exception {		
		return (KnowledgeRecord)knowledgeRecordDao.findEntityById(new DefaultEntity(krid));
	}

	public void deleteKnowledgeRcord(long krid) throws Exception {
		knowledgeRecordDao.deleteEntity(new DefaultEntity(krid));
		
	}

	public List<KnowledgeRecord> queryKnowledgeRcords(String queryKey) throws Exception {
/*	List rs =SearchUtils.searchRecord("d:\\weblucene", KnowledgeRecord.class.getName(), queryKey);
		List krs = new ArrayList();
		for(int i=0;i<rs.size();i++) {
			Content c =(Content)rs.get(i);
			long krid=Long.parseLong(c.getContent());
			KnowledgeRecord kr =(KnowledgeRecord)knowledgeRecordDao.findEntityById(new DefaultEntity(krid));
			krs.add(kr);
		}
		return krs;*/
		return null;
	}

	public List findAllCategories(String owner) throws Exception {
		List categories=knowledgeCategoryDao.findAllCategories(owner);
		
		return categories;
	} 

	public KnowledgeCategory findKnowledgeCategoryById(long kcid)
			throws Exception {
		KnowledgeCategory kc=(KnowledgeCategory)knowledgeCategoryDao.findEntityById(new DefaultEntity(kcid));
		
		return kc;
	}

	public long updateKnowledgeCategory(KnowledgeCategory kc)throws Exception {		
		return knowledgeCategoryDao.updateKnowledgeCategoryInfo(kc);
	}

	public long createKnowledgeCategory(KnowledgeCategory kc) throws Exception {
       
		kc=(KnowledgeCategory)knowledgeCategoryDao.createEntity(kc);
		return kc.getId();
	}

	public String getCategoryPath(long kcid) throws Exception {
		KnowledgeCategory kc=findKnowledgeCategoryById(kcid);
		String path="\\"+kc.getName();
		while(kc.getParcategoryno()!=-1) {			
			kc=findKnowledgeCategoryById(kc.getParcategoryno());
			path="\\"+kc.getName()+path;			
		}
		
		return path;
	}

	public OptMessage deleteKnowledgeCategory(long kcid) throws Exception {
		KnowledgeCategory kc=findKnowledgeCategoryById(kcid);
		OptMessage optmsg = new OptMessage();
		if ((kc.getInfoRecords()!=null)&&(kc.getInfoRecords().size()>0)){
			String s="当前类别存在记录关联，只有该类别没有记录或没有子类别才能删除！";
			optmsg.setMsg(s);
			optmsg.setSuccessful(false);
		}else if (knowledgeCategoryDao.isExistSubCategory(kcid)){
			String s="当前类别存在记录关联，只有该类别没有记录或没有子类别才能删除！";
			optmsg.setMsg(s);
			optmsg.setSuccessful(false);
		}else{
			knowledgeCategoryDao.deleteEntity(new DefaultEntity(kcid));
			String s="删除类别成功";
			optmsg.setMsg(s);
			optmsg.setSuccessful(true);
		}
		return optmsg;
	}
	
	
	
}
