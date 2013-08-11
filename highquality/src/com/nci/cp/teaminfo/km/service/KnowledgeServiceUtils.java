package com.nci.cp.teaminfo.km.service;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nci.cp.core.sysmgt.service.DicServiceUtils;
import com.nci.cp.teaminfo.km.model.KnowledgeCategory;
import com.nci.cp.teaminfo.km.model.KnowledgeRecord;
import com.nci.cp.teaminfo.km.vo.KnowledgeCategoryVo;
import com.nci.cp.teaminfo.km.vo.KnowledgeRecordVo;

/**
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-10-30 
 */
public final class KnowledgeServiceUtils {
	private static Log log = LogFactory.getLog(KnowledgeServiceUtils.class);
	private static String  clzName= DicServiceUtils.class.getName();
    public static KnowledgeRecordVo boToVo(KnowledgeRecord knowledgeRecord)  throws Exception {
    	KnowledgeRecordVo krv = new KnowledgeRecordVo();
    	krv.setId(knowledgeRecord.getId().longValue());
    	krv.setTitle(knowledgeRecord.getTitle());
    	krv.setContent(knowledgeRecord.getContent());
    	krv.setOwner(knowledgeRecord.getOwner());
    	krv.setCreateDate(knowledgeRecord.getCreateDate());
    	if (knowledgeRecord.getInfoCategory()!=null) {
    		krv.setKcid(knowledgeRecord.getInfoCategory().getId());
    	}
    	
    	return krv;
    	
    }
    public static KnowledgeRecord voToBo(KnowledgeRecordVo knowledgeRecordVo)  throws Exception {
    	KnowledgeRecord kr = new KnowledgeRecord();
    	try {
			BeanUtils.copyProperties(kr, knowledgeRecordVo);
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			log.error(clzName+".boToVo for KnowledgeRecordVo fail! " + e.getMessage());
			throw new Exception(clzName+".boToVo for KnowledgeRecordVo fail! "
					+ e.getMessage());
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			log.error(clzName+".boToVo for KnowledgeRecordVo fail! " + e.getMessage());
			throw new Exception(clzName+".boToVo  for KnowledgeRecordVo fail! "
					+ e.getMessage());
		}
    	return kr;
    	
    }
   public static KnowledgeCategory voToBo(KnowledgeCategoryVo categoryVo)throws Exception {
	   KnowledgeCategory kc = new KnowledgeCategory();
   	try {
			BeanUtils.copyProperties(kc, categoryVo);			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			log.error(clzName+".boToVo for KnowledgeCategoryVo fail! " + e.getMessage());
			throw new Exception(clzName+".voToBo for KnowledgeCategoryVo fail! "
					+ e.getMessage());
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			log.error(clzName+".boToVo for KnowledgeCategoryVo fail! " + e.getMessage());
			throw new Exception(clzName+".voToBo  for KnowledgeCategoryVo fail! "
					+ e.getMessage());
		}
   	return kc;
   }
   public static KnowledgeCategoryVo boToVo(KnowledgeCategory category)throws Exception {
	   KnowledgeCategoryVo kcvo = new KnowledgeCategoryVo();
    	try {
			BeanUtils.copyProperties(kcvo, category);			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			log.error(clzName+".boToVo for KnowledgeCategoryVo fail! " + e.getMessage());
			throw new Exception(clzName+".boToVo for KnowledgeCategoryVo fail! "
					+ e.getMessage());
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			log.error(clzName+".boToVo for KnowledgeCategoryVo fail! " + e.getMessage());
			throw new Exception(clzName+".boToVo  for KnowledgeCategoryVo fail! "
					+ e.getMessage());
		}
   	 return kcvo;
   }
}
