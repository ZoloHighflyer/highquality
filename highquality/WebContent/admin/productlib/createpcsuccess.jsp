<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../sysmgt/jspheader.jsp" %>
<HTML>
 <HEAD> 
  <TITLE>sysadmin</TITLE>
 </HEAD>
 <BODY>
 </BODY>
<script language="javascript">
<c:choose>
  <c:when test="${msg!=null}">
    parent.location.href="productclassifymgmt.action?msg=success&&productClassifyVo.id=${productClassifyVo.id}";
  </c:when>
  <c:otherwise>
    parent.location.href="productclassifymgmt.action";
  </c:otherwise>
</c:choose>

</script>
</HTML>