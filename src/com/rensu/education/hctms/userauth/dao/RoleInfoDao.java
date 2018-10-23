package com.rensu.education.hctms.userauth.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.userauth.bean.RoleInfo;

import configuration.mapper.RoleInfoMapper;

@Repository("RoleInfoDao")
public class RoleInfoDao extends BaseDao<RoleInfo> {
	
	Logger log = Logger.getLogger(RoleInfoDao.class);
	
	@Autowired
	private RoleInfoMapper<RoleInfo> RoleInfoMapper;
	
	@Override
	public int add(RoleInfo RoleInfo) {
		return RoleInfoMapper.add(RoleInfo);
	};
	
	@Override
	public int update(RoleInfo RoleInfo) {
		return RoleInfoMapper.update(RoleInfo);
	};
	
	@Override
	public int delete(RoleInfo RoleInfo) {
		return RoleInfoMapper.delete(RoleInfo);
	};
	
	@Override
	public RoleInfo selectOne(int id) {
		return RoleInfoMapper.selectOne(id);
	};
	
	@Override
	public List<RoleInfo> selectList(RoleInfo RoleInfo) {
		return RoleInfoMapper.selectList(RoleInfo);
	};
	
	@Override
	public List<RoleInfo> selectPage(RowBounds rowBounds, RoleInfo RoleInfo) {
		return RoleInfoMapper.selectPage(rowBounds, RoleInfo);
	};
	
	@Override
	public int selectCount(RoleInfo RoleInfo) {
		return RoleInfoMapper.selectCount(RoleInfo);
	};
	
	@Override
	public int getSequence() {
		return RoleInfoMapper.getSequence();
	}
	
	public List<RoleInfo> loginByCodePsd(Map<String,Object> pramMap){
		return RoleInfoMapper.loginByCodePsd(pramMap);
	}

	public List<RoleInfo> selectTeacherList() {
		return RoleInfoMapper.selectTeacherList();
	}
	
}
