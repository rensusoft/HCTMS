package com.rensu.education.hctms.userauth.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.userauth.bean.StuClass;

import configuration.mapper.StuClassMapper;

@Repository("stuClassDao")
public class StuClassDao extends BaseDao<StuClass> {
	
	Logger log = Logger.getLogger(StuClassDao.class);
	
	@Autowired
	private StuClassMapper<StuClass> stuClassMapper;
	
	@Override
	public int add(StuClass stuClass) {
		return stuClassMapper.add(stuClass);
	};
	
	@Override
	public int update(StuClass stuClass) {
		return stuClassMapper.update(stuClass);
	};
	
	@Override
	public int delete(StuClass stuClass) {
		return stuClassMapper.delete(stuClass);
	};
	
	@Override
	public StuClass selectOne(int id) {
		return stuClassMapper.selectOne(id);
	};
	
	@Override
	public List<StuClass> selectList(StuClass stuClass) {
		return stuClassMapper.selectList(stuClass);
	};
	
	@Override
	public List<StuClass> selectPage(RowBounds rowBounds, StuClass stuClass) {
		return stuClassMapper.selectPage(rowBounds, stuClass);
	};
	
	@Override
	public int selectCount(StuClass stuClass) {
		return stuClassMapper.selectCount(stuClass);
	};
	
	@Override
	public int getSequence() {
		return stuClassMapper.getSequence();
	}
	
}
