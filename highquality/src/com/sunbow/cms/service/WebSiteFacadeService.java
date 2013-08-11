package com.sunbow.cms.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sunbow.erp.productlib.model.ProductEntity;
import com.sunbow.erp.productlib.service.IProductLibService;
import com.sunbow.erp.productlib.vo.ProductVo;
import com.sunbow.website.view.dao.IViewDao;
import com.sunbow.website.view.model.SiteView;
import com.sunbow.website.view.model.ViewProduct;

public class WebSiteFacadeService implements IWebSiteFacadeService {
	private static Log log = LogFactory.getLog(WebSiteFacadeService.class);
	private IProductLibService productLibService;
	private IViewDao viewDao;
    
	public void setProductLibService(IProductLibService productLibService) {
		this.productLibService = productLibService;
	}

	public void setViewDao(IViewDao viewDao) {
		this.viewDao = viewDao;
	}

	public List<ProductVo> findProductsByViewId(String viewid) throws Exception {
		SiteView sv = viewDao.findSiteViewByViewId(viewid);
		List<ProductVo> pvos = new ArrayList();
		if (sv!=null) {
			List<ViewProduct> vps = viewDao.findProductsByViewId(sv.getId());
			for(ViewProduct vp:vps) {
				ProductEntity pe=vp.getProductEntity();
				if (pe!=null) {
					ProductVo pvo = new ProductVo();
					pvo.setId(pe.getId());
					pvo.setProductNo(pe.getProductNo());
					pvo.setProductName(pe.getProductName());
					pvo.setPicture(pe.getPicture());
					pvo.setOutPrice(pe.getOutPrice());
					pvo.setPrice(pe.getPrice());
					pvos.add(pvo);
				}
			}
		}
		return pvos;
	}
}
