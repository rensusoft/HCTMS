package com.rensu.education.hctms.teach.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.teach.bean.TaskDeal;

import configuration.mapper.TaskDealMapper;

@Repository("taskDealDao")
public class TaskDealDao extends BaseDao<TaskDeal> {
	
	Logger log = Logger.getLogger(TaskDealDao.class);
	
	@Autowired
	private TaskDealMapper<TaskDeal> taskDealMapper;
	
	@Override
	public int add(TaskDeal taskDeal) {
		return taskDealMapper.add(taskDeal);
	};
	
	@Override
	public int update(TaskDeal taskDeal) {
		return taskDealMapper.update(taskDeal);
	};
	
	@Override
	public int delete(TaskDeal taskDeal) {
		return taskDealMapper.delete(taskDeal);
	};
	
	@Override
	public TaskDeal selectOne(int id) {
		return taskDealMapper.selectOne(id);
	};
	
	@Override
	public List<TaskDeal> selectList(TaskDeal taskDeal) {
		return taskDealMapper.selectList(taskDeal);
	};
	
	@Override
	public List<TaskDeal> selectPage(RowBounds rowBounds, TaskDeal taskDeal) {
		return taskDealMapper.selectPage(rowBounds, taskDeal);
	};
	
	@Override
	public int selectCount(TaskDeal taskDeal) {
		return taskDealMapper.selectCount(taskDeal);
	};
	
	@Override
	public int getSequence() {
		return taskDealMapper.getSequence();
	}

	public List<TaskDeal> selectPageAllStu(RowBounds rowBounds,
			TaskDeal taskDeal) {
		// TODO Auto-generated method stub
		return taskDealMapper.selectPageAllStu(rowBounds, taskDeal);
	}
	
}
