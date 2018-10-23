package com.rensu.education.hctms.teach.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class CathedraDetail extends BaseBean {
	
		private static final long serialVersionUID = 1L;
	private CathedraPlan cathedraPlan;
	private Integer class_condition_id;
	private Integer type_condition_id;
	private Integer class_condition_type;
	private String class_condition_value;
	private Integer type_condition_type;
	private String type_condition_value;
	private String type_name;  //学生类型
	private String stu_class;  //届次
	
	
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public String getStu_class() {
		return stu_class;
	}
	public void setStu_class(String stu_class) {
		this.stu_class = stu_class;
	}
	public CathedraPlan getCathedraPlan() {
		return cathedraPlan;
	}
	public void setCathedraPlan(CathedraPlan cathedraPlan) {
		this.cathedraPlan = cathedraPlan;
	}
	public Integer getClass_condition_id() {
		return class_condition_id;
	}
	public void setClass_condition_id(Integer class_condition_id) {
		this.class_condition_id = class_condition_id;
	}
	public Integer getType_condition_id() {
		return type_condition_id;
	}
	public void setType_condition_id(Integer type_condition_id) {
		this.type_condition_id = type_condition_id;
	}
	public Integer getClass_condition_type() {
		return class_condition_type;
	}
	public void setClass_condition_type(Integer class_condition_type) {
		this.class_condition_type = class_condition_type;
	}
	public String getClass_condition_value() {
		return class_condition_value;
	}
	public void setClass_condition_value(String class_condition_value) {
		this.class_condition_value = class_condition_value;
	}
	public Integer getType_condition_type() {
		return type_condition_type;
	}
	public void setType_condition_type(Integer type_condition_type) {
		this.type_condition_type = type_condition_type;
	}
	public String getType_condition_value() {
		return type_condition_value;
	}
	public void setType_condition_value(String type_condition_value) {
		this.type_condition_value = type_condition_value;
	}
	
}
