<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="jspheader.jsp" %>
<HTML>
 <HEAD> 
  <TITLE>sysadmin</TITLE>   
  <link href="../jscomponents/erp.css" type="text/css" rel="stylesheet" />
<script language="javascript">
        function check() {
	        var regex = /^[0-9]{1,6}$/;
	        var regexs = /^[^~!@#$%^&*·￥']{1,40}$/;
	        var regexss = /^[^~!@#$%^&*·￥']{0,100}$/;
	        var regexsss = /^[^~!@#$%^&*·￥']{0,255}$/;

		    if(document.forms[0]['func.nno'].value == ""){
			    alert("功能编码不能为空");
				return;
			}

		    if(document.forms[0]['func.nname'].value == ""){
			    alert("功能名称不能为空");
				return;
			}
		   
			if( !regex.test(document.forms[0]['func.nno'].value) ){
 			
  			 alert("功能编码只能为数字类型，请重新输入吧");
  			 document.forms[0]['func.nno'].value = "";
  			 return;
  			}
  			
			if( !regexs.test(document.forms[0]['func.nname'].value) ){
 			
  			 alert("功能名称有非法字符，请重新输入吧");
  			 document.forms[0]['func.nname'].value = "";
  			 return;
  			}
  			
			if( !regexss.test(document.forms[0]['func.addr'].value) ){
 			
  			 alert("功能入口地址有非法字符，请重新输入吧");
  			 document.forms[0]['func.addr'].value = "";
  			 return;
  			}
  			
			if( !regexsss.test(document.forms[0]['func.descr'].value) ){
  			 alert("功能描述有非法字符，请重新输入吧");
  			 document.forms[0]['func.descr'].value = "";
  			 return;
  			}

			document.forms[0].submit();
    	}
 </script>
<body class="managementtooledit" >
<div class="fullymarginalized">
<form method="POST" action="saveopt.action">
	<div class="EditDivBody">
		<table cellpadding="2" cellspacing="2" border="0" class="EditTableBorder" width="70%">
		     <input type="hidden" name="optVo.funcId" value="${optVo.funcId}"/> 
		     <tr>
				<td class="EditHead">结点：</td>
				<td class="EditRow">${optVo.funcname}</td>
			 </tr>	
		     <tr>
				 <td class="EditHead">操作名称：</td>
				 <td class="EditRow">
		         	<input type="text" class="text" name="optVo.name" size="18"/>
		         </td>
			 </tr>
			 <tr>
				 <td class="EditHead">操作方法：</td>
				 <td class="EditRow">
		         	<input type="text" class="text" name="optVo.optmethod" size="18"/>
		         </td>
			 </tr>
			 <tr>
				 <td class="EditHead">功能描述：</td>
				 <td class="EditRow">
		         	<input type="text" class="text" name="optVo.descr" size="18"/>
		         </td>
			 </tr>
			 <tr>
				<td colspan="2" class="EditHead">
					<input type="submit" value="创建" width="50" height="25" border="0">		
				</td>
			 </tr>
		</table>
	</div>
</form>
</div>

</body>
</html>