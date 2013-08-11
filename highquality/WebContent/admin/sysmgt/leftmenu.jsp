<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<HTML>
 <HEAD> 
  <TITLE>sysadmin</TITLE>
  <link rel="StyleSheet" href="${pageContext.request.contextPath}/jscomponents/dtree/dtree.css" type="text/css" />
  <script src="${pageContext.request.contextPath}/jscomponents/dtree/dtree.js"></script>  
</HEAD>
<body leftmargin="50" topmargin="0">
    <script type='text/javascript'>
    tree = new dTree('d','${pageContext.request.contextPath}/jscomponents/');//创建一个树对象 
    tree.config.folderLinks=true;
    tree.config.useCookies=false;

    <s:iterator value="#request.treeList" id="node" >
    tree.add('<s:property value="#node.id"/>','<s:property value="#node.parfuncno"/>','<s:property value="#node.funcname"/>','','<s:property value="#node.descr"/>','semain');
    </s:iterator>  
           document.write(tree);

	   </script>
</body>
</HTML>
