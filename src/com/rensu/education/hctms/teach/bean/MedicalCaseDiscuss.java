package com.rensu.education.hctms.teach.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class MedicalCaseDiscuss extends BaseBean {
	
	
	private String user_name;
	private Integer creator_id;
	private String discuss_time_str;
	
		return discuss_time_str;
	}
	public void setDiscuss_time_str(String discuss_time_str) {
		this.discuss_time_str = discuss_time_str;
	}
	public Integer getId() {
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Integer getCreator_id() {
		return creator_id;
	}
	public void setCreator_id(Integer creator_id) {
		this.creator_id = creator_id;
	}
	
}