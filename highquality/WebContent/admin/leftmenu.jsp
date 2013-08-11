<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<HTML>
 <HEAD> 
  <TITLE>sysadmin</TITLE>
  <link rel="StyleSheet" href="${pageContext.request.contextPath}/jscomponents/dtree/dtree.css" type="text/css" />
  <script src="${pageContext.request.contextPath}/jscomponents/dtree/dtree.js"></script>  
</HEAD>
<body  topmargin="0">

<script type="text/javascript">      

         d = new dTree('d','jscomponents/');//创建一个树对象   

         d.add(0,-1,'系统菜单',''); 
         d.add(1,0,'团队信息模块',''); 
         d.add(2,1,'主题管理','info/topicmanagement.action','','main'); 
         d.add(3,1,'团队信息','info/infomanagement.action','','main'); 
         
         document.write(d);              
     </script>   


</body>
</HTML>
