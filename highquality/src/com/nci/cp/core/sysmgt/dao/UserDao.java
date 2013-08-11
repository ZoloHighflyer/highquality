package com.nci.cp.core.sysmgt.dao;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;

import com.nci.cp.core.dao.AbstractDao;
import com.nci.cp.core.exception.DaoException;
import com.nci.cp.core.model.IdEntity;
import com.nci.cp.core.sysmgt.model.Group;
import com.nci.cp.core.sysmgt.model.IUserInfo;
import com.nci.cp.core.sysmgt.model.OrgVO_Corp;
import com.nci.cp.core.sysmgt.model.OrgVO_Corp_Tree;
import com.nci.cp.core.sysmgt.model.OrgVO_Department;
import com.nci.cp.core.sysmgt.model.Role;
import com.nci.cp.core.sysmgt.model.RoleToGroup;
import com.nci.cp.core.sysmgt.model.User;
import com.nci.cp.core.sysmgt.model.UserToGroup;
/**
 * The interface for user.
 * @company BlueCreation Workspace
 * @author  yanfeng 
 * @version 0.1
 * @date    2011-08-04 
 */
public class UserDao extends AbstractDao implements IUserDao {
	
	private static Log log = LogFactory.getLog(UserDao.class);
	
	@Override
	protected Class getDaoModel() {
		return User.class;
	}

	public List<IUserInfo> authenticationUser(IUserInfo user)
			throws DaoException {
		try{
			List entities = this.find("select u from " + getDaoModel().getName() + " u where u.userid='"+user.getProperty(IUserInfo.USER_ID)+"' and u.password='"+user.getProperty(IUserInfo.USER_PASS)+"'");
			return entities;
		}catch (DaoException e) {
			e.printStackTrace();
			log.error("UserDao.class:authenticationUser error!"
					+ getDaoModel());
			throw new DaoException(
					"UserDao.class:authenticationUser error!");
		}
	}
	
	/* (non-Javadoc)
	 * @see com.nci.cp.core.sysmgt.dao.IUserDao#findRolesOfUser(com.nci.cp.core.sysmgt.model.IUserInfo)
	 * 查找角色对应的菜单(注：各个角色对应的菜单可能会包含重复)
	 * 用户->用户组->角色
	 */
	public List<Role> findRolesOfUser(IUserInfo user) throws DaoException {
		List<IdEntity> users = this.find("select u from User u where u.userid='" + user.getProperty(IUserInfo.USER_ID) + "'");
		User usr = null;
		if ((users != null)&&(users.size()>0)) {
			usr = (User)users.get(0);
		}
		if (usr != null) {  
			List<IdEntity> utgs = this.find("select utg from UserToGroup utg where utg.userid=" + usr.getId());
			
			List<Group> groups = new ArrayList<Group>();
			// query group of user
			// 查找与user相关的用户组
			for (int i = 0;i < utgs.size(); i++) {
				UserToGroup utg = (UserToGroup)utgs.get(i);
				Group g = (Group)this.getHibernateTemplate().get(Group.class, utg.getGroupid());
				groups.add(g);
			}
			
			// 查找用户组对应的角色(注：各个用户组中可能会包含重复的角色)
			Set<Role> roleSet = new HashSet<Role>();
			for(int i = 0; i < groups.size(); i++) {
				Group g = (Group)groups.get(i);    		
				List<IdEntity> rtg = this.find("select rtg from RoleToGroup rtg where rtg.group.id = " + g.getId());
				for (int j = 0; j < rtg.size(); j++){
					RoleToGroup rtgentity = (RoleToGroup)rtg.get(j);
					Role role = rtgentity.getRole();
					System.out.println(role.getId());
					roleSet.add(role);
				}
			}
			
			List<Role> l = new ArrayList<Role>();
			for (Iterator<Role> i = roleSet.iterator(); i.hasNext();){
				Role r = (Role) i.next();
				l.add(r);
			}

			return l;
		}
		
		return new ArrayList();
	}
	
	/* (non-Javadoc)
	 * @see com.nci.cp.core.sysmgt.dao.IUserDao#modifyPassWord(com.nci.cp.core.sysmgt.vo.UserVo, java.lang.String)
	 */
	public void modifyPassWord(User user, String newPassWord) throws DaoException {
		user.getPassword();
		this.updateEntity(user);
	}

