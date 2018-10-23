package com.rensu.education.hctms.basicdata.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.basicdata.bean.RoleProcRela;

import configuration.mapper.RoleProcRelaMapper;

@Repository("roleProcRelaDao")
public class RoleProcRelaDao extends BaseDao<RoleProcRela> {
	
	Logger log = Logger.getLogger(RoleProcRelaDao.class);
	
	@Autowired
	private RoleProcRelaMapper<RoleProcRela> roleProcRelaMapper;
	
	@Override
	public int add(RoleProcRela roleProcRela) {
		return roleProcRelaMapper.add(roleProcRela);
	};
	
	@Override
	public int update(RoleProcRela roleProcRela) {
		return roleProcRelaMapper.update(roleProcRela);
	};
	
	@Override
	public int delete(RoleProcRela roleProcRela) {
		return roleProcRelaMapper.delete(roleProcRela);
	};
	
	@Override
	public RoleProcRela selectOne(int id) {
		return roleProcRelaMapper.selectOne(id);
	};
	
	@Override
	public List<RoleProcRela> selectList(RoleProcRela roleProcRela) {
		return roleProcRelaMapper.selectList(roleProcRela);
	};
	
	@Override
	public List<RoleProcRela> selectPage(RowBounds rowBounds, RoleProcRela roleProcRela) {
		return roleProcRelaMapper.selectPage(rowBounds, roleProcRela);
	};
	
	@Override
	public int selectCount(RoleProcRela roleProcRela) {
		return roleProcRelaMapper.selectCount(roleProcRela);
	};
	
	@Override
	public int getSequence() {
		return roleProcRelaMapper.getSequence();
	}

	public List<RoleProcRela> selectAuthIdList(RoleProcRela roleProcRela) {
		return roleProcRelaMapper.selectAuthIdList(roleProcRela);
	}

	public List<RoleProcRela> selectProcessRole(RoleProcRela roleProcRela) {
		return roleProcRelaMapper.selectProcessRole(roleProcRela);
	}

	public int deleteByEnd(RoleProcRela roleProcRela) {
		return roleProcRelaMapper.deleteByEnd(roleProcRela);
	}

	public int deleteByIds(RoleProcRela roleProcRela) {
		return roleProcRelaMapper.deleteByIds(roleProcRela);
		
	}

	public int updateByEnd(RoleProcRela roleProcRela) {
		return roleProcRelaMapper.updateByEnd(roleProcRela);
		
	}

	public List<RoleProcRela> selectListByprocId(RoleProcRela roleProcRela) {
		return roleProcRelaMapper.selectListByprocId(roleProcRela);
	}

	public List<RoleProcRela> selectRoleId(int end_id) {
		return roleProcRelaMapper.selectRoleId(end_id);
	}

	public List<RoleProcRela> selectRoleProc() {
		return roleProcRelaMapper.selectRoleProc();
	}
}
