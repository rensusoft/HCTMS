package com.rensu.education.hctms.log.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class OutdeptRecord extends BaseBean {
	
	
	private String dateTimeStr; //审核时间字符串 (年月日)
	private String dateTimeStrEnd; //审核时间字符串 (时分秒)
	private String userName;
	private String roleName;
	
	private Integer theory_sco; //理论考试分数
	private Integer skill_sco; //技能考试分数
	private Integer pub_num; //发起组次
	private Integer exam_result; //审批结果（1：同意  / -1：不同意）
	
	
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
	public Integer getId() {
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