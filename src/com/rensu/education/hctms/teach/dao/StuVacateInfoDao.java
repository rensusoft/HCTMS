package com.rensu.education.hctms.teach.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.basicdata.bean.ProcessInfo;
import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.teach.bean.StuVacateInfo;

import configuration.mapper.StuVacateInfoMapper;

@Repository("stuVacateInfoDao")
public class StuVacateInfoDao extends BaseDao<StuVacateInfo> {
	
	Logger log = Logger.getLogger(StuVacateInfoDao.class);
	
	@Autowired
	private StuVacateInfoMapper<StuVacateInfo> stuVacateInfoMapper;
	
	@Override
	public int add(StuVacateInfo stuVacateInfo) {
		return stuVacateInfoMapper.add(stuVacateInfo);
	};
	
	@Override
	public int update(StuVacateInfo stuVacateInfo) {
		return stuVacateInfoMapper.update(stuVacateInfo);
	};
	
	@Override
	public int delete(StuVacateInfo stuVacateInfo) {
		return stuVacateInfoMapper.delete(stuVacateInfo);
	};
	
	@Override
	public StuVacateInfo selectOne(int id) {
		return stuVacateInfoMapper.selectOne(id);
	};
	
	@Override
	public List<StuVacateInfo> selectList(StuVacateInfo stuVacateInfo) {
		return stuVacateInfoMapper.selectList(stuVacateInfo);
	};
	
	@Override
	public List<StuVacateInfo> selectPage(RowBounds rowBounds, StuVacateInfo stuVacateInfo) {
		return stuVacateInfoMapper.selectPage(rowBounds, stuVacateInfo);
	};
	
	@Override
	public int selectCount(StuVacateInfo stuVacateInfo) {
		return stuVacateInfoMapper.selectCount(stuVacateInfo);
	};
	
	@Override
	public int getSequence() {
		return stuVacateInfoMapper.getSequence();
	}
	
	public List<StuVacateInfo> selectVacateInfo(RowBounds rowBounds, StuVacateInfo stuVacateInfo) {
		return stuVacateInfoMapper.selectVacateInfo(rowBounds, stuVacateInfo);
	}
	
	public StuVacateInfo selectVacateById(int id) {
		return stuVacateInfoMapper.selectVacateById(id);
	}
	
	/**
	 * @param proc_num
	 * @return ProcessInfo
	 * @author guocc
	 * @date 2017/3/24
	 */
	public ProcessInfo getVacateProcessOfTeacher(int proc_num) {
		return stuVacateInfoMapper.getVacateProcessOfTeacher(proc_num);
	} 
	
	/**
	 * @param proc_num
	 * @return List<Integer>
	 * @author guocc
	 * @date 2017/3/24
	 */
	public List<Integer> selectRoleId(int proc_num) {
		return stuVacateInfoMapper.selectRoleId(proc_num);
	} 
	
	/**
	 * @param roleId
	 * @return List<Integer>
	 * @author guocc
	 * @date 2017/3/24
	 */
	public List<Integer> selectAuthId(Integer roleId) {
		return stuVacateInfoMapper.selectAuthId(roleId);
	}

	public List<StuVacateInfo> selectVacateList(RowBounds rowBounds,
			StuVacateInfo stuVacateInfo) {		
		return stuVacateInfoMapper.selectVacateList(rowBounds,stuVacateInfo);
	}

	public int selectVacateListCount(StuVacateInfo stuVacateInfo) {		
		return stuVacateInfoMapper.selectVacateListCount(stuVacateInfo);
	}
	
	/**
	 * 查询待销假的申请
	 * @param stuVacateInfo
	 * @return
	 * @author guocc
	 * @date 2017年6月2日
	 */
	public List<StuVacateInfo> selectVacateListByStatus(StuVacateInfo stuVacateInfo) {
		return stuVacateInfoMapper.selectVacateListByStatus(stuVacateInfo);
	}
}
