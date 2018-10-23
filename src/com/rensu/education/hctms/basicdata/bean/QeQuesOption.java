package com.rensu.education.hctms.basicdata.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class QeQuesOption extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private Integer qq_id;//   试题库ID（QE_QUESTION表ID）	private String option_flag;//   存放选项（A;B;C;D;E）	private String option_content;//   选项内容	private String state;//   状态	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getQq_id() {	    return this.qq_id;	}	public void setQq_id(Integer qq_id) {	    this.qq_id=qq_id;	}	public String getOption_flag() {	    return this.option_flag;	}	public void setOption_flag(String option_flag) {	    this.option_flag=option_flag;	}	public String getOption_content() {	    return this.option_content;	}	public void setOption_content(String option_content) {	    this.option_content=option_content;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
}
