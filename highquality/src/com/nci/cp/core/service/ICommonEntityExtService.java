package com.nci.cp.core.service;

import java.util.Map;

import com.nci.cp.core.model.IdEntity;

public interface ICommonEntityExtService extends IService {
    public void saveCommonEntity(IdEntity entityId,String extentityName,Map values)throws Exception;
}
