package com.rensu.education.hctms.teach.service;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.basicdata.bean.ProcessInfo;
import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.log.bean.ProcessRecord;
import com.rensu.education.hctms.log.dao.ProcessRecordDao;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.message.bean.MessagePublish;
import com.rensu.education.hctms.message.bean.MessageReceive;
import com.rensu.education.hctms.message.dao.MessagePublishDao;
import com.rensu.education.hctms.message.dao.MessageReceiveDao;
import com.rensu.education.hctms.teach.bean.ApprovalOpinion;
import com.rensu.education.hctms.teach.bean.ReviewLeaveInfo;
import com.rensu.education.hctms.teach.bean.StuAttendanceInfo;
import com.rensu.education.hctms.teach.bean.StuVacateInfo;
import com.rensu.education.hctms.teach.bean.TrainPlan;
import com.rensu.education.hctms.teach.dao.ReviewLeaveDao;
import com.rensu.education.hctms.teach.dao.StuAttendanceInfoDao;
import com.rensu.education.hctms.teach.dao.StuVacateInfoDao;
import com.rensu.education.hctms.teach.dao.TrainPlanDao;
import com.rensu.education.hctms.userauth.bean.StudentInfo;
import com.rensu.education.hctms.userauth.bean.UserInfo;
import com.rensu.education.hctms.userauth.dao.StudentInfoDao;
import com.rensu.education.hctms.userauth.dao.UserInfoDao;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.DateUtil;
import com.rensu.education.hctms.util.StringUtil;


@Service("reviewLeaveService")
public class ReviewLeaveService extends BaseService<ReviewLeaveInfo> {
	
	Logger log = Logger.getLogger(ReviewLeaveService.class);
	
	@Autowired
	protected ReviewLeaveDao reviewLeaveDao;
	@Autowired
	protected TrainPlanDao trainPlanDao; 
	@Autowired
	protected MessagePublishDao messagePublishDao;
	@Autowired
	protected MessageReceiveDao messageReceiveDao;
	@Autowired
	protected ProcessRecordDao processRecordDao;
	@Autowired
	protected StuVacateInfoDao stuVacateInfoDao;
	@Autowired
	protected StuAttendanceInfoDao stuAttendanceInfoDao;
	@Autowired
	protected StudentInfoDao studentInfoDao;
	@Autowired
	protected UserInfoDao userInfoDao;
	@Autowired
	protected StuVacateInfoService stuVacateInfoService;
	
	/**
	 * 查询待审批的请假申请
	 * @param pageIndex
	 * @param rows
	 * @param reviewLeaveInfo
	 * @return JSONObject
	 * @author guocc
	 * @date 2017年2月28日
	 */
	public JSONObject selectReviewLeave(int pageIndex, int rows,ReviewLeaveInfo reviewLeaveInfo) {
		JSONObject jobj = new JSONObject();
		Integer proc_num = -5;
		Integer end_proc_num = -5;
		String role = reviewLeaveInfo.getRole();  //
		if(role != null && role.equals("teacher")){
			proc_num = 1;
			end_proc_num = 1;
		}else if(role != null && role.equals("director")){
			proc_num = 2;
			end_proc_num = 2;
		}
		reviewLeaveInfo.setProc_num(proc_num);
		reviewLeaveInfo.setEnd_proc_num(end_proc_num);
		Integer id1 = reviewLeaveDao.selectProcessInfoState(reviewLeaveInfo);//判断该角色是否在本审批流程中有启用
		if(id1 == null){
			jobj.put("total", StringUtil.getMaxInt(0, rows));// 总页数
			jobj.put("page", pageIndex);// 当前页码
			jobj.put("records", 0);// 总的记录数
			jobj.put("rows", null);// 显示的具体数据，jsonarray格式。
			return jobj;
		}
		String state = reviewLeaveInfo.getState();
		if(state != null && state.equals("Y")){//查询待审批的
			Integer id2 = reviewLeaveDao.getFlagOfIsFrist(reviewLeaveInfo);//判断本角色的审批前是否配置有其他审批流程，有需先审批前面的
			if(role != null && role.equals("teacher")){
				if(id2 == null){
					proc_num = 0;
				}
				reviewLeaveInfo.setProc_num(proc_num);
			}else if(role != null && role.equals("director")){
				if(id2 == null){
					proc_num = 0;
				}else{
					proc_num = 1;
				}
				reviewLeaveInfo.setProc_num(proc_num);
			}
		}else{//查询已近审批的
			proc_num = null;
			reviewLeaveInfo.setProc_num(proc_num);
		}
		List<ReviewLeaveInfo> dataList = reviewLeaveDao.selectReviewLeave(
				new RowBounds((pageIndex - 1) * rows, pageIndex * rows),
				reviewLeaveInfo);
		int totalCount = reviewLeaveDao.selectReviewLeaveCount(reviewLeaveInfo);
		jobj.put("total", StringUtil.getMaxInt(totalCount, rows));// 总页数
		jobj.put("page", pageIndex);// 当前页码
		jobj.put("records", totalCount);// 总的记录数
		jobj.put("rows", dataList);// 显示的具体数据，jsonarray格式。
		return jobj;
	}
	
