package com.nci.cp.core.sysmgt.dao;

import com.nci.cp.core.dao.IDao;
import com.nci.cp.ds.paging.Pagination;
/**
 * @target  the interface is for DicEntry dao operations.
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-11-03 
 */
public interface IDicEntryDao extends IDao {
	public Pagination findDicEntrysByTypeWithPage(final Pagination page,final long dictypeid) throws Exception;
}
