package com.rensu.education.hctms.teach.bean;

import java.util.List;

import com.rensu.education.hctms.basicdata.bean.ExamineTypeContent;
import com.rensu.education.hctms.core.bean.BaseBean;

public class TaskDeal extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private Integer tp_id;//   任务发布表标识	private Integer receiver_auth_id;//   接收人权限标识(学生authId)_	private java.sql.Timestamp receive_time;//   接收时间	private String content;//   内容	private java.sql.Timestamp finish_time;//   完成时间	private String progress_state;//   进度（1未处理；2已处理；3已评分）	private String state;//   状态
	private String start_date;
	private String end_date;
	private String task_type_code;
	private String task_type_name;
	private String publish_time;
	private String user_name;
	private String progress_stateStr;
	private String title;
	private String content_teach;
	private String stuName;
	private List<ExamineTypeContent> contents;
	
		public List<ExamineTypeContent> getContents() {
		return contents;
	}
	public void setContents(List<ExamineTypeContent> contents) {
		this.contents = contents;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getContent_teach() {
		return content_teach;
	}
	public void setContent_teach(String content_teach) {
		this.content_teach = content_teach;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPublish_time() {
		return publish_time;
	}
	public void setPublish_time(String publish_time) {
		this.publish_time = publish_time;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getProgress_stateStr() {
		return progress_stateStr;
	}
	public void setProgress_stateStr(String progress_stateStr) {
		this.progress_stateStr = progress_stateStr;
	}
	public String getTask_type_name() {
		return task_type_name;
	}
	public void setTask_type_name(String task_type_name) {
		this.task_type_name = task_type_name;
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
	public String getTask_type_code() {
		return task_type_code;
	}
	public void setTask_type_code(String task_type_code) {
		this.task_type_code = task_type_code;
	}
	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getTp_id() {	    return this.tp_id;	}	public void setTp_id(Integer tp_id) {	    this.tp_id=tp_id;	}	public Integer getReceiver_auth_id() {	    return this.receiver_auth_id;	}	public void setReceiver_auth_id(Integer receiver_auth_id) {	    this.receiver_auth_id=receiver_auth_id;	}	public java.sql.Timestamp getReceive_time() {	    return this.receive_time;	}	public void setReceive_time(java.sql.Timestamp receive_time) {	    this.receive_time=receive_time;	}	public String getContent() {	    return this.content;	}	public void setContent(String content) {	    this.content=content;	}	public java.sql.Timestamp getFinish_time() {	    return this.finish_time;	}	public void setFinish_time(java.sql.Timestamp finish_time) {	    this.finish_time=finish_time;	}	public String getProgress_state() {	    return this.progress_state;	}	public void setProgress_state(String progress_state) {	    this.progress_state=progress_state;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
}
