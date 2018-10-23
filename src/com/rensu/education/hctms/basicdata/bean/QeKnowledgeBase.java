package com.rensu.education.hctms.basicdata.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class QeKnowledgeBase extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private Integer pid;//   父节点ID	private String name;//   名称	private String remarks;//   备注	private Integer ordinal;//   排序码	private String state;//   状态	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getPid() {	    return this.pid;	}	public void setPid(Integer pid) {	    this.pid=pid;	}	public String getName() {	    return this.name;	}	public void setName(String name) {	    this.name=name;	}	public String getRemarks() {	    return this.remarks;	}	public void setRemarks(String remarks) {	    this.remarks=remarks;	}	public Integer getOrdinal() {	    return this.ordinal;	}	public void setOrdinal(Integer ordinal) {	    this.ordinal=ordinal;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
}
