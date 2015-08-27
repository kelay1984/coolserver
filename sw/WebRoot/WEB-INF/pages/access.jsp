<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>coolserver starting page</title>

</style>
<script type="text/javascript" src="<%=basePath%>js/jquery-2.1.4.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
/* 		    $('#myTab a:first').tab('show');//初始化显示哪个tab 
		    
		    $('#myTab a').click(function (e) { 
		      e.preventDefault();//阻止a链接的跳转行为 
		      $(this).tab('show');//显示当前选中的链接及关联的content 
		    });  */
		    
		    $('#initServer').click(function (){
		    	var txt = $('#accesstxt').val();
		    	if(txt==''){
		    		alert("秘钥不能为空");
		    		return;
		    	}
		    	$.ajax( {   
		    	    type : "POST",   
		    	    url : "<%=path%>/welcome/access", 
		    	    data : {
		    	      'access' : txt
		    	     },  
		    	    dataType: "json",   
		    	    success : function(data) {   
		    	        if(data.success){   
		    	            alert("保存成功！");   
		    	             
		    	        }   
		    	        else{   
		    	            alert("保存失败！");   
		    	        }   
		    	    },   
		    	    error :function(){   
		    	        alert("网络连接出错！");   
		    	    }   
		    	});   
		    });

		});
	
	
	</script>
</head>

<body>
	<div class="row-fluid marginTop">
		<jsp:include page="./base/header_nav.jsp"/>
	</div>
	<div class="row-fluid marginTop active">
	  <div id="content" style="padding:150px;margin: 40px;">
	  	
	  	<div class="controls">
      		<input class="input-block-level" type="text" placeholder="输入授权码" id="accesstxt">
    	</div>
	  	<button type="button" class="btn btn-success" id="initServer">保存授权码</button>
	  </div>
	</div>		
</body>
</html>
