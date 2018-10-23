package com.rensu.education.hctms.basicdata.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class QeQuestion extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private Integer type_id;//   试题类型（QE_QUES_TYPE表的ID）	private Integer qkb_id;//   绑定知识点（QE_KNOWLEDGE_BASE表ID）	private Integer teaching_require;//   *教学要求（1：掌握；2：熟悉；3：了解；）	private String group_num;//   组题号（组4选1这种存放同一个值，串4选1存放主题干的ID）	private Integer group_order;//   组内序号	private Integer sco_num;//   题目分值	private String ques_content;//   题目内容	private String ques_analysis;//   试题解析	private Integer difficulty_num;//   *难度系数	private Integer different_num;//   *区分度	private Integer use_num;//   使用次数	private java.sql.Timestamp lately_usetime;//   最近使用时间	private java.sql.Timestamp create_time;//   创建时间	private Integer create_auth_id;//   创建人权限ID	private String state;//   状态
	private String type_name;
	private String update_time;
	
	private String ssco_num;
	private String esco_num;
	private String sdifficulty_num;
	private String edifficulty_num;
	private String sdifferent_num;
	private String edifferent_num;
		public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getType_id() {	    return this.type_id;	}	public void setType_id(Integer type_id) {	    this.type_id=type_id;	}	public Integer getQkb_id() {	    return this.qkb_id;	}	public void setQkb_id(Integer qkb_id) {	    this.qkb_id=qkb_id;	}	public Integer getTeaching_require() {	    return this.teaching_require;	}	public void setTeaching_require(Integer teaching_require) {	    this.teaching_require=teaching_require;	}	public String getGroup_num() {	    return this.group_num;	}	public void setGroup_num(String group_num) {	    this.group_num=group_num;	}	public Integer getGroup_order() {	    return this.group_order;	}	public void setGroup_order(Integer group_order) {	    this.group_order=group_order;	}	public Integer getSco_num() {	    return this.sco_num;	}	public void setSco_num(Integer sco_num) {	    this.sco_num=sco_num;	}	public String getQues_content() {	    return this.ques_content;	}	public void setQues_content(String ques_content) {	    this.ques_content=ques_content;	}	public String getQues_analysis() {	    return this.ques_analysis;	}	public void setQues_analysis(String ques_analysis) {	    this.ques_analysis=ques_analysis;	}	public Integer getDifficulty_num() {	    return this.difficulty_num;	}	public void setDifficulty_num(Integer difficulty_num) {	    this.difficulty_num=difficulty_num;	}	public Integer getDifferent_num() {	    return this.different_num;	}	public void setDifferent_num(Integer different_num) {	    this.different_num=different_num;	}	public Integer getUse_num() {	    return this.use_num;	}	public void setUse_num(Integer use_num) {	    this.use_num=use_num;	}	public java.sql.Timestamp getLately_usetime() {	    return this.lately_usetime;	}	public void setLately_usetime(java.sql.Timestamp lately_usetime) {	    this.lately_usetime=lately_usetime;	}	public java.sql.Timestamp getCreate_time() {	    return this.create_time;	}	public void setCreate_time(java.sql.Timestamp create_time) {	    this.create_time=create_time;	}	public Integer getCreate_auth_id() {	    return this.create_auth_id;	}	public void setCreate_auth_id(Integer create_auth_id) {	    this.create_auth_id=create_auth_id;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getSsco_num() {
		return ssco_num;
	}
	public void setSsco_num(String ssco_num) {
		this.ssco_num = ssco_num;
	}
	public String getEsco_num() {
		return esco_num;
	}
	public void setEsco_num(String esco_num) {
		this.esco_num = esco_num;
	}
	public String getSdifficulty_num() {
		return sdifficulty_num;
	}
	public void setSdifficulty_num(String sdifficulty_num) {
		this.sdifficulty_num = sdifficulty_num;
	}
	public String getEdifficulty_num() {
		return edifficulty_num;
	}
	public void setEdifficulty_num(String edifficulty_num) {
		this.edifficulty_num = edifficulty_num;
	}
	public String getSdifferent_num() {
		return sdifferent_num;
	}
	public void setSdifferent_num(String sdifferent_num) {
		this.sdifferent_num = sdifferent_num;
	}
	public String getEdifferent_num() {
		return edifferent_num;
	}
	public void setEdifferent_num(String edifferent_num) {
		this.edifferent_num = edifferent_num;
	}
}
