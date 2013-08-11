package com.bluecreation.erp.productmgt.service;

import java.util.List;

import com.bluecreation.erp.productmgt.vo.ProductVo;
import com.nci.cp.core.service.IService;
import com.nci.cp.ds.paging.Pagination;

public interface IProductMgmtService extends IService {
	 public long createProduct(ProductVo productvo) throws Exception;
	
	 public ProductVo findProductVoById(long productid) throws Exception;
		
	 public Pagination findAllProductVosByPage(Pagination page) throws Exception;
	 
	 public long updateProductVo(ProductVo productvo) throws Exception;
		
	 public boolean   deleteProduct(long productid) throws Exception;
	 
	 public List    findAllComponents() throws Exception;
}
