package com.rensu.education.hctms.userauth.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class StuType extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private String type_code;//   类型CODE	private String type_name;//   类型名称	private String state;//   状态	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getType_code() {	    return this.type_code;	}	public void setType_code(String type_code) {	    this.type_code=type_code;	}	public String getType_name() {	    return this.type_name;	}	public void setType_name(String type_name) {	    this.type_name=type_name;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
}
