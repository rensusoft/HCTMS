package com.rensu.education.hctms.config.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class TrainPlanConfig extends BaseBean {
	
	
	private String req_content;//轮转规范
	private String orga_name;  //科室名
	private Integer limit_num; //人数上限
	
	private String user_code;
    private Integer auth_id;
    
		return req_content;
	}
	public void setReq_content(String req_content) {
		this.req_content = req_content;
	}
	public Integer getId() {
	public String getOrga_name() {
		return orga_name;
	}
	public void setOrga_name(String orga_name) {
		this.orga_name = orga_name;
	}
	public Integer getLimit_num() {
		return limit_num;
	}
	public void setLimit_num(Integer limit_num) {
		this.limit_num = limit_num;
	}
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public Integer getAuth_id() {
		return auth_id;
	}
	public void setAuth_id(Integer auth_id) {
		this.auth_id = auth_id;
	}
	
	
}