package com.rensu.education.hctms.basicdata.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.basicdata.bean.ExamineTypeContent;
import com.rensu.education.hctms.basicdata.dao.ExamineTypeContentDao;


@Service("examineTypeContentService")
public class ExamineTypeContentService extends BaseService<ExamineTypeContent> {
	
	Logger log = Logger.getLogger(ExamineTypeContentService.class);
	
	@Autowired
	protected ExamineTypeContentDao examineTypeContentDao;

}
