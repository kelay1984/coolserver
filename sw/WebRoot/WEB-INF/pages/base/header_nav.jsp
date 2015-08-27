<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript" src="<%=basePath%>js/jquery-2.1.4.js"></script>
<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
<link type="text/css" rel="stylesheet"
	href="<%=basePath%>/css/bootstrap.min.css">
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
	padding: 120px;
}

#content .sidebar {
	padding: 10px;
}

#content p {
	line-height: 30px;
}
.input-sm {   /* 设置小的输入框input*/
  height: 30px;
  padding: 5px 10px;
  font-size: 12px;
  line-height: 1.5;
  border-radius: 3px;
}
select.input-sm {   /* 设置小的选择框select*/
  height: 30px;
  line-height: 30px;
}
textarea.input-sm,
select[multiple].input-sm { /* 设置小的文本框textarea*/
  height: auto;
}
.input-lg {   /* 设置大的输入框input*/
  height: 46px;  padding: 10px 16px;  font-size: 18px;  line-height: 1.33; 
      border-radius: 6px;
}
select.input-lg {  height: 46px;  line-height: 46px; /* 设置大的选择框select*/}
textarea.input-lg,select[multiple].input-lg {  height: auto; /* 设置大的文本框
    textarea*/}


</style>


</head>

<body>
	<div class='container'>
		<h1>冷链设备中间件控制中心</h1>
		<div class='navbar navbar-inverse'>
			<div class='navbar-inner nav-collapse' style="height: auto;">
				<ul id="myTab" class="nav">
					<li class="active"><a
						href="<%=request.getContextPath()%>/welcome/config">主页</a></li>
					<li><a href="<%=request.getContextPath()%>/welcome/setting">初始化</a></li>
					<li><a href="<%=request.getContextPath()%>/welcome/accesspage">授权</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>
