package com.nci.cp.core.sysmgt.dao;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Expression;

import com.nci.cp.core.dao.AbstractDao;
import com.nci.cp.core.exception.DaoException;
import com.nci.cp.core.sysmgt.model.Func;
/**
 * @target  the interface is for func dao operations implement.
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-8-16 
 */
public class FuncDao extends AbstractDao implements IFuncDao {

	private static Log log = LogFactory.getLog(FuncDao.class);
	@Override
	protected Class getDaoModel() {
		// TODO Auto-generated method stub
		return Func.class;
	}
	public List<Func> findChildrenOfFunc(Func func) throws DaoException {
	    //	super.getSession().createCriteria(getDaoModel()).add(Expression.eq("parFuncNo", func.getNno()));
	    	if (func==null)
	    		throw new  DaoException(this.getClass().getName()+" findChildrenOfFunc method unexpected Func is null or func.parFuncNo is null! ");
	    	
	    	List list = super.getSession()
	    	            .createCriteria(getDaoModel())
	    	            .add(Expression.eq("parfuncno", func.getId().longValue()))
	    	            .list();
			return list;
	    }
	    
	    public void findAllChildrenOfFunc(Func func,List funcs) throws DaoException {
	    	List  cFunc =null;
	    	try {
	    		cFunc = findChildrenOfFunc(func);
	    	} catch (Exception e) {
				e.printStackTrace();
				log.error(this.getClass().getName()+" findAllChildrenOfFunc(Func func,List funcs) ");
				throw new DaoException(this.getClass().getName()+" findAllChildrenOfFunc(Func func,List funcs) ");
			}
	    
	    	if ((cFunc!=null)&&(cFunc.size()>0)) {
	    		funcs.addAll(cFunc);
	    		for(int i=0,length=cFunc.size();i<length;i++) {	    			
	    			findAllChildrenOfFunc((Func)cFunc.get(i),funcs);
	    		}    		
	    	}    	
	    	
	    }
	   
	    
		public List findAllFuncs() throws DaoException {
		    
			return super.findEntityByNamedQuery("findAllFuncs");
		}

		public List findThreeLayersFuncs() throws DaoException {
			
			return super.findEntityByNamedQuery("findThreeLayersFuncs");
		}
		
		public Func findRoot() throws DaoException {
			List l = super.find("select f from Func f where f.parfuncno='-1'");
			if ((l!=null)&&(l.size()>0)) {
				return (Func)l.get(0);
			}
			return null;
		}
	    

}
