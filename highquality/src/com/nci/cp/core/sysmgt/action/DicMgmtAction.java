package com.nci.cp.core.sysmgt.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nci.cp.core.sysmgt.service.IDicMgmtService;
import com.nci.cp.core.sysmgt.vo.DicEntryVo;
import com.nci.cp.core.sysmgt.vo.DicTypeVo;
import com.nci.cp.core.web.AbstractAction;
import com.nci.cp.core.web.IAction;

/**
 * @target  the class is  for dic action .
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-11-03 
 */
public class DicMgmtAction extends AbstractAction {

	private static Log log = LogFactory.getLog(DicMgmtAction.class);
	private IDicMgmtService dicMgmtService;
	protected DicTypeVo     dicTypeVo;
	protected DicEntryVo    dicEntryVo;

	public DicTypeVo getDicTypeVo() {
		return dicTypeVo;
	}

	public void setDicTypeVo(DicTypeVo dicTypeVo) {
		this.dicTypeVo = dicTypeVo;
	}

	public DicEntryVo getDicEntryVo() {
		return dicEntryVo;
	}

	public void setDicEntryVo(DicEntryVo dicEntryVo) {
		this.dicEntryVo = dicEntryVo;
	}

	public void setDicMgmtService(IDicMgmtService dicMgmtService) {
		this.dicMgmtService = dicMgmtService;
	}

	/**
	 * 获取字典列表
	 * @return
	 * @throws Exception
	 */
	public String dicTypeMgmt() throws Exception {
		if (dicTypeVo==null)  {
			dicTypeVo= new DicTypeVo();
			dicTypeVo.setPage(dicMgmtService.findAllDicTypesByPage(null));
		}else {
			dicTypeVo.setPage(dicMgmtService.findAllDicTypesByPage(dicTypeVo.getPage()));
		}
		return SUCCESS;
	}
	
	public String saveDicType()throws Exception {
		if ((dicTypeVo.getAction()!=null)&&(dicTypeVo.getAction().equals(IAction.ACTION_EDIT))) {          
			dicMgmtService.updateDicType(dicTypeVo);
		}else {			
			dicMgmtService.createDicType(dicTypeVo);
		}
		return SUCCESS;
	}
	
	public String editDicType() throws Exception {
		dicTypeVo = dicMgmtService.findDicTypeById(dicTypeVo.getId());
		return SUCCESS;
	}
	
	public String delDicType() throws Exception {
		dicMgmtService.deleteDicType(dicTypeVo.getId());
		return SUCCESS;
	}
	
	public String dicEntryMgmt() throws Exception { 
		dicTypeVo =dicMgmtService.findDicTypeById(dicTypeVo.getId()); 
		dicTypeVo.setPage(dicMgmtService.findDicEntrysByTypeWithPage(dicTypeVo.getPage(),dicTypeVo));		
		return SUCCESS;
	}
	
	public String addDicEntry() throws Exception {
		dicTypeVo = dicMgmtService.findDicTypeById(dicTypeVo.getId()); 	    
		return SUCCESS;
	}
	
	public String saveDicEntry()throws Exception {
		if ((dicEntryVo.getAction()!=null)&&(dicEntryVo.getAction().equals(IAction.ACTION_EDIT))) {          
			long id= dicMgmtService.updateDicEntry(dicEntryVo);
			dicEntryVo = dicMgmtService.findDicEntryById(id);
			dicTypeVo  = dicEntryVo.getDicTypeVo();
		}else {			
			long id=dicMgmtService.createDicEntry(dicEntryVo);
			dicEntryVo = dicMgmtService.findDicEntryById(id);
			dicTypeVo = dicEntryVo.getDicTypeVo();
		}
		return SUCCESS;
	}
	
	public String editDicEntry() throws Exception {
		dicEntryVo = dicMgmtService.findDicEntryById(dicEntryVo.getId());
		return SUCCESS;
	}
	
	public String delDicEntry() throws Exception {

		dicEntryVo = dicMgmtService.findDicEntryById(dicEntryVo.getId());	

		dicTypeVo = dicEntryVo.getDicTypeVo();	
		dicMgmtService.deleteDicEntry(dicEntryVo.getId());
		dicTypeVo=dicMgmtService.findDicTypeById(dicTypeVo.getId());
		return SUCCESS;
	}
}
