package com.rensu.education.hctms.score.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.basicdata.bean.ProcessInfo;
import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.log.bean.OutdeptRecord;
import com.rensu.education.hctms.log.dao.OutdeptRecordDao;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.message.bean.MessagePublish;
import com.rensu.education.hctms.message.bean.MessageReceive;
import com.rensu.education.hctms.message.dao.MessagePublishDao;
import com.rensu.education.hctms.message.dao.MessageReceiveDao;
import com.rensu.education.hctms.score.bean.ScoFormDetail;
import com.rensu.education.hctms.score.bean.ScoFormMain;
import com.rensu.education.hctms.score.dao.ScoFormDetailDao;
import com.rensu.education.hctms.score.dao.ScoFormMainDao;
import com.rensu.education.hctms.teach.bean.TrainPlan;
import com.rensu.education.hctms.teach.dao.TrainPlanDao;
import com.rensu.education.hctms.userauth.bean.UserAuthority;
import com.rensu.education.hctms.userauth.dao.UserAuthorityDao;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.StringUtil;


@Service("scoFormDetailService")
public class ScoFormDetailService extends BaseService<ScoFormDetail> {
	
	Logger log = Logger.getLogger(ScoFormDetailService.class);
	
	@Autowired
	protected ScoFormDetailDao scoFormDetailDao;
	@Autowired
	protected TrainPlanDao trainPlanDao; 
	@Autowired
	protected OutdeptRecordDao outdeptRecordDao;  
	@Autowired
	protected ScoFormMainDao scoFormMainDao;
	@Autowired
	protected MessagePublishDao messagePublishDao;
	@Autowired
	protected UserAuthorityDao userAuthorityDao;
	@Autowired
	protected MessageReceiveDao messageReceiveDao;
	/**
	 * 出科审核   出科
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年4月24日
	 */
	public Object applyForGraduateCheck(HttpServletRequest req ){
		String form_id_str = req.getParameter("form_id_str");
		//从session中获取当前用户、科室和所属附属医院id
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer stu_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
		Integer orga_id = loginBean.getvUserDetailInfo().getOrga_id();
		
		String stu_name = loginBean.getvUserDetailInfo().getUser_name();
		String orga_name = loginBean.getvUserDetailInfo().getOrga_name();
		String train_dept_id = orga_id.toString();
//		Integer hos_id = loginBean.getvUserDetailInfo().getHos_id();
		//更新TRAIN_PLAN表轮转状态
		TrainPlan trainPlan = new TrainPlan();
		trainPlan.setStu_auth_id(stu_auth_id);
		trainPlan.setTrain_dept_id(train_dept_id);
//		trainPlan.setHos_id(hos_id);
		int train_status = Consts.ProcessInfo.TRAINPROCESS2;
		trainPlan.setTrain_status(train_status);
		trainPlan.setQueryCondition("and train_status in (53,54,55,56,57)");
		int num1 = trainPlanDao.updateTrainStatus(trainPlan);
		//往OUTDEPT_RECORD表写入审核记录
		List<ProcessInfo> processList = (List<ProcessInfo>)req.getSession().getServletContext().getAttribute(Consts.SESSION_PROCESS_INFO);
		List<ProcessInfo> processInfoList = new ArrayList<ProcessInfo>();
		for (ProcessInfo processInfo : processList) {
			if(processInfo.getType_code().equals("TRAINPROCESS") && processInfo.getId() != Consts.ProcessInfo.TRAINPROCESSNull && 
					processInfo.getId() != Consts.ProcessInfo.TRAINPROCESS0 && processInfo.getId() != Consts.ProcessInfo.TRAINPROCESS1 && 
					processInfo.getId() != Consts.ProcessInfo.TRAINPROCESS6 && processInfo.getId() != Consts.ProcessInfo.TRAINPROCESS2){
				processInfoList.add(processInfo);
			}
		}
		Collections.sort(processInfoList, new Comparator<ProcessInfo>() {
	          public int compare(ProcessInfo arg0, ProcessInfo arg1) {
		          Integer code0 = arg0.getProc_num();
		          Integer code1 = arg1.getProc_num();
		          return code0.compareTo(code1);
	          }
		});
		//获取组次
		OutdeptRecord ord=new OutdeptRecord(); 
		ord.setStu_auth_id(stu_auth_id);
		ord.setOrga_id(orga_id);
		ord.setOrderCondition(" pub_num desc");
		OutdeptRecord outdeptRecordGet = outdeptRecordDao.getLastPubNum(ord);//  获取在本科室学生最后一次发起出科的组次
		Integer pub_num = 0;
		if (outdeptRecordGet != null) {
			pub_num = outdeptRecordGet.getPub_num() + 1;
		}else {
			pub_num = 1;
		}
		//生成学生发起出科的出科审核记录
		OutdeptRecord outdeptRecord = new OutdeptRecord();
		int or_id = outdeptRecordDao.getSequence();
		outdeptRecord.setId(or_id);
		outdeptRecord.setStu_auth_id(stu_auth_id);
		outdeptRecord.setExam_auth_id(stu_auth_id);
		outdeptRecord.setExam_datetime(new Timestamp((new Date()).getTime()));
		outdeptRecord.setOrga_id(orga_id);
		outdeptRecord.setExam_content("申请出科");
		Integer exam_role_id = loginBean.getvUserDetailInfo().getRole_id();
		outdeptRecord.setExam_role_id(exam_role_id);//  10
		outdeptRecord.setState("Y");
		outdeptRecord.setPub_num(pub_num);
		outdeptRecord.setExam_result(1);
		int num2 = outdeptRecordDao.add(outdeptRecord);
		//根据轮转流程配置生成出科审核轨迹
		for (ProcessInfo processInfo : processInfoList) {
			OutdeptRecord or = new OutdeptRecord();
			or.setId(outdeptRecordDao.getSequence());
			or.setStu_auth_id(stu_auth_id);
			if (processInfo.getId() == Consts.ProcessInfo.TRAINPROCESS3) {//  默认是老师角色，等到教秘审核时改为30
				or.setExam_role_id(20);
			}else if (processInfo.getId() == Consts.ProcessInfo.TRAINPROCESS4) {
				or.setExam_role_id(40);
			}else if (processInfo.getId() == Consts.ProcessInfo.TRAINPROCESS5) {
				or.setExam_role_id(50);
			}
			or.setOrga_id(orga_id);
			or.setState("Y");
			or.setPub_num(pub_num);
			outdeptRecordDao.add(or);
		}
		//给SCO_FORM_MAIN表的or_id字段添加数据
		int sfm_id = 0;
		ScoFormMain scoFormMain = new ScoFormMain();
		scoFormMain.setUser_auth_id(stu_auth_id);
		scoFormMain.setCreate_auth_id(stu_auth_id);
		scoFormMain.setOrga_id(orga_id);
		if (form_id_str != null && !form_id_str.equals("")) {
			String[] form_id_strs = form_id_str.split("-");
			for (int i = 0; i < form_id_strs.length; i++) {
				scoFormMain.setForm_id(Integer.parseInt(form_id_strs[i]));
				ScoFormMain scoFormM = scoFormMainDao.getSFMId(scoFormMain);
				if (scoFormM != null) {
					sfm_id = scoFormM.getId();
					ScoFormMain sfm = new ScoFormMain();
					sfm.setId(sfm_id);
					sfm.setOr_id(or_id);
					scoFormMainDao.update(sfm);
				}
			}
		}
		if(num1 > 0 && num2 > 0){
			// 往消息发布表插入数据（MESSAGE_PUBLISH）
			MessagePublish mp = new MessagePublish();
			int mpId = messagePublishDao.getSequence();
			mp.setId(mpId);
			String title = stu_name  + "【" + orga_name + "】" +  "发起了出科申请，请及时审批！";
			mp.setTitle(title);
			mp.setContent("申请出科");
			mp.setSender_auth_id(stu_auth_id);
			Date date = new Date();
			Timestamp create_time = new Timestamp((date.getTime()));
			mp.setSend_time(create_time);
			mp.setType_id(2);
			mp.setRelated_table("MESSAGE_RECEIVE");
			mp.setState("Y");
			messagePublishDao.add(mp); 
			// 往消息接收表插入数据（MESSAGE_RECEIVE）
			MessageReceive mr = new MessageReceive();
			mr.setProgress_state(0);
			mr.setMp_id(mpId);
			mr.setState("Y");
			@SuppressWarnings("unchecked")
			List<ProcessInfo> processSelList = new ArrayList<ProcessInfo>();
			for (ProcessInfo processInfo : processList) {
				if(processInfo.getType_code().equals("TRAINPROCESS") && processInfo.getId() != Consts.ProcessInfo.TRAINPROCESSNull && processInfo.getId() != Consts.ProcessInfo.TRAINPROCESS0 && 
						processInfo.getId() != Consts.ProcessInfo.TRAINPROCESS1 && processInfo.getId() != Consts.ProcessInfo.TRAINPROCESS2 && processInfo.getId() != Consts.ProcessInfo.TRAINPROCESS6){
					processSelList.add(processInfo);
				}
			}
			Collections.sort(processSelList, new Comparator<ProcessInfo>() {
	            public int compare(ProcessInfo arg0, ProcessInfo arg1) {
	                Integer code0 = arg0.getProc_num();
	                Integer code1 = arg1.getProc_num();
	                return code0.compareTo(code1);
	            }
	        });
			List<UserAuthority> userAuthorityList = new ArrayList<UserAuthority>();
			UserAuthority userAuthority = new UserAuthority();
			userAuthority.setOrga_id(orga_id);
			userAuthority.setState("Y");
			for (ProcessInfo pio : processSelList) {
				if (pio.getId() == Consts.ProcessInfo.TRAINPROCESS3) {
					TrainPlan tp = new TrainPlan();
					tp.setState("Y");
					tp.setStu_auth_id(stu_auth_id);
					tp.setTrain_dept_id(train_dept_id);
					List<TrainPlan> list = trainPlanDao.selectList(tp);
					if (list != null && !list.isEmpty() && list.get(0).getTeacher_auth_id() != null) {
						int teacher_auth_id = list.get(0).getTeacher_auth_id();
						int mrId=messageReceiveDao.getSequence();
						mr.setId(mrId);
						mr.setReceiver_auth_id(teacher_auth_id);
						messageReceiveDao.add(mr); 
					}
					//老师是指定的，唯一的
//					userAuthority.setRole_id(20);
//					userAuthorityList = userAuthorityDao.selectList(userAuthority);
//					for (UserAuthority userAuthorityGet : userAuthorityList) {
//						int mrId=messageReceiveDao.getSequence();
//						mr.setId(mrId);
//						mr.setReceiver_auth_id(userAuthorityGet.getAuth_id());
//						messageReceiveDao.add(mr); 
//					}
					userAuthority.setRole_id(30);
					userAuthorityList = userAuthorityDao.selectList(userAuthority);
					for (UserAuthority userAuthorityGet : userAuthorityList) {
						int mrId=messageReceiveDao.getSequence();
						mr.setId(mrId);
						mr.setReceiver_auth_id(userAuthorityGet.getAuth_id());
						messageReceiveDao.add(mr); 
					}
					break;
				}else if (pio.getId() == Consts.ProcessInfo.TRAINPROCESS4) {
					userAuthority.setRole_id(40);
					userAuthorityList = userAuthorityDao.selectList(userAuthority);
					for (UserAuthority userAuthorityGet : userAuthorityList) {
						int mrId=messageReceiveDao.getSequence();
						mr.setId(mrId);
						mr.setReceiver_auth_id(userAuthorityGet.getAuth_id());
						messageReceiveDao.add(mr); 
					}
					break;
				}else if (pio.getId() == Consts.ProcessInfo.TRAINPROCESS5) {
					userAuthority.setRole_id(50);
					userAuthorityList = userAuthorityDao.selectList(userAuthority);
					for (UserAuthority userAuthorityGet : userAuthorityList) {
						int mrId=messageReceiveDao.getSequence();
						mr.setId(mrId);
						mr.setReceiver_auth_id(userAuthorityGet.getAuth_id());
						messageReceiveDao.add(mr); 
					}
					break;
				}
			}
			return StringUtil.returns(true, "操作成功！");
		}else{
			return StringUtil.returns(false, "操作失败！");
		}
	}
}
