package com.nci.cp.core.sysmgt.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nci.cp.core.model.DefaultEntity;
import com.nci.cp.core.sysmgt.dao.IDicEntryDao;
import com.nci.cp.core.sysmgt.dao.IDicTypeDao;
import com.nci.cp.core.sysmgt.model.DicEntry;
import com.nci.cp.core.sysmgt.model.DicType;
import com.nci.cp.core.sysmgt.vo.DicEntryVo;
import com.nci.cp.core.sysmgt.vo.DicTypeVo;
import com.nci.cp.ds.paging.Pagination;

public class DicMgmtService implements IDicMgmtService {
	private static Log log = LogFactory.getLog(DicMgmtService.class);
    private IDicTypeDao dicTypeDao;
    private IDicEntryDao dicEntryDao;
    
	public void setDicTypeDao(IDicTypeDao dicTypeDao) {
		this.dicTypeDao = dicTypeDao;
	}

	public void setDicEntryDao(IDicEntryDao dicEntryDao) {
		this.dicEntryDao = dicEntryDao;
	}

	public long createDicEntry(DicEntryVo dicentryVo) throws Exception {
	    
	    DicType dicType = (DicType)dicTypeDao.findEntityById(new DefaultEntity(dicentryVo.getDicTypeVo().getId()));
	    DicEntry dicEntry = DicServiceUtils.voToBo(dicentryVo);
	    dicEntry.setDicType(dicType);
	    dicEntry =(DicEntry)dicEntryDao.createEntity(dicEntry);
		return dicEntry.getId().longValue();
	}

	public long createDicType(DicTypeVo dictypevo) throws Exception {		
		DicType dicType =(DicType)dicEntryDao.createEntity(DicServiceUtils.voToBo(dictypevo));
		return dicType.getId();
	}

	public boolean deleteDicEntry(long dicentryid) throws Exception {

		DicEntry dicEntry = (DicEntry)dicEntryDao.findEntityById(new DefaultEntity(dicentryid));
		dicEntry.setDicType(null);	
	    return dicEntryDao.deleteEntity(dicEntry);
	}

	public boolean deleteDicType(long dictypeid) throws Exception {
		
		return dicTypeDao.deleteEntity(new DefaultEntity(dictypeid));
	}

	public List findAllDicEntries() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Pagination findAllDicEntrysByPage(Pagination page) throws Exception {
		if (page==null) {
			page  = new Pagination();		
		}
	    Pagination pageResult=dicEntryDao.findEntitiesByPage(page);
	    
		return pageResult;
	}

	public Pagination findDicEntrysByTypeWithPage(Pagination page,DicTypeVo dicTypeVo)
			throws Exception {
		if (page==null) {
			page  = new Pagination();		
		}
	    Pagination pageResult=dicEntryDao.findDicEntrysByTypeWithPage(page, dicTypeVo.getId());
	    List dicentryvoList = pageResult.getList();
	    List pageVoList = new ArrayList();
	    for(int i=0;i<dicentryvoList.size();i++){
	    	DicEntry dicEntry = (DicEntry)dicentryvoList.get(i);
	    	
	    	DicTypeVo tempDicTypeVo = DicServiceUtils.boToVo(dicEntry.getDicType());
	    	DicEntryVo tempDicEntryVo =DicServiceUtils.boToVo(dicEntry);
	    	tempDicEntryVo.setDicTypeVo(tempDicTypeVo);
	    	pageVoList.add(tempDicEntryVo);
	    	
	    }
	    pageResult.setList(pageVoList);
		return pageResult;
	}

	public List findAllDicTypes() throws Exception {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nci.cp.core.sysmgt.service.IDicMgmtService#findAllDicTypesByPage(com.nci.cp.ds.paging.Pagination)
	 */
	public Pagination findAllDicTypesByPage(Pagination page) throws Exception {
		if (page==null) 
			page = new Pagination();
		Pagination pageResult = dicTypeDao.findEntitiesByPage(page);
		List results = pageResult.getList();
		List vos = new ArrayList();
		for (int i=0;i<results.size();i++) {
			DicTypeVo dictypeVo  = new DicTypeVo();;
			DicType  dictype = (DicType)results.get(i);
			vos.add(DicServiceUtils.boToVo(dictype));
		}
		pageResult.setList(vos);
		
		// return dicTypeDao.findEntitiesByPage(page);
		return pageResult;
	}

	public DicEntryVo findDicEntryById(long dicentryid) throws Exception {
		DicEntry dicEntry=(DicEntry)dicEntryDao.findEntityById(new DefaultEntity(dicentryid));
		DicEntryVo dicEntryVo = DicServiceUtils.boToVo(dicEntry);
		return dicEntryVo;
	}

	public DicTypeVo findDicTypeById(long dictypeid) throws Exception {		
		return DicServiceUtils.boToVo((DicType)dicTypeDao.findEntityById(new DefaultEntity(dictypeid)));
	}

	public long updateDicEntry(DicEntryVo dicentryVo) throws Exception {
		DicEntry dicEntry = (DicEntry)dicEntryDao.findEntityById(new DefaultEntity(dicentryVo.getId()));
		dicEntry.setName(dicentryVo.getName());
		dicEntry=(DicEntry)dicEntryDao.updateEntity(dicEntry);
		return dicEntry.getId();
	}

	public long updateDicType(DicTypeVo dictypevo) throws Exception {	
		return ((DicType)dicTypeDao.updateEntity(DicServiceUtils.voToBo(dictypevo))).getId().longValue();
	}

}
