package com.rensu.education.hctms.teach.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.teach.bean.MedicalAdviceSub;

import configuration.mapper.MedicalAdviceSubMapper;

@Repository("medicalAdviceSubDao")
public class MedicalAdviceSubDao extends BaseDao<MedicalAdviceSub> {
	
	Logger log = Logger.getLogger(MedicalAdviceSubDao.class);
	
	@Autowired
	private MedicalAdviceSubMapper<MedicalAdviceSub> medicalAdviceSubMapper;
	
	@Override
	public int add(MedicalAdviceSub medicalAdviceSub) {
		return medicalAdviceSubMapper.add(medicalAdviceSub);
	};
	
	@Override
	public int update(MedicalAdviceSub medicalAdviceSub) {
		return medicalAdviceSubMapper.update(medicalAdviceSub);
	};
	
	public int updateByMamId(MedicalAdviceSub medicalAdviceSub) {
		return medicalAdviceSubMapper.updateByMamId(medicalAdviceSub);
	};
	
	@Override
	public int delete(MedicalAdviceSub medicalAdviceSub) {
		return medicalAdviceSubMapper.delete(medicalAdviceSub);
	};
	
	@Override
	public MedicalAdviceSub selectOne(int id) {
		return medicalAdviceSubMapper.selectOne(id);
	};
	
	@Override
	public List<MedicalAdviceSub> selectList(MedicalAdviceSub medicalAdviceSub) {
		return medicalAdviceSubMapper.selectList(medicalAdviceSub);
	};
	
	@Override
	public List<MedicalAdviceSub> selectPage(RowBounds rowBounds, MedicalAdviceSub medicalAdviceSub) {
		return medicalAdviceSubMapper.selectPage(rowBounds, medicalAdviceSub);
	};
	
	@Override
	public int selectCount(MedicalAdviceSub medicalAdviceSub) {
		return medicalAdviceSubMapper.selectCount(medicalAdviceSub);
	};
	
	@Override
	public int getSequence() {
		return medicalAdviceSubMapper.getSequence();
	}
	
	public List<MedicalAdviceSub> selectMedicalAdviceSubList(MedicalAdviceSub medicalAdviceSub) {
		return medicalAdviceSubMapper.selectMedicalAdviceSubList(medicalAdviceSub);
	}
}