	/***
     * 根据ID查询待审批的申请详情
     * @param req
     * @return
     * @author guocc
     * @date 2017年3月16日
     */
	public Object selectReviewLeaveById(HttpServletRequest req) {
		String id = req.getParameter("id");
		ReviewLeaveInfo reviewLeaveInfo = new ReviewLeaveInfo();
		if (id != null && !id.equals("")) {
			reviewLeaveInfo.setId(Integer.parseInt(id));
		}
		ReviewLeaveInfo reviewLeaveInfomation = reviewLeaveDao.selectReviewLeaveById(reviewLeaveInfo);
		return StringUtil.returns(true, reviewLeaveInfomation);
	}
	
	/***
     * 审批请假申请
     * @param req
     * @return
     * @author guocc
     * @date 2017年3月16日
     */
	public Object submitReviewLeave(HttpServletRequest req) {
		String id = req.getParameter("id");
		String flag = req.getParameter("flag");
		String role = req.getParameter("role");
		Integer end_proc_num = -5;
		if(role != null && role.equals("teacher")){
			end_proc_num = 1;
		}else if(role != null && role.equals("director")){
			end_proc_num = 2;
		}
		String proc_content = req.getParameter("proc_content");
		ApprovalOpinion approvalOpinion = new ApprovalOpinion();
		if(id != null && !id.equals("")){
			approvalOpinion.setId(Integer.parseInt(id));
		}
		approvalOpinion.setEnd_proc_num(end_proc_num);
		approvalOpinion.setProc_content(proc_content);
		Date date = new Date();
		Timestamp create_time = new Timestamp((date.getTime())); 
		approvalOpinion.setCreate_time(create_time);
		LoginBean loginBean = (LoginBean)req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer create_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
		String user_name = loginBean.getvUserDetailInfo().getUser_name();
		String orga_name = loginBean.getvUserDetailInfo().getOrga_name();
		approvalOpinion.setCreate_auth_id(create_auth_id);
		ReviewLeaveInfo reviewLeaveInfomation = new ReviewLeaveInfo();
		ReviewLeaveInfo reviewLeaveInfo = new ReviewLeaveInfo();
		reviewLeaveInfo.setId(Integer.parseInt(id));
		reviewLeaveInfo.setCreate_auth_id(create_auth_id);
		reviewLeaveInfo.setEnd_proc_num(end_proc_num);
		reviewLeaveInfomation = reviewLeaveDao.selectReviewLeaveById(reviewLeaveInfo);
		int proc_result = 0;
		int num = 0;
		int proc_num = 2;
		int mpId = 0;
		String title = "";
		// 1、往消息发布表插入数据（MESSAGE_PUBLISH）
		MessagePublish mp = new MessagePublish();
		mpId = messagePublishDao.getSequence();
		mp.setId(mpId);
		mp.setContent(proc_content);
		mp.setSend_time(create_time);
		mp.setSender_auth_id(create_auth_id);
		mp.setState("Y");
		mp.setType_id(2);
		if(flag != null && flag.equals("yes")){
			proc_result = 1;
			approvalOpinion.setProc_result(proc_result);
			num = reviewLeaveDao.submitAgree(approvalOpinion)==0?
					reviewLeaveDao.submitAgree(approvalOpinion):reviewLeaveDao.updateStuVacateState(approvalOpinion)==0?
							reviewLeaveDao.updateStuVacateState(approvalOpinion):reviewLeaveDao.updateProcessUserRelaState(approvalOpinion);
			
			ProcessInfo processInfoOfDirector = reviewLeaveDao.getVacateProcessOfTeacher(proc_num);
			List<Integer> authIdList = new ArrayList<Integer>();
			List<Integer> roleIdList = new ArrayList<Integer>();
			MessageReceive mr = new MessageReceive();
			mr.setId(messageReceiveDao.getSequence());
			mr.setProgress_state(0);
			mr.setState("Y");
			mr.setMp_id(mpId);
			if(role != null && role.equals("teacher")){//老师才去做判断
				if(processInfoOfDirector != null){
					title = reviewLeaveInfomation.getStu_name()  + "【" + reviewLeaveInfomation.getOrga_name() + "】" +  "发起了请假申请，请及时审批！";
					mp.setTitle(title);
					messagePublishDao.add(mp);                     //发布表
					roleIdList = reviewLeaveDao.selectRoleId(proc_num);
					for (Integer roleId : roleIdList){
						authIdList = reviewLeaveDao.selectAuthId(roleId);	
						for (Integer authId : authIdList) {
							mr.setReceiver_auth_id(authId);
							messageReceiveDao.add(mr);            //接收表
						}
					}
				}else{
					title = user_name  + "【" + orga_name + "】" +  "批准了你的请假申请！";
					mp.setTitle(title);
					messagePublishDao.add(mp);                     //发布表
					mr.setReceiver_auth_id((reviewLeaveDao.selectOne(Integer.parseInt(id)).getCreate_auth_id()));
					messageReceiveDao.add(mr);            //接收表
				}
			}else{
				title = user_name  + "【" + orga_name + "】" +  "批准了你的请假申请！";
				mp.setTitle(title);
				messagePublishDao.add(mp);                     //发布表
				mr.setReceiver_auth_id((reviewLeaveDao.selectOne(Integer.parseInt(id)).getCreate_auth_id()));
				messageReceiveDao.add(mr);            //接收表
			}
			
			if(num > 0){
				return StringUtil.returns(true, "操作成功");
			}else{
				return StringUtil.returns(false, "操作失败");
			}
		}
		int num1 = 0;
		if(flag != null && flag.equals("no")){
			proc_result = -1;
			approvalOpinion.setProc_result(proc_result);
			num1 = reviewLeaveDao.submitDisagree(approvalOpinion)==0?
					reviewLeaveDao.submitDisagree(approvalOpinion):reviewLeaveDao.updateStuVacateStateDis(approvalOpinion)==0?
					reviewLeaveDao.updateStuVacateStateDis(approvalOpinion):reviewLeaveDao.updateProcessUserRelaState(approvalOpinion);
			
			title = user_name  + "【" + orga_name + "】" +  "驳回了你的请假申请！";
			mp.setTitle(title);
			messagePublishDao.add(mp);                     //发布表		
			MessageReceive mr = new MessageReceive();
			mr.setId(messageReceiveDao.getSequence());
			mr.setProgress_state(0);
			mr.setState("Y");
			mr.setMp_id(mpId);
			mr.setReceiver_auth_id((reviewLeaveDao.selectOne(Integer.parseInt(id)).getCreate_auth_id()));
			messageReceiveDao.add(mr);            //接收表
			
			if(num1 > 0){
				return StringUtil.returns(true, "操作成功");
			}else{
				return StringUtil.returns(false, "操作失败");
			}
		}
		return null;
	}
	
