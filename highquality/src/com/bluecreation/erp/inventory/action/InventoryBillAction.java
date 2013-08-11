package com.bluecreation.erp.inventory.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bluecreation.erp.inventory.service.IInventoryBillService;
import com.bluecreation.erp.inventory.vo.InventoryBillVo;
import com.bluecreation.erp.inventory.vo.InventoryComponent;
import com.bluecreation.erp.productmgt.service.IProductMgmtService;
import com.bluecreation.erp.productmgt.vo.ProductComponent;
import com.nci.cp.core.sysmgt.model.IUserInfo;
import com.nci.cp.core.utils.CommonUtils;
import com.nci.cp.core.utils.Contants;
import com.nci.cp.core.utils.WebUtils;
import com.nci.cp.core.web.AbstractAction;
import com.nci.cp.core.web.IAction;

public class InventoryBillAction extends AbstractAction {
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

	public String addInventoryBill() throws Exception {
		inventoryBillVo = new InventoryBillVo();
		inventoryBillVo.setBillNo(CommonUtils.dateStr(new Date()));
		inventoryBillVo.setCreateDate(new Date());
		
		components = productmgmtService.findAllComponents();
    	return SUCCESS;
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
	public String waittingInventoryBillMgmt() throws Exception{
		if (inventoryBillVo==null) {
			inventoryBillVo = new InventoryBillVo();
			
			inventoryBillVo.setPage(inventoryBillService.findWaitingAuditInventoryBillsByPage(null));
		}else {
			inventoryBillVo.setPage(inventoryBillService.findWaitingAuditInventoryBillsByPage(inventoryBillVo.getPage()));
		}
		return SUCCESS;
	}
	public String auditedInventoryBillMgmt() throws Exception {
		if (inventoryBillVo==null) {
			inventoryBillVo = new InventoryBillVo();
			
			inventoryBillVo.setPage(inventoryBillService.findAuditedInventoryBills(null));
		}else {
			inventoryBillVo.setPage(inventoryBillService.findAuditedInventoryBills(inventoryBillVo.getPage()));
		}
		return SUCCESS;
	}
	 public String initeditBill()throws Exception {
		 
		 inventoryBillVo= inventoryBillService.findInventoryBillVoById(inventoryBillVo.getId());
		 components = productmgmtService.findAllComponents();
		 if (inventoryBillVo.getPartList()!=null) {
				for(int i=0;i<inventoryBillVo.getPartList().size();i++) {
					InventoryComponent ic = (InventoryComponent)inventoryBillVo.getPartList().get(i);				
					ic.setComponentId(ic.getComponentId()+"#"+ic.getComponentType());			
				}
				
			}
		 
		
		 return SUCCESS;
	 }
	public String saveInventoryBill() throws Exception {
		inventoryBillVo.setPartList(obtainComponents());
		
		IUserInfo userInfo=(IUserInfo)WebUtils.getSessionAttribute(Contants.LOGON_USER_KEY);
	//	log.info("================="+userInfo.getProperty(IUserInfo.USER_ID));
		inventoryBillVo.setOptName((String)userInfo.getProperty(IUserInfo.USER_ID));
		if ((inventoryBillVo.getAction()!=null)&&(inventoryBillVo.getAction().equals(IAction.ACTION_EDIT))) {
			inventoryBillService.updateInventoryBillVo(inventoryBillVo);
		}else {
			inventoryBillService.saveInventoryBillVo(inventoryBillVo);
		}
		
		return SUCCESS;
	}
	public String delInventoryBill() throws Exception {
		inventoryBillService.deleteInventoryBillVo(inventoryBillVo.getId());
		return SUCCESS;		
	}
	public String auditInventoryBill() throws Exception {
		inventoryBillService.auditInventoryBillVo(inventoryBillVo.getId());
		return SUCCESS;		
	}
	public String submitInventoryBill() throws Exception {
		inventoryBillService.submitInventoryBillVo(inventoryBillVo.getId());
		return SUCCESS;		
	}
	public String recedeInventoryBill() throws Exception {
		inventoryBillService.recedeInventoryBillVo(inventoryBillVo.getId());
		return SUCCESS;		
	}
	public String viewInventoryBill() throws Exception {
		 inventoryBillVo= inventoryBillService.findInventoryBillVoById(inventoryBillVo.getId());
		 components = productmgmtService.findAllComponents();
		 if (inventoryBillVo.getPartList()!=null) {
				for(int i=0;i<inventoryBillVo.getPartList().size();i++) {
					InventoryComponent ic = (InventoryComponent)inventoryBillVo.getPartList().get(i);
					ic.setComponentId(ic.getComponentId()+"#"+ic.getComponentType());			
				}
				
			}
		return SUCCESS;
	}
	private List obtainComponents() {
		List cps =new ArrayList();
		if (components!=null) {				
			for(int i=0;i<components.size();i++){
				 String cpstr = (String)components.get(i);
				 int idno=cpstr.indexOf("#");
				 InventoryComponent p = new InventoryComponent();
				 p.setComponentId(cpstr.substring(0,idno));					 
				 p.setComponentType(Integer.parseInt(cpstr.substring(idno+1,cpstr.length())));
				 String qstr =(String)quantities.get(i); 
				 int quantity =0;
				 try {
					quantity = Integer.parseInt(qstr);
				} catch (Exception e) {
					quantity =0;
				} 
			    p.setComponentQuantity(quantity);
			    cps.add(p);
			}
		}
		return cps;
	}
}
