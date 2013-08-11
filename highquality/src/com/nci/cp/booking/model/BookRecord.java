package com.nci.cp.booking.model;

import com.nci.cp.core.model.AbstractEntity;
/**
 * The object is to storage booking record.
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-08-04 
 */
public class BookRecord extends AbstractEntity {
	private String name;
    private String menu;
    private Float  price;
    private Float  payed;
    private Float  balance;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Float getPayed() {
		return payed;
	}
	public void setPayed(Float payed) {
		this.payed = payed;
	}
	public Float getBalance() {
		return balance;
	}
	public void setBalance(Float balance) {
		this.balance = balance;
	}
    
}
