package com.nci.cp.web.uicomponent;

/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-2-1 
 */
public abstract class AbstractUIComponent implements IUIComponent {
	
    public abstract String trieveTag();
    public abstract String trieveContent();
    protected String cssstyle =null;
    protected String id       =null;
    protected String name     =null;
    protected String label    =null;
    public AbstractUIComponent( String id, String name,
			String label,String cssstyle) {
		super();
		this.cssstyle = cssstyle;
		this.id = id;
		this.name = name;
		this.label = label;
	}
	
	public AbstractUIComponent() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCssstyle() {
		return cssstyle;
	}
	public void setCssstyle(String cssstyle) {
		this.cssstyle = cssstyle;
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
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String render() {
		StringBuffer sb = new StringBuffer();
		sb.append(getBeginTag());
		sb.append(trieveContent());
		sb.append(getEndTag());
		
		return sb.toString();
	}
    protected String getBeginTag() {
    	StringBuffer sbb = new StringBuffer();
    	sbb.append("\n <"+trieveTag());
    	if (id!=null) sbb.append(" id=\""+id+"\"");
    	if (name!=null) sbb.append(" name=\""+name+"\"");
    	if (cssstyle!=null) sbb.append("style=\""+cssstyle+"\"" );
    	sbb.append(" >");
    	return sbb.toString();
    }
    protected String getEndTag() {
    	return "</"+trieveTag()+">";
    }
}