	/* 
	 * @获取所有 用户 
	 */
	
/*	public  List<User> getAllUser()	throws DaoException {
			try{
				List entities = this.find("select u from User u ");
				return entities;
			}catch (DaoException e) {
				e.printStackTrace();
			
				throw new DaoException(
						"UserDao.class:authenticationUser error!");
			}
	}
				*/
	
	/**
	 * 根据orgBCFL查找其父部门和子部门
	 */
	public Collection fndAllDep(String orgBCFL) {
		Collection col = new ArrayList();
		StringBuffer sql = new StringBuffer();
		String tmp = null;
		int len = orgBCFL.length();
		int limit = 5;
		sql.append(" select h.orgBCFL,h.orgName,h.orgSCFL,h.orgType,h.orgCode,h.corpCode,h.px,h.showorder from (");
		while (true) {
			tmp = orgBCFL.substring(0, limit);
			sql
					.append(
							" select if(t.orgType='GW', concat(t.orgSCFL,'y'), if(t.orgType='YG', concat(t.orgSCFL,concat(t.orgSCFL,'z')),  orgBCFL ) )  px," +
					        " t.orgBCFL,t.orgName,t.orgSCFL,t.orgType,t.orgCode,t.corpCode,t.showorder from org_corp_tree t")
						//	" t.* from org_corp_tree t")
					//           .append(" where instr('")
					//           .append(tmp)
					//           .append("',orgBCFL)=1 and flgdeleted='N' ")
					.append(" where orgBCFL=substr('")
					.append(tmp)
					.append("',1,length(orgBCFL)) and flgdeleted='N' ")
					.append(" union ")
					.append(
							" select if(t.orgType='GW', concat(t.orgSCFL,'y'), if(t.orgType='YG', concat(t.orgSCFL,concat(t.orgSCFL,'z')),  orgBCFL ) )  px," +
							"  t.orgBCFL,t.orgName,t.orgSCFL,t.orgType,t.orgCode,t.corpCode,t.showorder  from org_corp_tree t")
							//"t.* from org_corp_tree t")
					.append(" where orgSCFL='").append(tmp).append(
							"' and flgdeleted='N' ");
			limit += 5;
			if (limit <= len) {
				sql.append("union");
			} else {
				break;
			}
		}
		sql.append(") h order by px,showorder");
	
			SQLQuery query = this.getSession().createSQLQuery(sql.toString());
			List list = query.list();
			if(null != list && !list.isEmpty()){
				for(int i=0;i<list.size();i++){
					
					OrgVO_Corp_Tree orgVO_Corp_tree = new OrgVO_Corp_Tree();
					                    
					Object   obj[]   =   (Object[]) list.get(i);
					String   orgbcfl   =   (String)obj[0]; 
					String   orgName   =   (String)obj[1]; 
					String   orgSCFL   =   (String)obj[2]; 
					String   orgType  =   (String)obj[3]; 
					String   orgCode  =   (String)obj[4];
					String   corpCode  =   (String)obj[5];
					
					orgVO_Corp_tree.setOrgBCFL(orgbcfl);
					orgVO_Corp_tree.setOrgName(orgName);
					orgVO_Corp_tree.setOrgSCFL(orgSCFL);
					orgVO_Corp_tree.setOrgType(orgType);
					orgVO_Corp_tree.setOrgCode(orgCode);
					orgVO_Corp_tree.setCorpCode(corpCode);
					col.add(orgVO_Corp_tree);
				}
			}
			
		

		return col;

	}
	  
