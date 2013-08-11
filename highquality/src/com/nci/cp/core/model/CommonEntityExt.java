package com.nci.cp.core.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class CommonEntityExt extends AbstractEntity {
	public static String ATTR_STRING="com.nci.cp.core.model.StringAttr";
	protected String name="commattr";
	protected String componentName;
	protected Long   entityId=null;
	protected Map attrsDef = new HashMap();
	protected Map attrValues=new HashMap();
	public CommonEntityExt() {
		super();		
		attrsDef.put("brand", ATTR_STRING);
		attrsDef.put("specification", ATTR_STRING);
		attrsDef.put("crowdBound", ATTR_STRING);		
	}
	
	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
    
	
}
