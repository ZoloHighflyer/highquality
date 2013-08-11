package com.bluecreation.erp.productmgt.service;


import java.util.List;

import com.bluecreation.erp.productmgt.model.Fittings;
import com.bluecreation.erp.productmgt.vo.FittingsVo;
import com.nci.cp.core.service.IService;
import com.nci.cp.ds.paging.Pagination;
/**
 * @target  the interface is for fittings service operations implement.
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-10-19 
 */
public interface IFittingsService extends IService {
    public long createFittings(FittingsVo ftvo) throws Exception;
	
	public FittingsVo findFittingsVoById(long ftid) throws Exception;
	
	public Fittings findFittingById(long ftid) throws Exception;
	
	public Pagination findAllFittingsVosByPage(Pagination page) throws Exception;
	
	public List findAllFittingsVos() throws Exception;
	
	public long updateFittingsVo(FittingsVo ftvo) throws Exception;
	
	public boolean   deleteFittings(long ftid) throws Exception;
}
