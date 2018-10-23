package com.rensu.education.hctms.publicdata.bean;


import com.rensu.education.hctms.core.bean.BaseBean;

public class ForumSub extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private Integer fi_id;//   主表ID	private Integer cite_state;//   是否引用（0：未引用；1：有引用）	private Integer cite_id;//   引用ID（FORUM_SUB表的ID）	private String content;//   回复内容	private Integer support_num;//   支持数	private Integer against_num;//   反对数	private Integer publisher_auth_id;//   发布人	private java.sql.Timestamp publish_time;//   发布时间	private String state;//   状态
	private String user_name;//   发布人名字
	private String type_name;//   发布人类型
	private String orga_name;//   发布人所在科室
	private String publish_time_str;//   发布时间 
	private String cite_user_name;//   引用人姓名
	private String cite_content;//   引用内容  
	private String cite_publish_time_str;//   引用时间
	private String stu_num;	public String getStu_num() {
		return stu_num;
	}
	public void setStu_num(String stu_num) {
		this.stu_num = stu_num;
	}
	public String getCite_user_name() {
		return cite_user_name;
	}
	public void setCite_user_name(String cite_user_name) {
		this.cite_user_name = cite_user_name;
	}
	public String getCite_content() {
		return cite_content;
	}
	public void setCite_content(String cite_content) {
		this.cite_content = cite_content;
	}
	public String getCite_publish_time_str() {
		return cite_publish_time_str;
	}
	public void setCite_publish_time_str(String cite_publish_time_str) {
		this.cite_publish_time_str = cite_publish_time_str;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public String getOrga_name() {
		return orga_name;
	}
	public void setOrga_name(String orga_name) {
		this.orga_name = orga_name;
	}
	public String getPublish_time_str() {
		return publish_time_str;
	}
	public void setPublish_time_str(String publish_time_str) {
		this.publish_time_str = publish_time_str;
	}
	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getFi_id() {	    return this.fi_id;	}	public void setFi_id(Integer fi_id) {	    this.fi_id=fi_id;	}	public Integer getCite_state() {	    return this.cite_state;	}	public void setCite_state(Integer cite_state) {	    this.cite_state=cite_state;	}	public Integer getCite_id() {	    return this.cite_id;	}	public void setCite_id(Integer cite_id) {	    this.cite_id=cite_id;	}	public String getContent() {	    return this.content;	}	public void setContent(String content) {	    this.content=content;	}	public Integer getSupport_num() {	    return this.support_num;	}	public void setSupport_num(Integer support_num) {	    this.support_num=support_num;	}	public Integer getAgainst_num() {	    return this.against_num;	}	public void setAgainst_num(Integer against_num) {	    this.against_num=against_num;	}	public Integer getPublisher_auth_id() {	    return this.publisher_auth_id;	}	public void setPublisher_auth_id(Integer publisher_auth_id) {	    this.publisher_auth_id=publisher_auth_id;	}	public java.sql.Timestamp getPublish_time() {	    return this.publish_time;	}	public void setPublish_time(java.sql.Timestamp publish_time) {	    this.publish_time=publish_time;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
}
