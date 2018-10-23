package com.rensu.education.hctms.userauth.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class StuClass extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private String class_year;//   年份(2017)	private String class_month;//   月份(03)	private Integer stu_class;//   届次(201703)	private String available;//   有效标志	private java.sql.Timestamp create_time;//   创建时间	private String creator;//   创建人	private String remark;//   备注
	private String class_day;//   月份(03)	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getClass_year() {	    return this.class_year;	}	public void setClass_year(String class_year) {	    this.class_year=class_year;	}	public String getClass_month() {	    return this.class_month;	}	public void setClass_month(String class_month) {	    this.class_month=class_month;	}	public Integer getStu_class() {	    return this.stu_class;	}	public void setStu_class(Integer stu_class) {	    this.stu_class=stu_class;	}	public String getAvailable() {	    return this.available;	}	public void setAvailable(String available) {	    this.available=available;	}	public java.sql.Timestamp getCreate_time() {	    return this.create_time;	}	public void setCreate_time(java.sql.Timestamp create_time) {	    this.create_time=create_time;	}	public String getCreator() {	    return this.creator;	}	public void setCreator(String creator) {	    this.creator=creator;	}	public String getRemark() {	    return this.remark;	}	public void setRemark(String remark) {	    this.remark=remark;	}
	public String getClass_day() {
		return class_day;
	}
	public void setClass_day(String class_day) {
		this.class_day = class_day;
	}
}
