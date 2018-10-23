package com.rensu.education.hctms.log.bean;

import java.sql.Timestamp;

import com.rensu.education.hctms.core.bean.BaseBean;

public class LoginLog extends BaseBean {
	
	private static final long serialVersionUID = 1L;	private Integer id;//   标识	private Integer user_auth_id;//   工号	private Integer login_type;//   登录类型1-登录系统，2-退出系统。	private Integer create_auth_id;//   创建人权限标识	private Integer create_date;//   创建日期	private java.sql.Timestamp create_time;//   创建时间	private String ip;//   IP地址	private String remark;//   备注
	
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
		public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getUser_auth_id() {	    return this.user_auth_id;	}	public void setUser_auth_id(Integer user_auth_id) {	    this.user_auth_id=user_auth_id;	}	public Integer getLogin_type() {	    return this.login_type;	}	public void setLogin_type(Integer login_type) {	    this.login_type=login_type;	}	public Integer getCreate_auth_id() {	    return this.create_auth_id;	}	public void setCreate_auth_id(Integer create_auth_id) {	    this.create_auth_id=create_auth_id;	}	public Integer getCreate_date() {	    return this.create_date;	}	public void setCreate_date(Integer create_date) {	    this.create_date=create_date;	}	public java.sql.Timestamp getCreate_time() {	    return this.create_time;	}	public void setCreate_time(java.sql.Timestamp create_time) {	    this.create_time=create_time;	}	public String getIp() {	    return this.ip;	}	public void setIp(String ip) {	    this.ip=ip;	}	public String getRemark() {	    return this.remark;	}	public void setRemark(String remark) {	    this.remark=remark;	}
}