	   public OrgVO_Department fndDepartment(String code) {
	   	 OrgVO_Department orgVO_Department = new OrgVO_Department();
	      String sqlStr = 
	    	   "select a.depCode,a.depName,a.msgServer,a.flgSysUser, a.flgSameProvider,a.flgDeleted,a.corpCode, " +
	      		" a.showOrder,a.bureauShortName,a.flgSysUser,a.regionid, b.orgbcfl,b.orgscfl from org_department a,org_corp_tree b  " +
	      		" where a.depCode =b.orgCode  and a.depCode='" + code + "'";
		   
	      SQLQuery query = this.getSession().createSQLQuery(sqlStr.toString());
	      List list = query.list();
	      if(null != list && !list.isEmpty()){
		      Object   obj[]   =   (Object[]) list.get(0);
			  String   depCode   =   (String)obj[0]; 
			  String   depName   =   (String)obj[1]; 
			  String   msgServer   =   (String)obj[2]; 
			  String   flgSysUser  =   (String)obj[3]; 
			  String   flgSameProvider  =   (String)obj[4];
			  String   flgDeleted  =   (String)obj[5];		  
			  String   corpCode  =   (String)obj[6];
			  String   showOrder  =   (String)obj[7];
			  String   bureauShortName  =   (String)obj[8];
			  String   orgBCFL  =   (String)obj[9];
			  String   orgSCFL  =   (String)obj[10];
			  String   regionid  =   (String)obj[11];
						
			  orgVO_Department.setDepCode(depCode);
	          orgVO_Department.setDepName(depName);
	          orgVO_Department.setMsgServer(msgServer);
	          orgVO_Department.setFlgSysUser(flgSysUser);
	          orgVO_Department.setFlgSameProvider(flgSameProvider);
	          orgVO_Department.setFlgDeleted(flgDeleted);
	          orgVO_Department.setCorpCode(corpCode);
	          orgVO_Department.setShowOrder(showOrder);
	  		  orgVO_Department.setBureauShortName(bureauShortName);
	          orgVO_Department.setOrgBCFL(orgBCFL);
	          orgVO_Department.setOrgSCFL(orgSCFL);
	          orgVO_Department.setRegionid(regionid);
	      }	    
	    
	      return orgVO_Department;
	   }

	   
	   public OrgVO_Corp findCorpByCode(String corpCode) {
	     String sqlStr = "select c.corpCode,c.corpName,c.msgServer,c.flgSysUser,c.flgSameprovider,c.showOrder, " +
	     		" d.orgBCFL,d.orgSCFL,c.regionid from org_corp c,org_corp_tree d where" +
	     		" c.corpCode = d.orgCode and c.corpCode='" + corpCode + "'";
	     OrgVO_Corp orgVO_Corp = new OrgVO_Corp() ;
	     SQLQuery query = this.getSession().createSQLQuery(sqlStr.toString());
	     List list = query.list();
	     if(null != list && !list.isEmpty()){
	    	 Object   obj[]   =   (Object[]) list.get(0);
	    	  String   corpcode   =   (String)obj[0]; 
			  String   corpName   =   (String)obj[1]; 
			  String   msgServer   =   (String)obj[2]; 
			  String   flgSysUser  =   (String)obj[3]; 
			  String   flgSameProvider  =   (String)obj[4];
			  String   showOrder  =   (String)obj[5];		  
			  String   orgBCFL  =   (String)obj[6];
			  String   orgSCFL  =   (String)obj[7];
			  String   regionid  =   (String)obj[8];
	       
	         orgVO_Corp.setCorpCode(corpcode);
	         orgVO_Corp.setCorpName(corpName);
	         orgVO_Corp.setMsgServer(msgServer);
	         orgVO_Corp.setFlgSysUser(flgSysUser);
	         orgVO_Corp.setFlgSameProvider(flgSameProvider);
	         orgVO_Corp.setShowOrder(showOrder);
	         orgVO_Corp.setOrgBCFL(orgBCFL);
	         orgVO_Corp.setOrgSCFL(orgSCFL);
	         orgVO_Corp.setRegionid(regionid);
	       }
	     return orgVO_Corp;
	   }
	   
	   public OrgVO_Corp saveCorp(OrgVO_Corp orgVO_Corp) {
		    //这里简单 处理 为 只更新公司名称，后续可修改为用updateEntity（orgVO_Corp）   ---to huaxiong 
		   String sqlStr =
		        "update org_corp set corpName='" + orgVO_Corp.getCorpName() + "' where corpCode='" + orgVO_Corp.getCorpCode() + "'";
		       SQLQuery query = this.getSession().createSQLQuery(sqlStr.toString());
		   return orgVO_Corp;
		   }
}
