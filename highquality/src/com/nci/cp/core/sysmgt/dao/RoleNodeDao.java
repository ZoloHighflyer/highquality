package com.nci.cp.core.sysmgt.dao;

import java.util.List;

import org.hibernate.Query;

import com.nci.cp.core.dao.AbstractDao;
import com.nci.cp.core.exception.DaoException;
import com.nci.cp.core.sysmgt.model.Role;
import com.nci.cp.core.sysmgt.model.RoleNode;
/**
 * @target  the interface for rolenode entity.
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-8-29 
 */
public class RoleNodeDao extends AbstractDao implements IRoleNodeDao {

	@Override
	protected Class getDaoModel() {
		
		return RoleNode.class;
	}
    public boolean deleteRoleNodesByRoleId(Role role) throws DaoException {
   
    	Query q = this.getSession().createQuery("delete  from RoleNode nd where nd.role is null");
    	q.executeUpdate();
    	
    	return true;
    }
	
}
