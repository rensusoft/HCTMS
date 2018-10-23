package com.rensu.education.hctms.teach.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class MedicalAdviceSub extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private Integer mam_id;//   医嘱主表ID	private String advice_name;//   医嘱名称	private String advice_spec;//   规格	private Float advice_dose;//   剂量	private String dose_unit_code;//   剂量单位代码	private String path_code;//   途径代码	private String frequency_code;//   频率代码	private Float total_dose;//   总量	private String total_dose_unit_code;//   总量单位代码	private String doctor_memo;//   医生说明	private String state;//   状态
	private String dose_unit_code_str;
	private String path_code_str;
	private String frequency_code_str;
	private String total_dose_unit_code_str;
		public Float getAdvice_dose() {
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
	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getMam_id() {	    return this.mam_id;	}	public void setMam_id(Integer mam_id) {	    this.mam_id=mam_id;	}	public String getAdvice_name() {	    return this.advice_name;	}	public void setAdvice_name(String advice_name) {	    this.advice_name=advice_name;	}	public String getAdvice_spec() {	    return this.advice_spec;	}	public void setAdvice_spec(String advice_spec) {	    this.advice_spec=advice_spec;	}	public String getDose_unit_code() {	    return this.dose_unit_code;	}	public void setDose_unit_code(String dose_unit_code) {	    this.dose_unit_code=dose_unit_code;	}	public String getPath_code() {	    return this.path_code;	}	public void setPath_code(String path_code) {	    this.path_code=path_code;	}	public String getFrequency_code() {	    return this.frequency_code;	}	public void setFrequency_code(String frequency_code) {	    this.frequency_code=frequency_code;	}	public String getTotal_dose_unit_code() {	    return this.total_dose_unit_code;	}	public void setTotal_dose_unit_code(String total_dose_unit_code) {	    this.total_dose_unit_code=total_dose_unit_code;	}	public String getDoctor_memo() {	    return this.doctor_memo;	}	public void setDoctor_memo(String doctor_memo) {	    this.doctor_memo=doctor_memo;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
}
