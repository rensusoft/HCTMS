package com.rensu.education.hctms.userauth.bean;

import com.rensu.education.hctms.core.bean.BaseBean;


public class VUserDetailInfo extends BaseBean {
	
	
	private Integer role_id;//   null
	private String role_code;//   null
	private String state;//   null
	private String state_desc;//   null
	private java.sql.Timestamp create_time;//   null
	private String create_time_desc;//   null
	private Integer role_type;
	private String type_name;//  学生类型
	private Integer stu_type_id;
	private String stu_type_name;
	private String img_path;//  头像路径
	private String  stu_num;
	
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
	public Integer getAuth_id() {
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