package com.rensu.education.hctms.teach.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.teach.bean.StuExerQuestion;

import configuration.mapper.StuExerQuestionMapper;

@Repository("stuExerQuestionDao")
public class StuExerQuestionDao extends BaseDao<StuExerQuestion> {
	
	Logger log = Logger.getLogger(StuExerQuestionDao.class);
	
	@Autowired
	private StuExerQuestionMapper<StuExerQuestion> stuExerQuestionMapper;
	
	@Override
	public int add(StuExerQuestion stuExerQuestion) {
		return stuExerQuestionMapper.add(stuExerQuestion);
	};
	
	@Override
	public int update(StuExerQuestion stuExerQuestion) {
		return stuExerQuestionMapper.update(stuExerQuestion);
	};
	
	@Override
	public int delete(StuExerQuestion stuExerQuestion) {
		return stuExerQuestionMapper.delete(stuExerQuestion);
	};
	
	@Override
	public StuExerQuestion selectOne(int id) {
		return stuExerQuestionMapper.selectOne(id);
	};
	
	@Override
	public List<StuExerQuestion> selectList(StuExerQuestion stuExerQuestion) {
		return stuExerQuestionMapper.selectList(stuExerQuestion);
	};
	
	@Override
	public List<StuExerQuestion> selectPage(RowBounds rowBounds, StuExerQuestion stuExerQuestion) {
		return stuExerQuestionMapper.selectPage(rowBounds, stuExerQuestion);
	};
	
	@Override
	public int selectCount(StuExerQuestion stuExerQuestion) {
		return stuExerQuestionMapper.selectCount(stuExerQuestion);
	};
	
	@Override
	public int getSequence() {
		return stuExerQuestionMapper.getSequence();
	}
	
	public int insertWithList(List<StuExerQuestion> list){
		return stuExerQuestionMapper.insertWithList(list);
	}
}
