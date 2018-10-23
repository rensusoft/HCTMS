package com.rensu.education.hctms.basicdata.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class MarksheetMain extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private String mm_name;//   表单名称	private String validity;//   有效性（Y：启用；X：禁用）	private Integer total_sconum;//   总分	private Integer create_auth_id;//   创建人	private java.sql.Timestamp create_time;//   创建时间	private String state;//   状态	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getMm_name() {	    return this.mm_name;	}	public void setMm_name(String mm_name) {	    this.mm_name=mm_name;	}	public String getValidity() {	    return this.validity;	}	public void setValidity(String validity) {	    this.validity=validity;	}	public Integer getTotal_sconum() {	    return this.total_sconum;	}	public void setTotal_sconum(Integer total_sconum) {	    this.total_sconum=total_sconum;	}	public Integer getCreate_auth_id() {	    return this.create_auth_id;	}	public void setCreate_auth_id(Integer create_auth_id) {	    this.create_auth_id=create_auth_id;	}	public java.sql.Timestamp getCreate_time() {	    return this.create_time;	}	public void setCreate_time(java.sql.Timestamp create_time) {	    this.create_time=create_time;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
}
