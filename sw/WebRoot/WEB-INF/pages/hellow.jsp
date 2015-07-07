<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%-- <base href="<%=basePath%>"> --%>
		<!-- 引入已经压缩的js文件 -->
	 <script type="text/javascript" src="/js/jquery-2.1.1.js" ></script>  
<!-- 	 <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script> -->
		<style type="text/css">
	        .red
	        {
	            background-color: Red;
	        }
	        .green
	        {
	            background-color: Green;
	        }
	        .blue
	        {
	         background-color: Blue;
	        }
    	</style>
		<title>My JSP 'helloWorld.jsp' starting page</title>
<script type="text/javascript">
alert(11);
//alert($('#olID').val());
$(document).ready(function(){

	  alert(333);
	});
</script>

	</head>

	<body>
		<h1 class="page-header">
			Message12 : ${message}
		</h1>
		    <form id="form1">
    <div>
        <ol id="olID">
            <li>Sissy.Nong.C</li>
            <li>农凤新</li>
            <li>nongfengxin@gmail.com</li>
        </ol>
    </div>
    </form>
	</body>
</html>
