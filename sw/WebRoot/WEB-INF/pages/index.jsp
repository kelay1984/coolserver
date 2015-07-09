<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	 <script type="text/javascript" src="<%=basePath%>js/jquery-2.1.4.js" ></script>  
	 <script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js" ></script>  
	 <link type="text/css" rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css">
	 <title>coolserver starting page</title>
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
		<h1 class="page-header">
			Message12 : ${message}
		</h1>

	<ul class="nav nav-pills " id="myTab"> 
      <li class="active"><a href="#home">Home</a></li> 
      <li><a href="#profile">Profile</a></li> 
      <li><a href="#messages">Messages</a></li> 
      <li><a href="#settings">Settings</a></li> 
    </ul>

     <div class="tab-content span9 main"> 
      <div class="tab-pane active" id="home">
      	<button type="button" class="btn btn-success" id="initServer">start</button>
      	<button type="button" class="btn btn-danger" id="stopServer">stop</button>
      </div> 
      <div class="tab-pane" id="profile">.2..</div> 
      <div class="tab-pane" id="messages">.3..</div> 
      <div class="tab-pane" id="settings">..4.</div> 
    </div> 
    
 
	</body>
</html>
