package com.rensu.education.hctms.teach.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class ReviewLeaveInfo extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private Integer stu_auth_id;//   学生权限ID	private Integer orga_id;//   所在科室ID	private String content;//   申请内容	private Integer vacate_status;//   当前流程状态（对应PROCESS_INFO表中“VACATE”的ID）	private String state;//   状态	private java.sql.Timestamp start_time;//   请假开始时间	private java.sql.Timestamp end_time;//   请假结束时间	private String start_time_str;//   请假开始时间	private String end_time_str;//   请假结束时间	private Integer start_date;//   请假开始时间（20170101）	private Integer end_date;//   请假结束时间（20170101）	private String vacate_type_code;//   请假类型CODE	private String vacate_type_name;//   请假类型名称
	private String create_time_str;  //
	private String stu_create_time_str;
	private java.sql.Timestamp create_time; 
	private Integer create_auth_id;//   操作老师权限ID 
	private String proc_name;//   当前流程名称
	private String orga_name;//所在科室名称
	private String stu_name;//学生姓名 
	private Integer proc_result;//   审批结果 
	private String proc_content;//审批意见
	private String flag;//本审批流程前面是否有审批流程的标志 
	private Integer proc_num;//  
	private Integer create_role_id;//   操作老师角色ID 
	private String role;//  老师角色/秘书角色  
	private Integer end_proc_num;//  
	private String start_proc_name;//
	private String end_proc_name;//
	private String proc_result_str;// 
	private String user_name;// 
	private Integer related_id;//  
	private Integer role_id;//   
	private Integer vacate_date_num;//   
	private String stu_num;
	
	
	public String getStu_num() {
		return stu_num;
	}
	public void setStu_num(String stu_num) {
		this.stu_num = stu_num;
	}
	public String getStu_create_time_str() {
		return stu_create_time_str;
	}
	public void setStu_create_time_str(String stu_create_time_str) {
		this.stu_create_time_str = stu_create_time_str;
	}
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	public Integer getRelated_id() {
		return related_id;
	}
	public void setRelated_id(Integer related_id) {
		this.related_id = related_id;
	}
	public String getStart_proc_name() {
		return start_proc_name;
	}
	public void setStart_proc_name(String start_proc_name) {
		this.start_proc_name = start_proc_name;
	}
	public String getEnd_proc_name() {
		return end_proc_name;
	}
	public void setEnd_proc_name(String end_proc_name) {
		this.end_proc_name = end_proc_name;
	}
	public String getProc_result_str() {
		return proc_result_str;
	}
	public void setProc_result_str(String proc_result_str) {
		this.proc_result_str = proc_result_str;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Integer getEnd_proc_num() {
		return end_proc_num;
	}
	public void setEnd_proc_num(Integer end_proc_num) {
		this.end_proc_num = end_proc_num;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Integer getCreate_role_id() {
		return create_role_id;
	}
	public void setCreate_role_id(Integer create_role_id) {
		this.create_role_id = create_role_id;
	}
	public Integer getProc_num() {
		return proc_num;
	}
	public void setProc_num(Integer proc_num) {
		this.proc_num = proc_num;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getProc_content() {
		return proc_content;
	}
	public void setProc_content(String proc_content) {
		this.proc_content = proc_content;
	}
	public Integer getProc_result() {
		return proc_result;
	}
	public void setProc_result(Integer proc_result) {
		this.proc_result = proc_result;
	}
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
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
	}	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getStu_auth_id() {	    return this.stu_auth_id;	}	public void setStu_auth_id(Integer stu_auth_id) {	    this.stu_auth_id=stu_auth_id;	}	public Integer getOrga_id() {	    return this.orga_id;	}	public void setOrga_id(Integer orga_id) {	    this.orga_id=orga_id;	}	public String getContent() {	    return this.content;	}	public void setContent(String content) {	    this.content=content;	}	public Integer getVacate_status() {	    return this.vacate_status;	}	public void setVacate_status(Integer vacate_status) {	    this.vacate_status=vacate_status;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}	public java.sql.Timestamp getStart_time() {	    return this.start_time;	}	public void setStart_time(java.sql.Timestamp start_time) {	    this.start_time=start_time;	}	public java.sql.Timestamp getEnd_time() {	    return this.end_time;	}	public void setEnd_time(java.sql.Timestamp end_time) {	    this.end_time=end_time;	}	public Integer getStart_date() {	    return this.start_date;	}	public void setStart_date(Integer start_date) {	    this.start_date=start_date;	}	public Integer getEnd_date() {	    return this.end_date;	}	public void setEnd_date(Integer end_date) {	    this.end_date=end_date;	}	public String getVacate_type_code() {	    return this.vacate_type_code;	}	public void setVacate_type_code(String vacate_type_code) {	    this.vacate_type_code=vacate_type_code;	}	public String getVacate_type_name() {	    return this.vacate_type_name;	}	public void setVacate_type_name(String vacate_type_name) {	    this.vacate_type_name=vacate_type_name;	}
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
	public Integer getVacate_date_num() {
		return vacate_date_num;
	}
	public void setVacate_date_num(Integer vacate_date_num) {
		this.vacate_date_num = vacate_date_num;
	}
	
}
