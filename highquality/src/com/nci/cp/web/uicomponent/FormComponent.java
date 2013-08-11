package com.nci.cp.web.uicomponent;

/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-2-4 
 */
public class FormComponent extends AbstractUIComponent {
    protected static String COMPONENT_NAME="form";
    protected String id="";
    protected String name="";
    protected String action="";
    protected String method="";
    protected String content="";
	
	public FormComponent(String action, String id, String method, String name,String content) {
		super();
		this.action = action;
		this.id = id;
		this.method = method;
		this.name = name;
		this.content=content;
	}

	public FormComponent() {
		super();
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

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
    
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String trieveContent() {	
		
		return this.content;
	}

	@Override
	public String trieveTag() {
		
		return COMPONENT_NAME;
	}

	@Override
	protected String getBeginTag() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n <"+this.COMPONENT_NAME);
		if (this.id!=null)
		  sb.append(" id=\""+this.id+"\"");
		if (this.name!=null)
		  sb.append(" name=\""+this.name+"\"");
		if (this.method!=null) {
		  sb.append(" method=\""+this.method+"\"");
		}else{
			sb.append(" method=\"post\"");
		}
		if (this.action!=null)
		  sb.append(" action=\""+this.action+"\"");
		sb.append(">");		
	
		return sb.toString();
	}
	

}
