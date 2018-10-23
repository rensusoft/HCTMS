package com.rensu.education.hctms.basicdata.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class HtmlTemplate extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private String t_name;//   模板名称	private String t_content;//   模板内容（HTML格式）	private String validity;//   有效性（Y：启用；X：禁用）	private Integer create_auth_id;//   创建人	private java.sql.Timestamp create_time;//   创建时间	private String state;//   状态
	private String user_name;//   模板名称
	private String time;
		public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getT_name() {	    return this.t_name;	}	public void setT_name(String t_name) {	    this.t_name=t_name;	}	public String getT_content() {	    return this.t_content;	}	public void setT_content(String t_content) {	    this.t_content=t_content;	}	public String getValidity() {	    return this.validity;	}	public void setValidity(String validity) {	    this.validity=validity;	}	public Integer getCreate_auth_id() {	    return this.create_auth_id;	}	public void setCreate_auth_id(Integer create_auth_id) {	    this.create_auth_id=create_auth_id;	}	public java.sql.Timestamp getCreate_time() {	    return this.create_time;	}	public void setCreate_time(java.sql.Timestamp create_time) {	    this.create_time=create_time;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
