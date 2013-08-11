package com.bluecreation.erp.productmgt.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bluecreation.erp.productmgt.dao.IProductDao;
import com.bluecreation.erp.productmgt.dao.IProductPartDao;
import com.bluecreation.erp.productmgt.model.Product;
import com.bluecreation.erp.productmgt.vo.ProductVo;
import com.nci.cp.core.model.DefaultEntity;
import com.nci.cp.ds.paging.Pagination;

/**
 * @company BlueCreation Workspace
 * @author OliverChan
 * @version 0.1
 * @date 2011-10-30
 */
public class ProductService implements IProductService {
	private static Log log = LogFactory.getLog(ProductService.class);
	protected IProductDao productDao;
	protected IProductPartDao productPartDao;

	public void setProductDao(IProductDao productDao) {
		this.productDao = productDao;
	}

	public void setProductPartDao(IProductPartDao productPartDao) {
		this.productPartDao = productPartDao;
	}

	public long createProduct(ProductVo productvo) throws Exception {
		Product product = ProductServiceUtils.voToBo(productvo);
		productDao.createEntity(product);
		return product.getId();
	}

	public boolean deleteProduct(long productid) throws Exception {		
		return productDao.deleteEntity(new DefaultEntity(productid));
	}

	public Pagination findAllProductVosByPage(Pagination page) throws Exception {
		if (page==null) {
			page  = new Pagination();		
		}	  
		Pagination pg= productDao.findEntitiesByPage(page);		
		return pg;
	}

	public ProductVo findProductVoById(long productid) throws Exception {
		ProductVo productVo = ProductServiceUtils.boToVo(findProductById(productid));		
		return productVo;
	}
    
	
	public Product findProductById(long productid) throws Exception {
		return (Product)productDao.findEntityById(new DefaultEntity(productid));
	}

	public long updateProductVo(ProductVo productvo) throws Exception {
		Product product = ProductServiceUtils.voToBo(productvo);
		productDao.updateEntity(product);
		return product.getId();
	}

	public List findAllProductVos() throws Exception {		
		return productDao.findAllEntities();
	}
    
	
}
