<%@ page  language="java"  pageEncoding="GBK"  %>
<%@ include file="../envvar.jsp" %>
<html>  
  <head>
    <title>Personal Infomation System</title>    
	 <link rel="stylesheet" type="text/css" href="<%=ROOTPATH%>/jscomponents/login.css" media="screen"/>   
	 <script language="javascript">
	 
	 function checknameinput(){
	
       var namein = document.getElementById("user.userid");
        
       if (namein.value.length==0) {         
         var nameinlabel = document.getElementById("user.userid.label");
         nameinlabel.innerText="本字段不能为空！";
         nameinlabel.focus();
       }
       return;
      }
      
      function check() {
       var namein = document.getElementById("user.userid");
        
       if (namein.value.length==0) {         
         var nameinlabel = document.getElementById("user.userid.label");
         
         nameinlabel.innerText="本字段不能为空！";
        
         return;
       }
          document.forms[0].submit();
      
       
      }
      
<!--
if(window.top!=window.self){
	if (self.name!="long_domain") {
		parent.location=self.location;
	}
}
-->
 
   

</script>
  </head>

  <body>
   <table border="0" align="center" height="150">
   <tr><td>
    <%
     String message = (String)request.getAttribute("errormessage");
     if (message!=null)
     {%>
    	<h5 align="center"><font color="red"><%=message %></font></h5> 
     <%  }%>
   </td></tr>
   </table>
  
   <fieldset align="center" style="width: 500px;">
       <div id="content">		      
		    <form method="POST" action="auth.action">
              <table border="0" align="center" width="90%">               
	            <tr><td align="right"  >    
                   用户名
				 </td><td align="left">
                   <input type="text" id="user.userid" name="user.userid" size="25"  onfocusout="checknameinput();" />
                 </td><td><label id="user.userid.label">用户名由字母与数字组成。</label></td></tr>             
				 <tr><td align="right">
                   密码
				 </td><td align="left">
                  <input type="password" name="user.password" size="27"/>
                  </td><td></td></tr>
				  <tr><td>
                  </td><td align="left">
                   <input type="button" value="登录" onclick="check();" />
				   </td><td></td></tr>
				 </table>         
             </form>   
        </div>     
  
   </fieldset>
   <div style="text-align: center">
        &copy; 2009-2011 Deployment Team
      </div>
  </body>
  
</html>
