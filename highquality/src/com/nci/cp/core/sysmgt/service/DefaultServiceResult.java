package com.nci.cp.core.sysmgt.service;

public class DefaultServiceResult implements IServiceResult {
    protected int status= new Integer(0);
    protected String  message="";
    protected Object  data;
	public Object getResultData() {
		return data;
	}

	public String getResultMessage() {
		return message;
	}

	public int getResultStatus() {
		return IServiceResult.INVOKE_STATUS;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isSuccess() {
		if (IServiceResult.INVOKE_STATUS==1) {
			return true;
		}
		return false;
	}
    
	
}
