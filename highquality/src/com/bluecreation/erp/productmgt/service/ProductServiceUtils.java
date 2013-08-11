package com.bluecreation.erp.productmgt.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bluecreation.erp.productmgt.model.Product;
import com.bluecreation.erp.productmgt.model.ProductPart;
import com.bluecreation.erp.productmgt.vo.ProductComponent;
import com.bluecreation.erp.productmgt.vo.ProductVo;

/**
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-10-30 
 */
public final class ProductServiceUtils {
	private static Log log = LogFactory.getLog(ProductServiceUtils.class);
    public static ProductVo boToVo(Product product)  throws Exception {
    	ProductVo productVo = new ProductVo();
    	try {
			BeanUtils.copyProperties(productVo, product);

		} catch (IllegalAccessException e) {
			e.printStackTrace();
			log.error(" ProductServiceUtils.boToVo fail! " + e.getMessage());
			throw new Exception(" ProductServiceUtils.boToVo fail! "
					+ e.getMessage());
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			log.error(" ProductServiceUtils.boToVo fail! " + e.getMessage());
			throw new Exception(" ProductServiceUtils.boToVo fail! "
					+ e.getMessage());
		}
		if (product.getParts()!=null){
			Iterator it = product.getParts().iterator();
			List cps = new ArrayList();
			while(it.hasNext()){
				ProductPart pp = (ProductPart)it.next();
				ProductComponent pc = new ProductComponent();
				pc.setComponentId(""+pp.getComponentId()+"");
				pc.setComponentType(pp.getComponentType());
				pc.setComponentQuantity(pp.getQuantity());
				cps.add(pc);				
			}
			productVo.setPartlist(cps);
		}
		return productVo;
    	
    }
    public static Product voToBo(ProductVo productVo) throws Exception {
    	Product product = new Product();
    	try {
			BeanUtils.copyProperties(product,productVo);

		} catch (IllegalAccessException e) {
			e.printStackTrace();
			log.error(" ProductServiceUtils.voToBo fail! " + e.getMessage());
			throw new Exception(" ProductServiceUtils.voToBo fail! "
					+ e.getMessage());
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			log.error(" ProductServiceUtils.voToBo fail! " + e.getMessage());
			throw new Exception(" ProductServiceUtils.voToBo fail! "
					+ e.getMessage());
		}
		if (productVo.getPartlist()!=null) {
			Set pparts = new HashSet();
			for(int i=0;i<productVo.getPartlist().size();i++){
				ProductComponent pc =(ProductComponent)productVo.getPartlist().get(i);
				ProductPart pp = new ProductPart();
				pp.setComponentId(Long.parseLong(pc.getComponentId()));
				pp.setComponentType(pc.getComponentType());
				pp.setQuantity(pc.getComponentQuantity());
				pparts.add(pp);
			}
			product.setParts(pparts);
		}
		return product;

    }
}
