package com.nci.cp.core.sysmgt.service;
/**
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-8-29 
 */
public interface IServiceResult {
	public static String  SUCCESS_MESSAGE="invoking is successful!";
	public static String  FAIL_MESSAGE="invoking is fail!";
	public static int SUCCESS_STATUS=1;
	public static int FAIL_STATUS=0;
	public static int INVOKE_STATUS=SUCCESS_STATUS;
     /**
      * 返回服务调用的状态值
      * 0 表示成功
      * 1 表示失败
     * @return 
     */
     public int getResultStatus();
     public String  getResultMessage();
     public Object  getResultData();  
     public boolean isSuccess();
}
