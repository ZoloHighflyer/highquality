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
  		//flag 参数是指示当前队列是一个选项还是多个选项（0为一个选项/1为多个选项）
  		var flag=0;
  		     
        if (window.opts!=null) {
  			count=0;
  		    flag=1;
  		    if(window.opts.length!=undefined){
  		        for(var i=0;i<window.opts.length;i++) {  		            
  		        	if (window.opts[i].checked) {
  		            	checkedopt=i;
  		                count++;
  		            }
  		        }
  		    } else {
  		    	flag=0;
  		        if (window.opts.checked) {
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
  		        if (flag==1) {
  		        	window.location.href= 'editinitrole.action?roleVo.id='+window.opts[checkedopt].value;
  		        }else {
  		        	window.location.href= 'editinitrole.action?roleVo.id='+window.opts.value;
  		        }
  		    }
  		}	  
  	}
  	
  	function delopt() {
  		var checkedopt=0;
  		//flag 参数是指示当前队列是一个选项还是多个选项（0为一个选项/1为多个选项）
  		var flag=0;
  
  		if (window.opts!=null) {
	        count=0;
	        flag=1;
	        if(window.opts.length!=undefined){
	            for(var i=0;i<window.opts.length;i++) {  		            
	                if (window.opts[i].checked) {
	                	checkedopt=i;
	                	count++;
	                }
	            }
	        } else {
	             flag=0;
	             if (window.opts.checked) {
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
	            if (flag==1) {
	             window.location.href= 'delrole.action?roleVo.id='+window.opts[checkedopt].value;
	            }else {
	             window.location.href= 'delrole.action?roleVo.id='+window.opts.value;
	            }
	        }
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
	   	window.location.href= 'viewroles.action?pagi.currentPage=' + curpage;
    }
  </script>
</HEAD>

<BODY>
<!-- 
<a href="addrole.action">增加角色</a>&nbsp;&nbsp;&nbsp;&nbsp;
<a href="javascript:updateopt();">修改角色</a>&nbsp;&nbsp;&nbsp;&nbsp;
<a href="javascript:delopt();">删除角色</a>
 -->

  <div class="ToolBar">
     <span class="ToolBarButton">
        <a class="Btn_Add" id="Btn_Add" onmouseover='javaScript:this.className="Btn_Add_Over"' onmouseout='javaScript:this.className="Btn_Add"' href="addrole.action"></a>
       	<a class="Btn_Change" id="Btn_Change" onmouseover='javaScript:this.className="Btn_Change_Over"' onmouseout='javaScript:this.className="Btn_Change"'  onclick="javascript:updateopt();"></a>
        <a class="Btn_Delete" id="Btn_Delete" onmouseover='javaScript:this.className="Btn_Delete_Over"' onmouseout='javaScript:this.className="Btn_Delete"' onclick="javascript:delopt();"></a>
     </span>
  </div> 
  
  <div class="SheetBody" style="text-align:center;width:70%;">
	<table class="GridBorder" cellspacing="1px" style="width:100%;">
		<tr>
		   <td class="GridHeader" width="35">选择</td>
		   <td class="GridHeader" width="35">序号</td>
		   <td class="GridHeader">角色名称</td>
		</tr>
		<c:if test="${roleVos!=null}"> 
		   <c:set var="order" value="1"/> 
		   <c:forEach items="${roleVos}" var="roleVo" >
		   <c:choose>
		   	   <c:when test="${order%2!=0}"> 
				   <tr class="GridBodyA" onmouseover="this.style.backgroundColor='#E8F2FC'" onmouseout="this.style.backgroundColor=''">
				     <td> 
				         <input id="c${order}" name="opts" type="checkbox" value="${roleVo.id}"/>
				     </td>
				     <td>${order}</td>
				     <td>${roleVo.name}</td> 
				   </tr>
			   </c:when>
			    <c:otherwise>
			    	<tr class="GridBodyB" onmouseover="this.style.backgroundColor='#E8F2FC'" onmouseout="this.style.backgroundColor=''">
				     <td> 
				         <input id="c${order}" name="opts" type="checkbox" value="${roleVo.id}"/>
				     </td>
				     <td>${order}</td>
				     <td>${roleVo.name}</td> 
				   </tr>
			    </c:otherwise>
		   </c:choose>
		   <c:set var="order" value="${order+1 }"/>
		   </c:forEach>
		</c:if>
	</table>
  </div>
  
  <!-- 分页 -->
  <div class='Pager' style="float:left; padding-left:8px;">
		<input type="hidden" name="currentPage" id="currentPage" value="${pagi.currentPage}"  />
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