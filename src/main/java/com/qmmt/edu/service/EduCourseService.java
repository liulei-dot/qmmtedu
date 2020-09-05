package com.qmmt.edu.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qmmt.edu.persistence.dao.EduCourseMapper;
import com.qmmt.edu.persistence.dao.EduOrderMapper;
import com.qmmt.edu.persistence.dao.EduStudentMapper;
import com.qmmt.edu.persistence.dao.EduSubcourseMapper;
import com.qmmt.edu.persistence.dao.EduTeacherMapper;
import com.qmmt.edu.persistence.dao.EduVideoroomMapper;
import com.qmmt.edu.persistence.dao.EduVideoroomStuMapper;
import com.qmmt.edu.persistence.dao.WeixinUserinfoMapper;
import com.qmmt.edu.persistence.po.EduCourse;
import com.qmmt.edu.persistence.po.EduOrder;
import com.qmmt.edu.persistence.po.EduStudent;
import com.qmmt.edu.persistence.po.EduSubcourse;
import com.qmmt.edu.persistence.po.EduTeacher;
import com.qmmt.edu.persistence.po.EduVideoroom;
import com.qmmt.edu.persistence.po.EduVideoroomStu;
import com.qmmt.edu.persistence.po.WeixinUserinfo;
import com.qmmt.edu.pojo.EduCourseOrderPojo;
import com.qmmt.edu.pojo.EduCoursePojo;
import com.qmmt.edu.pojo.EduSubcoursePojo;
import com.qmmt.edu.rmiclient.WxApiService;
import com.qmmt.edu.util.AmountMath;
import com.qmmt.edu.util.BeanCopyUtil;
import com.qmmt.edu.util.DateUtil;
import com.qmmt.edu.util.HessianFactoryUtil;

@Service("eduCourseService")
public class EduCourseService {
	
	@Autowired
	EduCourseMapper eduCourseMapper;
	@Autowired
	EduTeacherMapper eduTeacherMapper;
	@Autowired
	EduSubcourseMapper eduSubcourseMapper;
	@Autowired
	EduVideoroomMapper eduVideoroomMapper;
	@Autowired
	WeixinUserinfoMapper weixinUserinfoMapper;
	@Autowired
	EduOrderMapper eduOrderMapper;
	@Autowired
	EduVideoroomStuMapper eduVideoroomStuMapper;
	@Autowired
	EduStudentMapper eduStudentMapper;
	
	@Autowired
	BaijiacloudApiService baijiacloudApiService;
	
	public List<EduCoursePojo> getEduCourseFreeList(String ctype,BigDecimal courseFee,Integer intPage,Integer pageSize) {
		List<EduCoursePojo> retList = new ArrayList<EduCoursePojo>();
		EduCourse record = new EduCourse();
		record.setCtype(ctype);
		record.setCourseFee(courseFee);
		record.setLimitStart((long)(intPage - 1)*pageSize);
		record.setLimitCount(pageSize);
		record.setSqlSort("open_time desc");
		record.setStatus(1);
		List<EduCourse> list = eduCourseMapper.selectList(record);
		for(EduCourse eduCourse : list) {
			EduCoursePojo eduCoursePojo = new EduCoursePojo();
			BeanCopyUtil.copyProperties(eduCourse, eduCoursePojo);
			retList.add(eduCoursePojo);
		}
		return retList;
	}
	
	public List<EduCoursePojo> getEduCourseFeeList(String ctype,Integer intPage,Integer pageSize) {
		List<EduCoursePojo> retList = new ArrayList<EduCoursePojo>();
		EduCourse record = new EduCourse();
		record.setCtype(ctype);
		record.setSqlStr("and course_fee>0 and close_time>now()");
		record.setLimitStart((long)(intPage - 1)*pageSize);
		record.setLimitCount(pageSize);
		record.setSqlSort("create_time desc");
		record.setStatus(1);
		List<EduCourse> list = eduCourseMapper.selectList(record);
		for(EduCourse eduCourse : list) {
			EduCoursePojo eduCoursePojo = new EduCoursePojo();
			BeanCopyUtil.copyProperties(eduCourse, eduCoursePojo);
			eduCoursePojo.setOrderNum(getEduOrderCount(eduCoursePojo.getId()));
			retList.add(eduCoursePojo);
		}
		return retList;
	}
	
