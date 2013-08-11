package com.nci.cp.ds.paging;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @company BlueCreation Workspace
 * @author OliverChan
 * @version 0.1
 * @date 2011-10-25
 */
public class Pagination {

	private List list = null; //要返回的某一页的记录列表

	private int totalCount; //总记录数
	private int currentPage = 1; //当前页
	private int pageSize = 10; //每页记录数
	private int totalPage;

	private boolean firstPage; //是否为第一页

	private boolean lastPage; //是否为最后一页

	private boolean hasPreviousPage; //是否有前一页

	private boolean hasNextPage; //是否有下一页
    private Map  query = new HashMap();
    
	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int allRow) {
		this.totalCount = allRow;
	}

	public int getTotalPage() {
		return (totalCount % pageSize) == 0 ? totalCount / pageSize
				: totalCount / pageSize + 1;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartPosition() {
		return (this.currentPage - 1) * this.pageSize;
	}

	/** */
	/**
	 * 以下判断页的信息,只需getter方法(is方法)即可
	 * @return
	 */

	public boolean isFirstPage() {
		return currentPage == 1; // 如是当前页是第1页
	}

	public void setFirstPage(boolean firstPage) {
		this.firstPage = firstPage;
	}

	public boolean isLastPage() {
		return currentPage == getTotalPage(); //如果当前页是最后一页
	}

	public boolean isHasPreviousPage() {
		return currentPage != 1; //只要当前页不是第1页
	}

	public boolean isHasNextPage() {
		return currentPage != getTotalPage(); //只要当前页不是最后1页
	}

	public Map getQuery() {
		return query;
	}

	public void setQuery(Map query) {
		this.query = query;
	}
    
}
