package com.nci.cp.web.uicomponent;

/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-2-2 
 */
public class LinkImportComponent extends AbstractUIComponent {
	
    protected String rel="";
	protected String href="";
	protected String type="";
	
	public LinkImportComponent() {
		super();
	}

	public LinkImportComponent(String href, String rel, String type) {
		super();
		if (href!=null)
		  this.href = href;
		
		if (rel!=null)
		   this.rel = rel;
		
		if (type!=null)
		   this.type = type;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String trieveContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String trieveTag() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getBeginTag() {
		String tag="\n <link rel=\""+rel+"\" href=\""+href+"\" type=\""+type+"\"/> \n";
		return tag;
	}

	

	@Override
	public String render() {
		// TODO Auto-generated method stub
		return getBeginTag();
	}
	

}
