package com.sunbow.erp.productlib.model;

import com.nci.cp.core.model.AbstractEntity;

public class VendorInfo extends AbstractEntity {
	private String vendorName;
	private String typeName;
	private ProductEntity productEntity;
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public ProductEntity getProductEntity() {
		return productEntity;
	}
	public void setProductEntity(ProductEntity productEntity) {
		this.productEntity = productEntity;
	}
	
}
