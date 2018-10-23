package com.rensu.education.hctms.teach.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class TaskPublish extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private String title;//   标题	private String content;//   内容	private String orga_id;//   科室ID	private String publisher_auth_id;//   发布人authId	private java.sql.Timestamp publish_time;//   发布时间	private String task_type_code;//   任务类型ID	private String task_type_name;//   任务类型名称	private String progress_state;//   进度（1已发布；2已处理；3完成）状态为1时可以修改，2和3时不可修改	private String state;//   状态
	private String progress_stateStr;
	private String publish_timeStr;
	private String start_date;
	private String end_date;
	private String stuList;
		public String getStuList() {
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
	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getTitle() {	    return this.title;	}	public void setTitle(String title) {	    this.title=title;	}	public String getContent() {	    return this.content;	}	public void setContent(String content) {	    this.content=content;	}	public String getOrga_id() {	    return this.orga_id;	}	public void setOrga_id(String orga_id) {	    this.orga_id=orga_id;	}	public String getPublisher_auth_id() {	    return this.publisher_auth_id;	}	public void setPublisher_auth_id(String publisher_auth_id) {	    this.publisher_auth_id=publisher_auth_id;	}	public java.sql.Timestamp getPublish_time() {	    return this.publish_time;	}	public void setPublish_time(java.sql.Timestamp publish_time) {	    this.publish_time=publish_time;	}	public String getTask_type_code() {	    return this.task_type_code;	}	public void setTask_type_code(String task_type_code) {	    this.task_type_code=task_type_code;	}	public String getTask_type_name() {	    return this.task_type_name;	}	public void setTask_type_name(String task_type_name) {	    this.task_type_name=task_type_name;	}	public String getProgress_state() {	    return this.progress_state;	}	public void setProgress_state(String progress_state) {	    this.progress_state=progress_state;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
}
