<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.qmmt.edu.util.*,com.qmmt.edu.pojo.*,com.qmmt.edu.persistence.po.*" %>

<% 
String path = "http://edu.gdzqyz.cn:8081/qmmtedu";
List<WeixinUserinfo>  slist = (List<WeixinUserinfo>)request.getAttribute("slist");

System.out.println(slist.size());

%>


<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>控制台</title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<!-- basic styles -->
		<link href="<%=path %>/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="<%=path %>/assets/css/font-awesome.css" />
		<link rel="stylesheet" href="<%=path %>/assets/css/bootstrap-fileinput.css" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/js/laydate/theme/default/laydate.css"/>		
		<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
		<script src="<%=path %>/assets/js/bootstrap.js"></script>
		<script src="<%=path %>/js/jquery/bootstrap-fileinput.js"></script>
		<script src="<%=path %>/js/laydate/laydate.js"></script>
		<script src="<%=path %>/js/jquery/jquery.cookie.js"></script>
		<script src="<%=path %>/js/jquery/jquery.form.js"></script>
		<!--[if IE 7]>
		  <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
		<![endif]-->

		<!-- page specific plugin styles -->

		<!-- fonts -->
		<!--<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />-->
		<!-- ace styles -->
		<link rel="stylesheet" href="<%=path %>/assets/css/ace.min.css" />
		<!--<link rel="stylesheet" href="../../../assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="../../../assets/css/ace-skins.min.css" />-->
		<!--[if lte IE 8]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->
		<!-- ace settings handler -->
		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

		<!--[if lt IE 9]>
		<script src="assets/js/html5shiv.js"></script>
		<script src="assets/js/respond.min.js"></script>
		<![endif]-->
		<style>
		/*loading-3*/
		
		</style>
	</head>

	<body>
	<div class="breadcrumbs" id="breadcrumbs">
		<script type="text/javascript">
			try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
		</script>

		<ul class="breadcrumb">
			<li>
				<i class="icon-home home-icon"></i>
				<a href="#">首页</a>
			</li>
			<li class="active">系统管理</li>
			<li class="active">报名学生</li>
		</ul><!-- .breadcrumb -->
	</div>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<div class="col-xs-12">
						<div class="col-sm-12" id="my">		
						<form class="form-horizontal" method="get" role="form" id="searchs">
							<div class="col-sm-12">
								<div class="row">
										<div class="container-fluid">
											<div class="row">
											<div class="col-sm-10 text-center line-height-150" style="line-height: 3.5 !important;">报名列表</div>

												<!--table-->
												<table class="table table-bordered">													
													<thead>
														<tr>
															<th>头像</th>
															<th>openid</th>
															<th>昵称</th>
															<th>微信支付单号</th>
															<th>省份</th>
															<th>城市</th>
																								
														</tr>
													</thead>
													<tbody>
													<%
													
													for(WeixinUserinfo userinfo: slist){
													%>
														<tr>
															<td><img src="<%=userinfo.getHeadimgurl()%>" height="30" width="30" /></td>
															<td><%=userinfo.getOpenid()%></td>
															<td><%=userinfo.getNickname()%></td>
															<td><%=userinfo.getRemark()%></td>
															<td><%=userinfo.getProvince()%></td>
															<td><%=userinfo.getCity()%></td>									
														
																
														</tr>
														<%} %>
												
													</tbody>
												</table>
											</div>
										</div>					
								    </form>					    	   
								</div><!-- /.page-header -->						
							</div>
				<div class="space"></div>
				<div class="row">																									
				<hr />								     						     						     		
				<!-- PAGE CONTENT ENDS -->
			</div><!-- /.col -->
		</div><!-- /.row -->
	</div>
	<!--新增数据-->
	</div><!-- /.page-content -->
