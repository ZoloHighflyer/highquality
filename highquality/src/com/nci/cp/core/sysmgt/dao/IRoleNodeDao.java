package com.nci.cp.core.sysmgt.dao;

import com.nci.cp.core.dao.IDao;
import com.nci.cp.core.exception.DaoException;
import com.nci.cp.core.sysmgt.model.Role;
/**
 * @target  the interface for rolenode entity.
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-8-29 
 */
public interface IRoleNodeDao extends IDao {
	public boolean deleteRoleNodesByRoleId(Role role) throws DaoException;
}
