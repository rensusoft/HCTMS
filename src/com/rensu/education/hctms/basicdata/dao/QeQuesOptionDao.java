package com.rensu.education.hctms.basicdata.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.basicdata.bean.QeQuesOption;

import configuration.mapper.QeQuesOptionMapper;

@Repository("qeQuesOptionDao")
public class QeQuesOptionDao extends BaseDao<QeQuesOption> {
	
	Logger log = Logger.getLogger(QeQuesOptionDao.class);
	
	@Autowired
	private QeQuesOptionMapper<QeQuesOption> qeQuesOptionMapper;
	
	@Override
	public int add(QeQuesOption qeQuesOption) {
		return qeQuesOptionMapper.add(qeQuesOption);
	};
	
	@Override
	public int update(QeQuesOption qeQuesOption) {
		return qeQuesOptionMapper.update(qeQuesOption);
	};
	
	@Override
	public int delete(QeQuesOption qeQuesOption) {
		return qeQuesOptionMapper.delete(qeQuesOption);
	};
	
	@Override
	public QeQuesOption selectOne(int id) {
		return qeQuesOptionMapper.selectOne(id);
	};
	
	@Override
	public List<QeQuesOption> selectList(QeQuesOption qeQuesOption) {
		return qeQuesOptionMapper.selectList(qeQuesOption);
	};
	
	@Override
	public List<QeQuesOption> selectPage(RowBounds rowBounds, QeQuesOption qeQuesOption) {
		return qeQuesOptionMapper.selectPage(rowBounds, qeQuesOption);
	};
	
	@Override
	public int selectCount(QeQuesOption qeQuesOption) {
		return qeQuesOptionMapper.selectCount(qeQuesOption);
	};
	
	@Override
	public int getSequence() {
		return qeQuesOptionMapper.getSequence();
	}
	
	public int insertWithList(List<QeQuesOption> list){
		return qeQuesOptionMapper.insertWithList(list);
	}
}
