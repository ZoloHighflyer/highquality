package com.nci.cp.core.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2010-12-25 
 */
public abstract class AbstractEntity implements IdEntity {
	
	protected Long id = null;   
	
    protected java.util.Date createDate;  	
	
    protected java.util.Date modifyDate;    
    
	
	
	public AbstractEntity() {
		super();
	}
    
	public AbstractEntity(Long eid) {
		this();
		setId(eid);
	}
	public AbstractEntity(String eid) {
		this();
	    try {
			setId(eid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Serializable getEntityId() {		
		return getId();
	}    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {		
			this.id = (Long)id;		
	}
    
	public void setId(String id) throws Exception {
		try {
			Long.parseLong(id);
			this.id=new Long(Long.parseLong(id));
		} catch (Exception e) {
			throw new Exception("Can not change string to long");
		}
		
    }

	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	public java.util.Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
    
	
	
}
