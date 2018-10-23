package com.rensu.education.hctms.basicdata.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class MarksheetOrgaRela extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer mm_id;//   评分表主表ID	private Integer orga_id;//   机构/科室ID	private String state;//   状态	public Integer getMm_id() {	    return this.mm_id;	}	public void setMm_id(Integer mm_id) {	    this.mm_id=mm_id;	}	public Integer getOrga_id() {	    return this.orga_id;	}	public void setOrga_id(Integer orga_id) {	    this.orga_id=orga_id;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
}
