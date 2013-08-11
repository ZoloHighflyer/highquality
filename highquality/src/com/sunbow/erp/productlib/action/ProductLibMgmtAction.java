package com.sunbow.erp.productlib.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.nci.cp.core.utils.CommonUtils;
import com.nci.cp.core.utils.WebUtils;
import com.nci.cp.core.web.AbstractAction;
import com.nci.cp.core.web.IAction;
import com.nci.cp.ds.paging.Pagination;
import com.sunbow.erp.productlib.model.ProductClassify;
import com.sunbow.erp.productlib.model.ProductEntity;
import com.sunbow.erp.productlib.service.IProductLibService;
import com.sunbow.erp.productlib.vo.ProductClassifyVo;
import com.sunbow.erp.productlib.vo.ProductVo;

public class ProductLibMgmtAction extends AbstractAction {
	private static Log log = LogFactory.getLog(ProductLibMgmtAction.class);
	private IProductLibService productLibService=null;
	private ProductVo          productVo=null;
	private ProductClassifyVo  productClassifyVo=null;	
	
	public ProductVo getProductVo() {
		return productVo;
	}

	public void setProductVo(ProductVo productVo) {
		this.productVo = productVo;
	}
    
	public ProductClassifyVo getProductClassifyVo() {
		return productClassifyVo;
	}

	public void setProductClassifyVo(ProductClassifyVo productClassifyVo) {
		this.productClassifyVo = productClassifyVo;
	}

	public void setProductLibService(IProductLibService productLibService) {
		this.productLibService = productLibService;
	}  
	

	public String productLibMgmt() throws Exception{
		if (productVo==null)  {
			productVo = new ProductVo();
			productVo.setProductClassifyId(new Long(-1));
			
		}
		if (productVo.getPage()==null) {
			productVo.setPage(new Pagination());
		}
		if (!productVo.getQuery().isEmpty()) {
			
			Map conditions = productVo.getQuery();
			String[] values =(String[])conditions.get("productClassifyid");
			long longv=-1; 
			try {
				longv=Long.parseLong(values[0]);
			} catch (Exception e) {
				longv=-1;
			}
			//log.debug("======================= in action value: "+longv);
			productVo.getPage().getQuery().put("productClassify.id", new Long(longv));
			productVo.setProductClassifyId(new Long(longv));
		}
		
		productVo.setPage(productLibService.findProductsByPage(productVo.getPage()));
		
		return SUCCESS;
	}
	public String addProductEntity() throws Exception{		 
		return SUCCESS;		
	}
	public List getCategories() throws Exception{
		List pclist = productLibService.findAvailibleClassifies();
		List pcvos = new ArrayList();
		ProductClassifyVo allpcvo = new ProductClassifyVo();
		allpcvo.setId(new Long(-1));
		allpcvo.setName("所有类别");
		pcvos.add(allpcvo);
		ProductClassifyVo nullpcvo = new ProductClassifyVo();
		nullpcvo.setId(new Long(0));
		nullpcvo.setName("空类别");
		pcvos.add(nullpcvo);
		for(int i=0;i<pclist.size();i++){
			ProductClassify pc = (ProductClassify)pclist.get(i);
			ProductClassifyVo pcvo = new ProductClassifyVo();
			pcvo.setId(pc.getId());
			pcvo.setName(productLibService.findClassifyPath(pc.getId()));
			pcvos.add(pcvo);
		}
		return pcvos;
	}
	public String saveProductEntity() throws Exception {
		String targetDirectory = ServletActionContext.getRequest().getRealPath("/upload");
		//处理上传文件
		if (productVo.getFile() != null) {
			String fileName = new Date().getTime()
					+ productVo.getFileFileName();
			CommonUtils
					.saveFile(productVo.getFile(), targetDirectory, fileName);
			productVo.setPicture("/upload/" + fileName);
		}
		
		ProductEntity pe = new ProductEntity();
		
		CommonUtils.copyAttrs(productVo, pe);

		if ((productVo.getAction() != null)
				&& (productVo.getAction().equals(IAction.ACTION_EDIT))) {
			//在修改时，没有实现修改图片的功能
			if ((productVo.getPicture() == null)
					|| (productVo.getPicture().equals(""))) {
				//查找原来对象，找到图片链接字段。
				ProductVo oldPeVo = productLibService.findProductEntityById(productVo.getId());
				pe.setPicture(oldPeVo.getPicture());
				
			}
			
			
			ProductClassify pc = new ProductClassify();
			if (productVo.getProductClassifyId() != null) {				
				pc.setId(productVo.getProductClassifyId());
				pe.setProductClassify(pc);					
				pe.getProductClassify().setId(productVo.getProductClassifyId());
			}
			long peid=productLibService.updateProductEntity(pe);
			productVo=productLibService.findProductEntityById(peid);
		//创建产品	
		} else {
			if(productVo.getAttrs()!=null) {
				Map ats = productVo.getAttrs();
				Set keys = ats.keySet();
				Iterator it=keys.iterator();
				while(it.hasNext()){
					String key = (String)it.next();
					String[] values= (String[])ats.get(key);
					log.debug("=============key: "+key+ "  value: "+values[0]);
				}
				
			}else{
				log.debug("=============attrs map is null");
			}
			ProductClassify pc = new ProductClassify();
			if (productVo.getProductClassifyId() != null) {
				pc.setId(productVo.getProductClassifyId());
				pe.setProductClassify(pc);
			}
			productLibService.saveProductEntity(pe);
		}

		return SUCCESS;
	}
	
