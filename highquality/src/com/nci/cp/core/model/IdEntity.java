package com.nci.cp.core.model;

import java.io.Serializable;

/**
 * IdEntity. Interface. This is interface for all entity for operate id field.
 * 
 * @author Oliver Chen 2010-3-23
 * 
 */
public interface IdEntity extends Serializable {
	
	/**
	 * @return
	 */
	Serializable getEntityId();

}
