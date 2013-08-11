package com.sunbow.erp.productlib.dao;

import java.util.List;

import com.nci.cp.core.dao.IDao;
import com.nci.cp.core.exception.DaoException;
import com.nci.cp.ds.paging.Pagination;
import com.sunbow.erp.productlib.model.ProductClassify;
import com.sunbow.erp.productlib.model.ProductEntity;

public interface IProductLibDao extends IDao {
	public Pagination findProductsByPage(Pagination page) throws DaoException ;
	public List findAllClassifies() throws DaoException;
	public List findAvailibleClassifies() throws DaoException;
	public ProductClassify findProductClassifyById(long pcId) throws DaoException;
	public List<ProductEntity> findProductsByClassifyId(long cId) throws DaoException ;
	public ProductClassify updateProductClassify(ProductClassify pc) throws DaoException;
	public boolean deleteProductClassify(Long pcid)throws DaoException;
}
