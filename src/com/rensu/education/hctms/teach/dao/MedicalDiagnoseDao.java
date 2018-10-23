package com.rensu.education.hctms.teach.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.teach.bean.MedicalDiagnose;

import configuration.mapper.MedicalDiagnoseMapper;

@Repository("medicalDiagnoseDao")
public class MedicalDiagnoseDao extends BaseDao<MedicalDiagnose> {
	
	Logger log = Logger.getLogger(MedicalDiagnoseDao.class);
	
	@Autowired
	private MedicalDiagnoseMapper<MedicalDiagnose> medicalDiagnoseMapper;
	
	@Override
	public int add(MedicalDiagnose medicalDiagnose) {
		return medicalDiagnoseMapper.add(medicalDiagnose);
	};
	
	@Override
	public int update(MedicalDiagnose medicalDiagnose) {
		return medicalDiagnoseMapper.update(medicalDiagnose);
	};
	
	public int updateByMrId(MedicalDiagnose medicalDiagnose) {
		return medicalDiagnoseMapper.updateByMrId(medicalDiagnose);
	};
	
	@Override
	public int delete(MedicalDiagnose medicalDiagnose) {
		return medicalDiagnoseMapper.delete(medicalDiagnose);
	};
	
	@Override
	public MedicalDiagnose selectOne(int id) {
		return medicalDiagnoseMapper.selectOne(id);
	};
	
	@Override
	public List<MedicalDiagnose> selectList(MedicalDiagnose medicalDiagnose) {
		return medicalDiagnoseMapper.selectList(medicalDiagnose);
	};
	
	@Override
	public List<MedicalDiagnose> selectPage(RowBounds rowBounds, MedicalDiagnose medicalDiagnose) {
		return medicalDiagnoseMapper.selectPage(rowBounds, medicalDiagnose);
	};
	
	@Override
	public int selectCount(MedicalDiagnose medicalDiagnose) {
		return medicalDiagnoseMapper.selectCount(medicalDiagnose);
	};
	
	@Override
	public int getSequence() {
		return medicalDiagnoseMapper.getSequence();
	}
	
}
