<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ include file="../sysmgt/jspheader.jsp" %>
<HTML>
<HEAD> 
  <TITLE>团队信息</TITLE>
   <link href="${pageContext.request.contextPath}/jscomponents/erp.css" type="text/css" rel="stylesheet" /> 
  <script type="text/javascript" language="JavaScript">
//全选		
  function SelectAll (chkVal, idVal)   { 
    var thisfrm = document.forms[0];
    if (idVal.indexOf ('chkAll') != -1)   	{
    	for (i=0; i<thisfrm.length; i++)   	{
  		  if (thisfrm.elements[i].id.indexOf ('chkItem') != -1) 		{
  		    	if(chkVal == true) 	{
  			    	thisfrm.elements[i].checked = true;
  			    } else 	{
  				thisfrm.elements[i].checked = false;
  			}
  		 }
  	    }   
  	 }
  } 
  function UpdateItem()  { 
      var thisfrm = document.forms[0];
      var selectedCount =0;
      var selectedOrder =-1;
      for (i=0; i<thisfrm.length; i++)     	{
    		if (thisfrm.elements[i].id.indexOf ('chkItem') != -1) {
    			if(thisfrm.elements[i].checked == true) {
    				selectedOrder=i;
    				selectedCount++;	
    			}
    		 }
    	}  
      if(selectedCount==0) {
            alert("请选择一个修改的操作！");
            return;
          }else if(selectedCount>1) {
            alert("不能选定多于一个的操作！");
            return;		        
          }else {        
             window.location.href= 'initeditproductentity.action?productVo.id='+thisfrm.elements[selectedOrder].value;
          
          }  	
  } 


  function DeleteItem() {
	  var thisfrm = document.forms[0];
      var selectedCount =0;
      var selectedOrder =-1;
      for (i=0; i<thisfrm.length; i++)     	{
    		if (thisfrm.elements[i].id.indexOf ('chkItem') != -1) {
    			if(thisfrm.elements[i].checked == true) {
    				selectedOrder=i;
    				selectedCount++;	
    			}
    		 }
    	}  
      if(selectedCount==0) {
            alert("请选择一个修改的操作！");
            return;
          }else if(selectedCount>1) {
            alert("不能选定多于一个的操作！");
            return;		        
          }else {        
             window.location.href= 'delproductentity.action?productVo.id='+thisfrm.elements[selectedOrder].value;
          
          }

  }
   function requestPage(curpage) {
	   
	   document.getElementById("cur").value=curpage;
	  // window.location.href= 'productmgmt.action?productVo.page.currentPage='+curpage;
	   var submitForm = document.getElementById('ProductTable');      
       submitForm.submit();  
	   
   }
   function requestRearch() {
	   var submitForm = document.getElementById('ProductTable');      
              submitForm.submit();   
     
   }
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
  		             window.location.href= 'editinitgroup.action?groupVo.id='+window.opts[checkedopt].value;
  		            }else {
  		             window.location.href= 'delrecord.action?groupVo.id='+window.opts.value;
  		            }
  		          }
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
  		         window.location.href= 'delgroup.action?knowledgeRecordVo.id='+window.opts[checkedopt].value;;
  		         
  		        }  		     
  	}	
  	function openWindow(id){
           
    		var a=showModalDialog('viewrecord.action?knowledgeRecordVo.id='+id, window,'dialogHeight:500px;dialogWidth:650px;status=off');
           
    	} 	 
  </script>
</HEAD>

<BODY>
 <div class="ToolBar">
        <span class="ToolBarButton">
             
            	<a class="Btn_Add" id="Btn_Add" onmouseover='javaScript:this.className="Btn_Add_Over"' onmouseout=javaScript:this.className="Btn_Add" href="addproductentity.action"></a>
            
           		<a class="Btn_Change" id="Btn_Change" onmouseover=javaScript:this.className="Btn_Change_Over" onmouseout=javaScript:this.className="Btn_Change"  onclick="javascript:UpdateItem();"></a>
            
            	<a class="Btn_Delete" id="Btn_Delete" onmouseover=javaScript:this.className="Btn_Delete_Over" onmouseout=javaScript:this.className="Btn_Delete" onclick="javascript:DeleteItem();"></a>

        </span>
 </div>  
 
<form id="ProductTable" name="ProductTable" method="post" action="productmgmt.action" >
    <div class="SheetBody">
        <table class="GridBorder" cellspacing="1px"  width="100%">
            <tr>       	 
                <td class="GridHeader" width="5%">产品类别</td>                
                <td class="GridHeader" width="10%">
                  <div style="text-align:left;padding:0px;">      
                    <select id="productVo.query" name="productVo.query.productClassifyid">     
                    <c:forEach items="${categories}" var="entity" >	
