package com.rensu.education.hctms.publicdata.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class ForumInfo extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private String type_code;//   类型ID（SYS_DICT_MAIN表记录）	private String title;//   标题	private String content;//   内容	private Integer publisher_auth_id;//   发布人	private java.sql.Timestamp publish_time;//   发布时间	private String state;//   状态
	private Integer totalCount;//   总论坛数目
	private Integer mineCount;//   个人发布的论坛数目
	private String searchStr;//   关键字搜索
	private String item_name;//   类型名
	private String user_name;//   发帖人
	private String publish_time_str;//   发帖时间
	private String sub_user_name;//   最后跟帖人   
	private Integer reply_count;//   论坛跟帖数目
	private String type_name;//   发帖人类型 
	private String orga_name;//   发帖人所在科室
	private Integer checked_num;//查看数
	private String stu_num;     //学号
	private Integer auth_id;
	private Integer role_id;
	
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	public String getStu_num() {
		return stu_num;
	}
	public void setStu_num(String stu_num) {
		this.stu_num = stu_num;
	}
	public Integer getAuth_id() {
		return auth_id;
	}
	public void setAuth_id(Integer auth_id) {
		this.auth_id = auth_id;
	}
	public Integer getChecked_num() {
		return checked_num;
	}
	public void setChecked_num(Integer checked_num) {
		this.checked_num = checked_num;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public String getOrga_name() {
		return orga_name;
	}
	public void setOrga_name(String orga_name) {
		this.orga_name = orga_name;
	}
	public Integer getReply_count() {
		return reply_count;
	}
	public void setReply_count(Integer reply_count) {
		this.reply_count = reply_count;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPublish_time_str() {
		return publish_time_str;
	}
	public void setPublish_time_str(String publish_time_str) {
		this.publish_time_str = publish_time_str;
	}
	public String getSub_user_name() {
		return sub_user_name;
	}
	public void setSub_user_name(String sub_user_name) {
		this.sub_user_name = sub_user_name;
	}
	public String getSearchStr() {
		return searchStr;
	}
	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getMineCount() {
		return mineCount;
	}
	public void setMineCount(Integer mineCount) {
		this.mineCount = mineCount;
	}
	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getType_code() {
		return type_code;
	}
	public void setType_code(String type_code) {
		this.type_code = type_code;
	}
	public String getTitle() {	    return this.title;	}	public void setTitle(String title) {	    this.title=title;	}	public String getContent() {	    return this.content;	}	public void setContent(String content) {	    this.content=content;	}	public Integer getPublisher_auth_id() {	    return this.publisher_auth_id;	}	public void setPublisher_auth_id(Integer publisher_auth_id) {	    this.publisher_auth_id=publisher_auth_id;	}	public java.sql.Timestamp getPublish_time() {	    return this.publish_time;	}	public void setPublish_time(java.sql.Timestamp publish_time) {	    this.publish_time=publish_time;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
}
