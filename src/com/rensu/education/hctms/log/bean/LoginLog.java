package com.rensu.education.hctms.log.bean;

import java.sql.Timestamp;

import com.rensu.education.hctms.core.bean.BaseBean;

public class LoginLog extends BaseBean {
	
	private static final long serialVersionUID = 1L;
	
	public LoginLog(){};
	
	public LoginLog(Integer id, Integer user_auth_id, Integer login_type,
			Integer create_auth_id, Integer create_date, Timestamp create_time,
			String ip, String remark) {
		this.id = id;
		this.user_auth_id = user_auth_id;
		this.login_type = login_type;
		this.create_auth_id = create_auth_id;
		this.create_date = create_date;
		this.create_time = create_time;
		this.ip = ip;
		this.remark = remark;
	}
	
}