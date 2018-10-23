package com.rensu.education.hctms.teach.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.teach.bean.MedicalCaseMember;

import configuration.mapper.MedicalCaseMemberMapper;

@Repository("medicalCaseMemberDao")
public class MedicalCaseMemberDao extends BaseDao<MedicalCaseMember> {
	
	Logger log = Logger.getLogger(MedicalCaseMemberDao.class);
	
	@Autowired
	private MedicalCaseMemberMapper<MedicalCaseMember> medicalCaseMemberMapper;
	
	@Override
	public int add(MedicalCaseMember medicalCaseMember) {
		return medicalCaseMemberMapper.add(medicalCaseMember);
	};
	
	@Override
	public int update(MedicalCaseMember medicalCaseMember) {
		return medicalCaseMemberMapper.update(medicalCaseMember);
	};
	
	@Override
	public int delete(MedicalCaseMember medicalCaseMember) {
		return medicalCaseMemberMapper.delete(medicalCaseMember);
	};
	
	@Override
	public MedicalCaseMember selectOne(int id) {
		return medicalCaseMemberMapper.selectOne(id);
	};
	
	@Override
	public List<MedicalCaseMember> selectList(MedicalCaseMember medicalCaseMember) {
		return medicalCaseMemberMapper.selectList(medicalCaseMember);
	};
	
	@Override
	public List<MedicalCaseMember> selectPage(RowBounds rowBounds, MedicalCaseMember medicalCaseMember) {
		return medicalCaseMemberMapper.selectPage(rowBounds, medicalCaseMember);
	};
	
	@Override
	public int selectCount(MedicalCaseMember medicalCaseMember) {
		return medicalCaseMemberMapper.selectCount(medicalCaseMember);
	};
	
	@Override
	public int getSequence() {
		return medicalCaseMemberMapper.getSequence();
	}

	public List<MedicalCaseMember> selectNum(int mcm_id) {
		// TODO Auto-generated method stub
		return medicalCaseMemberMapper.selectNum(mcm_id);
	}
	
}
