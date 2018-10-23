package com.rensu.education.hctms.teach.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class MedicalRecord extends BaseBean {
	
	
	private Integer teacher_auth_id;
	private String start_date;
	private String type_id_str;
    private String create_time_str;
	private String correct_auth_id_str;
	private String correct_status_str;
	private String end_date;
	private String stu_auth_id_str;
	private String correct_time_str;
	private String correct_name;
	
	public String getCorrect_time_str() {
		return correct_time_str;
	}
	public void setCorrect_time_str(String correct_time_str) {
		this.correct_time_str = correct_time_str;
	}
	public String getStu_auth_id_str() {
		return stu_auth_id_str;
	}
	public void setStu_auth_id_str(String stu_auth_id_str) {
		this.stu_auth_id_str = stu_auth_id_str;
	}
	public String getCorrect_name() {
		return correct_name;
	}
	public void setCorrect_name(String correct_name) {
		this.correct_name = correct_name;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getType_id_str() {
		return type_id_str;
	}
	public void setType_id_str(String type_id_str) {
		this.type_id_str = type_id_str;
	}
	public String getCreate_time_str() {
		return create_time_str;
	}
	public void setCreate_time_str(String create_time_str) {
		this.create_time_str = create_time_str;
	}
	public String getCorrect_auth_id_str() {
		return correct_auth_id_str;
	}
	public void setCorrect_auth_id_str(String correct_auth_id_str) {
		this.correct_auth_id_str = correct_auth_id_str;
	}
	public String getCorrect_status_str() {
		return correct_status_str;
	}
	public void setCorrect_status_str(String correct_status_str) {
		this.correct_status_str = correct_status_str;
	}
	public Integer getTeacher_auth_id() {
		return teacher_auth_id;
	}
	public void setTeacher_auth_id(Integer teacher_auth_id) {
		this.teacher_auth_id = teacher_auth_id;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	
	public Integer getId() {
	
}