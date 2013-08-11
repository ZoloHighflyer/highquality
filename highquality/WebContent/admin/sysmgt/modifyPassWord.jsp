<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="jspheader.jsp" %>
<% 
	String basePath = request.getContextPath();
	System.out.println(basePath);
	String errorMessage = (String) request.getAttribute("errormessage");
%>
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
		        
				var oldPassWord = document.getElementById("userVo.password").value;
				var newPassWord = document.getElementById("newPassWord").value;
				var checkNewPassWord = document.getElementById("checkNewPassWord").value;
				if (oldPassWord == null || oldPassWord == "") {
					alert("请填写旧密码!");
					return;
				}
				if (newPassWord == null || newPassWord == "") {
					alert("请填写新密码!");
					return;
				}
				if (checkNewPassWord == null || checkNewPassWord == "") {
					alert("请帖写确认密码!");
					return;
				}
				if (newPassWord != checkNewPassWord) {
					alert("两次输入密码不一致!");
				}
				
  				document.forms[0].submit();
    	}
 </script>
</HEAD>
<BODY>
<div class="ToolBar" style="height:30px;border-bottom:solid 1px #cccccc; padding:4px; padding-left:10px;">
    <span class="ToolBarButton" style="padding:4px; padding-left:10px;">                  
       	<a class="Btn_Save" id="Btn_Save" onmouseover='javaScript:this.className="Btn_Save_Over"' onmouseout='javaScript:this.className="Btn_Save"' onclick="javascript:check();"></a>            
      	<!-- <a class="Btn_Back" id="Btn_Back" onmouseover='javaScript:this.className="Btn_Back_Over"' onmouseout='javaScript:this.className="Btn_Back"' onclick="history.go(-1)"></a> -->        
    </span>
</div>

<form method="POST" action="<%=basePath %>/sysmgt/modifyPassWord.action" >
	<div class="EditDivBody" style="text-align:center; padding:10px;" >
		<table cellspacing="1" border="0" class="EditTableBorder" width="30%">
		    <caption>修改密码</caption>
			<tr>
				<td class="EditHead">旧密码：</td>  	
				<td class="EditRow">
					<!-- 
		         	<input type="text" class="text" id="userVo.password" name="userVo.password" value="" size="38"/>
		         	 -->
		         	<s:textfield name="userVo.password" cssClass="text" theme="simple" size="38"></s:textfield>
		    	</td>
			</tr>
			<tr>
				<td class="EditHead">新密码：</td>  	
				<td class="EditRow">
					<!-- 
		        	<input type="text" class="text" id="newPassWord" name="newPassWord" value="" size="38"/>
		        	 -->
		        	<s:textfield name="newPassWord" cssClass="text" theme="simple" size="38"></s:textfield>
			    </td>
			</tr>
			<tr>
				<td class="EditHead">确认新密码：</td>  	
				<td class="EditRow">
					<!-- 
		        	<input type="text" class="text" id="checkNewPassWord" name="checkNewPassWord" value="" size="38"/>
		        	 -->
		        	<s:textfield name="checkNewPassWord" cssClass="text" theme="simple" size="38"></s:textfield>
			    </td>
			</tr>
		</table>
	</div>
	<%
     	if (errorMessage != null) {
     	%>
    			<h5 align="center"><font color="red"><%=errorMessage%></font></h5> 
    	<% 
    	}
    	%>
</form>
</BODY>
</HTML>