package com.rensu.education.hctms.message.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class MessagePublish extends BaseBean {
	
	
	private String send_time_str;//   发送时间  字符串格式
	public String getSend_time_str() {
		return send_time_str;
	}
	public void setSend_time_str(String send_time_str) {
		this.send_time_str = send_time_str;
	}
}