<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<HTML>
<HEAD>
<TITLE>文章列表</TITLE>
<link href="../jscomponents/erp.css" type="text/css" rel="stylesheet" />

<script type="text/javascript" language="JavaScript">
  	// 分页函数
  	function requestPage(curpage) {
  		var total_Page = document.getElementById("total_Page").value;
   	   	if (curpage <= 0 || curpage > total_Page) {
   	       alert("页数输入不合法");
   	       return;
   	   	}
		document.getElementById("currentPage").value = curpage;
	   	window.location.href= 'showArticleList.action?pagi.currentPage=' + curpage;
    }	 
  	
  	// 文章详情
  	function showArticleDetail(articleId){
  		window.location.href= 'showArticleDetail.action?article.id='+ articleId;
  	}
  	
  	// 删除文章
  	function deleteArticle(articleId){
	         window.location.href= 'deleteArticle.action?article.id='+articleId;
  	}
  	
    // 修改文章
  	function updateArticle(articleId){
	         window.location.href= 'showUpdateArticle.action?article.id='+articleId;
  	}
  	
  </script>

</HEAD>

<BODY>

<div class="ToolBar"><span class="ToolBarButton"> <a
	class="Btn_Add" id="Btn_Add"
	onmouseover='javaScript:this.className="Btn_Add_Over"'
	onmouseout='javaScript:this.className="Btn_Add"'
	href="addArticle.action"></a> </span></div>

<div class="SheetBody" style="text-align: center; width: 70%;">


<table class="GridBorder" cellspacing="1px" style="width: 100%;">
	<tr>
		<td class="GridHeader">选择</td>
		<td class="GridHeader">序号</td>
		<td class="GridHeader">最后修改时间</td>
		<td class="GridHeader">标题</td>
		<td class="GridHeader">操作</td>
	</tr>

	<c:if test="${not empty pagi.list }">
		<s:iterator value="pagi.list" status="article">
			<tr id="<s:property value="id" />"
				<c:if test="${article.count%2!=0}">class="GridBodyA"</c:if>
				<c:if test="${article.count%2==0}">class="GridBodyB"</c:if>
				onmouseover="this.style.backgroundColor='#E8F2FC'"
				onmouseout="this.style.backgroundColor=''">
				<td width="10%" style="text-align: left; padding-left: 8px;"><input
					type="checkbox" value="${id}" /></td>
				<td width="10%" align="center"><s:property
					value='#article.count' /></td>
				<td width="15%" style="word-wrap: break-word; word-break: break-all"
					align="center"><s:property value="modifyDate" />&nbsp;</td>
				<td width="50%" style="word-wrap: break-word; word-break: break-all"
					align="center"><a
					href="javascript:showArticleDetail('<s:property value='id'/>')"><u><s:property
					value="title" /></u></a></td>
				<td width="15%" align="center"><a class="Btn_Change"
					id="Btn_Change"
					onmouseover='javaScript:this.className="Btn_Change_Over"'
					onmouseout='javaScript:this.className="Btn_Change"'
					onclick="javascript:updateArticle('<s:property value='id'/>');">
				</a> <a class="Btn_Delete" id="Btn_Delete"
					onmouseover='javaScript:this.className="Btn_Delete_Over"'
					onmouseout='javaScript:this.className="Btn_Delete"'
					onclick="javascript:deleteArticle('<s:property value='id'/>');">
				</a></td>
			</tr>
		</s:iterator>
	</c:if>

	<c:if test="${pagi.list != null && empty pagi.list }">
		<tr>
			<td style="color: red;" colspan="6">对不起，系统暂时没有符合条件的数据记录！</td>
		</tr>
	</c:if>




</table>
</div>

<!-- 分页 -->
<div class='Pager' style="float: left; padding-left: 8px;"><input
	type="hidden" name="currentPage" id="currentPage"
	value="${pagi.currentPage}" /> <input type="hidden" name="total_Page"
	id="total_Page" value="${pagi.totalPage}" />
<table>
	<tr>
		<td>总记录&nbsp;<span id='Pagination1_lbTotalCount'>${pagi.totalCount}</span>&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp;
		当前第&nbsp;<span id='Pagination1_lbpageCurrent'>${pagi.currentPage}/${pagi.totalPage}</span>&nbsp;页&nbsp;&nbsp;
		<a id='Pagination1_lbtnFirst'
			<c:choose>
		                    <c:when test="${pagi.firstPage==true}"> 
		                    	disabled='disabled'
		                    </c:when>   
		                    <c:otherwise>
		                        href="javascript:requestPage(1)"
		                    </c:otherwise>  
	                 	</c:choose>>首
		页</a>&nbsp;&nbsp; <a id='Pagination1_lbtnPre'
			<c:choose>
	                    	<c:when test="${pagi.hasPreviousPage==true}"> 
	                        	href="javascript:requestPage(${pagi.currentPage}-1)"
	                    	</c:when>   
	                    	<c:otherwise>                        
	                        	disabled='disabled'
	                        </c:otherwise>  
	                   </c:choose>>上一页</a>&nbsp;&nbsp;

		<a id='Pagination1_lbtnNext'
			<c:choose>
	                      <c:when test="${pagi.hasNextPage==true}">
	                           href="javascript:requestPage(${pagi.currentPage}+1)"
	                      </c:when>   
	                      <c:otherwise>                        
	                        disabled='disabled'
	                      </c:otherwise>  
	                   </c:choose>>下一页</a>&nbsp;&nbsp;

		<a id='Pagination1_lbtnFinally'
			<c:choose>
	                      <c:when test="${pagi.lastPage==true}"> 
	                          disabled='disabled'
	                      </c:when>   
	                      <c:otherwise>                       
	                        href="javascript:requestPage(${pagi.totalPage})"
	                      </c:otherwise>  
	                   </c:choose>>尾
		页</a>&nbsp;&nbsp; 第&nbsp;<input name='nPage' type='text' maxlength='5'
			id='nPage'
			style='height: 17px; border-color: #D5E0E6; border-width: 1px; border-style: Solid; width: 25px;'>&nbsp;页
		</td>
		<td><a id="Pagination_btnGo" class="btnGo" href="#"
			onclick="javascript:requestPage(document.getElementById('nPage').value)"></a>
		</td>
	</tr>
</table>
</div>

</BODY>
</HTML>