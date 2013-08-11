package com.nci.cp.core.sysmgt.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import com.nci.cp.core.exception.ServiceException;
import com.nci.cp.core.sysmgt.helper.StringHelper;
import com.nci.cp.core.sysmgt.model.OrgVO_Corp;
import com.nci.cp.core.sysmgt.model.OrgVO_Corp_Tree;
import com.nci.cp.core.sysmgt.model.OrgVO_Department;
import com.nci.cp.core.sysmgt.model.OrgVO_Person;
import com.nci.cp.core.sysmgt.model.User;
import com.nci.cp.core.sysmgt.service.ISysManagement;
import com.nci.cp.core.sysmgt.vo.UserVo;
import com.nci.cp.core.utils.WebUtils;
import com.nci.cp.core.web.AbstractAction;
import com.nci.cp.core.web.IAction;
import com.nci.cp.ds.paging.Pagination;
/**
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-8-24 
 */
public class UserManagerAction extends AbstractAction {
	
	private static Log log = LogFactory.getLog(UserManagerAction.class);
	
	private UserVo  userVo;
	
	private ISysManagement sysmgmtService;
	
	public List<UserVo> userVos;
	
	public String newPassWord;
	
	public String errormessage;
	
	public Pagination pagi;

	public Pagination getPagi() {
		return pagi;
	}

	public void setPagi(Pagination pagi) {
		this.pagi = pagi;
	}

	public List<UserVo> getUserVos() {
		return userVos;
	}

	public void setUserVos(List<UserVo> userVos) {
		this.userVos = userVos;
	}

	public void setSysmgmtService(ISysManagement sysmgmtService) {
		this.sysmgmtService = sysmgmtService;
	}

	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}
	
	public String getNewPassWord() {
		return newPassWord;
	}

	public void setNewPassWord(String newPassWord) {
		this.newPassWord = newPassWord;
	}
	
	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

	/**
	 * 查询用户
	 * @return
	 * @throws ServiceException
	 */
	public String usersMain() throws ServiceException {
		// userVos = sysmgmtService.findUsers();
		// 分页查询
		if (pagi == null) {
			pagi = new Pagination();
		}
		userVos = sysmgmtService.findUsersByPage(pagi);
		return SUCCESS;
	}

