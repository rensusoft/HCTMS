package com.rensu.education.hctms.publicdata.bean;


import com.rensu.education.hctms.core.bean.BaseBean;

public class ForumSub extends BaseBean {
	
	
	private String user_name;//   发布人名字
	private String type_name;//   发布人类型
	private String orga_name;//   发布人所在科室
	private String publish_time_str;//   发布时间 
	private String cite_user_name;//   引用人姓名
	private String cite_content;//   引用内容  
	private String cite_publish_time_str;//   引用时间
	private String stu_num;
		return stu_num;
	}
	public void setStu_num(String stu_num) {
		this.stu_num = stu_num;
	}
	public String getCite_user_name() {
		return cite_user_name;
	}
	public void setCite_user_name(String cite_user_name) {
		this.cite_user_name = cite_user_name;
	}
	public String getCite_content() {
		return cite_content;
	}
	public void setCite_content(String cite_content) {
		this.cite_content = cite_content;
	}
	public String getCite_publish_time_str() {
		return cite_publish_time_str;
	}
	public void setCite_publish_time_str(String cite_publish_time_str) {
		this.cite_publish_time_str = cite_publish_time_str;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
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
	public String getPublish_time_str() {
		return publish_time_str;
	}
	public void setPublish_time_str(String publish_time_str) {
		this.publish_time_str = publish_time_str;
	}
	public Integer getId() {
}