	/***
	 * 查询请假流程详细信息
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年3月22日
	 */
	public Object selectLeaveProcessInfo(HttpServletRequest req) {
		String id = req.getParameter("id");
		ReviewLeaveInfo reviewLeaveInfo = new ReviewLeaveInfo();
		if(id!=null&&!id.equals("")){
			reviewLeaveInfo.setId(Integer.parseInt(id));
			List<ReviewLeaveInfo> list=reviewLeaveDao.selectLeaveProcessInfo(reviewLeaveInfo);
			if(list!=null||!list.equals("")){
				StudentInfo studentInfo=studentInfoDao.selectStuNumByAuthId(list.get(0).getStu_auth_id());
				String stuNum=studentInfo.getStuNum();
				list.get(0).setStu_num(stuNum);
			}
			
				return StringUtil.returns(true, list);
		}else{
			return StringUtil.returns(false, "操作异常!");
	}   } 
   
   /***
	 * 查询请假申请的当前状态
	 * @param id
	 * @return Integer
	 * @author guocc
	 * @date 2017年3月22日
	 */
	public Integer selectVacateStatus(int id) {
		return reviewLeaveDao.selectVacateStatus(id);
	} 
	
	public Integer getApprNum(HttpServletRequest req) {
		//从缓存中得到stu_auth_id
		LoginBean loginBean = (LoginBean)req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer orga_id = loginBean.getvUserDetailInfo().getOrga_id();
		Integer role_id = loginBean.getvUserDetailInfo().getRole_id();
		StuVacateInfo stuVacateInfo = new StuVacateInfo();
		stuVacateInfo.setQueryCondition("and t.vacate_status=61 and p.proc_result is null");
		stuVacateInfo.setOrga_id(orga_id);
		stuVacateInfo.setEnd_proc_id(63);
		stuVacateInfo.setRole_id(role_id);
		JSONObject json=stuVacateInfoService.selectVacateList(1, 10000, stuVacateInfo);
		int totalCount = json.getInt("records");
		return totalCount;
	}

