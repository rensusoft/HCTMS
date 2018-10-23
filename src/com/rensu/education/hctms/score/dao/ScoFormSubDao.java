package com.rensu.education.hctms.score.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.score.bean.ScoFormSub;

import configuration.mapper.ScoFormSubMapper;

@Repository("scoFormSubDao")
public class ScoFormSubDao extends BaseDao<ScoFormSub> {
	
	Logger log = Logger.getLogger(ScoFormSubDao.class);
	
	@Autowired
	private ScoFormSubMapper<ScoFormSub> scoFormSubMapper;
	
	@Override
	public int add(ScoFormSub scoFormSub) {
		return scoFormSubMapper.add(scoFormSub);
	};
	
	@Override
	public int update(ScoFormSub scoFormSub) {
		return scoFormSubMapper.update(scoFormSub);
	};
	
	@Override
	public int delete(ScoFormSub scoFormSub) {
		return scoFormSubMapper.delete(scoFormSub);
	};
	
	@Override
	public ScoFormSub selectOne(int id) {
		return scoFormSubMapper.selectOne(id);
	};
	
	@Override
	public List<ScoFormSub> selectList(ScoFormSub scoFormSub) {
		return scoFormSubMapper.selectList(scoFormSub);
	};
	
	@Override
	public List<ScoFormSub> selectPage(RowBounds rowBounds, ScoFormSub scoFormSub) {
		return scoFormSubMapper.selectPage(rowBounds, scoFormSub);
	};
	
	@Override
	public int selectCount(ScoFormSub scoFormSub) {
		return scoFormSubMapper.selectCount(scoFormSub);
	};
	
	@Override
	public int getSequence() {
		return scoFormSubMapper.getSequence();
	}  
	
}
