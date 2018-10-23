package com.rensu.education.hctms.log.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.log.bean.ProcrecordUserRela;
import com.rensu.education.hctms.log.dao.ProcrecordUserRelaDao;


@Service("procrecordUserRelaService")
public class ProcrecordUserRelaService extends BaseService<ProcrecordUserRela> {
	
	Logger log = Logger.getLogger(ProcrecordUserRelaService.class);
	
	@Autowired
	protected ProcrecordUserRelaDao procrecordUserRelaDao;

}
