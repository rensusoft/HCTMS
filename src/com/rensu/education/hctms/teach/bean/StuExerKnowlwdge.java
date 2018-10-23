package com.rensu.education.hctms.teach.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class StuExerKnowlwdge extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private Integer qe_id;//   练习ID(STU_EXERCISES表ID)	private Integer qkb_id;//   绑定知识点（QE_KNOWLEDGE_BASE表ID）	private Integer proportion;//   所占比重(%)	private String state;//   状态	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getQe_id() {	    return this.qe_id;	}	public void setQe_id(Integer qe_id) {	    this.qe_id=qe_id;	}	public Integer getQkb_id() {	    return this.qkb_id;	}	public void setQkb_id(Integer qkb_id) {	    this.qkb_id=qkb_id;	}	public Integer getProportion() {	    return this.proportion;	}	public void setProportion(Integer proportion) {	    this.proportion=proportion;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
}
