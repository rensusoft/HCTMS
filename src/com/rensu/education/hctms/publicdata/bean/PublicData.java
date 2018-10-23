package com.rensu.education.hctms.publicdata.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class PublicData extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private String type_code;//   资料类型（1：学习资料；2：日常资料；3：其他资料）	private String title;//   资料标题	private String content;//   资料内容	private String publisher_auth_id;//   发布人	private java.sql.Timestamp publish_time;//   发布时间	private String time;//   发布时间	private String state;//   状态	private String searchStr;//   关键字	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getType_code() {	    return this.type_code;	}	public void setType_code(String type_code) {	    this.type_code=type_code;	}	public String getTitle() {	    return this.title;	}	public void setTitle(String title) {	    this.title=title;	}	public String getContent() {	    return this.content;	}	public void setContent(String content) {	    this.content=content;	}	public String getPublisher_auth_id() {	    return this.publisher_auth_id;	}	public void setPublisher_auth_id(String publisher_auth_id) {	    this.publisher_auth_id=publisher_auth_id;	}	public java.sql.Timestamp getPublish_time() {	    return this.publish_time;	}	public void setPublish_time(java.sql.Timestamp publish_time) {	    this.publish_time=publish_time;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSearchStr() {
		return searchStr;
	}
	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}
	
}
