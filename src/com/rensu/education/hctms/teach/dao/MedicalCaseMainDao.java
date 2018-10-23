package com.rensu.education.hctms.teach.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.teach.bean.MedicalCaseMain;

import configuration.mapper.MedicalCaseMainMapper;

@Repository("medicalCaseMainDao")
public class MedicalCaseMainDao extends BaseDao<MedicalCaseMain> {
	
	Logger log = Logger.getLogger(MedicalCaseMainDao.class);
	
	@Autowired
	private MedicalCaseMainMapper<MedicalCaseMain> medicalCaseMainMapper;
	
	@Override
	public int add(MedicalCaseMain medicalCaseMain) {
		return medicalCaseMainMapper.add(medicalCaseMain);
	};
	
	@Override
	public int update(MedicalCaseMain medicalCaseMain) {
		return medicalCaseMainMapper.update(medicalCaseMain);
	};
	
	@Override
	public int delete(MedicalCaseMain medicalCaseMain) {
		return medicalCaseMainMapper.delete(medicalCaseMain);
	};
	
	@Override
	public MedicalCaseMain selectOne(int id) {
		return medicalCaseMainMapper.selectOne(id);
	};
	
	@Override
	public List<MedicalCaseMain> selectList(MedicalCaseMain medicalCaseMain) {
		return medicalCaseMainMapper.selectList(medicalCaseMain);
	};
	
	@Override
	public List<MedicalCaseMain> selectPage(RowBounds rowBounds, MedicalCaseMain medicalCaseMain) {
		return medicalCaseMainMapper.selectPage(rowBounds, medicalCaseMain);
	};
	
	public List<MedicalCaseMain> selectMedicalCaseDiscuss(RowBounds rowBounds, MedicalCaseMain medicalCaseMain) {
		return medicalCaseMainMapper.selectMedicalCaseDiscuss(rowBounds, medicalCaseMain);
	};
	
	@Override
	public int selectCount(MedicalCaseMain medicalCaseMain) {
		return medicalCaseMainMapper.selectCount(medicalCaseMain);
	};
	
	public int selectMCDCount(MedicalCaseMain medicalCaseMain) {
		return medicalCaseMainMapper.selectMCDCount(medicalCaseMain);
	};
	
	@Override
	public int getSequence() {
		return medicalCaseMainMapper.getSequence();
	}

	public int selectCountDiscuss(MedicalCaseMain medicalCaseMain) {
		// TODO Auto-generated method stub
		return medicalCaseMainMapper.selectCountDiscuss(medicalCaseMain);
	}

	public List<MedicalCaseMain> selectPageDiscuss(RowBounds rowBounds,
			MedicalCaseMain medicalCaseMain) {
		// TODO Auto-generated method stub
		return medicalCaseMainMapper.selectPageDiscuss(rowBounds,medicalCaseMain);
	}
	
	public List<MedicalCaseMain> getDiscussants(MedicalCaseMain medicalCaseMain) {
		return medicalCaseMainMapper.getDiscussants(medicalCaseMain);
	}
	
	public List<MedicalCaseMain> getDiscussRecords(MedicalCaseMain medicalCaseMain) {
		return medicalCaseMainMapper.getDiscussRecords(medicalCaseMain);
	}
}
