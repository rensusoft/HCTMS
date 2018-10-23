package com.rensu.education.hctms.teach.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class StuVacateInfo extends BaseBean {
	
	
	private static final long serialVersionUID = 1L;
	private Integer id;//   标识
	private Integer stu_auth_id;//   学生权限ID
	private Integer orga_id;//   所在科室ID
	private String content;//   申请内容
	private Integer vacate_status;//   当前流程状态（对应PROCESS_INFO表中“VACATE”的ID）
	private String state;//   状态
	private java.sql.Timestamp start_time;//   请假开始时间
	private java.sql.Timestamp end_time;//   请假结束时间
	private String start_time_str;//   请假开始时间
	private String end_time_str;//   请假结束时间
	private Integer start_date;//   请假开始时间（20170101）
	private Integer end_date;//   请假结束时间（20170101）
	private String vacate_type_code;//   请假类型CODE
	private String vacate_type_name;//   请假类型名称
	private String create_time_str;
	private java.sql.Timestamp create_time;
	private Integer create_auth_id;
	private String proc_name;//   当前流程名称
	private String orga_name;//所在科室名称
	private Integer vacate_date_num; //请假天数
	private String vacate_time; //请假时间
	private Integer hos_id; //附属医院ID
	private Integer proc_result; 
	private String user_name;
	
	private String train_status_str;
	private Integer teacher_auth_id;
	private Integer role_id;
	private String vacate_date_num_str;
	private String stu_name; //学生姓名
	private Integer end_proc_id;
	
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	public String getVacate_date_num_str() {
		return vacate_date_num_str;
	}
	public void setVacate_date_num_str(String vacate_date_num_str) {
		this.vacate_date_num_str = vacate_date_num_str;
	}
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	public Integer getTeacher_auth_id() {
		return teacher_auth_id;
	}
	public void setTeacher_auth_id(Integer teacher_auth_id) {
		this.teacher_auth_id = teacher_auth_id;
	}
	public String getTrain_status_str() {
		return train_status_str;
	}
	public void setTrain_status_str(String train_status_str) {
		this.train_status_str = train_status_str;
	}
	public Integer getHos_id() {
		return hos_id;
	}
	public void setHos_id(Integer hos_id) {
		this.hos_id = hos_id;
	}
	public Integer getVacate_date_num() {
		return vacate_date_num;
	}
	public void setVacate_date_num(Integer vacate_date_num) {
		this.vacate_date_num = vacate_date_num;
	}
	public String getCreate_time_str() {
		return create_time_str;
	}
	public void setCreate_time_str(String create_time_str) {
		this.create_time_str = create_time_str;
	}
	public java.sql.Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(java.sql.Timestamp create_time) {
		this.create_time = create_time;
	}
	public Integer getCreate_auth_id() {
		return create_auth_id;
	}
	public void setCreate_auth_id(Integer create_auth_id) {
		this.create_auth_id = create_auth_id;
	}
	public Integer getId() {
	    return this.id;
	}
	public void setId(Integer id) {
	    this.id=id;
	}
	public Integer getStu_auth_id() {
	    return this.stu_auth_id;
	}
	public void setStu_auth_id(Integer stu_auth_id) {
	    this.stu_auth_id=stu_auth_id;
	}
	public Integer getOrga_id() {
	    return this.orga_id;
	}
	public void setOrga_id(Integer orga_id) {
	    this.orga_id=orga_id;
	}
	public String getContent() {
	    return this.content;
	}
	public void setContent(String content) {
	    this.content=content;
	}
	public Integer getVacate_status() {
	    return this.vacate_status;
	}
	public void setVacate_status(Integer vacate_status) {
	    this.vacate_status=vacate_status;
	}
	public String getState() {
	    return this.state;
	}
	public void setState(String state) {
	    this.state=state;
	}
	public java.sql.Timestamp getStart_time() {
	    return this.start_time;
	}
	public void setStart_time(java.sql.Timestamp start_time) {
	    this.start_time=start_time;
	}
	public java.sql.Timestamp getEnd_time() {
	    return this.end_time;
	}
	public void setEnd_time(java.sql.Timestamp end_time) {
	    this.end_time=end_time;
	}
	public Integer getStart_date() {
	    return this.start_date;
	}
	public void setStart_date(Integer start_date) {
	    this.start_date=start_date;
	}
	public Integer getEnd_date() {
	    return this.end_date;
	}
	public void setEnd_date(Integer end_date) {
	    this.end_date=end_date;
	}
	public String getVacate_type_code() {
	    return this.vacate_type_code;
	}
	public void setVacate_type_code(String vacate_type_code) {
	    this.vacate_type_code=vacate_type_code;
	}
	public String getVacate_type_name() {
	    return this.vacate_type_name;
	}
	public void setVacate_type_name(String vacate_type_name) {
	    this.vacate_type_name=vacate_type_name;
	}
	public String getStart_time_str() {
		return start_time_str;
	}
	public void setStart_time_str(String start_time_str) {
		this.start_time_str = start_time_str;
	}
	public String getEnd_time_str() {
		return end_time_str;
	}
	public void setEnd_time_str(String end_time_str) {
		this.end_time_str = end_time_str;
	}
	public String getProc_name() {
		return proc_name;
	}
	public void setProc_name(String proc_name) {
		this.proc_name = proc_name;
	}
	public String getOrga_name() {
		return orga_name;
	}
	public void setOrga_name(String orga_name) {
		this.orga_name = orga_name;
	}
	public String getVacate_time() {
		return vacate_time;
	}
	public void setVacate_time(String vacate_time) {
		this.vacate_time = vacate_time;
	}
	public Integer getProc_result() {
		return proc_result;
	}
	public void setProc_result(Integer proc_result) {
		this.proc_result = proc_result;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Integer getEnd_proc_id() {
		return end_proc_id;
	}
	public void setEnd_proc_id(Integer end_proc_id) {
		this.end_proc_id = end_proc_id;
	}
	
}
