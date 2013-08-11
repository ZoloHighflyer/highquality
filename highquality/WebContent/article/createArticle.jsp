<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
String htmlData = request.getParameter("article.content") != null ? request.getParameter("article.content") : "";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	    <link href="../jscomponents/erp.css" type="text/css" rel="stylesheet" />
		<title>写新文章</title>
		<link rel="stylesheet" href="../kindeditor/themes/default/default.css" />
		<link rel="stylesheet" href="../kindeditor/plugins/code/prettify.css" />
		<script charset="utf-8" src="../kindeditor/kindeditor.js"></script>
		<script charset="utf-8" src="../kindeditor/lang/zh_CN.js"></script>
		<script charset="utf-8" src="../kindeditor/plugins/code/prettify.js"></script>
		<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="article.content"]', {
				cssPath : '../kindeditor/plugins/code/prettify.css',
				uploadJson : '../kindeditor/jsp/upload_json.jsp',
				fileManagerJson : '../kindeditor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['addBlog'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['addBlog'].submit();
					});
				}
			});
			prettyPrint();
			
			
		});
	</script>
	</head>
	<body>
	    <div class="ToolBar">
		   	<span class="ToolBarButton">                  
		   		<a class="Btn_Back" id="Btn_Back" onmouseover='javaScript:this.className="Btn_Back_Over"' onmouseout='javaScript:this.className="Btn_Back"' onclick="history.go(-1)"></a>        
		   	</span>
		</div> 
		<p>
			请您输入您的博文内容
		</p>
		<p>
			&nbsp;
		</p>
		<form id="addBlog" name="addBlog" method="post" action="saveArticle.action" enctype="multipart/form-data">
			<table width="629" border="0">
				<tr>
					<td width="76">
						标题：
					</td>
					<td width="543">
						<label>
							<input name="article.title" type="text" id="title" size="60" />
						</label>
					</td>
				</tr>
				
				<tr>
					<td width="100">
						标题图片：
					</td>
					<td width="543">
						<label>
							<input name="image" type="file" id="image" size="60" />
						</label>
					</td>
				</tr>
				
				
				<tr>
					<td width="76">
						摘要：
					</td>
					<td width="543">
						<label>
							<input name="article.brief" type="text" id="brief" size="60" />
						</label>
					</td>
				</tr>
				
				<tr>
					<td>
						KindEditor内容：
					</td>
					<td>
						<label>
						   <textarea name="article.content" cols="100" rows="8" style="width:700px;height:200px;visibility:hidden;"><%=htmlspecialchars(htmlData)%></textarea>
						</label>
					</td>
				</tr>
				<tr>
					<td width="50%">
						<label>
							<input type="reset" name="button" id="button" value="重置" />
						</label>
					</td>
					<td width="50%"> 
						<label>
							<input type="submit" name="button2" id="button2"  value="提交" />
						</label>
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
			</table>
		</form>
		<p>
			&nbsp;
		</p>
	</body>
</html>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>
