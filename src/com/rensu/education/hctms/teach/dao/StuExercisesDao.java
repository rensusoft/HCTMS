package com.rensu.education.hctms.teach.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.teach.bean.StuExercises;

import configuration.mapper.StuExercisesMapper;

@Repository("stuExercisesDao")
public class StuExercisesDao extends BaseDao<StuExercises> {
	
	Logger log = Logger.getLogger(StuExercisesDao.class);
	
	@Autowired
	private StuExercisesMapper<StuExercises> stuExercisesMapper;
	
	@Override
	public int add(StuExercises stuExercises) {
		return stuExercisesMapper.add(stuExercises);
	};
	
	@Override
	public int update(StuExercises stuExercises) {
		return stuExercisesMapper.update(stuExercises);
	};
	
	@Override
	public int delete(StuExercises stuExercises) {
		return stuExercisesMapper.delete(stuExercises);
	};
	
	@Override
	public StuExercises selectOne(int id) {
		return stuExercisesMapper.selectOne(id);
	};
	
	@Override
	public List<StuExercises> selectList(StuExercises stuExercises) {
		return stuExercisesMapper.selectList(stuExercises);
	};
	
	@Override
	public List<StuExercises> selectPage(RowBounds rowBounds, StuExercises stuExercises) {
		return stuExercisesMapper.selectPage(rowBounds, stuExercises);
	};
	
	@Override
	public int selectCount(StuExercises stuExercises) {
		return stuExercisesMapper.selectCount(stuExercises);
	};
	
	@Override
	public int getSequence() {
		return stuExercisesMapper.getSequence();
	}
	
}
