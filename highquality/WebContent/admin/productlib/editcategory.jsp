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

<form method="POST" id="editpcform" name="editpcform" action="savecategory.action" >
	<input type="hidden" name="productClassifyVo.action" value="edit_action"/> 	
    <input type="hidden"  name="productClassifyVo.id" id="productClassifyVo.id"  value="${productClassifyVo.id}"  /> 
   <table class="managementtooledit" cellpadding="2" cellspacing="2" border="0" width="100%" >     
    <tr>
		<td >
			父结点:&nbsp;&nbsp;${parCategoryName.name}<br/>
			<input type="hidden" name="productClassifyVo.parCategoryNo" value="${productClassifyVo.parCategoryNo}"/>                      
  		</td>
	</tr>  	
	<tr>
		<td>
                       类别名称  	
         <br/>
         <input type="text" class="text" id="productClassifyVo.name" name="productClassifyVo.name" value="${productClassifyVo.name}" size="18"/>
    </td>
    <tr>
		<td>
                       类别编号  	
         <br/>
         ${productClassifyVo.categoryNo}
    </td>
	</tr>
	<input type="hidden"  name="productClassifyVo.categoryNo" id="productClassifyVo.categoryNo"  value="${productClassifyVo.categoryNo}"  /> 
	<tr>
		<td width="400">&nbsp;</td>
	</tr>
	<tr>
		<td>
			<input type="button" value="修改" onclick="javascript:submit('editpcform');"　width="50" height="25" border="0">
			
		</td>
	</tr>
</table>
</form>
</body>
</html>