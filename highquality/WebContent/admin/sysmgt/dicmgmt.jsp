<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<HTML>
<HEAD> 
  <TITLE>团队信息</TITLE>
   <link href="../jscomponents/erp.css" type="text/css" rel="stylesheet" /> 
  <script type="text/javascript" language="JavaScript">
  //全选		
  function SelectAll (chkVal, idVal) { 
  	  var thisfrm = document.forms[0];
  	  if (idVal.indexOf ('chkAll') != -1) {
  		  for (i=0; i<thisfrm.length; i++) {
	  		  if (thisfrm.elements[i].id.indexOf ('chkItem') != -1) {
	  			  if(chkVal == true) {
	  				  thisfrm.elements[i].checked = true;
	  			  } else {
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
             window.location.href= 'editdictype.action?dicTypeVo.id='+thisfrm.elements[selectedOrder].value;
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
             window.location.href= 'deldictype.action?dicTypeVo.id='+thisfrm.elements[selectedOrder].value;
          
          }

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
             window.location.href= 'editinitgroup.action?groupVo.id='+window.opts.value;
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
	         window.location.href= 'delgroup.action?groupVo.id='+window.opts[checkedopt].value;;
	         
	        }
  	  }	 
  	  
   //分页函数  	
   function requestPage(curpage) {
   	   var totalpage = document.getElementById("totalpage").value;
   	   if (curpage <= 0 || curpage > totalpage) {
   	       alert("页数输入不合法");
   	       return;
   	   }
	   document.getElementById("cur").value = curpage;
	   window.location.href= 'dictypemgmt.action?dicTypeVo.page.currentPage='+curpage;
   }	 
  </script>
</HEAD>

<BODY>
 <div class="ToolBar">
        <span class="ToolBarButton">
            	<a class="Btn_Add" id="Btn_Add" onmouseover='javaScript:this.className="Btn_Add_Over"' onmouseout=javaScript:this.className="Btn_Add" href="adddictype.action"></a>
           		<a class="Btn_Change" id="Btn_Change" onmouseover=javaScript:this.className="Btn_Change_Over" onmouseout=javaScript:this.className="Btn_Change"  onclick="javascript:UpdateItem();"></a>
            	<a class="Btn_Delete" id="Btn_Delete" onmouseover=javaScript:this.className="Btn_Delete_Over" onmouseout=javaScript:this.className="Btn_Delete" onclick="javascript:DeleteItem();"></a>
        </span>
 </div>  
 
	<form id="DicTypeTable" name="DicTypeTable" method="post" action="dictypemgmt.action" >
	    <div class="SheetBody" style="text-align:center;width:70%;">
			<table class="GridBorder" cellspacing="1px" align="center" width="100%">
	            <tr>            	
	                <td class="GridHeader" width="30"><input id="Id_chkAll" type="checkbox" name="Id_chkAll" onclick="javascript:return SelectAll(this.checked,this.id);" /></td>
	                <td class="GridHeader" width="38">序号</td>                
	                <td class="GridHeader">标识</td>
	                <td class="GridHeader">类型</td>                 
	            </tr>
	            <c:set var="order" value="1"/> 
	            <c:forEach items="${dicTypeVo.page.list}" var="dictype" >	       
	                <c:set var="trcss" value="GridBodyB"/> 
	            	<c:choose>
	            	<c:when test="${order%2!=0}"> 
	                	<c:set var="trcss" value="GridBodyA"/> 				    
					</c:when>   
	            	</c:choose>
					<tr class="${trcss}" onmouseover="this.style.backgroundColor='#E8F2FC'" onmouseout="this.style.backgroundColor=''">
	                    <td>
	                        <input id="Id_chkItem" type="checkbox" name="Id_chkItem" value="${dictype.id}" />
	                    </td>                   	
					    <td>${order}</td>
				        <td style="text-align:center;padding-left:8px;" title="${dictype.id}">${dictype.name}</td>
						<td style="text-align:center;padding-left:8px;" title="${dictype.identifyCode}">${dictype.identifyCode}</td>
	                </tr>	
	              	<c:set var="order" value="${order+1}"/>
	            </c:forEach>			    			
	        </table>
	    </div>
	    
	    <!-- 分页 -->
		<div class='Pager' style="float:left; padding-left:8px;">
			<input type="hidden" name="cur" id="cur" value="${dicTypeVo.page.currentPage}"  />
			<input type="hidden" name="totalpage" id="totalpage" value="${dicTypeVo.page.totalPage}"  />
	        <table>
	        	<tr>
	            	<td>
	                  	总记录&nbsp;<span id='Pagination1_lbTotalCount'>${dicTypeVo.page.totalCount}</span>&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp;
	                       当前第&nbsp;<span id='Pagination1_lbpageCurrent'>${dicTypeVo.page.currentPage}/${dicTypeVo.page.totalPage}</span>&nbsp;页&nbsp;&nbsp;
	                    <a id='Pagination1_lbtnFirst' 
	                        <c:choose>
	                            <c:when test="${dicTypeVo.page.firstPage==true}"> 
	                            	disabled='disabled'
	                      		</c:when>   
	                        	<c:otherwise>
	                        		href="javascript:requestPage(1)"
	                      		</c:otherwise>  
	                   		</c:choose>  
	                  	>首　页</a>&nbsp;&nbsp;
	                  	
	                	<a id='Pagination1_lbtnPre' 
	                  		<c:choose>
	                      		<c:when test="${dicTypeVo.page.hasPreviousPage==true}"> 
	                         		href="javascript:requestPage(${dicTypeVo.page.currentPage}-1)"
	                      		</c:when>   
	                      		<c:otherwise>                        
	                        		disabled='disabled'
	       			            </c:otherwise>  
	                   		</c:choose>  
	                	>上一页</a>&nbsp;&nbsp;
	                	
	                	<a id='Pagination1_lbtnNext' 
	    	                <c:choose>
		                    	<c:when test="${dicTypeVo.page.hasNextPage==true}"> 
	                            	href="javascript:requestPage(${dicTypeVo.page.currentPage}+1)"
	                      	</c:when>   
	                      	<c:otherwise>                        
	                        	disabled='disabled'
	                    	  </c:otherwise>  
	                   	</c:choose>                 
	                	>下一页</a>&nbsp;&nbsp;
	                	
	                	<a id='Pagination1_lbtnFinally' 
	        	            <c:choose>
	            	            <c:when test="${dicTypeVo.page.lastPage==true}"> 
	                	            disabled='disabled'
	                    	    </c:when>   
	                      		<c:otherwise>                       
	                        		href="javascript:requestPage(${dicTypeVo.page.totalPage})"
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
	</form> 
 
</BODY>
</HTML>