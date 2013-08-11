package com.bluecreation.erp.contact.service;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bluecreation.erp.contact.model.Contact;
import com.bluecreation.erp.contact.vo.ContactVo;
import com.nci.cp.teaminfo.km.model.KnowledgeRecord;
import com.nci.cp.teaminfo.km.vo.KnowledgeRecordVo;

/**
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-10-30 
 */
public final class ContactServiceUtils {
	private static Log log = LogFactory.getLog(ContactServiceUtils.class);
	private static String  clzName= ContactServiceUtils.class.getName();
    public static ContactVo boToVo(Contact contact)  throws Exception {
    	ContactVo cvo = new ContactVo();
    	try {
			BeanUtils.copyProperties(cvo, contact);
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			log.error(clzName+".boToVo for ContactVo fail! " + e.getMessage());
			throw new Exception(clzName+".boToVo for ContactVo fail! "
					+ e.getMessage());
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			log.error(clzName+".boToVo for ContactVo fail! " + e.getMessage());
			throw new Exception(clzName+".boToVo  for ContactVo fail! "
					+ e.getMessage());
		}
		return cvo;
    	
    }
    public static Contact voToBo(ContactVo contactVo)  throws Exception {
    	Contact c = new Contact();
    	try {
			BeanUtils.copyProperties(c, contactVo);
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			log.error(clzName+".boToVo for ContactVo fail! " + e.getMessage());
			throw new Exception(clzName+".boToVo for ContactVo fail! "
					+ e.getMessage());
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			log.error(clzName+".boToVo for ContactVo fail! " + e.getMessage());
			throw new Exception(clzName+".boToVo  for ContactVo fail! "
					+ e.getMessage());
		}
    	return c;
    	
    }
   
}
