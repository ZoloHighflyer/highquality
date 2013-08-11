package com.sunbow.website.view.dao;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;

import com.nci.cp.core.dao.AbstractDao;
import com.nci.cp.core.exception.DaoException;
import com.nci.cp.core.model.AbstractEntity;
import com.nci.cp.ds.paging.Pagination;
import com.sunbow.erp.productlib.model.ProductClassify;
import com.sunbow.website.view.model.SiteView;
import com.sunbow.website.view.model.ViewProduct;

public class ViewDao extends AbstractDao implements IViewDao {
	private static Log log = LogFactory.getLog(ViewDao.class);
	@Override
	protected Class getDaoModel() {		
		return ViewProduct.class;
	}
	public Pagination findSiteViewsByPage( Pagination page) throws DaoException  {
		   String hsql="select s from SiteView s order by s.modifyDate desc"; 
		   return super.findEntitiesByPage(hsql, page);
	}
	
	public SiteView findSiteViewById(long viewid)throws DaoException  {
		return (SiteView)this.getHibernateTemplate().get(SiteView.class, new Long(viewid));
		
	}
	public SiteView findSiteViewByViewId(String viewId)throws DaoException  {
		Query q=super.getSession().createQuery("select sv from SiteView sv where sv.viewId='"+viewId+"' ");
		if(q.list().size()>0){
			return (SiteView)q.list().get(0);
		}
		return null;
	}
	public List<ViewProduct> findProductsByViewId(long cId) throws DaoException {
	    	Query q=super.getSession().createQuery("select vp from ViewProduct vp where vp.siteView.id="+cId+" order by vp.order asc");
			return q.list();
	}
	
	public SiteView updateSiteView(SiteView siteView)throws DaoException  {
		try {
			AbstractEntity ae = (AbstractEntity)siteView;
			AbstractEntity old = (AbstractEntity)this.getHibernateTemplate().get(SiteView.class, siteView.getEntityId());
			ae.setCreateDate(old.getCreateDate());
			ae.setModifyDate(new Date()); 
			super.getHibernateTemplate().merge(siteView);			
			return siteView;
		} catch (RuntimeException e) {
			e.printStackTrace();
			log.error(this.getClass().getName()+" updateSiteView fail,when update "
					+ siteView.toString());
			throw new DaoException(this.getClass().getName()+" can not update updateProductClassify!");
		}
	}
	public boolean deleteViewProductsByViewId(Long viewid)throws DaoException {
		Query q=super.getSession().createQuery("select vp from ViewProduct vp where vp.siteView.id="+viewid  );
		if(q.list().size()>0){
			super.deleteEntities(q.list());
			
		}else{
			log.error(this.getClass().getName()+" deleteViewProductsByViewId id is null,can not delete! ");	
			return false;
		}
		return true;
	}
	public boolean deleteSiteView(Long viewid)throws DaoException {
		Query q=super.getSession().createQuery("select sv from SiteView sv where sv.id="+viewid  );
		if(q.list().size()>0){
			SiteView sv =(SiteView)q.list().get(0);
			super.getHibernateTemplate().delete(sv);
		}else{
			log.error(this.getClass().getName()+" deleteSiteView id is null,can not delete! ");
			return false;
		}
		return true;
	}
}
