package com.sunbow.website.view.vo;

import com.nci.cp.core.model.BaseVo;

public class ViewProductVo extends BaseVo {
	private String catelogName;
	private String productName;

	public String getCatelogName() {
		return catelogName;
	}

	public void setCatelogName(String catelogName) {
		this.catelogName = catelogName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}
