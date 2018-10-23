package com.rensu.education.hctms.score.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.score.bean.ScoEval;

import configuration.mapper.ScoEvalMapper;

@Repository("scoEvalDao")
public class ScoEvalDao extends BaseDao<ScoEval> {
	
	Logger log = Logger.getLogger(ScoEvalDao.class);
	
	@Autowired
	private ScoEvalMapper<ScoEval> scoEvalMapper;
	
	@Override
	public int add(ScoEval scoEval) {
		return scoEvalMapper.add(scoEval);
	};
	
	@Override
	public int update(ScoEval scoEval) {
		return scoEvalMapper.update(scoEval);
	};
	
	@Override
	public int delete(ScoEval scoEval) {
		return scoEvalMapper.delete(scoEval);
	};
	
	@Override
	public ScoEval selectOne(int id) {
		return scoEvalMapper.selectOne(id);
	};
	
	@Override
	public List<ScoEval> selectList(ScoEval scoEval) {
		return scoEvalMapper.selectList(scoEval);
	};
	
	@Override
	public List<ScoEval> selectPage(RowBounds rowBounds, ScoEval scoEval) {
		return scoEvalMapper.selectPage(rowBounds, scoEval);
	};
	
	@Override
	public int selectCount(ScoEval scoEval) {
		return scoEvalMapper.selectCount(scoEval);
	};
	
	@Override
	public int getSequence() {
		return scoEvalMapper.getSequence();
	}
	
}
