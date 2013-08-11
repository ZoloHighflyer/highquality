package com.nci.cp.core.sysmgt.dao;

import java.util.List;

import com.nci.cp.core.dao.IDao;
import com.nci.cp.core.sysmgt.model.Menu;
/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-5-18 
 */
public interface IMenuDao extends IDao {
    public List<Menu> findAllValidMenus();
}
