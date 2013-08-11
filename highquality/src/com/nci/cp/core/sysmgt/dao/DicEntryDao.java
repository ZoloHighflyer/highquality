package com.nci.cp.core.sysmgt.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.nci.cp.core.dao.AbstractDao;
import com.nci.cp.core.sysmgt.model.DicEntry;
import com.nci.cp.ds.paging.Pagination;

public class DicEntryDao extends AbstractDao implements IDicEntryDao {

	@Override
	protected Class getDaoModel() {
	
		return DicEntry.class;
	}

	public Pagination findDicEntrysByTypeWithPage(final Pagination page,final long dictypeid )
			throws Exception {
		 Pagination pagination =(Pagination)getHibernateTemplate().execute(new HibernateCallback(){
				public Object doInHibernate(Session session) throws HibernateException,SQLException{
				   StringBuffer sb = new StringBuffer();
				   sb.append("select e from " + getDaoModel().getName() + " e where e.dicType.id="+new Long(dictypeid)); 
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

}
