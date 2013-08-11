package com.nci.cp.core.sysmgt.model;

import java.io.ByteArrayInputStream;

import com.nci.cp.core.sysmgt.helper.StringHelper;

/**
 *
 * <p>����: weboa�칫ϵͳ</p>
 * <p>����: ְԱVO</p>
 * <p>��Ȩ: Copyright (c) 2003</p>
 * <p>��˾: �㶫�Ϸ�ͨ�Ÿ߿Ƽ����޹�˾</p>
 * @���� luoshiqiang
 * @�汾 1.0
 */
public class OrgVO_Person 
{

  public static final String ORG_TYPE_PERSON = "YG";
  private String personCode;
  private String personName;
  private String businessId;
  private String logonId;
  private String personPwd;
  private String msgServer;
  private String mobileNumber;
  private String mobileSwitch = "N";
  private String flgSysUser;
  private String flgSameProvider;
  private String delFlag = "N";
  private String corpCode;
  private String orgSCFL = "";
  private String flgDefaultID = "";
  private String orgBCFL;
  private String showOrder;
  private String xltPhone;
  private String flgSMS;
  private String telphone;
  private String flgSign;
  private String signText;
  private java.io.ByteArrayInputStream signImage;
  private String fax;
  private String noticeType;
  private String email;
  private String sapID;
  private String orgType;

/**
 * @return Returns the email.
 */
public String getEmail() {
	return StringHelper.convertStringNull(email);
}
/**
 * @param email The email to set.
 */
public void setEmail(String email) {
	this.email = email;
}
/**
 * @return Returns the oRG_TYPE_PERSON.
 */
public static String getORG_TYPE_PERSON() {
	return ORG_TYPE_PERSON;
}
  
/**
 * @return Returns the logonId.
 */
public String getLogonId() {
	return logonId;
}
/**
 * @param logonId The logonId to set.
 */
public void setLogonId(String logonId) {
	this.logonId = logonId;
}

 
  public void setLogonID(String userID)
  {
    this.logonId = userID;
  }

  public void setFlgSameProvider(String flgSameProvider)
  {
    this.flgSameProvider = flgSameProvider;
  }

  public String getFlgSameProvider()
  {
    flgSameProvider = flgSameProvider == null ? "" : flgSameProvider;
    return this.flgSameProvider;
  }

  public String getLogonID()
  {
    this.logonId = this.logonId == null ? "" : this.logonId.trim();
    return this.logonId;
  }

  public void setMobileSwitch(String flag)
  {
    this.mobileSwitch = flag;
  }

  public String getMobileSwitch()
  {
    return this.mobileSwitch;
  }

  public void setDelFlag(String flag)
  {

    delFlag = flag;

  }

  public String getDelFlag()
  {
    delFlag = delFlag == null ? "" : delFlag;
    return delFlag;
  }

  public void setMobileNumber(String mobile)
  {
    this.mobileNumber = mobile;
  }

  public String getMobileNumber()
  {
    mobileNumber = mobileNumber == null ? "" : mobileNumber.trim();
    return this.mobileNumber;
  }

  public OrgVO_Person()
  {
  }

  public void setPersonName(String personName)
  {
    this.personName = personName;
  }

  public String getPersonName()
  {
    personName = personName == null ? "" : personName.trim();
    return (this.personName);
  }

  public void setPersonCode(String personCode)
  {
    this.personCode = personCode;
  }

  public String getPersonCode()
  {
    personCode = personCode == null ? null : personCode.trim();
    return (this.personCode);
  }

  public void setMsgServer(String msgServer)
  {
    this.msgServer = msgServer;
  }

  public String getMsgServer()
  {
    msgServer = msgServer == null ? "" : msgServer.trim();
    return (this.msgServer);
  }

  public void setBusinessId(String businessId)
  {
    this.businessId = businessId;
  }

  public String getBusinessId()
  {
    businessId = businessId == null ? "" : businessId.trim();
    return (this.businessId);
  }

  public void setPersonPwd(String personPwd)
  {
    personPwd = personPwd == null ? "" : personPwd.trim();
    this.personPwd = personPwd;
  }

  public String getPersonPwd()
  {
    personPwd = personPwd == null ? "" : personPwd.trim();
    return (this.personPwd.trim());
  }

  public void setCorpCode(String corpCode)
  {
    this.corpCode = corpCode;
  }

  public String getCorpCode()
  {
    this.corpCode = this.corpCode == null ? "" : this.corpCode.trim();
    return this.corpCode;
  }

  public void setFlgSysUser(String flgSysUser)
  {
    this.flgSysUser = flgSysUser;
  }

  public String getFlgSysUser()
  {
    this.flgSysUser = this.flgSysUser == null ? "" : this.flgSysUser.trim();
    return this.flgSysUser;
  }

  public void setOrgSCFL(String orgSCFL)
  {
    this.orgSCFL = orgSCFL;
  }

  public String getOrgSCFL()
  {
    orgSCFL = orgSCFL == null ? "" : orgSCFL.trim();
    return this.orgSCFL;
  }

  public String getFlgDefaultID()
  {
    return  StringHelper.convertStringNull(flgDefaultID);
  }

  public void setFlgDefaultID(String flgDefaultID)
  {
    flgDefaultID=flgDefaultID==null ? "" : flgDefaultID.trim();
    this.flgDefaultID = flgDefaultID;
  }
  public String getOrgBCFL()
  {
    return  StringHelper.convertStringNull(orgBCFL);
  }
  public void setOrgBCFL(String orgBCFL)
  {
    this.orgBCFL = orgBCFL;
  }
  public String getShowOrder()
  {
    return  StringHelper.convertStringNull(showOrder);
  }
  public void setShowOrder(String showOrder)
  {
    this.showOrder = showOrder;
  }
  public String getXltPhone()
  {
    return StringHelper.convertStringNull( xltPhone );
  }
  public void setXltPhone(String xltPhone)
  {
    this.xltPhone = xltPhone;
  }
  public String getFlgSMS()
  {
    return  StringHelper.convertStringNull(flgSMS);
  }
  public void setFlgSMS(String flgSMS)
  {
    this.flgSMS = flgSMS;
  }
  public String getTelphone()
  {
    return StringHelper.convertStringNull(telphone);
  }
  public void setTelphone(String telphone)
  {
    this.telphone = telphone;
  }
  public String getFlgSign()
  {
    return flgSign;
  }
  public void setFlgSign(String flgSign)
  {
    this.flgSign = flgSign;
  }
  public String getSignText()
  {
    return StringHelper.convertStringNull(signText);
  }
  public void setSignText(String signText)
  {
    this.signText = signText;
  }
  public ByteArrayInputStream getSignImage()
  {
    return signImage;
  }
  public void setSignImage(ByteArrayInputStream signImage)
  {
    this.signImage = signImage;
  }
  public String getFax()
  {
    return StringHelper.convertStringNull(fax);
  }
  public void setFax(String fax)
  {
    this.fax = fax;
  }

 
public String getSapID() {
	return sapID;
}
public void setSapID(String sapID) {
	this.sapID = sapID;
}
/**
 * @return Returns the noticeType.
 */
public String getNoticeType() {
	return noticeType;
}
/**
 * @param noticeType The noticeType to set.
 */
public void setNoticeType(String noticeType) {
	this.noticeType = noticeType;
}
}