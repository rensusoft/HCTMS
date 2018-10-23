package com.rensu.education.hctms.config.bean;

import java.util.ArrayList;
import java.util.List;

import com.rensu.education.hctms.core.bean.BaseBean;

public class TrainTeachOrder extends BaseBean {
	
	
	
	private Integer data_type_id;
	private Integer stu_auth_id;
	private Integer dept_id;
	
	private Integer finnish_num;
	private float completion_rate;
	private String completion_rate_str;
	private List<TrainTeachOrder> trainTeachOrderSubList;
	private List<TrainTeachOrder> subMenuList = new ArrayList<TrainTeachOrder>();//子菜单列表
	
	public String getCompletion_rate_str() {
		return completion_rate_str;
	}
	public void setCompletion_rate_str(String completion_rate_str) {
		this.completion_rate_str = completion_rate_str;
	}
	public List<TrainTeachOrder> getTrainTeachOrderSubList() {
		return trainTeachOrderSubList;
	}
	public void setTrainTeachOrderSubList(
			List<TrainTeachOrder> trainTeachOrderSubList) {
		this.trainTeachOrderSubList = trainTeachOrderSubList;
	}
	public float getCompletion_rate() {
		return completion_rate;
	}
	public void setCompletion_rate(float completion_rate) {
		this.completion_rate = completion_rate;
	}
	public Integer getFinnish_num() {
		return finnish_num;
	}
	public void setFinnish_num(Integer finnish_num) {
		this.finnish_num = finnish_num;
	}
	public Integer getStu_auth_id() {
		return stu_auth_id;
	}
	public void setStu_auth_id(Integer stu_auth_id) {
		this.stu_auth_id = stu_auth_id;
	}
	public Integer getDept_id() {
		return dept_id;
	}
	public void setDept_id(Integer dept_id) {
		this.dept_id = dept_id;
	}
	public Integer getData_type_id() {
		return data_type_id;
	}
	public void setData_type_id(Integer data_type_id) {
		this.data_type_id = data_type_id;
	}
	public Integer getId() {
	public List<TrainTeachOrder> getSubMenuList() {
		return subMenuList;
	}
	public void setSubMenuList(List<TrainTeachOrder> subMenuList) {
		this.subMenuList = subMenuList;
	}
	
}