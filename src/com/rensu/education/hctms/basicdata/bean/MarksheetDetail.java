package com.rensu.education.hctms.basicdata.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class MarksheetDetail extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private Integer ms_id;//   评分表字表ID	private String text;//   内容	private Integer sco_num;//   对应分值	private String state;//   状态	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getMs_id() {	    return this.ms_id;	}	public void setMs_id(Integer ms_id) {	    this.ms_id=ms_id;	}	public String getText() {	    return this.text;	}	public void setText(String text) {	    this.text=text;	}	public Integer getSco_num() {	    return this.sco_num;	}	public void setSco_num(Integer sco_num) {	    this.sco_num=sco_num;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
}
