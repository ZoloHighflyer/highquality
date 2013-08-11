package com.sunbow.website.view.service;

import java.util.List;

import com.nci.cp.core.service.IService;
import com.nci.cp.ds.paging.Pagination;
import com.sunbow.website.view.vo.SelectItemVo;
import com.sunbow.website.view.vo.ViewVo;

public interface IViewFacadeService extends IService {
     /**
     * @return
     * @throws Exception
     */
    public List<SelectItemVo> findAllCategories() throws Exception;
     /**
     * @param cId
     * @return products of the classify id
     * @throws Exception
     */
    public List<SelectItemVo> findProductsByClassifyId(long cId) throws Exception;
    /**
     * @param viewVo
     * @throws Exception
     */
    public ViewVo findViewById(long viewId) throws Exception;
    public void saveView(ViewVo viewVo) throws Exception;
    public void updateView(ViewVo viewVo) throws Exception;
    /**
     * @param page
     * @return
     * @throws Exception
     */
    public Pagination findSiteViewsByPage(Pagination page) throws Exception;
    
    public boolean deleteView(ViewVo viewVo)throws Exception;
    
    public List<SelectItemVo> findProductsByViewId(long viewid) throws Exception;
}
