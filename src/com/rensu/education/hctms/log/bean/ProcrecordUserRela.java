package com.rensu.education.hctms.log.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class ProcrecordUserRela extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private Integer pr_id;//   对应PROCESS_RECORD的ID	private Integer user_auth_id;//   用户authId	private String state;//   状态	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getPr_id() {	    return this.pr_id;	}	public void setPr_id(Integer pr_id) {	    this.pr_id=pr_id;	}	public Integer getUser_auth_id() {	    return this.user_auth_id;	}	public void setUser_auth_id(Integer user_auth_id) {	    this.user_auth_id=user_auth_id;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
}
