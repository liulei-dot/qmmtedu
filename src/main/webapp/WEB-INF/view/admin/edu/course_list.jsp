<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.qmmt.edu.util.*,com.qmmt.edu.pojo.*,com.qmmt.edu.persistence.po.*" %>

<% 
String path = "http://edu.gdzqyz.cn:8081/qmmtedu";
List<EduCoursePojo> courseList = (List<EduCoursePojo>)request.getAttribute("courseList");

List<EduTeacher> teacherList= (List<EduTeacher>)request.getAttribute("teacherList");

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
		<script src="<%=path %>/js/laydate/laydate.js"></script>
		<script src="<%=path %>/js/jquery/bootstrap-fileinput.js"></script>
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
			<li class="active">课程列表</li>
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
											<div class="col-sm-10 text-center line-height-150" style="line-height: 3.5 !important;">课程列表</div>
												<a id="add-modal" class="btn btn-info btn-lg smaller-90 bg-primary white pull-right" data-toggle="modal" data-target="" style="height:34px;">
													<span class="glyphicon glyphicon-plus"></span> 创建课程
												</a>
												<!--table-->
												<table class="table table-bordered">													
													<thead>
														<tr>
															<th>课程ID</th>
															<th>课程名称</th>
															<th>教师</th>
															<th>开课时间</th>
															<th>结课时间</th>
															<th>科目类别</th>
															<th>价格</th>
															<th>报名情况</th>
															<th>课程目录</th>
															<th>操作</th>
														</tr>
													</thead>
													<tbody>
													<%for(EduCoursePojo eduCoursePojo: courseList){ %>
														<tr>
															<td><%=eduCoursePojo.getId() %></td>
															<td><%=eduCoursePojo.getCourseName() %></td>
															<td><%=eduCoursePojo.getEduTeacher().getTecherName() %></td>
															<td><%=DateUtil.getDateStr(eduCoursePojo.getOpenTime(), "yyyy年MM月dd日") %></td>
															<td><%=DateUtil.getDateStr(eduCoursePojo.getCloseTime(), "yyyy年MM月dd日") %></td>
															<th><%=eduCoursePojo.getCtype() %></th>
															<th><%=eduCoursePojo.getCourseFee() %></th>
															<th><a href="<%=path%>/admin/get_students_list.htm?cid=<%=eduCoursePojo.getId() %>">学生列表</a></th>
															<th><a href="<%=path%>/admin/sub_course_list.htm?cid=<%=eduCoursePojo.getId() %>">子课列表</a></th>
															<th>
															 <button type="button" openurl="get_course.htm?cid=<%=eduCoursePojo.getId() %>" class="btn btn-success find-modal">修改</button>															 
															</th>
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
	               创建课程
	            </h4>
	        </div>
			<form class="form-horizontal myform" id="uploadForm" role="form" method = "POST" enctype="multipart/form-data">
			   <div class="form-group">
			      <label for="firstname" class="col-sm-2 control-label">课程名称:</label>
			      <div class="col-sm-5">
			         <input type="text" class="form-control" id="courseName" name="courseName"
			            placeholder="请输入名称">
			      </div>
			   </div>
			   <div class="form-group">
			      <label  class="col-sm-2 control-label">教师:</label>
			      <div class="col-sm-5">
			         <select class="form-control" id="teacherId" name="teacherId">
			         <%for(EduTeacher eduTeacher: teacherList){ %>
				      <option value ="<%=eduTeacher.getId() %>"><%=eduTeacher.getTecherName() %></option>		
				      <% }%>			     				      
				    </select>
			      </div>
			   </div>
			   <div class="form-group">
			      <div class="col-sm-6">
			        <div class="row">
			          <label for="firstname" class="col-sm-4 control-label">开课时间:</label>
				      <div class="col-sm-8">
				        <input type="text" class="form-control layui-input" id="openTime" name="openTime" placeholder="请选择上课时间" lay-key="6">
				      </div>
			        </div>
			      </div>
			     <div class="col-sm-6">
			        <div class="row">
			          <label for="firstname" class="col-sm-4 control-label">结课时间:</label>
				      <div class="col-sm-8">
				         <input type="text" class="form-control layui-input" id="closeTime" name="closeTime" placeholder="请选择结课时间" lay-key="7">
				      </div>
			        </div>
			      </div>
			   </div>
			   <div class="form-group">
			      <label for="firstname" class="col-sm-2 control-label">科目类别:</label>
			      <div class="col-sm-5">			        			   
				    <select class="form-control" id="ctype" name="ctype">
				      <option value ="英语">英语</option>
				      <option value ="语文">语文</option>
				      <option value ="数学">数学</option>					      
				    </select>					
			      </div>
			   </div>
                <div class="form-group">
			      <label for="firstname" class="col-sm-2 control-label">课时:</label>
			      <div class="col-sm-5">
			         <input type="text" class="form-control" id="courseNum" name="courseNum"
			            placeholder="请输入名称">
			      </div>
			   </div>
			   <div class="form-group">
			      <div class="col-sm-6">
			        <div class="row">
			          <label for="firstname" class="col-sm-4 control-label">原价(非必填):</label>
				      <div class="col-sm-8">
				         <input type="text" class="form-control" id="marketCourseFee" name="marketCourseFee"
				            placeholder="原价">
				      </div>
			        </div>
			      </div>
			     <div class="col-sm-6">
			        <div class="row">
			          <label for="firstname" class="col-sm-4 control-label">现价:</label>
				      <div class="col-sm-8">
				         <input type="text" class="form-control" id="courseFee" name="courseFee"
				            placeholder="现价">
				      </div>
			        </div>
			      </div>			      
			   </div>
			   <!--<div class="form-group">
				   <div class="col-sm-6">
				        <div class="row">
				          <label for="firstname" class="col-sm-4 control-label">课程封面:</label>
					      <div class="col-sm-8">
					         <input type="file"  id="" >
					      </div>
				        </div>
				    </div>
			   </div>		-->
			   <div class="form-group" id="uploadPage" enctype='multipart/form-data'>
				   <div class="col-sm-6">
				      <div class="row">
				            <label for="firstname" class="col-sm-4 control-label">课程封面:</label>
				                <div class="col-sm-8">
				                   <div class="fileinput fileinput-new" data-provides="fileinput"  id="exampleInputUpload">
				                    <div class="fileinput-new thumbnail" style="width: 200px;height: auto;max-height:150px;">
				                        <img id='picImg' style="width: 100%;height: auto;max-height: 140px;" src="<%=path %>/assets/css/images/border.png" alt="" />
				                    </div>
				                    <div class="fileinput-preview fileinput-exists thumbnail" style="max-width: 200px; max-height: 150px;"></div>
				                    <div>
				                        <span class="btn btn-primary btn-file">
				                            <span class="fileinput-new">选择文件</span>
				                            <span class="fileinput-exists">换一张</span>
				                            <input type="file" id="imgUrl" name="imgUrl" accept="image/gif,image/jpeg,image/x-png"/>
				                        </span>
				                        <a href="javascript:;" class="btn btn-warning fileinput-exists" data-dismiss="fileinput">移除</a>
				                    </div>
				                </div> 
			                </div>
				      </div>
				   </div>				                       
               </div>

			   <div class="form-group">				   			        
		          <label for="firstname" class="col-sm-2 control-label">课程简介:</label>
			      <div class="col-sm-6">				        
			          <textarea class="form-control" rows="3" id="info" name="info"></textarea>				        
			      </div>				        				    
			   </div>		
			   <div class="form-group">				   
	              <label for="firstname" class="col-sm-2 control-label">课程状态:</label>
			      <div class="col-sm-6">
			      <input type="text" class="form-control" id="status" name="status"
			            placeholder="请输地址">1有效，0无效		        
		            				       
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
	               修改课程
	            </h4>
	        </div>
			<form class="form-horizontal myforms" id="uploadForm4up" role="form" method = "POST" enctype="multipart/form-data">
			   <div class="form-group">
			      <label for="firstname" class="col-sm-2 control-label">课程名称:</label>
			      <div class="col-sm-5">
			         <input type="text" class="form-control" id="courseName" name="courseName"
			            placeholder="请输入名称"><input type="hidden" id="id" name="id" value="">
			      </div>
			   </div>
			  
			   <div class="form-group">
			      <div class="col-sm-6">
			        <div class="row">
			          <label for="firstname" class="col-sm-4 control-label">开课时间:</label>
				      <div class="col-sm-8">
				        <input type="text" class="form-control layui-input" id="openTime" name="openTime" placeholder="请选择上课时间" lay-key="6">
				      </div>
			        </div>
			      </div>
			     <div class="col-sm-6">
			        <div class="row">
			          <label for="firstname" class="col-sm-4 control-label">结课时间:</label>
				      <div class="col-sm-8">
				         <input type="text" class="form-control layui-input" id="closeTime" name="closeTime" placeholder="请选择结课时间" lay-key="7">
				      </div>
			        </div>
			      </div>
			   </div>
			 
                <div class="form-group">
			      <label for="firstname" class="col-sm-2 control-label">课时:</label>
			      <div class="col-sm-5">
			         <input type="text" class="form-control" id="courseNum" name="courseNum"
			            placeholder="请输入名称">
			      </div>
			   </div>
			   <div class="form-group">
			      <div class="col-sm-6">
			        <div class="row">
			          <label for="firstname" class="col-sm-4 control-label">原价(非必填):</label>
				      <div class="col-sm-8">
				         <input type="text" class="form-control" id="marketCourseFee" name="marketCourseFee"
				            placeholder="原价">
				      </div>
			        </div>
			      </div>
			     <div class="col-sm-6">
			        <div class="row">
			          <label for="firstname" class="col-sm-4 control-label">现价:</label>
				      <div class="col-sm-8">
				         <input type="text" class="form-control" id="courseFee" name="courseFee"
				            placeholder="现价">
				      </div>
			        </div>
			      </div>			      
			   </div>
			   <!--<div class="form-group">
				   <div class="col-sm-6">
				        <div class="row">
				          <label for="firstname" class="col-sm-4 control-label">课程封面:</label>
					      <div class="col-sm-8">
					         <input type="file"  id="" >
					      </div>
				        </div>
				    </div>
			   </div>		-->
			   <div class="form-group" id="uploadPage" enctype='multipart/form-data'>
				   <div class="col-sm-6">
				      <div class="row">
				            <label for="firstname" class="col-sm-4 control-label">课程封面:</label>
				                <div class="col-sm-8">
				                   <div class="fileinput fileinput-new" data-provides="fileinput"  id="exampleInputUpload">
				                    <div class="fileinput-new thumbnail" style="width: 200px;height: auto;max-height:150px;">
				                        <img id='picImgUpt' class="picImgUpt" style="width: 100%;height: auto;max-height: 140px;" src="<%=path %>/assets/css/images/border.png" alt="" />
				                    </div>
				                    <div class="fileinput-preview fileinput-exists thumbnail" style="max-width: 200px; max-height: 150px;"></div>
				                    <div>
				                        <span class="btn btn-primary btn-file">
				                            <span class="fileinput-new">选择文件</span>
				                            <span class="fileinput-exists">换一张</span>
				                            <input type="file" id="imgUrl" name="imgUrl" accept="image/gif,image/jpeg,image/x-png"/>
				                        </span>
				                        <a href="javascript:;" class="btn btn-warning fileinput-exists" data-dismiss="fileinput">移除</a>
				                    </div>
				                </div> 
			                </div>
				      </div>
				   </div>				                       
               </div>

			   		
			   <div class="form-group">				   
	              <label for="firstname" class="col-sm-2 control-label">课程状态:</label>
			      <div class="col-sm-6">
			      <input type="text" class="form-control" id="status" name="status"
			            placeholder="请输地址">1有效，0无效		        
		            				       
			      </div>						         
			   </div>
			   <div class="form-group">				   			        
		          <label for="firstname" class="col-sm-2 control-label">课程简介:</label>
			      <div class="col-sm-6">				        
			          <textarea class="form-control" rows="3" id="info" name="info"></textarea>				        
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
					url : "add_course.htm",
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
						$(".myforms :input").eq(0).val(data.data.courseName);
						$(".myforms :input").eq(1).val(data.data.id);
						$(".myforms :input").eq(2).val(data.data.openTime);
						$(".myforms :input").eq(3).val(data.data.closeTime);
						$(".myforms :input").eq(4).val(data.data.courseNum);
						$(".myforms :input").eq(5).val(data.data.marketCourseFee);
						$(".myforms :input").eq(6).val(data.data.courseFee);
						$(".myforms :input").eq(8).val(data.data.status);
						$(".myforms :input").eq(9).val(data.data.info);
						$("#picImgUpt").attr('src',data.data.imgUrl); 

					}
				});

			});
			
			//修改信息btnUpt提交 请求数据
			$(".btnUpd").click(function() {
				var formData = new FormData($("#uploadForm4up")[0]); 
				$.ajax({
					url : "edit_course.htm",
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
				
				//时间选择器
		laydate.render({
		  elem: '#openTime'
		  ,type: 'datetime'
		});
		laydate.render({
		  elem: '#closeTime'
		  ,type: 'datetime'
		});
		});
	</script>
</body>
</html>

