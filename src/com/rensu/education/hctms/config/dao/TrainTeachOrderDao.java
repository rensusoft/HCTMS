package com.rensu.education.hctms.config.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.config.bean.TrainTeachOrder;
import com.rensu.education.hctms.teach.bean.TrainPlan;

import configuration.mapper.TrainTeachOrderMapper;

@Repository("trainTeachOrderDao")
public class TrainTeachOrderDao extends BaseDao<TrainTeachOrder> {
	
	Logger log = Logger.getLogger(TrainTeachOrderDao.class);
	
	@Autowired
	private TrainTeachOrderMapper<TrainTeachOrder> trainTeachOrderMapper;
	
	@Override
	public int add(TrainTeachOrder trainTeachOrder) {
		return trainTeachOrderMapper.add(trainTeachOrder);
	};
	
	@Override
	public int update(TrainTeachOrder trainTeachOrder) {
		return trainTeachOrderMapper.update(trainTeachOrder);
	};
	
	@Override
	public int delete(TrainTeachOrder trainTeachOrder) {
		return trainTeachOrderMapper.delete(trainTeachOrder);
	};
	
	@Override
	public TrainTeachOrder selectOne(int id) {
		return trainTeachOrderMapper.selectOne(id);
	};
	
	@Override
	public List<TrainTeachOrder> selectList(TrainTeachOrder trainTeachOrder) {
		return trainTeachOrderMapper.selectList(trainTeachOrder);
	};
	
	@Override
	public List<TrainTeachOrder> selectPage(RowBounds rowBounds, TrainTeachOrder trainTeachOrder) {
		return trainTeachOrderMapper.selectPage(rowBounds, trainTeachOrder);
	};
	
	@Override
	public int selectCount(TrainTeachOrder trainTeachOrder) {
		return trainTeachOrderMapper.selectCount(trainTeachOrder);
	};
	
	@Override
	public int getSequence() {
		return trainTeachOrderMapper.getSequence();
	}

	public int deleteByTpc(int tpc_id) {
			return trainTeachOrderMapper.deleteByTpc(tpc_id);
		}

	public List<TrainTeachOrder> outlineExhibition(int tpc_id) {
			return trainTeachOrderMapper.outlineExhibition(tpc_id);
		}

	public List<TrainTeachOrder> selectTTOList(TrainTeachOrder trainTeachOrder) {
		// TODO Auto-generated method stub
		return trainTeachOrderMapper.selectTTOList(trainTeachOrder);
	}
	
	public List<TrainTeachOrder> selectTrainTeachOrderList(TrainPlan trainPlan) {
		return trainTeachOrderMapper.selectTrainTeachOrderList(trainPlan);
	}
}