　　　　　　                    <option value="${entity.id}"  <c:if test="${productVo.productClassifyId==entity.id}">  selected
                          </c:if>
                          > 
                          ${entity.name}                         
                         </option>
                      </c:forEach> 
                    </select>
                  </div>
                </td>
                <td class="GridHeader" width="20%">
                 <a class="Btn_Seach" id="Btn_Seach" onmouseover=javaScript:this.className="Btn_Seach_Over" onmouseout=javaScript:this.className="Btn_Seach"  onclick="javascript:requestRearch();"></a>
                </td> 
            </tr>
         </table>
		<table class="GridBorder" cellspacing="1px"  width="100%">
            <tr>       	
                <td class="GridHeader" width="5%"><input id="Id_chkAll" type="checkbox" name="Id_chkAll" onclick="javascript:return SelectAll(this.checked,this.id);" /></td>
                <td class="GridHeader" width="5%">序号</td>                
                <td class="GridHeader" width="10%">产品编号</td> 
                <td class="GridHeader" width="20%">产品类别</td>                
                <td class="GridHeader" width="20%">产品名称</td>         
                <td class="GridHeader" width="10%">产品价格</td>
                <td class="GridHeader" width="10%">产品出货价格</td>
                <td class="GridHeader" width="10%">产品入货价格</td>
            </tr>
            
             <c:set var="pageData" value="${productVo.page}"/> 
             <c:set var="order" value="1"/> 
             <c:set var="entities" value="${pageData.list}"/>
             <input type="hidden"  name="productVo.page.currentPage" id="cur"  value="${pageData.currentPage}"  /> 
             <c:forEach items="${entities}" var="entity" >	         
                
              <c:choose>

                <c:when test="${order%2!=0}"> 
                    <tr class="GridBodyA" onmouseover="this.style.backgroundColor='#E8F2FC'" onmouseout="this.style.backgroundColor=''">				
						 <td><input id="Id_chkItem" type="checkbox" name="Id_chkItem" value="${entity.id}" /></td>                   	
					     <td>${order}</td>			    		 
						 <td style="text-align:left;padding-left:8px;" ><a href="viewproductentity.action?productVo.id=${entity.id}" style="a{color:#00F;}a.link{ color:#00F}a.hover{color:#F00}" >${entity.productNo}</a></td>
						 <td>${entity.classify}</td>
						 <td style="text-align:left;padding-left:8px;"><a href="viewproductentity.action?productVo.id=${entity.id}" style="a{color:#00F;}a.link{ color:#00F}a.hover{color:#F00}" >${entity.productName}</a></td>
	    			     <td>${entity.price}</td>
					     <td>${entity.outPrice}</td>
					     <td>${entity.inPrice}</td>
                    </tr>   
                </c:when>                
                <c:otherwise>
                     <tr class="GridBodyB" onmouseover="this.style.backgroundColor='#E8F2FC'" onmouseout="this.style.backgroundColor=''">				
						 <td><input id="Id_chkItem" type="checkbox" name="Id_chkItem" value="${entity.id}" /></td>                   	
					     <td>${order}</td>			    		 
						 <td style="text-align:left;padding-left:8px;" ><a href="viewproductentity.action?productVo.id=${entity.id}" style="a{color:#00F;}a.link{ color:#00F}a.hover{color:#F00}" >${entity.productNo}</a></td>
						 <td>${entity.classify}</td>
						 <td style="text-align:left;padding-left:8px;" ><a href="viewproductentity.action?productVo.id=${entity.id}" style="a{color:#00F;}a.link{ color:#00F}a.hover{color:#F00}" >${entity.productName}</a></td>
					     <td>${entity.price}</td>
					     <td>${entity.outPrice}</td>
					     <td>${entity.inPrice}</td>					     						
                     </tr>	
                </c:otherwise>
              </c:choose>
              <c:set var="order" value="${order+1}"/>
             </c:forEach>			    			
        </table>
          
    </div>
	<div class='Pager'>
	
       <table>
         <tr>
           <td>
                                  总记录&nbsp;<span id='Pagination1_lbTotalCount'>${pageData.totalCount}</span>&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp;
                                      当前第&nbsp;<span id='Pagination1_lbpageCurrent'>${pageData.currentPage}/${pageData.totalPage}</span>&nbsp;页&nbsp;&nbsp;
                <a id='Pagination1_lbtnFirst' 
                  <c:choose>
                      <c:when test="${pageData.firstPage==true}"> 
                         disabled='disabled'
                      </c:when>   
                      <c:otherwise>
                        href="javascript:requestPage(1)"
                      </c:otherwise>  
                   </c:choose>  
                  >首　页</a>&nbsp;&nbsp;
                <a id='Pagination1_lbtnPre' 
                  <c:choose>
                      <c:when test="${pageData.hasPreviousPage==true}"> 
                         href="javascript:requestPage(${pageData.currentPage}-1)"
                      </c:when>   
                      <c:otherwise>                        
                        disabled='disabled'
                      </c:otherwise>  
                   </c:choose>  
                               
                >上一页</a>&nbsp;&nbsp;
                <a id='Pagination1_lbtnNext' 
                    <c:choose>
                      <c:when test="${pageData.hasNextPage==true}"> 
                           href="javascript:requestPage(${pageData.currentPage}+1)"
                      </c:when>   
                      <c:otherwise>                        
                        disabled='disabled'
                      </c:otherwise>  
                   </c:choose>                 
                >下一页</a>&nbsp;&nbsp;
                <a id='Pagination1_lbtnFinally' 
                   <c:choose>
                      <c:when test="${pageData.lastPage==true}"> 
                          disabled='disabled'
                      </c:when>   
                      <c:otherwise>                       
                        href="javascript:requestPage(${pageData.totalPage})"
                      </c:otherwise>  
                   </c:choose>   
                >尾　页</a>&nbsp;&nbsp;
                                             第&nbsp;<input name='nPage' type='text' maxlength='5' id='nPage' style='height:17px;border-color:#D5E0E6;border-width:1px;border-style:Solid;width:25px;'>&nbsp;页
           </td>
           <td>
               <a id="Pagination_btnGo" class="btnGo" href="#" onclick="javascript:location.href='/RM/PublicTable.asp?PageCode=Material&Page='+ document.getElementById('nPage').value;return false;"></a>
           </td>
         </tr>
       </table>
     </div>
   </form> 
 
</BODY>
</HTML>