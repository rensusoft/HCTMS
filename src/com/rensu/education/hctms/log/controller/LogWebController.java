package com.rensu.education.hctms.log.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rensu.education.hctms.log.service.ProcessRecordService;
 
@Controller
@RequestMapping(value="/logweb")
public class LogWebController{
	
	Logger log = Logger.getLogger(LogWebController.class);
	
	@Autowired
	protected ProcessRecordService processRecordService;

}
