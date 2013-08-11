package com.nci.cp.core.sysmgt.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Property;

import com.nci.cp.core.dao.AbstractDao;
import com.nci.cp.core.exception.DaoException;
import com.nci.cp.core.sysmgt.model.Func;
import com.nci.cp.core.sysmgt.model.Operation;
/**
 * @target  the object is for operation dao implemention .
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-8-19 
 */
public class OperationDao extends AbstractDao implements IOperationDao {

	@Override
	protected Class getDaoModel() {
		return Operation.class;
	}
	
    public boolean deleteOptOfFuncs(List<Func> funcs) throws DaoException{
    	List ids = new ArrayList();
    	for(int i=0;i<funcs.size();i++){
    		Func f = (Func)funcs.get(i);
    		ids.add(f.getId().longValue());
    	}
    	List opts = super.getSession()
        .createCriteria(getDaoModel())
        .add(Property.forName("funcId").in(ids))
        .list();
           	
    	return this.deleteEntities(opts);
    }

	public List findOptsByFuncId(Long funcid) throws DaoException {
			Query query = super.getSession().getNamedQuery("findOptsByFuncId");
			query.setLong("funcid", funcid);
			List l = query.list();
		    if ((l!=null)&&(l.size()>0)) {
		    	   return l;
		    }
			return null;
			
		}


}
