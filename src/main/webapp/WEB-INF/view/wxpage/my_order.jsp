<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.math.*,com.qmmt.edu.util.*,com.qmmt.edu.pojo.*" %>
<%
List<EduCourseOrderPojo> olist =  (List<EduCourseOrderPojo>)request.getAttribute("olist");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="refresh" content="120" >
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="format-detection" content="telephone=no">
		<title>我的课程</title>
		<link rel="stylesheet" type="text/css" href="css/index.css"/>
		<link rel="stylesheet" type="text/css" href="css/mycss.css"/>
		
	</head>
	<body style="background: #e5e5e5;">
		<div class="main">
			<!--content START-->			
			<div class="content-group newgroup">
				<h3 style="border-bottom: 1px solid #CCCCCC; line-height: 3rem;">
					已购课程
				  
				</h3>
				<%if(olist != null && olist.size()>0){
					for(EduCourseOrderPojo eduCourseOrderPojo :olist ){ %>
				<a href="/qmmtedu/course/course_detail.htm?course_id=<%=eduCourseOrderPojo.getCourseId()%>">
				<div class="addborder">
					<div class="prise pull-left" style="width: 26%;">
						<img src="<%=eduCourseOrderPojo.getImgUrl() %>" alt="老师照片" width="100" hight="100" class="pull-left"/>
					</div>
					<div class="more pull-right" style="width: 72%;">
						<p><strong><%=eduCourseOrderPojo.getCourseName() %></strong></p>
						<p><%=DateUtil.getDateStr(eduCourseOrderPojo.getOpenTime(), "MM月dd日") %> <%=DateUtil.getWeekStr(eduCourseOrderPojo.getOpenTime()) %> <%=DateUtil.getDateStr(eduCourseOrderPojo.getOpenTime(), "HH:mm") %>-<%=DateUtil.getDateStr(eduCourseOrderPojo.getCloseTime(), "HH:mm") %></p>
						<%if(eduCourseOrderPojo.getReceivableAmount().compareTo(BigDecimal.ZERO) == 0){ %>
						<p>免费体验</p>
						<%}else if(eduCourseOrderPojo.getRealpayAmount() == null || eduCourseOrderPojo.getRealpayAmount().compareTo(BigDecimal.ZERO) == 0){ %>
						<p>待支付</p>
						<%} else if(eduCourseOrderPojo.getRealpayAmount() != null){%>
						<p><%=eduCourseOrderPojo.getRealpayAmount().toString() %>元</p>
						<%} %>
					</div>
					<%if(eduCourseOrderPojo.getRoomId() != null){
							if(DateUtil.isSameDay(eduCourseOrderPojo.getOpenTime(), new Date())){%>
					
					<div class="rightbox pull-right" style="color: #01dead;float: right; position: absolute;right: 0;">正在直播</div>
					<%}else{ %>
					<div class="rightbox pull-right" style="color: #01dead;float: right; position: absolute;right: 0;">待上课</div>
					
					<%} }%>
				</div>
				</a>
				<%} }else{%>
				
				<div class="graybg" style="text-align:center">
				<img src="/qmmtedu/course/img/noorder.png" alt="无订单" style="width: 30%;" />
				<br/>
				暂无订单
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				</div>
				<%} %>
			</div>
			
		</div>
		<script src="js/jquery-1.10.2.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
    var isOpen = 0;
    //全局变量，判断是否已经打开弹出框
    $(".btnDel").click(function() {
        //$(".box-mask").css({"display":"block"});
        $(".box-mask").fadeIn(500);
        center($(".box"));
        //载入弹出窗口上的按钮事件
        checkEvent($(this).parent(), $(".btnSure"), $(".btnCancel"));
    });
    function center(obj) {
        //obj这个参数是弹出框的整个对象
        var screenWidth = $(window).width(), screenHeigth = $(window).height();
        //获取屏幕宽高
        var scollTop = $(document).scrollTop();
        //当前窗口距离页面顶部的距离
        var objLeft = (screenWidth - obj.width()) / 2;
        ///弹出框距离左侧距离
        var objTop = (screenHeigth - obj.height()) / 2 + scollTop;
        ///弹出框距离顶部的距离
        obj.css({
            left:objLeft + "px",
            top:objTop + "px"
        });
        obj.fadeIn(500);
        //弹出框淡入
        isOpen = 1;
        //弹出框打开后这个变量置1 说明弹出框是打开装填
        //当窗口大小发生改变时
        $(window).resize(function() {
            //只有isOpen状态下才执行
            if (isOpen == 1) {
                //重新获取数据
                screenWidth = $(window).width();
                screenHeigth = $(window).height();
                var scollTop = $(document).scrollTop();
                objLeft = (screenWidth - obj.width()) / 2;
                var objTop = (screenHeigth - obj.height()) / 2 + scollTop;
                obj.css({
                    left:objLeft + "px",
                    top:objTop + "px"
                });
                obj.fadeIn(500);
            }
        });
        //当滚动条发生改变的时候
        $(window).scroll(function() {
            if (isOpen == 1) {
                //重新获取数据
                screenWidth = $(window).width();
                screenHeigth = $(window).height();
                var scollTop = $(document).scrollTop();
                objLeft = (screenWidth - obj.width()) / 2;
                var objTop = (screenHeigth - obj.height()) / 2 + scollTop;
                obj.css({
                    left:objLeft + "px",
                    top:objTop + "px"
                });
                obj.fadeIn(500);
            }
        });
    }
    //导入两个按钮事件 obj整个页面的内容对象，obj1为确认按钮，obj2为取消按钮
    function checkEvent(obj, obj1, obj2) {
        //确认后删除页面所有东西
        obj1.click(function() {
            //移除所有父标签内容
            //obj.remove();
            //调用closed关闭弹出框
            closed($(".box-mask"), $(".box"));
        });
        //取消按钮事件
        obj2.click(function() {
            //调用closed关闭弹出框
            closed($(".box-mask"), $(".box"));
        });
    }
    //关闭弹出窗口事件
    function closed(obj1, obj2) {
        obj1.fadeOut(500);
        obj2.fadeOut(500);
        isOpen = 0;
    }
});
		</script>
	</body>
</html>