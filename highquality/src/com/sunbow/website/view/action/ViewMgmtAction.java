package com.sunbow.website.view.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.nci.cp.core.web.AbstractAction;
import com.nci.cp.core.web.IAction;
import com.opensymphony.xwork2.ActionContext;
import com.sunbow.erp.productlib.vo.ProductVo;
import com.sunbow.website.view.service.IViewFacadeService;
import com.sunbow.website.view.vo.SelectItemVo;
import com.sunbow.website.view.vo.ViewVo;


public class ViewMgmtAction extends AbstractAction {
	private static Log log = LogFactory.getLog(ViewMgmtAction.class);
	private IViewFacadeService viewFacadeService;
	private List categories;
	private long cid;
	private ViewVo viewVo;
	public void setViewFacadeService(IViewFacadeService viewFacadeService) {
		this.viewFacadeService = viewFacadeService;
	}
	
	public void setCategories(List categories) {
		this.categories = categories;
	}
    
	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}
    
	public ViewVo getViewVo() {
		return viewVo;
	}

	public void setViewVo(ViewVo viewVo) {
		this.viewVo = viewVo;
	}

	public String viewMgmt() throws Exception{
		if (viewVo==null)  {
			viewVo = new ViewVo();			
		}		
		viewVo.setPage(viewFacadeService.findSiteViewsByPage(viewVo.getPage()));
		return SUCCESS;
	}
	public String addView() throws Exception{
		
		return SUCCESS;
	} 
	public String initEditView() throws Exception {
		viewVo = viewFacadeService.findViewById(viewVo.getId());
		return SUCCESS;
	}
    public String saveView() throws Exception{
    	if ((viewVo.getAction() != null)
				&& (viewVo.getAction().equals(IAction.ACTION_EDIT))) {
    		viewFacadeService.updateView(viewVo);
    	}else {
    	    viewFacadeService.saveView(viewVo);
    	}
		return SUCCESS;
	}
    
    public String deleteView() throws Exception{
    	viewFacadeService.deleteView(viewVo);
    	return SUCCESS;
    }
	public List getCategories()throws Exception {
		categories=viewFacadeService.findAllCategories();		
		return categories;
	}
	 public String getAvailibleCategoritesXml()throws Exception { 
		 getResponse().setContentType("text/xml;charset=UTF-8");
		 List items =viewFacadeService.findAllCategories();		 
		 getResponse().getWriter().write(createSelectItemsXml(items));
		 return null;
	 }
	 public String getProductsByCid()throws Exception { 
		 getResponse().setContentType("text/xml;charset=UTF-8");
		 List items =viewFacadeService.findProductsByClassifyId(cid);		 
		 getResponse().getWriter().write(createSelectItemsXml(items));
		 return null;
	 }
	 public String getProductsByViewId()throws Exception { 
		 getResponse().setContentType("text/xml;charset=UTF-8");
		 List items =viewFacadeService.findProductsByViewId(cid);		 
		 getResponse().getWriter().write(createSelectItemsXml(items));
		 return null;
	 }
	 public HttpServletResponse getResponse(){			
			return (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	}
	 private String createSelectItemsXml(List<SelectItemVo> sivos) {
		 List<SelectItemVo> items =sivos;
		 String itemsxml="<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		 itemsxml=itemsxml+"<items>";
		 for(int i=0;i<items.size();i++){
			 SelectItemVo si =(SelectItemVo)items.get(i);
			 itemsxml=itemsxml+"<option><value>"+si.getId()+"</value><name>"+si.getName()+"</name></option>";
		 }
		 itemsxml=itemsxml+"</items>";
		 return itemsxml;
	 }
	 
}
