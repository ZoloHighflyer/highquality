package com.sunbow.website.view.service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nci.cp.core.utils.CommonUtils;
import com.nci.cp.ds.paging.Pagination;
import com.sunbow.erp.productlib.model.ProductClassify;
import com.sunbow.erp.productlib.model.ProductEntity;
import com.sunbow.erp.productlib.service.IProductLibService;
import com.sunbow.erp.productlib.vo.ProductVo;
import com.sunbow.website.view.dao.IViewDao;
import com.sunbow.website.view.model.SiteView;
import com.sunbow.website.view.model.ViewProduct;
import com.sunbow.website.view.vo.SelectItemVo;
import com.sunbow.website.view.vo.ViewVo;

public class ViewFacadeService implements IViewFacadeService {
	private static Log log = LogFactory.getLog(ViewFacadeService.class);
    private IProductLibService productLibService;
    private IViewDao           viewDao;
	public void setProductLibService(IProductLibService productLibService) {
		this.productLibService = productLibService;
	}
   
	public void setViewDao(IViewDao viewDao) {
		this.viewDao = viewDao;
	}

	public List<SelectItemVo> findAllCategories() throws Exception{
		List<ProductClassify>  pcs =productLibService.findAvailibleClassifies();		
		List<SelectItemVo> selectItems = new ArrayList<SelectItemVo>();
		if (pcs.size()>0) {
			for(int i=0;i<pcs.size();i++) {
				ProductClassify pc =(ProductClassify)pcs.get(i);
				SelectItemVo sivo= new SelectItemVo();
				sivo.setId(pc.getId());				
				sivo.setName(productLibService.findClassifyPath(pc.getId()));
				selectItems.add(sivo);
			}			
		}
		return selectItems;
	}
	public List<SelectItemVo> findProductsByClassifyId(long cId) throws Exception{
		List<ProductEntity>  pes =productLibService.findProductByClassifyId(cId);		
		List<SelectItemVo> selectItems = new ArrayList<SelectItemVo>();
		if (pes.size()>0) {
			for(int i=0;i<pes.size();i++) {
				ProductEntity pe =(ProductEntity)pes.get(i);
				SelectItemVo sivo= new SelectItemVo();
				sivo.setId(pe.getId());				
				sivo.setName(pe.getProductName());
				selectItems.add(sivo);
			}			
		}
		return selectItems;
	}
    public List<SelectItemVo> findProductsByViewId(long viewid) throws Exception{
    	SiteView sv = viewDao.findSiteViewById(viewid);
    	List<SelectItemVo> sis = new ArrayList<SelectItemVo>();
    	if(sv!=null) {
    		List<ViewProduct> vps =viewDao.findProductsByViewId(viewid);
    		for (ViewProduct vp:vps) {
    			ProductEntity pe=vp.getProductEntity();
    			if (pe!=null) {
    				SelectItemVo sivo = new SelectItemVo();
    				sivo.setId(pe.getId());
    				sivo.setName(pe.getProductName());
    				sis.add(sivo);
    			}    
    		}
    		/*Set viewproducts=sv.getViewproducts();
    		log.debug("================= find products from viewid size: "+viewproducts.size());
    		Iterator it=viewproducts.iterator();
    		while(it.hasNext()) {
    			ViewProduct vp = (ViewProduct)it.next();
    			ProductEntity pe=vp.getProductEntity();
    			if (pe!=null) {
    				SelectItemVo sivo = new SelectItemVo();
    				sivo.setId(pe.getId());
    				sivo.setName(pe.getProductName());
    				sis.add(sivo);
    			}    			
    		}*/
    	}
    	
      return sis;
    }
	
