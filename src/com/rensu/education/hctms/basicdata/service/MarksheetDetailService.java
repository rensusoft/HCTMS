package com.rensu.education.hctms.basicdata.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.basicdata.bean.MarksheetDetail;
import com.rensu.education.hctms.basicdata.dao.MarksheetDetailDao;


@Service("marksheetDetailService")
public class MarksheetDetailService extends BaseService<MarksheetDetail> {
	
	Logger log = Logger.getLogger(MarksheetDetailService.class);
	
	@Autowired
	protected MarksheetDetailDao marksheetDetailDao;

}
