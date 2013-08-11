<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
request.setCharacterEncoding("UTF-8");
String htmlData = request.getParameter("article.content") != null ? request.getParameter("article.content") : "";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="../jscomponents/erp.css" type="text/css" rel="stylesheet" />
		<title>文章详情</title>
		<script>
	    
	    
	    </script>
	</head>
	<body>
		<div class="ToolBar">
		   	<span class="ToolBarButton">                  
		   		<a class="Btn_Back" id="Btn_Back" onmouseover='javaScript:this.className="Btn_Back_Over"' onmouseout='javaScript:this.className="Btn_Back"' onclick="history.go(-1)"></a>        
		   	</span>
		</div> 
		<p>
			您的文章的详细内容
		</p>
		<p>
			&nbsp;
		</p>
		<form id="articleDetail" name="articleDetail">
			<table width="629" border="0">
				<tr >
					<td width="10%">
						主题：
					</td>
					<td width="90%">
							${article.title}
					</td>
				</tr>
				
				<tr >
					<td width="10%">
						摘要：
					</td>
					<td width="90%">
							${article.brief}
					</td>
				</tr>
				
				<tr>
					<td width="10%"> 
						内容：
					</td>
					<!--<td>
						<label>
						   <textarea name="article.content"  cols="100" rows="8" style="width:700px;height:200px;visibility:hidden;"></textarea>
						</label>
					</td>
					-->
					<td width="90%" style="width:700px;height:200px;"><s:property value="article.content" escape="false"/></td>
				</tr>
			</table>
		</form>
		<p>
			&nbsp;
		</p>
	</body>
</html>
