package com.rensu.education.hctms.basicdata.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.basicdata.bean.RprFormRela;

import configuration.mapper.RprFormRelaMapper;

@Repository("rprFormRelaDao")
public class RprFormRelaDao extends BaseDao<RprFormRela> {
	
	Logger log = Logger.getLogger(RprFormRelaDao.class);
	
	@Autowired
	private RprFormRelaMapper<RprFormRela> rprFormRelaMapper;
	
	@Override
	public int add(RprFormRela rprFormRela) {
		return rprFormRelaMapper.add(rprFormRela);
	};
	
	@Override
	public int update(RprFormRela rprFormRela) {
		return rprFormRelaMapper.update(rprFormRela);
	};
	
	@Override
	public int delete(RprFormRela rprFormRela) {
		return rprFormRelaMapper.delete(rprFormRela);
	};
	
	@Override
	public RprFormRela selectOne(int id) {
		return rprFormRelaMapper.selectOne(id);
	};
	
	@Override
	public List<RprFormRela> selectList(RprFormRela rprFormRela) {
		return rprFormRelaMapper.selectList(rprFormRela);
	};
	
	@Override
	public List<RprFormRela> selectPage(RowBounds rowBounds, RprFormRela rprFormRela) {
		return rprFormRelaMapper.selectPage(rowBounds, rprFormRela);
	};
	
	@Override
	public int selectCount(RprFormRela rprFormRela) {
		return rprFormRelaMapper.selectCount(rprFormRela);
	};
	
	@Override
	public int getSequence() {
		return rprFormRelaMapper.getSequence();
	}

	public int deleteByRpr_id(RprFormRela rprFormRela) {
		return rprFormRelaMapper.deleteByRpr_id(rprFormRela);
	}
	
}
