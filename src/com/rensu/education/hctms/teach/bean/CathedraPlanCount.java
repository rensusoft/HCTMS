package com.rensu.education.hctms.teach.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class CathedraPlanCount extends BaseBean {
	
		private static final long serialVersionUID = 1L;
	private Integer count;
	private Integer cath_date;
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getCath_date() {
		return cath_date;
	}
	public void setCath_date(Integer cath_date) {
		this.cath_date = cath_date;
	}
	
}
