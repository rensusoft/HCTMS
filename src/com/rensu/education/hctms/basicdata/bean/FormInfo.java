package com.rensu.education.hctms.basicdata.bean;

import java.util.List;

import com.rensu.education.hctms.core.bean.BaseBean;

public class FormInfo extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识
	private Integer type_id;//   表单类型（1：评分表单类型--MARKSHEET_MAIN表；2：HTML普通表单类型--HTML_TEMPLATE表）
	private Integer end_proc_id;
	private Integer related_id;//   关联表单ID（评分表单类型--MARKSHEET_MAIN表的ID；HTML普通表单类型--HTML_TEMPLATE表的ID）
	private java.sql.Timestamp create_time;//   创建时间
	private String state;//   状态
	private String availability;
	private String namestr;
	private String t_name;
	private String name;
	private String form_state;
	private Integer form_type;
	private String total_sconum;//   总分
	private String t_content;//   模板内容（HTML格式）
	private Integer create_auth_id;
	private String user_name;//   模板名称
	private String time;//   创建时间  
	private List<MarksheetSub> subs; 
	private Integer flag; 
	
	private Integer form_sco; //   总分
	private Integer get_sco; //   得分
	private String sfm_id;
	
	private Integer visual_flag;
	
	public Integer getVisual_flag() {
		return visual_flag;
	}
	public void setVisual_flag(Integer visual_flag) {
		this.visual_flag = visual_flag;
	}
	public String getSfm_id() {
		return sfm_id;
	}
	public void setSfm_id(String sfm_id) {
		this.sfm_id = sfm_id;
	}
	public Integer getForm_sco() {
		return form_sco;
	}
	public void setForm_sco(Integer form_sco) {
		this.form_sco = form_sco;
	}
	public Integer getGet_sco() {
		return get_sco;
	}
	public void setGet_sco(Integer get_sco) {
		this.get_sco = get_sco;
	}
	public String getNamestr() {
		return namestr;
	}
	public void setNamestr(String namestr) {
		this.namestr = namestr;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getType_id() {	    return this.type_id;	}	public void setType_id(Integer type_id) {	    this.type_id=type_id;	}	public Integer getRelated_id() {	    return this.related_id;	}	public void setRelated_id(Integer related_id) {	    this.related_id=related_id;	}	public java.sql.Timestamp getCreate_time() {	    return this.create_time;	}	public void setCreate_time(java.sql.Timestamp create_time) {	    this.create_time=create_time;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
	
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getEnd_proc_id() {
		return end_proc_id;
	}
	public void setEnd_proc_id(Integer end_proc_id) {
		this.end_proc_id = end_proc_id;
	}
	public Integer getForm_type() {
		return form_type;
	}
	public void setForm_type(Integer form_type) {
		this.form_type = form_type;
	}
	public String getTotal_sconum() {
		return total_sconum;
	}
	public void setTotal_sconum(String total_sconum) {
		this.total_sconum = total_sconum;
	}
	public String getT_content() {
		return t_content;
	}
	public void setT_content(String t_content) {
		this.t_content = t_content;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getCreate_auth_id() {
		return create_auth_id;
	}
	public void setCreate_auth_id(Integer create_auth_id) {
		this.create_auth_id = create_auth_id;
	}
	public List<MarksheetSub> getSubs() {
		return subs;
	}
	public void setSubs(List<MarksheetSub> subs) {
		this.subs = subs;
	}
	public String getForm_state() {
		return form_state;
	}
	public void setForm_state(String form_state) {
		this.form_state = form_state;
	}

}
