package com.rensu.education.hctms.userauth.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.userauth.bean.StuClass;
import com.rensu.education.hctms.userauth.dao.StuClassDao;


@Service("stuClassService")
public class StuClassService extends BaseService<StuClass> {
	
	Logger log = Logger.getLogger(StuClassService.class);
	
	@Autowired
	protected StuClassDao stuClassDao;

}
