package com.rensu.education.hctms.score.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.message.bean.MessagePublish;
import com.rensu.education.hctms.message.bean.MessageReceive;
import com.rensu.education.hctms.message.dao.MessagePublishDao;
import com.rensu.education.hctms.message.dao.MessageReceiveDao;
import com.rensu.education.hctms.score.bean.ScoEval;
import com.rensu.education.hctms.score.dao.ScoEvalDao;
import com.rensu.education.hctms.teach.bean.MedicalCaseMain;
import com.rensu.education.hctms.teach.bean.MedicalDiagnose;
import com.rensu.education.hctms.teach.bean.TaskDeal;
import com.rensu.education.hctms.teach.bean.TaskPublish;
import com.rensu.education.hctms.teach.dao.TaskDealDao;
import com.rensu.education.hctms.teach.dao.MedicalCaseMainDao;
import com.rensu.education.hctms.teach.dao.TaskPublishDao;
import com.rensu.education.hctms.teach.service.TaskDealService;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.StringUtil;


@Service("scoEvalService")
public class ScoEvalService extends BaseService<ScoEval> {
	
	Logger log = Logger.getLogger(ScoEvalService.class);
	
	@Autowired
	protected ScoEvalDao scoEvalDao;
    @Autowired
    protected TaskDealService taskDealService;
    @Autowired
    protected TaskPublishDao taskPublishDao;
    @Autowired
    protected MessagePublishDao messagePublishDao; 
    @Autowired
    protected MessageReceiveDao messageReceiveDao;
    @Autowired
    protected TaskDealDao taskDealDao;
    @Autowired
    protected MedicalCaseMainDao medicalCaseMainDao;
    
	public Object saveScoTeach(HttpServletRequest req) {
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer auth_id = loginBean.getvUserDetailInfo().getAuth_id();
		String user_name = loginBean.getvUserDetailInfo().getUser_name();
		String orga_name = loginBean.getvUserDetailInfo().getOrga_name();
		  int num1=0;
		  int num2=0;
		  int num3=0;
		 String id=req.getParameter("id");
		 String content=req.getParameter("content");
		 String sco=req.getParameter("sco");
		 TaskDeal taskDeal=new TaskDeal();
		 TaskPublish taskPublish=new TaskPublish();
		 ScoEval scoEval=new ScoEval();
		 if(id!=null&&!id.equals("")){
			taskDeal=taskDealService.selectOne(Integer.parseInt(id));
			taskPublish=taskPublishDao.selectOne(taskDeal.getTp_id());
			scoEval.setId(scoEvalDao.getSequence());
			scoEval.setType_id(10);
			scoEval.setRelation_id(taskDeal.getId());
			scoEval.setRelation_table("TASK_DEAL");
			scoEval.setStu_orga_id(Integer.parseInt(taskPublish.getOrga_id()));
			scoEval.setEval_auth_id(taskDeal.getReceiver_auth_id().toString());
			Date date = new Date();
		    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = format.format(date);
			Timestamp evalTime = Timestamp.valueOf(time);
			scoEval.setEval_time(evalTime);
			if(content!=null&&!content.equals("")){
				scoEval.setEval_content(content);
			}
		    SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");  
		    scoEval.setEval_date(Integer.parseInt( sf.format(date)));
		    if(sco!=null&&!sco.equals("")){
		    	scoEval.setEval_score(Integer.parseInt(sco));
		    }
		    scoEval.setEval_auth_id(taskPublish.getPublisher_auth_id());
		    scoEval.setStu_auth_id(taskDeal.getReceiver_auth_id().toString());
		    scoEval.setState("Y");
		    num1=scoEvalDao.add(scoEval);
		    taskDeal.setProgress_state("3");
		    num2=taskDealService.update(taskDeal);
		    taskPublish.setProgress_state("3");
		    num3=taskPublishDao.update(taskPublish);
		    int mpId = 0;
			String title = user_name  + "【" + orga_name + "】" +  "提交了作业评分，请及时查看！";
			// 1、往消息发布表插入数据（MESSAGE_PUBLISH）
		    MessagePublish mp = new MessagePublish();
			mpId = messagePublishDao.getSequence();
			mp.setId(mpId);
			mp.setTitle(title);
			mp.setContent(content);
			mp.setSend_time(evalTime);
			mp.setSender_auth_id(auth_id);
			mp.setState("Y");
			mp.setType_id(2);
			messagePublishDao.add(mp);                     //发布表
			MessageReceive mr = new MessageReceive();
			mr.setProgress_state(0);
			mr.setState("Y");
			mr.setMp_id(mpId);
			mr.setReceiver_auth_id(taskDeal.getReceiver_auth_id());
			mr.setId(messageReceiveDao.getSequence());
			messageReceiveDao.add(mr);         
		 }
		 if(num1>0&&num2>0&&num3>0){
	        	return  StringUtil.returns(true, "操作成功!");
	        }else{
	        	return  StringUtil.returns(false, "操作失败!");
	        }
	        
	}

	/**
	 * 提交病例讨论老师对学生评价
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年9月6日
	 */
	public Object submitGrade(HttpServletRequest req) {
		//
		String id = req.getParameter("id");
		String data = req.getParameter("data");
		if (id != null && !id.equals("") && data != null && !data.equals("")) {
			LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
			Integer eval_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
			Integer orga_id = loginBean.getvUserDetailInfo().getOrga_id();
			//更新MEDICAL_CASE_MAIN表状态
			MedicalCaseMain medicalCaseMain = new MedicalCaseMain();
			medicalCaseMain.setId(Integer.parseInt(id));
			Date date = new Date();
			medicalCaseMain.setFinish_time(new Timestamp(date.getTime()));
			medicalCaseMain.setStatus("4");//  评分结束
			int num = medicalCaseMainDao.update(medicalCaseMain);
			//给SCO_EVAL表添加评论
			ScoEval scoEval = new ScoEval();
			scoEval.setRelation_id(Integer.parseInt(id));
			scoEval.setRelation_table("MEDICAL_CASE_MAIN");
			scoEval.setStu_orga_id(orga_id);//  科室可能有问题！
			scoEval.setEval_auth_id(eval_auth_id.toString());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");  
		    scoEval.setEval_date(Integer.parseInt(sdf.format(date)));
			scoEval.setEval_time(new Timestamp(date.getTime()));
			scoEval.setState("Y");
			JSONArray jsonArray = JSONArray.fromObject(data);
			List<ScoEval> list = JSONArray.toList(jsonArray, ScoEval.class);
			for (ScoEval se : list) {
				scoEval.setId(scoEvalDao.getSequence());
				if (se.getAuth_type() == 10) {
					scoEval.setType_id(30);
				}else if (se.getAuth_type() == 20) {
					scoEval.setType_id(31);
				}
				scoEval.setStu_auth_id(se.getStu_auth_id());
				scoEval.setEval_content(se.getEval_content());
				scoEval.setEval_score(se.getEval_score());
				scoEvalDao.add(scoEval);
			}
			if (num > 0) {
				return StringUtil.returns(true, "操作成功...");
			}else{
				return StringUtil.returns(false, "操作失败！");
			}
		}else{
			return StringUtil.returns(false, "操作失败！");
		}
	}
}
