package com.rensu.education.hctms.config.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.config.bean.TrainSchemeConfig;

import configuration.mapper.TrainSchemeConfigMapper;

@Repository("trainSchemeConfigDao")
public class TrainSchemeConfigDao extends BaseDao<TrainSchemeConfig> {
	
	Logger log = Logger.getLogger(TrainSchemeConfigDao.class);
	
	@Autowired
	private TrainSchemeConfigMapper<TrainSchemeConfig> trainSchemeConfigMapper;
	
	@Override
	public int add(TrainSchemeConfig trainSchemeConfig) {
		return trainSchemeConfigMapper.add(trainSchemeConfig);
	};
	
	@Override
	public int update(TrainSchemeConfig trainSchemeConfig) {
		return trainSchemeConfigMapper.update(trainSchemeConfig);
	};
	
	@Override
	public int delete(TrainSchemeConfig trainSchemeConfig) {
		return trainSchemeConfigMapper.delete(trainSchemeConfig);
	};
	
	@Override
	public TrainSchemeConfig selectOne(int id) {
		return trainSchemeConfigMapper.selectOne(id);
	};
	
	@Override
	public List<TrainSchemeConfig> selectList(TrainSchemeConfig trainSchemeConfig) {
		return trainSchemeConfigMapper.selectList(trainSchemeConfig);
	};
	
	@Override
	public List<TrainSchemeConfig> selectPage(RowBounds rowBounds, TrainSchemeConfig trainSchemeConfig) {
		return trainSchemeConfigMapper.selectPage(rowBounds, trainSchemeConfig);
	};
	
	@Override
	public int selectCount(TrainSchemeConfig trainSchemeConfig) {
		return trainSchemeConfigMapper.selectCount(trainSchemeConfig);
	};
	
	@Override
	public int getSequence() {
		return trainSchemeConfigMapper.getSequence();
	}

	public List<TrainSchemeConfig> selectStuPage(RowBounds rowBounds,
			TrainSchemeConfig t) {
		return trainSchemeConfigMapper.selectStuPage(rowBounds, t);
	}
	
}
