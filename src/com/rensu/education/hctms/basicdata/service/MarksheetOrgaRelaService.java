package com.rensu.education.hctms.basicdata.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.basicdata.bean.MarksheetOrgaRela;
import com.rensu.education.hctms.basicdata.dao.MarksheetOrgaRelaDao;


@Service("marksheetOrgaRelaService")
public class MarksheetOrgaRelaService extends BaseService<MarksheetOrgaRela> {
	
	Logger log = Logger.getLogger(MarksheetOrgaRelaService.class);
	
	@Autowired
	protected MarksheetOrgaRelaDao marksheetOrgaRelaDao;

}
