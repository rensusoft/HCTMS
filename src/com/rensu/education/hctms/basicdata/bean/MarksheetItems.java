package com.rensu.education.hctms.basicdata.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class MarksheetItems extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private String item_code;//   项目代码	private String item_name;//   项目名称	private String state;//   状态	public String getItem_code() {	    return this.item_code;	}	public void setItem_code(String item_code) {	    this.item_code=item_code;	}	public String getItem_name() {	    return this.item_name;	}	public void setItem_name(String item_name) {	    this.item_name=item_name;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
}
