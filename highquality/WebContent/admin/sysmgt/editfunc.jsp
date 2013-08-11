<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="jspheader.jsp" %>
<%@ include file="../../envvar.jsp" %>
<HTML>
<HEAD> 
  <TITLE>sysadmin</TITLE>
  <link href="<%=ROOTPATH%>/jscomponents/erp.css" type="text/css" rel="stylesheet"/>
</HEAD>
<BODY>
<form method="POST" action="savefunc.action">
    <div class="EditDivBody">
		<table cellspacing="1" border="0" class="EditTableBorder" width="70%"> 
		    <input type="hidden" name="funcVo.action" value="edit_action"/>
		    <input type="hidden" name="funcVo.id" value="${funcVo.id}"/>
		    <input type="hidden" name="funcVo.parfuncno" value="${funcVo.parfuncno}"/> 
		 	
			<tr>
				<td class="EditHead">功能名称：</td>
				<td class="EditRow">
					<input type="text" class="text" name="funcVo.funcname" value="${funcVo.funcname}" size="18"/>
				</td>
			</tr>
			<tr>
				<td class="EditHead">功能地址：</td>  	
		        <td class="EditRow">
		        	<input type="text" class="text" name="funcVo.funcaction" value="${funcVo.funcaction}" size="18"/>
		    	</td>
			</tr>
			<tr>
				<td class="EditHead">描述：</td>
				<td class="EditRow">	
					<input type="text" class="text" name="funcVo.descr" value="${funcVo.descr}" size="18"/>							
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