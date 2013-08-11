package com.bluecreation.erp.inventory.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bluecreation.erp.inventory.dao.IInventoryBillDao;
import com.bluecreation.erp.inventory.dao.IInventoryBillItemDao;
import com.bluecreation.erp.inventory.model.Inventory;
import com.bluecreation.erp.inventory.model.InventoryBill;
import com.bluecreation.erp.inventory.model.InventoryBillItem;
import com.bluecreation.erp.inventory.vo.InventoryBillVo;
import com.nci.cp.core.model.DefaultEntity;
import com.nci.cp.ds.paging.Pagination;

public class InventoryBillService implements IInventoryBillService {
	private static Log log = LogFactory.getLog(InventoryBillService.class);
	private IInventoryBillDao inventoryBillDao;
	private IInventoryBillItemDao inventoryBillItemDao;
	private IInventoryService inventoryService;

	public void setInventoryBillDao(IInventoryBillDao inventoryBillDao) {
		this.inventoryBillDao = inventoryBillDao;
	}

	public void setInventoryBillItemDao(
			IInventoryBillItemDao inventoryBillItemDao) {
		this.inventoryBillItemDao = inventoryBillItemDao;
	}
		
    public void setInventoryService(IInventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}

	public InventoryBillVo findInventoryBillVoById(long inventorybillid)
			throws Exception {
		InventoryBill iBill=(InventoryBill)inventoryBillDao.findEntityById(new DefaultEntity(inventorybillid));
		return InventoryBillServiceUtils.boToVo(iBill);
	}
    public long updateInventoryBillVo(InventoryBillVo inventoryBillVo) throws Exception {
    	InventoryBill inventoryBill=InventoryBillServiceUtils.voToBo(inventoryBillVo);
    	inventoryBill=	(InventoryBill)inventoryBillDao.updateEntity(inventoryBill);
    	return inventoryBill.getId();
    }
	public long saveInventoryBillVo(InventoryBillVo inventoryBillVo) throws Exception {
    	
    	InventoryBill inventoryBill=InventoryBillServiceUtils.voToBo(inventoryBillVo);
    	inventoryBill=	(InventoryBill)inventoryBillDao.createEntity(inventoryBill);
    	return inventoryBill.getId();
    }
	
	public boolean deleteInventoryBillVo(long inventorybillid) throws Exception {		
		return inventoryBillDao.deleteEntity(new DefaultEntity(inventorybillid));
	}
	
	public boolean auditInventoryBillVo(long inventorybillid) throws Exception {
		InventoryBill iBill = (InventoryBill)inventoryBillDao.findEntityById(new DefaultEntity(inventorybillid));
		if (iBill.getBillState()!=1) {
			throw new Exception("the state is not submited state!");
		}
		Set items =iBill.getBillItems();
		Iterator it =items.iterator();
		while(it.hasNext()){
			InventoryBillItem ibillItem = (InventoryBillItem)it.next();
			Inventory inv = new Inventory();
			inv.setInventoryId(ibillItem.getStorageItemId());
			inv.setInventoryType(ibillItem.getStorageItemType());
			inv.setQuantity(ibillItem.getQuantity());
			if (iBill.getBillType()==0) {
				inventoryService.addInventory(inv);
			}else if(iBill.getBillType()==1) {
				inventoryService.outInventory(inv);
			}
			
		}
		
		iBill.setBillState(2);
		iBill= (InventoryBill)inventoryBillDao.updateEntity(iBill);
		return iBill.getId().longValue()>0?true:false;
	}
	public boolean submitInventoryBillVo(long inventorybillid) throws Exception {
		
		InventoryBill iBill = (InventoryBill)inventoryBillDao.findEntityById(new DefaultEntity(inventorybillid));
		if (iBill.getBillState()!=0) {
			throw new Exception("the state is not equal nonsubmit state!");
		}
		iBill.setBillState(1);
		iBill= (InventoryBill)inventoryBillDao.updateEntity(iBill);
		return iBill.getId().longValue()>0?true:false;
	}
    public boolean recedeInventoryBillVo(long inventorybillid) throws Exception {
		
		InventoryBill iBill = (InventoryBill)inventoryBillDao.findEntityById(new DefaultEntity(inventorybillid));
		if (iBill.getBillState()!=1) {
			throw new Exception("the state is not equal nonrecede state!");
		}
		iBill.setBillState(0);
		iBill= (InventoryBill)inventoryBillDao.updateEntity(iBill);
		return iBill.getId().longValue()>0?true:false;
	}
	public Pagination findWaitingAuditInventoryBillsByPage(Pagination page)  throws Exception {
		if (page==null) {
			page  = new Pagination();		
		}	 
    	Pagination pg=inventoryBillDao.findWaitingAuditInventoryBillsWithPage(page);
    	List inventoryBills =pg.getList();
    	List iBillVos = new ArrayList();    	
    	for(int i=0;i<inventoryBills.size();i++) {
    		InventoryBill iBill =(InventoryBill)inventoryBills.get(i);
    		InventoryBillVo iBillVo = new InventoryBillVo();
    		iBillVo.setId(iBill.getId());
    		iBillVo.setBillNo(iBill.getBillNo());
    		iBillVo.setOptName(iBill.getOpUserName());
    		iBillVo.setState(iBill.getBillState());
    		iBillVo.setBillType(iBill.getBillType());
    		iBillVo.setCreateDate(iBill.getCreateDate());
    		iBillVos.add(iBillVo);    		
    	}
    	pg.setList(iBillVos);
    	return pg;
	}
	public Pagination findAuditedInventoryBills(Pagination page)  throws Exception {
    	if (page==null) {
			page  = new Pagination();		
		}	 
    	Pagination pg=inventoryBillDao.findAuditedInventoryBillsWithPage(page);
    	List inventoryBills =pg.getList();
    	List iBillVos = new ArrayList();    	
    	for(int i=0;i<inventoryBills.size();i++) {
    		InventoryBill iBill =(InventoryBill)inventoryBills.get(i);
    		InventoryBillVo iBillVo = new InventoryBillVo();
    		iBillVo.setId(iBill.getId());
    		iBillVo.setBillNo(iBill.getBillNo());
    		iBillVo.setBillType(iBill.getBillType());
    		iBillVo.setOptName(iBill.getOpUserName());
    		iBillVo.setState(iBill.getBillState());
    		iBillVo.setCreateDate(iBill.getCreateDate());
    		iBillVos.add(iBillVo);    		
    	}
    	pg.setList(iBillVos);
    	return pg;
    }
    public Pagination findInventoryBillsByUserName(String userName, Pagination page)  throws Exception {
    	if (page==null) {
			page  = new Pagination();		
		}	 
    	Pagination pg=inventoryBillDao.findInventoryBillsByUserWithPage(userName, page);
    	List inventoryBills =pg.getList();
    	List iBillVos = new ArrayList();    	
    	for(int i=0;i<inventoryBills.size();i++) {
    		InventoryBill iBill =(InventoryBill)inventoryBills.get(i);
    		InventoryBillVo iBillVo = new InventoryBillVo();
    		iBillVo.setId(iBill.getId());
    		iBillVo.setBillNo(iBill.getBillNo());
    		iBillVo.setOptName(iBill.getOpUserName());
    		iBillVo.setState(iBill.getBillState());
    		iBillVo.setCreateDate(iBill.getCreateDate());
    		iBillVo.setBillType(iBill.getBillType());
    		iBillVos.add(iBillVo);    		
    	}
    	pg.setList(iBillVos);
    	return pg;
    }
}
