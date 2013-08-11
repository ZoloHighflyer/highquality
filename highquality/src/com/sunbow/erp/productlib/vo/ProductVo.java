package com.sunbow.erp.productlib.vo;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.nci.cp.core.model.BaseVo;

public class ProductVo extends BaseVo {
	protected long   id =0;
	protected String productNo=null;
	protected String productName=null;
	//protected String productEnName=null;
	//产品类的id
	protected Long   productClassifyId;
	
	protected String picture=null;
	protected File  file=null;
	protected String fileContentType;
	protected String fileFileName;
	protected String classify="";
	
	//产品入货价格
	protected float  inPrice;
	//产品出货价格
	protected float  outPrice;
	//产品价格
	protected float  price;
	/*protected String type=null;
	protected String oemName=null;
	protected String aliasName=null;
	protected String otherType=null;
	protected String usedVendors=null;
	protected String usedCarModel=null;
	*/
	//产品包装
	//protected String propackage;
	//是否热销
	//protected int hotSale;
	/*protected float  height;
	//产品长度
	protected float  length;
	//产品重量
	protected float  weight;
	//产品单位
	protected String unit;*/
	
	protected Map attrs=null;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	
	public Long getProductClassifyId() {
		return productClassifyId;
	}
	public void setProductClassifyId(Long productClassifyId) {
		this.productClassifyId = productClassifyId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	/*public String getProductEnName() {
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
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}*/
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	/*public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getLength() {
		return length;
	}
	public void setLength(float length) {
		this.length = length;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}*/
	
	
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getInPrice() {
		return inPrice;
	}
	public void setInPrice(float inPrice) {
		this.inPrice = inPrice;
	}
	public float getOutPrice() {
		return outPrice;
	}
	public void setOutPrice(float outPrice) {
		this.outPrice = outPrice;
	}
	public Map getAttrs() {
		return attrs;
	}
	public void setAttrs(Map attrs) {
		this.attrs = attrs;
	}
    
	/*public String getPropackage() {
		return propackage;
	}
	public void setPropackage(String propackage) {
		this.propackage = propackage;
	}
	public int getHotSale() {
		return hotSale;
	}
	public void setHotSale(int hotSale) {
		this.hotSale = hotSale;
	}*/
	
	
}
