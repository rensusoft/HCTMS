package com.rensu.education.hctms.teach.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class MedicalDiagnose extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private Integer mr_id;//   入院记录ID	private String diag_sort_code;//   诊断分类代码（ZY:中医;XY:西医）【暂时不用】	private String diag_type_code;//   中医诊断类型编码（1:疾病;2:症候）【暂时不用】	private String diag_name;//   诊断名称	private String icd_code;//   ICD编码	private String state;//   状态	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getMr_id() {	    return this.mr_id;	}	public void setMr_id(Integer mr_id) {	    this.mr_id=mr_id;	}	public String getDiag_sort_code() {	    return this.diag_sort_code;	}	public void setDiag_sort_code(String diag_sort_code) {	    this.diag_sort_code=diag_sort_code;	}	public String getDiag_type_code() {	    return this.diag_type_code;	}	public void setDiag_type_code(String diag_type_code) {	    this.diag_type_code=diag_type_code;	}	public String getDiag_name() {	    return this.diag_name;	}	public void setDiag_name(String diag_name) {	    this.diag_name=diag_name;	}	public String getIcd_code() {	    return this.icd_code;	}	public void setIcd_code(String icd_code) {	    this.icd_code=icd_code;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
}
