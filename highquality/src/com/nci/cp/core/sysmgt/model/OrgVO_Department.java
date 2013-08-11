package com.nci.cp.core.sysmgt.model;

import com.nci.cp.core.sysmgt.helper.StringHelper;

public class OrgVO_Department {
	public static final String ORG_TYPE_DEPARTMENT = "BM";

	private String depCode = "";
	private String depName = "";
	private String msgServer = ""; //消息服务器地址
	private String flgSysUser = ""; //是否系统使用用户
	private String flgSameProvider = ""; //所用系统的开发商是否相同
	private String flgDeleted = "N";
	private String orgSCFL = ""; //上层分类
	private String corpCode = "";
	private String orgBCFL;
	private String showOrder; //公司代码
	private String bureauShortName = ""; //分局的简称,只有2位
	private String regionid="";//区域
	private String orgType;

		/**
	 * @return Returns the regionid.
	 */
	public String getRegionid() {
		return regionid;
	}
	/**
	 * @param regionid The regionid to set.
	 */
	public void setRegionid(String regionid) {
		this.regionid = regionid;
	}
public void setFlgSameProvider(String flgSameProvider) {
		this.flgSameProvider = flgSameProvider;
	}

	public String getFlgSameProvider() {
		flgSameProvider = flgSameProvider == null ? "" : flgSameProvider;
		return this.flgSameProvider;
	}

	public void setCorpCode(String corpCode) {
		this.corpCode = corpCode;
	}

	public String getCorpCode() {
		corpCode = corpCode == null ? "" : corpCode;
		return this.corpCode;
	}

	public void setDepCode(String depCode) {
		this.depCode = depCode;
	}

	public String getDepCode() {
		depCode = depCode == null ? "" : depCode;
		return this.depCode;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getDepName() {
		depName = depName == null ? "" : depName;
		return this.depName;
	}

	public void setFlgDeleted(String flgDeleted) {
		this.flgDeleted = flgDeleted;
	}

	public String getFlgDeleted() {
		flgDeleted = flgDeleted == null ? "" : flgDeleted;
		return this.flgDeleted;
	}

	public void setFlgSysUser(String flgSysUser) {
		this.flgSysUser = flgSysUser;
	}

	public String getFlgSysUser() {
		flgSysUser = flgSysUser == null ? "" : flgSysUser;
		return this.flgSysUser;
	}

	public void setMsgServer(String msgServer) {
		this.msgServer = msgServer;
	}

	public String getMsgServer() {
		msgServer = msgServer == null ? "" : msgServer;
		return this.msgServer;
	}

	public void setOrgSCFL(String orgSCFL) {
		this.orgSCFL = orgSCFL;
	}

	public String getOrgSCFL() {
		orgSCFL = orgSCFL == null ? "" : orgSCFL;
		return this.orgSCFL;
	}
	public String getOrgBCFL() {
		return StringHelper.convertStringNull(orgBCFL);
	}
	public void setOrgBCFL(String orgBCFL) {
		this.orgBCFL = orgBCFL;
	}
	public String getShowOrder() {
		return StringHelper.convertStringNull(showOrder);
	}
	public void setShowOrder(String showOrder) {
		this.showOrder = showOrder;
	}

	/*
	   public void setEmail(String email)
	   {
	  this.email=email;
	   }
	   public String getEmail()
	   {
	  return this.email;
	   }
	  public void setLogonID(String logonID)
	   {
	  this.logonID=logonID;
	   }
	   public String getLogonID()
	   {
	  return this.logonID;
	   }
	   public void setPassword(String password)
	   {
	  this.password=password;
	   }
	   public String getPassword()
	   {
	  return this.password;
	   }
	 */

	/**
	 * @return
	 */
	public String getBureauShortName() {
		return StringHelper.convertStringNull(bureauShortName);
	}

	/**
	 * @param string
	 */
	public void setBureauShortName(String string) {
		bureauShortName = string;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	public String getOrgType() {
		return orgType;
	}

}