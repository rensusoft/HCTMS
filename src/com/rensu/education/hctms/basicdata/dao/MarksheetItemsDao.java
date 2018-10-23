package com.rensu.education.hctms.basicdata.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.basicdata.bean.MarksheetItems;

import configuration.mapper.MarksheetItemsMapper;

@Repository("marksheetItemsDao")
public class MarksheetItemsDao extends BaseDao<MarksheetItems> {
	
	Logger log = Logger.getLogger(MarksheetItemsDao.class);
	
	@Autowired
	private MarksheetItemsMapper<MarksheetItems> marksheetItemsMapper;
	
	@Override
	public int add(MarksheetItems marksheetItems) {
		return marksheetItemsMapper.add(marksheetItems);
	};
	
	@Override
	public int update(MarksheetItems marksheetItems) {
		return marksheetItemsMapper.update(marksheetItems);
	};
	
	@Override
	public int delete(MarksheetItems marksheetItems) {
		return marksheetItemsMapper.delete(marksheetItems);
	};
	
	@Override
	public MarksheetItems selectOne(int id) {
		return marksheetItemsMapper.selectOne(id);
	};
	
	@Override
	public List<MarksheetItems> selectList(MarksheetItems marksheetItems) {
		return marksheetItemsMapper.selectList(marksheetItems);
	};
	
	@Override
	public List<MarksheetItems> selectPage(RowBounds rowBounds, MarksheetItems marksheetItems) {
		return marksheetItemsMapper.selectPage(rowBounds, marksheetItems);
	};
	
	@Override
	public int selectCount(MarksheetItems marksheetItems) {
		return marksheetItemsMapper.selectCount(marksheetItems);
	};
	
	@Override
	public int getSequence() {
		return marksheetItemsMapper.getSequence();
	}
	
}