	public List<EduCoursePojo> getEduCourseFeeAllList(String ctype,Integer intPage,Integer pageSize) {
		List<EduCoursePojo> retList = new ArrayList<EduCoursePojo>();
		EduCourse record = new EduCourse();
		record.setCtype(ctype);
		record.setSqlStr("and course_fee>0");
		record.setLimitStart((long)(intPage - 1)*pageSize);
		record.setLimitCount(pageSize);
		record.setSqlSort("create_time desc");
		record.setStatus(1);
		List<EduCourse> list = eduCourseMapper.selectList(record);
		for(EduCourse eduCourse : list) {
			EduCoursePojo eduCoursePojo = new EduCoursePojo();
			BeanCopyUtil.copyProperties(eduCourse, eduCoursePojo);
			retList.add(eduCoursePojo);
		}
		return retList;
	}
	
	public List<EduCoursePojo> getEduCourseFeeHisList(String ctype,Integer intPage,Integer pageSize) {
		List<EduCoursePojo> retList = new ArrayList<EduCoursePojo>();
		EduCourse record = new EduCourse();
		record.setCtype(ctype);
		record.setSqlStr("and course_fee>0 and open_time<now()");
		record.setLimitStart((long)(intPage - 1)*pageSize);
		record.setLimitCount(pageSize);
		record.setSqlSort("create_time desc");
		record.setStatus(1);
		List<EduCourse> list = eduCourseMapper.selectList(record);
		for(EduCourse eduCourse : list) {
			EduCoursePojo eduCoursePojo = new EduCoursePojo();
			BeanCopyUtil.copyProperties(eduCourse, eduCoursePojo);
			retList.add(eduCoursePojo);
		}
		return retList;
	}

	public EduCoursePojo getEduCourseDetail(Long courseId) {
		EduCourse eduCourse = eduCourseMapper.selectByPrimaryKey(courseId);
		EduCoursePojo eduCoursePojo = new EduCoursePojo();
		BeanCopyUtil.copyProperties(eduCourse, eduCoursePojo);
		EduTeacher eduTeacher = eduTeacherMapper.selectByPrimaryKey(eduCourse.getTeacherId());
		eduCoursePojo.setEduTeacher(eduTeacher);
		
		EduSubcourse record = new EduSubcourse();
		record.setParentId(courseId);
		record.setSqlSort("id asc");
		record.setStatus(1);
		List<EduSubcoursePojo> eduSubcoursepjList = new ArrayList<EduSubcoursePojo>();
		
		List<EduSubcourse> eduSubcourseList = eduSubcourseMapper.selectList(record);
		for(EduSubcourse eduSubcourse: eduSubcourseList) {
			EduSubcoursePojo eduSubcoursePojo = new EduSubcoursePojo();
			BeanCopyUtil.copyProperties(eduSubcourse, eduSubcoursePojo);
			EduVideoroom eduVideoroom = getCourseRoom(eduSubcoursePojo.getId());
			if(eduVideoroom != null) {
				eduSubcoursePojo.setRoomId(eduVideoroom.getRoomId());	
			}
			eduSubcoursepjList.add(eduSubcoursePojo);
		}
		eduCoursePojo.setEduSubcourseList(eduSubcoursepjList);
		
		return eduCoursePojo;
	}

	public EduVideoroom getCourseRoom(Long subcourseId) {
		EduVideoroom record = new EduVideoroom();
		record.setSubcourseId(subcourseId);
		record.setStatus(1);
		List<EduVideoroom> list = eduVideoroomMapper.selectList(record);
		if(list.size() >= 1)
			return list.get(0);
		else
			return null;
	}

