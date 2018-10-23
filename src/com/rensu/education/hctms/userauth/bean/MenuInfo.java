package com.rensu.education.hctms.userauth.bean;

import java.util.ArrayList;
import java.util.List;

import com.rensu.education.hctms.core.bean.BaseBean;
/**
 * 菜单表
 * @date 2016年4月20日
 * @autor chen xiaoxiao
 */
public class MenuInfo extends BaseBean {
	
	
	private String sup_name;//上级菜单名称
	private String stateDesc;//状态描述
	private String createTimeStr;//创建时间字符类型。
	private List<MenuInfo> subMenuList = new ArrayList<MenuInfo>();//子菜单列表
	/**
	 * @return the sup_name
	 */
	public String getSup_name() {
		return sup_name;
	}
	/**
	 * @param sup_name the sup_name to set
	 */
	public void setSup_name(String sup_name) {
		this.sup_name = sup_name;
	}
	/**
	 * @return the stateDesc
	 */
	public String getStateDesc() {
		return stateDesc;
	}
	/**
	 * @param stateDesc the stateDesc to set
	 */
	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc;
	}
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
	 * @return the subMenuList
	 */
	public List<MenuInfo> getSubMenuList() {
		return subMenuList;
	}
	/**
	 * @param subMenuList the subMenuList to set
	 */
	public void setSubMenuList(List<MenuInfo> subMenuList) {
		this.subMenuList = subMenuList;
	}
	/**
	 * 添加单个子菜单。
	 * @param menu
	 * @date 2016年5月3日
	 * @autor chen xiaoxiao
	 */
	public void addSubMenu(MenuInfo menu) {
		this.subMenuList.add(menu);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MenuInfo [menu_id=" + menu_id + ", menu_name=" + menu_name
				+ ", url=" + url + ", sup_id=" + sup_id + ", sup_name="
				+ sup_name + ", remark=" + remark + ", state=" + state
				+ ", stateDesc=" + stateDesc + ", create_time=" + create_time
				+ ", createTimeStr=" + createTimeStr + ", icon_cls=" + icon_cls
				+ ", ordinal=" + ordinal + ", subMenuList=" + subMenuList + "]";
	}
	
	
}