<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
<script type="text/javascript">
var xmlhttp;
function loadXMLDoc(url)
{
xmlhttp=null;
if (window.XMLHttpRequest)
  {// code for IE7, Firefox, Opera, etc.
  xmlhttp=new XMLHttpRequest();
  }
else if (window.ActiveXObject)
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
if (xmlhttp!=null)
  {
  xmlhttp.onreadystatechange=state_Change;
  xmlhttp.open("GET",url,true);
  xmlhttp.send(null);
  }
else
  {
  alert("Your browser does not support XMLHTTP.");
  }
}

function state_Change()
{
if (xmlhttp.readyState==4)
  {// 4 = "loaded"
  if (xmlhttp.status==200) {// 200 = "OK"
        data=xmlhttp.responseXML;
        str='';
        var units=data.getElementsByTagName('unit');
        for(var i=0;i<units.length;i++)   {   
        //取unit的子节点   
          var items=data.getElementsByTagName('unit')[i].childNodes;
          for(var k=0;k<items.length;k++){
        	  if(units[i].childNodes[k].nodeName!="#text"){
        		 str=str+data.getElementsByTagName('unit')[i].childNodes[k].firstChild.nodeValue;
        	  }  
          }

        }
        document.getElementById('A1').innerHTML=str;
        /*
        x=data.documentElement.childNodes;
        str='';
    for (i=0;i<x.length;i++)
    { 
      if (x[i].nodeType==1)
      {//Process only element nodes (type 1) 
      str=x[i].nodeName+str;
     
      } 
    }
    document.getElementById('A1').innerHTML=str;
        /*
    	var userName=data.getElementsByTagName("userName")[0].firstChild.nodeValue;
    var message=data.getElementsByTagName("message")[0].firstChild.nodeValue;
    document.getElementById('A1').innerHTML=userName;
    document.getElementById('A2').innerHTML=message;
     */
    }
  else
    {
    alert("Problem retrieving XML data:" + xmlhttp.statusText);
    }
  }
}
</script>
</head>

<body>
<h2>Using the HttpRequest Object</h2>

<p><b>Status:</b>
<span id="A1"></span>
</p>

<p><b>Status text:</b>
<span id="A2"></span>
</p>

<p><b>Response:</b>
<br /><span id="A3"></span>
</p>

<button onclick="loadXMLDoc('topxml.jsp')">Get XML</button>

</body>
</html>