	public EduVideoroom createCourseRoom(Long subcourseId) {
		EduSubcourse eduSubcourse = eduSubcourseMapper.selectByPrimaryKey(subcourseId);
		if(eduSubcourse != null && eduSubcourse.getStatus() == 1) {
			EduCourse eduCourse = eduCourseMapper.selectByPrimaryKey(eduSubcourse.getParentId());
			if(eduCourse != null && eduCourse.getStatus() == 1) {
				//创建
				String title = eduCourse.getCourseName()+"-"+eduSubcourse.getSubName();
				HashMap<String,String> roomMap = baijiacloudApiService.createRoom(title, new Date(eduSubcourse.getOpenTime().getTime()-30*60000), 
						new Date(eduSubcourse.getCloseTime().getTime() + 60*60000), eduSubcourse.getStudentNum());
				if(roomMap.containsKey("room_id")) {
					EduVideoroom record = new EduVideoroom();
					record.setRoomId(roomMap.get("room_id"));
					record.setSubcourseId(subcourseId);
					record.setAdminCode(roomMap.get("admin_code"));
					record.setTeacherCode(roomMap.get("teacher_code"));
					record.setCreateTime(new Date());
					record.setModifyTime(new Date());
					record.setStatus(1);
					eduVideoroomMapper.insertSelective(record);
					return record;
				}
	
			}
		}
		return null;
	}

	public EduOrder getUserOrderInfo(String openId, Long courseId) {
		WeixinUserinfo weixinUserinfo = getWeixinUserinfo( openId) ;
		if(weixinUserinfo != null) {
			EduOrder eduOrder = new EduOrder();
			eduOrder.setCourseId(courseId);
			eduOrder.setWxUid(weixinUserinfo.getId());
			eduOrder = eduOrderMapper.selectOne(eduOrder);
			return eduOrder;
		}
		return null;
	}
	
	public WeixinUserinfo getWeixinUserinfo(String openId) {
		WeixinUserinfo weixinUserinfo = new WeixinUserinfo();
		weixinUserinfo.setOpenid(openId);
		weixinUserinfo = weixinUserinfoMapper.selectOne(weixinUserinfo);
		if(weixinUserinfo == null) {
			WxApiService wxApiService = (WxApiService)HessianFactoryUtil.getHessianObj(WxApiService.class);
			Long wxuid = wxApiService.createWxUser(openId, "gh_a4c6900fdb5a");
			if(wxuid != null) {
				weixinUserinfo = weixinUserinfoMapper.selectByPrimaryKey(wxuid);
			}
		}
		
		if(weixinUserinfo != null) {
			if(weixinUserinfo.getNickname() == null) {
				weixinUserinfo.setNickname("学生"+weixinUserinfo.getId());
			}
			if(weixinUserinfo.getHeadimgurl() == null) {
				weixinUserinfo.setHeadimgurl("http://edu.gdzqyz.cn/defaulthead.jpg");
			}
		}
		return weixinUserinfo;
	}

