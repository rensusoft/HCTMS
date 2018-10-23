package com.rensu.education.hctms.teach.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class StuExercises extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private String ques_title;//   练习标题	private Integer ques_num;//   题目总数	private Integer ques_sco;//   总得分	private java.sql.Timestamp create_time;//   创建时间	private Integer create_auth_id;//   创建人权限ID	private String state;//   状态	private Integer answer;//   答对题数
	private String time;
	private String cstate;	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getQues_title() {	    return this.ques_title;	}	public void setQues_title(String ques_title) {	    this.ques_title=ques_title;	}	public Integer getQues_num() {	    return this.ques_num;	}	public void setQues_num(Integer ques_num) {	    this.ques_num=ques_num;	}	public Integer getQues_sco() {	    return this.ques_sco;	}	public void setQues_sco(Integer ques_sco) {	    this.ques_sco=ques_sco;	}	public java.sql.Timestamp getCreate_time() {	    return this.create_time;	}	public void setCreate_time(java.sql.Timestamp create_time) {	    this.create_time=create_time;	}	public Integer getCreate_auth_id() {	    return this.create_auth_id;	}	public void setCreate_auth_id(Integer create_auth_id) {	    this.create_auth_id=create_auth_id;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}	public Integer getAnswer() {	    return this.answer;	}	public void setAnswer(Integer answer) {	    this.answer=answer;	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCstate() {
		return cstate;
	}
	public void setCstate(String cstate) {
		this.cstate = cstate;
	}
}
