package com.rensu.education.hctms.userauth.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

/**
 * 角色菜单关联表
 * @date 2016年4月20日
 * @autor chen xiaoxiao
 */
public class RoleMenuRela extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer role_id;//   角色标识	private Integer menu_id;//   菜单标识	private String state;//   状态	public Integer getRole_id() {	    return this.role_id;	}	public void setRole_id(Integer role_id) {	    this.role_id=role_id;	}	public Integer getMenu_id() {	    return this.menu_id;	}	public void setMenu_id(Integer menu_id) {	    this.menu_id=menu_id;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
}
