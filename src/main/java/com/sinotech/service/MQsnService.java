package com.sinotech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinotech.mapper.MQsnMapper;

@Service
public class MQsnService {

	@Autowired
	private MQsnMapper mQsnMapper;
	
	public int addMQsn(String sn) {
		return mQsnMapper.addMQsn(sn);
	}
}
