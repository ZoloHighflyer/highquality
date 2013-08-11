package com.bluecreation.erp.productmgt.vo;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.nci.cp.core.model.BaseVo;

public class ProductVo extends BaseVo {
	protected long   id;
	protected String name;                           //配件名称
	protected String specification;                  //配件规格
	protected String material;                       //配件材质
	protected String color;                          //配件颜色
	protected float  price;							 //配件价格
	protected String mark;							 //配件备注
    protected String designer;
    protected String cartographer;
    protected String assessor;
    protected String approved;
    protected Date   validdate;
    protected String validdatestr;
	protected String assemblephoto;   
    protected File   asbphotofile;
    protected String asbphotofileContentType;
	protected String asbphotofileFileName;
	protected String effectphoto;
    protected File   effectphotofile;
    protected String effectphotofileContentType;
	protected String effectphotofileFileName;
    protected String photo;
    protected File   photofile;
    protected String photofileContentType;
	protected String photofileFileName;
    protected List   partlist =new ArrayList();
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
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
		
		//format.format(validdate);
		
		
		return this.validdate;
	}
	public void setValiddate(Date validdate) {
		
		this.validdate = validdate;
	}
	
	
	public String getValiddatestr() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return (validdate!=null?format.format(validdate):"");
	}
	public String getAssemblephoto() {
		return assemblephoto;
	}
	public void setAssemblephoto(String assemblephoto) {
		this.assemblephoto = assemblephoto;
	}
	public File getAsbphotofile() {
		return asbphotofile;
	}
	public void setAsbphotofile(File asbphotofile) {
		this.asbphotofile = asbphotofile;
	}
	public String getAsbphotofileContentType() {
		return asbphotofileContentType;
	}
	public void setAsbphotofileContentType(String asbphotofileContentType) {
		this.asbphotofileContentType = asbphotofileContentType;
	}
	public String getAsbphotofileFileName() {
		return asbphotofileFileName;
	}
	public void setAsbphotofileFileName(String asbphotofileFileName) {
		this.asbphotofileFileName = asbphotofileFileName;
	}
	
	public String getEffectphoto() {
		return effectphoto;
	}
	public void setEffectphoto(String effectphoto) {
		this.effectphoto = effectphoto;
	}
	public File getEffectphotofile() {
		return effectphotofile;
	}
	public void setEffectphotofile(File effectphotofile) {
		this.effectphotofile = effectphotofile;
	}
	
	public String getEffectphotofileContentType() {
		return effectphotofileContentType;
	}
	public void setEffectphotofileContentType(String effectphotofileContentType) {
		this.effectphotofileContentType = effectphotofileContentType;
	}
	public String getEffectphotofileFileName() {
		return effectphotofileFileName;
	}
	public void setEffectphotofileFileName(String effectphotofileFileName) {
		this.effectphotofileFileName = effectphotofileFileName;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public File getPhotofile() {
		return photofile;
	}
	public void setPhotofile(File photofile) {
		this.photofile = photofile;
	}
	public String getPhotofileContentType() {
		return photofileContentType;
	}
	public void setPhotofileContentType(String photofileContentType) {
		this.photofileContentType = photofileContentType;
	}
	public String getPhotofileFileName() {
		return photofileFileName;
	}
	public void setPhotofileFileName(String photofileFileName) {
		this.photofileFileName = photofileFileName;
	}
	public List getPartlist() {
		return partlist;
	}
	public void setPartlist(List partlist) {
		this.partlist = partlist;
	}
	
	
    
}
