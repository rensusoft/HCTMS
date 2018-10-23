package com.rensu.education.hctms.config.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class SysDictMain extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private String item_code;//   项目代码	private String item_code_old;//   旧项目代码	private String item_name;//   项目名称	private String py_code;//   拼音码	private Integer ordinal;//   排序码	private String item_describe;//   项目描述	private String other_code;//   其它代码	private String state;//   状态
	private Integer availability; //有效性
		public Integer getAvailability() {
		return availability;
	}
	public void setAvailability(Integer availability) {
		this.availability = availability;
	}
	public String getItem_code() {	    return this.item_code;	}	public void setItem_code(String item_code) {	    this.item_code=item_code;	}	public String getItem_name() {	    return this.item_name;	}	public void setItem_name(String item_name) {	    this.item_name=item_name;	}	public String getPy_code() {	    return this.py_code;	}	public void setPy_code(String py_code) {	    this.py_code=py_code;	}	public Integer getOrdinal() {	    return this.ordinal;	}	public void setOrdinal(Integer ordinal) {	    this.ordinal=ordinal;	}	public String getItem_describe() {	    return this.item_describe;	}	public void setItem_describe(String item_describe) {	    this.item_describe=item_describe;	}	public String getOther_code() {	    return this.other_code;	}	public void setOther_code(String other_code) {	    this.other_code=other_code;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
	public String getItem_code_old() {
		return item_code_old;
	}
	public void setItem_code_old(String item_code_old) {
		this.item_code_old = item_code_old;
	}
}
