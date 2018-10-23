package com.rensu.education.hctms.config.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.config.bean.TrainPlanConfig;
import com.rensu.education.hctms.userauth.bean.OrgaInfo;

import configuration.mapper.TrainPlanConfigMapper;

@Repository("trainPlanConfigDao")
public class TrainPlanConfigDao extends BaseDao<TrainPlanConfig> {
	
	Logger log = Logger.getLogger(TrainPlanConfigDao.class);
	
	@Autowired
	private TrainPlanConfigMapper<TrainPlanConfig> trainPlanConfigMapper;
	
	@Override
	public int add(TrainPlanConfig trainPlanConfig) {
		return trainPlanConfigMapper.add(trainPlanConfig);
	};
	
	@Override
	public int update(TrainPlanConfig trainPlanConfig) {
		return trainPlanConfigMapper.update(trainPlanConfig);
	};
	
	@Override
	public int delete(TrainPlanConfig trainPlanConfig) {
		return trainPlanConfigMapper.delete(trainPlanConfig);
	};
	
	@Override
	public TrainPlanConfig selectOne(int id) {
		return trainPlanConfigMapper.selectOne(id);
	};
	
	@Override
	public List<TrainPlanConfig> selectList(TrainPlanConfig trainPlanConfig) {
		return trainPlanConfigMapper.selectList(trainPlanConfig);
	};
	
	@Override
	public List<TrainPlanConfig> selectPage(RowBounds rowBounds, TrainPlanConfig trainPlanConfig) {
		return trainPlanConfigMapper.selectPage(rowBounds, trainPlanConfig);
	};
	
	@Override
	public int selectCount(TrainPlanConfig trainPlanConfig) {
		return trainPlanConfigMapper.selectCount(trainPlanConfig);
	};
	
	@Override
	public int getSequence() {
		return trainPlanConfigMapper.getSequence();
	}

	public List<TrainPlanConfig> findRotaryDepartment(
			TrainPlanConfig trainPlanConfig) {
		return trainPlanConfigMapper.findRotaryDepartment(trainPlanConfig);
	}
	/**
	 * 根据tsc_id修改
	 * @param trainPlanConfig
	 *@author huq
	 *@date 2017年2月10日
	 */
	public void updatebyTsc_id(TrainPlanConfig trainPlanConfig) {
		trainPlanConfigMapper.updatebyTsc_id(trainPlanConfig);
	}

	public int insertMany(List<TrainPlanConfig> list) {
		return trainPlanConfigMapper.insertMany(list);
	}

	public List<TrainPlanConfig> selectOrgaInfo(TrainPlanConfig trainPlanConfig) {
		return trainPlanConfigMapper.selectOrgaInfo(trainPlanConfig);
	}

	public TrainPlanConfig getDeptId(TrainPlanConfig config) {
		return trainPlanConfigMapper.getDeptId(config);
	}

	public List<TrainPlanConfig> selectOrgaByTscId(TrainPlanConfig trainPlanConfig) {
		return trainPlanConfigMapper.selectOrgaByTscId(trainPlanConfig);
	}	
}
