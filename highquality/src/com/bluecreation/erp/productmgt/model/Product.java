package com.bluecreation.erp.productmgt.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.nci.cp.core.model.AbstractEntity;
/**
 * The object is to storage fittings model.
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-10-29 
 */
public class Product extends CommonComponent {
							 //配件备注
    protected String designer;
    protected String cartographer;
    protected String assessor;
    protected String approved;
    protected Date   validdate;
    protected String assemblephoto;
    protected String effectphoto;
    protected String photo;
    protected Set    parts =new HashSet();
	

	public String getDesigner() {
		return designer;
	}
	public void setDesigner(String designer) {
		this.designer = designer;
	}
	public String getCartographer() {
		return cartographer;
	}
	public void setCartographer(String cartographer) {
		this.cartographer = cartographer;
	}
	public String getAssessor() {
		return assessor;
	}
	public void setAssessor(String assessor) {
		this.assessor = assessor;
	}
	public String getApproved() {
		return approved;
	}
	public void setApproved(String approved) {
		this.approved = approved;
	}
	public Date getValiddate() {
		return validdate;
	}
	public void setValiddate(Date validdate) {
		this.validdate = validdate;
	}
	public String getAssemblephoto() {
		return assemblephoto;
	}
	public void setAssemblephoto(String assemblephoto) {
		this.assemblephoto = assemblephoto;
	}
	
	public String getEffectphoto() {
		return effectphoto;
	}
	public void setEffectphoto(String effectphoto) {
		this.effectphoto = effectphoto;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Set getParts() {
		return parts;
	}
	public void setParts(Set parts) {
		this.parts = parts;
	}
    
}
