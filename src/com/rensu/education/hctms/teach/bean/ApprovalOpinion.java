package com.rensu.education.hctms.teach.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class ApprovalOpinion extends BaseBean {
	
	private Integer id;//请假申请ID	private static final long serialVersionUID = 1L;
	private java.sql.Timestamp create_time;//审批时间
	private Integer create_auth_id;//审批人权限ID	private Integer proc_result;//   审批结果 
	private String proc_content;//审批意见
	private String role;//角色，（带教老师/教学秘书） 
	private Integer end_proc_num;//   流程结束表示
	
	
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	
}
