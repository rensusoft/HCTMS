package com.rensu.education.hctms.basicdata.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class ProcessInfo extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private String type_code;//   流程typeCode（1、“outDept”：出科流程；）	private Integer proc_num;//   流程序号	private String proc_name;//   流程名称	private String state;//   状态	private Integer vacate_date_num;	private Integer start_proc_id;	private Integer end_proc_id;	private Integer require_datenum;	private Integer role_id;	private Integer start_num;	private String start_name;
	private Integer flag_color;//  显示颜色标识		public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getType_code() {	    return this.type_code;	}	public void setType_code(String type_code) {	    this.type_code=type_code;	}	public Integer getProc_num() {	    return this.proc_num;	}	public void setProc_num(Integer proc_num) {	    this.proc_num=proc_num;	}	public String getProc_name() {	    return this.proc_name;	}	public void setProc_name(String proc_name) {	    this.proc_name=proc_name;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
	public Integer getVacate_date_num() {
		return vacate_date_num;
	}
	public void setVacate_date_num(Integer vacate_date_num) {
		this.vacate_date_num = vacate_date_num;
	}
	public Integer getStart_proc_id() {
		return start_proc_id;
	}
	public void setStart_proc_id(Integer start_proc_id) {
		this.start_proc_id = start_proc_id;
	}
	public Integer getEnd_proc_id() {
		return end_proc_id;
	}
	public void setEnd_proc_id(Integer end_proc_id) {
		this.end_proc_id = end_proc_id;
	}
	public Integer getRequire_datenum() {
		return require_datenum;
	}
	public void setRequire_datenum(Integer require_datenum) {
		this.require_datenum = require_datenum;
	}
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	public String getStart_name() {
		return start_name;
	}
	public void setStart_name(String start_name) {
		this.start_name = start_name;
	}
	public Integer getStart_num() {
		return start_num;
	}
	public void setStart_num(Integer start_num) {
		this.start_num = start_num;
	}
	public Integer getFlag_color() {
		return flag_color;
	}
	public void setFlag_color(Integer flag_color) {
		this.flag_color = flag_color;
	}

	
}
