package com.rensu.education.hctms.basicdata.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.basicdata.bean.MarksheetDetail;

import configuration.mapper.MarksheetDetailMapper;

@Repository("marksheetDetailDao")
public class MarksheetDetailDao extends BaseDao<MarksheetDetail> {
	
	Logger log = Logger.getLogger(MarksheetDetailDao.class);
	
	@Autowired
	private MarksheetDetailMapper<MarksheetDetail> marksheetDetailMapper;
	
	@Override
	public int add(MarksheetDetail marksheetDetail) {
		return marksheetDetailMapper.add(marksheetDetail);
	};
	
	@Override
	public int update(MarksheetDetail marksheetDetail) {
		return marksheetDetailMapper.update(marksheetDetail);
	};
	
	@Override
	public int delete(MarksheetDetail marksheetDetail) {
		return marksheetDetailMapper.delete(marksheetDetail);
	};
	
	@Override
	public MarksheetDetail selectOne(int id) {
		return marksheetDetailMapper.selectOne(id);
	};
	
	@Override
	public List<MarksheetDetail> selectList(MarksheetDetail marksheetDetail) {
		return marksheetDetailMapper.selectList(marksheetDetail);
	};
	
	@Override
	public List<MarksheetDetail> selectPage(RowBounds rowBounds, MarksheetDetail marksheetDetail) {
		return marksheetDetailMapper.selectPage(rowBounds, marksheetDetail);
	};
	
	@Override
	public int selectCount(MarksheetDetail marksheetDetail) {
		return marksheetDetailMapper.selectCount(marksheetDetail);
	};
	
	@Override
	public int getSequence() {
		return marksheetDetailMapper.getSequence();
	}
	
	public int insertWithList(List<MarksheetDetail> list){
		return marksheetDetailMapper.insertWithList(list);
	}
	
}
