package com.nci.cp.teaminfo.km.model;

import java.util.Set;
import java.util.TreeSet;

import com.nci.cp.core.model.AbstractEntity;

public class KnowledgeCategory extends AbstractEntity{

	protected Set infoRecords=new TreeSet() ;

	protected String owner;
	
	protected String name;
    
	protected long   parcategoryno;

	

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Set getInfoRecords() {
		return infoRecords;
	}

	public void setInfoRecords(Set infoRecords) {
		this.infoRecords = infoRecords;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getParcategoryno() {
		return parcategoryno;
	}

	public void setParcategoryno(long parcategoryno) {
		this.parcategoryno = parcategoryno;
	}

	 
	
	
}
