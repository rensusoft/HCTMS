package com.rensu.education.hctms.basicdata.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class HtmlTemplate extends BaseBean {
	
	
	private String user_name;//   模板名称
	private String time;
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}