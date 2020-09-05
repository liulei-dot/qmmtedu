package com.qmmt.edu.controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.qmmt.edu.controllers.json.PageResults;
import com.qmmt.edu.persistence.po.EduCourse;
import com.qmmt.edu.persistence.po.EduSubcourse;
import com.qmmt.edu.persistence.po.EduTeacher;
import com.qmmt.edu.persistence.po.WeixinUserinfo;
import com.qmmt.edu.pojo.EduCoursePojo;
import com.qmmt.edu.service.EduCourseService;
import com.qmmt.edu.service.EduTeacherService;
import com.qmmt.edu.util.Constants;
import com.qmmt.edu.util.DateUtil;

@Controller
public class AdminEduCourseController {
	final Logger logger = LoggerFactory.getLogger(AdminEduCourseController.class);
	
	@Autowired
	EduCourseService eduCourseService;
	
	@Autowired
	EduTeacherService eduTeacherService;
	
	@RequestMapping(value = "/admin/course_list.htm")
	public String courseList(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		String username = request.getParameter("username");
		
		List<EduCoursePojo> courseList = eduCourseService.getEduCourseList(null,1,100);
		
		model.addAttribute("courseList", courseList);
		
		List<EduTeacher> teacherList= eduTeacherService.getEduTeacherList();
		
		model.addAttribute("teacherList", teacherList);
		
		return "admin/edu/course_list"; 
		
	}
	
	@RequestMapping(value = "/admin/get_course.htm")
	public void getCourse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String cid = request.getParameter("cid");		
		
		EduCourse eduCoursePojo = eduCourseService.getEduCourse (Long.parseLong(cid));
		
		PageResults pageResults = new PageResults();
		pageResults.setDraw(1);
		pageResults.setRecordsTotal(1);
		pageResults.setRecordsFiltered(1);
		pageResults.setData(eduCoursePojo);		
		
