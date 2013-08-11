package com.nci.cp.web.uicomponent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-6-9
 */
public class EditableComponent extends AbstractUIComponent {
	private static Log log = LogFactory.getLog(EditableComponent.class);
	private static String tag="editablecomponent";
	protected String  action="";
	protected String  _ITEMNAME="data.itemname";
	protected String  _ACTIONNAME="data.actionname";
	
	public EditableComponent(String id, String name, String label,
			String cssstyle,String action,String itemname,String actionname) {
		super(id, name, label, cssstyle);
		this.action=action;
		_ITEMNAME=itemname;
		_ACTIONNAME=actionname;
	}

	@Override
	public String trieveContent() {
		StringBuffer sb = new StringBuffer();
	    
		sb.append("\n<script language=\"javascript\"> \n"+
		  " function addRow(tbid) { \n "+
		  "   var tb = document.getElementById(tbid);\n"+
		  "   var tr = tb.insertRow(tb.rows.length);\n "+
		  "   td0 = tr.insertCell(0);               \n "+
		  "   td0.innerHTML=\'<input type=\"text\" name=\\\""+_ITEMNAME+"\\\"  />\'; \n"+
		  "   td1=tr.insertCell(1); \n"+
		  "   td1.innerHTML=\"<input type=\\\"text\\\" name=\\\""+_ACTIONNAME+"\\\"  />\"; \n"+
		  "   td2=tr.insertCell(2);                                         \n  "+
		  "   td2.innerHTML=\"<input type=\\\"button\\\" value=\\\"删除\\\" onclick=\\\"delRow('bc_table')\\\" /><input type=\\\"button\\\" value=\\\"上移\\\" onclick=\\\"upRow('bc_table')\\\" /><input type=\\\"button\\\" value=\\\"下移\\\" onclick=\\\"downRow('bc_table')\\\" />\"; \n"+
		  " } \n "+
		  " function delRow(tbid) { \n "+
		  "      var tb= document.getElementById(tbid); \n "+
		  "     	tb.deleteRow(event.srcElement.parentElement.parentElement.rowIndex); \n"+
		  " }\n "+
		  "function upRow(tbid) { \n "+
		  "      var tb= document.getElementById(tbid);\n "+
		  "      var currow = event.srcElement.parentElement.parentElement.rowIndex;\n "+
		  "		  if (currow>1)		  {\n "+
		  "		     tb.moveRow(currow,currow-1); \n"+
		  "	  }\n "+				  
		  " } \n  "+
		  "function downRow(tbid) {\n "+
		  "      var tb= document.getElementById(tbid);\n "+
		  "		var currow = event.srcElement.parentElement.parentElement.rowIndex; \n"+
		  "		if ((currow+1)<tb.rows.length)	{  \n"+
		  "	       tb.moveRow(currow,currow+1); \n"+
		  "		}\n"+				 
		  " } \n "+
		  "</script>\n "+	
		  "<form action=\""+this.action+"\"> \n"+
		  "<table border=\"0\"> \n"+
		  "  <tr><td> \n"+
		  "   <input type=\"button\"  value=\"增加行\" onclick=\"addRow('bc_table')\" /><input type=\"submit\"  value=\"保存\"  /> \n"+
		  "	</td></tr> \n"+
		  "	<tr><td> \n"+
		  "    <table id=\"bc_table\" name=\"bc_table\" border=\"1\">\n"+
		  "      <tr>\n"+
		  "       <td width=\"150px\">操作项名</td><td>操作</td><td>选择</td>\n"+
		  "      </tr>\n"+
		  "    </table>\n"+
		  "	</td></tr>\n"+
		  "</table>\n"+
		  "</form>\n");
		
		
		
		return sb.toString();
	}
	
	@Override
	public String trieveTag() {
		// TODO Auto-generated method stub
		return "table";
	}

	
	protected String addPropertyToBeginTage() {
	
		return "border=\"0\"";
	}
    
}
