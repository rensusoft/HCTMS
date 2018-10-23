package com.rensu.education.hctms.basicdata.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.basicdata.bean.MarksheetItems;
import com.rensu.education.hctms.basicdata.dao.MarksheetItemsDao;


@Service("marksheetItemsService")
public class MarksheetItemsService extends BaseService<MarksheetItems> {
	
	Logger log = Logger.getLogger(MarksheetItemsService.class);
	
	@Autowired
	protected MarksheetItemsDao marksheetItemsDao;

}
