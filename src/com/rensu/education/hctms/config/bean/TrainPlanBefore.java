package com.rensu.education.hctms.config.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class TrainPlanBefore extends BaseBean {
	
	
	private String orga_name;
	private Integer tsc_id;
	private Integer days;
	
	private Integer stu_class;
	private String tsc_name;
	
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
    public Integer getId() {
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