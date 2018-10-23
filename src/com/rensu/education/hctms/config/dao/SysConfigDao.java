package com.rensu.education.hctms.config.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.config.bean.SysConfig;

import configuration.mapper.SysConfigMapper;

@Repository("sysConfigDao")
public class SysConfigDao extends BaseDao<SysConfig> {
	
	Logger log = Logger.getLogger(SysConfigDao.class);
	
	@Autowired
	private SysConfigMapper<SysConfig> sysConfigMapper;
	
	@Override
	public int add(SysConfig sysConfig) {
		return sysConfigMapper.add(sysConfig);
	};
	
	@Override
	public int update(SysConfig sysConfig) {
		return sysConfigMapper.update(sysConfig);
	};
	
	@Override
	public int delete(SysConfig sysConfig) {
		return sysConfigMapper.delete(sysConfig);
	};
	
	@Override
	public SysConfig selectOne(int id) {
		return sysConfigMapper.selectOne(id);
	};
	
	@Override
	public List<SysConfig> selectList(SysConfig sysConfig) {
		return sysConfigMapper.selectList(sysConfig);
	};
	
	@Override
	public List<SysConfig> selectPage(RowBounds rowBounds, SysConfig sysConfig) {
		return sysConfigMapper.selectPage(rowBounds, sysConfig);
	};
	
	@Override
	public int selectCount(SysConfig sysConfig) {
		return sysConfigMapper.selectCount(sysConfig);
	};
	
	@Override
	public int getSequence() {
		return sysConfigMapper.getSequence();
	}
	
	/***
	 * 查询系统开关配置
	 * @param sysConfig
	 * @return List<SysConfig>
	 * @author guocc
	 * @date 2017年4月5日
	 */
	public List<SysConfig> selectSysConfig(SysConfig sysConfig) {
		return sysConfigMapper.selectSysConfig(sysConfig);
	}
	
}
