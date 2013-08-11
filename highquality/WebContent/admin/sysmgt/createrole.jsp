<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="jspheader.jsp" %>
<HTML>
 <HEAD> 
  <TITLE>sysadmin</TITLE>
  <link rel="StyleSheet" href="${pageContext.request.contextPath}/auth/dtree/dtree.css" type="text/css" />
  <link href="../jscomponents/erp.css" type="text/css" rel="stylesheet" />
  <script src="${pageContext.request.contextPath}/auth/dtree/dtree.js"></script>  
  <script language="javascript">
        function check() {
		        var regex = /^[0-9]{1,6}$/;
		        var regexs = /^[^~!@#$%^&*·￥']{1,40}$/;
		        var regexss = /^[^~!@#$%^&*·￥']{0,100}$/;
		        var regexsss = /^[^~!@#$%^&*·￥']{0,255}$/;
			    if(document.forms[0]['roleVo.name'].value == ""){
				    alert("组名称不能为空");
					return;
				}

  				document.forms[0].submit();
    	}
 </script>
</HEAD>
<body  topmargin="0">
<div class="ToolBar">
    <span class="ToolBarButton">                  
       	<a class="Btn_Save" id="Btn_Save" onmouseover='javaScript:this.className="Btn_Save_Over"' onmouseout='javaScript:this.className="Btn_Save"' onclick="javascript:check();"></a>            
      	<a class="Btn_Back" id="Btn_Back" onmouseover='javaScript:this.className="Btn_Back_Over"' onmouseout='javaScript:this.className="Btn_Back"' onclick="history.go(-1)"></a>        
    </span>
</div> 

<form method="POST" action="saverole.action">
	<input type="hidden" name="roleVo.action" value="save_action"/>
	<div class="EditDivBody">
		<table cellspacing="1px" border="0" class="EditTableBorder"  width="40%">    
			<caption>增加角色</caption>
			<tr>
				<td class="EditHead">角色名称：</td>
		        <td class="EditRow">
		        	<input type="text" class="text" name="roleVo.name" value="" size="55"/>
		        </td>               
			</tr>
			<tr>
				<td class="EditHead">描述：</td>
				<td class="EditRow">	
		         <input type="text" class="text" name="roleVo.descr" value="" size="55"/>
		    	</td>
			</tr>
			<c:if test="${roleVo.node!=null}">
			  <c:forEach items="${roleVo.node.children}" var="roleleveltwo" >       
			    <tr style="background-color: white">
			      <td colspan="2" align="left">	    
		          	<input id="2#${rolelevelone.nodeid}" name="roleVo.roleNodes" type="checkbox" value="2#${roleleveltwo.nodeid}"/>${roleleveltwo.nodename}<br>
		        	<c:forEach items="${roleleveltwo.children}" var="rolelevelthree" >
		        	  &nbsp;&nbsp;&nbsp;&nbsp;
		        	  <input id="3#${rolelevelthree.nodeid}" name="roleVo.roleNodes" type="checkbox" value="3#${rolelevelthree.nodeid}"/>${rolelevelthree.nodename}<br>
		              <c:forEach items="${rolelevelthree.children}" var="rolelevelfour">	
		        	  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                <input id="4#${rolelevelfour.nodeid}" name="roleVo.roleNodes" type="checkbox" value="4#${rolelevelfour.nodeid}"/>${rolelevelfour.nodename}<br>
		              </c:forEach>
		       		</c:forEach>
		          </td>
		        </tr>
		      </c:forEach>
			</c:if>
			<!-- 
			<tr>
				<td class="EditRow" colspan="2">
					<input type="button" value="创建" width="50" onclick="javascript:check();" height="25" border="0">
				</td>
			</tr>
			 -->
		</table>
	</div>
</form>

</body>
</html>