<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../sysmgt/jspheader.jsp" %>
<HTML>
 <HEAD> 
  <TITLE>sysadmin</TITLE>
  <link rel="StyleSheet" href="${pageContext.request.contextPath}/jscomponents/dtree/dtree.css" type="text/css" />
  <script src="${pageContext.request.contextPath}/jscomponents/dtree/dtree.js"></script>  
  
  
</HEAD>
<body  topmargin="0">

<table width="100%" cellpadding="0" cellspacing="0" border="0">
<tr>
	<td valign="middle" width="100%" height="100%">
		<span style="height:22px;margin-left:15px;vertical-align:middle"><a href="javascript:addCategory();" >增加类别</a></span><img src="${pageContext.request.contextPath}/images/splitter.gif">
		<span style="height:22px;margin-left:5px;vertical-align:middle"><a href="javascript:updatecategory();">修改类别</a></span><img src="${pageContext.request.contextPath}/images/splitter.gif">
		<span style="height:22px;margin-left:5px;vertical-align:middle"><a href="javascript:delcategory();">删除类别</a></span><img src="${pageContext.request.contextPath}/images/splitter.gif">
		
	</td>
	
</tr>
</table>
    <table  leftmargin="50" height="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
	 <td width="5%">
		&nbsp;
	 </td>
	 <td width="30%" valign="top">
		<script type="text/javascript" language="JavaScript"><!--	     
             tree =  new dTree('tree','${pageContext.request.contextPath}/jscomponents/');//创建一个树对象 
             <s:iterator value="#request.treeList" id="node" >
             tree.add('<s:property value="#node.id"/>','<s:property value="#node.parCategoryNo"/>','<s:property value="#node.name"/>','#','','');
             </s:iterator>                        
                               
             document.write(tree);

            
             
              // alert(tree.aNodes[tree.selectedNode].id);
               function addCategory()      {
                   if((!tree.isEmpty())&&(tree.selectedNode!=null)){
                     frames["rightPanel"].location.href= 'addcategory.action?productClassifyVo.id='+tree.aNodes[tree.selectedNode].id;
  		           } else if(tree.isEmpty()) {
  		              frames["rightPanel"].location.href= 'addcategory.action?productClassifyVo.id=-1';
  		           }else {
  		            alert("请选择一个结点");
  		            return ;
  		         }	
    		     	            
  		     }	 
  		     
  		     function delcategory()      {
                 if(tree.selectedNode!=null){
                      //alert(tree.aNodes[tree.selectedNode].id);
  		            location.href= 'delcategory.action?productClassifyVo.id='+tree.aNodes[tree.selectedNode].id;
  		         }else {
  		            alert("请选择一个结点");
  		         }			    	            
  		     }	
  		   function updatecategory()      {
                   if(tree.selectedNode!=null){
                      //alert(tree.aNodes[tree.selectedNode].id);
  		            frames["rightPanel"].location.href= 'initeditcategory.action?productClassifyVo.id='+tree.aNodes[tree.selectedNode].id;
  		         }else {
  		            alert("请选择一个结点");
  		         }			            
  		   }
  		   
  		 
  		
  		    
            
        --></script>
	 </td>
	 <td valign="top" width="65%">	
		<iframe id="rightPanel" border="0" width="600" name="rightPanel" src="" height="400" scrolling="NO" frameborder="no"  >
		</iframe>		
	 </td>
	</tr>
</table>
<script>
	function openselectnode(nid){
      //  tree.openTo(tree.findNodeOrder(tree.findNodeParent(tree.findNodeById(nid))), true, false);
        tree.s(tree.findNodeOrder(tree.findNodeById(nid)));
    }
	        
	function init() {
 		if (tree.aNodes.length>0) {
  			tree.s(0);
  		}
	}
	    
	
</script>

<script type="text/javascript" language="JavaScript">
	<c:choose>
		<c:when test="${null!=msg}">
	   		openselectnode(${productClassifyVo.id});
		</c:when>
		<c:otherwise>
	    	init();
		</c:otherwise>
	</c:choose>
</script>
</body>
</HTML>