package com.rensu.education.hctms.basicdata.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.util.StringUtil;
import com.rensu.education.hctms.basicdata.bean.QeQuesOption;
import com.rensu.education.hctms.basicdata.dao.QeQuesOptionDao;


@Service("qeQuesOptionService")
public class QeQuesOptionService extends BaseService<QeQuesOption> {
	
	Logger log = Logger.getLogger(QeQuesOptionService.class);
	
	@Autowired
	protected QeQuesOptionDao qeQuesOptionDao;

	public Object selectListByQqid(HttpServletRequest req){
		boolean flag = false;
		String qid = req.getParameter("qid");
		List<QeQuesOption> list =null;
		if(qid!=null&&!qid.equals("")){
			QeQuesOption option = new QeQuesOption();
			option.setQq_id(Integer.parseInt(qid));
			option.setState("Y");
			list = qeQuesOptionDao.selectList(option);
			if(list!=null&&list.size()>0)
				flag = true;
		}
		return StringUtil.returns(flag, list);
	}
}
