package com.nci.cp.core.sysmgt.dao;

import com.nci.cp.core.dao.AbstractDao;
import com.nci.cp.core.sysmgt.model.MenuItem;
/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-5-18 
 */
public class MenuItemDao extends AbstractDao implements IMenuItemDao {

	@Override
	protected Class getDaoModel() {
		// TODO Auto-generated method stub
		return MenuItem.class;
	}

}
