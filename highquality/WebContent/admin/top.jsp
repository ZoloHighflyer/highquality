<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/jscomponents/ZH.css" rel="stylesheet" type="text/css" />
</head>
<script>
	var a = "asdf";
	
	function logout() {
		window.location.href = "logout.action";
	}
	
	function modifyPassword() {
		window.parent.main.location = "sysmgt/modifyPassWord.jsp";
	}
	
</script>
<body>
	<div class="ZH_top">
  	<div class="ZH_Logo"></div>
  	<div class="ZH_tipbox">
  	<div class="ZH_QuickMenu">
    	<ul>
            <li class="setup"><a href="#">个人信息</a></li>
            <li class="link"></li>
            <li class="password"><a href="#" onclick="modifyPassword()">修改密码</a></li>
            <li class="link"></li>
            <li class="close"><a href="#" onclick="logout();">退出</a></li>
        </ul>
    </div>
    <div class="ZH_tip_text">
    	<!-- <div class="ZH_tip_ID">欢迎     2012-2-2 11:09</div> -->
    	<div class="ZH_tip_run" style="float: right">欢迎     2012-2-2 11:09:28</div>
    </div>
  </div>
</div>
</body>
</html>