package com.nci.cp.core.model;

import java.util.HashMap;
import java.util.Map;

import com.nci.cp.ds.paging.Pagination;

public abstract class BaseVo {
    protected String action;
    protected Pagination  page;
    protected Map  query= new HashMap();
	
	
   
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Pagination getPage() {
		return page;
	}

	public void setPage(Pagination page) {
		this.page = page;
	}

	public Map getQuery() {
		return query;
	}

	public void setQuery(Map query) {
		this.query = query;
	}
	
	
    
}
