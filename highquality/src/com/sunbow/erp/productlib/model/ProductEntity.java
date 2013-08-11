package com.sunbow.erp.productlib.model;



import java.util.HashMap;
import java.util.Map;

import com.nci.cp.core.model.AbstractEntity;
import com.nci.cp.core.model.CommonEntityExt;
/**
 * The object is to storage product model.
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-10-29 
 */
public class ProductEntity extends AbstractEntity {
	//产品编号
	protected String productNo=null;
	//产品名称
	protected String productName=null;
	//产品英文名称
//	protected String productEnName=null;
	//产品型号
//	protected String type=null;
	//产品OEM号
//	protected String oemName=null;
	//产品别称
//	protected String aliasName=null;
	//产品其他型号
//	protected String otherType=null;
	//适合厂商
//	protected String usedVendors=null;
	//适合车型
//	protected String usedCarModel=null;	
    //图片存放路径
	protected String picture=null;
	//产品高度
//	protected Float  height;
	//产品长度
//	protected Float  length;
	//产品重量
//	protected Float  weight;
	//产品单位
//	protected String unit;
	//产品官方价格
	protected Float  price;
	//产品入货价格
	protected Float  inPrice;
	//产品出货价格
	protected Float  outPrice;
	//产品包装
//	protected String propackage;
	//是否热销
//	protected Integer hotSale;
	protected Map extValues = new HashMap();
	
	//产品分类
	protected ProductClassify productClassify=null;
    


	public String getProductNo() {
		return productNo;
	}


	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


/*	public String getProductEnName() {
		return productEnName;
	}


	public void setProductEnName(String productEnName) {
		this.productEnName = productEnName;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getOemName() {
		return oemName;
	}


	public void setOemName(String oemName) {
		this.oemName = oemName;
	}


	public String getAliasName() {
		return aliasName;
	}


	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}


	public String getOtherType() {
		return otherType;
	}


	public void setOtherType(String otherType) {
		this.otherType = otherType;
	}


	public String getUsedVendors() {
		return usedVendors;
	}


	public void setUsedVendors(String usedVendors) {
		this.usedVendors = usedVendors;
	}


	public String getUsedCarModel() {
		return usedCarModel;
	}


	public void setUsedCarModel(String usedCarModel) {
		this.usedCarModel = usedCarModel;
	}
*/

	public String getPicture() {
		return picture;
	}


	public void setPicture(String picture) {
		this.picture = picture;
	}


	/*public Float getHeight() {
		return height;
	}


	public void setHeight(Float height) {
		this.height = height;
	}


	public Float getLength() {
		return length;
	}


	public void setLength(Float length) {
		this.length = length;
	}


	public Float getWeight() {
		return weight;
	}


	public void setWeight(Float weight) {
		this.weight = weight;
	}


	public String getUnit() {
		return unit;
	}


	public void setUnit(String unit) {
		this.unit = unit;
	}
*/

	public Float getPrice() {
		return price;
	}


	public void setPrice(Float price) {
		this.price = price;
	}
    
	
/*
	public String getPropackage() {
		return propackage;
	}


	public void setPropackage(String propackage) {
		this.propackage = propackage;
	}


	public Integer getHotSale() {
		return hotSale;
	}


	public void setHotSale(Integer hotSale) {
		this.hotSale = hotSale;
	}*/


	public Float getOutPrice() {
		return outPrice;
	}


	public void setOutPrice(Float outPrice) {
		this.outPrice = outPrice;
	}

    
	public Float getInPrice() {
		return inPrice;
	}


	public void setInPrice(Float inPrice) {
		this.inPrice = inPrice;
	}


	public ProductClassify getProductClassify() {
		return productClassify;
	}


	public void setProductClassify(ProductClassify productClassify) {
		this.productClassify = productClassify;
	}		
	
}
