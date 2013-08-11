package com.nci.cp.core.sysmgt.dao;

import java.util.Collection;
import java.util.List;

import com.nci.cp.core.dao.IDao;
import com.nci.cp.core.exception.DaoException;
import com.nci.cp.core.sysmgt.model.IUserInfo;
import com.nci.cp.core.sysmgt.model.OrgVO_Corp;
import com.nci.cp.core.sysmgt.model.OrgVO_Department;
import com.nci.cp.core.sysmgt.model.Role;
import com.nci.cp.core.sysmgt.model.User;
/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-8-8 
 */
public interface IUserDao extends IDao {
	
    public List<IUserInfo> authenticationUser(IUserInfo iUser) throws DaoException;
    
    public List<Role> findRolesOfUser(IUserInfo user) throws DaoException;
    
    /**
     * 修改用户密码
     * @param userVo
     * @throws DaoException
     */
    public void modifyPassWord(User user, String newPassWord) throws DaoException;
    /**
     * 获取所有用户 
     * @param userVo
     * @throws DaoException
     */
  //  public List<User> getAllUser() throws DaoException;
  
	public Collection fndAllDep(String orgBCFL)  ;
	public OrgVO_Department fndDepartment(String code) ;
    public OrgVO_Corp findCorpByCode(String corpCode) ;
	public OrgVO_Corp saveCorp(OrgVO_Corp orgVO_Corp) ;
}
