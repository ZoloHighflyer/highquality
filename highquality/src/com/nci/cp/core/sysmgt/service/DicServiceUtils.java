package com.nci.cp.core.sysmgt.service;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nci.cp.core.sysmgt.model.DicEntry;
import com.nci.cp.core.sysmgt.model.DicType;
import com.nci.cp.core.sysmgt.vo.DicEntryVo;
import com.nci.cp.core.sysmgt.vo.DicTypeVo;

/**
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-11-03 
 */
public final class DicServiceUtils {
	private static Log log = LogFactory.getLog(DicServiceUtils.class);
	private static String  clzName= "DicServiceUtils";
    public static DicTypeVo boToVo(DicType dictype)  throws Exception {
    	DicTypeVo dictypeVo = new DicTypeVo();
    	try {
			BeanUtils.copyProperties(dictypeVo, dictype);

		} catch (IllegalAccessException e) {
			e.printStackTrace();
			log.error(clzName+".boToVo for DicTypeVo fail! " + e.getMessage());
			throw new Exception(clzName+".boToVo for DicTypeVo fail! "
					+ e.getMessage());
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			log.error(clzName+".boToVo for DicTypeVo fail! " + e.getMessage());
			throw new Exception(clzName+".boToVo  for DicTypeVo fail! "
					+ e.getMessage());
		}
		
		return dictypeVo;
    	
    }
    public static DicType voToBo(DicTypeVo dictypeVo) throws Exception {
    	DicType dicType = new DicType();
    	try { 
			BeanUtils.copyProperties(dicType,dictypeVo);

		} catch (IllegalAccessException e) {
			e.printStackTrace();
			log.error(clzName+".voToBo for DicTypeVo fail! " + e.getMessage());
			throw new Exception(clzName+".voToBo for DicTypeVo fail! "
					+ e.getMessage());
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			log.error(clzName+".voToBo for DicTypeVo fail! " + e.getMessage());
			throw new Exception(clzName+".voToBo for DicTypeVo fail! "
					+ e.getMessage());
		}
		
		return dicType;

    }
    public static DicEntryVo boToVo(DicEntry dicentry)  throws Exception {
    	DicEntryVo dicentryVo = new DicEntryVo();
    	try {
			BeanUtils.copyProperties(dicentryVo, dicentry);
			DicTypeVo dictypeVo = DicServiceUtils.boToVo(dicentry.getDicType());
			dicentryVo.setDicTypeVo(dictypeVo);

		} catch (IllegalAccessException e) {
			e.printStackTrace();
			log.error(clzName+".boToVo for DicEntryVo fail! " + e.getMessage());
			throw new Exception(clzName+".boToVo for DicEntryVo fail! "
					+ e.getMessage());
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			log.error(clzName+".boToVo for DicEntryVo fail! " + e.getMessage());
			throw new Exception(clzName+".boToVo  for DicEntryVo fail! "
					+ e.getMessage());
		}
		
		return dicentryVo;
    	
    }
    public static DicEntry voToBo(DicEntryVo dicentryVo) throws Exception {
    	DicEntry dicEntry = new DicEntry();
    	try {    		
			BeanUtils.copyProperties(dicEntry,dicentryVo);			
			DicType dicType = DicServiceUtils.voToBo(dicentryVo.getDicTypeVo());
			dicEntry.setDicType(dicType);

		} catch (IllegalAccessException e) {
			e.printStackTrace();
			log.error(clzName+".voToBo for DicEntry fail! " + e.getMessage());
			throw new Exception(clzName+".voToBo for DicEntry fail! "
					+ e.getMessage());
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			log.error(clzName+".voToBo for DicEntry fail! " + e.getMessage());
			throw new Exception(clzName+".voToBo for DicEntry fail! "
					+ e.getMessage());
		}
		
		return dicEntry;

    }
}
