package com.nci.cp.core.sysmgt.service;

import java.util.List;

import com.nci.cp.core.service.IService;
import com.nci.cp.core.sysmgt.model.DicEntry;
import com.nci.cp.core.sysmgt.model.DicType;
import com.nci.cp.core.sysmgt.vo.DicEntryVo;
import com.nci.cp.core.sysmgt.vo.DicTypeVo;
import com.nci.cp.ds.paging.Pagination;
/**
 * @target  the interface is for Dic data operations.
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-11-03 
 */
public interface IDicMgmtService extends IService {
	 public long createDicType(DicTypeVo dictypevo) throws Exception;
		
	 public DicTypeVo findDicTypeById(long doctypeid) throws Exception;
		
	 public Pagination findAllDicTypesByPage(Pagination page) throws Exception;
	 
	 public List       findAllDicTypes()  throws Exception;
	 
	 public long updateDicType(DicTypeVo dictypeVo) throws Exception;
		
	 public boolean   deleteDicType(long dictypeid) throws Exception;
	 
	 public long createDicEntry(DicEntryVo dicentryVo) throws Exception;
		
	 public DicEntryVo findDicEntryById(long dicentryid) throws Exception;
		
	 public Pagination findAllDicEntrysByPage(Pagination page) throws Exception;
	 
	 public Pagination findDicEntrysByTypeWithPage(Pagination page,DicTypeVo dicTypeVo) throws Exception;
	 
	 public List       findAllDicEntries()  throws Exception;
	 
	 public long updateDicEntry(DicEntryVo dicentryVo) throws Exception;
		
	 public boolean   deleteDicEntry(long dicentryid) throws Exception;
}
