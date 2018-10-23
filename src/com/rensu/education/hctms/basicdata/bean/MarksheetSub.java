package com.rensu.education.hctms.basicdata.bean;

import java.util.List;

import com.rensu.education.hctms.core.bean.BaseBean;

public class MarksheetSub extends BaseBean {
	
	
	private String title;//   标题
	private Integer ms_id;//   评分表字表ID
	private List<MarksheetDetail> details;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getMs_id() {
		return ms_id;
	}
	public void setMs_id(Integer ms_id) {
		this.ms_id = ms_id;
	}
	public List<MarksheetDetail> getDetails() {
		return details;
	}
	public void setDetails(List<MarksheetDetail> details) {
		this.details = details;
	}
}