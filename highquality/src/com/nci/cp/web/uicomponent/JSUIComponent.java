package com.nci.cp.web.uicomponent;

/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-2-1 
 */
public class JSUIComponent extends AbstractUIComponent {
    private static String tag="jscomponent";
    public  static String TYPE_SCRIPT="TYPE_SCRIPT";
    public  static String TYPE_REL   ="TYPE_REL";
    protected String type=TYPE_SCRIPT;
    protected String jsContent=null;
    
	
	public JSUIComponent(String type,String jsContent) {
		  super();		
		  this.type=type;
		  this.jsContent = jsContent;		
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}	
    
	public String getJsContent() {
		return jsContent;
	}

	public void setJsContent(String jsContent) {
		this.jsContent = jsContent;
	}

	@Override
	public String trieveContent() {
		// TODO Auto-generated method stub
		return jsContent;
	}

	@Override
	public String trieveTag() {
		// TODO Auto-generated method stub
		return "script";
	}

	@Override
	protected String getBeginTag() {
		if (TYPE_REL.equals(this.type)){
			return "\n<script src=\""+this.jsContent+"\" ></script>";
		}else  {
			return "\n<script type="+"\"text/javascript\""+">";
		}
	}

	@Override
	protected String getEndTag() {
		if (type.equals(TYPE_REL))
			return "";
		
		return super.getEndTag();
	}

	@Override
	public String render() {
		if (TYPE_REL.equals(this.type)) {
			return getBeginTag();
		}
		
		return super.render();
	}
	
	

}
