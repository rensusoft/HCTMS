package com.rensu.education.hctms.score.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class ScoEval extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private Integer type_id;//   评分类型ID（10：作业评分；20：医疗文书评分；30：病例讨论-阐述评分；31：病例讨论-讨论评分）	private Integer relation_id;//   关联ID	private String relation_table;//   关联表名（作业：TASK_DEAL；医疗文书：MEDICAL_RECORD或MEDICAL_ADVICE_MAIN；病例讨论：MEDICAL_CASE_MAIN）	private Integer stu_orga_id;//   科室ID   学生的科室	private String stu_auth_id;//   学生authId	private String eval_auth_id;//   评分人authId	private Integer eval_date;//   评分日期(类似：20170606)	private java.sql.Timestamp eval_time;//   评分时间	private String eval_content;//   评价内容	private Integer eval_score;//   评分分数	private String template_content;//   模板内容（*评价表单使用，暂时不用）	private String state;//   状态
	private Integer auth_type;
		public Integer getAuth_type() {
		return auth_type;
	}
	public void setAuth_type(Integer auth_type) {
		this.auth_type = auth_type;
	}
	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getType_id() {	    return this.type_id;	}	public void setType_id(Integer type_id) {	    this.type_id=type_id;	}	public Integer getRelation_id() {	    return this.relation_id;	}	public void setRelation_id(Integer relation_id) {	    this.relation_id=relation_id;	}	public String getRelation_table() {	    return this.relation_table;	}	public void setRelation_table(String relation_table) {	    this.relation_table=relation_table;	}	public Integer getStu_orga_id() {	    return this.stu_orga_id;	}	public void setStu_orga_id(Integer stu_orga_id) {	    this.stu_orga_id=stu_orga_id;	}	public String getStu_auth_id() {	    return this.stu_auth_id;	}	public void setStu_auth_id(String stu_auth_id) {	    this.stu_auth_id=stu_auth_id;	}	public String getEval_auth_id() {	    return this.eval_auth_id;	}	public void setEval_auth_id(String eval_auth_id) {	    this.eval_auth_id=eval_auth_id;	}	public Integer getEval_date() {	    return this.eval_date;	}	public void setEval_date(Integer eval_date) {	    this.eval_date=eval_date;	}	public java.sql.Timestamp getEval_time() {	    return this.eval_time;	}	public void setEval_time(java.sql.Timestamp eval_time) {	    this.eval_time=eval_time;	}	public String getEval_content() {	    return this.eval_content;	}	public void setEval_content(String eval_content) {	    this.eval_content=eval_content;	}	public Integer getEval_score() {	    return this.eval_score;	}	public void setEval_score(Integer eval_score) {	    this.eval_score=eval_score;	}	public String getTemplate_content() {	    return this.template_content;	}	public void setTemplate_content(String template_content) {	    this.template_content=template_content;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
}
