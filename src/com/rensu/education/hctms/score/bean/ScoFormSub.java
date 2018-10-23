package com.rensu.education.hctms.score.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class ScoFormSub extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private Integer sfm_id;//   表单评分记录主表ID	private Integer type_code;//   是否子项（0：主项；1：子项）	private Integer sort_num;//   排序码	private String item_type_code;//   类型CODE（只有子项才有值，分值类型等，参照MARKSHEET_ITEMS表）	private String text;//   内容	private String title;//   标题	private Integer sco_num;//   评分分值	private String state;//   状态
	private Integer sfs_id;//   本表的ID，用于子项查找主项
		public Integer getSfs_id() {
		return sfs_id;
	}
	public void setSfs_id(Integer sfs_id) {
		this.sfs_id = sfs_id;
	}
	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getSfm_id() {	    return this.sfm_id;	}	public void setSfm_id(Integer sfm_id) {	    this.sfm_id=sfm_id;	}	public Integer getType_code() {	    return this.type_code;	}	public void setType_code(Integer type_code) {	    this.type_code=type_code;	}	public Integer getSort_num() {	    return this.sort_num;	}	public void setSort_num(Integer sort_num) {	    this.sort_num=sort_num;	}	public String getItem_type_code() {	    return this.item_type_code;	}	public void setItem_type_code(String item_type_code) {	    this.item_type_code=item_type_code;	}	public String getText() {	    return this.text;	}	public void setText(String text) {	    this.text=text;	}	public String getTitle() {	    return this.title;	}	public void setTitle(String title) {	    this.title=title;	}	public Integer getSco_num() {	    return this.sco_num;	}	public void setSco_num(Integer sco_num) {	    this.sco_num=sco_num;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
}