	public String initEditProductEntity() throws Exception {
		if ((productVo!=null)&&(productVo.getId()!=0)) {
		//	initProductVoById(productVo.getId());
			productVo=productLibService.findProductEntityById(productVo.getId());
		}
		return SUCCESS;
	}
	public String viewProductEntity() throws Exception{
		if ((productVo!=null)&&(productVo.getId()!=0)) {
			initProductVoById(productVo.getId());
		}
		return SUCCESS;
	}
	public String deleteProductEntity() throws Exception {
		if ((productVo!=null)&&(productVo.getId()!=0)) {
			productLibService.deleteProductEntity(productVo.getId());
		}
		
		return SUCCESS;
	}
	private void initProductVoById(long productid) throws Exception{
		productVo=productLibService.findProductEntityById(productid);
		
		//productVo.setClassify(productLibService.findClassifyPath(pe.getProductClassify().getId()));
		
	}
	
	
	public String mgmtCategory() throws Exception { 
		if ((msg!=null)&&(msg.equals("success"))&&(productClassifyVo!=null)&&(productClassifyVo.getId()!=null)) {
			msg ="selected";
			
			ProductClassify pc=productLibService.findProductClassifyById(productClassifyVo.getId()); 
			productClassifyVo = new ProductClassifyVo();
			CommonUtils.copyAttrs(pc,productClassifyVo);
		}
		List list = productLibService.findAllClassifies();
		WebUtils.setRequestAttribute("treeList", list);
    	return SUCCESS;
	}
	public String categorytree() throws Exception { 		
		List list = productLibService.findAllClassifies();
		WebUtils.setRequestAttribute("treeList", list);
    	return SUCCESS;
	}
	
	 public String addCategory()throws Exception { 
	    	if (productClassifyVo.getId()!=null) {
	       		if (productClassifyVo.getId()!=-1) {
	    			ProductClassify pc=productLibService.findProductClassifyById(productClassifyVo.getId().longValue()); 
	    			productClassifyVo = new ProductClassifyVo();
	    			CommonUtils.copyAttrs(pc,productClassifyVo);
	    		}
	    		
	    	//	knowledgeCategoryVo =  sysmgmtService.findFuncById(funcVo.getId());
	          }
	    	return SUCCESS;
	    }
	 public String saveCategory() throws Exception { 
	    	
	    	if ((productClassifyVo != null )&& (productClassifyVo.getAction()!=null)&&(productClassifyVo.getAction().equals(IAction.ACTION_EDIT))) {
	    		ProductClassify pc= new ProductClassify();
				CommonUtils.copyAttrs(productClassifyVo,pc);
				long pcid=productLibService.updateProductClassify(pc) ;
				productClassifyVo = new ProductClassifyVo();
				productClassifyVo.setId(pcid);
			} else {
				ProductClassify pc= new ProductClassify();
				CommonUtils.copyAttrs(productClassifyVo,pc);
				long pcid=productLibService.saveProductClassify(pc);
				productClassifyVo = new ProductClassifyVo();
				productClassifyVo.setId(pcid);
			}
	    	
	    	msg="selected";
			WebUtils.getServletRequest().setAttribute(IAction.ACTION_MESSAGE, "success");	
	    	return SUCCESS;
	    }
	 public String initEditProductClassify() throws Exception {		
		 log.info("============pcid: "+productClassifyVo.getId());
		 ProductClassify pc=productLibService.findProductClassifyById(productClassifyVo.getId());
		 productClassifyVo = new ProductClassifyVo();
		 CommonUtils.copyAttrs(pc,productClassifyVo);
		 log.info("============pc parent id: "+pc.getParCategoryNo());
		 if (pc.getParCategoryNo()!=-1){
		     ProductClassify pcPart =productLibService.findProductClassifyById(pc.getParCategoryNo());		 
		     productClassifyVo.setParCategoryName(pcPart.getName());	
		 }else{
			 productClassifyVo.setParCategoryName("");
		 }
		 return SUCCESS;
		}
	 
	 public String deleteCategory() throws Exception { 
	    	if(productClassifyVo.getId() != null){
	    		if(productLibService.deleteProductClassify(productClassifyVo.getId())){
	    			return SUCCESS;
	    		}else{
	    			return ERROR;
	    		}
	    	}
	    	return ERROR;
	    	}
}
