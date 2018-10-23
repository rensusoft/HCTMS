package com.rensu.education.hctms.userauth.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.userauth.bean.StuType;

import configuration.mapper.StuTypeMapper;

@Repository("stuTypeDao")
public class StuTypeDao extends BaseDao<StuType> {
	
	Logger log = Logger.getLogger(StuTypeDao.class);
	
	@Autowired
	private StuTypeMapper<StuType> stuTypeMapper;
	
	@Override
	public int add(StuType stuType) {
		return stuTypeMapper.add(stuType);
	};
	
	@Override
	public int update(StuType stuType) {
		return stuTypeMapper.update(stuType);
	};
	
	@Override
	public int delete(StuType stuType) {
		return stuTypeMapper.delete(stuType);
	};
	
	@Override
	public StuType selectOne(int id) {
		return stuTypeMapper.selectOne(id);
	};
	
	@Override
	public List<StuType> selectList(StuType stuType) {
		return stuTypeMapper.selectList(stuType);
	};
	
	@Override
	public List<StuType> selectPage(RowBounds rowBounds, StuType stuType) {
		return stuTypeMapper.selectPage(rowBounds, stuType);
	};
	
	@Override
	public int selectCount(StuType stuType) {
		return stuTypeMapper.selectCount(stuType);
	};
	
	@Override
	public int getSequence() {
		return stuTypeMapper.getSequence();
	}
	
}
