package com.rensu.education.hctms.config.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class SysDictSub extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private String sup_code;//   主表类型	private String item_type_code;//   项目类型说明	private String item_type_code_old;//   旧的项目类型说明	private String item_type_name;//   项目类型名称	private Integer ordinal;//   排序码	private String state;//   状态	public String getSup_code() {	    return this.sup_code;	}	public void setSup_code(String sup_code) {	    this.sup_code=sup_code;	}	public String getItem_type_code() {	    return this.item_type_code;	}	public void setItem_type_code(String item_type_code) {	    this.item_type_code=item_type_code;	}	public String getItem_type_name() {	    return this.item_type_name;	}	public void setItem_type_name(String item_type_name) {	    this.item_type_name=item_type_name;	}	public Integer getOrdinal() {	    return this.ordinal;	}	public void setOrdinal(Integer ordinal) {	    this.ordinal=ordinal;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
	public String getItem_type_code_old() {
		return item_type_code_old;
	}
	public void setItem_type_code_old(String item_type_code_old) {
		this.item_type_code_old = item_type_code_old;
	}
	
}
