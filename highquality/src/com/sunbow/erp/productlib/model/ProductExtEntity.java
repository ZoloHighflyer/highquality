package com.sunbow.erp.productlib.model;



import com.nci.cp.core.model.AbstractEntity;
/**
 * The object is to storage product model.
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2013-03-12 
 */
public class ProductExtEntity extends AbstractEntity {	
	String brand="";
	String specification="";
	String crowdBound="";
	String ageBound="";
	String skinBound="";
	String feature="";
	String packDesr="";
	String efficacyDesr="";
	String elementName="";
	String usageDesr="";
	protected ProductEntity product=null;
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getCrowdBound() {
		return crowdBound;
	}
	public void setCrowdBound(String crowdBound) {
		this.crowdBound = crowdBound;
	}
	public String getAgeBound() {
		return ageBound;
	}
	public void setAgeBound(String ageBound) {
		this.ageBound = ageBound;
	}
	public String getSkinBound() {
		return skinBound;
	}
	public void setSkinBound(String skinBound) {
		this.skinBound = skinBound;
	}
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public String getPackDesr() {
		return packDesr;
	}
	public void setPackDesr(String packDesr) {
		this.packDesr = packDesr;
	}
	public String getEfficacyDesr() {
		return efficacyDesr;
	}
	public void setEfficacyDesr(String efficacyDesr) {
		this.efficacyDesr = efficacyDesr;
	}
	public String getElementName() {
		return elementName;
	}
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
	public String getUsageDesr() {
		return usageDesr;
	}
	public void setUsageDesr(String usageDesr) {
		this.usageDesr = usageDesr;
	}
	public ProductEntity getProduct() {
		return product;
	}
	public void setProduct(ProductEntity product) {
		this.product = product;
	}
    
    
	
	
	
	
}
