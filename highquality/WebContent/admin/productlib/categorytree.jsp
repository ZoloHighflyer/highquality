<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../sysmgt/jspheader.jsp" %>
<HTML>
 <HEAD> 
  <TITLE>sysadmin</TITLE>
  <link href="${pageContext.request.contextPath}/jscomponents/erp.css" type="text/css" rel="stylesheet" />
  <link rel="STYLESHEET" type="text/css" href="${pageContext.request.contextPath}/jscomponents/dhtmlxTree/codebase/dhtmlxtree.css">
	
	<script  src="${pageContext.request.contextPath}/jscomponents/dhtmlxTree/codebase/dhtmlxcommon.js"></script>
	<script  src="${pageContext.request.contextPath}/jscomponents/dhtmlxTree/codebase/dhtmlxtree.js"></script>
	<script  src="${pageContext.request.contextPath}/jscomponents/dhtmlxTree/codebase/ext/dhtmlxtree_start.js"></script>
  
</HEAD>
<body  topmargin="0" leftmargin="0" cellpadding="0" cellspacing="0" >
<div class="ToolBar">
        <span class="ToolBarButton">                  
                <a class="Btn_Select" id="Btn_Select" onmouseover='javaScript:this.className="Btn_Select_Over"' onmouseout='javaScript:this.className="Btn_Select"' onclick="javascript:selectopt();"></a>            
           		<a class="Btn_Close" id="Btn_Close" onmouseover='javaScript:this.className="Btn_Close_Over"' onmouseout='javaScript:this.className="Btn_Close"' onclick="cancle();"></a>        
        </span>
</div> 
    <table  leftmargin="50" border="0" cellspacing="0" cellpadding="0" width="100%">
	<tr> 	 
	 <td width="30%" valign="top">
	    <div id="treeBox" style="width:400;height:300"></div>
        <script>
	    treeview=new dhtmlXTreeObject(document.getElementById('treeBox'),"100%","100%",0);
	    treeview.enableCheckBoxes(false);
	    treeview.enableDragAndDrop(true);

	    <s:iterator value="#request.treeList" id="node" >
	         <c:choose>
		       <c:when test="${node.parCategoryNo!=-1}">
	   		     treeview.insertNewItem('<s:property value="#node.parCategoryNo"/>','<s:property value="#node.id"/>','<s:property value="#node.name"/>');
		       </c:when>
		       <c:otherwise>
	    	       treeview.insertNewItem(0,'<s:property value="#node.id"/>','<s:property value="#node.name"/>');
		       </c:otherwise>
	         </c:choose>
             
        </s:iterator> 
         findNodePath = function(id) {
            var curid=id;
	       // var path=treeview.getItemText(curid);
	       var path='';
	       var first=true;
	        while(treeview.getParentId(curid)!=0) {
	           if (first) {
	             //if the node is first node , the path is the nodename;
	             path=treeview.getItemText(curid);
	             first=false;
	           }else{
	              //if the node is not the first node,the path use pre-path + current node name
	              path=treeview.getItemText(curid)+"\\"+path;
	           }
		        
		        curid=treeview.getParentId(curid);	
	        }
	        
	        return "\\"+path;
         }
         function selectopt() {                 
	       		var a = new Array(2);
	       		a[0]= treeview.getSelectedItemId();
	       		a[1]=findNodePath(treeview.getSelectedItemId());
	       		window.returnValue=a;
	       		//window.opener.setSelect(curNode,curNode);
	       	    window.close();
	     }
	     function cancle() {
	       	    window.close();
	     }
   
         </script>

	 </td> 
	</tr>
</table>

</body>
</HTML>