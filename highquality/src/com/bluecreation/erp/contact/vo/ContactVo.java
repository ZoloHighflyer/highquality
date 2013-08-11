package com.bluecreation.erp.contact.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.nci.cp.core.model.BaseVo;
import com.nci.cp.core.utils.CommonUtils;

public class ContactVo extends BaseVo {
	protected  long   id;
	protected String name;         //联系人名字
    protected String shortName;    //联系人简称
    protected String mobile;       //客户在中国电话
    protected String telNo;        //客户电话
    protected String alternateTel; //备用电话
    protected String addr;         //客户地址
    protected String webSite;      //客户网址
    protected String email;        //客户邮箱
    protected String country;      //客户所属国家
    protected String city;         //客户所属城市
    protected String useProducts;  //客户主要采购产品
    protected int    level;        //客户信用等级
    protected String trustMsg;     //本公司对此客户评价
    protected String owner;        //创建
    protected String remark;        //备注
    protected Date   createDate;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getAlternateTel() {
		return alternateTel;
	}
	public void setAlternateTel(String alternateTel) {
		this.alternateTel = alternateTel;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getWebSite() {
		return webSite;
	}
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getUseProducts() {
		return useProducts;
	}
	public void setUseProducts(String useProducts) {
		this.useProducts = useProducts;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getTrustMsg() {
		return trustMsg;
	}
	public void setTrustMsg(String trustMsg) {
		this.trustMsg = trustMsg;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreateDateString() {	
		if (createDate==null) return "";
		return CommonUtils.formatDate(createDate,new SimpleDateFormat("yyyy-MM-dd"));
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
