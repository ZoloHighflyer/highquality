<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="jspheader.jsp" %>
<HTML>
 <HEAD> 
  <TITLE>sysadmin</TITLE>
 </HEAD>
 <BODY>
 </BODY>
<script language="javascript">
<c:choose>
  <c:when test="${msg!=null}">
    parent.location.href="viewfuncs.action?msg=success&&funcVo.id=${funcVo.id}";
  </c:when>
  <c:otherwise>
    parent.location.href="viewfuncs.action";
  </c:otherwise>
</c:choose>

</script>
</HTML>