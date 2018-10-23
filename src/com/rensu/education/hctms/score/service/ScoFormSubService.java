package com.rensu.education.hctms.score.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.score.bean.ScoFormSub;
import com.rensu.education.hctms.score.dao.ScoFormSubDao;


@Service("scoFormSubService")
public class ScoFormSubService extends BaseService<ScoFormSub> {
	
	Logger log = Logger.getLogger(ScoFormSubService.class);
	
	@Autowired
	protected ScoFormSubDao scoFormSubDao;

}
