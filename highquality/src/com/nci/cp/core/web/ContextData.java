package com.nci.cp.core.web;

/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-4-27
 */
public class ContextData {
	public static String SCOPE_SESSION="SCOPE_SESSION";
	public static String SCOPE_REQUEST="SCOPE_REQUEST";
    private boolean iset=false;
    private String  scope=null;
    private String  key=null;
	public boolean isIset() {
		return iset;
	}
	public void setIset(boolean iset) {
		this.iset = iset;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
    
    
}
