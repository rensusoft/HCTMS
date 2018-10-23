package com.rensu.education.hctms.message.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class MessagePublish extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private String title;//   标题	private String content;//   内容	private Integer sender_auth_id;//   发送人authId	private java.sql.Timestamp send_time;//   发送时间	private Integer type_id;//   消息类型标识【1：系统消息；2：业务消息；】	private String related_table;//   关联表名	private Integer related_id;//   关联标识	private String state;//   状态
	private String send_time_str;//   发送时间  字符串格式	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getTitle() {	    return this.title;	}	public void setTitle(String title) {	    this.title=title;	}	public String getContent() {	    return this.content;	}	public void setContent(String content) {	    this.content=content;	}	public Integer getSender_auth_id() {	    return this.sender_auth_id;	}	public void setSender_auth_id(Integer sender_auth_id) {	    this.sender_auth_id=sender_auth_id;	}	public java.sql.Timestamp getSend_time() {	    return this.send_time;	}	public void setSend_time(java.sql.Timestamp send_time) {	    this.send_time=send_time;	}	public Integer getType_id() {	    return this.type_id;	}	public void setType_id(Integer type_id) {	    this.type_id=type_id;	}	public String getRelated_table() {	    return this.related_table;	}	public void setRelated_table(String related_table) {	    this.related_table=related_table;	}	public Integer getRelated_id() {	    return this.related_id;	}	public void setRelated_id(Integer related_id) {	    this.related_id=related_id;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
	public String getSend_time_str() {
		return send_time_str;
	}
	public void setSend_time_str(String send_time_str) {
		this.send_time_str = send_time_str;
	}
}
