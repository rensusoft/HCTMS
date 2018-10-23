package com.rensu.education.hctms.teach.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class CathedraPlan extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private String cath_title;//   讲座标题	private String cath_content;//   讲座内容	private Integer speaker;//   主讲人authId
	private Integer cath_date;//   讲座日期（20170301）	private java.sql.Timestamp cath_time;//   讲座时间	private String cath_place;//   讲座地点	private java.sql.Timestamp create_time;//   创建时间	private Integer creater_authid;//   创建人authId	private String status;//   状态
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
	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getCath_title() {	    return this.cath_title;	}	public void setCath_title(String cath_title) {	    this.cath_title=cath_title;	}	public String getCath_content() {	    return this.cath_content;	}	public void setCath_content(String cath_content) {	    this.cath_content=cath_content;	}	public Integer getSpeaker() {	    return this.speaker;	}	public void setSpeaker(Integer speaker) {	    this.speaker=speaker;	}	public Integer getCath_date() {	    return this.cath_date;	}	public void setCath_date(Integer cath_date) {	    this.cath_date=cath_date;	}	public java.sql.Timestamp getCath_time() {	    return this.cath_time;	}	public void setCath_time(java.sql.Timestamp cath_time) {	    this.cath_time=cath_time;	}	public String getCath_place() {	    return this.cath_place;	}	public void setCath_place(String cath_place) {	    this.cath_place=cath_place;	}	public java.sql.Timestamp getCreate_time() {	    return this.create_time;	}	public void setCreate_time(java.sql.Timestamp create_time) {	    this.create_time=create_time;	}	public Integer getCreater_authid() {	    return this.creater_authid;	}	public void setCreater_authid(Integer creater_authid) {	    this.creater_authid=creater_authid;	}	public String getStatus() {	    return this.status;	}	public void setStatus(String status) {	    this.status=status;	}
}
