<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String wxpaydata = (String)request.getAttribute("wxpaydata");

%>
<!DOCTYPE html>
<html>
	<head>
		<title>课程预付</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="format-detection" content="telephone=no">
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
		        	   
		        	   window.location.href='';
		        
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
						alert("成功")
					}else{
						//alert("err")
					}
	 			}
	 		});
		}
	    
	</script>
	</head>

	<body>
		<header class="header">
			<a>课程预付</a>
		</header>

		<dl class="box stu-box">
			<dt class="border">
				<span>课程标题</span>美白牙膏
			</dt>
			<dt class="border">
			<input type="hidden" name="calendar" id="oid" value="<%=request.getAttribute("order_id")%>"/>
			<input type="text" name="calendar" id="mobile"/>
				<span></span> 
			</dt>

			<dt class="border">
				<span>预付金额 </span> <span style="color: red;"><b>￥0.10</b></span>
			</dt>


			<dt class="border">
				<span></span> 
			</dt>
			
		</dl>
		<a href="javascript:pay();" class="bt detail-bt">立即支付</a>
		<a class="toTop"> </a>

	</body>
</html>