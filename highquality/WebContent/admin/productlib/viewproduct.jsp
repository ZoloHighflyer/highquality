<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../jspheader.jsp" %>

<HTML>
 <HEAD> 
  <TITLE>创建产品</TITLE>
  <link href="${pageContext.request.contextPath}/jscomponents/erp.css" type="text/css" rel="stylesheet" />
       
</HEAD>
<body  topmargin="0">
<div class="ToolBar">
        <span class="ToolBarButton">                  
   
           		<a class="Btn_Back" id="Btn_Back" onmouseover='javaScript:this.className="Btn_Back_Over"' onmouseout='javaScript:this.className="Btn_Back"' onclick="history.go(-1)"></a>        
        </span>
</div> 
<form method="POST" id="editproductform" name="editproductform" action="saveproduct.action" enctype="multipart/form-data">
     <div class="EditDivBody">		
        <table class="EditTableBorder" cellspacing="1px" width="60%">
        <caption>产品信息</caption>
				<tr>
                    <td class="EditHead">产品编号  </td>
                    <td class="EditRow" >${productVo.productNo}</td>
                    <td class="EditHead">产品分类</td>
                    <td class="EditRow">${productVo.classify}</td>                   
                </tr>
                <tr>
                	<td class="EditHead" style="width:20%;">产品名称</td>
                    <td class="EditRow" style="width:30%;">${productVo.productName}</td>
                	<td class="EditHead" style="width:20%;"> 产品官方价格 </td>
                    <td class="EditRow" style="width:30%;">${productVo.price}</td>
                </tr>             
                <tr>
                    <td class="EditHead"> 产品入货价格  </td>
                    <td class="EditRow" >${productVo.inPrice}</td>
                    <td class="EditHead"> 产品报价  </td>
                    <td class="EditRow" >${productVo.outPrice}</td>
                </tr>
               
               <tr>
                    <td class="EditHead">产品图片  </td>
                    <td class="EditRow" colspan="3" >
                    <c:choose>
                      <c:when test="${not empty productVo.picture}">  
                         <img src="${pageContext.request.contextPath}${productVo.picture}" />
                      </c:when>                      
                    </c:choose>
                    </td>                  
                </tr>
                   
                     
        </table>
      
    </div>  
</form>

</body>
</html>