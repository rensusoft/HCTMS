package com.rensu.education.hctms.basicdata.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.basicdata.bean.RprFormRela;
import com.rensu.education.hctms.basicdata.dao.RprFormRelaDao;


@Service("rprFormRelaService")
public class RprFormRelaService extends BaseService<RprFormRela> {
	
	Logger log = Logger.getLogger(RprFormRelaService.class);
	
	@Autowired
	protected RprFormRelaDao rprFormRelaDao;

}
