package com.nci.cp.web.uicomponent;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nci.cp.web.uicomponent.model.SelectItem;

/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-5-10
 */
public class SelectComponent extends AbstractUIComponent {
	private static Log log = LogFactory.getLog(SelectComponent.class);
	private static String tag="selectcomponent";
	protected List selectlist = new ArrayList();
	
	
	public SelectComponent( String label,String id, String name,String cssstyle,
			List<SelectItem> selectlist) {
		super();
		this.cssstyle = cssstyle;
		this.id = id;
		this.name = name;
		this.label = label;
		this.selectlist = selectlist;
	}

	@Override
	public String trieveContent() {
		StringBuffer sb = new StringBuffer();
	
		if ((selectlist!=null)&&(selectlist.size()>0) ){		
			for (int i=0;i<selectlist.size();i++) {
				SelectItem si = (SelectItem)selectlist.get(i);
				sb.append("<option value=\""+si.getKey()+"\"");
				if (si.isSelected()) sb.append(" selected");
				sb.append(">");
				sb.append(si.getValue());
				sb.append("</option>");
			}
			
		}else {
			log.info("============"+this.getClass().getName()+": selectlist is null!");
		}
		return sb.toString();
	}
    
	@Override
	protected String getBeginTag() {
		StringBuffer sb = new StringBuffer(); 
		if (label!=null) {
			sb.append("\n "+label+": <"+trieveTag()+" ");
		}else {
			sb.append("\n  <"+trieveTag());
		}
		
		if (this.id!=null) 
		   sb.append(" id=\""+this.id+"\" ");
		if (this.name!=null)
		   sb.append(" name=\""+this.name+"\" ");
		
		sb.append(">");
		
		
		return sb.toString();
	}

	@Override
	public String trieveTag() {
		// TODO Auto-generated method stub
		return "select";
	}

}
