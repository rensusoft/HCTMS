package com.rensu.education.hctms.core.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository("baseDao")
public class BaseDao<T> {
	
	/**
	 * 获取该模块对应表的序列。
	 * @return
	 */
	public int getSequence() {
		return -1;
	}
	
	/**
	 * 新增操作
	 * @param t
	 */
	public int add(T t) {
		return 0;
	}
	
	/**
	 * 修改操作
	 * @param t
	 */
	public int update(T t) {
		return 0;
	}
	
	/**
	 * 删除操作
	 * @param t
	 */
	public int delete(T t) {
		return 0;
	}
	
	/**
	 * 查询单个对象
	 * @param argsMap
	 * @return
	 */
	public T selectOne(int id) {
		return null;
	}
	
	/**
	 * 查询列表-将泛型作为查询条件。
	 * @param t
	 * @return
	 */
	public List<T> selectList(T t) {
		return null;
	}
	
	/**
	 * 分页查询
	 * @param rowBounds
	 * @param t
	 * @return
	 */
	public List<T> selectPage(RowBounds rowBounds, T t) {
		return null;
	}
	
	/**
	 * 根据bean查询总数
	 * @param t
	 * @return
	 */
	public int selectCount(T t) {
		return 0;
	}
	
}
