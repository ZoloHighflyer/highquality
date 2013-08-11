package com.bluecreation.erp.inventory.service;

import com.bluecreation.erp.inventory.vo.InventoryBillVo;

public class InventoryMgmtService implements IInventoryMgmtService {
    
	private IInventoryBillService billService;
	private IInventoryService     inventoryService; 
    
	public void setBillService(IInventoryBillService billService) {
		this.billService = billService;
	}
    
	public void setInventoryService(IInventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}

	public InventoryBillVo findInventoryBillVoById(long inventorybillid)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
