package com.rensu.education.hctms.teach.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class StuExerQuestion extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private Integer qek_id;//   练习知识点ID(STU_EXER_KNOWLWDGE表ID)	private Integer qq_id;//   题库ID(QE_QUESTION表ID）	private String stu_answer;//   学生答案	private String stu_result;//   答题结果(答对Y;答错N)	private java.sql.Timestamp create_time;//   创建时间	private String state;//   状态
	private Integer qe_id;//   练习ID(STU_EXERCISES表ID)	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getQek_id() {	    return this.qek_id;	}	public void setQek_id(Integer qek_id) {	    this.qek_id=qek_id;	}	public Integer getQq_id() {	    return this.qq_id;	}	public void setQq_id(Integer qq_id) {	    this.qq_id=qq_id;	}	public String getStu_answer() {	    return this.stu_answer;	}	public void setStu_answer(String stu_answer) {	    this.stu_answer=stu_answer;	}	public String getStu_result() {	    return this.stu_result;	}	public void setStu_result(String stu_result) {	    this.stu_result=stu_result;	}	public java.sql.Timestamp getCreate_time() {	    return this.create_time;	}	public void setCreate_time(java.sql.Timestamp create_time) {	    this.create_time=create_time;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
	public Integer getQe_id() {
		return qe_id;
	}
	public void setQe_id(Integer qe_id) {
		this.qe_id = qe_id;
	}
}
