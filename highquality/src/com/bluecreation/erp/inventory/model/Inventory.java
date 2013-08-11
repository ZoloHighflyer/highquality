package com.bluecreation.erp.inventory.model;

import com.nci.cp.core.model.AbstractEntity;

public class Inventory extends AbstractEntity {
	protected long  inventoryId;
	protected int   inventoryType; //0是配件        1是产品
	protected long quantity;

	public long getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(long inventoryId) {
		this.inventoryId = inventoryId;
	}

	public int getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(int inventoryType) {
		this.inventoryType = inventoryType;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

    
}
