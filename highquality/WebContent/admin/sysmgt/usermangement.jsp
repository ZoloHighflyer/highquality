<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ include file="../../envvar.jsp" %>
<HTML>
<HEAD> 
  <TITLE>团队信息</TITLE>
  <link href="<%=ROOTPATH%>/jscomponents/erp.css" type="text/css" rel="stylesheet" />
   
  <script type="text/javascript" language="JavaScript">
   function updateopt() {
       var checkedopt=0;
       var count=0;
        
       for(var i=0;i<window.opts.length;i++) {
          if (window.opts[i].checked) {
            checkedopt=i;
            count++;
          }
       }
     
       if(count==0) {
          alert("请选择一个修改的操作！");
          return;
       }else if(count>1) {
          alert("不能选定多于一个的操作！");
          return;		        
       }else {
         // alert(frames["rightPanel"].opts[checkedopt].value);
         window.location.href= 'editinituser.action?userVo.id='+window.opts[checkedopt].value;;
       }
  	}
  	
  	function delopt() {
	     var checkedopt=0;
	     var count=0;
	        
	     for(var i=0;i<window.opts.length;i++) {
	           if (window.opts[i].checked) {
	             checkedopt=i;
	             count++;
	           }
	        }
	        if(count==0) {
	          alert("请选择一个修改的操作！");
	          return;
	        }else if(count>1) {
	          alert("不能选定多于一个的操作！");
	          return;		        
	        }else {
	         // alert(frames["rightPanel"].opts[checkedopt].value);
	         window.location.href= 'deluser.action?userVo.id='+window.opts[checkedopt].value;;
	         
	        }
  	}
  	
  	// 分页函数
  	function requestPage(curpage) {
  		var total_Page = document.getElementById("total_Page").value;
   	   	if (curpage <= 0 || curpage > total_Page) {
   	       alert("页数输入不合法");
   	       return;
   	   	}
		document.getElementById("currentPage").value = curpage;
	   	window.location.href= 'viewusers.action?pagi.currentPage=' + curpage;
    }	 	 
  </script>
  
</HEAD>

<BODY>
<!-- 
<a href="adduser.action">增加用户</a>&nbsp;&nbsp;&nbsp;&nbsp;
<a href="javascript:updateopt();">修改用户</a>&nbsp;&nbsp;&nbsp;&nbsp;
<a href="javascript:delopt();">删除用户</a>
 -->
 
<div class="ToolBar">
     <span class="ToolBarButton">
        <a class="Btn_Add" id="Btn_Add" onmouseover='javaScript:this.className="Btn_Add_Over"' onmouseout='javaScript:this.className="Btn_Add"' href="adduser.action"></a>
       	<a class="Btn_Change" id="Btn_Change" onmouseover='javaScript:this.className="Btn_Change_Over"' onmouseout='javaScript:this.className="Btn_Change"'  onclick="javascript:updateopt();"></a>
        <a class="Btn_Delete" id="Btn_Delete" onmouseover='javaScript:this.className="Btn_Delete_Over"' onmouseout='javaScript:this.className="Btn_Delete"' onclick="javascript:delopt();"></a>
     </span>
 </div> 
  
	 <div class="SheetBody" style="text-align:center;width:70%;">
		<table class="GridBorder" cellspacing="1px" style="width:100%;" >
			<tr>            	
	            <td class="GridHeader" width="38">选择</td>     
	            <td class="GridHeader" width="38">序号</td>           
	            <td class="GridHeader">姓名</td>                
	            <td class="GridHeader">用户标识</td>                
	        </tr>
			<c:set var="order1" value="1" />
			<c:forEach items="${userVos}" var="userVo">
			  <c:choose>
			    <c:when test="${order1%2!=0}">  
				  <tr class="GridBodyA" onmouseover="this.style.backgroundColor='#E8F2FC'" onmouseout="this.style.backgroundColor=''">
					<td style="text-align:left;padding-left:8px;">
						<input id="c${order}" name="opts" type="checkbox" value="${userVo.id}" />
					</td>
					<td>${order1}</td>
					<td>${userVo.username}</td>
					<td>${userVo.userid}</td>
				  </tr>
				</c:when> 
				<c:otherwise>
				  <tr class="GridBodyB" onmouseover="this.style.backgroundColor='#E8F2FC'" onmouseout="this.style.backgroundColor=''">
					<td style="text-align:left;padding-left:8px;">
						<input id="c${order}" name="opts" type="checkbox" value="${userVo.id}" />
						<c:set var="order" value="${order+1 }" />
					</td>
					<td>${order1}</td>
					<td>${userVo.username}</td>
					<td>${userVo.userid}</td>
				  </tr>
				</c:otherwise>
			  </c:choose>
			  <c:set var="order1" value="${order1+1 }" />
			</c:forEach>
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