package com.rensu.education.hctms.teach.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class StuExerQuestion extends BaseBean {
	
	
	private Integer qe_id;//   练习ID(STU_EXERCISES表ID)
	public Integer getQe_id() {
		return qe_id;
	}
	public void setQe_id(Integer qe_id) {
		this.qe_id = qe_id;
	}
}