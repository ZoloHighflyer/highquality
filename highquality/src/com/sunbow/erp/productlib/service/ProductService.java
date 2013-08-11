package com.sunbow.erp.productlib.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nci.cp.core.model.DefaultEntity;
import com.nci.cp.core.service.ICommonEntityExtService;
import com.nci.cp.core.utils.CommonUtils;
import com.nci.cp.ds.paging.Pagination;
import com.sunbow.erp.productlib.dao.IProductLibDao;
import com.sunbow.erp.productlib.model.ProductClassify;
import com.sunbow.erp.productlib.model.ProductEntity;
import com.sunbow.erp.productlib.vo.ProductVo;

public class ProductService implements IProductLibService {
	private static Log log = LogFactory.getLog(ProductService.class);
	private IProductLibDao productDao;
	private ICommonEntityExtService commEntityExtService;

	public void setProductDao(IProductLibDao productDao) {
		this.productDao = productDao;
	}
    
	public void setCommEntityExtService(ICommonEntityExtService commEntityExtService) {
		this.commEntityExtService = commEntityExtService;
	}

	public Pagination findProductsByPage(Pagination page) throws Exception {
		// 如果page为null则新建
		if (page == null) {
			page = new Pagination();
		}

		Pagination p = productDao.findProductsByPage(page);
		List products = p.getList();
		List productVos = new ArrayList();
		for (int i = 0; i < products.size(); i++) {
			ProductEntity proe = (ProductEntity) products.get(i);
			ProductClassify pc = proe.getProductClassify();
			ProductVo pevo = new ProductVo();
			pevo.setId(proe.getId());
			pevo.setProductNo(proe.getProductNo());
			pevo.setProductName(proe.getProductName());
			pevo.setPrice(proe.getPrice());
			pevo.setInPrice(proe.getInPrice());
			pevo.setOutPrice(proe.getOutPrice());
			String pcpath = null;
			if (pc != null) {
				pcpath = findClassifyPath(pc.getId().longValue());
			} else {
				pcpath = "";
			}
			pevo.setClassify(pcpath);
			productVos.add(pevo);
		}
		p.setList(productVos);
		return p;
	}

	public long saveProductEntity(ProductEntity pe) throws Exception {
		log.debug("=============test save pe");
		/*
		 * CommonEntityExt cee = new CommonEntityExt();
		 * cee.setComponentName("product ext attr");
		 * cee=(CommonEntityExt)productDao.createEntity(cee);
		 * pe.setCommEntityExt(cee);
		 * 
		 * if (pe.getCommEntityExt()==null) { log.debug("=============test cee
		 * is null"); }
		 */
		Map values = new HashMap();
		values.put("brand", "倩碧");
		values.put("specification", "125ml");
		values.put("crowdBound", "成年人");
		pe = (ProductEntity) productDao.createEntity(pe);
		commEntityExtService.saveCommonEntity(pe, "productext", values);
		return pe.getId();
	}

	public ProductVo findProductEntityById(long productId) throws Exception {
		ProductEntity pe = null;
		try {
			pe = (ProductEntity) productDao.findEntityById(new DefaultEntity(
					productId));
		} catch (Exception e) {
			log
					.error(this.getClass().getName()
							+ " findProductEntityById can  not find productentity id is: "
							+ productId);
			return null;
		}
        
		
		if (pe != null) {
			ProductVo pvo = new ProductVo();
			CommonUtils.copyAttrs(pe, pvo);
			String pcpath = null;
			if (pe.getProductClassify() != null) {
				pcpath = findClassifyPath(pe.getProductClassify().getId());
				pvo.setProductClassifyId(pe.getProductClassify().getId());
			} else {
				pcpath = "";
			}
			pvo.setClassify(pcpath);
			return pvo;
		}
		return null;
	}

	public long updateProductEntity(ProductEntity pe) throws Exception {
		pe = (ProductEntity) productDao.updateEntity(pe);
		return pe.getId();
	}

	public boolean deleteProductEntity(long productId) throws Exception {

		return productDao.deleteEntity(new DefaultEntity(productId));
	}

	public List findAllClassifies() throws Exception {
		return productDao.findAllClassifies();
	}

	public List findAvailibleClassifies() throws Exception {
		return productDao.findAvailibleClassifies();
	}

	public ProductClassify findProductClassifyById(long pcId) throws Exception {
		return productDao.findProductClassifyById(pcId);
	}

	public List<ProductEntity> findProductByClassifyId(long cId)
			throws Exception {

		return productDao.findProductsByClassifyId(cId);
	}

	public long saveProductClassify(ProductClassify pc) throws Exception {
		return ((Long) productDao.createEntity(pc).getEntityId()).longValue();
	}

	public long updateProductClassify(ProductClassify pc) throws Exception {
		return productDao.updateProductClassify(pc).getId();
	}

	public boolean deleteProductClassify(Long productId) throws Exception {
		return productDao.deleteProductClassify(productId);
	}

	public String findClassifyPath(long pcid) throws Exception {
		ProductClassify pc = findProductClassifyById(pcid);
		String path = null;

		if (pc != null) {
			path = pc.getName();
			while (pc.getParCategoryNo() != -1) {
				ProductClassify pcTemp = findProductClassifyById(pc
						.getParCategoryNo());
				if (pcTemp != null) {
					path = pcTemp.getName() + "\\" + path;
					pc = pcTemp;
				} else {
					break;
				}
			}
		} else {
			path = "";
		}
		return path;
	}
}
