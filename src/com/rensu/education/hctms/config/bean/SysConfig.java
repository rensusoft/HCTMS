package com.rensu.education.hctms.config.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class SysConfig extends BaseBean {
	
		private static final long serialVersionUID = 1L;	private Integer id;//   标识	private Integer config_num;//   开关编号（数字）	private String config_code;//   配置代码（由开发人员自行定义，不能重复）	private Integer config_type;//   配置类型（1：系统设置；2：教学设置）	private String config_content;//   配置内容（存放HTML代码，在处理的时候需要后台方法处理）	private String config_data;//   配置中的数据（方便后台使用）	private Integer config_flag;//   启用标识（-1：未启用；1：启用）	private Integer availability;//   有效标识（-1：页面不可见不可编辑；1：页面上可见可编辑）
	private String config_explain;//开发说明
		public String getConfig_explain() {
		return config_explain;
	}
	public void setConfig_explain(String config_explain) {
		this.config_explain = config_explain;
	}
	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getConfig_num() {	    return this.config_num;	}	public void setConfig_num(Integer config_num) {	    this.config_num=config_num;	}	public String getConfig_code() {	    return this.config_code;	}	public void setConfig_code(String config_code) {	    this.config_code=config_code;	}	public Integer getConfig_type() {	    return this.config_type;	}	public void setConfig_type(Integer config_type) {	    this.config_type=config_type;	}	public String getConfig_content() {	    return this.config_content;	}	public void setConfig_content(String config_content) {	    this.config_content=config_content;	}	public String getConfig_data() {	    return this.config_data;	}	public void setConfig_data(String config_data) {	    this.config_data=config_data;	}	public Integer getConfig_flag() {	    return this.config_flag;	}	public void setConfig_flag(Integer config_flag) {	    this.config_flag=config_flag;	}	public Integer getAvailability() {	    return this.availability;	}	public void setAvailability(Integer availability) {	    this.availability=availability;	}
}
