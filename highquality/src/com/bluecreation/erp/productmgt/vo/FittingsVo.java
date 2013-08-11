package com.bluecreation.erp.productmgt.vo;

import java.io.File;

import com.nci.cp.core.model.BaseVo;
/**
 * Common Platform Team * 
 * @author Oliver Chan
 * @since 0.1
 */
public class FittingsVo extends BaseVo {
	protected long   id;
	protected String name;                           //配件名称
	protected String specification;                  //配件规格
	protected String material;                       //配件材质
	protected String color;                          //配件颜色
	protected float  price;							 //配件价格
	protected String mark;							 //配件备注
	
	protected String photo;							 //配件照片的地址
	protected File   file;
	protected String fileContentType;
	protected String fileFileName;
	
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
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	
}
