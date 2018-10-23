package com.rensu.education.hctms.basicdata.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.basicdata.bean.MarksheetSub;

import configuration.mapper.MarksheetSubMapper;

@Repository("marksheetSubDao")
public class MarksheetSubDao extends BaseDao<MarksheetSub> {
	
	Logger log = Logger.getLogger(MarksheetSubDao.class);
	
	@Autowired
	private MarksheetSubMapper<MarksheetSub> marksheetSubMapper;
	
	@Override
	public int add(MarksheetSub marksheetSub) {
		return marksheetSubMapper.add(marksheetSub);
	};
	
	@Override
	public int update(MarksheetSub marksheetSub) {
		return marksheetSubMapper.update(marksheetSub);
	};
	
	@Override
	public int delete(MarksheetSub marksheetSub) {
		return marksheetSubMapper.delete(marksheetSub);
	};
	
	@Override
	public MarksheetSub selectOne(int id) {
		return marksheetSubMapper.selectOne(id);
	};
	
	@Override
	public List<MarksheetSub> selectList(MarksheetSub marksheetSub) {
		return marksheetSubMapper.selectList(marksheetSub);
	};
	
	@Override
	public List<MarksheetSub> selectPage(RowBounds rowBounds, MarksheetSub marksheetSub) {
		return marksheetSubMapper.selectPage(rowBounds, marksheetSub);
	};
	
	@Override
	public int selectCount(MarksheetSub marksheetSub) {
		return marksheetSubMapper.selectCount(marksheetSub);
	};
	
	@Override
	public int getSequence() {
		return marksheetSubMapper.getSequence();
	}
	
	public int insertWithList(List<MarksheetSub> list) {
		return marksheetSubMapper.insertWithList(list);
	}
	
	public int updateList(MarksheetSub marksheetSub) {
		return marksheetSubMapper.updateList(marksheetSub);
	}
}
