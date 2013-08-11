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
        function swap(divid)  {
    	    tabdiv = document.getElementById(divid);
              if(tabdiv.style.display == "none"){   
                  tabdiv.style.display="block";
                  imgid = divid+'_imgid';
                  imgo = document.getElementById(imgid);
                  imgo.src="${pageContext.request.contextPath}/jscomponents/imgs/Down.gif";
              }else{
                  tabdiv.style.display="none";
                  imgid = divid+'_imgid';
                  imgo = document.getElementById(imgid);
                  imgo.src="${pageContext.request.contextPath}/jscomponents/imgs/Up.gif";
              } 	   
    	}
        function delRow(tbid) {
	        var tb= document.getElementById(tbid);
			tb.deleteRow(event.srcElement.parentElement.parentElement.rowIndex);
	   }

        function addVenInfoRow(tbid) {
     	   var tb = document.getElementById(tbid);
     	   var tr = tb.insertRow(tb.rows.length);
     	       td0 = tr.insertCell(0);
     	   var selstr ="<input type=\"text\" name=\"appnames\" value=\"\" style=\"width:100%;\"/>";
       		   td0.innerHTML=selstr;         		   
     		   td1=tr.insertCell(1);
     		   td1.innerHTML="<input type=\"text\" name=\"appvers\" value=\"\" style=\"width:100%;\"/>";
     	       td2=tr.insertCell(2);
     		   td2.innerHTML="<input type=\"button\" value=\"删除\" onclick=\"delRow('"+tbid+"')\" />";
    
     	  }
        function addProInfoRow(tbid) {
      	   var tb = document.getElementById(tbid);
      	   var tr = tb.insertRow(tb.rows.length);
      	       td0 = tr.insertCell(0);
      	   var selstr ="<input type=\"text\" name=\"protypes\" value=\"\" style=\"width:100%;\"/>";
        		   td0.innerHTML=selstr;         		   
      		   td1=tr.insertCell(1);
      		   td1.innerHTML="<input type=\"text\" name=\"pronames\" value=\"\" style=\"width:100%;\"/>";
      	       td2=tr.insertCell(2);
      		   td2.innerHTML="<input type=\"button\" value=\"删除\" onclick=\"delRow('"+tbid+"')\" />";
     
      	  }
        function addAtrrInfoRow(tbid) {
       	   var tb = document.getElementById(tbid);
       	   var tr = tb.insertRow(tb.rows.length);
       	       td0 = tr.insertCell(0);
       	   var selstr ="<input type=\"text\" name=\"attrnames\" value=\"\" style=\"width:100%;\"/>";
         		   td0.innerHTML=selstr;         		   
       		   td1=tr.insertCell(1);
       		   td1.innerHTML="<input type=\"text\" name=\"atrrvals\" value=\"\" style=\"width:100%;\"/>";
       	       td2=tr.insertCell(2);
       		   td2.innerHTML="<input type=\"button\" value=\"删除\" onclick=\"delRow('"+tbid+"')\" />";
      
       	  }  	  	
 </script>
</HEAD>
<body  topmargin="0">
<div class="ToolBar">
        <span class="ToolBarButton">                  
            	<a class="Btn_Save" id="Btn_Save" onmouseover='javaScript:this.className="Btn_Save_Over"' onmouseout='javaScript:this.className="Btn_Save"' onclick="javascript:submit('createpeform');"></a>            
           		<a class="Btn_Back" id="Btn_Back" onmouseover='javaScript:this.className="Btn_Back_Over"' onmouseout='javaScript:this.className="Btn_Back"' onclick="history.go(-1)"></a>        
        </span>
</div> 
<form method="POST" id="createpeform" name="createpeform" action="saveproductentity.action" enctype="multipart/form-data">
	<div class="EditDivBody">		
        <table class="EditTableBorder" cellspacing="1px" width="60%">
        <caption>产品信息</caption>
				<tr>
                    <td class="EditHead">产品编号  </td>
                    <td class="EditRow"><input name="productVo.productNo" id="productVo.productNo" class="EditInput" type="text"  maxlength="100"  style="width:100%;"/></td>
                    <td class="EditHead">产品分类<input value="选择.." onclick="openWindow();"  type="button"   />  </td>
                    <td class="EditRow"><input id="productVo.productClassify.name" name="productVo.productClassify.name"  readonly class="EditInput" type="text" /></td>                  
                    <input id="productVo.productClassifyId" name="productVo.productClassifyId"  type="hidden" />
                </tr>
                <tr>
                	<td class="EditHead" style="width:20%;">产品中文名称</td>
                    <td class="EditRow" style="width:30%;"><input id="productVo.productName" name="productVo.productName" class="EditInput" type="text"  maxlength="100"   style="width:100%;" /></td>
                	<td class="EditHead" style="width:20%;"> 产品官方价格</td>
                    <td class="EditRow" style="width:30%;"><input id="productVo.price" name="productVo.price" class="EditInput" type="text"  maxlength="100"  style="width:100%;" /></td>
                </tr>               
               
                <tr>
                    <td class="EditHead"> 产品入货价格  </td>
                    <td class="EditRow" ><input id="productVo.inPrice" name="productVo.inPrice" class="EditInput" type="text"  maxlength="100"  style="width:100%;" /></td>
                    <td class="EditHead"> 产品报价 </td>
                    <td class="EditRow" ><input id="productVo.outPrice" name="productVo.outPrice" class="EditInput" type="text"  maxlength="100" value="" style="width:100%;" /></td>
                </tr>
                <tr>
                    <td class="EditHead"> 产品品牌  </td>
                    <td class="EditRow" ><input id="productVo.attrs.brand" name="productVo.attrs.brand" class="EditInput" type="text"  maxlength="100"  style="width:100%;" /></td>
                    <td class="EditHead"> 产品规格 </td>
                    <td class="EditRow" ><input id="productVo.attrs.specification" name="productVo.attrs.specification" class="EditInput" type="text"  maxlength="100" value="" style="width:100%;" /></td>
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
        <br>              
       
  </div>
  
</form>


</body>
</html>