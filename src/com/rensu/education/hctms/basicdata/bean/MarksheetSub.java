package com.rensu.education.hctms.basicdata.bean;

import java.util.List;

import com.rensu.education.hctms.core.bean.BaseBean;

public class MarksheetSub extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private Integer mm_id;//   评分表单主表ID	private Integer type_code;//   是否子项（0：主项；1：子项）	private Integer sort_num;//   排序码	private String item_type_code;//   类型CODE（只有子项才有值，分值类型等，参照MARKSHEET_ITEMS表）	private String state;//   状态	private String text;//   内容
	private String title;//   标题
	private Integer ms_id;//   评分表字表ID
	private List<MarksheetDetail> details;	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getMm_id() {	    return this.mm_id;	}	public void setMm_id(Integer mm_id) {	    this.mm_id=mm_id;	}	public Integer getType_code() {	    return this.type_code;	}	public void setType_code(Integer type_code) {	    this.type_code=type_code;	}	public Integer getSort_num() {	    return this.sort_num;	}	public void setSort_num(Integer sort_num) {	    this.sort_num=sort_num;	}	public String getItem_type_code() {	    return this.item_type_code;	}	public void setItem_type_code(String item_type_code) {	    this.item_type_code=item_type_code;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}	public String getText() {	    return this.text;	}	public void setText(String text) {	    this.text=text;	}
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
