package com.rensu.education.hctms.config.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class TrainPlanConfig extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private Integer tsc_id;//   轮转方案配置表（TRAIN_SCHEME_CONFIG）对应的ID	private String stu_type_code;//   学生类型（对应stu_type表的type_code）	private Integer dept_id;//   科室ID	private Integer duration;//   轮转时长	private String duration_unit;//   轮转时长单位（D：天；M：周；Y：年；）	private String group_num;//   科室组号（相同的表示一组）	private Integer ordinal;//   序号（数值越大越优先排）	private Integer train_dept_type;//   轮转科室类型（1：必选科室；2：自选科室）	private String state;//   状态
	private String req_content;//轮转规范
	private String orga_name;  //科室名
	private Integer limit_num; //人数上限
	
	private String user_code;
    private Integer auth_id;
    	public String getReq_content() {
		return req_content;
	}
	public void setReq_content(String req_content) {
		this.req_content = req_content;
	}
	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getTsc_id() {	    return this.tsc_id;	}	public void setTsc_id(Integer tsc_id) {	    this.tsc_id=tsc_id;	}	public String getStu_type_code() {	    return this.stu_type_code;	}	public void setStu_type_code(String stu_type_code) {	    this.stu_type_code=stu_type_code;	}	public Integer getDept_id() {	    return this.dept_id;	}	public void setDept_id(Integer dept_id) {	    this.dept_id=dept_id;	}	public Integer getDuration() {	    return this.duration;	}	public void setDuration(Integer duration) {	    this.duration=duration;	}	public String getDuration_unit() {	    return this.duration_unit;	}	public void setDuration_unit(String duration_unit) {	    this.duration_unit=duration_unit;	}	public String getGroup_num() {	    return this.group_num;	}	public void setGroup_num(String group_num) {	    this.group_num=group_num;	}	public Integer getOrdinal() {	    return this.ordinal;	}	public void setOrdinal(Integer ordinal) {	    this.ordinal=ordinal;	}	public Integer getTrain_dept_type() {	    return this.train_dept_type;	}	public void setTrain_dept_type(Integer train_dept_type) {	    this.train_dept_type=train_dept_type;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
	public String getOrga_name() {
		return orga_name;
	}
	public void setOrga_name(String orga_name) {
		this.orga_name = orga_name;
	}
	public Integer getLimit_num() {
		return limit_num;
	}
	public void setLimit_num(Integer limit_num) {
		this.limit_num = limit_num;
	}
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public Integer getAuth_id() {
		return auth_id;
	}
	public void setAuth_id(Integer auth_id) {
		this.auth_id = auth_id;
	}
	
	
}
