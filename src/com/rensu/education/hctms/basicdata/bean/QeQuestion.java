package com.rensu.education.hctms.basicdata.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class QeQuestion extends BaseBean {
	
	
	private String type_name;
	private String update_time;
	
	private String ssco_num;
	private String esco_num;
	private String sdifficulty_num;
	private String edifficulty_num;
	private String sdifferent_num;
	private String edifferent_num;
	
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getSsco_num() {
		return ssco_num;
	}
	public void setSsco_num(String ssco_num) {
		this.ssco_num = ssco_num;
	}
	public String getEsco_num() {
		return esco_num;
	}
	public void setEsco_num(String esco_num) {
		this.esco_num = esco_num;
	}
	public String getSdifficulty_num() {
		return sdifficulty_num;
	}
	public void setSdifficulty_num(String sdifficulty_num) {
		this.sdifficulty_num = sdifficulty_num;
	}
	public String getEdifficulty_num() {
		return edifficulty_num;
	}
	public void setEdifficulty_num(String edifficulty_num) {
		this.edifficulty_num = edifficulty_num;
	}
	public String getSdifferent_num() {
		return sdifferent_num;
	}
	public void setSdifferent_num(String sdifferent_num) {
		this.sdifferent_num = sdifferent_num;
	}
	public String getEdifferent_num() {
		return edifferent_num;
	}
	public void setEdifferent_num(String edifferent_num) {
		this.edifferent_num = edifferent_num;
	}
}