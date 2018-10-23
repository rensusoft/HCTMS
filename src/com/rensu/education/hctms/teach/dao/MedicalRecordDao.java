package com.rensu.education.hctms.teach.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.teach.bean.MedicalRecord;

import configuration.mapper.MedicalRecordMapper;

@Repository("medicalRecordDao")
public class MedicalRecordDao extends BaseDao<MedicalRecord> {
	
	Logger log = Logger.getLogger(MedicalRecordDao.class);
	
	@Autowired
	private MedicalRecordMapper<MedicalRecord> medicalRecordMapper;
	
	@Override
	public int add(MedicalRecord medicalRecord) {
		return medicalRecordMapper.add(medicalRecord);
	};
	
	@Override
	public int update(MedicalRecord medicalRecord) {
		return medicalRecordMapper.update(medicalRecord);
	};
	
	@Override
	public int delete(MedicalRecord medicalRecord) {
		return medicalRecordMapper.delete(medicalRecord);
	};
	
	@Override
	public MedicalRecord selectOne(int id) {
		return medicalRecordMapper.selectOne(id);
	};
	
	@Override
	public List<MedicalRecord> selectList(MedicalRecord medicalRecord) {
		return medicalRecordMapper.selectList(medicalRecord);
	};
	
	@Override
	public List<MedicalRecord> selectPage(RowBounds rowBounds, MedicalRecord medicalRecord) {
		return medicalRecordMapper.selectPage(rowBounds, medicalRecord);
	};
	
	@Override
	public int selectCount(MedicalRecord medicalRecord) {
		return medicalRecordMapper.selectCount(medicalRecord);
	};  
	
	@Override
	public int getSequence() {
		return medicalRecordMapper.getSequence();
	}

	public List<MedicalRecord> selectMedicalDocumentTea(MedicalRecord medicalRecord) {
		
		return medicalRecordMapper.selectMedicalDocumentTea(medicalRecord);
	}

	public int selectMedicalDocumentCountTea(MedicalRecord medicalRecord) {
		
		return medicalRecordMapper.selectMedicalDocumentCountTea(medicalRecord);
	}
	
	public List<MedicalRecord> selectMedicalDocument(RowBounds rowBounds, MedicalRecord medicalRecord) {
		return medicalRecordMapper.selectMedicalDocument(rowBounds, medicalRecord);
	}
	
	public int selectMedicalDocumentCount(MedicalRecord medicalRecord) {
		return medicalRecordMapper.selectMedicalDocumentCount(medicalRecord);
	};
	
	public MedicalRecord selectMedicalRecordById(int id) {
		return medicalRecordMapper.selectMedicalRecordById(id);
	}
}
