package com.rensu.education.hctms.publicdata.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.publicdata.bean.PublicData;

import configuration.mapper.PublicDataMapper;

@Repository("publicDataDao")
public class PublicDataDao extends BaseDao<PublicData> {
	
	Logger log = Logger.getLogger(PublicDataDao.class);
	
	@Autowired
	private PublicDataMapper<PublicData> publicDataMapper;
	
	@Override
	public int add(PublicData publicData) {
		return publicDataMapper.add(publicData);
	};
	
	@Override
	public int update(PublicData publicData) {
		return publicDataMapper.update(publicData);
	};
	
	@Override
	public int delete(PublicData publicData) {
		return publicDataMapper.delete(publicData);
	};
	
	@Override
	public PublicData selectOne(int id) {
		return publicDataMapper.selectOne(id);
	};
	
	@Override
	public List<PublicData> selectList(PublicData publicData) {
		return publicDataMapper.selectList(publicData);
	};
	
	@Override
	public List<PublicData> selectPage(RowBounds rowBounds, PublicData publicData) {
		return publicDataMapper.selectPage(rowBounds, publicData);
	};
	
	@Override
	public int selectCount(PublicData publicData) {
		return publicDataMapper.selectCount(publicData);
	};
	
	@Override
	public int getSequence() {
		return publicDataMapper.getSequence();
	}
	
	public List<PublicData> selectPage(PublicData publicData) {
		return publicDataMapper.selectPage(publicData);
	}

	public PublicData selectOneById(int id) {
		return publicDataMapper.selectOneById(id);
	}

	public List<PublicData> selectPageOfOthers(RowBounds rowBounds,
			PublicData publicData) {
		
		return publicDataMapper.selectPageOfOthers(rowBounds, publicData);
	}

	public int selectCountOfOthers(PublicData publicData) {
		return publicDataMapper.selectCountOfOthers(publicData);
	}

	public List<PublicData> selectPageOfSchool(RowBounds rowBounds,
			PublicData publicData) {
		return publicDataMapper.selectPageOfSchool(rowBounds, publicData);
	};
}
