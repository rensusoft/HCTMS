package com.rensu.education.hctms.userauth.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.userauth.bean.VUserDetailInfo;
import com.rensu.education.hctms.userauth.dao.VUserDetailInfoDao;


@Service("vUserDetailInfoService")
public class VUserDetailInfoService extends BaseService<VUserDetailInfo> {
	
	Logger log = Logger.getLogger(VUserDetailInfoService.class);
	
	@Autowired
	protected VUserDetailInfoDao vUserDetailInfoDao;

}
