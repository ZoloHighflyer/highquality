package com.nci.cp.core.dao;

import java.util.Map;

import com.nci.cp.core.exception.DaoException;
import com.nci.cp.core.model.CommonEntityExt;
import com.nci.cp.core.model.IdEntity;
import com.nci.cp.core.model.StringAttr;

public interface ICommonEntityExtDao {
	public CommonEntityExt findCommEntityExiByEntityId(IdEntity entityId,
			String extentityName) throws DaoException;

	public CommonEntityExt saveCommonEntity(IdEntity entityId,
			String extentityName) throws DaoException;
	
	public StringAttr saveStringAttr(StringAttr strAttr) throws DaoException;
}
