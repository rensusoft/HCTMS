package com.rensu.education.hctms.basicdata.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class RprFormRela extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private String rpr_ids;	private Integer rpr_id;//   角色-流程关联表ID	private Integer form_type;//   表单类型（1：量表类型；2：普通表单类型）	private Integer type_id;//   表单ID（量表类型对应MARKSHEET_MAIN表的ID；普通表单对应HTML_TEMPLATE表的ID）	private String state;//   状态	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getRpr_id() {	    return this.rpr_id;	}	public void setRpr_id(Integer rpr_id) {	    this.rpr_id=rpr_id;	}	public Integer getForm_type() {	    return this.form_type;	}	public void setForm_type(Integer form_type) {	    this.form_type=form_type;	}	public Integer getType_id() {	    return this.type_id;	}	public void setType_id(Integer type_id) {	    this.type_id=type_id;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
	public String getRpr_ids() {
		return rpr_ids;
	}
	public void setRpr_ids(String rpr_ids) {
		this.rpr_ids = rpr_ids;
	}
	
}
