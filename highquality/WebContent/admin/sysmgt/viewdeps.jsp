<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="jspheader.jsp" %>
<HTML>
 <HEAD> 
  <TITLE>sysadmin</TITLE>
  <link rel="StyleSheet" href="${pageContext.request.contextPath}/jscomponents/dtree/dtree.css" type="text/css" />
  <script src="${pageContext.request.contextPath}/jscomponents/dtree/dtree.js"></script>  
  
</HEAD>
<body  topmargin="0">

<table width="100%" cellpadding="0" cellspacing="0" border="0">
<tr>
	<td valign="middle" width="100%" height="100%">
		<span style="height:22px;margin-left:15px;vertical-align:middle"><a href="javascript:adddep();"><font size="2">增加部门</font></a></span><img src="../images/splitter.gif">
		<span style="height:22px;margin-left:5px;vertical-align:middle"><a href="javascript:updatedep();"><font size="2">修改功能</font></a></span><img src="../images/splitter.gif">
		<span style="height:22px;margin-left:5px;vertical-align:middle"><a href="javascript:deldep();"><font size="2">删除功能</font></a></span><img src="../images/splitter.gif">
		<span style="height:22px;margin-left:5px;vertical-align:middle"><a href="javascript:addopt();"><font size="2">增加操作</font></a></span><img src="../images/splitter.gif">						 
		<span style="height:22px;margin-left:5px;vertical-align:middle"><a href="javascript:updateopt();"><font size="2">修改操作</font><a></span><img src="../images/splitter.gif">
		<span style="height:22px;margin-left:5px;vertical-align:middle"><a href="javascript:delopt();"><font size="2">删除操作</font></a></span><img src="../images/splitter.gif">
	</td>
