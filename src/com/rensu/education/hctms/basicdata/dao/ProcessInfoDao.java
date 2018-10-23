package com.rensu.education.hctms.basicdata.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.basicdata.bean.ProcessInfo;

import configuration.mapper.ProcessInfoMapper;

@Repository("processInfoDao")
public class ProcessInfoDao extends BaseDao<ProcessInfo> {
	
	Logger log = Logger.getLogger(ProcessInfoDao.class);
	
	@Autowired
	private ProcessInfoMapper<ProcessInfo> processInfoMapper;
	
	@Override
	public int add(ProcessInfo processInfo) {
		return processInfoMapper.add(processInfo);
	};
	
	@Override
	public int update(ProcessInfo processInfo) {
		return processInfoMapper.update(processInfo);
	};
	
	@Override
	public int delete(ProcessInfo processInfo) {
		return processInfoMapper.delete(processInfo);
	};
	
	@Override
	public ProcessInfo selectOne(int id) {
		return processInfoMapper.selectOne(id);
	};
	
	@Override
	public List<ProcessInfo> selectList(ProcessInfo processInfo) {
		return processInfoMapper.selectList(processInfo);
	};
	
	@Override
	public List<ProcessInfo> selectPage(RowBounds rowBounds, ProcessInfo processInfo) {
		return processInfoMapper.selectPage(rowBounds, processInfo);
	};
	
	@Override
	public int selectCount(ProcessInfo processInfo) {
		return processInfoMapper.selectCount(processInfo);
	};
	
	@Override
	public int getSequence() {
		return processInfoMapper.getSequence();
	}

	public List<ProcessInfo> selectByNum(int vacate_date_num) {
		return processInfoMapper.selectByNum(vacate_date_num);
	}
	
}
