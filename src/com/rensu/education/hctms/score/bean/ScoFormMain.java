package com.rensu.education.hctms.score.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class ScoFormMain extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private Integer type_id;//   类型ID（1：入科宣教；2：出科审核；）	private Integer form_type;//   表单类型（1：评分表单类型--MARKSHEET_MAIN表；2：HTML普通表单类型--HTML_TEMPLATE表）	private String form_name;//   表单名称	private Integer form_sco;//   总分	private Integer user_auth_id;//   用户权限ID（针对哪个用户进行评分）	private Integer orga_id;//   机构ID	private String html_content;//   普通HTML表单内容	private Integer create_auth_id;//   创建人	private java.sql.Timestamp create_time;//   创建时间	private String state;//   状态	private Integer get_sco;//   得分
	private Integer form_id; //表单ID 
	private Integer or_id;
		public Integer getOr_id() {
		return or_id;
	}
	public void setOr_id(Integer or_id) {
		this.or_id = or_id;
	}
	public Integer getForm_id() {
		return form_id;
	}
	public void setForm_id(Integer form_id) {
		this.form_id = form_id;
	}
	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getType_id() {	    return this.type_id;	}	public void setType_id(Integer type_id) {	    this.type_id=type_id;	}	public Integer getForm_type() {	    return this.form_type;	}	public void setForm_type(Integer form_type) {	    this.form_type=form_type;	}	public String getForm_name() {	    return this.form_name;	}	public void setForm_name(String form_name) {	    this.form_name=form_name;	}	public Integer getForm_sco() {	    return this.form_sco;	}	public void setForm_sco(Integer form_sco) {	    this.form_sco=form_sco;	}	public Integer getUser_auth_id() {	    return this.user_auth_id;	}	public void setUser_auth_id(Integer user_auth_id) {	    this.user_auth_id=user_auth_id;	}	public Integer getOrga_id() {	    return this.orga_id;	}	public void setOrga_id(Integer orga_id) {	    this.orga_id=orga_id;	}	public String getHtml_content() {	    return this.html_content;	}	public void setHtml_content(String html_content) {	    this.html_content=html_content;	}	public Integer getCreate_auth_id() {	    return this.create_auth_id;	}	public void setCreate_auth_id(Integer create_auth_id) {	    this.create_auth_id=create_auth_id;	}	public java.sql.Timestamp getCreate_time() {	    return this.create_time;	}	public void setCreate_time(java.sql.Timestamp create_time) {	    this.create_time=create_time;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}	public Integer getGet_sco() {	    return this.get_sco;	}	public void setGet_sco(Integer get_sco) {	    this.get_sco=get_sco;	}
}
