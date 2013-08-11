package com.bluecreation.erp.productmgt.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bluecreation.erp.productmgt.model.CommonComponent;
import com.bluecreation.erp.productmgt.model.Fittings;
import com.bluecreation.erp.productmgt.vo.FittingsVo;
/**
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-10-30 
 */
public final class FittingServiceUtils {
	private static Log log = LogFactory.getLog(FittingServiceUtils.class);

	public static CommonComponent voToBo(FittingsVo ftvo) throws Exception {
		CommonComponent ftbo = new Fittings();
		try {
			BeanUtils.copyProperties(ftbo, ftvo);

		} catch (IllegalAccessException e) {
			e.printStackTrace();
			log.error(" FittingServiceUtils.voToBo fail! " + e.getMessage());
			throw new Exception(" FittingServiceUtils.voToBo fail! "
					+ e.getMessage());
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			log.error(" FittingServiceUtils.voToBo fail! " + e.getMessage());
			throw new Exception(" FittingServiceUtils.voToBo fail! "
					+ e.getMessage());
		}
		return ftbo;
	}

	public static FittingsVo boTovo(CommonComponent ft) throws Exception {
		FittingsVo ftvo = new FittingsVo();
		try {
			BeanUtils.copyProperties(ftvo, ft);

		} catch (IllegalAccessException e) {
			e.printStackTrace();
			log.error(" FittingServiceUtils.boTovo fail! " + e.getMessage());
			throw new Exception(" FittingServiceUtils.boTovo fail! "
					+ e.getMessage());
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			log.error(" FittingServiceUtils.boTovo fail! " + e.getMessage());
			throw new Exception(" FittingServiceUtils.boTovo fail! "
					+ e.getMessage());
		}
		
		return ftvo;
	}
	public static List<FittingsVo> bosTovos(List<Fittings> fts) throws Exception {
		List ftvos = new ArrayList();
		for(int i=0;i<fts.size();i++) {
			CommonComponent ft = (CommonComponent)fts.get(i);
			FittingsVo ftvo = boTovo(ft);
			ftvos.add(ftvo);
		}
		return ftvos;
	}
}
