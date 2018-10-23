package com.rensu.education.hctms.basicdata.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.basicdata.bean.TeachFormConfig;

import configuration.mapper.TeachFormConfigMapper;

@Repository("teachFormConfigDao")
public class TeachFormConfigDao extends BaseDao<TeachFormConfig> {
	
	Logger log = Logger.getLogger(TeachFormConfigDao.class);
	
	@Autowired
	private TeachFormConfigMapper<TeachFormConfig> teachFormConfigMapper;
	
	@Override
	public int add(TeachFormConfig teachFormConfig) {
		return teachFormConfigMapper.add(teachFormConfig);
	};
	
	@Override
	public int update(TeachFormConfig teachFormConfig) {
		return teachFormConfigMapper.update(teachFormConfig);
	};
	
	@Override
	public int delete(TeachFormConfig teachFormConfig) {
		return teachFormConfigMapper.delete(teachFormConfig);
	};
	
	@Override
	public TeachFormConfig selectOne(int id) {
		return teachFormConfigMapper.selectOne(id);
	};
	
	@Override
	public List<TeachFormConfig> selectList(TeachFormConfig teachFormConfig) {
		return teachFormConfigMapper.selectList(teachFormConfig);
	};
	
	@Override
	public List<TeachFormConfig> selectPage(RowBounds rowBounds, TeachFormConfig teachFormConfig) {
		return teachFormConfigMapper.selectPage(rowBounds, teachFormConfig);
	};
	
	@Override
	public int selectCount(TeachFormConfig teachFormConfig) {
		return teachFormConfigMapper.selectCount(teachFormConfig);
	};
	
	@Override
	public int getSequence() {
		return teachFormConfigMapper.getSequence();
	}
	
}
