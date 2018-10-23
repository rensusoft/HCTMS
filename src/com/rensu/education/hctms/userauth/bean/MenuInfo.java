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
	
		private static final long serialVersionUID = 1L;	private Integer menu_id;//   菜单标识	private String menu_name;//   菜单名称	private String url;//   路径	private Integer sup_id;//   上级标识
	private String sup_name;//上级菜单名称	private String remark;//   备注	private String state;//   状态
	private String stateDesc;//状态描述	private java.sql.Timestamp create_time;//   创建时间
	private String createTimeStr;//创建时间字符类型。	private String icon_cls;//   图标	private Integer ordinal;//   排序码
	private List<MenuInfo> subMenuList = new ArrayList<MenuInfo>();//子菜单列表	public Integer getMenu_id() {	    return this.menu_id;	}	public void setMenu_id(Integer menu_id) {	    this.menu_id=menu_id;	}	public String getMenu_name() {	    return this.menu_name;	}	public void setMenu_name(String menu_name) {	    this.menu_name=menu_name;	}	public String getUrl() {	    return this.url;	}	public void setUrl(String url) {	    this.url=url;	}	public Integer getSup_id() {	    return this.sup_id;	}	public void setSup_id(Integer sup_id) {	    this.sup_id=sup_id;	}	public String getRemark() {	    return this.remark;	}	public void setRemark(String remark) {	    this.remark=remark;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}	public java.sql.Timestamp getCreate_time() {	    return this.create_time;	}	public void setCreate_time(java.sql.Timestamp create_time) {	    this.create_time=create_time;	}	public String getIcon_cls() {	    return this.icon_cls;	}	public void setIcon_cls(String icon_cls) {	    this.icon_cls=icon_cls;	}	public Integer getOrdinal() {	    return this.ordinal;	}	public void setOrdinal(Integer ordinal) {	    this.ordinal=ordinal;	}
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
