package com.rensu.education.hctms.app.bean;

public class StuOutDeptRecord {
	
	private Integer id;//ID
	private Integer examResult;//分数
	private Integer userRoleId;//操作时间
	private String createDateTime;//操作时间
	private String examContent1;//审核说明
	private String examContent2;//审核意见
	private String examContent3;//分数
	
	public Integer getExamResult() {
		return examResult;
	}
	public void setExamResult(Integer examResult) {
		this.examResult = examResult;
	}
	public Integer getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}
	public String getExamContent1() {
		return examContent1;
	}
	public void setExamContent1(String examContent1) {
		this.examContent1 = examContent1;
	}
	public String getExamContent2() {
		return examContent2;
	}
	public void setExamContent2(String examContent2) {
		this.examContent2 = examContent2;
	}
	public String getExamContent3() {
		return examContent3;
	}
	public void setExamContent3(String examContent3) {
		this.examContent3 = examContent3;
	}
	
	
}
