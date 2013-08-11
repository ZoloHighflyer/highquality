<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../sysmgt/jspheader.jsp" %>
<HTML>
 <HEAD> 
  <TITLE>sysadmin</TITLE>
  <link href="${pageContext.request.contextPath}/jscomponents/erp.css" type="text/css" rel="stylesheet" />
  <script language="JavaScript" src="${pageContext.request.contextPath}/jscomponents/validation-framework.js"></script>  
   <script language="javascript"> 
   ValidationFramework.init("validation-config.xml");
   function submit(formid)  {
       var submitForm = document.getElementById(formid);
       if (doValidate(formid))
                   submitForm.submit();        	   
	}   	
 </script>
</HEAD>
<body  topmargin="0">

<div class="ToolBar">
        <span class="ToolBarButton">                  
            	<a class="Btn_Save" id="Btn_Save" onmouseover='javaScript:this.className="Btn_Save_Over"' onmouseout='javaScript:this.className="Btn_Save"' onclick="javascript:submit('createpc');"></a>            
           		<a class="Btn_Back" id="Btn_Back" onmouseover='javaScript:this.className="Btn_Back_Over"' onmouseout='javaScript:this.className="Btn_Back"' onclick="history.go(-1)"></a>        
        </span>
</div> 
<form method="POST" id="createpc" name="createpc" action="savecategory.action" >
	
   <table class="managementtooledit" cellpadding="2" cellspacing="2" border="0" width="100%" >     
    <tr>
		<td >
			父结点:&nbsp;&nbsp;${productClassifyVo.name}<br/>
			<input type="hidden" name="productClassifyVo.parCategoryNo" value="${productClassifyVo.id}"/>                      
  		</td>
	</tr>  	
	<tr>
		<td>
                       类别名称  	
         <br/>
         <input type="text" class="text" id="productClassifyVo.name" name="productClassifyVo.name"  size="18"/>
    </td>
    <tr>
		<td>
                       类别编号  	
         <br/>
         <input type="text" class="text" id="productClassifyVo.categoryNo" name="productClassifyVo.categoryNo"  size="18"/>
    </td>
	</tr>
	
	
</table>
</form>
</body>
</html>