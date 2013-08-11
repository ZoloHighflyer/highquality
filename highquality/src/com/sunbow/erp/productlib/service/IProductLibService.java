package com.sunbow.erp.productlib.service;

import java.util.List;

import com.nci.cp.core.service.IService;
import com.nci.cp.ds.paging.Pagination;
import com.sunbow.erp.productlib.model.ProductClassify;
import com.sunbow.erp.productlib.model.ProductEntity;
import com.sunbow.erp.productlib.vo.ProductVo;

/**
 * @author Administrator
 *
 */
public interface IProductLibService extends IService {
	public Pagination findProductsByPage(Pagination page)throws Exception;

	public long saveProductEntity(ProductEntity pe) throws Exception;

	public ProductVo findProductEntityById(long productId) throws Exception;

	public long updateProductEntity(ProductEntity pe) throws Exception;

	public boolean deleteProductEntity(long productId) throws Exception;
	
	public List<ProductEntity> findProductByClassifyId(long cId) throws Exception;

	public List findAllClassifies() throws Exception;
	
	public List findAvailibleClassifies() throws Exception;
	
	public ProductClassify findProductClassifyById(long pcId) throws Exception;
	
	public long saveProductClassify(ProductClassify pc) throws Exception;

	public long updateProductClassify(ProductClassify pc) throws Exception;
	
	public boolean deleteProductClassify(Long productId) throws Exception;
	
	/**
	 * @param pcid
	 * @return the path of classify
	 * @throws Exception
	 */
	public String findClassifyPath(long pcid) throws Exception;
}
