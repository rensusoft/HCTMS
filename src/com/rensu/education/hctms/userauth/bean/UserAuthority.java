package com.rensu.education.hctms.userauth.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

/**
 * 用户权限关联
 * @date 2016年4月20日
 * @autor chen xiaoxiao
 */
public class UserAuthority extends BaseBean {
	
	private Integer user_id;//   用户标识
	private String user_name;//   用户姓名
	private Integer role_id;//   角色标识
	private String role_name;//   角色标识
	private Integer orga_id;//   机构标识
	private String orga_name;//   机构标识
	private String state;//   状态
	private String state_desc;//   状态
	private java.sql.Timestamp create_time;//   创建时间
	private java.sql.Timestamp create_time_str;//   创建时间
	private Integer stu_type_id; //学生类型ID
	private String stu_type_name; //学生类型
	private String name;// 姓名
	private Integer count;//人数和
	
	public String getStu_type_name() {
		return stu_type_name;
	}
	public void setStu_type_name(String stu_type_name) {
		this.stu_type_name = stu_type_name;
	}
	public Integer getStu_type_id() {
		return stu_type_id;
	}
	public void setStu_type_id(Integer stu_type_id) {
		this.stu_type_id = stu_type_id;
	}
	public Integer getAuth_id() {
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getOrga_name() {
		return orga_name;
	}
	public void setOrga_name(String orga_name) {
		this.orga_name = orga_name;
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
	 * @return the create_time_str
	 */
	public java.sql.Timestamp getCreate_time_str() {
		return create_time_str;
	}
	/**
	 * @param create_time_str the create_time_str to set
	 */
	public void setCreate_time_str(java.sql.Timestamp create_time_str) {
		this.create_time_str = create_time_str;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserAuthority [auth_id=" + auth_id + ", user_id=" + user_id
				+ ", user_name=" + user_name + ", role_id=" + role_id
				+ ", role_name=" + role_name + ", orga_id=" + orga_id
				+ ", orga_name=" + orga_name + ", state=" + state
				+ ", state_desc=" + state_desc + ", create_time=" + create_time
				+ ", create_time_str=" + create_time_str + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}