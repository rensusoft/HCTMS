package com.rensu.education.hctms.userauth.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.userauth.bean.StaffInfo;

import configuration.mapper.StaffInfoMapper;

@Repository("staffInfoDao")
public class StaffInfoDao extends BaseDao<StaffInfo> {
	
	Logger log = Logger.getLogger(StaffInfoDao.class);
	
	@Autowired
	private StaffInfoMapper<StaffInfo> staffInfoMapper;
	
	@Override
	public int add(StaffInfo staffInfo) {
		return staffInfoMapper.add(staffInfo);
	};
	
	@Override
	public int update(StaffInfo staffInfo) {
		return staffInfoMapper.update(staffInfo);
	};
	
	@Override
	public int delete(StaffInfo staffInfo) {
		return staffInfoMapper.delete(staffInfo);
	};
	
	@Override
	public StaffInfo selectOne(int id) {
		return staffInfoMapper.selectOne(id);
	};
	
	@Override
	public List<StaffInfo> selectList(StaffInfo staffInfo) {
		return staffInfoMapper.selectList(staffInfo);
	};
	
	@Override
	public List<StaffInfo> selectPage(RowBounds rowBounds, StaffInfo staffInfo) {
		return staffInfoMapper.selectPage(rowBounds, staffInfo);
	};
	
	@Override
	public int selectCount(StaffInfo staffInfo) {
		return staffInfoMapper.selectCount(staffInfo);
	};
	
	@Override
	public int getSequence() {
		return staffInfoMapper.getSequence();
	}

	public StaffInfo selectOneByUserCode(String user_code) {
		return staffInfoMapper.selectOneByUserCode(user_code);
	}

	public int insertWithList(List<StaffInfo> list) {
		return staffInfoMapper.insertWithList(list);
	}
	
}
