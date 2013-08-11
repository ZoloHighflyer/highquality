<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="jspheader.jsp" %>
<%@ include file="../../envvar.jsp" %>
<HTML>
<HEAD> 
  <TITLE>sysadmin</TITLE>
  <link href="<%=ROOTPATH%>/jscomponents/erp.css" type="text/css" rel="stylesheet" />
  
  <script language="javascript">
        function check() {
	        var regex = /^[0-9]{1,6}$/;
	        var regexs = /^[^~!@#$%^&*·￥']{1,40}$/;
	        var regexss = /^[^~!@#$%^&*·￥']{0,100}$/;
	        var regexsss = /^[^~!@#$%^&*·￥']{0,255}$/;

		    if(document.forms[0]['userVo.userid'].value == ""){
			    alert("用户登录名不能为空");
				return;
			}

		    if(document.forms[0]['userVo.username'].value == ""){
			    alert("姓名不能为空");
				return;
			}
		    
		    if(document.forms[0]['userVo.password'].value == ""){
			    alert("密码不能为空");
				return;
			}
			
			if(document.forms[0]['userVo.cpassword'].value == ""){
			    alert("确认密码不能为空");
				return;
			}
			if(document.forms[0]['userVo.password'].value !=document.forms[0]['userVo.cpassword'].value ) {
			   alert("密码与确认密码必须要一致！");
			   return;
			}
 			
 			document.forms[0].submit();
    	}
 </script>
</HEAD>
<BODY>
<div class="ToolBar">
   	<span class="ToolBarButton">                  
   		<a class="Btn_Save" id="Btn_Save" onmouseover='javaScript:this.className="Btn_Save_Over"' onmouseout='javaScript:this.className="Btn_Save"' onclick="javascript:check();"></a>            
   		<a class="Btn_Back" id="Btn_Back" onmouseover='javaScript:this.className="Btn_Back_Over"' onmouseout='javaScript:this.className="Btn_Back"' onclick="history.go(-1)"></a>        
   	</span>
</div> 
<form method="POST" action="saveuser.action">
<div class="EditDivBody">
	<table cellpadding="2" cellspacing="2" border="0" class="EditTableBorder"  width="40%"> 
	     <input type="hidden" name="userVo.action" value="edit_action"/>
	     <input type="hidden" name="userVo.id" value="${userVo.id}"/>
	    <caption>修改用户</caption>
		<tr>
			<td class="EditHead">用户标识：</td>
			<td class="EditRow">
				<input type="text" class="text" name="userVo.userid" value="${userVo.userid}" size="55"/>
			</td>
		</tr>
		<tr>
			<td class="EditHead">用户姓名：</td>
			<td class="EditRow">
			    <input type="text" class="text" name="userVo.username" value="${userVo.username}" size="55"/>							
			</td>
		</tr>	
		<tr>
			<td class="EditHead">密码：</td>  	
	        <td class="EditRow"> 
	        	<input type="password" class="text" name="userVo.password" value="${userVo.password}" size="57"/>
	    	</td>
	    <tr>
			<td class="EditHead">确认密码：</td>
			<td class="EditRow">  	
	         <input type="password" class="text" name="userVo.cpassword" value="${userVo.password}" size="57"/>
	    	</td>
		</tr>
		<!-- 
		<tr>
			<td class="EditRow" colspan="2">
				<input type="button" value="修改" width="50" onclick="javascript:check();" height="25" border="0">
			</td>
		</tr>
		 -->
	</table>
</div>
</form>
</BODY>
</HTML>