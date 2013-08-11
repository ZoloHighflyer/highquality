package com.bluecreation.erp.inventory.dao;

import com.nci.cp.core.dao.IDao;
import com.nci.cp.core.model.IdEntity;
import com.nci.cp.ds.paging.Pagination;

public interface IInventoryBillDao extends IDao {
	public Pagination findInventoryBillsByUserWithPage(String userName, Pagination page);
	public Pagination findWaitingAuditInventoryBillsWithPage( Pagination page);
	public Pagination findAuditedInventoryBillsWithPage( Pagination page);
}
