package com.rensu.education.hctms.log.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class OutdeptRecord extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private Integer stu_auth_id;//   学生auth_id	private Integer exam_auth_id;//   审核人auth_id	private java.sql.Timestamp exam_datetime;//   审核时间	private Integer orga_id;//   科室ID	private String exam_content;//   审核意见	private Integer exam_sco;//   审核分数（总分，百分制）	private String state;//   状态	private Integer exam_role_id;//   审核人角色ID
	private String dateTimeStr; //审核时间字符串 (年月日)
	private String dateTimeStrEnd; //审核时间字符串 (时分秒)
	private String userName;
	private String roleName;
	
	private Integer theory_sco; //理论考试分数
	private Integer skill_sco; //技能考试分数
	private Integer pub_num; //发起组次
	private Integer exam_result; //审批结果（1：同意  / -1：不同意）
	
		public Integer getPub_num() {
		return pub_num;
	}
	public void setPub_num(Integer pub_num) {
		this.pub_num = pub_num;
	}
	public Integer getExam_result() {
		return exam_result;
	}
	public void setExam_result(Integer exam_result) {
		this.exam_result = exam_result;
	}
	public Integer getTheory_sco() {
		return theory_sco;
	}
	public void setTheory_sco(Integer theory_sco) {
		this.theory_sco = theory_sco;
	}
	public Integer getSkill_sco() {
		return skill_sco;
	}
	public void setSkill_sco(Integer skill_sco) {
		this.skill_sco = skill_sco;
	}
	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getStu_auth_id() {	    return this.stu_auth_id;	}	public void setStu_auth_id(Integer stu_auth_id) {	    this.stu_auth_id=stu_auth_id;	}	public Integer getExam_auth_id() {	    return this.exam_auth_id;	}	public void setExam_auth_id(Integer exam_auth_id) {	    this.exam_auth_id=exam_auth_id;	}	public java.sql.Timestamp getExam_datetime() {	    return this.exam_datetime;	}	public void setExam_datetime(java.sql.Timestamp exam_datetime) {	    this.exam_datetime=exam_datetime;	}	public Integer getOrga_id() {	    return this.orga_id;	}	public void setOrga_id(Integer orga_id) {	    this.orga_id=orga_id;	}	public String getExam_content() {	    return this.exam_content;	}	public void setExam_content(String exam_content) {	    this.exam_content=exam_content;	}	public Integer getExam_sco() {	    return this.exam_sco;	}	public void setExam_sco(Integer exam_sco) {	    this.exam_sco=exam_sco;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}	public Integer getExam_role_id() {	    return this.exam_role_id;	}	public void setExam_role_id(Integer exam_role_id) {	    this.exam_role_id=exam_role_id;	}
	public String getDateTimeStr() {
		return dateTimeStr;
	}
	public void setDateTimeStr(String dateTimeStr) {
		this.dateTimeStr = dateTimeStr;
	}
	
	public String getDateTimeStrEnd() {
		return dateTimeStrEnd;
	}
	public void setDateTimeStrEnd(String dateTimeStrEnd) {
		this.dateTimeStrEnd = dateTimeStrEnd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
