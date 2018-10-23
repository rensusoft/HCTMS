package com.rensu.education.hctms.teach.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class CathedraPlan extends BaseBean {
	
	
	private Integer cath_date;//   讲座日期（20170301）
	private java.sql.Timestamp timeStart;//  月份的第一天
	private java.sql.Timestamp timeEnd;//    月份最后一天
	private String cath_date_str;//   讲座左侧日期导航栏的日期
	private String speaker_name;//主讲人姓名
	private String cath_time_str;//   讲座左侧日期导航栏的日期
	
	public String getCath_time_str() {
		return cath_time_str;
	}
	public void setCath_time_str(String cath_time_str) {
		this.cath_time_str = cath_time_str;
	}
	public String getSpeaker_name() {
		return speaker_name;
	}
	public void setSpeaker_name(String speaker_name) {
		this.speaker_name = speaker_name;
	}
	public String getCath_date_str() {
		return cath_date_str;
	}
	public void setCath_date_str(String cath_date_str) {
		this.cath_date_str = cath_date_str;
	}
	public java.sql.Timestamp getTimeStart() {
		return timeStart;
	}
	public void setTimeStart(java.sql.Timestamp timeStart) {
		this.timeStart = timeStart;
	}
	public java.sql.Timestamp getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(java.sql.Timestamp timeEnd) {
		this.timeEnd = timeEnd;
	}
	public Integer getId() {
}