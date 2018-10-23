package com.rensu.education.hctms.basicdata.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.util.StringUtil;
import com.rensu.education.hctms.basicdata.bean.QeQuesAnswer;
import com.rensu.education.hctms.basicdata.dao.QeQuesAnswerDao;


@Service("qeQuesAnswerService")
public class QeQuesAnswerService extends BaseService<QeQuesAnswer> {
	
	Logger log = Logger.getLogger(QeQuesAnswerService.class);
	
	@Autowired
	protected QeQuesAnswerDao qeQuesAnswerDao;

	public Object selectOneByQqid(HttpServletRequest req){
		String qid = req.getParameter("qid");
		JSONObject jobj = new JSONObject();
		if(qid!=null&&!qid.equals("")){
			QeQuesAnswer answer = new QeQuesAnswer();
			answer.setQq_id(Integer.parseInt(qid));
			answer.setState("Y");
			List<QeQuesAnswer> list = qeQuesAnswerDao.selectList(answer);
			if(list!=null&&list.size()==1)
				jobj.put("data", list.get(0));
		}
		return StringUtil.dnull(jobj.toString());
	}
}
