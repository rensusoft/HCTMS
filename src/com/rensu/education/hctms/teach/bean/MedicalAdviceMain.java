package com.rensu.education.hctms.teach.bean;

import java.util.List;

import com.rensu.education.hctms.core.bean.BaseBean;

public class MedicalAdviceMain extends BaseBean {
	
	
	
	private List<MedicalAdviceSub> medicalAdviceSubList;
	private Integer type_id;
	private String correct_time_str;
	private String type_id_str;
	private String stu_auth_id_str;
	private String mr_name;
	private String content;
	
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getType_id() {
		return type_id;
	}
	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}
	public String getCorrect_time_str() {
		return correct_time_str;
	}
	public void setCorrect_time_str(String correct_time_str) {
		this.correct_time_str = correct_time_str;
	}
	public String getType_id_str() {
		return type_id_str;
	}
	public void setType_id_str(String type_id_str) {
		this.type_id_str = type_id_str;
	}
	public String getStu_auth_id_str() {
		return stu_auth_id_str;
	}
	public void setStu_auth_id_str(String stu_auth_id_str) {
		this.stu_auth_id_str = stu_auth_id_str;
	}
	public String getMr_name() {
		return mr_name;
	}
	public void setMr_name(String mr_name) {
		this.mr_name = mr_name;
	}
	public List<MedicalAdviceSub> getMedicalAdviceSubList() {
		return medicalAdviceSubList;
	}
	public void setMedicalAdviceSubList(List<MedicalAdviceSub> medicalAdviceSubList) {
		this.medicalAdviceSubList = medicalAdviceSubList;
	}
	public Integer getId() {
}