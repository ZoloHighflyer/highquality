<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="../jscomponents/erp.css" type="text/css" rel="stylesheet" />
		<title>修改文章</title>
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
						alert('aaa');
						self.sync();
						document.forms['addBlog'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						alert('bbb');
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
			请您输入您的文章内容
		</p>
		<p>
			&nbsp;
		</p>
		<form id="addBlog" name="addBlog" method="post" action="updateArticle.action">
		    <input name="article.id" value="${article.id}" type="hidden" id="article.id"/>
			<table width="629" border="0">
				<tr>
					<td width="10%">
						主题：
					</td>
					<td width="90%">
						<label>
							<input name="article.title" value="${article.title}" type="text" id="title" size="60" />
						</label>
					</td>
				</tr>
				
				<tr>
					<td width="10%">
						摘要：
					</td>
					<td width="90%">
						<label>
							<input name="article.brief" value="${article.brief}" type="text" id="title" size="60" />
						</label>
					</td>
				</tr>
				
				
				
				<tr>
					<td width="10%">
						内容：
					</td>
					<td width="90%">
						<label>
						   <textarea name="article.content" cols="100" rows="8" style="width:700px;height:200px;visibility:hidden;"><s:property value="article.content" escape="false"/></textarea>
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
			</table>
		</form>
		<p>
			&nbsp;
		</p>
	</body>
</html>
