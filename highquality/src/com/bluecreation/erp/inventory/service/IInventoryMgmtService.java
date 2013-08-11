package com.bluecreation.erp.inventory.service;

import com.bluecreation.erp.inventory.vo.InventoryBillVo;
import com.nci.cp.core.service.IService;

public interface IInventoryMgmtService extends IService {
	
	public InventoryBillVo findInventoryBillVoById(long inventorybillid) throws Exception;
	
}
