package com.bluecreation.erp.inventory.dao;

import com.bluecreation.erp.inventory.model.InventoryBillItem;
import com.nci.cp.core.dao.AbstractDao;

public class InventoryBillItemDao extends AbstractDao implements IInventoryBillItemDao {

	@Override
	protected Class getDaoModel() {

		return InventoryBillItem.class;
	}

}
