package com.rensu.education.hctms.basicdata.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.basicdata.bean.MarksheetSub;
import com.rensu.education.hctms.basicdata.dao.MarksheetSubDao;


@Service("marksheetSubService")
public class MarksheetSubService extends BaseService<MarksheetSub> {
	
	Logger log = Logger.getLogger(MarksheetSubService.class);
	
	@Autowired
	protected MarksheetSubDao marksheetSubDao;

}
