package com.qmmt.edu.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qmmt.edu.persistence.po.EduOrder;
import com.qmmt.edu.persistence.po.EduSubcourse;
import com.qmmt.edu.persistence.po.EduVideoroom;
import com.qmmt.edu.persistence.po.WeixinUserinfo;
import com.qmmt.edu.pojo.EduCourseOrderPojo;
import com.qmmt.edu.pojo.EduCoursePojo;
import com.qmmt.edu.pojo.EduSubcoursePojo;
import com.qmmt.edu.rmiclient.WxApiService;
import com.qmmt.edu.service.EduCourseService;
import com.qmmt.edu.util.Constants;
import com.qmmt.edu.util.CookieUtil;
import com.qmmt.edu.util.DateUtil;
import com.qmmt.edu.util.HessianFactoryUtil;

@Controller
public class WXCourseController {
	
	final Logger logger = LoggerFactory.getLogger(WXCourseController.class);

	@Autowired
	EduCourseService eduCourseService;

	//公开课首页
	@RequestMapping(value = "/course/free_index.htm")
	public String freeIndex(
			Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		String openId = getWxOpenId( request,  response);
		
		List<EduCoursePojo> ywList = eduCourseService.getEduCourseFreeList("语文", BigDecimal.ZERO, 1, 4);
		model.addAttribute("ywList", ywList);
		List<EduCoursePojo> msList = eduCourseService.getEduCourseFreeList("数学", BigDecimal.ZERO, 1, 4);
		model.addAttribute("msList", msList);
		List<EduCoursePojo> yzList = eduCourseService.getEduCourseFreeList("益智", BigDecimal.ZERO, 1, 4);
		model.addAttribute("yzList", yzList);
		List<EduCoursePojo> yyList = eduCourseService.getEduCourseFreeList("英语", BigDecimal.ZERO, 1, 4);
		model.addAttribute("yyList", yyList);
		
		model.addAttribute("openId", openId);
	
		return "wxpage/course_free_index";
	}
	
