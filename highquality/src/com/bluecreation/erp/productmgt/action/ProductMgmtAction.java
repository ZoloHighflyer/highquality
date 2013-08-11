package com.bluecreation.erp.productmgt.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.bluecreation.erp.productmgt.service.IProductMgmtService;
import com.bluecreation.erp.productmgt.vo.ProductComponent;
import com.bluecreation.erp.productmgt.vo.ProductVo;
import com.nci.cp.core.utils.CommonUtils;
import com.nci.cp.core.web.AbstractAction;
import com.nci.cp.core.web.IAction;
/**
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-10-30 
 */
public class ProductMgmtAction extends AbstractAction {
	private static Log log = LogFactory.getLog(ProductMgmtAction.class);
	protected IProductMgmtService productmgmtService;
	protected ProductVo   productVo;
	protected List        components;
	protected List        quantities;
	
	public void setProductmgmtService(IProductMgmtService productmgmtService) {
		this.productmgmtService = productmgmtService;
	}
	
	public ProductVo getProductVo() {
		return productVo;
	}

	public void setProductVo(ProductVo productVo) {
		this.productVo = productVo;
	}

    public List getComponents() {
		return components;
	}

	public void setComponents(List components) {
		this.components = components;
	}    
	
	public List getQuantities() {
		return quantities;
	}

	public void setQuantities(List quantities) {
		this.quantities = quantities;
	}

	public String addProduct() throws Exception {
		components = productmgmtService.findAllComponents();
		return SUCCESS;
    }
    public String initedit()throws Exception {
    	productVo = productmgmtService.findProductVoById(productVo.getId());
    	components = productmgmtService.findAllComponents();
    	
		if (productVo.getPartlist()!=null) {
			for(int i=0;i<productVo.getPartlist().size();i++) {
				ProductComponent pc = (ProductComponent)productVo.getPartlist().get(i);
				pc.setComponentId(pc.getComponentId()+"#"+pc.getComponentType());			
			}
			
		}
    	return SUCCESS;
    }
    public String viewProduct() throws Exception {
    	//取得所有配件信息，在页面上显示这个产品的所有配件。
    	components = productmgmtService.findAllComponents();
    	productVo = productmgmtService.findProductVoById(productVo.getId());
    	if (productVo.getPartlist()!=null) {
			for(int i=0;i<productVo.getPartlist().size();i++) {
				ProductComponent pc = (ProductComponent)productVo.getPartlist().get(i);
				pc.setComponentId(pc.getComponentId()+"#"+pc.getComponentType());			
			}
			
		}
    	return SUCCESS;
    }
    public String saveProduct() throws Exception {
    	String targetDirectory =ServletActionContext.getRequest().getRealPath("/upload");
    	long time =new Date().getTime();
		if (productVo.getAsbphotofile()!=null) {			
			String asbfileName = time+productVo.getAsbphotofileFileName();
			CommonUtils.saveFile(productVo.getAsbphotofile(), targetDirectory,asbfileName );
			productVo.setAssemblephoto("/upload/"+asbfileName); 		
		}
    	
		if (productVo.getEffectphotofile()!=null) {			
			String effectfileName = time+productVo.getEffectphotofileFileName();
			CommonUtils.saveFile(productVo.getEffectphotofile(), targetDirectory,effectfileName );
			productVo.setEffectphoto("/upload/"+effectfileName); 
			
		}
		
		if (productVo.getPhotofile()!=null) {			
			String photofileName = time+productVo.getPhotofileFileName();
			CommonUtils.saveFile(productVo.getPhotofile(), targetDirectory,photofileName );
			productVo.setPhoto("/upload/"+photofileName); 			
		}
		
		List cps = obtainComponents();
		productVo.setPartlist(cps);
    	if ((productVo.getAction()!=null)&&(productVo.getAction().equals(IAction.ACTION_EDIT))) {
    		ProductVo oldProductVo = productmgmtService.findProductVoById(productVo.getId());
            if ((productVo.getAssemblephoto()==null)||(productVo.getAssemblephoto().equals(""))){
            	productVo.setAssemblephoto(oldProductVo.getAssemblephoto());
            }
            if ((productVo.getEffectphoto()==null)||(productVo.getEffectphoto().equals(""))){
            	productVo.setEffectphoto(oldProductVo.getEffectphoto());
            }
            if ((productVo.getPhoto()==null)||(productVo.getPhoto().equals(""))){
            	productVo.setPhoto(oldProductVo.getPhoto());
            }
           
    		productmgmtService.updateProductVo(productVo);
		}else {			
		    productmgmtService.createProduct(productVo);
		}
    	return SUCCESS;
    }

	private List obtainComponents() {
		List cps =new ArrayList();
		if (components!=null) {				
			for(int i=0;i<components.size();i++){
				 String cpstr = (String)components.get(i);
				 int idno=cpstr.indexOf("#");
				 ProductComponent p = new ProductComponent();
				 p.setComponentId(cpstr.substring(0,idno));					 
				 p.setComponentType(Integer.parseInt(cpstr.substring(idno+1,cpstr.length())));
				 String qstr =(String)quantities.get(i); 
				 int quantity =0;
				 try {
					quantity = Integer.parseInt(qstr);
				} catch (Exception e) {
					quantity =0;
				} 
			    p.setComponentQuantity(quantity);
			    cps.add(p);
			}
		}
		return cps;
	}
   
	public String productMgmt() throws Exception{		
		if (productVo==null)  {
			productVo= new ProductVo();
			productVo.setPage(productmgmtService.findAllProductVosByPage(null));
		}else {
			productVo.setPage(productmgmtService.findAllProductVosByPage(productVo.getPage()));
		}
		return SUCCESS;
	}
	public String delProduct() throws Exception {
		productmgmtService.deleteProduct(productVo.getId());
		return SUCCESS;
	}
}
