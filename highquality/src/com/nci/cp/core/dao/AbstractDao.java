package com.nci.cp.core.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.nci.cp.core.exception.DaoException;
import com.nci.cp.core.model.AbstractEntity;
import com.nci.cp.core.model.IdEntity;
import com.nci.cp.ds.paging.Pagination;

/**
 * Common Platform Team
 * 
 * @author Oliver Chan
 * @since 0.1
 */
public abstract class AbstractDao extends HibernateDaoSupport implements IDao { 
	private static Log log = LogFactory.getLog(AbstractDao.class);

	protected abstract Class getDaoModel();

	public IdEntity createEntity(IdEntity entity) throws DaoException {
		try {
			AbstractEntity ae =(AbstractEntity)entity;
			Date now = new Date();
			ae.setCreateDate(now);
			ae.setModifyDate(now);
			super.getHibernateTemplate().save(entity);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("AbstractDao.class createEntity fail,when create "
					+ entity.toString());
			throw new DaoException("AbstractDao.class can not create entity!");
		}
	}

	public IdEntity findEntityById(IdEntity entity) throws DaoException {

		try {
			if ((entity == null) || (entity.getEntityId() == null)) {
				log
						.error("AbstractDao.class findEntity fail,the id of entity should  not been null");
				throw new DaoException("AbstractDao.class can not find entity!");
			}

			entity = (IdEntity) super.getHibernateTemplate().get(
					getDaoModel().getName(), entity.getEntityId());
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("AbstractDao.class findEntity fail,when find "
					+ entity.toString());
			throw new DaoException("AbstractDao.class can not find entity!");

		}
	}

	public boolean deleteEntity(IdEntity entity) throws DaoException {
		try {
			entity = this.findEntityById(entity);
			super.getHibernateTemplate().delete(entity);
			return true;
		} catch (RuntimeException e) {
			e.printStackTrace();
			log.error("AbstractDao.class deleteEntity fail,when operate "
					+ entity.toString());
			throw new DaoException("AbstractDao.class can not delete entity!");

		 }
      
	}
    public boolean deleteEntities(List<IdEntity> entities) throws DaoException {
    	try {
    		super.getHibernateTemplate().deleteAll(entities);
    		return true;
    	} catch (Exception e) {
			e.printStackTrace();
			log.error("AbstractDao.class deleteEntities fail,when operate ");
			throw new DaoException("AbstractDao.class can not delete entities!");

		 }    	
    	
    }
	public IdEntity updateEntity(IdEntity entity) throws DaoException {

		try {
			AbstractEntity ae = (AbstractEntity)entity;
			AbstractEntity old = (AbstractEntity)this.findEntityById(entity);
			ae.setCreateDate(old.getCreateDate());
			ae.setModifyDate(new Date());
			super.getHibernateTemplate().merge(entity);			
			return entity;
		} catch (RuntimeException e) {
			e.printStackTrace();
			log.error("AbstractDao.class updateEntity fail,when update "
					+ entity.toString());
			throw new DaoException("AbstractDao.class can not update entity!");
		}
	}
	
	public IdEntity updateOrSaveEntity(IdEntity entity) throws DaoException {
		// TODO Auto-generated method stub
		return updateEntity(entity);
	}
	
	public List<IdEntity> findAllEntities() throws DaoException {
		try {
			List<IdEntity> entities = super.getHibernateTemplate().find(
					"select e from " + getDaoModel().getName() + " e");
			return entities;
		} catch (RuntimeException e) {
			log.error("AbstractDao.class findAllEntities() fail,when find "
					+ getDaoModel());
			e.printStackTrace();
			throw new DaoException(
					"AbstractDao.class can not return all entities!");
		}

	}

	public List findEntityByNamedQuery(String namequery)
			throws DaoException {
		Query query = getSession().getNamedQuery(namequery);

		List el = null;
		try {
			el = query.list();
		} catch (Exception e) {
			log
					.error("AbstractDao findEntityByNamedQuery(String namequery) fail! "
							+ e.getMessage());
			e.printStackTrace();
			throw new DaoException(
					"AbstractDao findEntityByNamedQuery(String namequery) fail!");
		}
		return el;
	}
	
	

	public List<IdEntity> find(String query) throws DaoException {
		try {
			List<IdEntity> entities = super.getHibernateTemplate().find(query);
			return entities;
		} catch (RuntimeException e) {
			log.error("AbstractDao.class find() fail,when find "
					+ getDaoModel());
			e.printStackTrace();
			throw new DaoException(
					"AbstractDao.class can not return all entities!");
		}
		
	}
	public Pagination findEntitiesByPage(final String hsql,final Pagination page) {		   
	       Pagination pagination =(Pagination)getHibernateTemplate().execute(new HibernateCallback(){
				public Object doInHibernate(Session session) throws HibernateException,SQLException{
				   
				   Query query = session.createQuery(hsql);
				   ScrollableResults sc=query.scroll();
				   sc.last();				   
				   int maxcount=sc.getRowNumber()+1;
				   
				   query = session.createQuery(hsql);			 
				   query.setFirstResult(page.getStartPosition());
				   query.setMaxResults(page.getPageSize());
			       List queryResults =query.list();
			       
			       Pagination pg = new Pagination();
			       pg.setList(queryResults);
			       pg.setCurrentPage(page.getCurrentPage());
			       pg.setTotalCount(maxcount);
				   
				   return pg;
				  }
				});
			  return pagination;
		   
		   
	}
	public Pagination findEntitiesByPage(final Pagination page) {		   
	       Pagination pagination =(Pagination)getHibernateTemplate().execute(new HibernateCallback(){
				public Object doInHibernate(Session session) throws HibernateException,SQLException{
				   StringBuffer sb = new StringBuffer();
				   sb.append("select e from " + getDaoModel().getName() + " e"); 
				   Query query = session.createQuery(sb.toString());
				   ScrollableResults sc=query.scroll();
				   sc.last();				   
				   int maxcount=sc.getRowNumber()+1;
				   
				   query = session.createQuery(sb.toString());			 
				   query.setFirstResult(page.getStartPosition());
				   query.setMaxResults(page.getPageSize());
			       List queryResults =query.list();
			       
			       Pagination pg = new Pagination();
			       pg.setList(queryResults);
			       pg.setCurrentPage(page.getCurrentPage());
			       pg.setTotalCount(maxcount);
				   
				   return pg;
				  }
				});
			  return pagination;
		   
		   
	}
	
	public Pagination findEntitiesByPage(final String hql,final int startPos,final int pageSize,final int curPage) {
		Pagination pagination =(Pagination)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException,SQLException{
			  
			   Query query = session.createQuery(hql);
			   ScrollableResults sc=query.scroll();
			   sc.last();				   
			   int maxcount=sc.getRowNumber()+1;
			   
			   query = session.createQuery(hql);			 
			   query.setFirstResult(startPos);
			   query.setMaxResults(pageSize);
		       List queryResults =query.list();
		       
		       Pagination pg = new Pagination();
		       pg.setList(queryResults);
		       pg.setCurrentPage(curPage);
		       pg.setTotalCount(maxcount);
			   
			   return pg;
			  }
			});
		  return pagination;
	}
}
