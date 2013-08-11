package com.nci.cp.web.uicomponent.factory;

import com.nci.cp.web.uicomponent.IUIComponent;
import com.nci.cp.web.uicomponent.LinkImportComponent;

public class LinkImportComponentFactory {
    public static IUIComponent generateComponent(String href, String rel, String type){
    	IUIComponent lic = new LinkImportComponent(href,rel,type);
    	return lic;
    }
}
