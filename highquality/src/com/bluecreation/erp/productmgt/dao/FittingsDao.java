package com.bluecreation.erp.productmgt.dao;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.bluecreation.erp.productmgt.model.Fittings;
import com.nci.cp.core.dao.AbstractDao;

/**
 * The object is to storage fittings dao .
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-10-20 
 */
public class FittingsDao extends AbstractDao implements IFittingsDao {
	private static Log log = LogFactory.getLog(FittingsDao.class);
	@Override
	protected Class getDaoModel() {
		return Fittings.class;
	}

	
}