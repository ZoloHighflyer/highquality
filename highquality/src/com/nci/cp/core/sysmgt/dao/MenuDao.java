package com.nci.cp.core.sysmgt.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nci.cp.core.dao.AbstractDao;
import com.nci.cp.core.sysmgt.model.Menu;
/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-5-18 
 */
public class MenuDao extends AbstractDao implements IMenuDao {
	private static Log log = LogFactory.getLog(MenuDao.class);
	@Override
	protected Class getDaoModel() {
		return Menu.class;
	}
	public List<Menu> findAllValidMenus() {
		
		return null;
	}
    
}
