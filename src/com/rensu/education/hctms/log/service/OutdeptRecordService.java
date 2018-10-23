package com.rensu.education.hctms.log.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.log.bean.OutdeptRecord;
import com.rensu.education.hctms.log.dao.OutdeptRecordDao;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.StringUtil;


@Service("outdeptRecordService")
public class OutdeptRecordService extends BaseService<OutdeptRecord> {
	
	Logger log = Logger.getLogger(OutdeptRecordService.class);
	
	@Autowired
	protected OutdeptRecordDao outdeptRecordDao;

	/**
	 * 得到审批流程图(出科)
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年4月17日
	 */
	public Map<String, Object> getFlowChart(HttpServletRequest req) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("success", true);
		
		Integer stuId=Integer.parseInt(req.getParameter("stuId"));   //获取学生id
		OutdeptRecord outdeptRecord=new OutdeptRecord();
		outdeptRecord.setState("Y");
		outdeptRecord.setStu_auth_id(stuId);
		
		LoginBean loginBean=(LoginBean)req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer orgaId=loginBean.getvUserDetailInfo().getOrga_id();//科室id
		outdeptRecord.setOrga_id(orgaId);
		outdeptRecord.setOrderCondition(" o.exam_datetime");
		List<OutdeptRecord> lists=outdeptRecordDao.selectFlowList(outdeptRecord);
		map.put("lists", lists);
		return map;
	}

	/**
	 * 查询学生在本科室的最后一条出科审批记录是否为重新发起审核状态0
	 * @param outdeptRecord
	 * @return OutdeptRecord
	 * @author guocc
	 * @date 2017年5月15日
	 */
	public OutdeptRecord selectExamResult(OutdeptRecord outdeptRecord) {
		return outdeptRecordDao.selectExamResult(outdeptRecord);
	}
	/**
	 * 获取出科审核记录的组次
	 * @param outdeptRecord
	 * @return List<OutdeptRecord>
	 * @author guocc
	 * @date 2017年5月15日
	 */
	public List<OutdeptRecord> getGroups(OutdeptRecord outdeptRecord) {
		return outdeptRecordDao.getGroups(outdeptRecord);
	}
	/**
	 * 重新发起出科申请，将驳回的-1改为0
	 * @param req
	 * @return
	 *@author guocc
	 *@date 2017年5月15日
	 */
	public Object applyForGraduateCheckAgain(HttpServletRequest req) {
		String or_id = req.getParameter("or_id");
		String exam_result = req.getParameter("exam_result");
		if (or_id != null && !or_id.equals("") && exam_result != null && !exam_result.equals("")) {
			OutdeptRecord outdeptRecord = new OutdeptRecord();
			outdeptRecord.setId(Integer.parseInt(or_id));
			outdeptRecord.setExam_result(Integer.parseInt(exam_result));
			int num = outdeptRecordDao.update(outdeptRecord);
			if (num > 0) {
				return StringUtil.returns(true, "操作成功！");
			}else {
				return StringUtil.returns(true, "操作失败！");
			}
		}
		return StringUtil.returns(true, "操作失败！");
	}  

}
