package com.bluecreation.erp.contact.dao;

import com.nci.cp.core.dao.IDao;
import com.nci.cp.ds.paging.Pagination;

public interface IContactDao extends IDao {
	public Pagination findContactsByUserWithPage(Pagination page,String owner);
}
