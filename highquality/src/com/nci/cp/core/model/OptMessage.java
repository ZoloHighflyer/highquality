package com.nci.cp.core.model;
/**
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2012-2-25 
 */
public class OptMessage {
    protected boolean successful=false;
    protected String  msg="";
    
	public OptMessage() {
		super();
			}
	public OptMessage(boolean successful, String msg) {
		super();
		this.successful = successful;
		this.msg = msg;
	}
	public boolean isSuccessful() {
		return successful;
	}
	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
    
}
