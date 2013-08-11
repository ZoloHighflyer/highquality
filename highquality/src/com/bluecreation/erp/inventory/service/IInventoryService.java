package com.bluecreation.erp.inventory.service;

import java.util.List;

import com.bluecreation.erp.inventory.model.Inventory;
import com.bluecreation.erp.inventory.vo.InventoryVo;
import com.nci.cp.core.service.IService;
import com.nci.cp.ds.paging.Pagination;
/**
 * @target  the interface is for inventory service operations implement.
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-10-19 
 */
public interface IInventoryService extends IService {
	
	    public long createInventory(InventoryVo inventoryvo) throws Exception;
		
		public InventoryVo findInventoryVoById(long inventoryid) throws Exception;
		
		public Pagination findAllInventoryVosByPage(Pagination page) throws Exception;
		
		public List findAllInventoryVos() throws Exception;
		
		public long updateInventoryVo(InventoryVo inventoryvo) throws Exception;
		 
		public boolean   deleteInventory(long inventoryid) throws Exception;
		
		public void addInventory(Inventory inventory) throws Exception ;
		
		public void outInventory(Inventory inventory) throws Exception;
}
