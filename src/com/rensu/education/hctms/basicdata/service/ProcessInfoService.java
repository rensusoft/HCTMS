package com.rensu.education.hctms.basicdata.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.basicdata.bean.ProcessInfo;
import com.rensu.education.hctms.basicdata.dao.ProcessInfoDao;


@Service("processInfoService")
public class ProcessInfoService extends BaseService<ProcessInfo> {
	
	Logger log = Logger.getLogger(ProcessInfoService.class);
	
	@Autowired
	protected ProcessInfoDao processInfoDao;

}
