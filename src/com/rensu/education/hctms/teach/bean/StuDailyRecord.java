package com.rensu.education.hctms.teach.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class StuDailyRecord extends BaseBean {
	
	
	private String type_id_str; //类型ID的string展现
	private String create_time_str;//创建时间的string展现
	private String searchStr;//关键字
	private Integer teacher_auth_id;
	private String flag;
	private String week;
	private String timeBegin;
	private String timeEnd;
	private String userName;
	private String state_str;
	private String create_time_dateStr;//书写日期
	private String summary;
	
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getCreate_time_dateStr() {
		return create_time_dateStr;
	}
	public void setCreate_time_dateStr(String create_time_dateStr) {
		this.create_time_dateStr = create_time_dateStr;
	}
	public String getState_str() {
		return state_str;
	}
	public void setState_str(String state_str) {
		this.state_str = state_str;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getTimeBegin() {
		return timeBegin;
	}
	public void setTimeBegin(String timeBegin) {
		this.timeBegin = timeBegin;
	}
	public String getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}
	public Integer getTeacher_auth_id() {
		return teacher_auth_id;
	}
	public void setTeacher_auth_id(Integer teacher_auth_id) {
		this.teacher_auth_id = teacher_auth_id;
	}
	public String getSearchStr() {
		return searchStr;
	}
	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}
	public String getCreate_time_str() {
		return create_time_str;
	}
	public void setCreate_time_str(String create_time_str) {
		this.create_time_str = create_time_str;
	}
	public String getType_id_str() {
		return type_id_str;
	}
	public void setType_id_str(String type_id_str) {
		this.type_id_str = type_id_str;
	}
	public Integer getId() {
}