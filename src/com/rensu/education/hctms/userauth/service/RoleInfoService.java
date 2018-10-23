package com.rensu.education.hctms.userauth.service;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.userauth.bean.RoleInfo;
import com.rensu.education.hctms.userauth.dao.RoleInfoDao;


@Service("RoleInfoService")
public class RoleInfoService extends BaseService<RoleInfo> {
	
	Logger log = Logger.getLogger(RoleInfoService.class);
	
	@Autowired
	protected RoleInfoDao roleInfoDao;
	/**
	 * 查询所有非学生角色
	 * @return
	 *@author huq
	 *@date 2017年1月17日
	 */
	public List<RoleInfo> selectTeacherList() {
		return roleInfoDao.selectTeacherList();
	}

}
