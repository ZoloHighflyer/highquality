package com.bluecreation.erp.contact.action;

import com.bluecreation.erp.contact.service.ContactFacede;
import com.bluecreation.erp.contact.service.IContactFacede;
import com.bluecreation.erp.contact.vo.ContactVo;
import com.nci.cp.core.sysmgt.model.IUserInfo;
import com.nci.cp.core.utils.WebUtils;
import com.nci.cp.core.web.AbstractAction;
import com.nci.cp.teaminfo.km.vo.KnowledgeCategoryVo;

public class ContactAction extends AbstractAction {
    private IContactFacede contactFacede;
    private ContactVo      contactVo;
    
    

	public ContactVo getContactVo() {
		return contactVo;
	}
	public void setContactVo(ContactVo contactVo) {
		this.contactVo = contactVo;
	}
	public void setContactFacede(IContactFacede contactFacede) {
		this.contactFacede = contactFacede;
	}
	public String contactMgmt()throws Exception {
		
		if (contactVo==null)  {
			contactVo= new ContactVo();
			contactVo.setPage(contactFacede.findContactVosByUserWithPage(null,(String)WebUtils.getCurrentUser().getProperty(IUserInfo.USER_ID)));
		}else {	
			
			contactVo.setPage(contactFacede.findContactVosByUserWithPage(contactVo.getPage(),(String)WebUtils.getCurrentUser().getProperty(IUserInfo.USER_ID)));
		}		
		return SUCCESS;
	}
    public String saveContact() throws Exception {
    	contactVo.setOwner((String)WebUtils.getCurrentUser().getProperty(IUserInfo.USER_ID));
    	contactFacede.createContactVo(contactVo);
    	
    	return SUCCESS;
    }
    
}
