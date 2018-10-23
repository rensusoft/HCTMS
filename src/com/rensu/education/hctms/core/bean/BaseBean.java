/**
 * 
 */
package com.rensu.education.hctms.core.bean;

import java.io.Serializable;

/**
 * @author Chen Xiaoxiao
 * @date 2015年6月3日
 *
 */
public class BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//排序字段
	private String orderCondition;
	//分组等信息
	private String queryCondition;
	
	public String getOrderCondition() {
		return orderCondition;
	}
	public void setOrderCondition(String orderCondition) {
		this.orderCondition = orderCondition;
	}
	public String getQueryCondition() {
		return queryCondition;
	}
	public void setQueryCondition(String queryCondition) {
		this.queryCondition = queryCondition;
	}
	
}
