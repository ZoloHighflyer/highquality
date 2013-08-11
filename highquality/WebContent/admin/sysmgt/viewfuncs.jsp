<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="jspheader.jsp" %>
<%@ include file="../../envvar.jsp" %>

<HTML>
   <head> 
       <title>sysadmin</title>
   	   <link rel="StyleSheet" href="${pageContext.request.contextPath}/jscomponents/dtree/dtree.css" type="text/css" />
   	   <script src="${pageContext.request.contextPath}/jscomponents/dtree/dtree.js"></script>  
   </head>

	<script>
		// alert(tree.aNodes[tree.selectedNode].id);
		function addfunc() {
			if(tree.selectedNode!=null){
				//alert(tree.aNodes[tree.selectedNode].id);
				curNode = tree.aNodes[tree.selectedNode];
				level = tree.findNodeLevel(curNode);
				if (curNode!=null) {
					if (level<3) {
						frames["rightPanel"].location.href= 'addfunc.action?funcVo.id='+tree.aNodes[tree.selectedNode].id;
			    	}else{
				 		alert("选择一二层的结点才能增加功能！");
			    		return;
			    	}
			    }
	   		} else {
		      	alert("请选择一个结点");
		      	return ;
	   		}			            
		}	
	
		function delfunc() {
		    if(tree.selectedNode!=null){
		        //alert(tree.aNodes[tree.selectedNode].id);
		        frames["rightPanel"].location.href= 'delfunc.action?funcVo.id='+tree.aNodes[tree.selectedNode].id;
		    }else {
		      	alert("请选择一个结点");
		    }			            
		}	
	 	
		function updatefunc()      {
	        if(tree.selectedNode!=null){
	        	//alert(tree.aNodes[tree.selectedNode].id);
	       		frames["rightPanel"].location.href= 'editinitfunc.action?funcVo.id='+tree.aNodes[tree.selectedNode].id;
	    	}else {
	       		alert("请选择一个结点");
	        }			            
		}
	
		function addopt() {
		  curNode = tree.aNodes[tree.selectedNode];
		  level = tree.findNodeLevel(curNode);
		  if (curNode!=null) {
		     if (level<3) {
		        alert("增加操作必须要选择第3层的结点，当前您选择的是第"+level+"层结点！");
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
		       	} else {
		         	// alert(frames["rightPanel"].opts[checkedopt].value);
		         	if (flag==1) {
		          		frames["rightPanel"].location.href= 'editinitopt.action?optVo.id='+frames["rightPanel"].opts[checkedopt].value;
		         	} else {
		          		frames["rightPanel"].location.href= 'editinitopt.action?optVo.id='+frames["rightPanel"].opts.value;
		         	}
		       	}
		    } // if end
		} // function updateopt end
	
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
		  } // if end   	  
		} // function delopt end   
	</script>
<body>

	<table width="100%">
		<tr>
			<td valign="middle" width="100%" height="100%">
				<span style="height:22px;margin-left:15px;vertical-align:middle"><a href="javascript:addfunc();"><font size="2">增加菜单</font></a></span><img src="<%=ROOTPATH%>/images/splitter.gif">
				<span style="height:22px;margin-left:5px;vertical-align:middle"><a href="javascript:updatefunc();"><font size="2">修改菜单</font></a></span><img src="<%=ROOTPATH%>/images/splitter.gif">
				<span style="height:22px;margin-left:5px;vertical-align:middle"><a href="javascript:delfunc();"><font size="2">删除功能</font></a></span><img src="<%=ROOTPATH%>/images/splitter.gif">
				<span style="height:22px;margin-left:5px;vertical-align:middle"><a href="javascript:addopt();"><font size="2">增加操作</font></a></span><img src="<%=ROOTPATH%>/images/splitter.gif">						 
				<span style="height:22px;margin-left:5px;vertical-align:middle"><a href="javascript:updateopt();"><font size="2">修改操作</font></a></span><img src="<%=ROOTPATH%>/images/splitter.gif">
				<span style="height:22px;margin-left:5px;vertical-align:middle"><a href="javascript:delopt();"><font size="2">删除操作</font></a></span><img src="<%=ROOTPATH%>/images/splitter.gif">
			</td>
		</tr>
	</table>

    <table cellspacing="0" cellpadding="0" style="border-top:1px solid; border-color:#D5E0E6">
		<tr>
	 		<td width="5%">&nbsp;</td>
	 		<td width="30%" valign="top">
				<script type="text/javascript" language="JavaScript">	     
             		tree =  new dTree('tree','${pageContext.request.contextPath}/jscomponents/');//创建一个树对象 
          
             		<s:iterator value="#request.treeList" id="node" >
             			tree.add('<s:property value="#node.id"/>','<s:property value="#node.parfuncno"/>','<s:property value="#node.funcname"/>','showfunc.action?funcVo.id=<s:property value="#node.id"/>','<s:property value="#node.descr"/>','rightPanel');
             		</s:iterator>                        
                               
             		document.write(tree);
        		</script>
	 		</td>
	 		<td valign="top" width="65%">	
		 		<iframe id="rightPanel" frameborder="0" width="500" name="rightPanel" src="" height="100%" scrolling="NO">
				</iframe>
	 		</td>
		</tr>
	</table>

<script>
	function openselectnode(nid){
        tree.openTo(tree.findNodeOrder(tree.findNodeParent(tree.findNodeById(nid))), true, false);
        tree.s(tree.findNodeOrder(tree.findNodeById(nid)));
        frames["rightPanel"].location.href= 'showfunc.action?funcVo.id='+tree.aNodes[tree.selectedNode].id;
    }
	        
	function init() {
 		if (tree.aNodes.length>0) {
  			tree.s(0);
   			frames["rightPanel"].location.href= 'showfunc.action?funcVo.id='+tree.aNodes[tree.selectedNode].id;
  		}
	}
	    
	
</script>

<script type="text/javascript" language="JavaScript">
	<c:choose>
		<c:when test="${null!=msg}">
	   		openselectnode(${funcVo.id});
		</c:when>
		<c:otherwise>
	    	init();
		</c:otherwise>
	</c:choose>
</script>
</body>
</HTML>