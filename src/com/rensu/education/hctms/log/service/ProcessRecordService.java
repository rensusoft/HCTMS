package com.rensu.education.hctms.log.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.log.bean.ProcessRecord;
import com.rensu.education.hctms.log.dao.ProcessRecordDao;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.StringUtil;

@Service("processRecordService")
public class ProcessRecordService extends BaseService<ProcessRecord> {
	
	Logger log = Logger.getLogger(ProcessRecordService.class);
	
	@Autowired
	protected ProcessRecordDao processRecordDao;

	
	/***
	 * 查看请假流程
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年4月5日
	 */
	public Object selectListByRelatedId(HttpServletRequest req) {
		LoginBean loginBean = (LoginBean)req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		String id = req.getParameter("id");
		ProcessRecord processRecord = new ProcessRecord();
		if(id!=""&&id!=null)
		processRecord.setRelated_id(Integer.parseInt(id));
		List<ProcessRecord> processList = processRecordDao.selectListByRelatedId(processRecord);
		return StringUtil.returns(true, processList);
	}
}
