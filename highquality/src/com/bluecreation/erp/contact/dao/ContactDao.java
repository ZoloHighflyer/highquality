package com.bluecreation.erp.contact.dao;

import com.bluecreation.erp.contact.model.Contact;
import com.nci.cp.core.dao.AbstractDao;
import com.nci.cp.ds.paging.Pagination;

public class ContactDao extends AbstractDao implements IContactDao {

	@Override
	protected Class getDaoModel() {
		// TODO Auto-generated method stub
		return Contact.class;
	}
	public Pagination findContactsByUserWithPage(Pagination page,String owner) {
		String hsql ="select c from Contact c where  c.owner='"+owner+"' order by c.modifyDate desc";
		
		return findEntitiesByPage(hsql,page.getStartPosition(),page.getPageSize(),page.getCurrentPage());
	}
}
