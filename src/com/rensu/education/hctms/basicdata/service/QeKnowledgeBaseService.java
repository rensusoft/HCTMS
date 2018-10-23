package com.rensu.education.hctms.basicdata.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.util.StringUtil;
import com.rensu.education.hctms.basicdata.bean.QeKnowledgeBase;
import com.rensu.education.hctms.basicdata.dao.QeKnowledgeBaseDao;


@Service("qeKnowledgeBaseService")
public class QeKnowledgeBaseService extends BaseService<QeKnowledgeBase> {
	
	Logger log = Logger.getLogger(QeKnowledgeBaseService.class);
	
	@Autowired
	protected QeKnowledgeBaseDao qeKnowledgeBaseDao;

	public Object selectList(HttpServletRequest req){
		String pid = req.getParameter("pid");
		QeKnowledgeBase knowledge = new QeKnowledgeBase();
		if(pid!=null&&!pid.equals(""))
			knowledge.setPid(Integer.parseInt(pid));
		knowledge.setState("Y");
		knowledge.setOrderCondition(" ordinal asc");
		List<QeKnowledgeBase> list = qeKnowledgeBaseDao.selectList(knowledge);
        return StringUtil.returns(true, list);
	}
	
	public Object selectOne(HttpServletRequest req){
		String id = req.getParameter("id");
		JSONObject jobj = new JSONObject();
		if(id!=null&&!id.equals("")){
			QeKnowledgeBase knowledge = qeKnowledgeBaseDao.selectOne(Integer.parseInt(id));
			jobj.put("data", knowledge);
		}
		return StringUtil.dnull(jobj.toString());
	}
}
