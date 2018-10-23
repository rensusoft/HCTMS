package com.rensu.education.hctms.userauth.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.userauth.bean.OrgaInfo;

import configuration.mapper.OrgaInfoMapper;

@Repository("orgaInfoDao")
public class OrgaInfoDao extends BaseDao<OrgaInfo> {
	
	Logger log = Logger.getLogger(OrgaInfoDao.class);
	
	@Autowired
	private OrgaInfoMapper<OrgaInfo> orgaInfoMapper;
	
	@Override
	public int add(OrgaInfo orgaInfo) {
		return orgaInfoMapper.add(orgaInfo);
	};
	
	@Override
	public int update(OrgaInfo orgaInfo) {
		return orgaInfoMapper.update(orgaInfo);
	};
	
	@Override
	public int delete(OrgaInfo orgaInfo) {
		return orgaInfoMapper.delete(orgaInfo);
	};
	
	@Override
	public OrgaInfo selectOne(int id) {
		return orgaInfoMapper.selectOne(id);
	};
	
	@Override
	public List<OrgaInfo> selectList(OrgaInfo orgaInfo) {
		return orgaInfoMapper.selectList(orgaInfo);
	};
	
	@Override
	public List<OrgaInfo> selectPage(RowBounds rowBounds, OrgaInfo orgaInfo) {
		return orgaInfoMapper.selectPage(rowBounds, orgaInfo);
	};
	
	@Override
	public int selectCount(OrgaInfo orgaInfo) {
		return orgaInfoMapper.selectCount(orgaInfo);
	};
	
	@Override
	public int getSequence() {
		return orgaInfoMapper.getSequence();
	}
	/**
	 * 查组织的id和名字
	 * 
	 * @return List<OrgaInfo>
	 * @author guocc
	 * @date 2017-04-18
	 */
	public List<OrgaInfo> selectOrgaInfoList() {
		return orgaInfoMapper.selectOrgaInfoList();
	}
}
