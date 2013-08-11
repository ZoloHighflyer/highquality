package com.sunbow.cms.vo;

import java.util.ArrayList;
import java.util.List;

import com.nci.cp.core.model.BaseVo;

public class ViewPanelVo extends BaseVo {
    private String panelName="";
    private String identify="";
    private List   vos = new ArrayList();
	public String getPanelName() {
		return panelName;
	}
	public void setPanelName(String panelName) {
		this.panelName = panelName;
	}
	public String getIdentify() {
		return identify;
	}
	public void setIdentify(String identify) {
		this.identify = identify;
	}
	public List getVos() {
		return vos;
	}
	public void setVos(List vos) {
		this.vos = vos;
	}
    
}
