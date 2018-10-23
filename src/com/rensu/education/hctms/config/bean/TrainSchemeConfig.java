package com.rensu.education.hctms.config.bean;

import javax.servlet.http.HttpServletRequest;

import com.rensu.education.hctms.config.dao.TrainSchemeConfigDao;
import com.rensu.education.hctms.core.bean.BaseBean;

public class TrainSchemeConfig extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private String name;//   方案名称	private String stu_type_code;//   对应学生类型	private String major;//   专业	private String major_code;//   专业代码	private Integer time_long;//   年限	private String availability;//   是否启用（Y：启用；X：不启用）	private String state;//   状态
	private String stu_type_name;//学生类型名称
	private Integer orgaId;    //培训单位ID 	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getName() {	    return this.name;	}	public void setName(String name) {	    this.name=name;	}	public String getStu_type_code() {	    return this.stu_type_code;	}	public void setStu_type_code(String stu_type_code) {	    this.stu_type_code=stu_type_code;	}	public String getMajor() {	    return this.major;	}	public void setMajor(String major) {	    this.major=major;	}	public String getMajor_code() {	    return this.major_code;	}	public void setMajor_code(String major_code) {	    this.major_code=major_code;	}	public Integer getTime_long() {	    return this.time_long;	}	public void setTime_long(Integer time_long) {	    this.time_long=time_long;	}	public String getAvailability() {	    return this.availability;	}	public void setAvailability(String availability) {	    this.availability=availability;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
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
