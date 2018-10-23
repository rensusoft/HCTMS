package com.rensu.education.hctms.basicdata.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.basicdata.bean.MarksheetMain;

import configuration.mapper.MarksheetMainMapper;

@Repository("marksheetMainDao")
public class MarksheetMainDao extends BaseDao<MarksheetMain> {
	
	Logger log = Logger.getLogger(MarksheetMainDao.class);
	
	@Autowired
	private MarksheetMainMapper<MarksheetMain> marksheetMainMapper;
	
	@Override
	public int add(MarksheetMain marksheetMain) {
		return marksheetMainMapper.add(marksheetMain);
	};
	
	@Override
	public int update(MarksheetMain marksheetMain) {
		return marksheetMainMapper.update(marksheetMain);
	};
	
	@Override
	public int delete(MarksheetMain marksheetMain) {
		return marksheetMainMapper.delete(marksheetMain);
	};
	
	@Override
	public MarksheetMain selectOne(int id) {
		return marksheetMainMapper.selectOne(id);
	};
	
	@Override
	public List<MarksheetMain> selectList(MarksheetMain marksheetMain) {
		return marksheetMainMapper.selectList(marksheetMain);
	};
	
	@Override
	public List<MarksheetMain> selectPage(RowBounds rowBounds, MarksheetMain marksheetMain) {
		return marksheetMainMapper.selectPage(rowBounds, marksheetMain);
	};
	
	@Override
	public int selectCount(MarksheetMain marksheetMain) {
		return marksheetMainMapper.selectCount(marksheetMain);
	};
	
	@Override
	public int getSequence() {
		return marksheetMainMapper.getSequence();
	}
	
}
