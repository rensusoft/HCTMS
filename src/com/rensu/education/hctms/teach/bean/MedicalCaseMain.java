package com.rensu.education.hctms.teach.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class MedicalCaseMain extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private String title;//   讨论标题	private String in_patient_info;//   病人基本信息（随便写：类似：张三，02床，0329987）	private String content;//   讨论说明	private Integer exponent_auth_id;//   阐述人	private String exponent_content;//   阐述内容	private java.sql.Timestamp start_time;//   开始讨论时间	private java.sql.Timestamp finish_time;//   结束时间	private java.sql.Timestamp create_time;//   创建时间	private Integer creator_auth_id;//   创建人	private String status;//   状态（1：新建讨论，2：阐述人阐述， 3：学生讨论， 4：结束(已评分))	private String beginTime;
	private String  endTime;
	private String discussStatus;
	private String create_time_str;
		public String getDiscussStatus() {
		return discussStatus;
	}
	public void setDiscussStatus(String discussStatus) {
		this.discussStatus = discussStatus;
	}
	private String state;//   状态（Y：可用，X：删除）
	private String search_start_time;
	private String search_end_time;
	private String creator_auth_id_str;
	private String start_time_str;
	private String finish_time_str;
	private String exponent_auth_id_str;
	private String status_str;
	private Integer stu_auth_id;
	private String stu_auth_id_str;
	private Integer discuss_auth_id;
	private String discuss_time_str;
	private String discuss_auth_id_str;
	private String discuss_content;
		public String getDiscuss_time_str() {
		return discuss_time_str;
	}
	public void setDiscuss_time_str(String discuss_time_str) {
		this.discuss_time_str = discuss_time_str;
	}
	public String getDiscuss_auth_id_str() {
		return discuss_auth_id_str;
	}
	public void setDiscuss_auth_id_str(String discuss_auth_id_str) {
		this.discuss_auth_id_str = discuss_auth_id_str;
	}
	public String getDiscuss_content() {
		return discuss_content;
	}
	public void setDiscuss_content(String discuss_content) {
		this.discuss_content = discuss_content;
	}
	public Integer getStu_auth_id() {
		return stu_auth_id;
	}
	public void setStu_auth_id(Integer stu_auth_id) {
		this.stu_auth_id = stu_auth_id;
	}
	public String getStu_auth_id_str() {
		return stu_auth_id_str;
	}
	public void setStu_auth_id_str(String stu_auth_id_str) {
		this.stu_auth_id_str = stu_auth_id_str;
	}
	public String getCreator_auth_id_str() {
		return creator_auth_id_str;
	}
	public void setCreator_auth_id_str(String creator_auth_id_str) {
		this.creator_auth_id_str = creator_auth_id_str;
	}
	public String getStart_time_str() {
		return start_time_str;
	}
	public void setStart_time_str(String start_time_str) {
		this.start_time_str = start_time_str;
	}
	public String getFinish_time_str() {
		return finish_time_str;
	}
	public void setFinish_time_str(String finish_time_str) {
		this.finish_time_str = finish_time_str;
	}
	public String getExponent_auth_id_str() {
		return exponent_auth_id_str;
	}
	public void setExponent_auth_id_str(String exponent_auth_id_str) {
		this.exponent_auth_id_str = exponent_auth_id_str;
	}
	public String getStatus_str() {
		return status_str;
	}
	public void setStatus_str(String status_str) {
		this.status_str = status_str;
	}
	public String getSearch_start_time() {
		return search_start_time;
	}
	public void setSearch_start_time(String search_start_time) {
		this.search_start_time = search_start_time;
	}
	public String getSearch_end_time() {
		return search_end_time;
	}
	public void setSearch_end_time(String search_end_time) {
		this.search_end_time = search_end_time;
	}
	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getTitle() {	    return this.title;	}	public void setTitle(String title) {	    this.title=title;	}	public String getIn_patient_info() {	    return this.in_patient_info;	}	public void setIn_patient_info(String in_patient_info) {	    this.in_patient_info=in_patient_info;	}	public String getContent() {	    return this.content;	}	public void setContent(String content) {	    this.content=content;	}	public Integer getExponent_auth_id() {	    return this.exponent_auth_id;	}	public void setExponent_auth_id(Integer exponent_auth_id) {	    this.exponent_auth_id=exponent_auth_id;	}	public String getExponent_content() {	    return this.exponent_content;	}	public void setExponent_content(String exponent_content) {	    this.exponent_content=exponent_content;	}	public java.sql.Timestamp getStart_time() {	    return this.start_time;	}	public void setStart_time(java.sql.Timestamp start_time) {	    this.start_time=start_time;	}	public java.sql.Timestamp getFinish_time() {	    return this.finish_time;	}	public void setFinish_time(java.sql.Timestamp finish_time) {	    this.finish_time=finish_time;	}	public java.sql.Timestamp getCreate_time() {	    return this.create_time;	}	public void setCreate_time(java.sql.Timestamp create_time) {	    this.create_time=create_time;	}	public Integer getCreator_auth_id() {	    return this.creator_auth_id;	}	public void setCreator_auth_id(Integer creator_auth_id) {	    this.creator_auth_id=creator_auth_id;	}	public String getStatus() {	    return this.status;	}	public void setStatus(String status) {	    this.status=status;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
	public Integer getDiscuss_auth_id() {
		return discuss_auth_id;
	}
	public void setDiscuss_auth_id(Integer discuss_auth_id) {
		this.discuss_auth_id = discuss_auth_id;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getCreate_time_str() {
		return create_time_str;
	}
	public void setCreate_time_str(String create_time_str) {
		this.create_time_str = create_time_str;
	}
	
}
