package com.rensu.education.hctms.teach.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.teach.bean.StuExerKnowlwdge;

import configuration.mapper.StuExerKnowlwdgeMapper;

@Repository("stuExerKnowlwdgeDao")
public class StuExerKnowlwdgeDao extends BaseDao<StuExerKnowlwdge> {
	
	Logger log = Logger.getLogger(StuExerKnowlwdgeDao.class);
	
	@Autowired
	private StuExerKnowlwdgeMapper<StuExerKnowlwdge> stuExerKnowlwdgeMapper;
	
	@Override
	public int add(StuExerKnowlwdge stuExerKnowlwdge) {
		return stuExerKnowlwdgeMapper.add(stuExerKnowlwdge);
	};
	
	@Override
	public int update(StuExerKnowlwdge stuExerKnowlwdge) {
		return stuExerKnowlwdgeMapper.update(stuExerKnowlwdge);
	};
	
	@Override
	public int delete(StuExerKnowlwdge stuExerKnowlwdge) {
		return stuExerKnowlwdgeMapper.delete(stuExerKnowlwdge);
	};
	
	@Override
	public StuExerKnowlwdge selectOne(int id) {
		return stuExerKnowlwdgeMapper.selectOne(id);
	};
	
	@Override
	public List<StuExerKnowlwdge> selectList(StuExerKnowlwdge stuExerKnowlwdge) {
		return stuExerKnowlwdgeMapper.selectList(stuExerKnowlwdge);
	};
	
	@Override
	public List<StuExerKnowlwdge> selectPage(RowBounds rowBounds, StuExerKnowlwdge stuExerKnowlwdge) {
		return stuExerKnowlwdgeMapper.selectPage(rowBounds, stuExerKnowlwdge);
	};
	
	@Override
	public int selectCount(StuExerKnowlwdge stuExerKnowlwdge) {
		return stuExerKnowlwdgeMapper.selectCount(stuExerKnowlwdge);
	};
	
	@Override
	public int getSequence() {
		return stuExerKnowlwdgeMapper.getSequence();
	}
	
}
