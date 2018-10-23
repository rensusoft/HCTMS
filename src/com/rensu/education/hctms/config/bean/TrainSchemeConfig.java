package com.rensu.education.hctms.config.bean;

import javax.servlet.http.HttpServletRequest;

import com.rensu.education.hctms.config.dao.TrainSchemeConfigDao;
import com.rensu.education.hctms.core.bean.BaseBean;

public class TrainSchemeConfig extends BaseBean {
	
	
	private String stu_type_name;//学生类型名称
	private Integer orgaId;    //培训单位ID 
	public String getStu_type_name() {
		return stu_type_name;
	}
	public void setStu_type_name(String stu_type_name) {
		this.stu_type_name = stu_type_name;
	}
	public Integer getOrgaId() {
		return orgaId;
	}
	public void setOrgaId(Integer orgaId) {
		this.orgaId = orgaId;
	}
}