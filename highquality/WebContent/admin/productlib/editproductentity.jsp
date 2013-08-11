<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../jspheader.jsp" %>
<%
String path = request.getContextPath();
%>
<HTML>
 <HEAD> 
  <TITLE>创建产品</TITLE>
  <link href="${pageContext.request.contextPath}/jscomponents/erp.css" type="text/css" rel="stylesheet" />
  <script language="JavaScript" src="${pageContext.request.contextPath}/jscomponents/validation-framework.js"></script>
  <!-- script type="text/javascript" src="<%=path%>/sysmgt/orgselect/app/js/public.js"></script>   -->
<script type="text/javascript" src="<%=path%>/sysmgt/orgselect/app/js/orgsVo.js"></script>

<!--<script type="text/javascript" src="<%=path%>/sysmgt/orgselect/app/js/tabpane.js"></script>-->

  <script language="javascript">
 
        ValidationFramework.init("validation-config.xml");

        function submit(formid)  {
            var submitForm = document.getElementById(formid);
            if (doValidate(formid))
                    submitForm.submit();        	   
    	}

      function openWindow(){
            
    		var treeReturn = showModalDialog('categorytree.action', window,'dialogHeight:500px;dialogWidth:650px;status=off');
            if (treeReturn!=null){
                document.getElementById('productVo.productClassify.name').value=treeReturn[1];
                document.getElementById('productVo.productClassifyId').value=treeReturn[0];

            }else{
                alert("value is null");
            }
               return;
    	}  
 </script>
</HEAD>
<body  topmargin="0">
<div class="ToolBar">
        <span class="ToolBarButton">                  
            	<a class="Btn_Save" id="Btn_Save" onmouseover='javaScript:this.className="Btn_Save_Over"' onmouseout='javaScript:this.className="Btn_Save"' onclick="javascript:submit('editpeform');"></a>            
           		<a class="Btn_Back" id="Btn_Back" onmouseover='javaScript:this.className="Btn_Back_Over"' onmouseout='javaScript:this.className="Btn_Back"' onclick="history.go(-1)"></a>        
        </span>
</div> 
<form method="POST" id="editpeform" name="editpeform" action="saveproductentity.action" enctype="multipart/form-data">
	<input type="hidden" name="productVo.action" value="edit_action"/> 	
    <input type="hidden"  name="productVo.id" id="productVo.id"  value="${productVo.id}"  />    
    <div class="EditDivBody">		
        <table class="EditTableBorder" cellspacing="1px" width="60%">
        <caption>产品信息</caption>
				<tr>
                    <td class="EditHead">产品编号  </td>
                    <td class="EditRow"  ><input name="productVo.productNo" id="productVo.productNo" value="${productVo.productNo}" class="EditInput" type="text"  maxlength="100"  style="width:100%;"/></td>                  
                    <td class="EditHead">产品分类<input value="选择.." onclick="openWindow();"  type="button"   />  </td>
                    <td class="EditRow"><input id="productVo.productClassify.name" name="productVo.productClassify.name"  readonly class="EditInput" type="text" value="${productVo.classify}"/></td>                  
                    <input id="productVo.productClassifyId" name="productVo.productClassifyId"  value="${productVo.productClassifyId}" type="hidden" />
                </tr>
                <tr>
                	<td class="EditHead" style="width:20%;">产品名称${productVo.inPrice}</td>
                    <td class="EditRow" style="width:30%;"><input id="productVo.productName" name="productVo.productName" value="${productVo.productName}" class="EditInput" type="text"  maxlength="100"   style="width:100%;" /></td>
                	<td class="EditHead" style="width:20%;"> 产品官方价格 </td>
                    <td class="EditRow" style="width:30%;"><input id="productVo.price" name="productVo.price" value="${productVo.price}" class="EditInput" type="text"  maxlength="100"  style="width:100%;" /></td>
                </tr>
                <tr>
                	<td class="EditHead" style="width:20%;">产品入货价格</td>
                    <td class="EditRow" style="width:30%;"><input id="productVo.inPrice" name="productVo.inPrice" value="${productVo.inPrice}" class="EditInput" type="text"  maxlength="100"   style="width:100%;" /></td>
                	<td class="EditHead" style="width:20%;"> 产品报价 </td>
                    <td class="EditRow" style="width:30%;"><input id="productVo.outPrice" name="productVo.outPrice" value="${productVo.outPrice}" class="EditInput" type="text"  maxlength="100"  style="width:100%;" /></td>
                </tr>                  
                <tr>
                    <td class="EditHead">产品图片  </td>
                    <td class="EditRow" colspan="3" ><input id="productVo.file" name="productVo.file" class="EditInput" type="file"  maxlength="100"  style="width:50%;"/></td>                  
                </tr>
                <tr>
                    <td class="EditHead">备&nbsp; &nbsp; 注</td>
                    <td class="EditRow" style="padding-top:2px;padding-bottom:2px;" colspan="3"><textarea id="ftvo.mark" name="ftvo.mark" class="EditArea" cols="2" rows="2" style="width:98.1%" ></textarea></td>
                </tr>    
                     
        </table>
      
    </div> 
</form>


</body>
</html>