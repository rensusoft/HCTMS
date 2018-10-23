package com.rensu.education.hctms.teach.bean;

import java.util.ArrayList;
import java.util.List;

import com.rensu.education.hctms.core.bean.BaseBean;

public class StuTeachOrder extends BaseBean {
	
	
	private Integer finnish_num;//完成数量
	private Integer data_type_id;
	
	private Integer completion_rate;//完成率
	private String req_content;
	private Integer tpc_id; //轮转计划配置表（TRAIN_PLAN_CONFIG）对应的ID
	private List<StuTeachOrder> subMenuList = new ArrayList<StuTeachOrder>();//子菜单列表
	
	public Integer getData_type_id() {
		return data_type_id;
	}
	public void setData_type_id(Integer data_type_id) {
		this.data_type_id = data_type_id;
	}
	public Integer getId() {
	public String getReq_content() {
		return req_content;
	}
	public void setReq_content(String req_content) {
		this.req_content = req_content;
	}
	public Integer getTpc_id() {
		return tpc_id;
	}
	public void setTpc_id(Integer tpc_id) {
		this.tpc_id = tpc_id;
	}
	public List<StuTeachOrder> getSubMenuList() {
		return subMenuList;
	}
	public void setSubMenuList(List<StuTeachOrder> subMenuList) {
		this.subMenuList = subMenuList;
	}
	public Integer getFinnish_num() {
		return finnish_num;
	}
	public void setFinnish_num(Integer finnish_num) {
		this.finnish_num = finnish_num;
	}
	public Integer getCompletion_rate() {
		return completion_rate;
	}
	public void setCompletion_rate(Integer completion_rate) {
		this.completion_rate = completion_rate;
	}
	/***
	 * 添加子菜单
	 * @param menu
	 * @author hezx
	 * @date 2017年1月23日
	 */
	public void addSubMenu(StuTeachOrder stuTeachOrdermenu) {
		this.subMenuList.add(stuTeachOrdermenu);
	}
}