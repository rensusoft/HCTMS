package com.rensu.education.hctms.teach.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.teach.bean.MedicalCaseMember;
import com.rensu.education.hctms.teach.dao.MedicalCaseMemberDao;
import com.rensu.education.hctms.util.StringUtil;


@Service("medicalCaseMemberService")
public class MedicalCaseMemberService extends BaseService<MedicalCaseMember> {
	
	Logger log = Logger.getLogger(MedicalCaseMemberService.class);
	
	@Autowired
	protected MedicalCaseMemberDao medicalCaseMemberDao;

	public  Object selectNum(HttpServletRequest req) {
		int mcm_id=Integer.parseInt(req.getParameter("mcm_id"));
		
		List<MedicalCaseMember> medicalCaseMember=medicalCaseMemberDao.selectNum(mcm_id);
		
		return StringUtil.returns(true, medicalCaseMember);
	}

}
