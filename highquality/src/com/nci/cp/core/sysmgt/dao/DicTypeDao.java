package com.nci.cp.core.sysmgt.dao;

import com.nci.cp.core.dao.AbstractDao;
import com.nci.cp.core.sysmgt.model.DicType;

public class DicTypeDao extends AbstractDao implements IDicTypeDao {

	@Override
	protected Class getDaoModel() {
		// TODO Auto-generated method stub
		return DicType.class;
	}

}
