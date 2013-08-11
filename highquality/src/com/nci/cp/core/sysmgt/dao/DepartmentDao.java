package com.nci.cp.core.sysmgt.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Expression;

import com.nci.cp.core.dao.AbstractDao;
import com.nci.cp.core.exception.DaoException;
import com.nci.cp.core.sysmgt.model.Department;
import com.nci.cp.core.sysmgt.model.Func;

/**
 * @target  the interface is for department dao operations implement.
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-8-16 
 */
public class DepartmentDao extends AbstractDao implements IDepartmentDao {
	private static Log log = LogFactory.getLog(DepartmentDao.class);
	@Override
	protected Class getDaoModel() {
		// TODO Auto-generated method stub
		return Department.class;
	}

	public void findAllChildrenOfDepartment(Department dep, List deps)
			throws DaoException {
		List  cDep =null;
    	try {
    		cDep = findChildrenOfDepartment(dep);
    	} catch (Exception e) {
			e.printStackTrace();
			log.error(this.getClass().getName()+" findAllChildrenOfDepartment(Department dep, List deps) ");
			throw new DaoException(this.getClass().getName()+" findAllChildrenOfDepartment(Department dep, List deps) ");
		}
    
    	if ((cDep!=null)&&(cDep.size()>0)) {
    		deps.addAll(cDep);
    		for(int i=0,length=cDep.size();i<length;i++) {	    			
    			findAllChildrenOfDepartment((Department)cDep.get(i),deps);
    		}    		
    	}    	

	}

	public List findAllDepartments() throws DaoException {
		return super.findEntityByNamedQuery("findAllDeps");
	}

	public List<Department> findChildrenOfDepartment(Department dep)
			throws DaoException {
		if ((dep==null)||(dep.getPardepno()==null))
    		throw new  DaoException(this.getClass().getName()+" findChildrenOfFunc method unexpected Department is null or dep.pardepno is null! ");
    	
    	List list = super.getSession()
    	            .createCriteria(getDaoModel())
    	            .add(Expression.eq("pardepno", dep.getId()))
    	            .list();
		return list;

	}

	public Department findRoot() throws DaoException {
		List l = super.find("selec d from Department d where d.pardepno='-1'");
		if ((l!=null)&&(l.size()>0)) {
			return (Department)l.get(0);
		}
		return null;
	}

	public List findThreeLayersDepartments() throws DaoException {
		return super.findEntityByNamedQuery("findThreeLayersDeps");
	}

}
