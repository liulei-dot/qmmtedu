package com.qmmt.edu.controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.qmmt.edu.persistence.po.EduTeacher;
import com.qmmt.edu.service.EduTeacherService;
import com.qmmt.edu.util.Constants;

@Controller
public class AdminEduTeacherController {
	final Logger logger = LoggerFactory.getLogger(AdminEduTeacherController.class);
	
	@Autowired
	EduTeacherService eduTeacherService;
	
	@RequestMapping(value = "/admin/teacher_list.htm")
	public String teacherList(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		String username = request.getParameter("username");
		
		List<EduTeacher> teacherList= eduTeacherService.getEduTeacherList();
		
		model.addAttribute("teacherList", teacherList);
		
		return "admin/edu/teacher_list"; 
		
	}
	
	@RequestMapping(value = "/admin/get_teacher.htm")
	public void getTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String id = request.getParameter("id");		
		
		EduTeacher eduTeacher = eduTeacherService.getEduTeacher(Integer.parseInt(id));
		
		PageResults pageResults = new PageResults();
		pageResults.setDraw(1);
		pageResults.setRecordsTotal(1);
		pageResults.setRecordsFiltered(1);
		pageResults.setData(eduTeacher);		
		
		request.setAttribute(Constants.pageResultData,pageResults); 
		
	}
	
	@RequestMapping(value = "/admin/add_teacher.htm" , method = RequestMethod.POST)
	public void addTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String techerName = request.getParameter("teachername");
		String info = request.getParameter("teacherinfo");
		
		byte[] imgdata = null;
		String headerUrl = null;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		if(multipartRequest.getFileMap().size() >0 ){
			MultipartFile imgfile = multipartRequest.getFile("teacherhead");
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
					
					headerUrl = Constants.getConfig("img_host")+filePath;
				}
				
			}					
		}
		
		

		
		boolean res = eduTeacherService.addEduTeacher(techerName, headerUrl, info);
		
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
	
	@RequestMapping(value = "/admin/edit_teacher.htm" , method = RequestMethod.POST)
	public void editTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String id = request.getParameter("id");
		String techerName = request.getParameter("teachername");
		String info = request.getParameter("teacherinfo");
		
		byte[] imgdata = null;
		String headerUrl = null;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		if(multipartRequest.getFileMap().size() >0 ){
			MultipartFile imgfile = multipartRequest.getFile("teacherhead");
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
					
					headerUrl = Constants.getConfig("img_host")+filePath;
				}
				
			}					
		}
		
		

		
		boolean res = eduTeacherService.updateEduTeacher(Integer.parseInt(id),techerName, headerUrl, info);
		
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
		

}
