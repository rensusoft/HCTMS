package com.rensu.education.hctms.score.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.score.bean.StuSco;

import configuration.mapper.StuScoMapper;


@Repository("stuScoDao")
public class StuScoDao extends BaseDao<StuSco> {
	
	Logger log = Logger.getLogger(StuScoDao.class);
	
	@Autowired
	private StuScoMapper<StuSco> stuScoMapper;


	
	@Override
	public int add(StuSco stuSco) {
		return stuScoMapper.add(stuSco);
	};
	
	@Override
	public int update(StuSco stuSco) {
		return stuScoMapper.update(stuSco);
	};
	
	@Override
	public int delete(StuSco stuSco) {
		return stuScoMapper.delete(stuSco);
	};
	
	@Override
	public StuSco selectOne(int id) {
		return stuScoMapper.selectOne(id);
	};
	
	@Override
	public List<StuSco> selectList(StuSco stuSco) {
		return stuScoMapper.selectList(stuSco);
	};
	
	@Override
	public List<StuSco> selectPage(RowBounds rowBounds, StuSco stuSco) {
		return stuScoMapper.selectPage(rowBounds, stuSco);
	};
	
	@Override
	public int selectCount(StuSco stuSco) {
		return stuScoMapper.selectCount(stuSco);
	};
	
	@Override
	public int getSequence() {
		return stuScoMapper.getSequence();
	}

	public int insertWithList(List<StuSco> list) {
		
		return stuScoMapper.insertWithList(list);
	}
	
	public int updateByAuthIdAndSubjectId(StuSco stuSco) {
		
		return stuScoMapper.updateByAuthIdAndSubjectId(stuSco);
	}

	public List<StuSco> selectPageWithUserInfo(RowBounds rowBounds, StuSco stuSco) {
		
		return stuScoMapper.selectPageWithUserInfo(rowBounds, stuSco);
	}
	
	public List<StuSco> selectAll() {
		
		return stuScoMapper.selectAll();
	}

	public int selectPageWithUserInfoCount() {
		
		return stuScoMapper.selectPageWithUserInfoCount();
	}

}