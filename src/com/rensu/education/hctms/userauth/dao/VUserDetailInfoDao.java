package com.rensu.education.hctms.userauth.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.userauth.bean.VUserDetailInfo;

import configuration.mapper.VUserDetailInfoMapper;


@Repository("vUserDetailInfoDao")
public class VUserDetailInfoDao extends BaseDao<VUserDetailInfo> {
	
	Logger log = Logger.getLogger(VUserDetailInfoDao.class);
	
	@Autowired
	private VUserDetailInfoMapper<VUserDetailInfo> vUserDetailInfoMapper;
	
	@Override
	public int add(VUserDetailInfo vUserDetailInfo) {
		return vUserDetailInfoMapper.add(vUserDetailInfo);
	};
	
	@Override
	public int update(VUserDetailInfo vUserDetailInfo) {
		return vUserDetailInfoMapper.update(vUserDetailInfo);
	};
	
	@Override
	public int delete(VUserDetailInfo vUserDetailInfo) {
		return vUserDetailInfoMapper.delete(vUserDetailInfo);
	};
	
	@Override
	public VUserDetailInfo selectOne(int id) {
		return vUserDetailInfoMapper.selectOne(id);
	};
	
	@Override
	public List<VUserDetailInfo> selectList(VUserDetailInfo vUserDetailInfo) {
		return vUserDetailInfoMapper.selectList(vUserDetailInfo);
	};
	
	@Override
	public List<VUserDetailInfo> selectPage(RowBounds rowBounds, VUserDetailInfo vUserDetailInfo) {
		return vUserDetailInfoMapper.selectPage(rowBounds, vUserDetailInfo);
	};
	
	@Override
	public int selectCount(VUserDetailInfo vUserDetailInfo) {
		return vUserDetailInfoMapper.selectCount(vUserDetailInfo);
	};
	
	@Override
	public int getSequence() {
		return vUserDetailInfoMapper.getSequence();
	}

	
	
}
