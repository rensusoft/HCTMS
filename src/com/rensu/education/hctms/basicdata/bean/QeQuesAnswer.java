package com.rensu.education.hctms.basicdata.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class QeQuesAnswer extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private Integer qq_id;//   试题库ID	private String answer;//   "答案（选择题存QE_QUESTION_OPTION表的ID或ID集合，以;隔开；判断题存放Y或N；填空题存放每个空格的答案，以;隔开；简答题存放大文本内容）"	private String state;//   状态	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getQq_id() {	    return this.qq_id;	}	public void setQq_id(Integer qq_id) {	    this.qq_id=qq_id;	}	public String getAnswer() {	    return this.answer;	}	public void setAnswer(String answer) {	    this.answer=answer;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
}
