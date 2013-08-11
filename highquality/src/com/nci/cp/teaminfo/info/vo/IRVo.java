package com.nci.cp.teaminfo.info.vo;

import java.util.ArrayList;
import java.util.List;

import com.nci.cp.teaminfo.info.model.InfoRecord;
/**
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-5-11 
 **/
public class IRVo {
    private List sel = new ArrayList();
    private  InfoRecord ifr = new InfoRecord();
	public List getSel() {
		return sel;
	}
	public void setSel(List sel) {
		this.sel = sel;
	}
	public InfoRecord getIfr() {
		return ifr;
	}
	public void setIfr(InfoRecord ifr) {
		this.ifr = ifr;
	}
    
    
}
