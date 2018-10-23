package com.rensu.education.hctms.teach.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class MedicalCaseDiscuss extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private Integer mcm_id;//   讨论主表标识	private String content;//   讨论内容	private Integer discuss_auth_id;//   讨论人authId	private java.sql.Timestamp discuss_time;//   讨论时间	private String state;//   状态（Y：可用，X：删除）
	private String user_name;
	private Integer creator_id;
	private String discuss_time_str;
		public String getDiscuss_time_str() {
		return discuss_time_str;
	}
	public void setDiscuss_time_str(String discuss_time_str) {
		this.discuss_time_str = discuss_time_str;
	}
	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getMcm_id() {	    return this.mcm_id;	}	public void setMcm_id(Integer mcm_id) {	    this.mcm_id=mcm_id;	}	public String getContent() {	    return this.content;	}	public void setContent(String content) {	    this.content=content;	}	public Integer getDiscuss_auth_id() {	    return this.discuss_auth_id;	}	public void setDiscuss_auth_id(Integer discuss_auth_id) {	    this.discuss_auth_id=discuss_auth_id;	}	public java.sql.Timestamp getDiscuss_time() {	    return this.discuss_time;	}	public void setDiscuss_time(java.sql.Timestamp discuss_time) {	    this.discuss_time=discuss_time;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Integer getCreator_id() {
		return creator_id;
	}
	public void setCreator_id(Integer creator_id) {
		this.creator_id = creator_id;
	}
	
}