    public Pagination findSiteViewsByPage(Pagination page) throws Exception {
    	if (page==null) {
			page  = new Pagination();		
		}		
		Pagination p=viewDao.findSiteViewsByPage(page);
		List siteviews=p.getList();
		List views = new ArrayList();
		for (int i=0;i<siteviews.size();i++) {
			SiteView sv = (SiteView)siteviews.get(i);
			ViewVo vv = new ViewVo();
			vv.setId(sv.getId());
			vv.setName(sv.getName());
			vv.setViewId(sv.getViewId());			
			vv.setModifyDate(CommonUtils.dateStr(sv.getModifyDate(), "yyyy-MM-dd"));
			views.add(vv);
		}
		p.setList(views);
		return p;
	}
    
    public ViewVo findViewById(long viewid)throws Exception {
    //	return (SiteView)this.getHibernateTemplate().get(SiteView.class, new Long(viewid));
    	SiteView sv=viewDao.findSiteViewById(viewid);
    	ViewVo vo = new ViewVo();
    	vo.setId(sv.getId());
    	vo.setName(sv.getName());
    	vo.setViewId(sv.getViewId());
    	vo.setMark(sv.getMark());
    	List<ViewProduct> vps =viewDao.findProductsByViewId(viewid);
		for (ViewProduct vp:vps) {
			ProductEntity pe=vp.getProductEntity();
			if (pe!=null) {
				ProductVo pvo = productLibService.findProductEntityById(pe.getId());
				if (pvo!=null)
				  vo.getProducts().add(pvo);				
			}    
		}
    	return vo;
    }
    public void updateView(ViewVo viewVo) throws Exception{
    	SiteView sv = new SiteView();
    	sv.setId(viewVo.getId());
    	sv.setName(viewVo.getName());
    	sv.setViewId(viewVo.getViewId());    		
    	sv=(SiteView)viewDao.updateSiteView(sv);
    	viewDao.deleteViewProductsByViewId(sv.getId());
    	String productStr=viewVo.getProductids();
    	StringTokenizer st=new StringTokenizer(productStr,",");    
    	int i=1;
    	while(st.hasMoreElements()){
    		try {
    			long pid=Long.parseLong(((String)st.nextElement()).trim());
    			ProductVo pvo=productLibService.findProductEntityById(pid);
    			if (pvo!=null){
    				ViewProduct vp = new ViewProduct();
    				ProductEntity pe = new ProductEntity();
    				pe.setId(pid);
    				vp.setProductEntity(pe);
    				vp.setSiteView(sv);
    				vp.setOrder(i);    				
    				viewDao.createEntity(vp);
    				i++;
    			}
				
			} catch (Exception e) {
				log.info(this.getClass().getName()+" updateView() can not change the string to long ");
			}
    	}
    }
    public boolean deleteView(ViewVo viewVo)throws Exception{
    	if (viewVo.getId()!=0){
    		//SiteView sv=viewDao.findSiteViewById(viewVo.getId());
    		viewDao.deleteViewProductsByViewId(viewVo.getId());
    		viewDao.deleteSiteView(viewVo.getId());		
    		return true;
    	}
    	log.info(this.getClass().getName()+" deleteView() fail the view id is: "+viewVo.getId());
    	return false;
    }
	public void saveView(ViewVo viewVo) throws Exception{
    	SiteView sv = new SiteView();
    	sv.setName(viewVo.getName());
    	sv.setViewId(viewVo.getViewId());
    	String productStr=viewVo.getProductids();
    	StringTokenizer st=new StringTokenizer(productStr,",");    	
    	sv=(SiteView)viewDao.createEntity(sv);
    	int i=0;
    	while(st.hasMoreElements()){
    		try {
    			long pid=Long.parseLong(((String)st.nextElement()).trim());
    			ProductVo pvo=productLibService.findProductEntityById(pid);
    			if (pvo!=null){
    				ViewProduct vp = new ViewProduct();
    				ProductEntity pe = new ProductEntity();
    				pe.setId(pid);
    				vp.setProductEntity(pe);
    				vp.setSiteView(sv);
    				vp.setOrder(i);    				
    				viewDao.createEntity(vp);
    				i++;
    			}
				
			} catch (Exception e) {
				log.info(this.getClass().getName()+" saveView() can not change the string to long ");
			}
    	}    
    }
    
}
