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
</style>


</head>

<body>
	<div class='container'>
		<h1>冷链设备中间件控制中心</h1>
		<div class='navbar navbar-inverse'>
			<div class='navbar-inner nav-collapse' style="height: auto;">
				<ul id="myTab" class="nav">
					<li class="active"><a
						href="<%=request.getContextPath()%>/welcome/hellow">主页</a></li>
					<li><a href="<%=request.getContextPath()%>/welcome/setting">初始化</a></li>
					<li><a href="#">其他</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>
