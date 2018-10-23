package com.rensu.education.hctms.userauth.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.userauth.bean.StaffInfo;
import com.rensu.education.hctms.userauth.dao.StaffInfoDao;


@Service("staffInfoService")
public class StaffInfoService extends BaseService<StaffInfo> {
	
	Logger log = Logger.getLogger(StaffInfoService.class);
	
	@Autowired
	protected StaffInfoDao staffInfoDao;

	public StaffInfo selectOneByUserCode(String user_code) {		
		return staffInfoDao.selectOneByUserCode(user_code);
	}

}
