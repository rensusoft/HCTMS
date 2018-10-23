package com.rensu.education.hctms.basicdata.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class RoleProcRela extends BaseBean {
	
	
	private Integer require_datenum; //要求天数（这个字段专为请假流程使用，用于几天以下需要谁审批）
	private String  end_name;//   
	private Integer  end_num;//   
	private String  start_name;//   
	private Integer  start_num;//  
	
	
		return require_datenum;
	}
	public void setRequire_datenum(Integer require_datenum) {
		this.require_datenum = require_datenum;
	}
	public Integer getId() {
	public Integer getAuth_id() {
		return auth_id;
	}
	public void setAuth_id(Integer auth_id) {
		this.auth_id = auth_id;
	}
	public String getType_code() {
		return type_code;
	}
	public void setType_code(String type_code) {
		this.type_code = type_code;
	}
	public String getProc_name() {
		return proc_name;
	}
	public void setProc_name(String proc_name) {
		this.proc_name = proc_name;
	}
	public Integer getProc_num() {
		return proc_num;
	}
	public void setProc_num(Integer proc_num) {
		this.proc_num = proc_num;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole_ids() {
		return role_ids;
	}
	public void setRole_ids(String role_ids) {
		this.role_ids = role_ids;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getForm_name() {
		return form_name;
	}
	public void setForm_name(String form_name) {
		this.form_name = form_name;
	}
	public String getDelId() {
		return delId;
	}
	public void setDelId(String delId) {
		this.delId = delId;
	}
	public Integer getO_end_proc_id() {
		return o_end_proc_id;
	}
	public void setO_end_proc_id(Integer o_end_proc_id) {
		this.o_end_proc_id = o_end_proc_id;
	}
	public Integer getOrga_id() {
		return orga_id;
	}
	public void setOrga_id(Integer orga_id) {
		this.orga_id = orga_id;
	}
	public String getEnd_name() {
		return end_name;
	}
	public void setEnd_name(String end_name) {
		this.end_name = end_name;
	}
	public Integer getEnd_num() {
		return end_num;
	}
	public void setEnd_num(Integer end_num) {
		this.end_num = end_num;
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
	
	
}