<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String ctx = request.getContextPath();
	pageContext.setAttribute("ctx", ctx);

	String scheme = request.getScheme();
	String serverIp = request.getServerName();
	int serverPort = request.getServerPort();
	String path = scheme + "://" + serverIp + ":" + serverPort + ctx;
	pageContext.setAttribute("basePath", path);
%>
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
	   	window.location.href= 'showArticleIndex.action?pagi.currentPage=' + curpage;
    }	 
  	
  	// 文章详情
  	function showArticleDetail(articleId){
  		window.location.href= 'showArticleDetail.action?article.id='+ articleId;
  	}
  	
  </script>
  
</HEAD>

<BODY>
 
  <div class="SheetBody" style="text-align:center;width:70%;">
   <table>
	  <c:if test="${not empty pagi.list }">
	        <s:iterator value="pagi.list" status="article">
	        <tr>
	            <td width="210" height="158" rowspan="4"  >
	                 <img width="250" height="180" src="${ctx}/articleTitlePic/<s:property value='titlePic'/>" style="margin-top:5px;margin-bottom:10px"  />
	            </td>
	            <td rowspan="1" style="height:70;maxwidth:604px;margin-right:10px;font-family:Microsoft YaHei;font-size:22.5px;font-weight:400;vertical-align:baseline;color:000000"><font  ><a href="javascript:showArticleDetail('<s:property value='id'/>')"><u><s:property value="title"/></u></a></font></td>
	        </tr>   
	         
	         <tr>
	             <td width="350"  height="70"><font style="font-family:Microsoft YaHei;font-size:15px;"><s:property value="brief"/> </font></td>
	         </tr>       
	         <tr>
	             <td width="350"  height="20" align="left"><font style="font-family:Microsoft YaHei;font-size:14px;">标签：</font> </td>
	         </tr>
	         <tr>
	             <td width="350"  height="20" align="left"><font style="font-family:Microsoft YaHei;font-size:12px;">更新时间：<s:property value="modifyDate" /></font> </td>
	         </tr>
	        </s:iterator>
	        </c:if>
	 </table>
  </div>
  
	<!-- 分页 -->
	<div class='Pager' style="float:left; padding-left:8px;">
		<input type="hidden" name="currentPage" id="currentPage" value="${pagi.currentPage}" />
		<input type="hidden" name="total_Page" id="total_Page" value="${pagi.totalPage}" />
        <table>
        	<tr>
           		<td>
                       总记录&nbsp;<span id='Pagination1_lbTotalCount'>${pagi.totalCount}</span>&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp;
                       当前第&nbsp;<span id='Pagination1_lbpageCurrent'>${pagi.currentPage}/${pagi.totalPage}</span>&nbsp;页&nbsp;&nbsp;
	                <a id='Pagination1_lbtnFirst' 
	                	<c:choose>
		                    <c:when test="${pagi.firstPage==true}"> 
		                    	disabled='disabled'
		                    </c:when>   
		                    <c:otherwise>
		                        href="javascript:requestPage(1)"
		                    </c:otherwise>  
	                 	</c:choose>  
	                >首　页</a>&nbsp;&nbsp;
	                
	                <a id='Pagination1_lbtnPre' 
	                	<c:choose>
	                    	<c:when test="${pagi.hasPreviousPage==true}"> 
	                        	href="javascript:requestPage(${pagi.currentPage}-1)"
	                    	</c:when>   
	                    	<c:otherwise>                        
	                        	disabled='disabled'
	                        </c:otherwise>  
	                   </c:choose>  
	                >上一页</a>&nbsp;&nbsp;
	                
	                <a id='Pagination1_lbtnNext' 
	                    <c:choose>
	                      <c:when test="${pagi.hasNextPage==true}">
	                           href="javascript:requestPage(${pagi.currentPage}+1)"
	                      </c:when>   
	                      <c:otherwise>                        
	                        disabled='disabled'
	                      </c:otherwise>  
	                   </c:choose>                 
	                >下一页</a>&nbsp;&nbsp;
	                
	                <a id='Pagination1_lbtnFinally' 
	                   <c:choose>
	                      <c:when test="${pagi.lastPage==true}"> 
	                          disabled='disabled'
	                      </c:when>   
	                      <c:otherwise>                       
	                        href="javascript:requestPage(${pagi.totalPage})"
	                      </c:otherwise>  
	                   </c:choose>   
	                >尾　页</a>&nbsp;&nbsp;
	                
	                                          第&nbsp;<input name='nPage' type='text' maxlength='5' id='nPage' style='height:17px;border-color:#D5E0E6;border-width:1px;border-style:Solid;width:25px;'>&nbsp;页
               </td>
           <td>
               <a id="Pagination_btnGo" class="btnGo" href="#" onclick="javascript:requestPage(document.getElementById('nPage').value)"></a>
           </td>
         </tr>
       </table>
     </div>
	
</BODY>
</HTML>