package com.rensu.education.hctms.teach.bean;

import java.util.List;

import com.rensu.education.hctms.basicdata.bean.ProcessInfo;
import com.rensu.education.hctms.core.bean.BaseBean;

public class LeaveProcess extends BaseBean {
	
		private static final long serialVersionUID = 1L;
	private List<ProcessInfo> processInfoList;	private String vacate_status_str;//  
	
	
	public List<ProcessInfo> getProcessInfoList() {
		return processInfoList;
	}
	public void setProcessInfoList(List<ProcessInfo> processInfoList) {
		this.processInfoList = processInfoList;
	}
	public String getVacate_status_str() {
		return vacate_status_str;
	}
	public void setVacate_status_str(String vacate_status_str) {
		this.vacate_status_str = vacate_status_str;
	}
	
}
