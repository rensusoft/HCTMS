package com.rensu.education.hctms.userauth.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.userauth.bean.StuType;
import com.rensu.education.hctms.userauth.dao.StuTypeDao;


@Service("stuTypeService")
public class StuTypeService extends BaseService<StuType> {
	
	Logger log = Logger.getLogger(StuTypeService.class);
	
	@Autowired
	protected StuTypeDao stuTypeDao;

}
