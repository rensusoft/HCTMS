package com.rensu.education.hctms.log.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class ProcessRecord extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private Integer type_code;//   流程类型CODE（1、“outDept”：出科流程；）	private Integer start_proc_id;//   流程开始ID（PROCESS_INFO的ID）	private Integer start_proc_num;//   流程开始序号	private String start_proc_name;//   流程开始名称	private Integer end_proc_id;//   流程结束ID（PROCESS_INFO的ID）	private Integer end_proc_num;//   流程结束序号	private String end_proc_name;//   流程结束名称	private Integer create_auth_id;//   操作人authId	private String user_name;//   操作人	private java.sql.Timestamp create_time;//   操作时间
	private String create_time_str;//   操作时间
	private Integer orga_id;//   操作时间
	private Integer related_id;
	private String proc_content;
	private Integer proc_result;
	private String proc_result_str;
		public Integer getProc_result() {
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
	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getType_code() {	    return this.type_code;	}	public void setType_code(Integer type_code) {	    this.type_code=type_code;	}	public Integer getStart_proc_id() {	    return this.start_proc_id;	}	public void setStart_proc_id(Integer start_proc_id) {	    this.start_proc_id=start_proc_id;	}	public Integer getStart_proc_num() {	    return this.start_proc_num;	}	public void setStart_proc_num(Integer start_proc_num) {	    this.start_proc_num=start_proc_num;	}	public String getStart_proc_name() {	    return this.start_proc_name;	}	public void setStart_proc_name(String start_proc_name) {	    this.start_proc_name=start_proc_name;	}	public Integer getEnd_proc_id() {	    return this.end_proc_id;	}	public void setEnd_proc_id(Integer end_proc_id) {	    this.end_proc_id=end_proc_id;	}	public Integer getEnd_proc_num() {	    return this.end_proc_num;	}	public void setEnd_proc_num(Integer end_proc_num) {	    this.end_proc_num=end_proc_num;	}	public String getEnd_proc_name() {	    return this.end_proc_name;	}	public void setEnd_proc_name(String end_proc_name) {	    this.end_proc_name=end_proc_name;	}	public Integer getCreate_auth_id() {	    return this.create_auth_id;	}	public void setCreate_auth_id(Integer create_auth_id) {	    this.create_auth_id=create_auth_id;	}	public java.sql.Timestamp getCreate_time() {	    return this.create_time;	}	public void setCreate_time(java.sql.Timestamp create_time) {	    this.create_time=create_time;	}
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
