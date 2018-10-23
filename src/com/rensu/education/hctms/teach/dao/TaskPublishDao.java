package com.rensu.education.hctms.teach.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.teach.bean.TaskPublish;
import com.rensu.education.hctms.teach.bean.TrainPlan;

import configuration.mapper.TaskPublishMapper;

@Repository("taskPublishDao")
public class TaskPublishDao extends BaseDao<TaskPublish> {
	
	Logger log = Logger.getLogger(TaskPublishDao.class);
	
	@Autowired
	private TaskPublishMapper<TaskPublish> taskPublishMapper;
	
	@Override
	public int add(TaskPublish taskPublish) {
		return taskPublishMapper.add(taskPublish);
	};
	
	@Override
	public int update(TaskPublish taskPublish) {
		return taskPublishMapper.update(taskPublish);
	};
	
	@Override
	public int delete(TaskPublish taskPublish) {
		return taskPublishMapper.delete(taskPublish);
	};
	
	@Override
	public TaskPublish selectOne(int id) {
		return taskPublishMapper.selectOne(id);
	};
	
	@Override
	public List<TaskPublish> selectList(TaskPublish taskPublish) {
		return taskPublishMapper.selectList(taskPublish);
	};
	
	@Override
	public List<TaskPublish> selectPage(RowBounds rowBounds, TaskPublish taskPublish) {
		return taskPublishMapper.selectPage(rowBounds, taskPublish);
	};
	
	@Override
	public int selectCount(TaskPublish taskPublish) {
		return taskPublishMapper.selectCount(taskPublish);
	};
	
	@Override
	public int getSequence() {
		return taskPublishMapper.getSequence();
	}

	public List<TaskPublish> selectPageAll(RowBounds rowBounds, TaskPublish taskPublish) {
		return taskPublishMapper.selectPageAll(rowBounds, taskPublish);
	}

	public TaskPublish selectTask(Integer id) {
		// TODO Auto-generated method stub
		return taskPublishMapper.selectTask(id);
	};
	

	
}
