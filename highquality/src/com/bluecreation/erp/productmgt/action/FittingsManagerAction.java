package com.bluecreation.erp.productmgt.action;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.bluecreation.erp.productmgt.service.IFittingsService;
import com.bluecreation.erp.productmgt.vo.FittingsVo;
import com.bluecreation.erp.productmgt.vo.ProductVo;
import com.nci.cp.core.utils.CommonUtils;
import com.nci.cp.core.web.AbstractAction;
import com.nci.cp.core.web.IAction;
/**
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-10-20 
 */
public class FittingsManagerAction extends AbstractAction {
	private static Log log = LogFactory.getLog(FittingsManagerAction.class);
	private IFittingsService ftService;
	private FittingsVo       ftvo;
	
	
	public FittingsVo getFtvo() {
		return ftvo;
	}
	public void setFtvo(FittingsVo ftvo) {
		this.ftvo = ftvo;
	}
	public void setFtService(IFittingsService ftService) {
		this.ftService = ftService;
	}
	public String fittingsMgmt() throws Exception{
		if (ftvo==null)  {
			ftvo= new FittingsVo();
			ftvo.setPage(ftService.findAllFittingsVosByPage(null));
		}else {
			ftvo.setPage(ftService.findAllFittingsVosByPage(ftvo.getPage()));
		}		
		return SUCCESS;
	}
	
	public String addFitting() throws Exception {
		return SUCCESS;
	}
	
	public String saveFitting() throws Exception {
		//saveFile(ftvo.getFile());
		String targetDirectory =ServletActionContext.getRequest().getRealPath("/upload");
		if (ftvo.getFile()!=null) {			
			String fileName = new Date().getTime()+ftvo.getFileFileName();
			CommonUtils.saveFile(ftvo.getFile(), targetDirectory,fileName );
			ftvo.setPhoto("/upload/"+fileName); 
		}
		
		if ((ftvo.getAction()!=null)&&(ftvo.getAction().equals(IAction.ACTION_EDIT))) {
			FittingsVo oldFittingVo = ftService.findFittingsVoById(ftvo.getId());
			if ((ftvo.getPhoto()==null)||(ftvo.getPhoto().equals(""))){
				ftvo.setPhoto(oldFittingVo.getPhoto());
            }
			 ftService.updateFittingsVo(ftvo);
		}else {		   
		      ftService.createFittings(ftvo);
		}
       
		return super.save();
	}
	
	public String initEdit() throws Exception {		
		ftvo=ftService.findFittingsVoById(ftvo.getId());
		return SUCCESS;
	}
	public String delFitting() throws Exception {
		ftService.deleteFittings(ftvo.getId());
		return SUCCESS;
	}
	public String viewFitting() throws Exception {
		ftvo=ftService.findFittingsVoById(ftvo.getId());
		return SUCCESS;
	}
}
