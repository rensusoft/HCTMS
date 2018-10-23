package com.rensu.education.hctms.teach.bean;

import java.util.ArrayList;
import java.util.List;

import com.rensu.education.hctms.core.bean.BaseBean;

public class StuTeachOrder extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private Integer tto_id;//   教学大纲/要求表（TRAIN_TEACH_ORDER）的ID	private Integer dept_id;//   培训科室ID	private Integer stu_auth_id;//   学生authId	private String order_name;//   要求名称	private Integer type_id;//   是否子项（0:独立；1：主项；2：子项）	private Integer sup_id;//   上级ID（type_id是1的子项才有上级ID，表示属于哪个主项下面的子项）	private Integer sort_num;//   排序码	private Integer order_num;//   要求数（如果type_id是1主项的没有值）	private String order_num_unit;//   要求数单位	private String state;//   状态
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
	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getTto_id() {	    return this.tto_id;	}	public void setTto_id(Integer tto_id) {	    this.tto_id=tto_id;	}	public Integer getDept_id() {	    return this.dept_id;	}	public void setDept_id(Integer dept_id) {	    this.dept_id=dept_id;	}	public Integer getStu_auth_id() {	    return this.stu_auth_id;	}	public void setStu_auth_id(Integer stu_auth_id) {	    this.stu_auth_id=stu_auth_id;	}	public String getOrder_name() {	    return this.order_name;	}	public void setOrder_name(String order_name) {	    this.order_name=order_name;	}	public Integer getType_id() {	    return this.type_id;	}	public void setType_id(Integer type_id) {	    this.type_id=type_id;	}	public Integer getSup_id() {	    return this.sup_id;	}	public void setSup_id(Integer sup_id) {	    this.sup_id=sup_id;	}	public Integer getSort_num() {	    return this.sort_num;	}	public void setSort_num(Integer sort_num) {	    this.sort_num=sort_num;	}	public Integer getOrder_num() {	    return this.order_num;	}	public void setOrder_num(Integer order_num) {	    this.order_num=order_num;	}	public String getOrder_num_unit() {	    return this.order_num_unit;	}	public void setOrder_num_unit(String order_num_unit) {	    this.order_num_unit=order_num_unit;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
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
