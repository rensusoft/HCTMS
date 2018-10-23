package com.rensu.education.hctms.teach.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.teach.bean.CathedraCondition;

import configuration.mapper.CathedraConditionMapper;

@Repository("cathedraConditionDao")
public class CathedraConditionDao extends BaseDao<CathedraCondition> {
	
	Logger log = Logger.getLogger(CathedraConditionDao.class);
	
	@Autowired
	private CathedraConditionMapper<CathedraCondition> cathedraConditionMapper;
	
	@Override
	public int add(CathedraCondition cathedraCondition) {
		return cathedraConditionMapper.add(cathedraCondition);
	};
	
	@Override
	public int update(CathedraCondition cathedraCondition) {
		return cathedraConditionMapper.update(cathedraCondition);
	};
	
	@Override
	public int delete(CathedraCondition cathedraCondition) {
		return cathedraConditionMapper.delete(cathedraCondition);
	};
	
	@Override
	public CathedraCondition selectOne(int id) {
		return cathedraConditionMapper.selectOne(id);
	};
	
	@Override
	public List<CathedraCondition> selectList(CathedraCondition cathedraCondition) {
		return cathedraConditionMapper.selectList(cathedraCondition);
	};
	
	@Override
	public List<CathedraCondition> selectPage(RowBounds rowBounds, CathedraCondition cathedraCondition) {
		return cathedraConditionMapper.selectPage(rowBounds, cathedraCondition);
	};
	
	@Override
	public int selectCount(CathedraCondition cathedraCondition) {
		return cathedraConditionMapper.selectCount(cathedraCondition);
	};
	
	@Override
	public int getSequence() {
		return cathedraConditionMapper.getSequence();
	}
	/**
	 * 查询讲座编排
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年3月6日
	 */
	public List<CathedraCondition> selectCathedraCondition(CathedraCondition cathedraCondition){
		return cathedraConditionMapper.selectCathedraCondition(cathedraCondition);
	}
}
