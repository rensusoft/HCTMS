package com.rensu.education.hctms.userauth.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

/**
 * 角色信息
 * @date 2016年4月20日
 * @autor chen xiaoxiao
 */
public class RoleInfo extends BaseBean {
	
	private String role_code;//   角色编码
	private String role_name;//   角色名称
	private String state;//   状态
	private String state_desc;//   状态
	private String createTimeStr;
	private Integer role_type;
	
		return role_type;
	}
	public void setRole_type(Integer role_type) {
		this.role_type = role_type;
	}
	public Integer getRole_id() {
	/**
	 * @return the createTimeStr
	 */
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	/**
	 * @param createTimeStr the createTimeStr to set
	 */
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
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
}