//	public List getUserVos() throws ServiceException {
//		return sysmgmtService.findUsers();
//	}

	public String saveUser() throws ServiceException{
		userVo = sysmgmtService.createOrUpdateUser(userVo);	
		msg="selected";
		WebUtils.getServletRequest().setAttribute(IAction.ACTION_MESSAGE, "success");
		return SUCCESS;
	}
	
	public String initedit() throws Exception {
		if(userVo.getId() != null){
			userVo=sysmgmtService.findUserById(userVo);
		}


		return super.initedit();
	}	
	
	public String delete() throws Exception {
		if (userVo.getId() != null) {
			sysmgmtService.deleteUser(userVo);
		}
		return super.delete();
	}
	
	/**
	 * 修改密码
	 */
	public String modifyPassWord() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("GBK");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			
			HttpSession session = ServletActionContext.getRequest().getSession();
			User user = (User) session.getAttribute("_LOGON_SESSION_USER");
			userVo.setId(user.getId());
			String result = sysmgmtService.modifyPassWord(userVo, newPassWord);
			if ("false".equals(result)) {
				errormessage = "输入的旧密码不正确";
				// out.print("输入的旧密码不正确");
				return ERROR;
			} else {
				// out.print("密码修改成功！");
				errormessage = "密码修改成功！";
				return SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
			errormessage = "服务器错误";
			return ERROR;
		}
	}


	/**
	 * 查询用户
	 * @return
	 * @throws ServiceException
	 */
	public String selectUser() throws ServiceException {
		HttpServletRequest request=ServletActionContext.getRequest();
		//userVos = sysmgmtService.findUsers();
		// 分页查询
		  if (pagi == null) {
		 		pagi = new Pagination();
		 	}
		 	userVos = sysmgmtService.findUsersByPage(pagi);
		request.setAttribute("userVos", userVos);

		return SUCCESS;
		
	}
	
	 public String getOrgInfoXML() throws IOException {
			HttpServletResponse response = ServletActionContext.getResponse();
		    HttpServletRequest request=ServletActionContext.getRequest();
		    String CONTENT_TYPE = "text/xml;charset=GBK";
			String pOrgType = request.getParameter("orgType");
			String pOrgCode = request.getParameter("orgCode");    
			String pOrgBcfl = request.getParameter("orgBcfl");
			String flgPass =  request.getParameter("flgPass");
			
			if (pOrgBcfl==null) {
			pOrgBcfl="00001";
			}
			
			Document xmlDoc = null;
			response.setContentType(CONTENT_TYPE);
			PrintWriter out = response.getWriter();
			XMLOutputter output = new XMLOutputter("  ", true, "GB2312");
			//output.setEncoding("GB2312");
			try
			{
			//OrgJB_Tree jb = new OrgJB_Tree();
			Collection col = null;
			
			if (pOrgType == null || pOrgType.length()==0)
			{
				//默认打开页面
			col = sysmgmtService.fndAllDep(pOrgBcfl);
			}
			else if (flgPass!=null && flgPass.equalsIgnoreCase("Y"))
			{
				//从查找人员结果列表中点击进入页面
			//	col = sysmgmtService.fndAllDep(pOrgBcfl,pOrgType,pOrgCode);
			}
			else
			{
			//点击当前页面的部门或岗位
			//	col = sysmgmtService.fndAllSubDep(pOrgBcfl,pOrgType,pOrgCode);    	
			}
			
			if (col!=null && col.size()>0)
			{
			Element org = new Element("Orgs");
			xmlDoc = new Document(org);
			
			Iterator iter = col.iterator();
			
			while (iter.hasNext()){
			    OrgVO_Corp_Tree vo = (OrgVO_Corp_Tree)iter.next();
			
			      Element orgRS = new Element("org");
			      orgRS.setAttribute(new Attribute("orgName", StringHelper.convertStringNull(vo.getOrgName())));
			      orgRS.setAttribute(new Attribute("orgCode", StringHelper.convertStringNull(vo.getOrgCode())));
			      orgRS.setAttribute(new Attribute("orgType", StringHelper.convertStringNull(vo.getOrgType())));
			      orgRS.setAttribute(new Attribute("orgBCFL", StringHelper.convertStringNull(vo.getOrgBCFL())));
			      orgRS.setAttribute(new Attribute("flgMain", StringHelper.convertStringNull(vo.getFlgPrincipal())));
			      orgRS.setAttribute(new Attribute("px", StringHelper.convertStringNull(vo.getPxm())));
			      org.addContent(orgRS);            
			}        
			}
			
			if (xmlDoc!=null){
				 output.output(xmlDoc, out);
			}
			else{
				out.println("");      	
			}
			output = null;
			out.close();
			}
			catch (Exception e)
			{
			out.println("");
			e.printStackTrace();
			}
			return SUCCESS;
			}
	 
	 public String saveCorp() {
		 HttpServletResponse response = ServletActionContext.getResponse();
		 HttpServletRequest request=ServletActionContext.getRequest();
	     HttpSession session = request.getSession(false);
	   //  LogonVO logonVO = (LogonVO) session.getAttribute("logonVO");
	    /* String corpCodeNew = null;
	     if (logonVO != null)
	     {
	       corpCodeNew = logonVO.getCorpCode();
	     }
	     OrgVO_CorpAllInfo allInfo = new OrgVO_CorpAllInfo();*/

	     String BCFL = request.getParameter("BCFL");
	     String corpCode = request.getParameter("corpCode");
	     String orgCode = request.getParameter("orgCode");
	     String corpName = StringHelper.toChineseStr(request.getParameter("corpName"));
	     String flgSameProvider = request.getParameter("flgSameProvider");
	     String msgServer = StringHelper.toChineseStr(request.getParameter("msgServer"));

	     String flgSysUser = request.getParameter("flgSysUser");
	     String showOrder = request.getParameter("showOrder");
	     String flgDeleted = "N";
	     String region=request.getParameter("region");
	     String person=request.getParameter("person");
	     String unitAdmin = request.getParameter("unitAdmin");
	     String[] roleCode = request.getParameterValues("role");
	     String[] temp = null;
	     //----------------只处理保存公司部分  其他不处理
	     OrgVO_Corp orgVO_Corp = new OrgVO_Corp();
	     orgVO_Corp.setCorpCode(corpCode);
	     orgVO_Corp.setCorpName(corpName);
	     orgVO_Corp.setOrgCode(orgCode);
	     orgVO_Corp.setMsgServer(msgServer);
	     orgVO_Corp.setFlgSysUser(flgSysUser);
	     orgVO_Corp.setShowOrder(showOrder);
	     orgVO_Corp.setFlgDeleted(flgDeleted);
	     orgVO_Corp.setFlgSameProvider(flgSameProvider);
	     orgVO_Corp.setRegionid(region);
	     //公务管理员
	   /*  Collection coll = new ArrayList();
	     temp=StringHelper.parserString(person, "$#$");  
	     if(temp!=null)
	     {
	       for(int i=0;i<temp.length;i=i+1)
	        {
	         OrgVO_GW_Person vo = new OrgVO_GW_Person();
	         vo.setOrgCode(corpCode);
	         vo.setOrgType("GS");
	         vo.setPersonCode(temp[i]);
	         coll.add(vo);
	       }
	       OrgVO_GW_Person[] gws = new OrgVO_GW_Person[coll.size()];
	       int count =0;
	       Iterator ite = coll.iterator();
	      while (ite.hasNext())
	      {
	      	gws[count++] = (OrgVO_GW_Person) ite.next();
	      }
	      allInfo.setOrgVO_GW_Person(gws);
	     }*/
	     
	 //  部门管理员
	    /* String[] unitAdmins=StringHelper.parserString(unitAdmin, "$#$");  
	     if(unitAdmins!=null)
	     {
	     	OrgVO_Dept_Admin[] unitAdminCollection = new OrgVO_Dept_Admin[unitAdmins.length];

	       for(int i=0;i<unitAdmins.length;i=i+1)
	        {
	     	  OrgVO_Dept_Admin vo = new OrgVO_Dept_Admin();
	     	  vo.setAdminCode(unitAdmins[i]);
	     	  vo.setOrgCode(corpCode);
	     	  unitAdminCollection[i] = vo;
	       }
	       allInfo.setUnitAdmins(unitAdminCollection);
	     }
	     
	     if(coll!=null)coll.clear();*/
	     //权限
	    /* if (roleCode != null)
	     {
	       for (int i = 0; i < roleCode.length; i++)
	       {
	         RigVO_Rig_Role_Person rigVO_Rig_Role_Person = new RigVO_Rig_Role_Person();

	         rigVO_Rig_Role_Person.setCorpCode(corpCode);
	         rigVO_Rig_Role_Person.setOrgCode(orgCode);
	         rigVO_Rig_Role_Person.setOrgName(corpName);
	         rigVO_Rig_Role_Person.setOrgType("GS");
	         rigVO_Rig_Role_Person.setRoleCode(roleCode[i]);

	         coll.add(rigVO_Rig_Role_Person);
	       }
	        RigVO_Rig_Role_Person[] rig_persons = new RigVO_Rig_Role_Person[coll.size()];
	        int count =0;
	        Iterator ite = coll.iterator();
	       while (ite.hasNext())
	       {
	         rig_persons[count++] = (RigVO_Rig_Role_Person) ite.next();
	       }
	       allInfo.setRigVO_Role(rig_persons);
	     }*/
	     //set orgVO_Corp;
	    // allInfo.setOrgVO_Corp(orgVO_Corp);

	  
	      // try
	       // {
	        
	          sysmgmtService.saveCorp(orgVO_Corp);
	       /* }catch(Exception e)
	        {
	          e.printStackTrace();
	        }

	        this.nextView = "/OrgUI/msg.jsp?orgName=" +
	            com.gnct.weboa.common.helper.StringHelper.toStandardStr(corpName) + "&orgCode=" + orgCode +
	            "&orgType=" + orgVO_Corp.ORG_TYPE_CORPORATION + "&orgBCFL=" + BCFL +
	            "&action=edit";*/

	     return SUCCESS;
	   }
	
	 public String getOrgNodeInfo() {
		 HttpServletResponse response = ServletActionContext.getResponse();
		 HttpServletRequest request=ServletActionContext.getRequest();
		String orgCode=request.getParameter("code");
		String orgType=request.getParameter("orgType");
		String isSysOrg = request.getParameter("isSysOrg");
		
		request.setAttribute("orgType", orgType);
		request.setAttribute("code", orgCode);
		request.setAttribute("isSysOrg", isSysOrg);
		
		String catelog = "/OrgUI";
		if (isSysOrg != null && isSysOrg.equals("NO"))
		catelog = "/PartnerUI";
		
		
		if(orgType.equals(OrgVO_Corp.ORG_TYPE_CORPORATION)){
		  // this.nextView=catelog+"/OrgUI_CorpEntry.jsp?code="+orgCode;
			return "GS";
		 }
		
		if(orgType.equals(OrgVO_Department.ORG_TYPE_DEPARTMENT)){
		   //this.nextView=catelog+"/OrgUI_DepartmentEntry.jsp?code="+orgCode;
			return "BM";
		}
		
		//if(orgType.equals(OrgVO_Position.ORG_TYPE_POSITION)){
		//     this.nextView=catelog+"/OrgUI_PositionEntry.jsp?code="+orgCode;
		//}
		
		if(orgType.equals(OrgVO_Person.ORG_TYPE_PERSON)){
			  // this.nextView=catelog+"/OrgUI_PersonEntry.jsp?code="+orgCode;
			   return "YG";
		}
		
		
		return "GS";
		}
			
}
