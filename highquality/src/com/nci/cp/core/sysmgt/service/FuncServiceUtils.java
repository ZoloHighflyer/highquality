package com.nci.cp.core.sysmgt.service;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nci.cp.core.exception.ServiceException;
import com.nci.cp.core.sysmgt.model.Func;
import com.nci.cp.core.sysmgt.vo.FuncVo;

/**
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-9-27 
 */
public final class FuncServiceUtils {
	 private static Log log = LogFactory.getLog(FuncServiceUtils.class);
	 public static Func voToBo(FuncVo funcVo) throws ServiceException {
	    	Func func = new Func();
	    	try {
				BeanUtils.copyProperties(func, funcVo);
				
			} catch (IllegalAccessException e) {			
				e.printStackTrace();
				log.error(" FuncServiceUtils.voToBo(FuncVo funcVo) fail! "+e.getMessage());
				throw new ServiceException(" FuncServiceUtils.voToBo(FuncVo funcVo) fail!");	
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				throw new ServiceException(" FuncServiceUtils.voToBo(FuncVo funcVo) fail!");	
			}
	    	return func;
	    }
	 public static FuncVo boToVo(Func func) throws ServiceException{
	    	FuncVo funcVo = new FuncVo();
	    	try {
				BeanUtils.copyProperties(funcVo, func);
				
			} catch (IllegalAccessException e) {			
				e.printStackTrace();
				log.error(" FuncServiceUtils.boToVo(Func func) fail! "+e.getMessage());
				throw new ServiceException(" FuncServiceUtils.boToVo(Func func) fail!");	
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
	    	return funcVo;
	    }
}
