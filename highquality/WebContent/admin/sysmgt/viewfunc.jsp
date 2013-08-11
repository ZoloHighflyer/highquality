<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="jspheader.jsp" %>
<%@ include file="../../envvar.jsp" %>
<HTML>
 	<HEAD> 
  		<TITLE>sysadmin</TITLE>
    	<link href="<%=ROOTPATH%>/jscomponents/erp.css" type="text/css" rel="stylesheet" />
	</HEAD>
<BODY>
	
    <table style="width:100%">
    	<tr>
    		<td>
    			<div class="SheetBody">
			        <table class="GridBorder" cellspacing="1px" style="width:90%">
						<tr class="GridBodyA" onmouseover="this.style.backgroundColor='#E8F2FC'" onmouseout="this.style.backgroundColor=''">
							<td width="30%">菜单名称：</td>
							<td width="70%" class="tdwhite2" >
								&nbsp;<font color="#000000"> ${funcVo.funcname}</font>
							</td>
						</tr>
						<tr class="GridBodyA" onmouseover="this.style.backgroundColor='#E8F2FC'" onmouseout="this.style.backgroundColor=''">
							<td width="30%">功能地址：</td>
							<td width="70%" class="tdwhite2" >
								&nbsp;<font color="#000000"> ${funcVo.funcaction}</font>
							</td>
						</tr>					
						<tr class="GridBodyA" onmouseover="this.style.backgroundColor='#E8F2FC'" onmouseout="this.style.backgroundColor=''">
							<td width="30%">描述：</td>
							<td width="70%" class="tdwhite3">
								&nbsp;<font color="#000000">${funcVo.descr}</font><br>
							</td>
						</tr>					
					</table>
				</div>
        	</td>
        </tr>
        <c:if test="${funcVo.opts!=null}"> 
        <tr>
        	<td>	
        		<div class="SheetBody">			
				    <table class="GridBorder" cellspacing="1px" style="width:90%">
					    <tr class="GridBodyA"><td colspan="3" align="left">操作信息：</td></tr>
					    <tr class="GridBodyA" onmouseover="this.style.backgroundColor='#E8F2FC'" onmouseout="this.style.backgroundColor=''">
					    	<td width="10%">选择</td>
					  		<td width="30%">操作名</td>
					  		<td width="40%">操作方法</td>
					  	</tr>
					  	<c:set var="order" value="1"/>  
					  	<c:forEach items="${funcVo.opts}" var="opt" >
						<tr class="GridBodyA" onmouseover="this.style.backgroundColor='#E8F2FC'" onmouseout="this.style.backgroundColor=''">
					  		<td>
						   		<input id="c${order}" name="opts" type="checkbox" value="${opt.id}"/>
						    	<c:set var="order" value="${order+1 }"/>	
					  		</td>
					  		<td>${opt.name}</td>
					  		<td>${opt.optmethod}</td>
					  	</tr>
					  	</c:forEach>
					</table> 
				</div>
              </td>
        </tr>
        </c:if>
    </table>
</BODY>
</HTML>