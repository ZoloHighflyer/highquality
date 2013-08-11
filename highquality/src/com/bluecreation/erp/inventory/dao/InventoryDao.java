package com.bluecreation.erp.inventory.dao;

import java.util.List;

import com.bluecreation.erp.inventory.model.Inventory;
import com.nci.cp.core.dao.AbstractDao;


public class InventoryDao extends AbstractDao implements IInventoryDao {

	@Override
	protected Class getDaoModel() {
		// TODO Auto-generated method stub
		return Inventory.class;
	}
    
	public boolean findExistInventory(long itemId,int type)throws Exception{
		String hsql = "select i from Inventory i where i.inventoryId="+itemId+" and i.inventoryType="+type;
    	List result=super.find(hsql);
		return result.size()>0;
	}
	public Inventory findInventoryByItemIdAndType(long itemId,int type)throws Exception{
		String hsql = "select i from Inventory i where i.inventoryId="+itemId+" and i.inventoryType="+type;
    	List result=super.find(hsql);
		if(result.size()>0){
			return (Inventory)result.get(0);
		}
		return null;
	}
}
