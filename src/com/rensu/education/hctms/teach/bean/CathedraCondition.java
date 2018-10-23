package com.rensu.education.hctms.teach.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class CathedraCondition extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private Integer cp_id;//   讲座表标识	private Integer condition_type;//   条件类型(1:届次;2:学生类型;)	private String condition_value;//   条件值  Integer-->String	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getCp_id() {	    return this.cp_id;	}	public void setCp_id(Integer cp_id) {	    this.cp_id=cp_id;	}	public Integer getCondition_type() {	    return this.condition_type;	}	public void setCondition_type(Integer condition_type) {	    this.condition_type=condition_type;	}	public String getCondition_value() {	    return this.condition_value;	}	public void setCondition_value(String condition_value) {	    this.condition_value=condition_value;	}
}
