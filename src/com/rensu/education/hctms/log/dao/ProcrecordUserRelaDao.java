package com.rensu.education.hctms.log.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.log.bean.ProcrecordUserRela;

import configuration.mapper.ProcrecordUserRelaMapper;

@Repository("procrecordUserRelaDao")
public class ProcrecordUserRelaDao extends BaseDao<ProcrecordUserRela> {
	
	Logger log = Logger.getLogger(ProcrecordUserRelaDao.class);
	
	@Autowired
	private ProcrecordUserRelaMapper<ProcrecordUserRela> procrecordUserRelaMapper;
	
	@Override
	public int add(ProcrecordUserRela procrecordUserRela) {
		return procrecordUserRelaMapper.add(procrecordUserRela);
	};
	
	@Override
	public int update(ProcrecordUserRela procrecordUserRela) {
		return procrecordUserRelaMapper.update(procrecordUserRela);
	};
	
	@Override
	public int delete(ProcrecordUserRela procrecordUserRela) {
		return procrecordUserRelaMapper.delete(procrecordUserRela);
	};
	
	@Override
	public ProcrecordUserRela selectOne(int id) {
		return procrecordUserRelaMapper.selectOne(id);
	};
	
	@Override
	public List<ProcrecordUserRela> selectList(ProcrecordUserRela procrecordUserRela) {
		return procrecordUserRelaMapper.selectList(procrecordUserRela);
	};
	
	@Override
	public List<ProcrecordUserRela> selectPage(RowBounds rowBounds, ProcrecordUserRela procrecordUserRela) {
		return procrecordUserRelaMapper.selectPage(rowBounds, procrecordUserRela);
	};
	
	@Override
	public int selectCount(ProcrecordUserRela procrecordUserRela) {
		return procrecordUserRelaMapper.selectCount(procrecordUserRela);
	};
	
	@Override
	public int getSequence() {
		return procrecordUserRelaMapper.getSequence();
	}
	
}
