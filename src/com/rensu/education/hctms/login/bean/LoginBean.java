package com.rensu.education.hctms.login.bean;

import java.util.List;

import com.rensu.education.hctms.core.bean.BaseBean;
import com.rensu.education.hctms.userauth.bean.MenuInfo;
import com.rensu.education.hctms.userauth.bean.VUserDetailInfo;

/**
 * 登录bean
 * 
 * @date 2016年4月22日
 * @autor chen xiaoxiao
 */
public class LoginBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//用户详细信息
	private VUserDetailInfo vUserDetailInfo;
	//用户角色对应的菜单列表
	private List<MenuInfo> menuList;
	//消息数量
	private Integer msgCount;
	//消息数量
	private Integer allMsgCount;
//	//学年学期信息。格式：开始学年,结束学年,学期。如2015,2016,2
//	private String termInfo;
//	//学年学期信息。格式：[0]开始学年,[1]结束学年,[2]学期。
//	private int[] termInfoArr;
	
	private int availabeHeight;
	private int availabeWidth;


	public Integer getAllMsgCount() {
		return allMsgCount;
	}

	public void setAllMsgCount(Integer allMsgCount) {
		this.allMsgCount = allMsgCount;
	}

	public int getAvailabeWidth() {
		return availabeWidth;
	}

	public void setAvailabeWidth(int availabeWidth) {
		this.availabeWidth = availabeWidth;
	}

	public int getAvailabeHeight() {
		return availabeHeight;
	}

	public void setAvailabeHeight(int availabeHeight) {
		this.availabeHeight = availabeHeight;
	}

	/**
	 * @return the vUserDetailInfo
	 */
	public VUserDetailInfo getvUserDetailInfo() {
		return vUserDetailInfo;
	}

	/**
	 * @param vUserDetailInfo the vUserDetailInfo to set
	 */
	public void setvUserDetailInfo(VUserDetailInfo vUserDetailInfo) {
		this.vUserDetailInfo = vUserDetailInfo;
	}

	/**
	 * @return the menuList
	 */
	public List<MenuInfo> getMenuList() {
		return menuList;
	}

	/**
	 * @param menuList the menuList to set
	 */
	public void setMenuList(List<MenuInfo> menuList) {
		this.menuList = menuList;
	}

	/**
	 * @return the msgCount
	 */
	public Integer getMsgCount() {
		return msgCount;
	}

	/**
	 * @param msgCount the msgCount to set
	 */
	public void setMsgCount(Integer msgCount) {
		this.msgCount = msgCount;
	}

	
	
}
