package com.rensu.education.hctms.basicdata.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.util.StringUtil;
import com.rensu.education.hctms.basicdata.bean.QeQuesType;
import com.rensu.education.hctms.basicdata.dao.QeQuesTypeDao;


@Service("qeQuesTypeService")
public class QeQuesTypeService extends BaseService<QeQuesType> {
	
	Logger log = Logger.getLogger(QeQuesTypeService.class);
	
	@Autowired
	protected QeQuesTypeDao qeQuesTypeDao;
	
	public Object selectList() {
		QeQuesType type = new QeQuesType();
		type.setState("Y");
		List<QeQuesType> list = qeQuesTypeDao.selectList(type);
		boolean flag = false;
		if(list!=null&&list.size()>0){
			flag = true;
		}
		return StringUtil.returns(flag, list);
	}

}
