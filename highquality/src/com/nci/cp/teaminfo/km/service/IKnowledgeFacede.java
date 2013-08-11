package com.nci.cp.teaminfo.km.service;

import java.util.List;

import com.nci.cp.core.model.OptMessage;
import com.nci.cp.core.service.IService;
import com.nci.cp.ds.paging.Pagination;
import com.nci.cp.teaminfo.km.model.KnowledgeCategory;
import com.nci.cp.teaminfo.km.vo.KnowledgeCategoryVo;
import com.nci.cp.teaminfo.km.vo.KnowledgeRecordVo;

public interface IKnowledgeFacede extends IService {
     public Pagination findKnowledgeRecordVosByPage(Pagination page) throws Exception;  
     public Pagination findKnowledgeRecordVosByCategoryWithPage(Pagination page,Long kcid,String owner) throws Exception;
     public void createKnowledgeRecordVo(KnowledgeRecordVo krvo)throws Exception;
     public void updateKnowledgeRecordVo(KnowledgeRecordVo krvo)throws Exception;
     public KnowledgeRecordVo findKnowledgeRecordVoById(long krvoid) throws Exception;
     public void deleteKnowledgeRcord(long krid) throws Exception ;
     public List queryKnowledgeRcordVos(String queryKey) throws Exception ;
     public List findAllCategoryVos(String owner)throws Exception ;
     public KnowledgeCategoryVo findKnowledgeCategoryVoById(long kcid) throws Exception;
     public KnowledgeCategoryVo saveCategory(KnowledgeCategoryVo kcvo) throws Exception;
     public KnowledgeCategoryVo updateCategory(KnowledgeCategoryVo kcvo)	throws Exception;
     public String getCategoryPath(long kcid) throws Exception;
     public OptMessage deleteKnowledgeCategory(long kcid) throws Exception;
}
