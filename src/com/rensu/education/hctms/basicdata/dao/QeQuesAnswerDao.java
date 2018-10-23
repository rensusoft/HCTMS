package com.rensu.education.hctms.basicdata.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.basicdata.bean.QeQuesAnswer;

import configuration.mapper.QeQuesAnswerMapper;

@Repository("qeQuesAnswerDao")
public class QeQuesAnswerDao extends BaseDao<QeQuesAnswer> {
	
	Logger log = Logger.getLogger(QeQuesAnswerDao.class);
	
	@Autowired
	private QeQuesAnswerMapper<QeQuesAnswer> qeQuesAnswerMapper;
	
	@Override
	public int add(QeQuesAnswer qeQuesAnswer) {
		return qeQuesAnswerMapper.add(qeQuesAnswer);
	};
	
	@Override
	public int update(QeQuesAnswer qeQuesAnswer) {
		return qeQuesAnswerMapper.update(qeQuesAnswer);
	};
	
	@Override
	public int delete(QeQuesAnswer qeQuesAnswer) {
		return qeQuesAnswerMapper.delete(qeQuesAnswer);
	};
	
	@Override
	public QeQuesAnswer selectOne(int id) {
		return qeQuesAnswerMapper.selectOne(id);
	};
	
	@Override
	public List<QeQuesAnswer> selectList(QeQuesAnswer qeQuesAnswer) {
		return qeQuesAnswerMapper.selectList(qeQuesAnswer);
	};
	
	@Override
	public List<QeQuesAnswer> selectPage(RowBounds rowBounds, QeQuesAnswer qeQuesAnswer) {
		return qeQuesAnswerMapper.selectPage(rowBounds, qeQuesAnswer);
	};
	
	@Override
	public int selectCount(QeQuesAnswer qeQuesAnswer) {
		return qeQuesAnswerMapper.selectCount(qeQuesAnswer);
	};
	
	@Override
	public int getSequence() {
		return qeQuesAnswerMapper.getSequence();
	}
	
	public int insertWithList(List<QeQuesAnswer> list){
		return qeQuesAnswerMapper.insertWithList(list);
	}
}
