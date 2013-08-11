package com.sunbow.cms.service;

import java.util.List;

import com.nci.cp.core.service.IService;
import com.sunbow.erp.productlib.vo.ProductVo;

public interface IWebSiteFacadeService extends IService {
	public List<ProductVo> findProductsByViewId(String viewid) throws Exception;
}
