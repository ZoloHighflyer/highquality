package com.bluecreation.erp.inventory.vo;

import java.util.Date;

import com.bluecreation.erp.productmgt.model.CommonComponent;
import com.nci.cp.core.model.BaseVo;

public class InventoryVo extends BaseVo {
    protected  long   id;
    protected  CommonComponent commCmpt;
    protected  long   quantity;
    protected  Date   createDate;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}		
    
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public CommonComponent getCommCmpt() {
		return commCmpt;
	}
	public void setCommCmpt(CommonComponent commCmpt) {
		this.commCmpt = commCmpt;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
    
    
}
