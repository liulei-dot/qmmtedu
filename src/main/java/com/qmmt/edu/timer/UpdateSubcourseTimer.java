package com.qmmt.edu.timer;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.qmmt.edu.service.EduCourseService;

@Component
public class UpdateSubcourseTimer {
	
	@Autowired
	EduCourseService eduCourseService;

	//@Scheduled(cron="0 10 22 ? * *")  //"  1 * 0 * * ? //每天22点10分
	//@Scheduled(fixedDelay=180000)  //3分钟一次任务
	@Scheduled(cron = "0/60 * *  * * ? ")//每隔60秒隔行一次 
	public void run() {
		//System.out.println(new Date()+" time update token start ------");
		
		eduCourseService.updateRoomStatus();
		
	}

}
