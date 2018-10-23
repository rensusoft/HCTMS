package com.rensu.education.hctms.basicdata.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.basicdata.bean.TfcDeptformRela;
import com.rensu.education.hctms.basicdata.bean.TfcStutypeformRela;

import configuration.mapper.TfcStutypeformRelaMapper;

@Repository("tfcStutypeformRelaDao")
public class TfcStutypeformRelaDao extends BaseDao<TfcStutypeformRela> {
	
	Logger log = Logger.getLogger(TfcStutypeformRelaDao.class);
	
	@Autowired
	private TfcStutypeformRelaMapper<TfcStutypeformRela> tfcStutypeformRelaMapper;
	
	@Override
	public int add(TfcStutypeformRela tfcStutypeformRela) {
		return tfcStutypeformRelaMapper.add(tfcStutypeformRela);
	};
	
	@Override
	public int update(TfcStutypeformRela tfcStutypeformRela) {
		return tfcStutypeformRelaMapper.update(tfcStutypeformRela);
	};
	
	@Override
	public int delete(TfcStutypeformRela tfcStutypeformRela) {
		return tfcStutypeformRelaMapper.delete(tfcStutypeformRela);
	};
	
	@Override
	public TfcStutypeformRela selectOne(int id) {
		return tfcStutypeformRelaMapper.selectOne(id);
	};
	
	@Override
	public List<TfcStutypeformRela> selectList(TfcStutypeformRela tfcStutypeformRela) {
		return tfcStutypeformRelaMapper.selectList(tfcStutypeformRela);
	};
	
	@Override
	public List<TfcStutypeformRela> selectPage(RowBounds rowBounds, TfcStutypeformRela tfcStutypeformRela) {
		return tfcStutypeformRelaMapper.selectPage(rowBounds, tfcStutypeformRela);
	};
	
	@Override
	public int selectCount(TfcStutypeformRela tfcStutypeformRela) {
		return tfcStutypeformRelaMapper.selectCount(tfcStutypeformRela);
	};
	
	@Override
	public int getSequence() {
		return tfcStutypeformRelaMapper.getSequence();
	}

	public List<TfcStutypeformRela> getFormList(
			TfcStutypeformRela tfcStutypeformRela) {
		
		return tfcStutypeformRelaMapper.getFormList(tfcStutypeformRela);
	}
	
	public List<TfcStutypeformRela> getTfcList(
			TfcStutypeformRela tfcStutypeformRela) {
		return tfcStutypeformRelaMapper.getTfcList(tfcStutypeformRela);
	}
	
	public int deleteAllByCon(TfcStutypeformRela tfcStutypeformRela) {
		return tfcStutypeformRelaMapper.deleteAllByCon(tfcStutypeformRela);
	}
}
