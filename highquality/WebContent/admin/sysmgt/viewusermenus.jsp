<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="jspheader.jsp" %>
<HTML>
 <HEAD> 
  <TITLE>sysadmin</TITLE>
  <link rel="StyleSheet" href="${pageContext.request.contextPath}/jscomponents/dtree/dtree.css" type="text/css" />
  <link href="./jscomponents/ZH.css" rel="stylesheet" type="text/css" />
  <script src="${pageContext.request.contextPath}/jscomponents/dtree/dtree.js"></script>  
  
</HEAD>
<body  topmargin="0">

    <table leftmargin="50" height="100%" cellspacing="8" cellpadding="0" style="border:4px solid #D1EEEE; border-bottom-style: none; border-top: none; border-left-style: none">
		<tr>
		 <td width="30%" valign="top">
			<script type="text/javascript" language="JavaScript">	     
	             tree = new dTree('tree','${pageContext.request.contextPath}/jscomponents/');//创建一个树对象 
	          
	             <s:iterator value="userNodes" id="node" >
	             tree.add('<s:property value="#node.nodeid"/>','<s:property value="#node.parentid"/>','<s:property value="#node.nodename"/>','<s:property value="#node.data"/>','<s:property value="#node.nodename"/>','main');
	             </s:iterator>                        
					
	             document.write(tree);
	        </script>
		 </td>
		 
  		</tr>
	
	</table>
</body>
</HTML>