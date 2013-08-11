package com.nci.cp.web.uicomponent;

import java.util.ArrayList;
import java.util.List;

/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-2-22
 */
public class ListComponent extends AbstractUIComponent {
	private static String tag="listcomponent-list";
	private static String HEADER_TAG = "th";
	private static String COLUMM_TAG = "td";
	private List tableModel = new ArrayList();
	private List headerlist = new ArrayList();
	private String cssstyle =null;
	private String id       =null;
	private String name     =null;
	
	public ListComponent(String id,String name,String cssstyle,List headerlist,List tableModel) {
		super();
		this.cssstyle = cssstyle;
		this.tableModel = tableModel;
		this.headerlist = headerlist;
		this.id=id;
		this.name = name;
	}

	public String trieveContent() {
		StringBuffer sb = new StringBuffer();		
		//make the header html
		sb.append(makeRow(headerlist,HEADER_TAG,0));
		
		
		int rows = tableModel.size();
		for (int i=0;i<rows;i++) {
			List columndata = (List)tableModel.get(i);			
			sb.append(makeRow(columndata,COLUMM_TAG,i));			
		}		
		return sb.toString();
	}

	private String makeRow(List list,String tag,int order) {
		int cols = list.size();
		StringBuffer rowsb = new StringBuffer(); 
		rowsb.append("\n <tr>" );
		if (HEADER_TAG.equals(tag)) {
			rowsb.append("<th>选择</th>");
		}else if (COLUMM_TAG.equals(tag)) {
			rowsb.append("<td>");
			if (name==null) name="table";
			rowsb.append("<input  id=\"c"+name+order+"\" " +
					     "name=\""+this.name+"\""+
					     "type=\"checkbox\" value=\""+list.get(0)+"\"/>");
			rowsb.append("</td>");
		}
		for(int i=0;i<cols;i++) {
			if (COLUMM_TAG.equals(tag)&&(i==0)) continue;
			rowsb.append("\n <"+tag+">");
			  rowsb.append(list.get(i));				  
			rowsb.append("</"+tag+">");
		}
		rowsb.append("</tr>");	
		return rowsb.toString();
	}
    
	
	@Override
	protected String getBeginTag() {
		String hstr="<"+trieveTag();
		
		if (cssstyle!=null) {
			return hstr+" style=\""+cssstyle+"\" >";
		}
		hstr=hstr+">";
		return hstr;
	}

	@Override
	public String trieveTag() {
		return "table";
	}

}
