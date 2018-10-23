package com.rensu.education.hctms.basicdata.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class TfcDeptformRela extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private Integer tfc_id;//   教学模板配置表ID	private Integer dept_id;//   科室ID	private Integer stu_type_id;//   学生类型ID	private Integer form_id;//   对应的表单ID（FORM_INFO表的ID）	private String state;//   状态
	private String name;// 表单名称
	private Integer type_id;//表单类型ID
		public Integer getType_id() {
		return type_id;
	}
	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}
	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getTfc_id() {	    return this.tfc_id;	}	public void setTfc_id(Integer tfc_id) {	    this.tfc_id=tfc_id;	}	public Integer getDept_id() {	    return this.dept_id;	}	public void setDept_id(Integer dept_id) {	    this.dept_id=dept_id;	}	public Integer getStu_type_id() {	    return this.stu_type_id;	}	public void setStu_type_id(Integer stu_type_id) {	    this.stu_type_id=stu_type_id;	}	public Integer getForm_id() {	    return this.form_id;	}	public void setForm_id(Integer form_id) {	    this.form_id=form_id;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
