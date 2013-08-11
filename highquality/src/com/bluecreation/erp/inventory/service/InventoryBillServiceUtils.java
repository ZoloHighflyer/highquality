package com.bluecreation.erp.inventory.service;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bluecreation.erp.inventory.model.InventoryBill;
import com.bluecreation.erp.inventory.model.InventoryBillItem;
import com.bluecreation.erp.inventory.vo.InventoryBillVo;
import com.bluecreation.erp.inventory.vo.InventoryComponent;
import com.nci.cp.core.utils.CommonUtils;

/**
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-10-30 
 */
public final class InventoryBillServiceUtils {
	private static Log log = LogFactory.getLog(InventoryBillServiceUtils.class);
    public static InventoryBillVo boToVo(InventoryBill inventoryBill)  throws Exception {
    	InventoryBillVo inventoryBillVo = new InventoryBillVo();
    	inventoryBillVo.setBillNo(inventoryBill.getBillNo());
    	inventoryBillVo.setState(inventoryBill.getBillState());
    	inventoryBillVo.setId(inventoryBill.getId());
    	inventoryBillVo.setOptName(inventoryBill.getOpUserName());
    	inventoryBillVo.setBillType(inventoryBill.getBillType());
    	inventoryBillVo.setCreateDate(inventoryBill.getCreateDate());
    	Set items = inventoryBill.getBillItems();
    	Iterator it=items.iterator();
    	List voitems = new ArrayList();
    	while(it.hasNext()) {
    		InventoryBillItem item =(InventoryBillItem)it.next();
    		InventoryComponent ic =new InventoryComponent();
    		
    		ic.setComponentId(""+item.getStorageItemId());
    		ic.setComponentType(item.getStorageItemType());
    		ic.setComponentQuantity(item.getQuantity());
    		voitems.add(ic);
    	}
    	inventoryBillVo.setPartList(voitems);
    	return inventoryBillVo;
    	
    }
    public static InventoryBill voToBo(InventoryBillVo inventoryBillVo)  throws Exception {
    	InventoryBill inventoryBill = new InventoryBill(); 
    	
    	try {
			BeanUtils.copyProperties(inventoryBill,inventoryBillVo);

		} catch (IllegalAccessException e) {
			e.printStackTrace();
			log.error(" InventoryBillServiceUtils.voToBo fail! " + e.getMessage());
			throw new Exception(" InventoryBillServiceUtils.voToBo fail! "
					+ e.getMessage());
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			log.error(" InventoryBillServiceUtils.voToBo fail! " + e.getMessage());
			throw new Exception(" InventoryBillServiceUtils.voToBo fail! "
					+ e.getMessage());
		}
    	inventoryBill.setBillNo(inventoryBillVo.getBillNo());
    	inventoryBill.setOpUserName(inventoryBillVo.getOptName());
    	inventoryBill.setCreateDate(inventoryBillVo.getCreateDate());
    	inventoryBill.setBillType(inventoryBillVo.getBillType());
    	inventoryBillVo.getPartList();
    	if ((inventoryBillVo.getPartList()!=null)&&(inventoryBillVo.getPartList().size()>0)) {
    		Set invitems = new HashSet();
    		for (int i=0;i<inventoryBillVo.getPartList().size();i++) {
    			InventoryBillItem ibi = new InventoryBillItem();
    			InventoryComponent ic = (InventoryComponent)inventoryBillVo.getPartList().get(i);
    			ibi.setStorageItemId(Long.parseLong(ic.getComponentId()));
    			ibi.setStorageItemType(ic.getComponentType());
    			ibi.setQuantity(ic.getComponentQuantity());  
    			invitems.add(ibi);
    		}
    		inventoryBill.setBillItems(invitems);
    	}
    	
    	return inventoryBill;
    	
    }
}
