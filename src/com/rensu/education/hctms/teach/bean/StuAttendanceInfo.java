package com.rensu.education.hctms.teach.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class StuAttendanceInfo extends BaseBean {
	
	
	private static final long serialVersionUID = 1L;
	private Integer id;//   标识
	private Integer stu_auth_id;//   学生权限ID
	private Integer attend_date;//   考勤日期（例如：20170103）
	private Integer orga_id;//   考勤所在科室ID
	private Integer attend_state;//   考勤状态（0：缺勤；1：到勤；2：请假）
	private Integer attend_auth_id;//   考勤人
	private java.sql.Timestamp attend_time;//   考勤时间
	private String state;//   状态（Y：有效；X：无效）
	private String attend_name;  //考勤
	private String attendeDate_Str; //考勤时间字符串
	private Integer state_id; //记录考勤的状态ID （未考勤为-1）
	
	private java.sql.Timestamp start_time;//   开始时间
	private java.sql.Timestamp end_time;//   结束时间 
	
	private String attend_time_str;  //考勤时间
	private String attend_state_str;//  考勤状态
	private Integer absenceNum; //记录未考勤的人数
	private Integer absenceAllNum; //记录考勤的人数
	private Integer leaveNum; //记录请假的人数
	private float absenceAll_rate; 
	private float absence_rate;
	private float leave_rate; 
	
	private String stu_name;  
	private String type_name;  
	private Integer start_date;  
	
	public Integer getStart_date() {
		return start_date;
	}
	public void setStart_date(Integer start_date) {
		this.start_date = start_date;
	}
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public String getAttend_state_str() {
		return attend_state_str;
	}
	public void setAttend_state_str(String attend_state_str) {
		this.attend_state_str = attend_state_str;
	}
	public String getAttend_time_str() {
		return attend_time_str;
	}
	public void setAttend_time_str(String attend_time_str) {
		this.attend_time_str = attend_time_str;
	}
	public Integer getState_id() {
		return state_id;
	}
	public void setState_id(Integer state_id) {
		this.state_id = state_id;
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
	public Integer getAttend_date() {
	    return this.attend_date;
	}
	public void setAttend_date(Integer attend_date) {
	    this.attend_date=attend_date;
	}
	public Integer getOrga_id() {
	    return this.orga_id;
	}
	public void setOrga_id(Integer orga_id) {
	    this.orga_id=orga_id;
	}
	public Integer getAttend_state() {
	    return this.attend_state;
	}
	public void setAttend_state(Integer attend_state) {
	    this.attend_state=attend_state;
	}
	public Integer getAttend_auth_id() {
	    return this.attend_auth_id;
	}
	public void setAttend_auth_id(Integer attend_auth_id) {
	    this.attend_auth_id=attend_auth_id;
	}
	public java.sql.Timestamp getAttend_time() {
	    return this.attend_time;
	}
	public void setAttend_time(java.sql.Timestamp attend_time) {
	    this.attend_time=attend_time;
	}
	public String getState() {
	    return this.state;
	}
	public void setState(String state) {
	    this.state=state;
	}
	public String getAttend_name() {
		return attend_name;
	}
	public void setAttend_name(String attend_name) {
		this.attend_name = attend_name;
	}
	public String getAttendeDate_Str() {
		return attendeDate_Str;
	}
	public void setAttendeDate_Str(String attendeDate_Str) {
		this.attendeDate_Str = attendeDate_Str;
	}
	public java.sql.Timestamp getStart_time() {
		return start_time;
	}
	public void setStart_time(java.sql.Timestamp start_time) {
		this.start_time = start_time;
	}
	public java.sql.Timestamp getEnd_time() {
		return end_time;
	}
	public void setEnd_time(java.sql.Timestamp end_time) {
		this.end_time = end_time;
	}
	public Integer getAbsenceNum() {
		return absenceNum;
	}
	public void setAbsenceNum(Integer absenceNum) {
		this.absenceNum = absenceNum;
	}
	public Integer getAbsenceAllNum() {
		return absenceAllNum;
	}
	public void setAbsenceAllNum(Integer absenceAllNum) {
		this.absenceAllNum = absenceAllNum;
	}
	public Integer getLeaveNum() {
		return leaveNum;
	}
	public void setLeaveNum(Integer leaveNum) {
		this.leaveNum = leaveNum;
	}
	public float getAbsenceAll_rate() {
		return absenceAll_rate;
	}
	public void setAbsenceAll_rate(float absenceAll_rate) {
		this.absenceAll_rate = absenceAll_rate;
	}
	public float getAbsence_rate() {
		return absence_rate;
	}
	public void setAbsence_rate(float absence_rate) {
		this.absence_rate = absence_rate;
	}
	public float getLeave_rate() {
		return leave_rate;
	}
	public void setLeave_rate(float leave_rate) {
		this.leave_rate = leave_rate;
	}
	
}
