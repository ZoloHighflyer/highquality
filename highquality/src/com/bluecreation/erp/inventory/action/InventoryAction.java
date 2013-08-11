package com.bluecreation.erp.inventory.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bluecreation.erp.inventory.service.IInventoryService;
import com.bluecreation.erp.inventory.vo.InventoryVo;
import com.nci.cp.core.web.AbstractAction;

public class InventoryAction extends AbstractAction {
	private static Log log = LogFactory.getLog(InventoryAction.class);
	private IInventoryService inventoryService;
    private InventoryVo       inventoryVo;
    
	public void setInventoryService(IInventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}

	public InventoryVo getInventoryVo() {
		return inventoryVo;
	}

	public void setInventoryVo(InventoryVo inventoryVo) {
		this.inventoryVo = inventoryVo;
	}
	
	public String inventoryMgmt() throws Exception {
		if (inventoryVo==null)  {
			inventoryVo= new InventoryVo();
			inventoryVo.setPage(inventoryService.findAllInventoryVosByPage(null));
		}else {
			inventoryVo.setPage(inventoryService.findAllInventoryVosByPage(inventoryVo.getPage()));
		}
		
		return SUCCESS;
	}
}
