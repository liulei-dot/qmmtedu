<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.qmmt.edu.util.*,com.qmmt.edu.pojo.*" %>
<%
List<EduCoursePojo> ywList = (List<EduCoursePojo>)request.getAttribute("ywList");
List<EduCoursePojo> msList = (List<EduCoursePojo>)request.getAttribute("msList");
List<EduCoursePojo> yzList = (List<EduCoursePojo>)request.getAttribute("yzList");
List<EduCoursePojo> yyList = (List<EduCoursePojo>)request.getAttribute("yyList");
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
		<title>公开课</title>
		<link rel="stylesheet" type="text/css" href="css/index.css"/>
		<link rel="stylesheet" type="text/css" href="css/mycss.css"/>
		<style type="text/css">
			.newgroup a{font-size: 12px;}
		</style>
	</head>
	<body style="background: #e5e5e5;">
		<div class="main">
			<!--content START-->			
			<div class="content-group newgroup">
				<h3 style="border-bottom: 1px solid #CCCCCC; line-height: 3rem;">
					<img src="img/1_03.jpg" alt=""  style="width: 2rem;" /> 语文
				  <a href="#" class="pull-right">更多></a>
				</h3>
				<%for(EduCoursePojo eduCoursePojo: ywList){ %>
				<a href="/qmmtedu/course/course_detail.htm?course_id=<%=eduCoursePojo.getId()%>">
				<div class="addborder">
					<div class="prise pull-left" style="width: 29%;">
						<img src="<%=eduCoursePojo.getImgUrl() %>" alt="老师照片" style="max-width: 100%; height: 6rem;" class="pull-left"/>
					</div>
					<div class="more pull-right" style="width: 70%;">
						<p><strong><%=eduCoursePojo.getCourseName() %></strong></p>
						<p><%=DateUtil.getDateStr(eduCoursePojo.getOpenTime(), "MM月dd日") %> <%=DateUtil.getWeekStr(eduCoursePojo.getOpenTime()) %> <%=DateUtil.getDateStr(eduCoursePojo.getOpenTime(), "HH:mm") %>-<%=DateUtil.getDateStr(eduCoursePojo.getCloseTime(), "HH:mm") %></p>
						<p>免费</p>
					</div>
				</div>
				</a>
				<%} %>
			</div>
			<!--content END-->
			<!--美术-->
			<div class="content-group newgroup">
				<h3 style="border-bottom: 1px solid #CCCCCC; line-height: 3rem;">
					<img src="img/1_07.jpg" alt=""  style="width: 2rem;" /> 数学
				  <a href="#" class="pull-right">更多></a>
				</h3>
				<%for(EduCoursePojo eduCoursePojo: msList){ %>
				<a href="/qmmtedu/course/course_detail.htm?course_id=<%=eduCoursePojo.getId()%>">
				<div class="addborder">
					<div class="prise pull-left" style="width: 29%;">
						<img src="<%=eduCoursePojo.getImgUrl() %>" alt="老师照片" style="max-width: 100%; height: 6rem;" class="pull-left"/>
					</div>
					<div class="more pull-right" style="width: 70%;">
						<p><strong><%=eduCoursePojo.getCourseName() %></strong></p>
						<p><%=DateUtil.getDateStr(eduCoursePojo.getOpenTime(), "MM月dd日") %> <%=DateUtil.getWeekStr(eduCoursePojo.getOpenTime()) %> <%=DateUtil.getDateStr(eduCoursePojo.getOpenTime(), "HH:mm") %>-<%=DateUtil.getDateStr(eduCoursePojo.getCloseTime(), "HH:mm") %></p>
						<p>免费</p>
					</div>
				</div>
				</a>
				<%} %>
			</div>
				<!--音乐-->
				<div class="content-group newgroup">
				<h3 style="border-bottom: 1px solid #CCCCCC; line-height: 3rem;">
					<img src="img/1_07.jpg" alt=""  style="width: 2rem;" /> 英语
				  <a href="#" class="pull-right">更多></a>
				</h3>
				<%if(yyList != null) for(EduCoursePojo eduCoursePojo: yyList){ %>
				<a href="/qmmtedu/course/course_detail.htm?course_id=<%=eduCoursePojo.getId()%>">
				<div class="addborder">
					<div class="prise pull-left" style="width: 29%;">
						<img src="<%=eduCoursePojo.getImgUrl() %>" alt="老师照片" style="max-width: 100%; height: 6rem;" class="pull-left"/>
					</div>
					<div class="more pull-right" style="width: 70%;">
						<p><strong><%=eduCoursePojo.getCourseName() %></strong></p>
						<p><%=DateUtil.getDateStr(eduCoursePojo.getOpenTime(), "MM月dd日") %> <%=DateUtil.getWeekStr(eduCoursePojo.getOpenTime()) %> <%=DateUtil.getDateStr(eduCoursePojo.getOpenTime(), "HH:mm") %>-<%=DateUtil.getDateStr(eduCoursePojo.getCloseTime(), "HH:mm") %></p>
						<p>免费</p>
					</div>
				</div>
				</a>
				<%} %>
			</div>
			<!--音乐
			<div class="content-group newgroup">
				<h3 style="border-bottom: 1px solid #CCCCCC; line-height: 3rem;">
					<img src="img/1_11.jpg" alt=""  style="width: 2rem;" /> 数学
				  <a href="#" class="pull-right">更多></a>
				</h3>
				<div class="addborder">
					<div class="prise pull-left" style="width: 26%;">
						<img src="../../../img/xin_08.jpg" alt="老师照片" class="pull-left"/>
					</div>
					<div class="more pull-right" style="width: 72%;">
						<p>课程目录课程目录</p>
						<p>2月5日 19:00-20:00</p>
						<p>免费</p>
					</div>
				</div>
				<div class="addborder">
					<div class="prise pull-left" style="width: 26%;">
						<img src="../../../img/xin_08.jpg" alt="课程主题相关照片" class="pull-left"/>
					</div>
					<div class="more pull-right" style="width: 72%;">
						<p>课程目录课程目录</p>
						<p>2月5日 19:00-20:00</p>
						<p>免费</p>
					</div>
				</div>
			</div>-->
			<!--英语
			<div class="content-group newgroup">
				<h3 style="border-bottom: 1px solid #CCCCCC; line-height: 3rem;">
					<img src="img/1_13.jpg" alt=""  style="width: 2rem;" /> 英语
				  <a href="#" class="pull-right">更多></a>
				</h3>
				<div class="addborder">
					<div class="prise pull-left" style="width: 26%;">
						<img src="../../../img/xin_08.jpg" alt="老师照片" class="pull-left"/>
					</div>
					<div class="more pull-right" style="width: 72%;">
						<p>课程目录课程目录</p>
						<p>2月5日 19:00-20:00</p>
						<p>免费</p>
					</div>
				</div>
				<div class="addborder">
					<div class="prise pull-left" style="width: 26%;">
						<img src="../../../img/xin_08.jpg" alt="课程主题相关照片" class="pull-left"/>
					</div>
					<div class="more pull-right" style="width: 72%;">
						<p>课程目录课程目录</p>
						<p>2月5日 19:00-20:00</p>
						<p>免费</p>
					</div>
				</div>
			</div>-->
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
