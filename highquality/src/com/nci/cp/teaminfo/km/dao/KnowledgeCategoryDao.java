package com.nci.cp.teaminfo.km.dao;

import java.util.List;

import org.hibernate.Query;

import com.nci.cp.core.dao.AbstractDao;
import com.nci.cp.core.exception.DaoException;
import com.nci.cp.teaminfo.km.model.KnowledgeCategory;

public class KnowledgeCategoryDao extends AbstractDao implements IKnowledgeCategoryDao {

	@Override
	protected Class getDaoModel() {
		// TODO Auto-generated method stub
		return KnowledgeCategory.class;
	}
	public List findAllCategories(String owner) throws DaoException {
		Query q=super.getSession().createQuery("select kc from KnowledgeCategory kc   where kc.owner=:owner order by kc.id asc");
		q.setParameter("owner",owner);
		return q.list();
	}
	public long updateKnowledgeCategoryInfo(KnowledgeCategory kc)
			throws DaoException {
	//	String hql = "update "+getDaoModel().getName() +" kc set u.userName = ? , u.userUpdatetime = ? , u.school = ? where u.userId = ?";
		Query q=super.getSession().createQuery("update KnowledgeCategory kc  set kc.name=:name,kc.parcategoryno=:parcategoryno where kc.id=:kcid");
		q.setParameter("name",kc.getName());
		q.setParameter("parcategoryno",kc.getParcategoryno());
		q.setParameter("kcid",kc.getId());
		q.executeUpdate();	
		return kc.getId();
	}
	
	public boolean isExistSubCategory(long kcid) throws DaoException {
		Query q=super.getSession().createQuery("select kc from KnowledgeCategory kc where kc.parcategoryno=:kcid");
		q.setParameter("kcid",kcid);
		return q.list().size()>0;
	}
}
