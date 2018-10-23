package com.rensu.education.hctms.teach.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.teach.bean.MedicalAdviceSub;
import com.rensu.education.hctms.teach.dao.MedicalAdviceSubDao;


@Service("medicalAdviceSubService")
public class MedicalAdviceSubService extends BaseService<MedicalAdviceSub> {
	
	Logger log = Logger.getLogger(MedicalAdviceSubService.class);
	
	@Autowired
	protected MedicalAdviceSubDao medicalAdviceSubDao;

	/**
	 * 查询医嘱明细
	 * @param medicalAdviceSub
	 * @return
	 * @author guocc
	 * @date 2017年8月29日
	 */
	public List<MedicalAdviceSub> selectMedicalAdviceSubList(MedicalAdviceSub medicalAdviceSub) {
		return medicalAdviceSubDao.selectMedicalAdviceSubList(medicalAdviceSub);
	}
}
