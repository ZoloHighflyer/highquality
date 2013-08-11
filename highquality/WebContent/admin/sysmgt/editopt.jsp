<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="jspheader.jsp" %>
<HTML>
<HEAD> 
  <TITLE>sysadmin</TITLE>
  <link href="../jscomponents/erp.css" type="text/css" rel="stylesheet" />
</HEAD>
<BODY>
<form method="POST" action="saveopt.action">
	<div class="EditDivBody">
		<table cellpadding="2" cellspacing="2" border="0" class="EditTableBorder" width="70%">
			<input type="hidden" name="optVo.action" value="edit_action"/> 
		    <input type="hidden" name="optVo.id" value="${optVo.id}"/>
		    <input type="hidden" name="optVo.funcId" value="${optVo.funcId}"/> 
		 	
			<tr>
				<td class="EditHead">结点：</td>
				<td class="EditRow">${optVo.funcname}</td>
			</tr>	
		     <tr>
				 <td class="EditHead">操作名称:</td>
				 <td class="EditRow">
		         	<input type="text" class="text" name="optVo.name"  value="${optVo.name}" size="18"/>
		         </td>
			 </tr>
			 <tr>
				 <td class="EditHead">操作方法：</td>
				 <td class="EditRow">
		         	<input type="text" class="text" name="optVo.optmethod" value="${optVo.optmethod}" size="18"/>
		         </td>
			 </tr>
			 <tr>
				 <td class="EditHead">功能描述：</td>
				 <td class="EditRow">
		         	<input type="text" class="text" name="optVo.descr" value="${optVo.descr}" size="18"/>
		         </td>
			 </tr>	
			<tr>
				<td colspan="2" class="EditHead">
					<input type="submit" value="修改" width="50" height="25" border="0">
				</td>
			</tr>
		</table>
	</div>
</form>
</BODY>
</HTML>