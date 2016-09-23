package com.berwin.cloud.service.impl;

import com.berwin.cloud.service.BaseDateService;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.berwin.cloud.dao.BaseDateMapper;
import com.berwin.cloud.model.BaseDate;

@Service("BaseDateService")
public class BaseDateServiceImpl implements BaseDateService {
	
	private static final Logger logger = Logger.getLogger(BaseDateServiceImpl.class);
	
	@Autowired
	private BaseDateMapper baseDateMapper;

	@Override
	public List<BaseDate> find() {
		logger.info("service get data is : " + baseDateMapper.find());
		return baseDateMapper.find();
	}


	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	/**
	 * spring boot 定时任务
	 */
	@Scheduled(cron="0 * * * * ?")
	public void reportCurrentTime() {
		System.out.println("CurrentTime:" + dateFormat.format(new Date()));
	}
	
}
