package com.qmmt.edu.remoteservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qmmt.edu.persistence.po.WeixinUserinfo;
import com.qmmt.edu.service.EduCourseService;
import com.qmmt.edu.util.StringUtil;

@Service("qmmtApiService")  
public class QmmtApiServiceImpl implements QmmtApiService{
	
	@Autowired
	EduCourseService eduCourseService;

	@Override
	public String subWxCode(String wxAccountid, String openId, String EventKey) {
		System.out.println(wxAccountid +"----"+openId +"------"+EventKey);
		//qrscene_37362
		if(EventKey != null && EventKey.indexOf("_") != -1 && StringUtil.isNumeric(EventKey.split("_")[1]))
			eduCourseService.saveOrUpdateStudent(openId, null, Long.parseLong(EventKey.split("_")[1]));
		
		return null;
	
	}

}