	//收费课首页
	@RequestMapping(value = "/course/fee_index.htm")
	public String feeIndex(
			Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{

		String openId = getWxOpenId( request,  response);
		
		List<EduCoursePojo> ywList = eduCourseService.getEduCourseFeeList("语文", 1, 4);
		model.addAttribute("ywList", ywList);
		List<EduCoursePojo> msList = eduCourseService.getEduCourseFeeList("数学", 1, 4);
		model.addAttribute("msList", msList);
		List<EduCoursePojo> yzList = eduCourseService.getEduCourseFeeList("益智", 1, 4);
		model.addAttribute("yzList", yzList);
		List<EduCoursePojo> yyList = eduCourseService.getEduCourseFeeList("英语", 1, 4);
		model.addAttribute("yyList", yyList);
		
		model.addAttribute("openId", openId);
	
		return "wxpage/course_fee_index";
	}
	
	//历史课首页
	@RequestMapping(value = "/course/his_index.htm")
	public String hisIndex(
			Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{

		String openId = getWxOpenId( request,  response);
		
		List<EduCoursePojo> ywList = eduCourseService.getEduCourseFeeHisList("语文", 1, 4);
		model.addAttribute("ywList", ywList);
		List<EduCoursePojo> msList = eduCourseService.getEduCourseFeeHisList("数学", 1, 4);
		model.addAttribute("msList", msList);
		List<EduCoursePojo> yzList = eduCourseService.getEduCourseFeeHisList("益智", 1, 4);
		model.addAttribute("yzList", yzList);
		List<EduCoursePojo> yyList = eduCourseService.getEduCourseFeeHisList("英语", 1, 4);
		model.addAttribute("yyList", yyList);
		
		model.addAttribute("openId", openId);
	
		return "wxpage/course_fee_index";
	}
	
	//课程列表
	@RequestMapping(value = "/course/course_list.htm")
	public String courseList(@RequestParam(value = "is_free", required=false) Integer isFree,
			@RequestParam(value = "ctype", required=false) String ctype,
			@RequestParam(value = "int_page", required=false, defaultValue="1") Integer intPage,
			@RequestParam(value = "page_size", required=false, defaultValue="10") Integer pageSize,
			Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		if("1".equals(ctype)) {
			ctype = "语文";
		} else if("2".equals(ctype)) {
			ctype = "美术";
		}
		
		String openId = getWxOpenId( request,  response);
		
		if(isFree != null && isFree.intValue() == 0) {
			List<EduCoursePojo> cList = eduCourseService.getEduCourseFreeList(ctype, BigDecimal.ZERO, 1, 200);
			model.addAttribute("cList", cList);
		} else {
			List<EduCoursePojo> cList = eduCourseService.getEduCourseFeeAllList(ctype, 1, 200);
			model.addAttribute("cList", cList);
		}
	
	
		return "wxpage/course_list";
	}
	
	//教师课程列表
	@RequestMapping(value = "/course/teacher_course_list.htm")
	public String teacherCourseList(@RequestParam(value = "teacher_id", required=true) Integer teacherId,
			@RequestParam(value = "int_page", required=false, defaultValue="1") Integer intPage,
			@RequestParam(value = "page_size", required=false, defaultValue="10") Integer pageSize,
			Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		List<EduCoursePojo> cList = eduCourseService.getTeacherEduCourseList(teacherId, intPage, pageSize);
		model.addAttribute("cList", cList);
		
		return "wxpage/teacher_course_list";
	}
	
	//课程详情
	@RequestMapping(value = "/course/course_detail.htm")
	public String courseDetail(@RequestParam(value = "course_id", required=false) Long courseId,
			Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		String openId = getWxOpenId( request,  response);
		model.addAttribute("openId", openId);
		
		EduCoursePojo eduCoursePojo = eduCourseService.getEduCourseDetail(courseId);
		model.addAttribute("eduCourse", eduCoursePojo);
		
		int cc = eduCourseService.getEduOrderCount(courseId);
		if(eduCoursePojo.getEduSubcourseList() != null && 
				eduCoursePojo.getEduSubcourseList().size() >0 
				&& eduCoursePojo.getEduSubcourseList().get(0) != null)
			model.addAttribute("eduCourseNum", eduCoursePojo.getEduSubcourseList().get(0).getStudentNum()-cc);
		
		List<EduCoursePojo> cList = eduCourseService.getTeacherEduCourseList(eduCoursePojo.getTeacherId(), 1, 2);
		model.addAttribute("tList", cList);
		
		if(StringUtils.isNotBlank(openId)) {
			//判断是否有缴费，没有缴费提示去缴费，已缴费判断有无创建有效的直播教室
			EduOrder eduOrder = eduCourseService.getUserOrderInfo(openId,courseId);
			if(eduOrder != null && eduOrder.getRealpayAmount() != null 
					&& eduOrder.getRealpayAmount().compareTo(BigDecimal.ZERO)>0) {
				model.addAttribute("should_pay", "0");
				for(EduSubcoursePojo eduSubcoursePojo : eduCoursePojo.getEduSubcourseList()) {
					//已支付
					EduVideoroom eduVideoroom = eduCourseService.getCourseRoom(eduSubcoursePojo.getId());
					if(eduVideoroom != null) {
						String studentCode = eduCourseService.getOrCreateStudentCode(eduVideoroom,openId,eduCoursePojo.getCourseName()+" "+eduSubcoursePojo.getSubName(),DateUtil.getDateStr(eduSubcoursePojo.getOpenTime(), "MM月dd日 HH:mm"));
						eduSubcoursePojo.setStudentCode(studentCode);
						//显示课程的学生码
					}

				}
				
				
			}else {
				//显示支付提示
				model.addAttribute("should_pay", "1");
			}
			
			//无直播教室提示
		}else {
			//显示公众号二维码，引导关注
			
			//
			
		}
		
		
		
		
		return "wxpage/course_detail";
	}
	
	//支付页面
	//判断有无手机号，有手机号发起支付，无手机号转到手机号填写页面
	//保存手机号
	@RequestMapping(value = "/course/pay_order.htm")
	public String payOrder(@RequestParam(value = "course_id", required=false) Long courseId,
			Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		return "wxpage/orderList";
	}
	
	@RequestMapping(value = "/course/play_back.htm")
	public String playBack(@RequestParam(value = "course_id", required=false) Long courseId,
			@RequestParam(value = "subcourse_id", required=false) Long subcourseId,
			Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//检查是否缴费，没交费跳转
		String openId = getWxOpenId( request,  response);
		if(StringUtils.isNotBlank(openId)) {
			//判断是否有缴费，没有缴费提示去缴费，已缴费判断有无创建有效的直播教室
			EduOrder eduOrder = eduCourseService.getUserOrderInfo(openId,courseId);
			if(eduOrder != null && eduOrder.getRealpayAmount() != null 
					&& eduOrder.getRealpayAmount().compareTo(BigDecimal.ZERO)>0) {
				
				EduSubcourse eduSubcourse = eduCourseService.getEduSubcourse(subcourseId);
				if(eduSubcourse.getBackUrl() != null)
					model.addAttribute("back_url", eduSubcourse.getBackUrl());
				
				return "wxpage/course_back";
			}
		}
		
		return "redirect:/course/course_detail.htm?course_id="+courseId;
	}
	
	
	//学习交流
	@RequestMapping(value = "/course/connect_us.htm")
	public String connectUs(@RequestParam(value ="code", required=false) String code,
			@RequestParam(value = "course_id", required=false) Long courseId,
			Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		return "wxpage/connect_us";
	}
	
	//我的课程
	@RequestMapping(value = "/course/my_order.htm")
	public String myOrder(@RequestParam(value = "int_page", required=false, defaultValue="1") Integer intPage,
			@RequestParam(value = "page_size", required=false, defaultValue="50") Integer pageSize,
			Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		String openId = getWxOpenId( request,  response);
		if(StringUtils.isNotBlank(openId)) {
			List<EduCourseOrderPojo> olist = eduCourseService.getStusentCourseList(openId,intPage,pageSize);
			model.addAttribute("olist", olist);
		}
		
		return "wxpage/my_order";
	}
	
	//我的推荐
	@RequestMapping(value = "/course/my_comm.htm")
	public String myComm(@RequestParam(value = "int_page", required=false, defaultValue="1") Integer intPage,
			@RequestParam(value = "page_size", required=false, defaultValue="50") Integer pageSize,
			Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		String openId = getWxOpenId( request,  response);
		WeixinUserinfo weixinUserinfo = eduCourseService.getWeixinUserinfo(openId);
		if(weixinUserinfo != null) {
			
			//获取二维码
			try {
				WxApiService wxApiService = (WxApiService)HessianFactoryUtil.getHessianObj(WxApiService.class);
				String qrurl = wxApiService.createQrcodeUrl(weixinUserinfo.getAccountId(), null, weixinUserinfo.getId().intValue(), 1, "COMM");
				model.addAttribute("qrurl", qrurl);
			} catch (Exception e) {
				e.printStackTrace();
			}

			//获取推荐好友
			List<WeixinUserinfo> list = eduCourseService.getCommStudents(weixinUserinfo.getId());
			model.addAttribute("commlist", list);
			
		}
		
		return "wxpage/my_comm";
	}
	
	//进入直播教室
	@RequestMapping(value = "/course/enter_room.htm")
	public String enterRoom(
			@RequestParam(value = "nickname", required=false) String nickname,
			@RequestParam(value = "sc", required=false) Long sc,
			@RequestParam(value = "student_code", required=false) String studentCode,
			Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		String data = eduCourseService.getEnterData(sc,nickname,studentCode);
		model.addAttribute("jsdata", data);
		
		return "wxpage/enter_room";
	}
	
	
	//创建直播教室
	@RequestMapping(value = "/course/create_room.json")
	public void createRoom(
			@RequestParam(value = "subcourse_id", required=false) Long subcourseId,
			Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		//判断直播教室是否创建
		EduVideoroom eduVideoroom = eduCourseService.getCourseRoom(subcourseId);
		if(eduVideoroom == null) {
			eduVideoroom = eduCourseService.createCourseRoom(subcourseId);
		}
		request.setAttribute(Constants.jsonResultData,eduVideoroom);
		request.setAttribute(Constants.jsonResultCode,0);
	}
	
	@RequestMapping(value = "/course/pushopen.json")
	public void test(
			@RequestParam(value = "subcourse_id", required=false) Long subcourseId,
			Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		eduCourseService.pushOpenCousreNotify(subcourseId);
		request.setAttribute(Constants.jsonResultCode,0);
	}
	
	
	private String getWxOpenId(HttpServletRequest request, HttpServletResponse response) {
		String code = request.getParameter("code");
		//从COOKIE里取openid,没有存储到COOKIE
		Cookie cookieopenid = CookieUtil.getCookieByName(request, "open_id");
		String openid = null;
		if(cookieopenid != null)
			openid = cookieopenid.getValue();
		
		System.out.println("cookie openid="+openid);
		
		if(StringUtils.isBlank(openid) && StringUtils.isNotBlank(code)){			
			//通过code取微信的openid	
			try {
				WxApiService wxApiService = (WxApiService)HessianFactoryUtil.getHessianObj(WxApiService.class);
				openid = wxApiService.getWxOpenid("gh_a4c6900fdb5a", code);
				//openid = WXPubUtil.getSnsapiUserinfo(code);
				if(null!=openid){
					System.out.println("addCookie"+openid);
					Cookie cookie = new Cookie("open_id",openid);
					cookie.setMaxAge(60*60*24);//过期时间为24小时
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}
		return openid;
	}


}