		request.setAttribute(Constants.pageResultData,pageResults); 
		
	}
	
	@RequestMapping(value = "/admin/add_course.htm" , method = RequestMethod.POST)
	public void addCourse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String courseName = request.getParameter("courseName");
		String teacherId = request.getParameter("teacherId");
		String openTime = request.getParameter("openTime");
		String closeTime = request.getParameter("closeTime");
		String ctype = request.getParameter("ctype");
		String courseNum = request.getParameter("courseNum");
		String marketCourseFee = request.getParameter("marketCourseFee");
		String courseFee = request.getParameter("courseFee");
		//String imgUrl = request.getParameter("imgUrl");
		String info = request.getParameter("info");
		String status = request.getParameter("status");
		
		byte[] imgdata = null;
		String imgUrl = null;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		if(multipartRequest.getFileMap().size() >0 ){
			MultipartFile imgfile = multipartRequest.getFile("imgUrl");
			if(imgfile != null && imgfile.getSize()>10){
				imgdata = imgfile.getBytes();
				//上传阿里云，获取url
				//切方图
				//切方图
				//byte[] sm_imgdata = ImageUtil.subImage(imgdata,320,320);
				if(imgdata != null && imgdata.length > 0) {
					String filePath = "course/"+System.currentTimeMillis()+".jpg";
					String file = Constants.getConfig("img_dir")+"/"+filePath;
					FileOutputStream fout = new FileOutputStream(file);
					fout.write(imgdata);
					fout.close();
					
					imgUrl = Constants.getConfig("img_host")+filePath;
				}
				
			}					
		}

		boolean res = false;
		try {
			BigDecimal marketCourseFee01 = null;
			if(StringUtils.isNotBlank(marketCourseFee)) {
				marketCourseFee01 = new BigDecimal(marketCourseFee);
			}
			
			res = eduCourseService.addCourse(courseName, Integer.parseInt(teacherId), DateUtil.str2Date(openTime), DateUtil.str2Date(closeTime), ctype, Integer.parseInt(courseNum), 
				new BigDecimal(courseFee),marketCourseFee01, imgUrl, Integer.parseInt(status), info);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		PageResults pageResults = new PageResults();
		pageResults.setDraw(1);
		if(res)
			pageResults.setRecordsTotal(1);
		else
			pageResults.setRecordsTotal(0);
		pageResults.setRecordsFiltered(1);
		pageResults.setData(null);		
		
		request.setAttribute(Constants.pageResultData,pageResults);
		
	}
	
	@RequestMapping(value = "/admin/edit_course.htm" , method = RequestMethod.POST)
	public void editCourse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String id = request.getParameter("id");
		String courseName = request.getParameter("courseName");
		//String teacherId = request.getParameter("teacherId");
		String openTime = request.getParameter("openTime");
		String closeTime = request.getParameter("closeTime");
		String ctype = request.getParameter("ctype");
		String courseNum = request.getParameter("courseNum");
		String marketCourseFee = request.getParameter("marketCourseFee");
		String courseFee = request.getParameter("courseFee");
		//String imgUrl = request.getParameter("imgUrl");
		String info = request.getParameter("info");
		String status = request.getParameter("status");
		
		byte[] imgdata = null;
		String imgUrl = null;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		if(multipartRequest.getFileMap().size() >0 ){
			MultipartFile imgfile = multipartRequest.getFile("imgUrl");
			if(imgfile != null && imgfile.getSize()>10){
				imgdata = imgfile.getBytes();
				//上传阿里云，获取url
				//切方图
				//切方图
				//byte[] sm_imgdata = ImageUtil.subImage(imgdata,320,320);
				if(imgdata != null && imgdata.length > 0) {
					String filePath = "teacher/"+System.currentTimeMillis()+".jpg";
					String file = Constants.getConfig("img_dir")+"/"+filePath;
					FileOutputStream fout = new FileOutputStream(file);
					fout.write(imgdata);
					fout.close();
					
					imgUrl = Constants.getConfig("img_host")+filePath;
				}
				
			}					
		}

		boolean res = false;
		try {
			BigDecimal marketCourseFee01 = null;
			if(StringUtils.isNotBlank(marketCourseFee)) {
				marketCourseFee01 = new BigDecimal(marketCourseFee);
			}
			
			res = eduCourseService.editCourse(Long.parseLong(id),courseName, null, DateUtil.str2Date(openTime), DateUtil.str2Date(closeTime), ctype, Integer.parseInt(courseNum), 
				new BigDecimal(courseFee),marketCourseFee01, imgUrl, Integer.parseInt(status), info);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		PageResults pageResults = new PageResults();
		pageResults.setDraw(1);
		if(res)
			pageResults.setRecordsTotal(1);
		else
			pageResults.setRecordsTotal(0);
		pageResults.setRecordsFiltered(1);
		pageResults.setData(null);		
		
		request.setAttribute(Constants.pageResultData,pageResults);
		
	}
	
	
	@RequestMapping(value = "/admin/sub_course_list.htm")
	public String subCourseList(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		String cid = request.getParameter("cid");
		
		EduCoursePojo eduCoursePojo = eduCourseService.getEduCourseDetail(Long.parseLong(cid));
		
		model.addAttribute("eduCoursePojo", eduCoursePojo);
		
		return "admin/edu/subcourselist"; 
		
	}
	
	
	@RequestMapping(value = "/admin/get_students_list.htm")
	public String getStudentsList(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		String cid = request.getParameter("cid");
		
		List<WeixinUserinfo>  slist = eduCourseService.getStudentsByCourse(Long.parseLong(cid));
		
		//System.out.println("slist =="+slist.size());
		
		model.addAttribute("slist", slist);
		
		return "admin/edu/student_list"; 
		
	}
	
	@RequestMapping(value = "/admin/add_subcourse.htm" , method = RequestMethod.POST)
	public void addSubcourse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String cid = request.getParameter("cid");
		
		String sub_name = request.getParameter("sub_name");
		String student_num = request.getParameter("student_num");
		String open_time = request.getParameter("open_time");
		String close_time = request.getParameter("close_time");
		String status = request.getParameter("status");
		String back_url = request.getParameter("back_url");
		
		
		System.out.println(sub_name+"--"+student_num+"--"+open_time+"--"+close_time+"--"+status+"--"+back_url+"--");
		

		
		boolean res = false;
		try {
			res = eduCourseService.addSubCourse(Long.parseLong(cid), sub_name, Integer.parseInt(student_num), DateUtil.str2Date(open_time), 
					DateUtil.str2Date(close_time), Integer.parseInt(status), back_url);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		PageResults pageResults = new PageResults();
		pageResults.setDraw(1);
		if(res)
			pageResults.setRecordsTotal(1);
		else
			pageResults.setRecordsTotal(0);
		pageResults.setRecordsFiltered(1);
		pageResults.setData(null);		
		
		request.setAttribute(Constants.pageResultData,pageResults);
		
	}
	
	
	@RequestMapping(value = "/admin/edit_subcourse.htm" , method = RequestMethod.POST)
	public void editSubcourse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String sid = request.getParameter("sid");
		
		String sub_name = request.getParameter("sub_name");
		String student_num = request.getParameter("student_num");
		String open_time = request.getParameter("open_time");
		String close_time = request.getParameter("close_time");
		String status = request.getParameter("status");
		String back_url = request.getParameter("back_url");
		
		
		System.out.println(sub_name+"--"+student_num+"--"+open_time+"--"+close_time+"--"+status+"--"+back_url+"--");
		

		
		boolean res = false;
		try {
			res = eduCourseService.editSubCourse(Long.parseLong(sid), sub_name, Integer.parseInt(student_num), DateUtil.str2Date(open_time), 
					DateUtil.str2Date(close_time), Integer.parseInt(status), back_url);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		PageResults pageResults = new PageResults();
		pageResults.setDraw(1);
		if(res)
			pageResults.setRecordsTotal(1);
		else
			pageResults.setRecordsTotal(0);
		pageResults.setRecordsFiltered(1);
		pageResults.setData(null);		
		
		request.setAttribute(Constants.pageResultData,pageResults);
		
	}
	
	@RequestMapping(value = "/admin/get_subcourse.htm")
	public void getSubcourse(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String sid = request.getParameter("sid");		
		
		EduSubcourse eduSubcourse = eduCourseService.getEduSubcourse(Long.parseLong(sid));
		
		PageResults pageResults = new PageResults();
		pageResults.setDraw(1);
		pageResults.setRecordsTotal(1);
		pageResults.setRecordsFiltered(1);
		pageResults.setData(eduSubcourse);		
		
		request.setAttribute(Constants.pageResultData,pageResults); 
		
	}
		

}
