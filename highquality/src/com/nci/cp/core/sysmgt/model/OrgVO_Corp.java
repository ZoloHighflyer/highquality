package com.nci.cp.core.sysmgt.model;

import com.nci.cp.core.sysmgt.helper.StringHelper;



/**
 *
 * <p>标题: weboa办公系统</p>
 * <p>描述: 公司VO</p>
 * <p>版权: Copyright (c) 2003</p>
 * <p>公司: 广东南方通信高科技有限公司</p>
 * @作者 luoshiqiang
 * @版本 1.0
 */
public class OrgVO_Corp{
   public static final String ORG_TYPE_CORPORATION = "GS";

  // public final static String ORG_CORP = "CP";

   private String corpCode = "";
   private String corpName = "";
   private String orgCode ="";
   private String msgServer = ""; //消息服务器地址
   private String flgSysUser = ""; //是否系统使用用户
   private String flgSameProvider = ""; //所用系统的开发商是否相同
   private String flgDeleted = "N";
   private String orgSCFL = "";
   private String orgBCFL;
   private String showOrder; //上层分类
   private String regionid="";
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
   public void setCorpCode(String corpCode)
   {
      this.corpCode = corpCode;
   }

   public String getCorpCode()
   {
      corpCode = corpCode == null ? "" : corpCode;
      return this.corpCode;
   }

   public void setCorpName(String corpName)
   {
      this.corpName = corpName;
   }

   public String getCorpName()
   {
      corpName = corpName == null ? "" : corpName;
      return this.corpName;
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

   /*
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
      public void setEmail(String email)
      {
     this.email=email;
      }
      public String getEmail()
      {
     return this.email;
      }
    */
   public void setFlgDeleted(String flgDeleted)
   {
      this.flgDeleted = flgDeleted;
   }

   public String getFlgDeleted()
   {
      flgDeleted = flgDeleted == null ? "" : flgDeleted;
      return this.flgDeleted;
   }

   public void setFlgSysUser(String flgSysUser)
   {
      this.flgSysUser = flgSysUser;
   }

   public String getFlgSysUser()
   {
      flgSysUser = flgSysUser == null ? "" : flgSysUser;
      return this.flgSysUser;
   }

   public void setMsgServer(String msgServer)
   {
      this.msgServer = msgServer;
   }

   public String getMsgServer()
   {
      msgServer = msgServer == null ? "" : msgServer;
      return this.msgServer;
   }

   public void setOrgSCFL(String orgSCFL)
   {
      this.orgSCFL = orgSCFL;
   }

   public String getOrgSCFL()
   {
      orgSCFL = orgSCFL == null ? "" : orgSCFL;
      return this.orgSCFL;
   }

   public String getOrgCode()
   {
     return orgCode;
   }
   public void setOrgCode(String orgCode)
   {
     this.orgCode = orgCode;
   }
  public String getOrgBCFL()
  {
    return StringHelper.convertStringNull(orgBCFL);
  }
  public void setOrgBCFL(String orgBCFL)
  {
    this.orgBCFL = orgBCFL;
  }
  public String getShowOrder()
  {
    return StringHelper.convertStringNull(showOrder);
  }
  public void setShowOrder(String showOrder)
  {
    this.showOrder = showOrder;
  }
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	public String getOrgType() {
		return orgType;
	}

}