package com.bluecreation.erp.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bluecreation.erp.inventory.dao.IInventoryDao;
import com.bluecreation.erp.inventory.model.Inventory;
import com.bluecreation.erp.inventory.vo.InventoryVo;
import com.bluecreation.erp.productmgt.model.CommonComponent;
import com.bluecreation.erp.productmgt.service.IFittingsService;
import com.bluecreation.erp.productmgt.service.IProductService;
import com.nci.cp.ds.paging.Pagination;


public class InventoryService implements IInventoryService{
	private static Log log = LogFactory.getLog(InventoryService.class);
    private IInventoryDao inventoryDao;
    private IProductService productService;
    private IFittingsService fittingsService;
    
	public void setProductService(IProductService productService) {
		this.productService = productService;
	}

	public void setFittingsService(IFittingsService fittingsService) {
		this.fittingsService = fittingsService;
	}

	public void setInventoryDao(IInventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
	}
    
	public long createInventory(InventoryVo inventoryvo) throws Exception {
		
		return 0;
	}

	public boolean deleteInventory(long inventoryid) throws Exception {
		
		return false;
	}

	public List findAllInventoryVos() throws Exception {	
		return null;
	}
    
	public void addInventory(Inventory inventory) throws Exception {
		if (inventoryDao.findExistInventory(inventory.getInventoryId(), inventory.getInventoryType())){
			Inventory in = inventoryDao.findInventoryByItemIdAndType(inventory.getInventoryId(), inventory.getInventoryType());
			in.setQuantity(in.getQuantity()+inventory.getQuantity());
			inventoryDao.updateEntity(in);
		}else{
			inventoryDao.createEntity(inventory);
		}
		
	}
	public void outInventory(Inventory inventory) throws Exception {
		if (inventoryDao.findExistInventory(inventory.getInventoryId(), inventory.getInventoryType())){
			Inventory in = inventoryDao.findInventoryByItemIdAndType(inventory.getInventoryId(), inventory.getInventoryType());
			if (in.getQuantity()-inventory.getQuantity()>=0){
				in.setQuantity(in.getQuantity()-inventory.getQuantity());
			}else{
				throw new Exception("It does not exist the item in inventory!");
			}
			
			inventoryDao.updateEntity(in);
		}else{
			throw new Exception("It does not exist the item in inventory!");
		}
	}
	public Pagination findAllInventoryVosByPage(Pagination page)
			throws Exception {
		if (page==null) {
			page  = new Pagination();		
		}	  
		Pagination pg= inventoryDao.findEntitiesByPage(page);
		List inventories =pg.getList();
		List storageEntities = new ArrayList();
		for(int i=0;i<inventories.size();i++) {
			Inventory inventory = (Inventory)inventories.get(i);
			InventoryVo inVo = new InventoryVo();
			if (inventory.getInventoryType()==0) {
				CommonComponent ft = fittingsService.findFittingById(inventory.getInventoryId());				
				inVo.setCommCmpt(ft);
				inVo.setId(inventory.getId());
				inVo.setQuantity(inventory.getQuantity());				
				storageEntities.add(inVo);
				
			}else if (inventory.getInventoryType()==1){
				CommonComponent cc = productService.findProductById(inventory.getInventoryId());
				inVo.setCommCmpt(cc);
				inVo.setId(inventory.getId());
				inVo.setQuantity(inventory.getQuantity());
				storageEntities.add(inVo);
				
			}
			
		}
		pg.setList(storageEntities);
		return pg;
	}

	public InventoryVo findInventoryVoById(long inventoryid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public long updateInventoryVo(InventoryVo inventoryvo) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
