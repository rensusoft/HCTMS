package com.rensu.education.hctms.basicdata.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.basicdata.bean.QeQuestion;

import configuration.mapper.QeQuestionMapper;

@Repository("qeQuestionDao")
public class QeQuestionDao extends BaseDao<QeQuestion> {
	
	Logger log = Logger.getLogger(QeQuestionDao.class);
	
	@Autowired
	private QeQuestionMapper<QeQuestion> qeQuestionMapper;
	
	@Override
	public int add(QeQuestion qeQuestion) {
		return qeQuestionMapper.add(qeQuestion);
	};
	
	@Override
	public int update(QeQuestion qeQuestion) {
		return qeQuestionMapper.update(qeQuestion);
	};
	
	@Override
	public int delete(QeQuestion qeQuestion) {
		return qeQuestionMapper.delete(qeQuestion);
	};
	
	@Override
	public QeQuestion selectOne(int id) {
		return qeQuestionMapper.selectOne(id);
	};
	
	@Override
	public List<QeQuestion> selectList(QeQuestion qeQuestion) {
		return qeQuestionMapper.selectList(qeQuestion);
	};
	
	@Override
	public List<QeQuestion> selectPage(RowBounds rowBounds, QeQuestion qeQuestion) {
		return qeQuestionMapper.selectPage(rowBounds, qeQuestion);
	};
	
	@Override
	public int selectCount(QeQuestion qeQuestion) {
		return qeQuestionMapper.selectCount(qeQuestion);
	};
	
	@Override
	public int getSequence() {
		return qeQuestionMapper.getSequence();
	}
	
	public int insertWithList(List<QeQuestion> list){
		return qeQuestionMapper.insertWithList(list);
	}
}
