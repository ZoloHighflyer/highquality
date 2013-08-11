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
    for(var i=obj1.options.length-1;i>=0;i--){
       if(obj1.options[i].selected) {
         var opt = new Option(obj1.options[i].text,obj1.options[i].value);
         opt.selected=true;
         obj2.options.add(opt);
         obj1.remove(i);
       }
    }
  }
  //move selected objects to up
  function moveUp(obj) {
     for(var i=1;i<obj.length;i++){
         if(obj.options[i].selected) {
            if(!obj.options.item(i-1).selected) {
               var selText=obj.options[i].text;
               var selValue=obj.options[i].value;
               obj.options[i].text=obj.options[i-1].text;
               obj.options[i].value=obj.options[i-i].value;
               obj.options[i].selected=true;
               obj.options[i-1].text=selText;
               obj.options[i-1].value=selValue;
               obj.options[i-1].selected=false;
            }
         }
     }
  }
    //move selected objects to down
  function moveDown(obj) {
     for(var i=obj.length-2;i>=0;i--){
         if(obj.options[i].selected) {
            if(!obj.options[i+1].selected) {
               var selText=obj.options[i].text;
               var selValue=obj.options[i].value;
               obj.options[i].text=obj.options[i+1].text;
               obj.options[i].value=obj.options[i+i].value;
               obj.options[i].selected=false;
               obj.options[i+1].text=selText;
               obj.options[i+1].value=selValue;
               obj.options[i+1].selected=true;
            }
         }
     }
  }
  function moveTop(obj) {
     var opts=[];
     for(var i=obj.options.length-1;i>=0;i--) {
        if(obj.options[i].selected) {
           opts.push(obj.options[i]);
           obj.remove(i);
        }
     }
     var index=0;
     for(var t=opts.length-1;t>=0;t--) {
        var opt=new Option(opts[t].text.opts[t].value);
        opt.selected=true;
        obj.options.add(opt.index++);
     }
  }
  function moveBottom(obj) {
     var opts=[];
     for(var i=obj.options.length-1;i>=0;i--) {
        if(obj.options[i].selected) {
           opts.push(obj.options[i]);
           obj.remove(i);
        }
     }
    
     for(var t=opts.length-1;t>=0;t--) {
        var opt=new Option(opts[t].text.opts[t].value);
        opt.selected=true;
        obj.options.add(opt);
     }
  }
</script>
</head>
<body>
<span id="feedback"></span>
<form method="post" name="myform" >
  <table border="0" width="400">
    <tr>
      <td width="40%"><center> avalible order</center></td>
      <td width="20%"></td>
      <td width="40%"><center> selected order</center></td>
    </tr>
    <tr>
      <td >
         <select multiple name="left" id='left'  size="8" style='width:200;' ondblclick="moveOption(document.getElementById('left'),document.getElementById('right'))">
             <option value="20">ASP</option>
             <option value="30">ASP.net</option>
             <option value="40">PHP</option>
             <option value="50">JSP</option>
             <option value="60">VB</option>
             <option value="70">DELPHI</option>
             <option value="80">AJAX</option>
             <option value="90">FLASH</option>
             <option value="100">JS</option>
             <option value="110">XML</option>
         </select>
      </td>
      <td  align="center">
      <input type="button" value=">>" onclick="moveOption(document.getElementById('left'),document.getElementById('right'))" >
      <br><br>
      <input type="button" value="<<" onclick="moveOption(document.getElementById('right'),document.getElementById('left'))">
      </td>
      <td>
         <select multiple name="right" id='right' size="8" style="width:200;" ondblclick="moveOption(document.getElementById('right'),document.getElementById('left'))">
        </select>
        </td>
    </tr>
    <tr>
      <td colspan="3"><center>
        <input type="button" value="top"  onclick="moveTop(document.getElementById('right'))">
        <input type="button" value="up"  onclick="moveUp(document.getElementById('right'))">
        <input type="button" value="down"  onclick="moveDown(document.getElementById('right'))">
        <input type="button" value="bottom"  onclick="moveBottom(document.getElementById('right'))">
        </center>
      </td>
     </tr>       
    </table>
      
       

</body>
</html>