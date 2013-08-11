package com.bluecreation.erp.inventory.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bluecreation.erp.inventory.service.IInventoryBillService;
import com.bluecreation.erp.inventory.vo.InventoryBillVo;
import com.bluecreation.erp.productmgt.service.IProductMgmtService;
import com.nci.cp.core.sysmgt.model.IUserInfo;
import com.nci.cp.core.utils.WebUtils;
import com.nci.cp.core.web.AbstractAction;

public class AuditInventoryBillMgmtAction extends AbstractAction {
	private static Log log = LogFactory.getLog(InventoryBillAction.class);
	private IInventoryBillService  inventoryBillService;
	protected IProductMgmtService productmgmtService;
	private   InventoryBillVo        inventoryBillVo;
	private   List        components;
	protected List        quantities;
	
	public void setInventoryBillService(IInventoryBillService inventoryBillService) {
		this.inventoryBillService = inventoryBillService;
	}
    
	public void setProductmgmtService(IProductMgmtService productmgmtService) {
		this.productmgmtService = productmgmtService;
	}

	public InventoryBillVo getInventoryBillVo() {
		return inventoryBillVo;
	}

	public void setInventoryBillVo(InventoryBillVo inventoryBillVo) {
		this.inventoryBillVo = inventoryBillVo;
	}
    
	public List getComponents() {
		return components;
	}

	public void setComponents(List components) {
		this.components = components;
	}
    
	public List getQuantities() {
		return quantities;
	}

	public void setQuantities(List quantities) {
		this.quantities = quantities;
	}
	public String inventoryBillMgmt() throws Exception{
		if (inventoryBillVo==null) {
			inventoryBillVo = new InventoryBillVo();
			
			inventoryBillVo.setPage(inventoryBillService.findInventoryBillsByUserName((String)WebUtils.getCurrentUser().getProperty(IUserInfo.USER_ID), null));
		}else {
			inventoryBillVo.setPage(inventoryBillService.findInventoryBillsByUserName((String)WebUtils.getCurrentUser().getProperty(IUserInfo.USER_ID), inventoryBillVo.getPage()));
		}
		return SUCCESS;
	}
}
