package com.nci.cp.teaminfo.km.service;

import java.util.List;

import com.nci.cp.core.model.OptMessage;
import com.nci.cp.core.service.IService;
import com.nci.cp.ds.paging.Pagination;
import com.nci.cp.teaminfo.km.model.KnowledgeCategory;
import com.nci.cp.teaminfo.km.model.KnowledgeRecord;

public interface IKnowledgeService extends IService {
	public Pagination findKnowledgeRcordsByPage(Pagination page) throws Exception;
	public Pagination findKnowledgeRcordsByCategoryWithPage(Pagination page,Long kcid,String owner) throws Exception ;
	public KnowledgeRecord findKnowledgeRecordById(long krid) throws Exception;
	public void createKnowledgeRcord(KnowledgeRecord kr)throws Exception;
	public void updateKnowledgeRcord(KnowledgeRecord kr)throws Exception;
	public void deleteKnowledgeRcord(long krid) throws Exception ;
	public List queryKnowledgeRcords(String queryKey) throws Exception ;
	
	public List findAllCategories(String owner) throws Exception ;
	public KnowledgeCategory findKnowledgeCategoryById(long kcid) throws Exception;
	public long updateKnowledgeCategory(KnowledgeCategory kc)throws Exception;
	public long createKnowledgeCategory(KnowledgeCategory kc)throws Exception;
	public String getCategoryPath(long kcid)throws Exception;
	public OptMessage deleteKnowledgeCategory(long kcid)throws Exception;
}
