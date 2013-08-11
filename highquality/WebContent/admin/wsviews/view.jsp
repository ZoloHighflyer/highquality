<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../jspheader.jsp" %>

<HTML>
 <HEAD> 
  <TITLE>显示视图</TITLE>
  <link href="${pageContext.request.contextPath}/jscomponents/erp.css" type="text/css" rel="stylesheet" />
       
</HEAD>
<body  topmargin="0">
<div class="ToolBar">
        <span class="ToolBarButton">                  
   
           		<a class="Btn_Back" id="Btn_Back" onmouseover='javaScript:this.className="Btn_Back_Over"' onmouseout='javaScript:this.className="Btn_Back"' onclick="history.go(-1)"></a>        
        </span>
</div> 

<div class="EditDivBody">		
        <table class="EditTableBorder" cellspacing="1px" width="80%">
        <caption>视图信息</caption>				
                <tr>
                	<td class="EditHead" style="width:20%;">视图名称</td>
                    <td class="EditRow" style="width:30%;">${viewVo.name}</td>
                	<td class="EditHead" style="width:20%;">视图标识</td>
                    <td class="EditRow" style="width:30%;">${viewVo.viewId}</td>
                </tr>       
                <tr>
                    <td class="EditHead">备&nbsp; &nbsp; 注</td>
                    <td class="EditRow" style="padding-top:2px;padding-bottom:2px;" colspan="3">${viewVo.mark}</td>
                </tr>
                <tr>
                    <td class="EditRow" style="padding-top:2px;padding-bottom:2px;" colspan="4" align="center">产品列表</td>
                </tr> 
                <c:if test="${null!=viewVo.products}">   
                     <tr>
                      <td class="EditHead" style="width:30%;" >序号</td>
                	  <td class="EditHead" style="width:20%;" >产品分类</td>                      
                	  <td class="EditHead" style="width:20%;" colspan="2">产品名称</td>
                     </tr>
                     <c:set var="order" value="1"/>  
                     <c:forEach items="${viewVo.products}" var="entry" >
                       <tr>
                         <td class="EditRow" >${order}</td>
                         <td class="EditRow" >${entry.classify}</td>
                         <td class="EditRow" colspan="2">${entry.productName}</td>
                       </tr>
                       <c:set var="order" value="${order+1}"/>       
                     </c:forEach>
               </c:if>  
        </table>         
</div>
 


</body>
</html>