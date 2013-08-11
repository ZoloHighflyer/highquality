<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../jspheader.jsp" %>
<%
String path = request.getContextPath();
%>
<HTML>
 <HEAD> 
  <TITLE>创建产品</TITLE>
  <link href="${pageContext.request.contextPath}/jscomponents/erp.css" type="text/css" rel="stylesheet" />  
  <script language="JavaScript" src="${pageContext.request.contextPath}/jscomponents/validation-framework.js"></script>
  <script language="javascript"> 
  ValidationFramework.init("validation-config.xml");
  function submit(formid)  {
	  var str='';
	  for(var i=0;i< document.getElementById('right').length;i++) {
		  if (i==0){
			  str=document.getElementById('right').options[i].value+',';
			  continue;
		  } 
          str=str+document.getElementById('right').options[i].value+',';
	  }
	  if(str.charAt(str.length-1)==',') str=str.substring(0,str.length-1);
	  document.getElementById('viewVo.productids').value=str;
	 
	 
      var submitForm = document.getElementById(formid);
      if (doValidate(formid))
              submitForm.submit();   
           	   
	} 
  function getAjaxInstance() {
     xmlhttp=null;
     if (window.XMLHttpRequest)    {    // code for IE7, Firefox, Opera, etc.
         xmlhttp=new XMLHttpRequest();
     }  else if (window.ActiveXObject)  {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
     }
     return xmlhttp;
  }	
  
  function loadXMLDoc(url)
  {
  xmlhttp=getAjaxInstance();

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

  function state_Change(){
  if (xmlhttp.readyState==4)
    {// 4 = "loaded"
    if (xmlhttp.status==200) {// 200 = "OK"
          data=xmlhttp.responseXML;          
          var items=data.getElementsByTagName('option');
          leftselect=document.getElementById('left');         
          for(var j=leftselect.length-1;j>=0;j--) {
            leftselect.remove(j);
          }
          for(var i=0;i<items.length;i++)   {   
          //取option的子节点   
            var value=items[i].getElementsByTagName('value')[0].firstChild.nodeValue;
            var text =items[i].getElementsByTagName('name')[0].firstChild.nodeValue;
            if (!rightPanelContainKey(value)) {
               var opt = new Option(text,value);       	     
       	       leftselect.options.add(opt);
       	     }  
       	  }         
      }
    else
      {
      alert("Problem retrieving XML data:" + xmlhttp.statusText);
      }
    }
  }

 
  
  
  function rightPanelContainKey(key) {
       rightselect=document.getElementById('right');         
          for(var j=rightselect.length-1;j>=0;j--) {
            if(rightselect.options[j].value==key) {
               return true;
            }
          }
        return false;  
  }
  
       
          
       	  
       function moveUp(obj)　{　
       	　　　　　　for(var i=1; i < obj.length; i++)
       	　　　　　　{//最上面的一个不需要移动，所以直接从i=1开始
       	　　　　　　　　if(obj.options[i].selected)
       	　　　　　　　　{
       	　　　　　　　　　　if(!obj.options.item(i-1).selected)
       	　　　　　　　　　　{
       	　　　　　　　　　　　　var selText = obj.options[i].text;
       	　　　　　　　　　　　　var selValue = obj.options[i].value;
       	      obj.options[i].text = obj.options[i-1].text;
       	      obj.options[i].value = obj.options[i-1].value;
       	      obj.options[i].selected = false;
       	      obj.options[i-1].text = selText;
       	      obj.options[i-1].value = selValue;
       	      obj.options[i-1].selected=true;
       	　　　　　　　　　　}
       	　　　　　　　　}
       	　　　　　　}
       	　　　　}
       	  //下移
       	  function moveDown(obj)
       	　　　　{
       	　　　　　　for(var i = obj.length -2 ; i >= 0; i--)
       	　　　　　　{//向下移动，最后一个不需要处理，所以直接从倒数第二个开始
       	　　　　　　　　if(obj.options[i].selected)
       	　　　　　　　　{
       	　　　　　　　　　　if(!obj.options[i+1].selected)
       	　　　　　　　　　　{
       	　　　　　　　　　　　　var selText = obj.options[i].text;
       	　　　　　　　　　　　　var selValue = obj.options[i].value;
       	      obj.options[i].text = obj.options[i+1].text;
       	      obj.options[i].value = obj.options[i+1].value;
       	      obj.options[i].selected = false;
       	      obj.options[i+1].text = selText;
       	      obj.options[i+1].value = selValue;
       	     obj.options[i+1].selected=true;
       	　　　　　　　　　　}
       	　　　　　　　　}
       	　　　　　　}
       	　　　　}
       	  //移动
       	  function moveOption(obj1, obj2)
       	  {
       	    for(var k=obj2.options.length - 1 ; k >= 0 ; k--){
       	      obj2.options[k].selected=false;
       	    }
       	    for(var i = obj1.options.length - 1 ; i >= 0 ; i--)   {
       	     if(obj1.options[i].selected)	     {
       	       var opt = new Option(obj1.options[i].text,obj1.options[i].value);
       	       opt.selected = true;
       	       obj2.options.add(opt);
       	       obj1.remove(i);
       	      }
       	    }
       	  }
       	  //置顶
       	   function  moveTop(obj) 
       	   { 
       	   var  opts = []; 
       	   for(var i =obj.options.length -1 ; i >= 0; i--)
       	   {
       	    if(obj.options[i].selected)
       	    {
       	     opts.push(obj.options[i]);
       	     obj.remove(i);
       	    }
       	   }
       	   var index = 0 ;
       	   for(var t = opts.length-1 ; t>=0 ; t--)
       	   {
       	    var opt = new Option(opts[t].text,opts[t].value);
       	    opt.selected = true;
       	    obj.options.add(opt, index++);
       	   }
       	  } 
       	   //置底
       	   function  moveBottom(obj)    { 
       	   var  opts = []; 
       	   for(var i =obj.options.length -1 ; i >= 0; i--)
       	   {
       	    if(obj.options[i].selected)
       	    {
       	     opts.push(obj.options[i]);
       	     obj.remove(i);
       	    }
       	   }
       	    for(var t = opts.length-1 ; t>=0 ; t--)
       	   {
       	    var opt = new Option(opts[t].text,opts[t].value);
       	    opt.selected = true;
       	    obj.options.add(opt);
       	   }
       	  } 
       	  function  deleteBottom(obj)   { 
       	   for(var i =obj.options.length -1 ; i >= 0; i--)   {
       	    if(obj.options[i].selected)   {
       	        obj.remove(i);
       	    }
       	   }
       	  }
 </script>
</HEAD>
<body  topmargin="0">
<div class="ToolBar">
        <span class="ToolBarButton">                  
            	<a class="Btn_Save" id="Btn_Save" onmouseover='javaScript:this.className="Btn_Save_Over"' onmouseout='javaScript:this.className="Btn_Save"' onclick="javascript:submit('editviewform');"></a>            
           		<a class="Btn_Back" id="Btn_Back" onmouseover='javaScript:this.className="Btn_Back_Over"' onmouseout='javaScript:this.className="Btn_Back"' onclick="history.go(-1)"></a>        
        </span>
</div>
<form method="post" id="editviewform" name="editviewform" action="saveView.action">
	<input type="hidden" name="viewVo.action" value="edit_action"/> 	
    <input type="hidden"  name="viewVo.id" id="viewVo.id"  value="${viewVo.id}"  /> 
<div class="EditDivBody">		
        <table class="EditTableBorder" cellspacing="1px" width="60%">
        <caption>视图信息</caption>				
                <tr>
                	<td class="EditHead" style="width:20%;">视图名称</td>
                    <td class="EditRow" style="width:30%;"><input id="viewVo.name" name="viewVo.name" class="EditInput" type="text"  maxlength="100"   style="width:100%;" value="${viewVo.name}"/></td>
                	<td class="EditHead" style="width:20%;">视图标识</td>
                    <td class="EditRow" style="width:30%;"><input id="viewVo.viewId" name="viewVo.viewId" class="EditInput" type="text"  maxlength="100"  style="width:100%;" value="${viewVo.viewId}"/></td>
                </tr>       
                <tr>
                    <td class="EditHead">备&nbsp; &nbsp; 注</td>
                    <td class="EditRow" style="padding-top:2px;padding-bottom:2px;" colspan="3"><textarea id="viewVo.mark" name="viewVo.mark" class="EditArea" cols="2" rows="2" style="width:98.1%" >${viewVo.mark}</textarea></td>
                </tr>    
                   
        </table>  
        <table class="EditTableBorder" cellspacing="1px" width="60%" style="background-color:#FFFFFF;color:#000000;"><tr><td>            
        <div style="text-align:left;padding:0px;">      
           产品类别：<br><select name="classname" id="classname" onchange="loadXMLDoc('findProductByCid.action?cid='+this.options[this.selectedIndex].value);">
           <c:forEach items="${categories}" var="entity" >
           <option value="${entity.id}">${entity.name}</option>
            </c:forEach>
           </select>
         </div>

 <table border="0" width="400">
  <tr>
  <td><CENTER>可选择的产品列表</CENTER></td>
  <td> </td>
  <td><CENTER>已选择的产品列表</CENTER></td>
 </tr>
    <tr>
      <td width="40%">
       <select  multiple name="left" id="left"  style='width:200;height:400;' ondblclick="moveOption(document.getElementById('left'), document.getElementById('right'))">
       </select>
      </td>
      <td width="20%" align="center">
       <input type="button" value=" >> " onclick="moveOption(document.getElementById('left'),document.getElementById('right'))"><br><br>
       <input type="button" value=" << " onclick="moveOption(document.getElementById('right'), document.getElementById('left'))">
      </td>
      <td width="40%">
       <select  multiple name="right" id="right" size="8" style='width:200;height:400;' ondblclick="moveOption(document.getElementById('right'), document.getElementById('left'))">
       </select>
       <input type="hidden" name="viewVo.productids" id="viewVo.productids" /> 	
      </td>
    </tr>
   <tr>
  <td colspan="3">
     <CENTER>
      <INPUT TYPE="button" value="置顶" onclick="moveTop(document.getElementById('right'));">
      <INPUT TYPE="button" value="上移" onclick="moveUp(document.getElementById('right'));">
      <INPUT TYPE="button" value="下移" onclick="moveDown(document.getElementById('right'));">
      <INPUT TYPE="button" value="置底" onclick="moveBottom(document.getElementById('right'));">
      <INPUT TYPE="button" value="删除" onclick="deleteBottom(document.getElementById('right'));">
     </CENTER></td>
   </tr>
  </table>
  </td></tr></table>
</div>
 </form>
<script language="javascript"> 
function initRightPanelData(){	  
	  if (xmlhttp.readyState==4)  {// 4 = "loaded"
	    if (xmlhttp.status==200) {// 200 = "OK"
	          data=xmlhttp.responseXML;          
	          var items=data.getElementsByTagName('option');
	          rightselect=document.getElementById('right');         
	         
	          for(var i=0;i<items.length;i++)   {   
	          //取option的子节点   
	            var value=items[i].getElementsByTagName('value')[0].firstChild.nodeValue;
	            var text =items[i].getElementsByTagName('name')[0].firstChild.nodeValue;
	               var opt = new Option(text,value);       	     
	               rightselect.options.add(opt);
	       	     
	       	  }         
	      }  else    {
	      alert("Problem retrieving XML data:" + xmlhttp.statusText);
	      }
	 }
}
function loadXMLToRightPanel(url)   {
  xmlhttp=getAjaxInstance(); 
  if (xmlhttp!=null)    {
      xmlhttp.onreadystatechange=initRightPanelData;
      xmlhttp.open("GET",url,true);
      xmlhttp.send(null);
  }  else     {
      alert("Your browser does not support XMLHTTP.");
  }
}
loadXMLToRightPanel('findProductByViewId.action?cid=${viewVo.id}');
</script>
</body>
</html>