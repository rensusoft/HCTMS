package com.rensu.education.hctms.log.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class ProcessRecord extends BaseBean {
	
	
	private String create_time_str;//   操作时间
	private Integer orga_id;//   操作时间
	private Integer related_id;
	private String proc_content;
	private Integer proc_result;
	private String proc_result_str;
	
		return proc_result;
	}
	public void setProc_result(Integer proc_result) {
		this.proc_result = proc_result;
	}
	public String getProc_content() {
		return proc_content;
	}
	public void setProc_content(String proc_content) {
		this.proc_content = proc_content;
	}
	public Integer getRelated_id() {
		return related_id;
	}
	public void setRelated_id(Integer related_id) {
		this.related_id = related_id;
	}
	public Integer getId() {
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getProc_result_str() {
		return proc_result_str;
	}
	public void setProc_result_str(String proc_result_str) {
		this.proc_result_str = proc_result_str;
	}
	public String getCreate_time_str() {
		return create_time_str;
	}
	public void setCreate_time_str(String create_time_str) {
		this.create_time_str = create_time_str;
	}
	public Integer getOrga_id() {
		return orga_id;
	}
	public void setOrga_id(Integer orga_id) {
		this.orga_id = orga_id;
	}

	
}