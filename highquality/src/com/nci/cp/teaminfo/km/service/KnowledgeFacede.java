package com.nci.cp.teaminfo.km.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nci.cp.core.model.OptMessage;
import com.nci.cp.ds.paging.Pagination;
import com.nci.cp.teaminfo.km.model.KnowledgeCategory;
import com.nci.cp.teaminfo.km.model.KnowledgeRecord;
import com.nci.cp.teaminfo.km.vo.KnowledgeCategoryVo;
import com.nci.cp.teaminfo.km.vo.KnowledgeRecordVo;

public class KnowledgeFacede implements IKnowledgeFacede {
	private static Log log = LogFactory.getLog(KnowledgeFacede.class);
    private IKnowledgeService  knowledgeService;
    
	public void setKnowledgeService(IKnowledgeService knowledgeService) {
		this.knowledgeService = knowledgeService;
	}

	public Pagination findKnowledgeRecordVosByPage(Pagination page) throws Exception {
		if (page==null) {
			page  = new Pagination();		
		}	  
		Pagination p = knowledgeService.findKnowledgeRcordsByPage(page);
		List rds=p.getList();
		List vos = new ArrayList();
		for(int i=0;i<rds.size();i++) {
			KnowledgeRecord kr =(KnowledgeRecord)rds.get(i);
			vos.add(KnowledgeServiceUtils.boToVo(kr));
		}
		p.setList(vos);
		return p;
	}
	public Pagination findKnowledgeRecordVosByCategoryWithPage(Pagination page,Long kcid,String owner) throws Exception {
		if (page==null) 
			page  = new Pagination();		
		
		if(kcid==null) {
			List cs=knowledgeService.findAllCategories(owner);
			log.info("=============================== size: "+cs.size());
			if (cs.size()>0) {
				KnowledgeCategory first =(KnowledgeCategory)cs.get(0);
				kcid = first.getId();
			}else{
				new Exception("kcid is null!");
			}
		}

		Pagination p = knowledgeService.findKnowledgeRcordsByCategoryWithPage(page, kcid,owner);
		List rds=p.getList();
		List vos = new ArrayList();
		if ((rds!=null)&&(rds.size()>0)) {
		  for(int i=0;i<rds.size();i++) {
			KnowledgeRecord kr =(KnowledgeRecord)rds.get(i);
			KnowledgeRecordVo krvo = KnowledgeServiceUtils.boToVo(kr);
			krvo.setKcpath(this.getCategoryPath(kr.getInfoCategory().getId()));
			vos.add(krvo);
			
		  }
		  
		}
		p.setList(vos);
		return p;
	}
	public void createKnowledgeRecordVo(KnowledgeRecordVo krvo) throws Exception {
		KnowledgeRecord kr=KnowledgeServiceUtils.voToBo(krvo);
		if (krvo.getKcid()!=null){
		  KnowledgeCategory kc=knowledgeService.findKnowledgeCategoryById(krvo.getKcid());
		  kr.setInfoCategory(kc);
		}
		
		knowledgeService.createKnowledgeRcord(kr);		
	}
	
    public void updateKnowledgeRecordVo(KnowledgeRecordVo krvo)
			throws Exception {
    	KnowledgeRecord kr = KnowledgeServiceUtils.voToBo(krvo);
    	KnowledgeCategory kc = knowledgeService.findKnowledgeCategoryById(krvo.getKcid());
    	kr.setInfoCategory(kc);
    	knowledgeService.updateKnowledgeRcord(kr);
		
	}

	public KnowledgeRecordVo findKnowledgeRecordVoById(long krvoid) throws Exception {    	
    	return KnowledgeServiceUtils.boToVo(knowledgeService.findKnowledgeRecordById(krvoid));
    }
	
	public void deleteKnowledgeRcord(long krid) throws Exception {
		knowledgeService.deleteKnowledgeRcord(krid);
	}

	public List queryKnowledgeRcordVos(String queryKey) throws Exception {
		List krs = knowledgeService.queryKnowledgeRcords(queryKey);
		List vos = new ArrayList();
		
		for(int i=0;i<krs.size();i++) {
			KnowledgeRecord kr =(KnowledgeRecord)krs.get(i);
			vos.add(KnowledgeServiceUtils.boToVo(kr));
		}
		return vos;
	}

	public List findAllCategoryVos(String owner) throws Exception {
		
		return knowledgeService.findAllCategories(owner);
	}

	public KnowledgeCategoryVo findKnowledgeCategoryVoById(long kcid)
			throws Exception {		
		return KnowledgeServiceUtils.boToVo(knowledgeService.findKnowledgeCategoryById(kcid));
	}

	public KnowledgeCategoryVo saveCategory(KnowledgeCategoryVo kcvo)
			throws Exception {
		
		long kcid=knowledgeService.createKnowledgeCategory(KnowledgeServiceUtils.voToBo(kcvo));
		
		return findKnowledgeCategoryVoById(kcid);
	}
	public KnowledgeCategoryVo updateCategory(KnowledgeCategoryVo kcvo)	throws Exception {
      long kcid=knowledgeService.updateKnowledgeCategory(KnowledgeServiceUtils.voToBo(kcvo));
      return findKnowledgeCategoryVoById(kcid);
    }

	public String getCategoryPath(long kcid) throws Exception {
			return knowledgeService.getCategoryPath(kcid);
	}

	public OptMessage deleteKnowledgeCategory(long kcid) throws Exception {		
		return knowledgeService.deleteKnowledgeCategory(kcid);
	}
	
}
