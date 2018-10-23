package com.rensu.education.hctms.basicdata.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.basicdata.bean.TfcDeptformRela;

import configuration.mapper.TfcDeptformRelaMapper;

@Repository("tfcDeptformRelaDao")
public class TfcDeptformRelaDao extends BaseDao<TfcDeptformRela> {
	
	Logger log = Logger.getLogger(TfcDeptformRelaDao.class);
	
	@Autowired
	private TfcDeptformRelaMapper<TfcDeptformRela> tfcDeptformRelaMapper;
	
	@Override
	public int add(TfcDeptformRela tfcDeptformRela) {
		return tfcDeptformRelaMapper.add(tfcDeptformRela);
	};
	
	@Override
	public int update(TfcDeptformRela tfcDeptformRela) {
		return tfcDeptformRelaMapper.update(tfcDeptformRela);
	};
	
	@Override
	public int delete(TfcDeptformRela tfcDeptformRela) {
		return tfcDeptformRelaMapper.delete(tfcDeptformRela);
	};
	
	@Override
	public TfcDeptformRela selectOne(int id) {
		return tfcDeptformRelaMapper.selectOne(id);
	};
	
	@Override
	public List<TfcDeptformRela> selectList(TfcDeptformRela tfcDeptformRela) {
		return tfcDeptformRelaMapper.selectList(tfcDeptformRela);
	};
	
	@Override
	public List<TfcDeptformRela> selectPage(RowBounds rowBounds, TfcDeptformRela tfcDeptformRela) {
		return tfcDeptformRelaMapper.selectPage(rowBounds, tfcDeptformRela);
	};
	
	@Override
	public int selectCount(TfcDeptformRela tfcDeptformRela) {
		return tfcDeptformRelaMapper.selectCount(tfcDeptformRela);
	};
	
	@Override
	public int getSequence() {
		return tfcDeptformRelaMapper.getSequence();
	}


	public List<TfcDeptformRela> getTfcList() {
		return tfcDeptformRelaMapper.getTfcList();
	}

	public TfcDeptformRela selectOneTfc(TfcDeptformRela tfcDeptformRela) {
		return tfcDeptformRelaMapper.selectOneTfc(tfcDeptformRela);
	}
	
}
