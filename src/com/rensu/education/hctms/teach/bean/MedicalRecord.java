package com.rensu.education.hctms.teach.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class MedicalRecord extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private String pk_code;//   主键ID（用于老师批改后的对比，学生书写与老师批改通过此字段比对）	private Integer type_id;//   文书类型ID（1：入院记录；2：病程记录；3：其他文书）	private String mr_name;//   医疗文书名称	private String p_name;//   病人姓名	private String p_sex;//   病人性别	private String p_age;//   病人年龄	private String p_deptname;//   病人科室	private String p_bednum;//   病人床号	private String p_pid;//   病人住院号	private String content;//   内容	private Integer stu_auth_id;//   学生authId	private java.sql.Timestamp create_time;//   书写时间	private Integer correct_auth_id;//   批改人auth_id	private java.sql.Timestamp correct_time;//   批改时间	private String correct_status;//   批改状态（N：未批改；Y：已批改）	private String state;//   状态
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
	
	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getPk_code() {	    return this.pk_code;	}	public void setPk_code(String pk_code) {	    this.pk_code=pk_code;	}	public Integer getType_id() {	    return this.type_id;	}	public void setType_id(Integer type_id) {	    this.type_id=type_id;	}	public String getMr_name() {	    return this.mr_name;	}	public void setMr_name(String mr_name) {	    this.mr_name=mr_name;	}	public String getP_name() {	    return this.p_name;	}	public void setP_name(String p_name) {	    this.p_name=p_name;	}	public String getP_sex() {	    return this.p_sex;	}	public void setP_sex(String p_sex) {	    this.p_sex=p_sex;	}	public String getP_age() {	    return this.p_age;	}	public void setP_age(String p_age) {	    this.p_age=p_age;	}	public String getP_deptname() {	    return this.p_deptname;	}	public void setP_deptname(String p_deptname) {	    this.p_deptname=p_deptname;	}	public String getP_bednum() {	    return this.p_bednum;	}	public void setP_bednum(String p_bednum) {	    this.p_bednum=p_bednum;	}	public String getP_pid() {	    return this.p_pid;	}	public void setP_pid(String p_pid) {	    this.p_pid=p_pid;	}	public String getContent() {	    return this.content;	}	public void setContent(String content) {	    this.content=content;	}	public Integer getStu_auth_id() {	    return this.stu_auth_id;	}	public void setStu_auth_id(Integer stu_auth_id) {	    this.stu_auth_id=stu_auth_id;	}	public java.sql.Timestamp getCreate_time() {	    return this.create_time;	}	public void setCreate_time(java.sql.Timestamp create_time) {	    this.create_time=create_time;	}	public Integer getCorrect_auth_id() {	    return this.correct_auth_id;	}	public void setCorrect_auth_id(Integer correct_auth_id) {	    this.correct_auth_id=correct_auth_id;	}	public java.sql.Timestamp getCorrect_time() {	    return this.correct_time;	}	public void setCorrect_time(java.sql.Timestamp correct_time) {	    this.correct_time=correct_time;	}	public String getCorrect_status() {	    return this.correct_status;	}	public void setCorrect_status(String correct_status) {	    this.correct_status=correct_status;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
	
}
