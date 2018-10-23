package com.rensu.education.hctms.basicdata.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.basicdata.bean.QeQuesType;

import configuration.mapper.QeQuesTypeMapper;

@Repository("qeQuesTypeDao")
public class QeQuesTypeDao extends BaseDao<QeQuesType> {
	
	Logger log = Logger.getLogger(QeQuesTypeDao.class);
	
	@Autowired
	private QeQuesTypeMapper<QeQuesType> qeQuesTypeMapper;
	
	@Override
	public int add(QeQuesType qeQuesType) {
		return qeQuesTypeMapper.add(qeQuesType);
	};
	
	@Override
	public int update(QeQuesType qeQuesType) {
		return qeQuesTypeMapper.update(qeQuesType);
	};
	
	@Override
	public int delete(QeQuesType qeQuesType) {
		return qeQuesTypeMapper.delete(qeQuesType);
	};
	
	@Override
	public QeQuesType selectOne(int id) {
		return qeQuesTypeMapper.selectOne(id);
	};
	
	@Override
	public List<QeQuesType> selectList(QeQuesType qeQuesType) {
		return qeQuesTypeMapper.selectList(qeQuesType);
	};
	
	@Override
	public List<QeQuesType> selectPage(RowBounds rowBounds, QeQuesType qeQuesType) {
		return qeQuesTypeMapper.selectPage(rowBounds, qeQuesType);
	};
	
	@Override
	public int selectCount(QeQuesType qeQuesType) {
		return qeQuesTypeMapper.selectCount(qeQuesType);
	};
	
	@Override
	public int getSequence() {
		return qeQuesTypeMapper.getSequence();
	}
	
}
