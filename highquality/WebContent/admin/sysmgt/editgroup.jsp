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
			    if(document.forms[0]['groupVo.name'].value == ""){
				    alert("组名称不能为空！");
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

<form method="POST" action="savegroup.action">
	<div class="EditDivBody">
		<table cellspacing="1" border="0" class="EditTableBorder" width="40%">
		    <input type="hidden" name="groupVo.action" value="edit_action"/>
		    <input type="hidden" name="groupVo.id" value="${groupVo.id}"/>
		    <caption>修改用户组</caption>
			<tr>
				<td class="EditHead">组名称：</td>  	
				<td class="EditRow">
		         	<input type="text" class="text" name="groupVo.name" value="${groupVo.name}" size="18"/>
		    	</td>
			</tr>
			<tr>
				<td class="EditHead">描述：</td>  	
				<td class="EditRow">
		        	<input type="text" class="text" name="groupVo.descr" value="${groupVo.descr}" size="18"/>
			    </td>
			</tr>
			
			<tr>
				<td class="EditHead">角色列表：</td>
				<td class="EditRow">
					<c:if test="${groupVo.roles!=null}">
				    <table cellpadding="0" " border="0"> 
				    <c:set var="order" value="1"/> 
		            <c:forEach items="${groupVo.roles}" var="roleVo" >
		                <tr>
		                	<td> 
		                		<input id="c${order}" name="groupVo.roles" type="checkbox" value="${roleVo.id}"
		                    		<c:choose>
				            			<c:when test="${roleVo.selected}">
					               			checked
					            		</c:when>
				          			</c:choose> 
				           		/>${roleVo.name}
		                	</td> 
		               </tr>
		            </c:forEach>
		            </table>
		            </c:if>
		        </td>
			</tr>
			<tr>
				<td class="EditHead">用户列表：</td>
				<td class="EditRow">
					<c:if test="${groupVo.users!=null}">
				    <table cellpadding="0" cellspacing="2" border="0"> 
				     	<c:set var="order" value="1"/> 
		                <c:forEach items="${groupVo.users}" var="userVo" >
		                <tr>
		                 	<td> 
		                 		<input id="c${order}" name="groupVo.users" type="checkbox" value="${userVo.id}"
		                    		<c:choose>
			            				<c:when test="${userVo.selected}">
				               				checked
				            			</c:when>
				            		</c:choose> 
				           		/>${userVo.username}
							</td> 
		                </tr>
		              	</c:forEach>
		            </table>
		          	</c:if>
		        </td>
			</tr>
			<!-- 
			<tr>
				<td colspan="2" class="EditHead">
					<input type="button" value="修改" width="50" onclick="javascript:check();" height="25" border="0">
				</td>
			</tr>
			 -->
		</table>
	</div>
</form>
</BODY>
</HTML>