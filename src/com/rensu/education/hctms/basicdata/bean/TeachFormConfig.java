package com.rensu.education.hctms.basicdata.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class TeachFormConfig extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private String tf_code;//   配置CODE	private String tf_name;//   配置名称	private String state;//   状态	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getTf_code() {	    return this.tf_code;	}	public void setTf_code(String tf_code) {	    this.tf_code=tf_code;	}	public String getTf_name() {	    return this.tf_name;	}	public void setTf_name(String tf_name) {	    this.tf_name=tf_name;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
}
