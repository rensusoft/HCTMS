package com.rensu.education.hctms.basicdata.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class ExamineTypeContent extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private Integer type_id;//   类型ID（1：数据录入审核--不通过原因）	private String content;//   内容	private Integer order_num;//   排序码	private String state;//   状态	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getType_id() {	    return this.type_id;	}	public void setType_id(Integer type_id) {	    this.type_id=type_id;	}	public String getContent() {	    return this.content;	}	public void setContent(String content) {	    this.content=content;	}	public Integer getOrder_num() {	    return this.order_num;	}	public void setOrder_num(Integer order_num) {	    this.order_num=order_num;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
}
