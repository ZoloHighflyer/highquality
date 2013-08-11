package com.bluecreation.erp.contact.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bluecreation.erp.contact.model.Contact;
import com.bluecreation.erp.contact.vo.ContactVo;
import com.nci.cp.ds.paging.Pagination;


public class ContactFacede implements IContactFacede {
	private static Log log = LogFactory.getLog(ContactFacede.class);
	private IContactService contactService;

	public void setContactService(IContactService contactService) {
		this.contactService = contactService;
	}

	public Pagination findContactVosByUserWithPage(Pagination page, String owner) throws Exception {
		if (page==null) {
			page  = new Pagination();		
		}	  
		Pagination p = contactService.findContactsByUserWithPage(page, owner);
		List rds=p.getList();
		log.info("================= contact size: "+rds.size() );
		List vos = new ArrayList();
		for(int i=0;i<rds.size();i++) {
			Contact  c =(Contact)rds.get(i);
			vos.add(ContactServiceUtils.boToVo(c));
		}
		p.setList(vos);
		return p;
	}

	public long createContactVo(ContactVo cvo) throws Exception {
		return contactService.createContact(ContactServiceUtils.voToBo(cvo));
		
	}
	
	

}
