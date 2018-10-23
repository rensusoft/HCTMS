package com.rensu.education.hctms.config.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.config.bean.TrainPlanBefore;
import com.rensu.education.hctms.userauth.bean.StudentInfo;

import configuration.mapper.TrainPlanBeforeMapper;

@Repository("trainPlanBeforeDao")
public class TrainPlanBeforeDao extends BaseDao<TrainPlanBefore> {
	
	Logger log = Logger.getLogger(TrainPlanBeforeDao.class);
	
	@Autowired
	private TrainPlanBeforeMapper<TrainPlanBefore> trainPlanBeforeMapper;
	
	@Override
	public int add(TrainPlanBefore trainPlanBefore) {
		return trainPlanBeforeMapper.add(trainPlanBefore);
	};
	
	@Override
	public int update(TrainPlanBefore trainPlanBefore) {
		return trainPlanBeforeMapper.update(trainPlanBefore);
	};
	
	@Override
	public int delete(TrainPlanBefore trainPlanBefore) {
		return trainPlanBeforeMapper.delete(trainPlanBefore);
	};
	
	@Override
	public TrainPlanBefore selectOne(int id) {
		return trainPlanBeforeMapper.selectOne(id);
	};
	
	@Override
	public List<TrainPlanBefore> selectList(TrainPlanBefore trainPlanBefore) {
		return trainPlanBeforeMapper.selectList(trainPlanBefore);
	};
	
	public List<TrainPlanBefore> selectTPBList(TrainPlanBefore trainPlanBefore) {
	    return trainPlanBeforeMapper.selectTPBList(trainPlanBefore);
	};
	
	@Override
	public List<TrainPlanBefore> selectPage(RowBounds rowBounds, TrainPlanBefore trainPlanBefore) {
		return trainPlanBeforeMapper.selectPage(rowBounds, trainPlanBefore);
	};
	
	@Override
	public int selectCount(TrainPlanBefore trainPlanBefore) {
		return trainPlanBeforeMapper.selectCount(trainPlanBefore);
	};
	
	@Override
	public int getSequence() {
		return trainPlanBeforeMapper.getSequence();
	}
	
	public int selectTrainCount(TrainPlanBefore trainPlanBefore) {
		return trainPlanBeforeMapper.selectTrainCount(trainPlanBefore);
	};
	
	public int deleteList(TrainPlanBefore trainPlanBefore) {
		return trainPlanBeforeMapper.deleteList(trainPlanBefore);
	};
	
	public int deleteTPBList(TrainPlanBefore trainPlanBefore) {
	    return trainPlanBeforeMapper.deleteTPBList(trainPlanBefore);
	};
	
	public TrainPlanBefore getTscIdFromTPBefore(StudentInfo studentInfo) {
        return trainPlanBeforeMapper.getTscIdFromTPBefore(studentInfo);
    }

	public int insertWithList(List<TrainPlanBefore> list) {
		return trainPlanBeforeMapper.insertWithList(list);
	}
}
