package com.rensu.education.hctms.teach.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.teach.bean.StuActiveData;

import configuration.mapper.StuActiveDataMapper;

@Repository("stuActiveDataDao")
public class StuActiveDataDao extends BaseDao<StuActiveData> {
	
	Logger log = Logger.getLogger(StuActiveDataDao.class);
	
	@Autowired
	private StuActiveDataMapper<StuActiveData> stuActiveDataMapper;
	
	@Override
	public int add(StuActiveData stuActiveData) {
		return stuActiveDataMapper.add(stuActiveData);
	};
	
	@Override
	public int update(StuActiveData stuActiveData) {
		return stuActiveDataMapper.update(stuActiveData);
	};
	
	@Override
	public int delete(StuActiveData stuActiveData) {
		return stuActiveDataMapper.delete(stuActiveData);
	};
	
	@Override
	public StuActiveData selectOne(int id) {
		return stuActiveDataMapper.selectOne(id);
	};
	
	@Override
	public List<StuActiveData> selectList(StuActiveData stuActiveData) {
		return stuActiveDataMapper.selectList(stuActiveData);
	};
	
	@Override
	public List<StuActiveData> selectPage(RowBounds rowBounds, StuActiveData stuActiveData) {
		return stuActiveDataMapper.selectPage(rowBounds, stuActiveData);
	};
	
	public List<StuActiveData> selectStuActiveData(RowBounds rowBounds, StuActiveData stuActiveData) {
		return stuActiveDataMapper.selectStuActiveData(rowBounds, stuActiveData);
	};
	
	@Override
	public int selectCount(StuActiveData stuActiveData) {
		return stuActiveDataMapper.selectCount(stuActiveData);
	};
	
	public int selectStuActiveDataCount(StuActiveData stuActiveData) {
		return stuActiveDataMapper.selectStuActiveDataCount(stuActiveData);
	};
	
	@Override
	public int getSequence() {
		return stuActiveDataMapper.getSequence();
	}

	public List<StuActiveData> selectPageEntering(RowBounds rowBounds,
			StuActiveData t) {
		return stuActiveDataMapper.selectPageEntering(rowBounds, t);
	}

	public List<StuActiveData> selectShenHeCountByOA(StuActiveData stuActiveData) {
		return stuActiveDataMapper.selectShenHeCountByOA(stuActiveData);
	}

	public List<StuActiveData> getStuInput(StuActiveData stuActiveData) {
		return stuActiveDataMapper.getStuInput(stuActiveData);
	}

	/**
	 * 根据id查询审批评价
	 * @param stuActiveData
	 * @return
	 * @author guocc
	 * @date 2017年6月1日
	 */
	public StuActiveData getExamineTextById(StuActiveData stuActiveData) {
		return stuActiveDataMapper.getExamineTextById(stuActiveData);
	}
}
