package com.bluecreation.erp.contact.service;

import com.bluecreation.erp.contact.vo.ContactVo;
import com.nci.cp.core.service.IService;
import com.nci.cp.ds.paging.Pagination;


public interface IContactFacede extends IService {
	public Pagination findContactVosByUserWithPage(Pagination page,String owner) throws Exception;
	public long createContactVo(ContactVo cvo)throws Exception;
}
