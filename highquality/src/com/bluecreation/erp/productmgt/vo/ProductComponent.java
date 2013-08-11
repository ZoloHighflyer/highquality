package com.bluecreation.erp.productmgt.vo;

public class ProductComponent {
	protected String componentId;
	protected String componentName;
	protected int    componentType;
	protected int    componentQuantity;
	protected boolean checked=false;
	public ProductComponent() {
		
	}
	public String getComponentId() {
		return componentId;
	}
	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public int getComponentQuantity() {
		return componentQuantity;
	}
	public void setComponentQuantity(int componentQuantity) {
		this.componentQuantity = componentQuantity;
	}
	public int getComponentType() {
		return componentType;
	}
	public void setComponentType(int componentType) {
		this.componentType = componentType;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
}

