package com.qmmt.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qmmt.edu.persistence.dao.SysSeqMapper;
import com.qmmt.edu.persistence.po.SysSeq;

@Service("sysSeqService")
public class SysSeqService {
	
	@Autowired
	SysSeqMapper sysSeqMapper;
	
	
	@Transactional 
	public Long getSeqNextval(String seqname){
		SysSeq record = new SysSeq();
		record.setName(seqname);
		SysSeq sysSeq = sysSeqMapper.selectOne(record);
		sysSeq.setCurrid(sysSeq.getCurrid()+sysSeq.getIncrement());
		sysSeqMapper.updateByPrimaryKeySelective(sysSeq);
		return sysSeq.getCurrid();
	}
	

}
