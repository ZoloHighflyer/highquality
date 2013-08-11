package com.bluecreation.erp.inventory.vo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nci.cp.core.model.BaseVo;
import com.nci.cp.core.utils.CommonUtils;

public class InventoryBillVo extends BaseVo {
	protected  long   id;
	protected String billNo;
	protected String optName;
	protected Date   createDate;
	protected int    billType;;
	protected int    state;
	protected List   partList= new ArrayList();
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getOptName() {
		return optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}

	public String getCreateDateString() {
		if (createDate==null) return "";
		return CommonUtils.formatDate(createDate,new SimpleDateFormat("yyyy-MM-dd"));
	}
    public Date getCreateDate() {
    	return this.createDate;
    }
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
    
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
    
	public int getBillType() {
		return billType;
	}

	public void setBillType(int billType) {
		this.billType = billType;
	}

	public List getPartList() {
		return partList;
	}

	public void setPartList(List partList) {
		this.partList = partList;
	}
    
	
}
