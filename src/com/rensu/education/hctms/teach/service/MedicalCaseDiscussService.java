package com.rensu.education.hctms.teach.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.teach.bean.MedicalCaseDiscuss;
import com.rensu.education.hctms.teach.bean.MedicalCaseMember;
import com.rensu.education.hctms.teach.dao.MedicalCaseDiscussDao;
import com.rensu.education.hctms.util.StringUtil;


@Service("medicalCaseDiscussService")
public class MedicalCaseDiscussService extends BaseService<MedicalCaseDiscuss> {
	
	Logger log = Logger.getLogger(MedicalCaseDiscussService.class);
	
	@Autowired
	protected MedicalCaseDiscussDao medicalCaseDiscussDao;

	public  Object discussinfomation(HttpServletRequest req) {
		int mcm_id=Integer.parseInt(req.getParameter("mcm_id"));
		MedicalCaseDiscuss mcd=new MedicalCaseDiscuss();
		mcd.setMcm_id(mcm_id);
		List<MedicalCaseDiscuss> medicalCaseDiscusses=medicalCaseDiscussDao.discussinfomation(mcd);
		return StringUtil.returns(true, medicalCaseDiscusses);
	}



	public  Object discussinfomation1(HttpServletRequest req) {
		int mcm_id=Integer.parseInt(req.getParameter("mcm_id"));
		int discuss_auth_id=Integer.parseInt(req.getParameter("auth_id"));
		MedicalCaseDiscuss mcd=new MedicalCaseDiscuss();
		mcd.setMcm_id(mcm_id);
		mcd.setDiscuss_auth_id(discuss_auth_id);
		List<MedicalCaseDiscuss> medicalCaseDiscusses=medicalCaseDiscussDao.discussinfomation1(mcd);
		return StringUtil.returns(true, medicalCaseDiscusses);
	}



	public  Object discussinfomation2(HttpServletRequest req) {
		int mcm_id=Integer.parseInt(req.getParameter("mcm_id"));
		int discuss_auth_id=Integer.parseInt(req.getParameter("auth_id"));
		int creator_id=Integer.parseInt(req.getParameter("creator_id"));
		MedicalCaseDiscuss mcd=new MedicalCaseDiscuss();
		mcd.setMcm_id(mcm_id);
		mcd.setDiscuss_auth_id(discuss_auth_id);
		mcd.setCreator_id(creator_id);
		List<MedicalCaseDiscuss> medicalCaseDiscusses=medicalCaseDiscussDao.discussinfomation2(mcd);
		return StringUtil.returns(true, medicalCaseDiscusses);
	}
	
}
