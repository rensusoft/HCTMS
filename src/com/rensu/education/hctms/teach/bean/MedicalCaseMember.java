package com.rensu.education.hctms.teach.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class MedicalCaseMember extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private Integer mcm_id;//   讨论主表标识	private Integer stu_auth_id;//   讨论人权限ID	private String state;//   状态（Y：可用，X：删除）
	private String user_name;	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getMcm_id() {	    return this.mcm_id;	}	public void setMcm_id(Integer mcm_id) {	    this.mcm_id=mcm_id;	}	public Integer getStu_auth_id() {	    return this.stu_auth_id;	}	public void setStu_auth_id(Integer stu_auth_id) {	    this.stu_auth_id=stu_auth_id;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
}
