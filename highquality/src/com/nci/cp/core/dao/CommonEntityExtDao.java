package com.nci.cp.core.dao;


import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nci.cp.core.exception.DaoException;
import com.nci.cp.core.model.CommonEntityExt;
import com.nci.cp.core.model.IdEntity;
import com.nci.cp.core.model.StringAttr;


public class CommonEntityExtDao extends AbstractDao implements
		ICommonEntityExtDao {
	private static Log log = LogFactory.getLog(CommonEntityExtDao.class);
	@Override
	protected Class getDaoModel() {
		return CommonEntityExt.class;
	}

	public CommonEntityExt findCommEntityExiByEntityId(IdEntity entityId,
			String extentityName) throws DaoException {
		List l = super.find("select e from CommonEntityExt e "
				+ "where e.entityId=" + (Long) entityId.getEntityId() + " and "
				+ "e.componentName='" + extentityName + "'");
		if ((l != null) && (l.size() > 0)) {
			return (CommonEntityExt) l.get(0);
		}
		return null;
	}

	public CommonEntityExt saveCommonEntity(IdEntity entityId,
			String extentityName) throws DaoException {
		CommonEntityExt cee = new CommonEntityExt();
		cee.setEntityId(((Long)entityId.getEntityId()));
		cee.setComponentName(extentityName);
		cee=(CommonEntityExt)super.createEntity(cee);
		
		return cee;
	}

	public StringAttr saveStringAttr(StringAttr strAttr) throws DaoException {
		StringAttr stra=(StringAttr)super.createEntity(strAttr);
		return stra;
	}
	
}
