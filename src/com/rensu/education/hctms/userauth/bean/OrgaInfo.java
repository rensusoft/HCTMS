package com.rensu.education.hctms.userauth.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class OrgaInfo extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private String ids;	private Integer orga_id;//   机构标识	private String orga_name;//   机构名称	private String sup_name;//   上级机构名称	private Integer sup_id;//   上级标识	private Integer orga_level;//   机构级别	private String state;//   状态	private java.sql.Timestamp create_time;//   创建时间	private Integer orga_type;//   机构类别	private String orga_types;//   机构类别	private String orga_code1;//   机构代码1
	private String orga_code2;//   机构代码2
	private Integer limit_num;
	private String sup_orga_name;//上级机构名称
	
	//---每个学院的各角色与人员显示---
	private Integer role_id;
	private String role_name;
	private String auth_ids;
	private String user_names;
	//---每个学院的各角色与人员显示---
	
	
	public String getIds() {
		return ids;
	}
	public Integer getLimit_num() {
		return limit_num;
	}
	public void setLimit_num(Integer limit_num) {
		this.limit_num = limit_num;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public Integer getOrga_id() {
		return orga_id;
	}
	public void setOrga_id(Integer orga_id) {
		this.orga_id = orga_id;
	}
	public String getOrga_name() {
		return orga_name;
	}
	public void setOrga_name(String orga_name) {
		this.orga_name = orga_name;
	}
	public String getSup_name() {
		return sup_name;
	}
	public void setSup_name(String sup_name) {
		this.sup_name = sup_name;
	}
	public Integer getSup_id() {
		return sup_id;
	}
	public void setSup_id(Integer sup_id) {
		this.sup_id = sup_id;
	}
	public Integer getOrga_level() {
		return orga_level;
	}
	public void setOrga_level(Integer orga_level) {
		this.orga_level = orga_level;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public java.sql.Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(java.sql.Timestamp create_time) {
		this.create_time = create_time;
	}
	public Integer getOrga_type() {
		return orga_type;
	}
	public void setOrga_type(Integer orga_type) {
		this.orga_type = orga_type;
	}
	public String getOrga_types() {
		return orga_types;
	}
	public void setOrga_types(String orga_types) {
		this.orga_types = orga_types;
	}
	public String getOrga_code1() {
		return orga_code1;
	}
	public void setOrga_code1(String orga_code1) {
		this.orga_code1 = orga_code1;
	}
	public String getOrga_code2() {
		return orga_code2;
	}
	public void setOrga_code2(String orga_code2) {
		this.orga_code2 = orga_code2;
	}
	public String getSup_orga_name() {
		return sup_orga_name;
	}
	public void setSup_orga_name(String sup_orga_name) {
		this.sup_orga_name = sup_orga_name;
	}
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getAuth_ids() {
		return auth_ids;
	}
	public void setAuth_ids(String auth_ids) {
		this.auth_ids = auth_ids;
	}
	public String getUser_names() {
		return user_names;
	}
	public void setUser_names(String user_names) {
		this.user_names = user_names;
	}
}
