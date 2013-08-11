<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="jspheader.jsp" %>
<HTML>
<HEAD> 
	<link href="../jscomponents/erp.css" type="text/css" rel="stylesheet" />
  	<TITLE>sysadmin</TITLE>
</HEAD>
<BODY>
	<form method="POST" action="savedep.action">
		<div class="EditDivBody">
			<table cellspacing="1" border="0" class="EditTableBorder" width="70%"> 
				<input type="hidden" name="depVo.action" value="edit_action"/>
			    <input type="hidden" name="depVo.id" value="${depVo.id}"/>
			    <input type="hidden" name="depVo.pardepno" value="${depVo.pardepno}"/> 
			 	
				<tr>
					<td class="EditHead">部门名称：</td>
					<td class="EditRow">
						<input type="text" class="text" name="depVo.depname" value="${depVo.depname}" size="45"/>
					</td>
				</tr>
				<tr>
					<td class="EditHead">部门描述：</td>
					<td class="EditRow">
						<input type="text" class="text" name="depVo.descr" value="${depVo.descr}" size="45"/>							
					</td>
				</tr>		
				<tr>
					<td class="EditHead" colspan="2">
						<input type="submit" value="修改" width="50" height="25" border="0">
					</td>
				</tr>
			</table>
		</div>
	</form>
</BODY>
</HTML>