</tr>
</table>

    <table leftmargin="50" height="100%" border="0" cellspacing="0" cellpadding="0" style="border-top:1px solid; border-color:#D5E0E6">
	<tr>
	 <td width="5%">
		&nbsp;
	 </td>
	 <td width="30%" valign="top">
		<script type="text/javascript" language="JavaScript">	     
             tree =  new dTree('tree','${pageContext.request.contextPath}/jscomponents/');//创建一个树对象 
          
             <s:iterator value="#request.treeList" id="node" >
             tree.add('<s:property value="#node.id"/>','<s:property value="#node.pardepno"/>','<s:property value="#node.depname"/>','showdep.action?depVo.id=<s:property value="#node.id"/>','<s:property value="#node.descr"/>','rightPanel');
             </s:iterator>                        
                               
             document.write(tree);



             function openselectnode(nid){
                 tree.openTo(tree.findNodeOrder(tree.findNodeParent(tree.findNodeById(nid))),true,false);
                tree.s(tree.findNodeOrder(tree.findNodeById(nid)));
                 frames["rightPanel"].location.href= 'showdep.action?depVo.id='+tree.aNodes[tree.selectedNode].id;
               
               }
               function init() {
                   //如果树的结点数大于零，即树有结点。
                 if (tree.aNodes.length>0) {
                  tree.s(0);
                   frames["rightPanel"].location.href= 'showdep.action?depVo.id='+tree.aNodes[tree.selectedNode].id;
                  }
               }
             
              // alert(tree.aNodes[tree.selectedNode].id);
               function adddep()      {
                   if(tree.selectedNode!=null){
                      //alert(tree.aNodes[tree.selectedNode].id);
                      curNode = tree.aNodes[tree.selectedNode];
  		            level = tree.findNodeLevel(curNode);
  		            if (curNode!=null) {
  		              if (level<3) {
  	  		              frames["rightPanel"].location.href= 'adddep.action?depVo.id='+tree.aNodes[tree.selectedNode].id;
  		              }else{
  		                alert("选择一二层的结点才能增加功能！");
  		                return;
  		              }
  		            }
  		         }else {
  		            alert("请选择一个结点");
  		            return ;
  		         }			            
  		     }	
  		     
  		     function deldep()      {
                   if(tree.selectedNode!=null){
                      //alert(tree.aNodes[tree.selectedNode].id);
  		            frames["rightPanel"].location.href= 'deldep.action?depVo.id='+tree.aNodes[tree.selectedNode].id;
  		         }else {
  		            alert("请选择一个结点");
  		         }			            
  		     }	
  		   function updatedep()      {
                   if(tree.selectedNode!=null){
                      //alert(tree.aNodes[tree.selectedNode].id);
  		            frames["rightPanel"].location.href= 'editinitdep.action?depVo.id='+tree.aNodes[tree.selectedNode].id;
  		         }else {
  		            alert("请选择一个结点");
  		         }			            
  		   }
  		   
  		  function addopt()      {
  		    curNode = tree.aNodes[tree.selectedNode];
  		    level = tree.findNodeLevel(curNode);
  		    if (curNode!=null) {
  		       if (level<2) {
  		          alert("增加操作必须要选择第2层的结点，当前您选择的是第"+level+"层结点！");
  		          return ;
  		       }
  		       frames["rightPanel"].location.href= 'addopt.action?optVo.funcId='+tree.aNodes[tree.selectedNode].id;
  		    }
  		  
  		  }  
  		  
  		  function updateopt() {
  		     var checkedopt=0;
  		     //flag 参数是指示当前队列是一个选项还是多个选项（0为一个选项/1为多个选项）
  		     var flag=0;
  		     
  
  		     if (frames["rightPanel"].opts!=null) {
  		        count=0;
  		        flag=1;
  		         if(frames["rightPanel"].opts.length!=undefined){
  		            for(var i=0;i<frames["rightPanel"].opts.length;i++) {  		            
  		               if (frames["rightPanel"].opts[i].checked) {
  		                 checkedopt=i;
  		                 count++;
  		               }
  		            }
  		          } else {
  		             flag=0;
  		             if (frames["rightPanel"].opts.checked) {
  		               count++;
  		              }
  		          }
  		        
  		          if(count==0) {
  		            alert("请选择一个修改的操作！");
  		            return;
  		          }else if(count>1) {
  		            alert("不能选定多于一个的操作！");
  		            return;		        
  		          }else {
  		            // alert(frames["rightPanel"].opts[checkedopt].value);
  		            if (flag==1) {
  		             frames["rightPanel"].location.href= 'editinitopt.action?optVo.id='+frames["rightPanel"].opts[checkedopt].value;
  		            }else {
  		             frames["rightPanel"].location.href= 'editinitopt.action?optVo.id='+frames["rightPanel"].opts.value;
  		            }
  		          }
  		       }	  
  		  } 
  		  function delopt() {  		     
  		     var checkedopt=0;
  		     //flag 参数是指示当前队列是一个选项还是多个选项（0为一个选项/1为多个选项）
  		     var flag=0;
  		     
  
  		     if (frames["rightPanel"].opts!=null) {
  		        count=0;
  		        flag=1;
  		         if(frames["rightPanel"].opts.length!=undefined){
  		            for(var i=0;i<frames["rightPanel"].opts.length;i++) {  		            
  		               if (frames["rightPanel"].opts[i].checked) {
  		                 checkedopt=i;
  		                 count++;
  		               }
  		            }
  		          } else {
  		             flag=0;
  		             if (frames["rightPanel"].opts.checked) {
  		               count++;
  		              }
  		          }
  		        
  		          if(count==0) {
  		            alert("请选择一个修改的操作！");
  		            return;
  		          }else if(count>1) {
  		            alert("不能选定多于一个的操作！");
  		            return;		        
  		          }else {
  		            // alert(frames["rightPanel"].opts[checkedopt].value);
  		            if (flag==1) {
  		             frames["rightPanel"].location.href= 'delopt.action?optVo.id='+frames["rightPanel"].opts[checkedopt].value;
  		            }else {
  		             frames["rightPanel"].location.href= 'delopt.action?optVo.id='+frames["rightPanel"].opts.value;
  		            }
  		          }
  		       }	  
  		     
  		     	  
  		  }     
            
        </script>
	 </td>
	 <td valign="top" width="65%">	
		<iframe id="rightPanel" border="0" width="600" name="rightPanel" src="" height="400" scrolling="NO" frameborder="no"  >
		</iframe>
		
		<script type="text/javascript" language="JavaScript">
		
		<c:choose>
		<c:when test="${null!=msg}">
		   openselectnode(${depVo.id});
		</c:when>
		<c:otherwise>
		      init();
		</c:otherwise>
		</c:choose>
		
		</script>
		
	 </td>
	</tr>
</table>
</body>
</HTML>