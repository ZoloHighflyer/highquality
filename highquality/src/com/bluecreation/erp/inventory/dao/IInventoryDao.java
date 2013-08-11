package com.bluecreation.erp.inventory.dao;

import com.bluecreation.erp.inventory.model.Inventory;
import com.nci.cp.core.dao.IDao;

public interface IInventoryDao extends IDao {
	public boolean findExistInventory(long itemId,int type)throws Exception;
	public Inventory findInventoryByItemIdAndType(long itemId,int type)throws Exception;
}
