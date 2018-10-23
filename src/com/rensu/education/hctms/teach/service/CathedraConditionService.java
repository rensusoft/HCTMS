package com.rensu.education.hctms.teach.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.teach.bean.CathedraCondition;
import com.rensu.education.hctms.teach.bean.CathedraPlan;
import com.rensu.education.hctms.teach.dao.CathedraConditionDao;


@Service("cathedraConditionService")
public class CathedraConditionService extends BaseService<CathedraCondition> {
	
	Logger log = Logger.getLogger(CathedraConditionService.class);
	
	@Autowired
	protected CathedraConditionDao cathedraConditionDao;
	/**
	 * 查询讲座编排
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年3月6日
	 */
	public List<CathedraCondition> selectCathedraCondition(CathedraCondition cathedraCondition){
		return cathedraConditionDao.selectCathedraCondition(cathedraCondition);
	}

}
