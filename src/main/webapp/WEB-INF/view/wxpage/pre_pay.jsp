<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*,com.qmmt.edu.util.*,com.qmmt.edu.pojo.*" %>
<%
EduCoursePojo eduCoursePojo = (EduCoursePojo)request.getAttribute("eduCourse");
String wxpaydata = (String)request.getAttribute("wxpaydata");
%>
<html>
	<head>
		<meta charset="utf-8" />
		<title><%=eduCoursePojo.getCourseName() %></title>
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
		<script type="text/javascript">
	 
// 	var appId = urlparameter("appId");
// 	var timeStamp = urlparameter("timeStamp");
// 	var nonceStr = urlparameter("nonceStr");
// 	var pg = urlparameter("pg");
// 	var signType = urlparameter("signType");
// 	var paySign = urlparameter("paySign");
	
	
	  function onBridgeReady(){
		 
		   WeixinJSBridge.invoke(
		       'getBrandWCPayRequest', 
		           <%=wxpaydata%>
		       ,
		       
		       function(res){     
		           if(res.err_msg == "get_brand_wcpay_request:ok" ) {
		        	   uod();
		        	   
		        	   window.location.href='/qmmtedu/course/course_detail.htm?course_id=<%=eduCoursePojo.getId()%>';
		        
		        	   //alert("支付成功");
		           }     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。 
		       }
		   ); 
		}
	  
	  
	    function pay(){
	    	
			if (typeof WeixinJSBridge == "undefined"){
			   if( document.addEventListener ){
			       document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
			   }else if (document.attachEvent){
			       document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
			       document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
			   }
			}else{
			   onBridgeReady();
			} 
	    	
	    }
	    
	    function uod(){
			
			var datas={
					oid:$("#oid").val(),
					mobile:$("#mobile").val()
			}
			
			$.ajax({
	 			url:"/qmmtedu/wxpay/pupt.htm",
	 			type:'POST',
	 			async:false,
	 			dataType:'json',
	 			data:datas,
	 			success:function(data,textStatus,XMLHttpRequest){
	 				datas = data.status.code
					if(datas==0){
						//alert("成功")
					}else{
						//alert("err")
					}
	 			}
	 		});
		}
	    
	</script>
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
					<div class="form-groups meal-groups" style="height: auto;">
						<div class="addborder" style="">
							<div class="prise pull-left" style="width: 26%;">
								<img src="<%=eduCoursePojo.getImgUrl() %>" alt="老师照片" width="90" hight="90" class="pull-left"/>
							</div>
							<div class="more pull-right" style="width: 72%;">
								<p><strong>课程:<%=eduCoursePojo.getCourseName() %></strong></p>
								<p><span><%=eduCoursePojo.getCourseFee().toString() %>元</span></p>
							</div>
						</div>
					</div>
					<!--老师简介-->
					<div class="addborder" style="padding: 1rem;text-align: center;border: 0; margin-bottom: 7rem;">
						输入手机号，方便我们发手机开课提醒<br class="clear" />
						<input type="hidden" name="calendar" id="oid" value="<%=request.getAttribute("order_id")%>"/>
						<input type="text" name="" id="mobile" class="form-control btn-block " placeholder="选填" />
					</div>
				</div>	
				<!--底部元素-->
				<a href="javascript:pay();" class="bt detail-bt">
				<div class="content-group" style="width: 100%;background: #01DEAD;">
					<div class="form-groups meal-groups text-center" style="line-height: 3rem;" >
						立即支付
					</div>
				</div>
				</a>	
			</div>		
			<!--contentthree END-->			
		</div>
		<script src="../course/js/jquery-1.10.2.min.js"></script>
		<script type="text/javascript">
			$(function(){

			});
			
			//ajax请求
			function bao(){
			
			var datas={
					oid:$("#oid").val(),
					mobile:$("#mobile").val()
			}
			
			$.ajax({
	 			url:"/qmmtedu/wxpay/pupt.htm",
	 			type:'POST',
	 			async:false,
	 			dataType:'json',
	 			data:datas,
	 			success:function(data,textStatus,XMLHttpRequest){
	 				datas = data.status.code
					if(datas==0){
						alert("报名成功");
						 window.location.href='/qmmtedu/course/course_detail.htm?course_id=<%=eduCoursePojo.getId()%>';
					}else{
						//alert("err")
					}
	 			}
	 		});
		}	
		</script>
	</body>
</html>
