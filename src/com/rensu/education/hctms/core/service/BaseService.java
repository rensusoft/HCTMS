package com.rensu.education.hctms.core.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.util.StringUtil;


@Service("baseService")
public abstract class BaseService<T> {

	@Autowired
	private BaseDao<T> baseDao;
	
	/**
	 * 获取序列
	 * @return
	 */
	public int getSequence() {
		return baseDao.getSequence();
	}
	
	/**
	 * 新增操作
	 * @param t
	 */
	public int add(T t) {
		return baseDao.add(t);
	}
	
	/**
	 * 修改操作
	 * @param t
	 */
	public int update(T t) {
		return baseDao.update(t);
	}
	
	/**
	 * 删除操作
	 * @param t
	 */
	public int delete(T t) {
		return baseDao.delete(t);
	}

	/**
	 * 查询单个对象
	 * @param argsMap
	 * @return
	 */
	public T selectOne(int id) {
		return baseDao.selectOne(id);
	}

	/**
	 * 查询列表--将泛型作为查询条件
	 * @param argsMap
	 * @return
	 */
	public List<T> selectList(T t) {
		return baseDao.selectList(t);
	}
	
	/**
	 * jqgrid分页。
	 * @param pageIndex 要查询的页数
	 * @param rows 每次查询显示的记录数-即一次查多少行数据。
	 * @param t
	 * @return 
	 * total 总页数 
	 * page 当前页码 
	 * records 查询出的总的记录数 
	 * rows 包含实际数据的数组 
	 * @date 2016年5月9日
	 * @autor chen xiaoxiao
	 */
	public JSONObject selectPage(int pageIndex, int rows, T t) {
		JSONObject jobj = new JSONObject();
		int totalCount = baseDao.selectCount(t);
		List<T> dataList = baseDao.selectPage(new RowBounds((pageIndex - 1) * rows, pageIndex * rows), t);
		jobj.put("total", StringUtil.getMaxInt(totalCount, rows));//总页数
		jobj.put("page", pageIndex);//当前页码
		jobj.put("records", totalCount);//总的记录数
		jobj.put("rows", dataList);//显示的具体数据，jsonarray格式。
		if(jobj!=null){
			String jsonStr = StringUtil.dnull(jobj.toString());
			jobj = JSONObject.fromObject(jsonStr);
		}
		
		return jobj;
	}
	
	/**
	 * 根据bean查询总数
	 * @param t
	 * @return
	 */
	public int selectCount(T t) {
		return baseDao.selectCount(t);
	}
	
	
}
