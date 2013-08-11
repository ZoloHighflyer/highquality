package com.nci.cp.core.sysmgt.dao;

import java.util.List;

import com.nci.cp.core.dao.IDao;
import com.nci.cp.core.exception.DaoException;
import com.nci.cp.core.sysmgt.model.Group;
import com.nci.cp.core.sysmgt.model.Role;
import com.nci.cp.core.sysmgt.model.User;
/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-8-24 
 */
public interface IGroupDao extends IDao {
	
	public boolean createUsersOfGroup(Group group,List users) throws DaoException;
	
	public boolean createRolesOfGroup(Group group,List roles) throws DaoException;
	
	public List<User> findUserOfGroup(Group group) throws DaoException;
	
	public List<Role> findRolesOfGroup(Group group)throws DaoException;
	
	public boolean updateGroup(Group group,List users) throws DaoException;
	
	public boolean updateRolesOfGroup(Group group,List roles) throws DaoException;
	
	public boolean deleteGroup(Group group) throws DaoException;
}
