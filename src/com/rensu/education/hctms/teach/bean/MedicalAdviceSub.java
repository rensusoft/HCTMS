package com.rensu.education.hctms.teach.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class MedicalAdviceSub extends BaseBean {
	
	
	private String dose_unit_code_str;
	private String path_code_str;
	private String frequency_code_str;
	private String total_dose_unit_code_str;
	
		return advice_dose;
	}
	public String getDose_unit_code_str() {
		return dose_unit_code_str;
	}
	public void setDose_unit_code_str(String dose_unit_code_str) {
		this.dose_unit_code_str = dose_unit_code_str;
	}
	public String getPath_code_str() {
		return path_code_str;
	}
	public void setPath_code_str(String path_code_str) {
		this.path_code_str = path_code_str;
	}
	public String getFrequency_code_str() {
		return frequency_code_str;
	}
	public void setFrequency_code_str(String frequency_code_str) {
		this.frequency_code_str = frequency_code_str;
	}
	public String getTotal_dose_unit_code_str() {
		return total_dose_unit_code_str;
	}
	public void setTotal_dose_unit_code_str(String total_dose_unit_code_str) {
		this.total_dose_unit_code_str = total_dose_unit_code_str;
	}
	public void setAdvice_dose(Float advice_dose) {
		this.advice_dose = advice_dose;
	}
	public Float getTotal_dose() {
		return total_dose;
	}
	public void setTotal_dose(Float total_dose) {
		this.total_dose = total_dose;
	}
	public Integer getId() {
}