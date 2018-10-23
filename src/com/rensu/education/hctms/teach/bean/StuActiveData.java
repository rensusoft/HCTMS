package com.rensu.education.hctms.teach.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class StuActiveData extends BaseBean {
	
	
	private java.sql.Timestamp examine_time;
	private String order_name;   //要求名称
	private String examineState; //审核状态
	private String create_time_str;//创建时间 字符串类型 
	private String startTime;  //开始时间
	private String endTime;  //结束时间
	private String indistinct;//模糊查询 字段
	private Integer count;  //查询数量总和
	private String mOrder_name; //父类的要求名称
	
	private Integer examine_authid; //审批人authId
	private String examine_creater;  
	private String user_name;
	
	private Integer data_type_id;
	
	public Integer getData_type_id() {
		return data_type_id;
	}
	public void setData_type_id(Integer data_type_id) {
		this.data_type_id = data_type_id;
	}
	public String getExamine_creater() {
		return examine_creater;
	}
	public void setExamine_creater(String examine_creater) {
		this.examine_creater = examine_creater;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Integer getExamine_authid() {
		return examine_authid;
	}
	public void setExamine_authid(Integer examine_authid) {
		this.examine_authid = examine_authid;
	}
	public java.sql.Timestamp getExamine_time() {
		return examine_time;
	}
	public void setExamine_time(java.sql.Timestamp examine_time) {
		this.examine_time = examine_time;
	}
	public Integer getId() {
	public String getOrder_name() {
		return order_name;
	}
	public void setOrder_name(String order_name) {
		this.order_name = order_name;
	}
	public String getExamineState() {
		return examineState;
	}
	public void setExamineState(String examineState) {
		this.examineState = examineState;
	}
	public String getCreate_time_str() {
		return create_time_str;
	}
	public void setCreate_time_str(String create_time_str) {
		this.create_time_str = create_time_str;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getIndistinct() {
		return indistinct;
	}
	public void setIndistinct(String indistinct) {
		this.indistinct = indistinct;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getmOrder_name() {
		return mOrder_name;
	}
	public void setmOrder_name(String mOrder_name) {
		this.mOrder_name = mOrder_name;
	}
}