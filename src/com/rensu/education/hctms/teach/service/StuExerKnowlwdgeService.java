package com.rensu.education.hctms.teach.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.teach.bean.StuExerKnowlwdge;
import com.rensu.education.hctms.teach.dao.StuExerKnowlwdgeDao;
import com.rensu.education.hctms.util.StringUtil;


@Service("stuExerKnowlwdgeService")
public class StuExerKnowlwdgeService extends BaseService<StuExerKnowlwdge> {
	
	Logger log = Logger.getLogger(StuExerKnowlwdgeService.class);
	
	@Autowired
	protected StuExerKnowlwdgeDao stuExerKnowlwdgeDao;

	public Object selectList(HttpServletRequest req) {
		boolean flag = false;
		List<StuExerKnowlwdge> list = null;
		String qeid = req.getParameter("qeid");
		if(qeid!=null&&!qeid.equals("")){
			StuExerKnowlwdge knowledge = new StuExerKnowlwdge();
			knowledge.setQe_id(Integer.parseInt(qeid));
			knowledge.setOrderCondition(" id asc");
			knowledge.setState("Y");
			list = stuExerKnowlwdgeDao.selectList(knowledge);
			if(list!=null&&list.size()>0)
				flag = true;
		}
		return StringUtil.returns(flag, list);
	}
}
