package com.nci.cp.web.uicomponent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-2-3 
 */
public class InputComponent extends AbstractUIComponent {
	private static Log log = LogFactory.getLog(InputComponent.class);
    protected String id=null;
    protected String name=null;
    protected String type=null;
    protected String value=null;
    protected String size=null;
    protected String label=null;
    
    public InputComponent() {
    	super();
    }
	public InputComponent(String type,String id, String name,String value, String size) {
		super();
		this.id = id;
		this.name = name;
		this.size = size;
		this.type = type;
		this.value=value;
	}
    
	
	public InputComponent(String label,String type,String id, String name,String value, String size) {
		super();
		this.id = id;
		this.label = label;
		this.name = name;
		this.size = size;
		this.type = type;
		this.value = value;
		
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
    
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String trieveContent() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String trieveTag() {
		// TODO Auto-generated method stub
		return "input";
	}

	@Override
	protected String getBeginTag() {
		StringBuffer sb = new StringBuffer(); 
		if (label!=null) {
			sb.append("\n "+label+":<input  type=\""+this.type+"\" ");
		}else {
			sb.append("\n  <input  type=\""+this.type+"\" ");
		}
		
		if (this.id!=null) 
		   sb.append(" id=\""+this.id+"\" ");
		if (this.name!=null)
		   sb.append("name=\""+this.name+"\" ");
		if (this.value!=null)
			sb.append("value=\""+this.value+"\" ");
		if (this.size!=null)
		   sb.append(" size=\""+this.size+"\"");
		sb.append(">");
		
		
		return sb.toString();
	}
    
	
}
