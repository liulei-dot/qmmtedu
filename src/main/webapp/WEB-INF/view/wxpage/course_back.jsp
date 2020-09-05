<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.qmmt.edu.util.*,com.qmmt.edu.pojo.*" %>
<%
String back_url = (String)request.getAttribute("back_url");
%>
<html>
<head>
<title>园众学校课程回播</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="overview &amp; stats" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" type="text/css" href="css/index.css"/>
<link rel="stylesheet" type="text/css" href="css/mycss.css"/>
<style>
	.content-group{margin-top: 0rem;}
	.newgroup a{font-size: 12px;}
</style>
</head>
<body>

<%if(back_url!= null) {%>
<IFRAME id="test" name="test" frameBorder=0 scrolling=no src="<%=back_url %>" width="100%" height="100%"></IFRAME>
<%}else{ %>
当前课程无回放，感谢您对我们的支持！
<%} %>

</body>
</html>