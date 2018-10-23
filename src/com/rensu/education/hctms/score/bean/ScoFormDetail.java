package com.rensu.education.hctms.score.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class ScoFormDetail extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private Integer sfs_id;//   评分表子表ID	private String text;//   内容	private Integer sco_num;//   对应分值	private Integer result_val;//   记录最终分值（TEXT存数字分值，选项型没选中的插入-1，选中的插入1）	private String state;//   状态	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getSfs_id() {	    return this.sfs_id;	}	public void setSfs_id(Integer sfs_id) {	    this.sfs_id=sfs_id;	}	public String getText() {	    return this.text;	}	public void setText(String text) {	    this.text=text;	}	public Integer getSco_num() {	    return this.sco_num;	}	public void setSco_num(Integer sco_num) {	    this.sco_num=sco_num;	}	public Integer getResult_val() {	    return this.result_val;	}	public void setResult_val(Integer result_val) {	    this.result_val=result_val;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
}