<!--盲板弹出框-->
<div class="meng mengone" id="meng" style="position: fixed; left: 0;top: 0;width: 100%;height: 100%;background-color: rgba(0,0,0,0.5);z-index: 1040;display: none;">	
<!--添加信息-->
	<div class="col-sm-7 " id="modal" style="float: none;margin:100px auto;z-index: 1001;background-color: #fff; padding: 20px; display: none;">
		<div class="container-fluid">
			<div class="modal-header" style="margin-bottom: 10px;">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
	                  ×
	            </button>
	            <h4 class="modal-title" id="myModalLabel">
	               创建子课程
	            </h4>
	        </div>
			<form class="form-horizontal myform" id="uploadForm" role="form">
			   <div class="form-group">
			      <label for="firstname" class="col-sm-2 control-label">子课名字:</label>
			      <div class="col-sm-5">
			         <input type="text" class="form-control" id="sub_name" name="sub_name"
			            placeholder="请输入子课名字"><input type="hidden" id="cid" name="cid" value="">
			      </div>
			   </div>
			   <div class="form-group">
			      <label  class="col-sm-2 control-label">学生数量:</label>
			      <div class="col-sm-5">
			         <input type="text" class="form-control" id="student_num" name="student_num"
			            placeholder="请输学生数量">
			      </div>
			   </div>
			   <div class="form-group">
			      <label for="firstname" class="col-sm-2 control-label">上课时间:</label>
			      <div class="col-sm-5">      
			        <input type="text" class="form-control layui-input" id="open_time" name="open_time" placeholder="请选择上课时间" lay-key="6">
			      </div>
			   </div>
                <div class="form-group">
			      <label for="firstname" class="col-sm-2 control-label">下课时间:</label>
			      <div class="col-sm-5">
			        <input type="text" class="form-control layui-input" id="close_time" name="close_time" placeholder="请选择下课时间" lay-key="7">
			      </div>
			   </div>	
			   <div class="form-group">
			      <label  class="col-sm-2 control-label">状态:</label>
			      <div class="col-sm-5">
			         <input type="text" class="form-control" id="status" name="status"
			            placeholder="请输入状态">（1有效，0无效）
			      </div>
			   </div>
			   <div class="form-group">
			      <label  class="col-sm-2 control-label">回放地址:</label>
			      <div class="col-sm-5">
			         <input type="text" class="form-control" id="back_url" name="back_url"
			            placeholder="课程结束后输入">
			      </div>
			   </div>		   
			   <div class="form-group">
			      <div class="col-sm-offset-2 col-sm-3">
			         <button type="button" class="btn btn-default closeBT">取消</button>
			      </div>
			      <div class="col-sm-offset-2 col-sm-3">
			         <button type="button" class="btn btn-default btnAdd">保存</button>
			      </div>
			   </div>
			</form>
		</div>
	</div><!--添加信息-->
	
	<!--修改信息-->
	<div class="col-sm-7 modaltwo" id="modal" style="float: none;margin:100px auto;z-index: 1001;background-color: #fff; padding: 20px; display: none;">
		<div class="container-fluid">
			<div class="modal-header" style="margin-bottom: 10px;">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
	                  ×
	            </button>
	            <h4 class="modal-title" id="myModalLabel">
	               修改子课程
	            </h4>
	        </div>
			<form class="form-horizontal myforms" id="uploadForm4up" role="form">
			   <div class="form-group">
			      <label for="firstname" class="col-sm-2 control-label">子课名字:</label>
			      <div class="col-sm-5">
			         <input type="text" class="form-control" id="sub_name" name="sub_name"
			            placeholder="请输入子课名字"><input type="hidden" id="sid" name="sid" value="">
			      </div>
			   </div>
			   <div class="form-group">
			      <label  class="col-sm-2 control-label">学生数量:</label>
			      <div class="col-sm-5">
			         <input type="text" class="form-control" id="student_num" name="student_num"
			            placeholder="请输学生数量">
			      </div>
			   </div>
			   <div class="form-group">
			      <label for="firstname" class="col-sm-2 control-label">上课时间:</label>
			      <div class="col-sm-5">      
			        <input type="text" class="form-control layui-input" id="open_time" name="open_time" placeholder="请选择上课时间" lay-key="6">
			      </div>
			   </div>
                <div class="form-group">
			      <label for="firstname" class="col-sm-2 control-label">下课时间:</label>
			      <div class="col-sm-5">
			        <input type="text" class="form-control layui-input" id="close_time" name="close_time" placeholder="请选择下课时间" lay-key="7">
			      </div>
			   </div>	
			   <div class="form-group">
			      <label  class="col-sm-2 control-label">状态:</label>
			      <div class="col-sm-5">
			         <input type="text" class="form-control" id="status" name="status"
			            placeholder="请输入状态">（1有效，0无效）
			      </div>
			   </div>
			   <div class="form-group">
			      <label  class="col-sm-2 control-label">回放地址:</label>
			      <div class="col-sm-5">
			         <input type="text" class="form-control" id="back_url" name="back_url"
			            placeholder="课程结束后输入">
			      </div>
			   </div>		   
			   <div class="form-group">
			      <div class="col-sm-offset-2 col-sm-3">
			         <button type="button" class="btn btn-default closeBT">取消</button>
			      </div>
			      <div class="col-sm-offset-2 col-sm-3">
			         <button type="button" class="btn btn-default btnUpd">保存</button>
			      </div>
			   </div>
			</form>
		</div>
	</div>
