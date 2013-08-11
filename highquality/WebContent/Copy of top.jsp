<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/jscomponents/ZH.css" rel="stylesheet" type="text/css" />
<style >
center {
font-size:12px;
color:red;
font-weight:bold;
}
select {
font-size:12px;
color:green;
}
</style>
<script language="JavaScript">
  function moveOption(obj1,obj2) {
    for(var i=obj1.option.length-1;i>=0;i--){
       if(obj1.options[i].selected) {
         var opt = new Option(obj1.option[i].text,obj1.option[i].value);
         opt.selected=true;
         obj2.options.add(opt);
         obj1.remove(i);
       }
    }
  }
</script>
</head>
<body>
<span id="feedback"></span>
<form method="post" name="myform" >
  <table border="0" width="400">
    <tr>
      <td><center> avalible order</center></td>
      <td></td>
      <td><center> selected order</center></td>
    </tr>
    <tr>
      <td width="40%" >
         <select multiple name="left" id="left"  size="8" style='width:200;' ondblclick="moveOption(document.getElementById('left'),document.getElementById('right'))">
             <option value="20">ASP</option>
             <option value="30">ASP.net</option>
             <option value="40">PHP</option>
             <option value="50">JSP</option>
             <option value="60">VB</option>
             <option value="70">DELPHI</option>
             <option value="80">AJAX</option>
         </select>
      </td>
      <td width="20%" align="center">
      <input type="button" value=">>" onclick="moveOption(document.getElementById('left'),document.getElementById('right'))" >
      <br><br>
      <input type="botton" value="<<" onclick="moveOption(document.getElementById('right'),document.getElementById('left'))">
      </td>
      <td width="40%">
        <select multiple name="right" id="right" size="8" style="width:200;" ondblclick="moveOption(document.getElementById('right'),document.getElementById('left'))">
        </select>
        </td>
    </tr>
      
       

</body>
</html>