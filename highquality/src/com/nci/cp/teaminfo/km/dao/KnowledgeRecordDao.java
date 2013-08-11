package com.nci.cp.teaminfo.km.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.nci.cp.core.dao.AbstractDao;
import com.nci.cp.core.exception.DaoException;
import com.nci.cp.core.model.IdEntity;
import com.nci.cp.ds.paging.Pagination;
import com.nci.cp.teaminfo.km.model.KnowledgeRecord;

public class KnowledgeRecordDao extends AbstractDao implements IKnowledgeRecordDao {

	@Override
	protected Class getDaoModel() {
		
		return KnowledgeRecord.class;
	}
    
	@Override
	public IdEntity createEntity(IdEntity entity) throws DaoException {			
		super.createEntity(entity);
		/*KnowledgeRecord kr =(KnowledgeRecord)entity;	
		Content content = new Content();
		content.setId(kr.getId());
		content.setContent(kr.getContent());
		
		try {
			SearchUtils.indexRecord("d:\\weblucene", KnowledgeRecord.class.getName(), content);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("can not index data");
		}*/
		return entity;
	}

	@Override
	public IdEntity updateEntity(IdEntity entity) throws DaoException {	
		
		return super.updateEntity(entity);
	}

	
	public Pagination findRecordsByPage(final Pagination page) {
		Pagination pagination =(Pagination)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException,SQLException{
			   StringBuffer sb = new StringBuffer();
			   sb.append("select e from " + getDaoModel().getName() + " e order by e.modifyDate desc"); 
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
	public Pagination findRecordsByCategoryWithPage(Pagination page,long kcid,String owner) {
		String hsql ="select kr from KnowledgeRecord kr where kr.infoCategory.id="+kcid+" and kr.owner='"+owner+"' order by kr.modifyDate desc";
		
		return findEntitiesByPage(hsql,page.getStartPosition(),page.getPageSize(),page.getCurrentPage());
	}
	public Pagination findRecordsNoCategoryWithPage(Pagination page,String owner) {
		String hsql ="select kr from KnowledgeRecord kr where kr.infoCategory.id=null and kr.owner='"+owner+"' order by kr.modifyDate desc";		
		return findEntitiesByPage(hsql,page.getStartPosition(),page.getPageSize(),page.getCurrentPage());
	}
	
	
	
	
	
    
}
