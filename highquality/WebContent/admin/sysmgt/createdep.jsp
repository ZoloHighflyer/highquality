<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="jspheader.jsp" %>
<HTML>
 <HEAD> 
  	<TITLE>sysadmin</TITLE>
	<link href="${pageContext.request.contextPath}/jscomponents/erp.css" type="text/css" rel="stylesheet" />
  	<script src="auth/dtree/dtree.js"></script>  
  	<script language="javascript">
        function check() {
	        var regex = /^[0-9]{1,6}$/;
	        var regexs = /^[^~!@#$%^&*����']{1,40}$/;
	        var regexss = /^[^~!@#$%^&*����']{0,100}$/;
	        var regexsss = /^[^~!@#$%^&*����']{0,255}$/;

		    if(document.forms[0]['func.nno'].value == ""){
			    alert("���ܱ��벻��Ϊ��");
				return;
			}

		    if(document.forms[0]['func.nname'].value == ""){
			    alert("�������Ʋ���Ϊ��");
				return;
			}
		   
			if( !regex.test(document.forms[0]['func.nno'].value) ){
				alert("���ܱ���ֻ��Ϊ�������ͣ������������");
				document.forms[0]['func.nno'].value = "";
				return;
  			}
			if( !regexs.test(document.forms[0]['func.nname'].value) ){
	  			alert("���������зǷ��ַ��������������");
	  			document.forms[0]['func.nname'].value = "";
	  			return;
  			}
			if( !regexss.test(document.forms[0]['func.addr'].value) ){
 				alert("������ڵ�ַ�зǷ��ַ��������������");
  				document.forms[0]['func.addr'].value = "";
  				return;
  			}

			if( !regexsss.test(document.forms[0]['func.descr'].value) ){
  				alert("���������зǷ��ַ��������������");
  				document.forms[0]['func.descr'].value = "";
  				return;
  			}
 			document.forms[0].submit();
    	}
 	</script>
</HEAD>
<body  topmargin="0">
	<form method="POST" action="savedep.action">
		<div class="EditDivBody">
			<table cellspacing="1" border="0" class="EditTableBorder" width="70%">     
			    <tr>
					<td class="EditHead">����㣺</td>
					<td class="EditRow">
						${depVo.depname}
						<input type="hidden" name="depVo.pardepno" value="${depVo.id}"/>
			  		</td>
				</tr>  	
				<tr>
					<td class="EditHead">�������ƣ�</td>
					<td class="EditRow">  	
			        	<input type="text" class="text" name="depVo.depname" value="" size="45"/>
			    	</td>
				</tr>
				<tr>
					<td class="EditHead">����������</td>  	
					<td class="EditRow">
			        	<input type="text" class="text" name="depVo.descr" value="" size="45"/>
			    	</td>
				</tr>
				<tr>
					<td class="EditHead" colspan="2">
						<input type="submit" value="����" width="50" height="25" border="0">
					</td>
				</tr>
			</table>
		</div>
	</form>

</body>
</html>