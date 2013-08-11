package com.nci.cp.core.sysmgt.dao;

import com.nci.cp.core.dao.AbstractDao;
import com.nci.cp.core.sysmgt.model.Role;
/**
 * @target  the interface for rolenode entity.
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-8-29 
 */
public class RoleDao extends AbstractDao implements IRoleDao{

	@Override
	protected Class getDaoModel() {
		// TODO Auto-generated method stub
		return Role.class;
	}
	

}
