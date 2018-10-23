package com.rensu.education.hctms.teach.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.teach.bean.MedicalAdviceMain;

import configuration.mapper.MedicalAdviceMainMapper;

@Repository("medicalAdviceMainDao")
public class MedicalAdviceMainDao extends BaseDao<MedicalAdviceMain> {
	
	Logger log = Logger.getLogger(MedicalAdviceMainDao.class);
	
	@Autowired
	private MedicalAdviceMainMapper<MedicalAdviceMain> medicalAdviceMainMapper;
	
	@Override
	public int add(MedicalAdviceMain medicalAdviceMain) {
		return medicalAdviceMainMapper.add(medicalAdviceMain);
	};
	
	@Override
	public int update(MedicalAdviceMain medicalAdviceMain) {
		return medicalAdviceMainMapper.update(medicalAdviceMain);
	};
	
	@Override
	public int delete(MedicalAdviceMain medicalAdviceMain) {
		return medicalAdviceMainMapper.delete(medicalAdviceMain);
	};
	
	@Override
	public MedicalAdviceMain selectOne(int id) {
		return medicalAdviceMainMapper.selectOne(id);
	};
	
	@Override
	public List<MedicalAdviceMain> selectList(MedicalAdviceMain medicalAdviceMain) {
		return medicalAdviceMainMapper.selectList(medicalAdviceMain);
	};
	
	@Override
	public List<MedicalAdviceMain> selectPage(RowBounds rowBounds, MedicalAdviceMain medicalAdviceMain) {
		return medicalAdviceMainMapper.selectPage(rowBounds, medicalAdviceMain);
	};
	
	@Override
	public int selectCount(MedicalAdviceMain medicalAdviceMain) {
		return medicalAdviceMainMapper.selectCount(medicalAdviceMain);
	};
	
	@Override
	public int getSequence() {
		return medicalAdviceMainMapper.getSequence();
	}
	
	public MedicalAdviceMain selectMedicalAdviceMainById(int id) {
		return medicalAdviceMainMapper.selectMedicalAdviceMainById(id);
	}
	
}
