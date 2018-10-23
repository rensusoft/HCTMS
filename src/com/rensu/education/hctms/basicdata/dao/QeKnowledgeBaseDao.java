package com.rensu.education.hctms.basicdata.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.basicdata.bean.QeKnowledgeBase;

import configuration.mapper.QeKnowledgeBaseMapper;

@Repository("qeKnowledgeBaseDao")
public class QeKnowledgeBaseDao extends BaseDao<QeKnowledgeBase> {
	
	Logger log = Logger.getLogger(QeKnowledgeBaseDao.class);
	
	@Autowired
	private QeKnowledgeBaseMapper<QeKnowledgeBase> qeKnowledgeBaseMapper;
	
	@Override
	public int add(QeKnowledgeBase qeKnowledgeBase) {
		return qeKnowledgeBaseMapper.add(qeKnowledgeBase);
	};
	
	@Override
	public int update(QeKnowledgeBase qeKnowledgeBase) {
		return qeKnowledgeBaseMapper.update(qeKnowledgeBase);
	};
	
	@Override
	public int delete(QeKnowledgeBase qeKnowledgeBase) {
		return qeKnowledgeBaseMapper.delete(qeKnowledgeBase);
	};
	
	@Override
	public QeKnowledgeBase selectOne(int id) {
		return qeKnowledgeBaseMapper.selectOne(id);
	};
	
	@Override
	public List<QeKnowledgeBase> selectList(QeKnowledgeBase qeKnowledgeBase) {
		return qeKnowledgeBaseMapper.selectList(qeKnowledgeBase);
	};
	
	@Override
	public List<QeKnowledgeBase> selectPage(RowBounds rowBounds, QeKnowledgeBase qeKnowledgeBase) {
		return qeKnowledgeBaseMapper.selectPage(rowBounds, qeKnowledgeBase);
	};
	
	@Override
	public int selectCount(QeKnowledgeBase qeKnowledgeBase) {
		return qeKnowledgeBaseMapper.selectCount(qeKnowledgeBase);
	};
	
	@Override
	public int getSequence() {
		return qeKnowledgeBaseMapper.getSequence();
	}
	
}
