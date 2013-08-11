package com.bluecreation.erp.inventory.model;

import java.util.HashSet;
import java.util.Set;

import com.nci.cp.core.model.AbstractEntity;

public class InventoryBill extends AbstractEntity {
	protected String billNo;
	protected String opUserName;
	protected int  billType;  //0为入库，1为出库
	protected int  billState; //0为编写状态，1为待审核状态，2为通过审核状态
	protected Set  billItems=new HashSet();
	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
    
	public String getOpUserName() {
		return opUserName;
	}

	public void setOpUserName(String opUserName) {
		this.opUserName = opUserName;
	}
    
	public int getBillType() {
		return billType;
	}

	public void setBillType(int billType) {
		this.billType = billType;
	}

	public int getBillState() {
		return billState;
	}

	public void setBillState(int billState) {
		this.billState = billState;
	}

	public Set getBillItems() {
		return billItems;
	}

	public void setBillItems(Set billItems) {
		this.billItems = billItems;
	}
    
}
