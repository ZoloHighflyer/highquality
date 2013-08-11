package com.nci.cp.core.sysmgt.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;

import com.nci.cp.core.dao.AbstractDao;
import com.nci.cp.core.exception.DaoException;
import com.nci.cp.core.sysmgt.model.Group;
import com.nci.cp.core.sysmgt.model.Role;
import com.nci.cp.core.sysmgt.model.RoleToGroup;
import com.nci.cp.core.sysmgt.model.User;
import com.nci.cp.core.sysmgt.model.UserToGroup;
/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-8-24 
 */
public class GroupDao extends AbstractDao implements IGroupDao{
	private static Log log = LogFactory.getLog(GroupDao.class);
	@Override
	protected Class getDaoModel() {
		// TODO Auto-generated method stub
		return Group.class;
	}
	public boolean createUsersOfGroup(Group group,List users) throws DaoException {
	   for (int i=0;i<users.size();i++) {
		  UserToGroup utg =new UserToGroup();
		  utg.setGroupid(group.getId());
		  utg.setUserid(((User)users.get(i)).getId());
		  super.createEntity(utg);
	   }
	   return true;
	}
	public boolean createRolesOfGroup(Group group,List roles) throws DaoException {
		   group=(Group)this.findEntityById(group);
		   for (int i=0;i<roles.size();i++) {
			Role role = (Role)roles.get(i);
			RoleToGroup rtg = new RoleToGroup();
		    role=(Role)this.getHibernateTemplate().load(Role.class, role.getEntityId());
		    rtg.setRole(role);
		    rtg.setGroup(group);
		    super.createEntity(rtg);
		   }
		   return true;
		}
	public boolean updateGroup(Group group,List users) throws DaoException {
		   if ((users!=null)&&(users.size()>0)) {
			   List us = this.find("select utg from UserToGroup utg where  utg.groupid="+group.getId());
			   super.deleteEntities(us);
			   for(int i=0;i<users.size();i++) {
				       User u = (User)users.get(i);				   
					   UserToGroup utg = new UserToGroup();
					   utg.setUserid(u.getId());
					   utg.setGroupid(group.getId());
					   this.createEntity(utg);			 
			   }
			   
		   }		
		   return true;
	}
	public boolean updateRolesOfGroup(Group group,List roles) throws DaoException {
		 group=(Group)this.findEntityById(group);
		 if ((roles!=null)&&(roles.size()>0)) {
			   List rs = this.find("select rtg from RoleToGroup rtg where  rtg.group.id="+group.getEntityId());
			   super.deleteEntities(rs);
			   for(int i=0;i<roles.size();i++) {
				   Role r =(Role)roles.get(i);
				   RoleToGroup rtg =new RoleToGroup();
				   r=(Role)this.getHibernateTemplate().load(Role.class, r.getEntityId());
				   rtg.setRole(r);
				   rtg.setGroup(group);
				   this.createEntity(rtg);
			   }
		 }   
		return true;
	}
	public List<User> findUserOfGroup(Group group) throws DaoException {
		Query query = super.getSession().getNamedQuery("findUsersOfGroup");
		query.setLong("groupid", group.getId());
		List l = query.list();
		List users = new ArrayList();
	    if ((l!=null)&&(l.size()>0)) {
	    	
	    	for(int i=0;i<l.size();i++) {
	    		UserToGroup utg = (UserToGroup)l.get(i);
	    		User u = new User();
	    		u.setId(utg.getUserid());
	    		List us = this.find("select u from User u where u.id="+u.getId());
	    		if (us.size()>0) {
	    		  u = (User)us.get(0);	    		
	    		  users.add(u);
	    		}
	    	}
	    	
	    	
	    }
	    return users;
	}
	public List<Role> findRolesOfGroup(Group group)throws DaoException {
		Query query = super.getSession().getNamedQuery("findRolesOfGroup");
		query.setLong("groupid", group.getId());
		List roles = query.list();
		List rs  = new ArrayList();
		if ((roles!=null)&&(roles.size()>0)) {
			for(int i =0;i<roles.size();i++) {
				RoleToGroup rtg = (RoleToGroup)roles.get(i);
				Role r = (Role)this.getHibernateTemplate().load(Role.class, rtg.getRole().getEntityId());
				if (r!=null)
				   rs.add(r);
			}
		}
		
		return rs;
	}
	public boolean deleteGroup(Group group) throws DaoException {
		   
			   List us = this.find("select utg from UserToGroup utg where  utg.groupid="+group.getId());
			   super.deleteEntities(us);
			   Group g = (Group)super.findEntityById(group);	   
			   super.deleteEntity(g);
		   return true;
		}
	
	
}
