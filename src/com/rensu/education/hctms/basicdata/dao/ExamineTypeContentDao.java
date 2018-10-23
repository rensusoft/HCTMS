package com.rensu.education.hctms.basicdata.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.basicdata.bean.ExamineTypeContent;

import configuration.mapper.ExamineTypeContentMapper;

@Repository("examineTypeContentDao")
public class ExamineTypeContentDao extends BaseDao<ExamineTypeContent> {
	
	Logger log = Logger.getLogger(ExamineTypeContentDao.class);
	
	@Autowired
	private ExamineTypeContentMapper<ExamineTypeContent> examineTypeContentMapper;
	
	@Override
	public int add(ExamineTypeContent examineTypeContent) {
		return examineTypeContentMapper.add(examineTypeContent);
	};
	
	@Override
	public int update(ExamineTypeContent examineTypeContent) {
		return examineTypeContentMapper.update(examineTypeContent);
	};
	
	@Override
	public int delete(ExamineTypeContent examineTypeContent) {
		return examineTypeContentMapper.delete(examineTypeContent);
	};
	
	@Override
	public ExamineTypeContent selectOne(int id) {
		return examineTypeContentMapper.selectOne(id);
	};
	
	@Override
	public List<ExamineTypeContent> selectList(ExamineTypeContent examineTypeContent) {
		return examineTypeContentMapper.selectList(examineTypeContent);
	};
	
	@Override
	public List<ExamineTypeContent> selectPage(RowBounds rowBounds, ExamineTypeContent examineTypeContent) {
		return examineTypeContentMapper.selectPage(rowBounds, examineTypeContent);
	};
	
	@Override
	public int selectCount(ExamineTypeContent examineTypeContent) {
		return examineTypeContentMapper.selectCount(examineTypeContent);
	};
	
	@Override
	public int getSequence() {
		return examineTypeContentMapper.getSequence();
	}
	
}
