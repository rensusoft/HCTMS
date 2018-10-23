package com.rensu.education.hctms.basicdata.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.basicdata.bean.MarksheetOrgaRela;

import configuration.mapper.MarksheetOrgaRelaMapper;

@Repository("marksheetOrgaRelaDao")
public class MarksheetOrgaRelaDao extends BaseDao<MarksheetOrgaRela> {
	
	Logger log = Logger.getLogger(MarksheetOrgaRelaDao.class);
	
	@Autowired
	private MarksheetOrgaRelaMapper<MarksheetOrgaRela> marksheetOrgaRelaMapper;
	
	@Override
	public int add(MarksheetOrgaRela marksheetOrgaRela) {
		return marksheetOrgaRelaMapper.add(marksheetOrgaRela);
	};
	
	@Override
	public int update(MarksheetOrgaRela marksheetOrgaRela) {
		return marksheetOrgaRelaMapper.update(marksheetOrgaRela);
	};
	
	@Override
	public int delete(MarksheetOrgaRela marksheetOrgaRela) {
		return marksheetOrgaRelaMapper.delete(marksheetOrgaRela);
	};
	
	@Override
	public MarksheetOrgaRela selectOne(int id) {
		return marksheetOrgaRelaMapper.selectOne(id);
	};
	
	@Override
	public List<MarksheetOrgaRela> selectList(MarksheetOrgaRela marksheetOrgaRela) {
		return marksheetOrgaRelaMapper.selectList(marksheetOrgaRela);
	};
	
	@Override
	public List<MarksheetOrgaRela> selectPage(RowBounds rowBounds, MarksheetOrgaRela marksheetOrgaRela) {
		return marksheetOrgaRelaMapper.selectPage(rowBounds, marksheetOrgaRela);
	};
	
	@Override
	public int selectCount(MarksheetOrgaRela marksheetOrgaRela) {
		return marksheetOrgaRelaMapper.selectCount(marksheetOrgaRela);
	};
	
	@Override
	public int getSequence() {
		return marksheetOrgaRelaMapper.getSequence();
	}
	
}
