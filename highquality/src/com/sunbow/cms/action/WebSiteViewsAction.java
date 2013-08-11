package com.sunbow.cms.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nci.cp.core.web.AbstractAction;
import com.sunbow.cms.service.IWebSiteFacadeService;
import com.sunbow.cms.vo.ViewPanelVo;
import com.sunbow.erp.productlib.vo.ProductVo;

public class WebSiteViewsAction extends AbstractAction {
	private static Log log = LogFactory.getLog(WebSiteViewsAction.class);
	private IWebSiteFacadeService websiteFacadeService;
	private Map views = null;
	private List<String>   backbeans=new ArrayList<String>();
	private List  ids       =new ArrayList();
	private String nav      ="";
    
	public void setWebsiteFacadeService(IWebSiteFacadeService websiteFacadeService) {
		this.websiteFacadeService = websiteFacadeService;
	}

	public Map<String,String> getViews() {
		return views;
	}

	public void setViews(Map<String,ViewPanelVo> views) {
		this.views = views;
	}
    
    

	/*public String MainView() throws Exception {
		views=new HashMap();
        List<ProductVo> lastProducts = websiteFacadeService.findProductsByViewId("LastPros");
        views.put("LastPros", lastProducts); 
        log.debug("=======================view name: "+viewName);
		return SUCCESS;
	}*/
	


	public String getNav() {
		return nav;
	}

	public void setNav(String nav) {
		this.nav = nav;
	}

	public List getIds() {
		return ids;
	}

	public void setIds(List ids) {
		this.ids = ids;
	}

	@Override
	public String execute() throws Exception {
		views=new HashMap();        
        if ((backbeans!=null)&&(backbeans.size()>0)){
        	for(int i=0;i<backbeans.size();i++) {
        		String panel = (String)backbeans.get(i);
        		int dotpos = panel.indexOf(',');
        		String identify = panel.substring(0, dotpos).trim();
        		String panelName = panel.substring(dotpos+1).trim();
        		ViewPanelVo vpvo = new ViewPanelVo();
        		vpvo.setIdentify(identify);
        		vpvo.setPanelName(panelName);
        		List<ProductVo> products = websiteFacadeService.findProductsByViewId(vpvo.getIdentify());
                vpvo.setVos(products);
                ids.add(vpvo);
        		views.put(vpvo.getIdentify(),vpvo); 
               // views.put(vpvo.getIdentify(),vpvo.getIdentify()); 
        	}
        }
        log.debug("=========================id: "+ids.size());
		return SUCCESS;
		
	}
	/*
	public String CosmeticView() throws Exception {
		views=new HashMap();
        List<ProductVo> lastProducts = websiteFacadeService.findProductsByViewId("LastPros");
    //    views.put("LastPros", lastProducts); 
      //  log.debug("=======================view name: "+viewName);
		return SUCCESS;
	}
*/

	public List getBackbeans() {
		return backbeans;
	}

	public void setBackbeans(List backbeans) {
		this.backbeans = backbeans;
	}

	
	
}
