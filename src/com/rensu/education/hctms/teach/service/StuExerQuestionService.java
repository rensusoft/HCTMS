package com.rensu.education.hctms.teach.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.basicdata.bean.QeQuesAnswer;
import com.rensu.education.hctms.basicdata.dao.QeQuesAnswerDao;
import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.teach.bean.StuExerQuestion;
import com.rensu.education.hctms.teach.dao.StuExerQuestionDao;
import com.rensu.education.hctms.util.StringUtil;


@Service("stuExerQuestionService")
public class StuExerQuestionService extends BaseService<StuExerQuestion> {
	
	Logger log = Logger.getLogger(StuExerQuestionService.class);
	
	@Autowired
	protected StuExerQuestionDao stuExerQuestionDao;
	@Autowired
	protected QeQuesAnswerDao qeQuesAnswerDao;

	public Object update(HttpServletRequest req) {
		boolean flag = false;
		String seqid = req.getParameter("seqid");
		String stu_answer = req.getParameter("stu_answer");
		String aid = req.getParameter("aid");
		StuExerQuestion exerques = new StuExerQuestion();
		if(seqid!=null&&!seqid.equals(""))
			exerques.setId(Integer.parseInt(seqid));
		exerques.setStu_answer(stu_answer);
		if(aid!=null&&!aid.equals("")){
			QeQuesAnswer answer = qeQuesAnswerDao.selectOne(Integer.parseInt(aid));
			if(answer!=null){
				if(answer.getAnswer().equals(stu_answer))
					exerques.setStu_result("Y");
				else
					exerques.setStu_result("N");
			}
		}
		int num = stuExerQuestionDao.update(exerques);
		if(num>0) flag = true;
		else flag = false;
		return StringUtil.returns(flag, "");
	}
	
	public Object selectList(HttpServletRequest req) {
		boolean flag = false;
		List<StuExerQuestion> list = null;
		String qeid = req.getParameter("qeid");
		String sturesult = req.getParameter("sturesult");
		if(qeid!=null&&!qeid.equals("")){
			StuExerQuestion ques = new StuExerQuestion();
			ques.setQe_id(Integer.parseInt(qeid));
			ques.setStu_result(sturesult);
			ques.setOrderCondition(" id asc");
			list = stuExerQuestionDao.selectList(ques);
			if(list!=null&&list.size()>0)
				flag = true;
		}
		return StringUtil.returns(flag, list);
	}
}
