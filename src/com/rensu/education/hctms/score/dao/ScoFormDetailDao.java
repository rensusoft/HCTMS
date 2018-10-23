package com.rensu.education.hctms.score.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.score.bean.ScoFormDetail;
import com.rensu.education.hctms.score.bean.ScoFormSub;

import configuration.mapper.ScoFormDetailMapper;

@Repository("scoFormDetailDao")
public class ScoFormDetailDao extends BaseDao<ScoFormDetail> {
	
	Logger log = Logger.getLogger(ScoFormDetailDao.class);
	
	@Autowired
	private ScoFormDetailMapper<ScoFormDetail> scoFormDetailMapper;
	
	@Override
	public int add(ScoFormDetail scoFormDetail) {
		return scoFormDetailMapper.add(scoFormDetail);
	};
	
	@Override
	public int update(ScoFormDetail scoFormDetail) {
		return scoFormDetailMapper.update(scoFormDetail);
	};
	
	@Override
	public int delete(ScoFormDetail scoFormDetail) {
		return scoFormDetailMapper.delete(scoFormDetail);
	};
	
	@Override
	public ScoFormDetail selectOne(int id) {
		return scoFormDetailMapper.selectOne(id);
	};
	
	@Override
	public List<ScoFormDetail> selectList(ScoFormDetail scoFormDetail) {
		return scoFormDetailMapper.selectList(scoFormDetail);
	};
	
	@Override
	public List<ScoFormDetail> selectPage(RowBounds rowBounds, ScoFormDetail scoFormDetail) {
		return scoFormDetailMapper.selectPage(rowBounds, scoFormDetail);
	};
	
	@Override
	public int selectCount(ScoFormDetail scoFormDetail) {
		return scoFormDetailMapper.selectCount(scoFormDetail);
	};
	
	@Override
	public int getSequence() {
		return scoFormDetailMapper.getSequence();
	}  
	
}
