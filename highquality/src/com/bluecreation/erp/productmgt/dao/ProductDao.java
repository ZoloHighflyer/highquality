package com.bluecreation.erp.productmgt.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.bluecreation.erp.productmgt.model.Product;
import com.nci.cp.core.dao.AbstractDao;
import com.nci.cp.core.exception.DaoException;
import com.nci.cp.core.model.IdEntity;
import com.nci.cp.ds.paging.Pagination;
/**
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-10-31 
 */
public class ProductDao extends AbstractDao implements IProductDao {

	@Override
	protected Class getDaoModel() {
		// TODO Auto-generated method stub
		return Product.class;
	}
	
	@Override
	public IdEntity updateEntity(IdEntity entity) throws DaoException {
		
		entity =super.updateEntity(entity);
		Query q = this.getSession().createQuery("delete  from ProductPart pp where pp.product is null");
    	q.executeUpdate();
		return entity;
	}
	
}
