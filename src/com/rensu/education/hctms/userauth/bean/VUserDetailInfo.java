package com.rensu.education.hctms.userauth.bean;

import com.rensu.education.hctms.core.bean.BaseBean;


public class VUserDetailInfo extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer auth_id;//   null	private Integer user_id;//   null
	private Integer role_id;//   null
	private String role_code;//   null	private Integer orga_id;//   null
	private String state;//   null
	private String state_desc;//   null
	private java.sql.Timestamp create_time;//   null
	private String create_time_desc;//   null	private String user_code;//   null	private String user_password;//   null	private String user_name;//   null	private String mobile;//   null	private String user_state;//   null	private String role_name;//   null	private String role_state;//   null	private String orga_name;//   null	private Integer orga_level;//   null	private String orga_state;//   null
	private Integer role_type;
	private String type_name;//  学生类型
	private Integer stu_type_id;
	private String stu_type_name;
	private String img_path;//  头像路径
	private String  stu_num;
		public String getStu_type_name() {
		return stu_type_name;
	}
	public void setStu_type_name(String stu_type_name) {
		this.stu_type_name = stu_type_name;
	}
	public String getStu_num() {
		return stu_num;
	}
	public void setStu_num(String stu_num) {
		this.stu_num = stu_num;
	}
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	public Integer getStu_type_id() {
		return stu_type_id;
	}
	public void setStu_type_id(Integer stu_type_id) {
		this.stu_type_id = stu_type_id;
	}
	public Integer getRole_type() {
		return role_type;
	}
	public void setRole_type(Integer role_type) {
		this.role_type = role_type;
	}
	public Integer getAuth_id() {	    return this.auth_id;	}	public void setAuth_id(Integer auth_id) {	    this.auth_id=auth_id;	}	public Integer getUser_id() {	    return this.user_id;	}	public void setUser_id(Integer user_id) {	    this.user_id=user_id;	}	public Integer getRole_id() {	    return this.role_id;	}	public void setRole_id(Integer role_id) {	    this.role_id=role_id;	}	public Integer getOrga_id() {	    return this.orga_id;	}	public void setOrga_id(Integer orga_id) {	    this.orga_id=orga_id;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}	public java.sql.Timestamp getCreate_time() {	    return this.create_time;	}	public void setCreate_time(java.sql.Timestamp create_time) {	    this.create_time=create_time;	}	public String getUser_code() {	    return this.user_code;	}	public void setUser_code(String user_code) {	    this.user_code=user_code;	}	public String getUser_password() {	    return this.user_password;	}	public void setUser_password(String user_password) {	    this.user_password=user_password;	}	public String getUser_name() {	    return this.user_name;	}	public void setUser_name(String user_name) {	    this.user_name=user_name;	}	public String getMobile() {	    return this.mobile;	}	public void setMobile(String mobile) {	    this.mobile=mobile;	}	public String getUser_state() {	    return this.user_state;	}	public void setUser_state(String user_state) {	    this.user_state=user_state;	}	public String getRole_name() {	    return this.role_name;	}	public void setRole_name(String role_name) {	    this.role_name=role_name;	}	public String getRole_state() {	    return this.role_state;	}	public void setRole_state(String role_state) {	    this.role_state=role_state;	}	public String getOrga_name() {	    return this.orga_name;	}	public void setOrga_name(String orga_name) {	    this.orga_name=orga_name;	}	public Integer getOrga_level() {	    return this.orga_level;	}	public void setOrga_level(Integer orga_level) {	    this.orga_level=orga_level;	}	public String getOrga_state() {	    return this.orga_state;	}	public void setOrga_state(String orga_state) {	    this.orga_state=orga_state;	}
	/**
	 * @return the role_code
	 */
	public String getRole_code() {
		return role_code;
	}
	/**
	 * @param role_code the role_code to set
	 */
	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}
	/**
	 * @return the state_desc
	 */
	public String getState_desc() {
		return state_desc;
	}
	/**
	 * @param state_desc the state_desc to set
	 */
	public void setState_desc(String state_desc) {
		this.state_desc = state_desc;
	}
	/**
	 * @return the create_time_desc
	 */
	public String getCreate_time_desc() {
		return create_time_desc;
	}
	/**
	 * @param create_time_desc the create_time_desc to set
	 */
	public void setCreate_time_desc(String create_time_desc) {
		this.create_time_desc = create_time_desc;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
}
