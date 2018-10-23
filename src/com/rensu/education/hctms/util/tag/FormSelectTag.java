package com.rensu.education.hctms.util.tag;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.rensu.education.hctms.util.StringUtil;

/**
 * 生成select标签。
 * 
 * @date 2016年5月10日
 * @autor chen xiaoxiao
 */
public class FormSelectTag extends RequestContextAwareTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//标签id
	private String id;
	//标签名称
	private String name;
	//查询的sql
	private String sql;
	//初始值
	private String initVal;
	//是否包含第一个空值（一般显示“请选择”），值为true/false
	private String hasNull;
	//样式文件
	private String cls;
	//页面内样式
	private String style;
	
	private WebApplicationContext context;
	/**
	 * @return the sql
	 */
	public String getSql() {
		return sql;
	}
	/**
	 * @param sql the sql to set
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}
	/**
	 * @return the initVal
	 */
	public String getInitVal() {
		return initVal;
	}
	/**
	 * @param initVal the initVal to set
	 */
	public void setInitVal(String initVal) {
		this.initVal = initVal;
	}
	/**
	 * @return the hasNull
	 */
	public String getHasNull() {
		return hasNull;
	}
	/**
	 * @param hasNull the hasNull to set
	 */
	public void setHasNull(String hasNull) {
		this.hasNull = hasNull;
	}
	/**
	 * @return the cls
	 */
	public String getCls() {
		return cls;
	}
	/**
	 * @param cls the cls to set
	 */
	public void setCls(String cls) {
		this.cls = cls;
	}
	/**
	 * @return the style
	 */
	public String getStyle() {
		return style;
	}
	/**
	 * @param style the style to set
	 */
	public void setStyle(String style) {
		this.style = style;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * 生成select标签头部
	 * @return
	 * @date 2016年5月10日
	 * @autor chen xiaoxiao
	 */
	private String generateHead() {
		StringBuffer head = new StringBuffer("<select");
		if (StringUtil.isNotEmpty(cls)) {
			head.append(" class='" + cls + "'");
		}
		if (StringUtil.isNotEmpty(style)) {
			head.append(" style='" + style + "'");
		}
		if (StringUtil.isNotEmpty(id)) {
			head.append(" id='" + id + "'");
		}
		if (StringUtil.isNotEmpty(name)) {
			head.append(" name='" + name + "'");
		}
		head.append(">");
		
		return head.toString();
	}
	@Override
	protected int doStartTagInternal() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
