<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*,com.qmmt.edu.util.*,com.qmmt.edu.pojo.*,com.qmmt.edu.persistence.po.*" %>
<%
List<WeixinUserinfo> list = (List<WeixinUserinfo>)request.getAttribute("commlist");
String qrurl = (String)request.getAttribute("qrurl");
%>
<html>
	<head>
		<meta charset="utf-8" />
		<title>我的好友</title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="format-detection" content="telephone=no">
		<link rel="stylesheet" type="text/css" href="../course/css/index.css"/>
		<link rel="stylesheet" type="text/css" href="../course/css/mycss.css"/>
		<link href="https://weui.io/weui.css" rel="stylesheet" type="text/css" />
		<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<script src="js/jquery-2.1.4.min.js"></script>
		<script src="js/mobiscroll_date.js" charset="gb2312"></script> 
        <script src="js/mobiscroll.js"></script> 	
		<script src="../js/jquery/jquery.form.js"></script>

		<style>
			.content-group{margin-top: 0rem;}
			.form-control {
    display: block;
    width: 60%;
    height: 34px;
    padding: 6px 12px;
    font-size: 14px;
    line-height: 1.42857143;
    color: #555;
    background-color: #fff;
    background-image: none;
    border: 1px solid #ccc;
    margin-top: 0.3rem;
    margin-left: 20%;}
		</style>
	</head>
	<body style="background: #e5e5e5;">
		<div class="main">
			<!--contentthree START-->
			<div class="contentfirst content">
				<div class="content-group">
				<!--老师简介-->
					<div class="addborder" style="padding: 1rem;text-align: center;border: 0; margin-bottom: 7rem;">
						请用以下二维码推荐给你的好友<br class="clear" />
						<img src="<%=qrurl %>" alt="" width="60%" hight="60%" />
					</div>
					
					
					
					
					
					<div class="form-groups meal-groups" style="height: auto;">
						<%for(WeixinUserinfo weixinUserinfo :list){ %>
						<div class="addborder" style="">
							<div class="prise pull-left" style="width: 20%;">
								<img src="<%=weixinUserinfo.getHeadimgurl() %>" alt="老师照片" width="50"  class="pull-left"/>
							</div>
							<div class="more pull-right" style="width: 80%;">
								<p><strong>昵称:<%=weixinUserinfo.getNickname() %></strong></p>
								<p><span>邀请时间 <%=DateUtil.getDateStr(weixinUserinfo.getSubscribeTime(), "MM月dd日") %></span></p>
							</div>
						</div>
						<%} %>
					</div>
					
				</div>	
				
			</div>		
			<!--contentthree END-->			
		</div>
		<script src="../course/js/jquery-1.10.2.min.js"></script>
		
	</body>
</html>
