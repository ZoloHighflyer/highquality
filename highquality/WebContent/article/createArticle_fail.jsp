<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>创建新文章失败</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>
<body class="bg_upload_fail">
<table>
	<tr>
	   <td>
	       <br>
			&nbsp;创建新文章失败！<br>
			<font color="red">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="#request.typeError"/><br>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="#request.lengthError"/>
			</font>
			
	   </td>
	</tr>
	<tr>
		<td align="center">
			<input type="button" align="middle" class="btnbg"  value="返&nbsp;&nbsp;回" onclick="window.history.back();"/>
		</td>
	</tr>
</table>
</body>

</html>