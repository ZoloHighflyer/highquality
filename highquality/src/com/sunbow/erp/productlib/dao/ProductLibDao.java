package com.sunbow.erp.productlib.dao;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;

import com.nci.cp.core.dao.AbstractDao;
import com.nci.cp.core.exception.DaoException;
import com.nci.cp.core.model.AbstractEntity;
import com.nci.cp.ds.paging.Pagination;
import com.sunbow.erp.productlib.model.ProductClassify;
import com.sunbow.erp.productlib.model.ProductEntity;

public class ProductLibDao extends AbstractDao implements IProductLibDao {
	private static Log log = LogFactory.getLog(ProductLibDao.class);
	@Override
	protected Class getDaoModel() {		
		return ProductEntity.class;
	}
	
	public Pagination findProductsByPage( Pagination page) throws DaoException  {
           String conditions ="";           
           Long pcid =new Long(-1);
		   if (!page.getQuery().isEmpty()) {
			   pcid=(Long)page.getQuery().get("productClassify.id");
			   long lv =pcid.longValue();
			   if (lv==-1) {
				   conditions ="";
			   }else if(lv==0) {
				   conditions =" where e.productClassify.id=null";
			   }else {
				   conditions =" where e.productClassify.id="+pcid.longValue();
			   }			    
		   }		      
		   String hsql="select e from ProductEntity e "+conditions+" order by e.modifyDate desc"; 		
		   return super.findEntitiesByPage(hsql, page);		 
		   
	}
    
	public List findAllClassifies() throws DaoException {
		Query q=super.getSession().createQuery("select pc from ProductClassify pc  order by pc.id asc");
		return q.list();
	}	
	
	public List findAvailibleClassifies() throws DaoException {
		Query q=super.getSession().createQuery("select pc from ProductClassify pc where pc.id not in(select p.parCategoryNo from ProductClassify p )");
		return q.list();
	}
	
	public ProductClassify findProductClassifyById(long pcId) throws DaoException {
		
		return (ProductClassify)this.getHibernateTemplate().get(ProductClassify.class, new Long(pcId));
	}
	
    public List<ProductEntity> findProductsByClassifyId(long cId) throws DaoException {
    	Query q=super.getSession().createQuery("select pe from ProductEntity pe where pe.productClassify.id="+cId+" order by pe.id asc");
		return q.list();

	}
	public ProductClassify updateProductClassify(ProductClassify pc) throws DaoException {

		try {
			AbstractEntity ae = (AbstractEntity)pc;
			AbstractEntity old = (AbstractEntity)this.getHibernateTemplate().get(ProductClassify.class, pc.getEntityId());
			ae.setCreateDate(old.getCreateDate());
			ae.setModifyDate(new Date()); 
			super.getHibernateTemplate().merge(pc);			
			return pc;
		} catch (RuntimeException e) {
			e.printStackTrace();
			log.error("ProductLibDao.class updateProductClassify fail,when update "
					+ pc.toString());
			throw new DaoException("ProductLibDao.class can not update updateProductClassify!");
		}
	}
	
	public boolean deleteProductClassify(Long pcid)throws DaoException {
		Query q=super.getSession().createQuery("select pc from ProductClassify pc where pc.id="+pcid  );
		if(q.list().size()>0){
			ProductClassify pc =(ProductClassify)q.list().get(0);
			super.getHibernateTemplate().delete(pc);
		}else{
			log.error("ProductLibDao.class deleteProductClassify id is null,can not delete! ");	
			return false;
		}
		return true;
	}
	
}
