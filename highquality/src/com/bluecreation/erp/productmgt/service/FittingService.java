package com.bluecreation.erp.productmgt.service;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bluecreation.erp.productmgt.dao.IFittingsDao;
import com.bluecreation.erp.productmgt.model.CommonComponent;
import com.bluecreation.erp.productmgt.model.Fittings;
import com.bluecreation.erp.productmgt.vo.FittingsVo;
import com.nci.cp.core.model.DefaultEntity;
import com.nci.cp.ds.paging.Pagination;
/**
 * The interface is fittings module service interface.
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-10-20 
 */
public class FittingService implements IFittingsService {
	private static Log log = LogFactory.getLog(FittingService.class);
    private IFittingsDao  fittingsDao;
    
	public void setFittingsDao(IFittingsDao fittingsDao) {
		this.fittingsDao = fittingsDao;
	}

	public long createFittings(FittingsVo ftvo)  throws Exception{
		CommonComponent ft =FittingServiceUtils.voToBo(ftvo);
		fittingsDao.createEntity(ft);
		return ft.getId().longValue();
	}

	public boolean deleteFittings(long ftid) throws Exception {		
		return fittingsDao.deleteEntity(new DefaultEntity(ftid));
	}

	public FittingsVo findFittingsVoById(long ftid) throws Exception {
		CommonComponent ft=(CommonComponent)fittingsDao.findEntityById(new DefaultEntity(ftid));
		return FittingServiceUtils.boTovo(ft);
	}
	public Fittings findFittingById(long ftid) throws Exception {
		Fittings ft=(Fittings)fittingsDao.findEntityById(new DefaultEntity(ftid));
		return ft;
	}
	public long updateFittingsVo(FittingsVo ftvo) throws Exception {
		CommonComponent ft = FittingServiceUtils.voToBo(ftvo);
		ft=(CommonComponent)fittingsDao.updateEntity(ft);
		return ft.getId().longValue();
	}

	public Pagination findAllFittingsVosByPage(Pagination page) throws Exception {
		if (page==null) {
			page  = new Pagination();
		
		}
	  
		Pagination pg= fittingsDao.findEntitiesByPage(page);		
		return pg;
	}

	public List findAllFittingsVos() throws Exception {
		
		return fittingsDao.findAllEntities();
	}
    
	
	
	
}
