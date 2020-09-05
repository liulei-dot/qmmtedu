<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.qmmt.edu.util.*,com.qmmt.edu.pojo.*" %>
<%
EduCoursePojo eduCoursePojo = (EduCoursePojo)request.getAttribute("eduCourse");

List<EduCoursePojo> cList = (List<EduCoursePojo>)request.getAttribute("tList");

Integer eduCourseNum = (Integer)request.getAttribute("eduCourseNum");

String openId = (String)request.getAttribute("openId");
String should_pay = (String)request.getAttribute("should_pay");
Long curr_sc = 0L;
%>
<html>
	<head>
		<meta charset="utf-8" />
		<title>课程：<%=eduCoursePojo.getCourseName() %></title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="sharecontent" data-msg-img="<%=eduCoursePojo.getImgUrl() %>" data-msg-title="课程分享：<%=eduCoursePojo.getCourseName() %>" data-msg-content="你的简介"/>
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
	<body style="background: #e5e5e5;">
		<div class="main">
			<!--头部内容 START-->
			<div class="graybg" style="padding-top: 0;height: 200px;">
				<img src="<%=eduCoursePojo.getImgUrl() %>" alt="老师照片/课程主题相关图片" style="width: 100%;height:100%" />
			</div>
			<!--头部内容 END-->
			<div class="newcenter meal-tab text-center">
				<ul id="tabfirst">
					<li class="on">课程目录</li>
					<li>课程介绍</li>
					<li>认识老师</li>
				</ul>
			</div>
			<!--contentone START-->
			<div class="contentfirst content">
			<%int i=1; 
			for(EduSubcoursePojo eduSubcoursePojo : eduCoursePojo.getEduSubcourseList()){%>
				<div class="content-group">
				<%
							String status = "已结束";
							if(eduSubcoursePojo.getCloseTime().before(new Date()))
								status = "已结束";
							else if(eduSubcoursePojo.getRoomId() != null && DateUtil.isSameDay(eduSubcoursePojo.getOpenTime(), new Date())){
								//if(eduSubcoursePojo.getStudentCode()!= null )
								//	status = "<a href=\"http://www.baijiacloud.com/web/room/quickenter?code="+eduSubcoursePojo.getStudentCode()+"\"><font color=\"#FF0000\">参加码:"+eduSubcoursePojo.getStudentCode()+"   正在直播</font></a>";
								//else
									//System.out.println(eduSubcoursePojo.getOpenTime().getDate());
									//System.out.println(new Date().getDate());
									curr_sc = eduSubcoursePojo.getId();
									status = "正在直播";
							}else 
								status = "待上课";
							//判断交费的是否能回看
							if(!StringUtil.isEmpty(eduSubcoursePojo.getBackUrl()) && "0".equals(should_pay)){
								status = "点击回看";
							}
							
							%>
							<%if(eduSubcoursePojo.getStudentCode()!= null && status.equals("正在直播")){ %>
							<a href="/qmmtedu/course/enter_room.htm?sc=<%=curr_sc%>&student_code=<%=eduSubcoursePojo.getStudentCode()%>">
							<%}else if(status.equals("点击回看")){ %>
							<a href="/qmmtedu/course/play_back.htm?course_id=<%=eduCoursePojo.getId() %>&subcourse_id=<%=eduSubcoursePojo.getId()%>">
							<%}%>
					<div class="form-groups meal-groups">
						<label class="col-lg-1 pull-left">第<%=i %>节</label>
						<div class="col-lg-2 pull-left">
							<div class="lefbox pull-left">
								<p style="margin-bottom: 0.3rem;"><strong><%=eduSubcoursePojo.getSubName() %></strong></p>
								<p><%=eduCoursePojo.getEduTeacher().getTecherName() %>|<%if(eduCoursePojo.getCourseType() == null ){ %><%=DateUtil.getDateStr(eduSubcoursePojo.getOpenTime(), "MM月dd日") %> <%=DateUtil.getWeekStr(eduSubcoursePojo.getOpenTime()) %> <br/><%=DateUtil.getDateStr(eduSubcoursePojo.getOpenTime(), "HH:mm") %>-<%=DateUtil.getDateStr(eduSubcoursePojo.getCloseTime(), "HH:mm") %><%} %></p>
								<%if(eduSubcoursePojo.getStudentCode()!= null && status.equals("正在直播")){ %>
								<p style="margin-bottom: 0.3rem;"><font color="#FF0000">参加码: <%=eduSubcoursePojo.getStudentCode()%></font></p>
								<%} %>
							</div>
							
							<div class="rightbox pull-right" style="color: #01dead;float: right; position: absolute;right: 0;"><%=status %></div>
						</div>
					</div>
					<%if((eduSubcoursePojo.getStudentCode()!= null && status.equals("正在直播")) || status.equals("点击回看")){ %></a><%} %>
				</div>	
				<%i++;} %>	
				<!--底部元素-->
				<% 
			//名额==0 报名已截止
			//名额>0 && 免费---->报名
			//名额>0 && 不免费---->缴费
			if(openId != null){
			if("1".equals(should_pay) && (eduCourseNum == null || eduCourseNum == 0 || eduCoursePojo.getCloseTime().before(new Date())) ){
			%>		
				<!-- 						报名已截止 -->	
				<a href="/qmmtedu/wxpay/pre_pay.htm?course_id=<%=eduCoursePojo.getId()%>">
				<div class="content-group" style="width: 100%;background: #01DEAD;">
					<div class="form-groups meal-groups text-center" style="line-height: 3rem;font-size: 2rem;" >
						去报名缴费<%=eduCoursePojo.getCourseFee().floatValue() %>元
					</div>
				</div>	
				</a>
				<%} else if("1".equals(should_pay) && eduCourseNum > 0 && eduCoursePojo.getCourseFee().floatValue()==0){%>
				<a href="/qmmtedu/wxpay/pre_pay.htm?course_id=<%=eduCoursePojo.getId()%>">
				<div class="content-group" style="width: 100%;background: #01DEAD;">
					<div class="form-groups meal-groups text-center" style="line-height: 3rem;font-size: 2rem;" >
						免费报名
					</div>
				</div>	
				</a>
				<%} else if("1".equals(should_pay) && eduCourseNum > 0 && eduCoursePojo.getCourseFee().floatValue()>0){%>
				<a href="/qmmtedu/wxpay/pre_pay.htm?course_id=<%=eduCoursePojo.getId()%>">
				<div class="content-group" style="width: 100%;background: #01DEAD;">
					<div class="form-groups meal-groups text-center" style="line-height: 3rem;font-size: 2rem;" >
						去报名缴费<%=eduCoursePojo.getCourseFee().floatValue() %>元
					</div>
				</div>	
				</a>
				<%} else if("0".equals(should_pay)){%>
				<div class="content-group" style="width: 100%;background: #01DEAD;">
					<div class="form-groups meal-groups text-center" style="line-height: 3rem;font-size: 2rem;" >
						课程已报名
					</div>
				</div>	
				<%} %>
			<%}else{ %>	
			<div class="addborder" style="padding: 1rem;text-align: center;border: 0; margin-bottom: 1rem;">
				您可以关注"园众学校"公众号进行报名<br class="clear" />
				<img src="http://edu.gdzqyz.cn/qmqrcode.jpeg" alt="老师照片/课程主题相关图片" style="width: 30%;" />
			</div>
			
			<form id="formid" method = 'post'  action = '/qmmtedu/course/enter_room.htm'  >
			<div class="addborder" style="padding: 1rem;text-align: center;border: 0; margin-bottom: 1rem;">
				输入您的昵称<br class="clear" />
				<input type="text" name="nickname" id="asd" class="form-control btn-block " placeholder="昵称" />
				<input type="hidden" name="sc" value="<%=curr_sc%>"/>
				输入您在微信公众号的参加码，<a href="http://edu.gdzqyz.cn/intro.jpeg">获取方法</a><br class="clear" />
				<input type="text" name="student_code" id="open_code" class="form-control btn-block " placeholder="输入参加码进入直播课堂" />
			</div>
			</form>
			<a href="javascript:open();">
			<div class="content-group" style="width: 100%;background: #01DEAD;">
				<div class="form-groups meal-groups text-center" style="line-height: 3rem;" >
					进入课堂
				</div>
			</div>	
			</a>
			<%} %>	
	
			</div>		
			<!--结算-->	
			<!--contentone END-->
			<!--contenttwo START-->
			<div class="contentfirst">
				<div class="content-group">
					<div class="form-groups meal-groups" style="height: auto;line-height: 2.6rem;">
						<p><strong>课程名称：</strong> <%=eduCoursePojo.getCourseName() %>  <span class="pull-right" style="color: #01DEAD;"><%if(eduCoursePojo.getCourseFee().floatValue()==0){ %>公开课<% }else{ %>系列课<% }%></span></p>
						<p><strong>主讲老师：</strong> <%=eduCoursePojo.getEduTeacher().getTecherName() %>  </p>
						<%if(eduCoursePojo.getCourseType() == null ){ %>
						<p><strong>课程时间：</strong> <%=DateUtil.getDateStr(eduCoursePojo.getOpenTime(), "yyyy-MM-dd") %>至<%=DateUtil.getDateStr(eduCoursePojo.getOpenTime(), "yyyy-MM-dd") %></p>
						<%} %>
						<p><%=eduCoursePojo.getInfo()%></p>
					</div>
				</div>	
