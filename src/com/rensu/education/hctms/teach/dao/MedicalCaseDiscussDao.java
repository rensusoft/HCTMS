package com.rensu.education.hctms.teach.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.teach.bean.MedicalCaseDiscuss;

import configuration.mapper.MedicalCaseDiscussMapper;

@Repository("medicalCaseDiscussDao")
public class MedicalCaseDiscussDao extends BaseDao<MedicalCaseDiscuss> {
	
	Logger log = Logger.getLogger(MedicalCaseDiscussDao.class);
	
	@Autowired
	private MedicalCaseDiscussMapper<MedicalCaseDiscuss> medicalCaseDiscussMapper;
	
	@Override
	public int add(MedicalCaseDiscuss medicalCaseDiscuss) {
		return medicalCaseDiscussMapper.add(medicalCaseDiscuss);
	};
	
	@Override
	public int update(MedicalCaseDiscuss medicalCaseDiscuss) {
		return medicalCaseDiscussMapper.update(medicalCaseDiscuss);
	};
	
	@Override
	public int delete(MedicalCaseDiscuss medicalCaseDiscuss) {
		return medicalCaseDiscussMapper.delete(medicalCaseDiscuss);
	};
	
	@Override
	public MedicalCaseDiscuss selectOne(int id) {
		return medicalCaseDiscussMapper.selectOne(id);
	};
	
	@Override
	public List<MedicalCaseDiscuss> selectList(MedicalCaseDiscuss medicalCaseDiscuss) {
		return medicalCaseDiscussMapper.selectList(medicalCaseDiscuss);
	};
	
	@Override
	public List<MedicalCaseDiscuss> selectPage(RowBounds rowBounds, MedicalCaseDiscuss medicalCaseDiscuss) {
		return medicalCaseDiscussMapper.selectPage(rowBounds, medicalCaseDiscuss);
	};
	
	@Override
	public int selectCount(MedicalCaseDiscuss medicalCaseDiscuss) {
		return medicalCaseDiscussMapper.selectCount(medicalCaseDiscuss);
	};
	
	@Override
	public int getSequence() {
		return medicalCaseDiscussMapper.getSequence();
	}

	public List<MedicalCaseDiscuss> discussinfomation(MedicalCaseDiscuss mcd) {
		// TODO Auto-generated method stub
		return medicalCaseDiscussMapper.discussinfomation(mcd);
	}

	public List<MedicalCaseDiscuss> discussinfomation1(MedicalCaseDiscuss mcd) {
		// TODO Auto-generated method stub
		return medicalCaseDiscussMapper.discussinfomation1(mcd);
	}

	public List<MedicalCaseDiscuss> discussinfomation2(MedicalCaseDiscuss mcd) {
		// TODO Auto-generated method stub
		return medicalCaseDiscussMapper.discussinfomation2(mcd);
	}


	
}
