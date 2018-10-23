package com.rensu.education.hctms.config.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class TrainPlanBefore extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private Integer config_id;//   配置表ID（TRAIN_RULE_CONFIG表ID）	private Integer stu_auth_id;//   学生authId	private Integer train_dept_id;//   轮转科室ID	private Integer train_start_date;//   轮转开始日期	private Integer train_end_date;//   轮转结束日期	private java.sql.Timestamp train_start_time;//   轮转开始时间	private java.sql.Timestamp train_end_time;//   轮转结束时间	private String state;//   状态
	private String orga_name;
	private Integer tsc_id;
	private Integer days;
		private Integer orga_order;
	private Integer stu_class;
	private String tsc_name;
		public String getTsc_name() {
        return tsc_name;
    }
    public void setTsc_name(String tsc_name) {
        this.tsc_name = tsc_name;
    }
    public Integer getStu_class() {
        return stu_class;
    }
    public void setStu_class(Integer stu_class) {
        this.stu_class = stu_class;
    }
    public Integer getOrga_order() {
        return orga_order;
    }
    public void setOrga_order(Integer orga_order) {
        this.orga_order = orga_order;
    }
    public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getConfig_id() {	    return this.config_id;	}	public void setConfig_id(Integer config_id) {	    this.config_id=config_id;	}	public Integer getStu_auth_id() {	    return this.stu_auth_id;	}	public void setStu_auth_id(Integer stu_auth_id) {	    this.stu_auth_id=stu_auth_id;	}	public Integer getTrain_dept_id() {	    return this.train_dept_id;	}	public void setTrain_dept_id(Integer train_dept_id) {	    this.train_dept_id=train_dept_id;	}	public Integer getTrain_start_date() {	    return this.train_start_date;	}	public void setTrain_start_date(Integer train_start_date) {	    this.train_start_date=train_start_date;	}	public Integer getTrain_end_date() {	    return this.train_end_date;	}	public void setTrain_end_date(Integer train_end_date) {	    this.train_end_date=train_end_date;	}	public java.sql.Timestamp getTrain_start_time() {	    return this.train_start_time;	}	public void setTrain_start_time(java.sql.Timestamp train_start_time) {	    this.train_start_time=train_start_time;	}	public java.sql.Timestamp getTrain_end_time() {	    return this.train_end_time;	}	public void setTrain_end_time(java.sql.Timestamp train_end_time) {	    this.train_end_time=train_end_time;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
	public String getOrga_name() {
		return orga_name;
	}
	public void setOrga_name(String orga_name) {
		this.orga_name = orga_name;
	}
	public Integer getTsc_id() {
		return tsc_id;
	}
	public void setTsc_id(Integer tsc_id) {
		this.tsc_id = tsc_id;
	}
	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}
}
