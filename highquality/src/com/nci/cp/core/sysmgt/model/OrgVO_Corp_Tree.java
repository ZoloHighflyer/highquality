package com.nci.cp.core.sysmgt.model;

import com.nci.cp.core.sysmgt.helper.StringHelper;


public class OrgVO_Corp_Tree
{
  public static final int FCLEN = 5; //����ֲ㳤��
  private String orgBCFL;
  private String orgSCFL;
  private String orgType;
  private String orgName;
  private String orgCode;
  private String corpCode;
 // private OrgBaseVO orgType = null;
  private String flgSysUser;
  private String flgDeleted;
  private String showOrder;
  private String flgPrincipal;
  private String pxm;  //������
  private String prOrgCode; //��һ�������
  private String expand="N";

  
/**
 * @return Returns the prOrgCode.
 */
public String getPrOrgCode() {
	return prOrgCode;
}
/**
 * @param prOrgCode The prOrgCode to set.
 */
public void setPrOrgCode(String prOrgCode) {
	this.prOrgCode = prOrgCode;
}
/**
 * @return Returns the flgPrincipal.
 */
public String getFlgPrincipal() {
	return flgPrincipal;
}
/**
 * @param flgPrincipal The flgPrincipal to set.
 */
public void setFlgPrincipal(String flgPrincipal) {
	this.flgPrincipal = flgPrincipal;
}
  public OrgVO_Corp_Tree()
  {
  }

  public void setOrgBCFL(String orgBCFL)
  {
    this.orgBCFL = orgBCFL;
  }

  public String getOrgBCFL()
  {
    return (this.orgBCFL);
  }

  public void setOrgSCFL(String orgSCFL)
  {
    this.orgSCFL = orgSCFL;
  }

  public String getOrgSCFL()
  {
    return StringHelper.convertStringNull(this.orgSCFL);
  }

  public void setOrgType(String orgType)
  {
    this.orgType = orgType;
  }

  public String getOrgType()
  {
    return (this.orgType);
  }

  public void setOrgName(String orgName)
  {

    if (orgName != null)
      orgName = orgName.replace('\'', '\"');
    this.orgName = orgName;
  }

  public String getOrgName()
  {
    orgName = orgName == null ? null : orgName.trim();
    return (this.orgName);
  }

  public void setOrgCode(String orgCode)
  {
    this.orgCode = orgCode;
  }

  public String getOrgCode()
  {
    return (this.orgCode);
  }

  public void setCorpCode(String corpCode)
  {
    this.corpCode = corpCode;
  }

  public String getCorpCode()
  {
    return this.corpCode;
  }

 /* public OrgBaseVO getOrgObjVO()
  {
    return orgObjVO;
  }

  public void setOrgObjVO(OrgBaseVO orgObj)
  {
    this.orgObjVO = orgObj;
  }*/
  public String getFlgSysUser() {
    return flgSysUser;
  }
  public void setFlgSysUser(String flgSysUser) {
    this.flgSysUser = flgSysUser;
  }
  public String getFlgDeleted() {
    return flgDeleted;
  }
  public void setFlgDeleted(String flgDeleted) {
    this.flgDeleted = flgDeleted;
  }
  public String getShowOrder() {
    return StringHelper.convertStringNull(showOrder);
  }

  public void setShowOrder(String showOrder) {
      this.showOrder = showOrder;
    }

  public void setPxm(String pxm) {
    this.pxm = pxm;
  }

  public String getPxm() {
      if (pxm == null)
          return "";
      else
        return pxm;
    }
  
  /**
   * ת��Ϊ�ַ������չʾ���
   * @return
   */
  public String toString()
  {

    return orgName;
  }
   public String getExpand() {
     return expand;
   }
   public void setExpand(String expand) {
     this.expand = StringHelper.convertStringNull(expand);
   }

}