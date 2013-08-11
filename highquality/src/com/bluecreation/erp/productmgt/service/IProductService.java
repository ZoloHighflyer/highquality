package com.bluecreation.erp.productmgt.service;


import java.util.List;

import com.bluecreation.erp.productmgt.model.Product;
import com.bluecreation.erp.productmgt.vo.ProductVo;
import com.nci.cp.core.service.IService;
import com.nci.cp.ds.paging.Pagination;

/**
 * @target  the interface is for product service operations implement.
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-10-29 
 */
public interface IProductService extends IService{
	 public long createProduct(ProductVo productvo) throws Exception;
		
	 public ProductVo findProductVoById(long productid) throws Exception;
	 
	 public Product findProductById(long productid) throws Exception;
		
	 public Pagination findAllProductVosByPage(Pagination page) throws Exception;
	 
	 public List       findAllProductVos()  throws Exception;
	 
	 public long updateProductVo(ProductVo productvo) throws Exception;
		
	 public boolean   deleteProduct(long productid) throws Exception;
}
