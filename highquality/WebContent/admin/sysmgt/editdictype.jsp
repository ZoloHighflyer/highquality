<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../jspheader.jsp" %>
<HTML>
 <HEAD> 
  <TITLE>创建配件</TITLE>
  <link href="../jscomponents/erp.css" type="text/css" rel="stylesheet" />
  <script language="JavaScript" src="../jscomponents/validation-framework.js"></script>
  <script language="javascript">
 
        ValidationFramework.init("sysmgt-validation-config.xml");

        function submit(formid)  {
            var submitForm = document.getElementById(formid);
            if (doValidate(formid))
                    submitForm.submit();        	   
    	}
    	
 </script>
</HEAD>
<body  topmargin="0">
<div class="ToolBar">
        <span class="ToolBarButton">                  
            	<a class="Btn_Save" id="Btn_Save" onmouseover='javaScript:this.className="Btn_Save_Over"' onmouseout='javaScript:this.className="Btn_Save"' onclick="javascript:submit('editdictypeform');"></a>            
           		<a class="Btn_Back" id="Btn_Back" onmouseover='javaScript:this.className="Btn_Back_Over"' onmouseout='javaScript:this.className="Btn_Back"' onclick="history.go(-1)"></a>        
        </span>
</div> 
<form method="POST" id="editdictypeform" name="editdictypeform" action="savedictype.action" >
	<input type="hidden" name="dicTypeVo.action" value="edit_action"/> 	
    <input type="hidden"  name="dicTypeVo.id" id="dicTypeVo.id"  value="${dicTypeVo.id}"  />
    
    <div class="EditDivBody">		
        <table class="EditTableBorder" cellspacing="1px" width="60%">        
				<tr>
                    <td class="EditHead">字典类型名称  </td>
                    <td class="EditRow" colspan="3" ><input name="dicTypeVo.name" id="dicTypeVo.name" class="EditInput" type="text" value="${dicTypeVo.name}" maxlength="100"  style="width:100%;"/></td>                  
                </tr>
               <tr>
                    <td class="EditHead">字典类型标识  </td>
                    <td class="EditRow" colspan="3" ><input name="dicTypeVo.identifyCode" id="dicTypeVo.identifyCode" class="EditInput" type="text" value="${dicTypeVo.identifyCode}" maxlength="100"  style="width:100%;"/></td>                  
                </tr>   
        </table>
      
    </div> 
</form>


</body>
</html>