<!-- 				<div class="content-group" style="margin-top: 1rem;">
					<div class="form-groups meal-groups" style="height: auto;line-height: 2.6rem;">
					     <a href="#">课程第一节介绍 </a><br />
					     <a href="#">课程第二节介绍 </a><br />			     
					     <a href="#">课程第三节介绍 </a>
					</div>
				</div>	 -->
				<!--底部元素-->
				<% 
			//名额==0 报名已截止
			//名额>0 && 免费---->报名
			//名额>0 && 不免费---->缴费
			if(openId != null){
			if("1".equals(should_pay) && (eduCourseNum == null || eduCourseNum == 0)){
			%>
				<!-- 						报名已截止 -->	
				<a href="/qmmtedu/wxpay/pre_pay.htm?course_id=<%=eduCoursePojo.getId()%>">
				<div class="content-group" style="width: 100%;background: #01DEAD;">
					<div class="form-groups meal-groups text-center" style="line-height: 3rem;font-size: 2rem;" >
						去报名缴费<%=eduCoursePojo.getCourseFee().floatValue() %>元
					</div>
				</div>	
				</a>
				<%} else if("1".equals(should_pay) && eduCourseNum > 0 && eduCoursePojo.getCourseFee().floatValue()==0){%>
				<a href="/qmmtedu/wxpay/pre_pay.htm?course_id=<%=eduCoursePojo.getId()%>">
				<div class="content-group" style="width: 100%;background: #01DEAD;">
					<div class="form-groups meal-groups text-center" style="line-height: 3rem;font-size: 2rem;" >
						免费报名
					</div>
				</div>	
				</a>
				<%} else if("1".equals(should_pay) && eduCourseNum > 0 && eduCoursePojo.getCourseFee().floatValue()>0){%>
				<a href="/qmmtedu/wxpay/pre_pay.htm?course_id=<%=eduCoursePojo.getId()%>">
				<div class="content-group" style="width: 100%;background: #01DEAD;">
					<div class="form-groups meal-groups text-center" style="line-height: 3rem;font-size: 2rem;" >
						去报名缴费<%=eduCoursePojo.getCourseFee().floatValue() %>元
					</div>
				</div>	
				</a>
				<%} else if("0".equals(should_pay)){%>
				<div class="content-group" style="width: 100%;background: #01DEAD;">
					<div class="form-groups meal-groups text-center" style="line-height: 3rem;font-size: 2rem;" >
						课程已报名
					</div>
				</div>	
				<%} %>
			<%} %>	
			
			</div>		
			<!--contenttwo END-->	
			<!--contentthree START-->
			<div class="contentfirst">
				<div class="content-group">
					<div class="form-groups meal-groups" style="height: auto;">
						<div class="addborder" style="border: 1px;">
							<div style="width: 100%;margin: 0 auto;">
								<div class="prise pull-left" style="width: 32%;margin-right: 0rem;">
									<img src="<%=eduCoursePojo.getEduTeacher().getHeaderUrl() %>" alt="老师照片" width="100%"class="pull-left"/>
								</div>
								<div class="more pull-right" style="width: 60%;">
									<p><h3><%=eduCoursePojo.getEduTeacher().getTecherName() %></h3></p>
									<p>
									<%if(eduCoursePojo.getEduTeacher().getId() == 1){ %>
									毕业于山东大学汉语言文学专业
									<%} else if(eduCoursePojo.getEduTeacher().getId() == 2){ %>
									原名赵鑫 毕业于吉林省动画学院 
									<%}else{ %>
									
									<%} %></p>
								</div>
							</div>
						</div>
					</div>
					<!--老师简介-->
					<div class="addborder" style="margin:10px;text-indent: 2rem;border: 2px;">
						<%=eduCoursePojo.getEduTeacher().getInfo() %>	
					</div>
				</div>	
				<!--美术-->
			<div class="content-group newgroup" style="margin-top: 1rem;">
				<h3 style="border-bottom: 1px solid #CCCCCC; line-height: 3rem;">
					Ta的其他课程
				  <a href="#" class="pull-right">更多></a>
				</h3>
				<%for(EduCoursePojo eduCoursePojo1: cList){
					if(eduCoursePojo1.getId().longValue() != eduCoursePojo.getId().longValue()){%>
				<a href="/qmmtedu/course/course_detail.htm?course_id=<%=eduCoursePojo1.getId()%>">
				<div class="addborder">
					<div class="prise pull-left" style="width: 29%;">
						<img src="<%=eduCoursePojo1.getImgUrl() %>" alt="老师照片" style="max-width: 100%; height: 6rem;" class="pull-left"/>
					</div>
					<div class="more pull-right" style="width: 70%;">
						<p><strong><%=eduCoursePojo1.getCourseName() %></strong></p>
						<p><%=DateUtil.getDateStr(eduCoursePojo1.getOpenTime(), "MM月dd日") %> <%=DateUtil.getDateStr(eduCoursePojo1.getOpenTime(), "HH:mm") %>-<%=DateUtil.getDateStr(eduCoursePojo1.getCloseTime(), "HH:mm") %></p>
						<p><%=eduCoursePojo1.getCourseFee().toString() %>元</p>
					</div>
				</div>
				</a>
				<%} }%>
			</div>
			<!--底部元素-->
			<!--底部元素-->
				<% 
			//名额==0 报名已截止
			//名额>0 && 免费---->报名
			//名额>0 && 不免费---->缴费
			if(openId != null){
			if("1".equals(should_pay) && (eduCourseNum == null || eduCourseNum == 0)){
			%>
				<!-- 						报名已截止 -->	
				<a href="/qmmtedu/wxpay/pre_pay.htm?course_id=<%=eduCoursePojo.getId()%>">
				<div class="content-group" style="width: 100%;background: #01DEAD;">
					<div class="form-groups meal-groups text-center" style="line-height: 3rem;font-size: 2rem;" >
						去报名缴费<%=eduCoursePojo.getCourseFee().floatValue() %>元
					</div>
				</div>	
				</a>	
				<%} else if("1".equals(should_pay) && eduCourseNum > 0 && eduCoursePojo.getCourseFee().floatValue()==0){%>
				<a href="/qmmtedu/wxpay/pre_pay.htm?course_id=<%=eduCoursePojo.getId()%>">
				<div class="content-group" style="width: 100%;background: #01DEAD;">
					<div class="form-groups meal-groups text-center" style="line-height: 3rem;font-size: 2rem;" >
						免费报名
					</div>
				</div>	
				</a>
				<%} else if("1".equals(should_pay) && eduCourseNum > 0 && eduCoursePojo.getCourseFee().floatValue()>0){%>
				<a href="/qmmtedu/wxpay/pre_pay.htm?course_id=<%=eduCoursePojo.getId()%>">
				<div class="content-group" style="width: 100%;background: #01DEAD;">
					<div class="form-groups meal-groups text-center" style="line-height: 3rem;font-size: 2rem;" >
						去报名缴费<%=eduCoursePojo.getCourseFee().floatValue() %>元
					</div>
				</div>	
				</a>
				<%} else if("0".equals(should_pay)){%>
				<div class="content-group" style="width: 100%;background: #01DEAD;">
					<div class="form-groups meal-groups text-center" style="line-height: 3rem;font-size: 2rem;" >
						课程已报名
					</div>
				</div>	
				<%} %>
			<%} %>	
			
	
				
			<!--contentthree END-->			
		</div>
		<script src="js/jquery-1.10.2.min.js"></script>		
		<script type="text/javascript">
			$(function(){
				$("#tabfirst li").each(function(index){
					var liNode=$(this);
					$(this).click(function(){
						timeoutid=setTimeout(function(){//增加延时操作时间						
						$("div.content").removeClass("content");//鼠标移到第一个li标签，内容一消失
						$("#tabfirst li.on").removeClass("on");//第一个标签的背景色改变
						$("div.contentfirst").eq(index).addClass("content");//显示内容
						liNode.addClass("on");	//给li标签添加背景色
						},300);						
					})/*.mouseout(function(){
						clearTimeout(timeoutid);//鼠标移开，清除延时效果
					});*/
				});
			});
			
			//ajax请求
				//http://www.baijiacloud.com/web/room/quickenter?code=a0hykt
			function open(){
				var openid=$("#open_code").val();
				//window.location.href='http://www.baijiacloud.com/web/room/quickenter?code='+openid;
				var nk = encodeURI("测试");
				//window.location.href='/qmmtedu/course/enter_room.json?nickname='+nk+'"&student_code='+openid;
				document.getElementById("formid").submit();
			}
		</script>
	</body>
</html>

