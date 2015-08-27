<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	 <title>coolserver starting page</title>
	 	 <style type='text/css'>
    body {
      background-color: #CCC;
    }
    #content {
      background-color: #FFF;
      border-radius: 5px;
    }
    #content .main {
      padding: 20px;
    }
    #content .sidebar {
      padding: 10px;
    }
    #content p {
      line-height: 30px;
    }
</style>
	<script type="text/javascript">
	$(document).ready(function(){
		    $('#myTab a:first').tab('show');//初始化显示哪个tab 
		    
		    $('#myTab a').click(function (e) { 
		      e.preventDefault();//阻止a链接的跳转行为 
		      $(this).tab('show');//显示当前选中的链接及关联的content 
		    }); 
		    
		    $('#initServer').click(function (){
		    	$.ajax( {   
		    	    type : "POST",   
		    	    url : "<%=request.getContextPath()%>/welcome/startServer", 
		    	    data : {
		    	      'room' : '202',
		    	      'roomid' :'110'
		    	     },  
		    	    dataType: "json",   
		    	    success : function(data) {   
		    	        if(data.success){   
		    	            alert("设置成功！");   
		    	             
		    	        }   
		    	        else{   
		    	            alert("设置失败！");   
		    	        }   
		    	    },   
		    	    error :function(){   
		    	        alert("网络连接出错！");   
		    	    }   
		    	});   
		    });
		    
		    $('#stopServer').click(function (){
		    	$.ajax( {   
		    	    type : "POST",   
		    	    url : "<%=request.getContextPath()%>/welcome/stopServer", 
		    	    data : {
		    	      'room' : '202',
		    	      'roomid' :'110'
		    	     },  
		    	    dataType: "json",   
		    	    success : function(data) {   
		    	        if(data.success){   
		    	            alert("设置成功！");   
		    	             
		    	        }   
		    	        else{   
		    	            alert("设置失败！");   
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
			暂未开放
	  </div>
	</div>	
 
	</body>
</html>
