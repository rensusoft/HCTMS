package com.rensu.education.hctms.userauth.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

/**
 * 用户基本信息
 * 
 * @date 2016年4月20日
 * @autor chen xiaoxiao
 */
public class UserInfo extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer user_id;//   用户标识	private String user_code;//   工号	private String user_password;//   密码	private String user_name;//   姓名	private String mobile;//   手机
	private String state;//   状态
	private String state_desc;//   状态
	private java.sql.Timestamp create_time;//   创建时间
	private String create_time_str;//   创建时间
	private Integer orga_id;//  机构标识
	private String orga_name;//机构名称
	private Integer role_id;//  角色标识
	private String role_name;//  角色名称
	private String img_path;//头像路径
	
	private Integer auth_id;//权限ID
	
	private String role_ids; //角色集合
	
	private String identity; //身份判断
	private Integer teacher_auth_id; 
	private Integer stu_auth_id; 
	
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	public String getRole_ids() {
		return role_ids;
	}
	public void setRole_ids(String role_ids) {
		this.role_ids = role_ids;
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
	public Integer getAuth_id() {
		return auth_id;
	}
	public void setAuth_id(Integer auth_id) {
		this.auth_id = auth_id;
	}
	public Integer getUser_id() {	    return this.user_id;	}	public void setUser_id(Integer user_id) {	    this.user_id=user_id;	}	public String getUser_code() {	    return this.user_code;	}	public void setUser_code(String user_code) {	    this.user_code=user_code;	}	public String getUser_password() {	    return this.user_password;	}	public void setUser_password(String user_password) {	    this.user_password=user_password;	}	public String getUser_name() {	    return this.user_name;	}	public void setUser_name(String user_name) {	    this.user_name=user_name;	}	public String getMobile() {	    return this.mobile;	}	public void setMobile(String mobile) {	    this.mobile=mobile;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}	public java.sql.Timestamp getCreate_time() {	    return this.create_time;	}	public void setCreate_time(java.sql.Timestamp create_time) {	    this.create_time=create_time;	}
	/**
	 * @return the create_time_str
	 */
	public String getCreate_time_str() {
		return create_time_str;
	}
	/**
	 * @param create_time_str the create_time_str to set
	 */
	public void setCreate_time_str(String create_time_str) {
		this.create_time_str = create_time_str;
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
	public Integer getOrga_id() {
		return orga_id;
	}
	public void setOrga_id(Integer orga_id) {
		this.orga_id = orga_id;
	}
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	public Integer getTeacher_auth_id() {
		return teacher_auth_id;
	}
	public void setTeacher_auth_id(Integer teacher_auth_id) {
		this.teacher_auth_id = teacher_auth_id;
	}
	public Integer getStu_auth_id() {
		return stu_auth_id;
	}
	public void setStu_auth_id(Integer stu_auth_id) {
		this.stu_auth_id = stu_auth_id;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserInfo [user_id=" + user_id + ", user_code=" + user_code
				+ ", user_password=" + user_password + ", user_name="
				+ user_name + ", mobile=" + mobile + ", state=" + state
				+ ", state_desc=" + state_desc + ", create_time=" + create_time
				+ ", create_time_str=" + create_time_str + "]";
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
}
