package com.sunbow.website.view.model;



import com.nci.cp.core.model.AbstractEntity;
import com.sunbow.erp.productlib.model.ProductEntity;
/**
 * The object is to storage product model.
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-10-29 
 */
public class ViewProduct extends AbstractEntity {
	protected int      order=0;
	protected SiteView siteView=null;
    protected ProductEntity productEntity=null;
	public SiteView getSiteView() {
		return siteView;
	}
	public void setSiteView(SiteView siteView) {
		this.siteView = siteView;
	}
	public ProductEntity getProductEntity() {
		return productEntity;
	}
	public void setProductEntity(ProductEntity productEntity) {
		this.productEntity = productEntity;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	
}
