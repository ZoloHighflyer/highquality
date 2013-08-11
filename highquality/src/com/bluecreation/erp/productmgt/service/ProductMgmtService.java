package com.bluecreation.erp.productmgt.service;

import java.util.ArrayList;
import java.util.List;

import com.bluecreation.erp.productmgt.model.CommonComponent;
import com.bluecreation.erp.productmgt.model.Fittings;
import com.bluecreation.erp.productmgt.model.Product;
import com.bluecreation.erp.productmgt.vo.ProductComponent;
import com.bluecreation.erp.productmgt.vo.ProductVo;
import com.nci.cp.ds.paging.Pagination;

/**
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-10-30 
 */
public class ProductMgmtService implements IProductMgmtService {
    protected IProductService productService;
    protected IFittingsService fittingsService;

	public void setProductService(IProductService productService) {
		this.productService = productService; 
	}
    
	public void setFittingsService(IFittingsService fittingsService) {
		this.fittingsService = fittingsService;
	}

	public long createProduct(ProductVo productvo) throws Exception {		
		return productService.createProduct(productvo);
	}

	public boolean deleteProduct(long productid) throws Exception {
		
		return productService.deleteProduct(productid);
	}

	public Pagination findAllProductVosByPage(Pagination page) throws Exception {		
		return productService.findAllProductVosByPage(page);
	}

	public ProductVo findProductVoById(long productid) throws Exception {
		ProductVo productVo =productService.findProductVoById(productid);
		
		return productVo;
	}

	public long updateProductVo(ProductVo productvo) throws Exception {        
		return productService.updateProductVo(productvo);
	}
    public CommonComponent findProductOrFitting(long entityid,int componentType) throws Exception {
    	if (componentType==0) {
    		fittingsService.findFittingsVoById(entityid);
    	}else if (componentType==1) {
    		
    	}
    	return null;
    }
	public List findAllComponents() throws Exception {
		List components = new ArrayList();
		List products = productService.findAllProductVos();
		for(int i=0;i<products.size();i++) {
			Product p = (Product)products.get(i);
			
			ProductComponent pcomponent = new ProductComponent();
			pcomponent.setComponentId(p.getId()+"#1");
			pcomponent.setComponentName(p.getName());
			components.add(pcomponent);
		}
		List fittings = fittingsService.findAllFittingsVos();
		for(int i=0;i<fittings.size();i++){
			Fittings f = (Fittings)fittings.get(i);
			ProductComponent pcomponent = new ProductComponent();
			pcomponent.setComponentId(f.getId()+"#0");
			pcomponent.setComponentName(f.getName());
			components.add(pcomponent);
		}
		return components;
	}
   
    
}


