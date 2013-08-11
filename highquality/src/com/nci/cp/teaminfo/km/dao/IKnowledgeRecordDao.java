package com.nci.cp.teaminfo.km.dao;

import com.nci.cp.core.dao.IDao;
import com.nci.cp.ds.paging.Pagination;

public interface IKnowledgeRecordDao extends IDao {
	public Pagination findRecordsByPage(final Pagination page);
	public Pagination findRecordsByCategoryWithPage(Pagination page,long kcid,String owner);
	public Pagination findRecordsNoCategoryWithPage(Pagination page,String owner);
}
