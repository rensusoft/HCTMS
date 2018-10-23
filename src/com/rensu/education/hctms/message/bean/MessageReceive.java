package com.rensu.education.hctms.message.bean;


import com.rensu.education.hctms.core.bean.BaseBean;

public class MessageReceive extends BaseBean {
	
	
	private String check_time_str; //查看时间字符串
	private String title; //标题
	private String sendName;//发送人姓名
	private String sendTimeStr; //发送时间
	private String content; //内容
	private Integer type_id;  //消息类型标识 
	private String receiverName;// 接收人姓名
	private String type_Name;// 消息类型   
	public String getCheck_time_str() {
		return check_time_str;
	}
	public void setCheck_time_str(String check_time_str) {
		this.check_time_str = check_time_str;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSendName() {
		return sendName;
	}
	public void setSendName(String sendName) {
		this.sendName = sendName;
	}
	public String getSendTimeStr() {
		return sendTimeStr;
	}
	public void setSendTimeStr(String sendTimeStr) {
		this.sendTimeStr = sendTimeStr;
	}
	public Integer getType_id() {
		return type_id;
	}
	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getType_Name() {
		return type_Name;
	}
	public void setType_Name(String type_Name) {
		this.type_Name = type_Name;
	}
}