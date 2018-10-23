package com.rensu.education.hctms.basicdata.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class TfcStutypeformRela extends BaseBean {
	
	
	private static final long serialVersionUID = 1L;
	private Integer id;//   标识
	private Integer tfc_id;//   教学模板配置表ID
	private Integer stu_type_id;//   学生类型ID
	private String role_id;//   关联的角色，可以多个以分号分开（10;20）
	private Integer form_id;//   对应的表单ID（FORM_INFO表的ID），可以多个以分号分开（10;11）
	private String state;//   状态
	private String name;//  表单名
	private Integer orga_id;//   科室ID
	private Integer visual_flag;
	
	private Integer flag;//   表单是否已经填写内容标识（1：已填写        -1：未填写） 
	private Integer type_id;//表单类型
	private Integer pub_num;
	private Integer sfm_id;
	
	public Integer getSfm_id() {
		return sfm_id;
	}
	public void setSfm_id(Integer sfm_id) {
		this.sfm_id = sfm_id;
	}
	public Integer getVisual_flag() {
		return visual_flag;
	}
	public void setVisual_flag(Integer visual_flag) {
		this.visual_flag = visual_flag;
	}
	public Integer getPub_num() {
		return pub_num;
	}
	public void setPub_num(Integer pub_num) {
		this.pub_num = pub_num;
	}
	public Integer getType_id() {
		return type_id;
	}
	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Integer getOrga_id() {
		return orga_id;
	}
	public void setOrga_id(Integer orga_id) {
		this.orga_id = orga_id;
	}
	public Integer getId() {
	    return this.id;
	}
	public void setId(Integer id) {
	    this.id=id;
	}
	public Integer getTfc_id() {
	    return this.tfc_id;
	}
	public void setTfc_id(Integer tfc_id) {
	    this.tfc_id=tfc_id;
	}
	public Integer getStu_type_id() {
	    return this.stu_type_id;
	}
	public void setStu_type_id(Integer stu_type_id) {
	    this.stu_type_id=stu_type_id;
	}
	public String getRole_id() {
	    return this.role_id;
	}
	public void setRole_id(String role_id) {
	    this.role_id=role_id;
	}
	public Integer getForm_id() {
		return form_id;
	}
	public void setForm_id(Integer form_id) {
		this.form_id = form_id;
	}
	public String getState() {
	    return this.state;
	}
	public void setState(String state) {
	    this.state=state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
