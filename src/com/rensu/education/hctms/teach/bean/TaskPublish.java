package com.rensu.education.hctms.teach.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class TaskPublish extends BaseBean {
	
	
	private String progress_stateStr;
	private String publish_timeStr;
	private String start_date;
	private String end_date;
	private String stuList;
	
		return stuList;
	}
	public void setStuList(String stuList) {
		this.stuList = stuList;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getPublish_timeStr() {
		return publish_timeStr;
	}
	public void setPublish_timeStr(String publish_timeStr) {
		this.publish_timeStr = publish_timeStr;
	}
	public String getProgress_stateStr() {
		return progress_stateStr;
	}
	public void setProgress_stateStr(String progress_stateStr) {
		this.progress_stateStr = progress_stateStr;
	}
	public Integer getId() {
}