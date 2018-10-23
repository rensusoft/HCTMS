package com.rensu.education.hctms.userauth.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.userauth.bean.UserAuthority;

import configuration.mapper.UserAuthorityMapper;

@Repository("userAuthorityDao")
public class UserAuthorityDao extends BaseDao<UserAuthority> {
	
	Logger log = Logger.getLogger(UserAuthorityDao.class);
	
	@Autowired
	private UserAuthorityMapper<UserAuthority> userAuthorityMapper;
	
	@Override
	public int add(UserAuthority userAuthority) {
		return userAuthorityMapper.add(userAuthority);
	};
	
	@Override
	public int update(UserAuthority userAuthority) {
		return userAuthorityMapper.update(userAuthority);
	};
	
	@Override
	public int delete(UserAuthority userAuthority) {
		return userAuthorityMapper.delete(userAuthority);
	};
	
	@Override
	public UserAuthority selectOne(int id) {
		return userAuthorityMapper.selectOne(id);
	};
	
	@Override
	public List<UserAuthority> selectList(UserAuthority userAuthority) {
		return userAuthorityMapper.selectList(userAuthority);
	};
	
	@Override
	public List<UserAuthority> selectPage(RowBounds rowBounds, UserAuthority userAuthority) {
		return userAuthorityMapper.selectPage(rowBounds, userAuthority);
	};
	
	@Override
	public int selectCount(UserAuthority userAuthority) {
		return userAuthorityMapper.selectCount(userAuthority);
	};
	
	@Override
	public int getSequence() {
		return userAuthorityMapper.getSequence();
	}
	
	public int updateState(int authId) {		
		return userAuthorityMapper.updateState(authId);
	}

	public List<UserAuthority> selectTeacherList(UserAuthority userAuthority) {
		return userAuthorityMapper.selectTeacherList(userAuthority);
	}

	public UserAuthority selectOneNameByAuth(Integer teacherId) {
		return userAuthorityMapper.selectOneNameByAuth(teacherId);
	}
	
	public UserAuthority selectStuByAuthId(UserAuthority userAuthority){
		return userAuthorityMapper.selectStuByAuthId(userAuthority);
	}
	
    public int updateOrga_id(UserAuthority userAuthority) {
		
		return userAuthorityMapper.updateOrga_id(userAuthority);
	}

	public List<UserAuthority> selectMessagePeo(UserAuthority userAuthority) {
		return userAuthorityMapper.selectMessagePeo(userAuthority);
	}
}
