package com.rensu.education.hctms.config.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.config.bean.SysDictSub;

import configuration.mapper.SysDictSubMapper;

@Repository("sysDictSubDao")
public class SysDictSubDao extends BaseDao<SysDictSub> {
	
	Logger log = Logger.getLogger(SysDictSubDao.class);
	
	@Autowired
	private SysDictSubMapper<SysDictSub> sysDictSubMapper;
	
	@Override
	public int add(SysDictSub sysDictSub) {
		return sysDictSubMapper.add(sysDictSub);
	};
	
	@Override
	public int update(SysDictSub sysDictSub) {
		return sysDictSubMapper.update(sysDictSub);
	};
	
	@Override
	public int delete(SysDictSub sysDictSub) {
		return sysDictSubMapper.delete(sysDictSub);
	};
	
	@Override
	public SysDictSub selectOne(int id) {
		return sysDictSubMapper.selectOne(id);
	};
	
	@Override
	public List<SysDictSub> selectList(SysDictSub sysDictSub) {
		return sysDictSubMapper.selectList(sysDictSub);
	};
	
	@Override
	public List<SysDictSub> selectPage(RowBounds rowBounds, SysDictSub sysDictSub) {
		return sysDictSubMapper.selectPage(rowBounds, sysDictSub);
	};
	
	@Override
	public int selectCount(SysDictSub sysDictSub) {
		return sysDictSubMapper.selectCount(sysDictSub);
	};
	
	@Override
	public int getSequence() {
		return sysDictSubMapper.getSequence();
	}

	public SysDictSub selectByCode(String a) {
		
		return sysDictSubMapper.selectByCode(a);
	}
	
}
