package com.nci.cp.core.sysmgt.vo;

import java.util.List;

import com.nci.cp.core.model.BaseVo;
import com.nci.cp.core.sysmgt.model.Operation;

/**
 * Common Platform Team
 * 
 * @author Oliver Chan
 * @since 0.1
 */
public class FuncVo extends BaseVo {
	private Long    id = null;
	private long    parfuncno;    //父功能ID
	private String  parfuncname;  // 父功能名
	private String  funcname;        // 功能名称
	private String  funcaction;
	private String  descr;                  // 描述
    private List<Operation> opts;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	

	

	public long getParfuncno() {
		return parfuncno;
	}

	public void setParfuncno(long parfuncno) {
		this.parfuncno = parfuncno;
	}

	public String getParfuncname() {
		return parfuncname;
	}

	public void setParfuncname(String parfuncname) {
		this.parfuncname = parfuncname;
	}

	public String getFuncname() {
		return funcname;
	}

	public void setFuncname(String funcname) {
		this.funcname = funcname;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}
    
	public String getFuncaction() {
		return funcaction;
	}

	public void setFuncaction(String funcaction) {
		this.funcaction = funcaction;
	}

	public List<Operation> getOpts() {
		return opts;
	}

	public void setOpts(List<Operation> opts) {
		this.opts = opts;
	}
     
}
