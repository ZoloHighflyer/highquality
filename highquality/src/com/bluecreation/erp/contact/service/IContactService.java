package com.bluecreation.erp.contact.service;

import com.bluecreation.erp.contact.model.Contact;
import com.nci.cp.core.service.IService;
import com.nci.cp.ds.paging.Pagination;

public interface IContactService extends IService {
	public Pagination findContactsByUserWithPage(Pagination page,String owner);
	public long createContact(Contact c)throws Exception;
}
