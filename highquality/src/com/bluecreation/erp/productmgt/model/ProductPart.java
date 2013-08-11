package com.bluecreation.erp.productmgt.model;

import com.nci.cp.core.model.AbstractEntity;
/**
 * The object is to storage fittings model.
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-10-29 
 */
public class ProductPart extends AbstractEntity {
        protected  Product product;
        protected  long componentId;
        protected  int  componentType; //0是配件        1是产品

        protected  int  quantity;
		
		public Product getProduct() {
			return product;
		}
		public void setProduct(Product product) {
			this.product = product;
		}
		public long getComponentId() {
			return componentId;
		}
		public void setComponentId(long componentId) {
			this.componentId = componentId;
		}
		public int getComponentType() {
			return componentType;
		}
		public void setComponentType(int componentType) {
			this.componentType = componentType;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
         
         
}
