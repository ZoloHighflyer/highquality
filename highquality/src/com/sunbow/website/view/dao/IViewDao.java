package com.sunbow.website.view.dao;

import java.util.List;

import com.nci.cp.core.dao.IDao;
import com.nci.cp.core.exception.DaoException;
import com.nci.cp.ds.paging.Pagination;
import com.sunbow.website.view.model.SiteView;
import com.sunbow.website.view.model.ViewProduct;

public interface IViewDao extends IDao {
	public Pagination findSiteViewsByPage( Pagination page) throws DaoException ;
	public SiteView findSiteViewById(long viewid)throws DaoException;
	public SiteView findSiteViewByViewId(String viewId)throws DaoException ;
	public List<ViewProduct> findProductsByViewId(long cId) throws DaoException;
	public SiteView updateSiteView(SiteView siteView)throws DaoException ;
	public boolean deleteViewProductsByViewId(Long viewid)throws DaoException;
	public boolean deleteSiteView(Long viewid)throws DaoException;
}
