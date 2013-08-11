<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="jspheader.jsp" %>
<HTML>
 <HEAD> 
  	<TITLE>sysadmin</TITLE>
    <link href="../jscomponents/erp.css" type="text/css" rel="stylesheet" />
</HEAD>
<BODY>
	<table width="100%" border="0">
		<tr>
			<td>
				<div class="SheetBody">
			    	<table class="GridBorder" cellspacing="1px" style="width:90%">
						<tr class="GridBodyA" onmouseover="this.style.backgroundColor='#E8F2FC'" onmouseout="this.style.backgroundColor=''">
							<td>部门名称：</td>
							<td>
								&nbsp;<font color="#000000"> ${depVo.depname}</font>
							</td>
						</tr>
						<tr class="GridBodyA" onmouseover="this.style.backgroundColor='#E8F2FC'" onmouseout="this.style.backgroundColor=''">
							<td>部门描述：</td>
							<td class="tdwhite3">
								&nbsp;<font color="#000000">${depVo.descr}</font><br>
							</td>
						</tr>					
					</table>
				</div>
            </td>
        </tr>
    </table>
</BODY>
</HTML>