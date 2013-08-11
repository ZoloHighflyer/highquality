package com.bluecreation.erp.inventory.service;

import com.bluecreation.erp.inventory.vo.InventoryBillVo;
import com.nci.cp.core.service.IService;
import com.nci.cp.ds.paging.Pagination;

public interface IInventoryBillService extends IService {
	public InventoryBillVo findInventoryBillVoById(long inventorybillid) throws Exception;
	public long updateInventoryBillVo(InventoryBillVo inventoryBillVo) throws Exception;
	public long saveInventoryBillVo(InventoryBillVo inventoryBillVo) throws Exception;
	public boolean auditInventoryBillVo(long inventorybillid) throws Exception;
	public boolean submitInventoryBillVo(long inventorybillid) throws Exception;
	public boolean recedeInventoryBillVo(long inventorybillid) throws Exception;
	public boolean deleteInventoryBillVo(long inventorybillid) throws Exception;
	public Pagination findInventoryBillsByUserName(String userName, Pagination page)  throws Exception;
	public Pagination findWaitingAuditInventoryBillsByPage(Pagination page)  throws Exception;
	public Pagination findAuditedInventoryBills(Pagination page)  throws Exception ;
}