	public Object submitReviewLeaveRewrite(HttpServletRequest req) throws Exception {
		String id = req.getParameter("id");
		String flag = req.getParameter("flag");
		String proc_content = req.getParameter("proc_content");
		String stu_name=req.getParameter("stu_name");
		String state_time = req.getParameter("state_time");
		String days = req.getParameter("days");
		if (id != null && !id.equals("") && flag != null && !flag.equals("") && stu_name != null && !stu_name.equals("") && 
				state_time != null && !state_time.equals("") && days != null && !days.equals("")) {
			state_time=state_time.replace("-","");
			int day =0;
			if (days.equals("-10") || days.equals("-20")) {
				day = 1;
			}else{
				day = Integer.parseInt(days);
			}
			LoginBean loginBean = (LoginBean)req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
			Integer auth_id=loginBean.getvUserDetailInfo().getAuth_id();// 角色权限id
			String orga_name = loginBean.getvUserDetailInfo().getOrga_name();
			String user_name = loginBean.getvUserDetailInfo().getUser_name();
			Integer role_id = loginBean.getvUserDetailInfo().getRole_id();
			String title = "";
			List<UserInfo> userInfoList = new ArrayList<UserInfo>();
			StuVacateInfo stuVacateInfo = stuVacateInfoDao.selectOne(Integer.parseInt(id));
			Integer stu_auth_id = 0;
			Integer orga_id = 0;
			if (stuVacateInfo != null) {
				stu_auth_id = stuVacateInfo.getStu_auth_id();
				orga_id = stuVacateInfo.getOrga_id();
			}
			int vacate_status = 0;
			if (role_id == 20) {
				vacate_status = 62;
			}else if (role_id == 30) {
				vacate_status = 63;
			}else if (role_id == 50) {
				vacate_status = 64;
			}
			//
			StuVacateInfo stuVacateupdate = new StuVacateInfo();
			stuVacateupdate.setId(Integer.parseInt(id));
			//
			ProcessRecord processRecord1 = new ProcessRecord();
			processRecord1.setProc_content(proc_content);
			processRecord1.setCreate_auth_id(auth_id);
			Date date= new Date();
			Timestamp create_time = new Timestamp((date.getTime())); 
			processRecord1.setCreate_time(create_time);
			ProcessRecord pr = new ProcessRecord();
			pr.setEnd_proc_id(vacate_status);
			pr.setRelated_id(Integer.parseInt(id));
			List<ProcessRecord> prList = processRecordDao.selectList(pr);
			if (prList != null && !prList.isEmpty()) {
				processRecord1.setId(prList.get(0).getId());
			}
			if(flag.equals("yes")){//  批准请假
				ProcessRecord processRecord = new ProcessRecord();
				processRecord.setRelated_id(Integer.parseInt(id));
				List<ProcessRecord> processList=processRecordDao.selectListByRelatedId(processRecord);
				if (processList != null && !processList.isEmpty()) {
					if(vacate_status == processList.get(processList.size()-1).getEnd_proc_id()) {//  到了最后一个请假审批环节
						vacate_status = 0;
						stuVacateupdate.setVacate_status(vacate_status);
						int num1 = stuVacateInfoDao.update(stuVacateupdate);
						//
						processRecord1.setProc_result(1);
						int num2 = processRecordDao.update(processRecord1);
						//
						if(num1>0&&num2>0){
							//添加考勤信息
							StuAttendanceInfo stuAttendanceInfo=new StuAttendanceInfo();
							stuAttendanceInfo.setStu_auth_id(stu_auth_id);
							stuAttendanceInfo.setOrga_id(orga_id);
							stuAttendanceInfo.setState("Y");
							for(int j = 0 ; j<day; j++){
								String start_date= DateUtil.addDate(state_time+"000000", j, "yyyyMMdd");								
								stuAttendanceInfo.setAttend_date(Integer.parseInt(start_date));
								//将原来重复数据删除
								List<StuAttendanceInfo> stuAttendanceInfoList = stuAttendanceInfoDao.selectList(stuAttendanceInfo);
								for (StuAttendanceInfo sai : stuAttendanceInfoList) {
									if(sai.getAttend_state() != -10 && sai.getAttend_state() != -20) {
										stuAttendanceInfoDao.delete(sai);
									}else if (sai.getAttend_state() == -10 && !days.equals("-20")) {
										stuAttendanceInfoDao.delete(sai);
									}else if (sai.getAttend_state() == -20 && !days.equals("-10")) {
										stuAttendanceInfoDao.delete(sai);
									}
								}
							}
							stuAttendanceInfo.setAttend_auth_id(auth_id);
							if (days != null && days.equals("-10")) {
								stuAttendanceInfo.setAttend_state(-10);
							}else if (days != null && days.equals("-20")) {
								stuAttendanceInfo.setAttend_state(-20);
							}else if (days != null) {
								stuAttendanceInfo.setAttend_state(2);
							}
							for(int j = 0 ; j<day; j++){
								stuAttendanceInfo.setId(stuAttendanceInfoDao.getSequence());
								String start_date= DateUtil.addDate(state_time+"000000", j, "yyyyMMdd");	
								stuAttendanceInfo.setAttend_date(Integer.parseInt(start_date));
								String start_date_time= DateUtil.addDate(state_time+"000000", j, "yyyy-MM-dd HH:mm:ss");
								stuAttendanceInfo.setAttend_time(Timestamp.valueOf(start_date_time));
								stuAttendanceInfoDao.add(stuAttendanceInfo);
							}
						}
						//
						title = user_name  + "【" + orga_name + "】" +  "批准了你的请假申请！";
				}else{
					stuVacateupdate.setVacate_status(vacate_status);
					stuVacateInfoDao.update(stuVacateupdate);
					//
					processRecord1.setProc_result(1);
					processRecordDao.update(processRecord1);
					//
					title = stu_name  + "【" + orga_name + "】" +  "发起了请假申请，请及时审批！";
					int end_proc_id = 0;
					ProcessRecord pr1 = new ProcessRecord();
					pr1.setStart_proc_id(vacate_status);
					pr1.setRelated_id(Integer.parseInt(id));
					List<ProcessRecord> prList1 = processRecordDao.selectList(pr1);
					if (prList1 != null && !prList1.isEmpty()) {
						end_proc_id = prList1.get(0).getEnd_proc_id();
					}
					UserInfo userInfo = new UserInfo();
					userInfo.setState("Y");
					if (end_proc_id == 63) {
						userInfo.setRole_id(30);
						userInfo.setOrga_id(orga_id);
					}else if (end_proc_id == 64) {
						userInfo.setRole_id(50);
					}
					userInfoList = userInfoDao.selectOrgaTea(userInfo);
					}
				}
			}else if(flag.equals("no")){//  请假审批不通过
				vacate_status = 65;
				stuVacateupdate.setVacate_status(vacate_status);
				stuVacateInfoDao.update(stuVacateupdate);
				//
				processRecord1.setProc_result(-1);
				processRecordDao.update(processRecord1);
				//
				title = user_name  + "【" + orga_name + "】" +  "驳回了你的请假申请！";
			}
			// 1、往消息发布表插入数据（MESSAGE_PUBLISH）
			MessagePublish mp = new MessagePublish();
			int mpId = messagePublishDao.getSequence();
			mp.setId(mpId);
			mp.setTitle(title);
			mp.setContent(proc_content);
			mp.setSender_auth_id(auth_id);
			mp.setSend_time(create_time);
			mp.setType_id(2);
			mp.setRelated_table("MESSAGE_RECEIVE");
			mp.setState("Y");
			messagePublishDao.add(mp);
			//2、往消息接收表插入数据（MESSAGE_RECEIVE）
			MessageReceive mr = new MessageReceive();
			mr.setProgress_state(0);
			mr.setMp_id(mpId);
			mr.setState("Y");
			if(userInfoList != null && !userInfoList.isEmpty()){
				for (UserInfo userInfo : userInfoList) {
					mr.setId(messageReceiveDao.getSequence());
					mr.setReceiver_auth_id(userInfo.getAuth_id());
					messageReceiveDao.add(mr);            //接收表
				}
			}else{
				mr.setId(messageReceiveDao.getSequence());
				mr.setReceiver_auth_id(stu_auth_id);
				messageReceiveDao.add(mr);            //接收表
			}
			return StringUtil.returns(true, "操作成功...");
		}else{
			return StringUtil.returns(false, "操作失败！");
		}
	}
}