	public synchronized String getOrCreateStudentCode(EduVideoroom eduVideoroom, String openId,String courseName,String dateStr) {
		WeixinUserinfo weixinUserinfo =getWeixinUserinfo( openId) ;
		if(weixinUserinfo != null) {
			EduVideoroomStu record= new EduVideoroomStu();
			record.setRoomId(eduVideoroom.getRoomId());
			record.setWxUid(weixinUserinfo.getId());
			EduVideoroomStu eduVideoroomStu = eduVideoroomStuMapper.selectOne(record);
			if(eduVideoroomStu != null) {
				return eduVideoroomStu.getStudentCode();
			}else {
				String studentCode = baijiacloudApiService.createStudentCode(eduVideoroom.getRoomId(),weixinUserinfo.getId().toString(),weixinUserinfo.getHeadimgurl());
				if(studentCode != null) {
					record= new EduVideoroomStu();
					record.setRoomId(eduVideoroom.getRoomId());
					record.setSubcourseId(eduVideoroom.getSubcourseId());
					record.setWxUid(weixinUserinfo.getId());
					record.setStudentCode(studentCode);
					record.setCreateTime(new Date());
					record.setModifyTime(new Date());
					record.setStatus(1);
					eduVideoroomStuMapper.insertSelective(record);
					//发送微信模板课程开课通知
					try {
					WxApiService wxApiService = (WxApiService)HessianFactoryUtil.getHessianObj(WxApiService.class);
					String remark = "点击详情开始上课";
					String url= "http://edu.gdzqyz.cn/qmmtedu/course/enter_room.htm?student_code="+studentCode;
					wxApiService.openNotify("gh_a4c6900fdb5a", openId,courseName,dateStr,remark,url);
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
				
				return studentCode;
			}
		}
		return null;
	}

	public List<EduCoursePojo> getTeacherEduCourseList(Integer teacherId, Integer intPage, Integer pageSize) {
		List<EduCoursePojo> retList = new ArrayList<EduCoursePojo>();
		EduCourse record = new EduCourse();
		record.setTeacherId(teacherId);
		record.setLimitStart((long)(intPage - 1)*pageSize);
		record.setLimitCount(pageSize);
		record.setSqlSort("open_time desc");
		record.setStatus(1);
		List<EduCourse> list = eduCourseMapper.selectList(record);
		for(EduCourse eduCourse : list) {
			EduCoursePojo eduCoursePojo = new EduCoursePojo();
			BeanCopyUtil.copyProperties(eduCourse, eduCoursePojo);
			retList.add(eduCoursePojo);
		}
		return retList;
	}

	public List<EduCourseOrderPojo> getStusentCourseList(String openId, Integer intPage, Integer pageSize) {
		List<EduCourseOrderPojo> retList = new ArrayList<EduCourseOrderPojo>();
		WeixinUserinfo weixinUserinfo = getWeixinUserinfo( openId);
		if(weixinUserinfo != null) {
			EduOrder record = new EduOrder();
			record.setLimitStart((long)(intPage - 1)*pageSize);
			record.setLimitCount(pageSize);
			record.setWxUid(weixinUserinfo.getId());
			record.setStatus(1);
			record.setSqlSort(" id desc");
			List<EduOrder> list = eduOrderMapper.selectList(record);
			for(EduOrder eduOrder: list) {
				EduCourseOrderPojo eduCourseOrderPojo = new EduCourseOrderPojo();
				EduCourse eduCourse = eduCourseMapper.selectByPrimaryKey(eduOrder.getCourseId());
				BeanCopyUtil.copyProperties(eduCourse, eduCourseOrderPojo);
				
				EduSubcourse eduSubcourse = new EduSubcourse();
				eduSubcourse.setParentId(eduOrder.getCourseId());
				eduSubcourse.setSqlSort("id asc");
				eduSubcourse.setStatus(1);
				List<EduSubcourse> eduSubcourseList = eduSubcourseMapper.selectList(eduSubcourse);
				for(EduSubcourse es: eduSubcourseList) {
					EduVideoroom eduVideoroom = new EduVideoroom();
					eduVideoroom.setSubcourseId(es.getId());
					eduVideoroom.setStatus(1);
					List<EduVideoroom> eroolist = eduVideoroomMapper.selectList(eduVideoroom);
					for(EduVideoroom r: eroolist) {
						eduCourseOrderPojo.setRoomId(r.getRoomId());
						break;
					}
					if(es.getCloseTime().getTime()>System.currentTimeMillis()) {
						eduCourseOrderPojo.setOpenTime(es.getOpenTime());
						eduCourseOrderPojo.setCloseTime(es.getCloseTime());
						break;
					}
					
				}
				
				EduTeacher eduTeacher = eduTeacherMapper.selectByPrimaryKey(eduCourse.getTeacherId());
				eduCourseOrderPojo.setEduTeacher(eduTeacher);
				BeanCopyUtil.copyProperties(eduOrder, eduCourseOrderPojo);
				retList.add(eduCourseOrderPojo);
				
			}
		}
		return retList;
	}

	public EduOrder getOrCreatePreOrder(String openId, Long courseId) {
		WeixinUserinfo weixinUserinfo = getWeixinUserinfo( openId);
		EduCourse eduCourse = eduCourseMapper.selectByPrimaryKey(courseId);

		if(weixinUserinfo != null && eduCourse != null) {
			EduOrder eduOrder = new EduOrder();
			eduOrder.setCourseId(courseId);
			eduOrder.setWxUid(weixinUserinfo.getId());
			eduOrder = eduOrderMapper.selectOne(eduOrder);
			if(eduOrder != null) {
				if(eduOrder.getRealpayAmount() == null) {
					return eduOrder;
				}
			}else {
				//create
				EduOrder record = new EduOrder();
				record.setWxUid(weixinUserinfo.getId());
				record.setCourseId(eduCourse.getId());
				record.setReceivableAmount(eduCourse.getCourseFee());
				record.setStatus(1);
				record.setCreateTime(new Date());
				record.setModifyTime(new Date());
				eduOrderMapper.insertSelective(record);
				return record;
			}
			
		
		}
		return null;
		
	}

	public void updateEnrollPay(String pay_order_id, String openid, Integer cash_fee, String bank_type,
			String transactionId) {
		EduOrder eduOrder = eduOrderMapper.selectByPrimaryKey(Long.parseLong(pay_order_id));
		if(eduOrder != null) {
			eduOrder.setRealpayAmount(AmountMath.divide(new BigDecimal(cash_fee), new BigDecimal(100), 2));
			eduOrder.setPayTime(new Date());
			eduOrder.setOpenId(openid);
			eduOrder.setBankType(bank_type);
			eduOrder.setTransactionId(transactionId);
			eduOrder.setPayType(1);
			eduOrderMapper.updateByPrimaryKeySelective(eduOrder);
		}
		
	}

	public EduOrder getEduOrder(Long orderId) {
		EduOrder eduOrder = eduOrderMapper.selectByPrimaryKey(orderId);
		return eduOrder;
	}
	
	public int getEduOrderCount(Long courseId) {
		EduOrder record = new EduOrder();
		record.setCourseId(courseId);
		record.setSqlStr("and realpay_amount>0");
		record.setStatus(1);
		return eduOrderMapper.selectCount(record);
	}

	public String getEnterData(Long subcourseId,String nickname, String studentCode) {
		if(StringUtils.isBlank(studentCode))
			return null;
		
		EduVideoroomStu record = new EduVideoroomStu();
		record.setStudentCode(studentCode);
		record.setSubcourseId(subcourseId);
		//record.setSqlStr("and create_time<now() and create_time> date_sub(curdate(),interval 1 day)");
		record.setSqlSort("create_time desc");
		List<EduVideoroomStu> list = eduVideoroomStuMapper.selectList(record);
		if(list != null && list.size() > 0) {
			EduVideoroomStu eduVideoroomStu = list.get(0);
			eduVideoroomStu.getRoomId();
			eduVideoroomStu.getWxUid();
			
			WeixinUserinfo weixinUserinfo = weixinUserinfoMapper.selectByPrimaryKey(eduVideoroomStu.getWxUid());
			if(StringUtils.isBlank(nickname)) {
				nickname = weixinUserinfo.getNickname();
			}
			String data = baijiacloudApiService.enterRoomData(eduVideoroomStu.getRoomId(), weixinUserinfo.getId(), nickname, weixinUserinfo.getHeadimgurl());
			return data;
		}
		
		
		
		return null;
	}
	
	public void updateRoomStatus() {
		EduVideoroom record  = new EduVideoroom();
		record.setStatus(1);
		List<EduVideoroom> list = eduVideoroomMapper.selectList(record);
		for(EduVideoroom eduVideoroom: list) {
			EduSubcourse eduSubcourse = eduSubcourseMapper.selectByPrimaryKey(eduVideoroom.getSubcourseId());
			if(eduSubcourse != null && eduSubcourse.getCloseTime().getTime() < System.currentTimeMillis()) {
				eduVideoroom.setStatus(0);
				eduVideoroomMapper.updateByPrimaryKeySelective(eduVideoroom);
			}
		}
	}

	public void saveOrUpdateStudent(String openId, String mobile,Long commendWxuid) {
		WeixinUserinfo weixinUserinfo = getWeixinUserinfo( openId) ;
		if(weixinUserinfo != null) {
			EduStudent record = new EduStudent();
			record.setWxUid(weixinUserinfo.getId());
			EduStudent eduStudent = eduStudentMapper.selectOne(record);
			if(eduStudent != null) {
				eduStudent.setMobile(mobile);
//				record.setCommendWxuid(commendWxuid);
//				eduStudent.setCreateTime(new Date());
//				eduStudent.setModifyTime(new Date());
				eduStudentMapper.updateByPrimaryKeySelective(eduStudent);
			}else {
				eduStudent = new EduStudent();
				eduStudent.setWxUid(weixinUserinfo.getId());
				eduStudent.setMobile(mobile);
				eduStudent.setCommendWxuid(commendWxuid);
				eduStudent.setCreateTime(new Date());
				eduStudent.setModifyTime(new Date());
				eduStudentMapper.insertSelective(eduStudent);
			}
		}
		
		
	}
	
	public List<WeixinUserinfo> getStudentsByCourse(Long courseId) {
		List<WeixinUserinfo> ret = new ArrayList<WeixinUserinfo>();
		EduOrder record = new EduOrder();
		record.setCourseId(courseId);
		record.setSqlStr(" and realpay_amount>0");
		List<EduOrder> list = eduOrderMapper.selectList(record);
		for(EduOrder eduOrder: list) {
				try {
					//System.out.println("eduOrder.getWxUid() =="+eduOrder.getWxUid());
					WeixinUserinfo weixinUserinfo = weixinUserinfoMapper.selectByPrimaryKey(eduOrder.getWxUid());
					if(weixinUserinfo != null)
						weixinUserinfo.setRemark(eduOrder.getTransactionId());
					//System.out.println("weixinUserinfo=="+weixinUserinfo.getOpenid());
					ret.add(weixinUserinfo);
				
				}catch(Exception e) {
					e.printStackTrace();
				}
		}
		return ret;
	}
	
	public void pushOpenCousreNotify(Long subcourseId) {
		EduSubcourse eduSubcourse = eduSubcourseMapper.selectByPrimaryKey(subcourseId);
		EduCourse eduCourse = eduCourseMapper.selectByPrimaryKey(eduSubcourse.getParentId());
		String courseName = eduCourse.getCourseName()+" "+eduSubcourse.getSubName();
		String dateStr = "今天"+DateUtil.getDateStr(eduSubcourse.getOpenTime(), "HH:mm");
		
		EduOrder record = new EduOrder();
		record.setCourseId(eduCourse.getId());
		record.setSqlStr(" and realpay_amount>1");
		List<EduOrder> list = eduOrderMapper.selectList(record);
		for(EduOrder eduOrder: list) {
			//if(eduOrder.getWxUid() == 37362) {
				try {
					WeixinUserinfo weixinUserinfo = weixinUserinfoMapper.selectByPrimaryKey(eduOrder.getWxUid());
					EduVideoroom eduVideoroom = getCourseRoom(subcourseId);
					String studentCode = getOrCreateStudentCode(eduVideoroom, weixinUserinfo.getOpenid(), courseName, dateStr);
					WxApiService wxApiService = (WxApiService)HessianFactoryUtil.getHessianObj(WxApiService.class);
					String url= "http://edu.gdzqyz.cn/qmmtedu/course/enter_room.htm?student_code="+studentCode;
					// "+wxApiService.getShortUrl("gh_a4c6900fdb5a", url)+" 
					String remark = "点击详情开始上课。您也可以复制详情页链接到电脑上打开，获得更好的上课体验（推荐火狐，谷歌浏览器）";
					wxApiService.openNotify("gh_a4c6900fdb5a", weixinUserinfo.getOpenid(),courseName,dateStr,remark,url);
				}catch(Exception e) {
					e.printStackTrace();
				}
			//}
		}
	}

	public List<WeixinUserinfo> getCommStudents(Long commendWxuid) {
		List<WeixinUserinfo> ret = new ArrayList<WeixinUserinfo>();
		EduStudent record = new EduStudent();
		record.setCommendWxuid(commendWxuid);
		List<EduStudent> list = eduStudentMapper.selectList(record);
		for(EduStudent eduStudent: list) {
			WeixinUserinfo weixinUserinfo = weixinUserinfoMapper.selectByPrimaryKey(eduStudent.getWxUid());
			ret.add(weixinUserinfo);
		}
		return ret;
	}
	
	public EduSubcourse getEduSubcourse(Long id) {
		return eduSubcourseMapper.selectByPrimaryKey(id);
	}
	
	
	//以下是管理使用
	
	public List<EduCoursePojo> getEduCourseList(String ctype,Integer intPage,Integer pageSize) {
		List<EduCoursePojo> retList = new ArrayList<EduCoursePojo>();
		EduCourse record = new EduCourse();
		record.setCtype(ctype);
		record.setLimitStart((long)(intPage - 1)*pageSize);
		record.setLimitCount(pageSize);
		record.setSqlSort("open_time desc");
		record.setStatus(1);
		List<EduCourse> list = eduCourseMapper.selectList(record);
		for(EduCourse eduCourse : list) {
			EduCoursePojo eduCoursePojo = new EduCoursePojo();
			BeanCopyUtil.copyProperties(eduCourse, eduCoursePojo);
			eduCoursePojo.setEduTeacher(eduTeacherMapper.selectByPrimaryKey(eduCourse.getTeacherId()));
			retList.add(eduCoursePojo);
		}
		return retList;
	}
	
	public EduCourse getEduCourse(Long id) {
		EduCourse eduCourse = eduCourseMapper.selectByPrimaryKey(id);
	
		//EduCoursePojo eduCoursePojo = new EduCoursePojo();
		//BeanCopyUtil.copyProperties(eduCourse, eduCoursePojo);
		//eduCoursePojo.setEduTeacher(eduTeacherMapper.selectByPrimaryKey(eduCourse.getTeacherId()));
		
		return eduCourse;
	}
	
	public boolean addCourse( String courseName,Integer teacherId, Date openTime,Date closeTime,String ctype, Integer courseNum, BigDecimal courseFee,
					BigDecimal marketCourseFee,String imgUrl,Integer status, String info) {
		EduCourse record = new EduCourse();
		record.setCourseName(courseName);
		record.setTeacherId(teacherId);
		record.setOpenTime(openTime);
		record.setCloseTime(closeTime);
		record.setCtype(ctype);
		record.setCourseNum(courseNum);
		record.setCourseFee(courseFee);
		record.setMarketCourseFee(marketCourseFee);
		record.setImgUrl(imgUrl);
		record.setStatus(status);
		record.setCreateTime(new Date());
		record.setModifyTime(new Date());
		record.setInfo(info);
		eduCourseMapper.insertSelective(record);
		return true;
	}
	
	public boolean editCourse(Long id, String courseName,Integer teacherId, Date openTime,Date closeTime,String ctype, Integer courseNum, BigDecimal courseFee,
			BigDecimal marketCourseFee,String imgUrl,Integer status, String info) {
		EduCourse record = new EduCourse();
		record.setId(id);
		record.setCourseName(courseName);
//		record.setTeacherId(teacherId);
		record.setOpenTime(openTime);
		record.setCloseTime(closeTime);
		record.setCtype(ctype);
		record.setCourseNum(courseNum);
		record.setCourseFee(courseFee);
		record.setMarketCourseFee(marketCourseFee);
		record.setImgUrl(imgUrl);
		record.setStatus(status);
//		record.setCreateTime(new Date());
		record.setModifyTime(new Date());
		record.setInfo(info);
		eduCourseMapper.updateByPrimaryKeySelective(record);
		return true;
	}
	

	public boolean addSubCourse(Long parentId, String subName, Integer studentNum, Date openTime, Date closeTime, 
			Integer status, String backUrl) {
		EduCourse eduCourse = eduCourseMapper.selectByPrimaryKey(parentId);
		if(eduCourse == null)
			return false;
		
		EduSubcourse record = new EduSubcourse();
		record.setParentId(parentId);
		record.setTeacherId(eduCourse.getTeacherId());
		record.setSubName(subName);
		record.setOpenTime(openTime);
		record.setCloseTime(closeTime);
		record.setStudentNum(studentNum);
		record.setStatus(status);
		record.setBackUrl(backUrl);
		record.setCreateTime(new Date());
		record.setModifyTime(new Date());
		eduSubcourseMapper.insertSelective(record);
		return true;
	}

	public boolean editSubCourse(Long id, String subName, Integer studentNum, Date openTime, Date closeTime, 
			Integer status, String backUrl) {
		EduSubcourse record = new EduSubcourse();
		record.setId(id);
		record.setSubName(subName);
		record.setOpenTime(openTime);
		record.setCloseTime(closeTime);
		record.setStudentNum(studentNum);
		record.setStatus(status);
		record.setBackUrl(backUrl);
		record.setModifyTime(new Date());
		eduSubcourseMapper.updateByPrimaryKeySelective(record);
		return true;
	}
	

}
