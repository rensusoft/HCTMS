package com.rensu.education.hctms.log.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.log.bean.ProcessRecord;

import configuration.mapper.ProcessRecordMapper;

@Repository("processRecordDao")
public class ProcessRecordDao extends BaseDao<ProcessRecord> {
	
	Logger log = Logger.getLogger(ProcessRecordDao.class);
	
	@Autowired
	private ProcessRecordMapper<ProcessRecord> processRecordMapper;
	
	@Override
	public int add(ProcessRecord processRecord) {
		return processRecordMapper.add(processRecord);
	};
	
	@Override
	public int update(ProcessRecord processRecord) {
		return processRecordMapper.update(processRecord);
	};
	
	@Override
	public int delete(ProcessRecord processRecord) {
		return processRecordMapper.delete(processRecord);
	};
	
	@Override
	public ProcessRecord selectOne(int id) {
		return processRecordMapper.selectOne(id);
	};
	
	@Override
	public List<ProcessRecord> selectList(ProcessRecord processRecord) {
		return processRecordMapper.selectList(processRecord);
	};
	
	@Override
	public List<ProcessRecord> selectPage(RowBounds rowBounds, ProcessRecord processRecord) {
		return processRecordMapper.selectPage(rowBounds, processRecord);
	};
	
	@Override
	public int selectCount(ProcessRecord processRecord) {
		return processRecordMapper.selectCount(processRecord);
	};
	
	@Override
	public int getSequence() {
		return processRecordMapper.getSequence();
	}

	public List<ProcessRecord> selectProcessInfo(ProcessRecord processRecord) {
		return processRecordMapper.selectProcessInfo(processRecord);
	}
	
	public List<ProcessRecord> selectListByRelatedId(ProcessRecord processRecord) {
		return processRecordMapper.selectListByRelatedId(processRecord);
	};
}
