package com.nci.cp.core.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nci.cp.core.dao.ICommonEntityExtDao;
import com.nci.cp.core.model.CommonEntityExt;
import com.nci.cp.core.model.IdEntity;
import com.nci.cp.core.model.StringAttr;

public class CommonEntityExtService implements ICommonEntityExtService {
	private static Log log = LogFactory.getLog(CommonEntityExtService.class);
	private ICommonEntityExtDao  commonEntityExtDao;
	public static String ATTR_TYPE_STRING="string";
	public static String ATTR_TYPE_TEXT="text";
	public static String ATTR_TYPE_INT="int";
	
    private Map defMap = new HashMap();
    
	public CommonEntityExtService() {
		super();
		AttrDefine strdef = new AttrDefine(ATTR_TYPE_STRING,null);
		Map pexDef = new HashMap();
		pexDef.put("brand", strdef);
		pexDef.put("specification", strdef);
		pexDef.put("crowdBound", strdef);
		defMap.put("productext", pexDef);		
	}
    
	public void setCommonEntityExtDao(ICommonEntityExtDao commonEntityExtDao) {
		this.commonEntityExtDao = commonEntityExtDao;
	}
    public Map findEntityDef(String defName) {    	
    	return (Map)defMap.get(defName);
    }
    public StringAttr saveStringAttr(Long caId,String attrName,String value) throws Exception{
    	 StringAttr str = new StringAttr();
		 str.setEntityExtId(caId);
		 str.setName(attrName);		 					 
		 str.setValue(value);
		 str=(StringAttr)commonEntityExtDao.saveStringAttr(str);
		
    	return str;
    }
	public void saveCommonEntity(IdEntity entityId,String extentityName,
			Map values) throws Exception{
		 CommonEntityExt cee = commonEntityExtDao.findCommEntityExiByEntityId(entityId,extentityName);
		 if (cee==null) {
			 cee=commonEntityExtDao.saveCommonEntity(entityId, extentityName);			 
			 Map exd = (Map)defMap.get(extentityName);
			 
			 if ((exd!=null)&&(values!=null)) {
				 Set keySet = exd.keySet();
				 Iterator it = keySet.iterator();
				 while (it.hasNext()) {
					 String key =(String)it.next();
					 AttrDefine attr =(AttrDefine)exd.get(key);
					 if (ATTR_TYPE_STRING.equals(attr.getType())) {						 
						 String strvalue=(String)values.get(key);						 
						 saveStringAttr(cee.getEntityId(),extentityName,strvalue);
					 }
				 }
				 
				 
			 }
			 
			 
		 }else{
			 log.warn(this.getClass().getName()+" saveCommonEntity the CommonEntityExt is existed.");
		 }

	}
	public class AttrDefine {
		protected String type;
		protected String elementType;
		
		public AttrDefine() {
			super();		
		}
		public AttrDefine(String type, String elementType) {
			super();
			this.type = type;
			this.elementType = elementType;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getElementType() {
			return elementType;
		}
		public void setElementType(String elementType) {
			this.elementType = elementType;
		}
		

	}

}

