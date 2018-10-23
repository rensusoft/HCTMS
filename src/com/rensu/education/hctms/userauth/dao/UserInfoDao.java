package com.rensu.education.hctms.userauth.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.userauth.bean.UserInfo;
import com.rensu.education.hctms.userauth.bean.VUserDetailInfo;

import configuration.mapper.UserInfoMapper;

@Repository("userInfoDao")
public class UserInfoDao extends BaseDao<UserInfo> {
	
	Logger log = Logger.getLogger(UserInfoDao.class);
	
	@Autowired
	private UserInfoMapper<UserInfo> userInfoMapper;
	
	@Override
	public int add(UserInfo userInfo) {
		return userInfoMapper.add(userInfo);
	};
	
	@Override
	public int update(UserInfo userInfo) {
		return userInfoMapper.update(userInfo);
	};
	
	public int updateByUserCode(UserInfo userInfo) {
		return userInfoMapper.updateByUserCode(userInfo);
	};
	
	@Override
	public int delete(UserInfo userInfo) {
		return userInfoMapper.delete(userInfo);
	};
	
	@Override
	public UserInfo selectOne(int id) {
		return userInfoMapper.selectOne(id);
	};
	
	@Override
	public List<UserInfo> selectList(UserInfo userInfo) {
		return userInfoMapper.selectList(userInfo);
	};
	
	@Override
	public List<UserInfo> selectPage(RowBounds rowBounds, UserInfo userInfo) {
		return userInfoMapper.selectPage(rowBounds, userInfo);
	};
	
	@Override
	public int selectCount(UserInfo userInfo) {
		return userInfoMapper.selectCount(userInfo);
	};
	
	@Override
	public int getSequence() {
		return userInfoMapper.getSequence();
	}
	
	public List<UserInfo> loginByCodePsd(Map<String,Object> pramMap){
		return userInfoMapper.loginByCodePsd(pramMap);
	}

	public List<UserInfo> getAllUserInfo() {
		return userInfoMapper.getAllUserInfo();
	}

	public List<VUserDetailInfo> selectBytpId(UserInfo td) {
		return userInfoMapper.selectBytpId(td);
	}

	public List<UserInfo> selectPageByMoHu(RowBounds rowBounds, UserInfo user) {
		return userInfoMapper.selectPageByMoHu(rowBounds,user);
	}

	public int selectKeYonCount(UserInfo userinfo) {
		return userInfoMapper.selectKeYonCount(userinfo);
	}
	//根据学生ID查询学生的带教老师
	public UserInfo selectTeacher(UserInfo userinfo) {
		return userInfoMapper.selectTeacher(userinfo);
	}

	//根据orga_id和role_id查询科教秘书
	public List<UserInfo> selectOrgaTea(UserInfo userinfo) {
		return userInfoMapper.selectOrgaTea(userinfo);
	}

	public List<UserInfo> findAllUserName(UserInfo userInfo) {
		return userInfoMapper.findAllUserName(userInfo);
	}

	public List<UserInfo> selectTeachersList(UserInfo userInfo) {
		return userInfoMapper.selectTeachersList(userInfo);
	}
	//guocc模糊查询
	public List<UserInfo> findAllUserNameAndId(UserInfo userInfo) {
		return userInfoMapper.findAllUserNameAndId(userInfo);
	}

	public UserInfo selecAuthIdBy(UserInfo info) {
		return userInfoMapper.selecAuthIdBy(info);
	}

	public List<UserInfo> selectTeaList(UserInfo info) {
		return userInfoMapper.selectTeaList(info);
	}
}
