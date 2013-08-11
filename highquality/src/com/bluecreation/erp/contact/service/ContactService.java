package com.bluecreation.erp.contact.service;

import com.bluecreation.erp.contact.dao.IContactDao;
import com.bluecreation.erp.contact.model.Contact;
import com.nci.cp.ds.paging.Pagination;

public class ContactService implements IContactService {
	private IContactDao contactDao;

	public void setContactDao(IContactDao contactDao) {
		this.contactDao = contactDao;
	}
	public Pagination findContactsByUserWithPage(Pagination page,String owner){
		
		return contactDao.findContactsByUserWithPage(page, owner);	
	}
	public long createContact(Contact c) throws Exception {
		 contactDao.createEntity(c);
		return c.getId();
	}
	
	
}
