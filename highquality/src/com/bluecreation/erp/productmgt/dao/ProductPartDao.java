package com.bluecreation.erp.productmgt.dao;

import com.bluecreation.erp.productmgt.model.ProductPart;
import com.nci.cp.core.dao.AbstractDao;

public class ProductPartDao extends AbstractDao implements IProductPartDao {

	@Override
	protected Class getDaoModel() {
		// TODO Auto-generated method stub
		return ProductPart.class;
	}

}
