package com.rensu.education.hctms.score.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class StuSco extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private Integer stu_auth_id;//   学生权限ID	private Integer subject_id;//   科目ID（字典表中维护）	private Integer sco_num;//   成绩（分数，百分制）	private Integer create_auth_id;//   创建人员权限ID	private java.sql.Timestamp create_time;//   创建时间	private String state;//   状态
	
	private String stu_num;
	private String stu_name;
	private String item_type_name;
	
	private String scoNum;
	
	private String medicalSco;       //内科分数
	private String surgicalSco;      //外科
	private String especiallySco;    //妇儿科
	private String medicalScos;      //内科(补考)
	private String surgicalScos;     //外科(补考)
	private String especiallyScos;   //妇儿科(补考)
	private String finalSco;         //总考核
	private String user_code;        //学生工号

	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public String getMedicalSco() {
		return medicalSco;
	}
	public void setMedicalSco(String medicalSco) {
		this.medicalSco = medicalSco;
	}
	public String getSurgicalSco() {
		return surgicalSco;
	}
	public void setSurgicalSco(String surgicalSco) {
		this.surgicalSco = surgicalSco;
	}
	public String getEspeciallySco() {
		return especiallySco;
	}
	public void setEspeciallySco(String especiallySco) {
		this.especiallySco = especiallySco;
	}
	public String getMedicalScos() {
		return medicalScos;
	}
	public void setMedicalScos(String medicalScos) {
		this.medicalScos = medicalScos;
	}
	public String getSurgicalScos() {
		return surgicalScos;
	}
	public void setSurgicalScos(String surgicalScos) {
		this.surgicalScos = surgicalScos;
	}
	public String getEspeciallyScos() {
		return especiallyScos;
	}
	public void setEspeciallyScos(String especiallyScos) {
		this.especiallyScos = especiallyScos;
	}
	public String getFinalSco() {
		return finalSco;
	}
	public void setFinalSco(String finalSco) {
		this.finalSco = finalSco;
	}
	public String getScoNum() {
		return scoNum;
	}
	public void setScoNum(String scoNum) {
		this.scoNum = scoNum;
	}
	public String getItem_type_name() {
		return item_type_name;
	}
	public void setItem_type_name(String item_type_name) {
		this.item_type_name = item_type_name;
	}
	public String getStu_num() {
		return stu_num;
	}
	public void setStu_num(String stu_num) {
		this.stu_num = stu_num;
	}
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getStu_auth_id() {	    return this.stu_auth_id;	}	public void setStu_auth_id(Integer stu_auth_id) {	    this.stu_auth_id=stu_auth_id;	}	public Integer getSubject_id() {	    return this.subject_id;	}	public void setSubject_id(Integer subject_id) {	    this.subject_id=subject_id;	}	public Integer getSco_num() {	    return this.sco_num;	}	public void setSco_num(Integer sco_num) {	    this.sco_num=sco_num;	}	public Integer getCreate_auth_id() {	    return this.create_auth_id;	}	public void setCreate_auth_id(Integer create_auth_id) {	    this.create_auth_id=create_auth_id;	}	public java.sql.Timestamp getCreate_time() {	    return this.create_time;	}	public void setCreate_time(java.sql.Timestamp create_time) {	    this.create_time=create_time;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
}