<!--修改信息-->	

<!--修改信息-->		
</div><!--蒙版-->
<!--提交后的提示信息-->
<!--<div class="mengtwo" style="position: absolute;left: 0;top: 0;width: 100%;height: 100%;z-index: 1010;background: #222222;opacity: 0.5;display: none;"></div>-->
	<script type="text/javascript">
		//tooltip
		$(function() {
			//添加
			$("#add-modal").on("click", function() {
				$("#meng").fadeIn(500);
				$("#modal").fadeIn(500);
				$(".tooltips").fadeOut();
			});
			//关闭
			$(".close").click(function() {
				$("#meng").fadeOut(500);
				$("#modal").fadeOut(500);
				$(".modaltwo").fadeOut(500);
				$(".myform input").val("");//清空input的数据
			});
			//添加信息btnAdd提交 请求数据
			$(".btnAdd").click(function() {
				var formData = new FormData($("#uploadForm")[0]); 
				$.ajax({
					url : "add_subcourse.htm",
					 type: 'POST', 
				     data: formData, 
				     async: false, 
				     cache: false, 
				     contentType: false, 
				     processData: false,
					error : function(data) {

						alert("连接失败");
					},
					success : function(data) {
						if (data.recordsTotal == 1) {
							$("#modal").fadeOut();
							$("#meng").fadeOut();
							window.location.reload();
						} else {
							alert("数据处理错误");
						}
					}
				});

			});
			
			//修改数据
			$(".find-modal").on("click", function() {
				$(".mengone").fadeIn(500);
				$(".modaltwo").fadeIn(500);
				$(".tooltips").fadeOut();
				$(".myforms input").val("");//清空input的数据
				$.ajax({
					type : "GET",
					url : $(this).attr("openurl"),
					dataType : "json",
					success : function(data) {
						$(".myforms :input").eq(0).val(data.data.subName);
						$(".myforms :input").eq(1).val(data.data.id);
						$(".myforms :input").eq(2).val(data.data.studentNum);
						$(".myforms :input").eq(3).val(data.data.openTime);
						$(".myforms :input").eq(4).val(data.data.closeTime);
						$(".myforms :input").eq(5).val(data.data.status);
						$(".myforms :input").eq(6).val(data.data.backUrl);

					}
				});

			});
			
			//修改信息btnUpt提交 请求数据
			$(".btnUpd").click(function() {
				var formData = new FormData($("#uploadForm4up")[0]); 
				$.ajax({
					url : "edit_subcourse.htm",
					 type: 'POST', 
				     data: formData, 
				     async: false, 
				     cache: false, 
				     contentType: false, 
				     processData: false,
					error : function(data) {

						alert("连接失败");
					},
					success : function(data) {
						if (data.recordsTotal == 1) {
							$("#modal").fadeOut();
							$("#meng").fadeOut();
							window.location.reload();
						} else {
							alert("数据处理错误");
						}
					}
				});

			});
			
			//开课
			$(".kaike-modal").on("click", function() {
				$.ajax({
					type : "GET",
					url : $(this).attr("openurl"),
					dataType : "json",
					success : function(data) {
						alert("直播建立成功")
						window.location.reload();
					}
				});

			});

							
		});
		//时间选择器
		laydate.render({
		  elem: '#open_time'
		  ,type: 'datetime'
		});
		laydate.render({
		  elem: '#close_time'
		  ,type: 'datetime'
		});
	</script>
</body>
</html>

