package com.bluecreation.erp.inventory.model;

import com.nci.cp.core.model.AbstractEntity;

public class InventoryBillItem extends AbstractEntity {
    protected  InventoryBill inventoryBill;
    protected  long storageItemId;
    protected  int  storageItemType; //0是配件        1是产品
    protected  int  quantity;
	public InventoryBill getInventoryBill() {
		return inventoryBill;
	}
	public void setInventoryBill(InventoryBill inventoryBill) {
		this.inventoryBill = inventoryBill;
	}
	public long getStorageItemId() {
		return storageItemId;
	}
	public void setStorageItemId(long storageItemId) {
		this.storageItemId = storageItemId;
	}
	public int getStorageItemType() {
		return storageItemType;
	}
	public void setStorageItemType(int storageItemType) {
		this.storageItemType = storageItemType;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
    
}
