package com.rensu.education.hctms.teach.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.basicdata.bean.ProcessInfo;
import com.rensu.education.hctms.basicdata.bean.RoleProcRela;
import com.rensu.education.hctms.basicdata.dao.ProcessInfoDao;
import com.rensu.education.hctms.basicdata.dao.RoleProcRelaDao;
import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.log.bean.ProcessRecord;
import com.rensu.education.hctms.log.bean.ProcrecordUserRela;
import com.rensu.education.hctms.log.dao.ProcessRecordDao;
import com.rensu.education.hctms.log.dao.ProcrecordUserRelaDao;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.message.bean.MessagePublish;
import com.rensu.education.hctms.message.bean.MessageReceive;
import com.rensu.education.hctms.message.dao.MessagePublishDao;
import com.rensu.education.hctms.message.dao.MessageReceiveDao;
import com.rensu.education.hctms.teach.bean.StuVacateInfo;
import com.rensu.education.hctms.teach.bean.TrainPlan;
import com.rensu.education.hctms.teach.dao.StuVacateInfoDao;
import com.rensu.education.hctms.teach.dao.TrainPlanDao;
import com.rensu.education.hctms.userauth.bean.UserInfo;
import com.rensu.education.hctms.userauth.dao.UserAuthorityDao;
import com.rensu.education.hctms.userauth.dao.UserInfoDao;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.StringUtil;


@Service("stuVacateInfoService")
public class StuVacateInfoService extends BaseService<StuVacateInfo> {
	
	Logger log = Logger.getLogger(StuVacateInfoService.class);
	
	@Autowired
	protected StuVacateInfoDao stuVacateInfoDao;
	@Autowired
	protected TrainPlanDao trainPlanDao;
	@Autowired
	protected ProcessInfoDao processInfoDao;
	@Autowired
	protected ProcessRecordDao processRecordDao;
	@Autowired
	protected RoleProcRelaDao roleProcRelaDao;
	@Autowired
	protected ProcrecordUserRelaDao procrecordUserRelaDao;
	@Autowired
	protected UserInfoDao userInfoDao;
	@Autowired
	protected MessageReceiveDao messageReceiveDao;
	@Autowired
	protected MessagePublishDao messagePublishDao;
	@Autowired
	protected UserAuthorityDao userAuthorityDao;
	/***
	 * 提交请假申请预加载信息
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年2月6日
	 */
	public Object getAddVacateInfo(HttpServletRequest req) {
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer stu_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
	
		Integer orga_id = loginBean.getvUserDetailInfo().getOrga_id();
		ProcessInfo processInfo = new ProcessInfo();
		TrainPlan trainPlan = new TrainPlan();
		processInfo.setType_code("VACATE");
		processInfo.setState("Y");
//		processInfo.setProc_num(Integer.parseInt(proc_num));
		List<ProcessInfo> processList = processInfoDao.selectList(processInfo);
		if(processList!=null){
			Collections.sort(processList, new Comparator<ProcessInfo>() {
	            public int compare(ProcessInfo arg0, ProcessInfo arg1) {
	                Integer code0 = arg0.getProc_num();
	                Integer code1 = arg1.getProc_num();
	               return code0.compareTo(code1);
	            }
	        });	
		}
		List<RoleProcRela> roleProcRelaList= null;
		if(processList.get(2).getId()!=null){
			int end_id=processList.get(2).getId();
			roleProcRelaList =roleProcRelaDao.selectRoleId(end_id);
		}
		String name = "";
		String auth_id = "";
		TrainPlan trainPlan1=new TrainPlan();
		trainPlan1.setStu_auth_id(stu_auth_id);
		trainPlan1.setTrain_dept_id(orga_id+"");
		
		//在科
		String trainStatusStrZ = Consts.ProcessInfo.TRAINPROCESSZ + "";
		trainPlan1.setTrainStatusStrZ(trainStatusStrZ);
		List<TrainPlan> dataList=trainPlanDao.selectByAuthOrga(trainPlan1);
		for (RoleProcRela roleProcRela : roleProcRelaList) {
		if(roleProcRela.getRole_id()==20){
			for (TrainPlan trainPlans : dataList) {
					if(auth_id!=""){
						auth_id=auth_id+";"+trainPlans.getTeacher_auth_id();
						name=name+";"+trainPlans.getTeach_name();
					}else if(!trainPlans.getTeach_name().equals("暂无")){
						auth_id=auth_id+trainPlans.getTeacher_auth_id();
						name=name+trainPlans.getTeach_name();
				}
			}
		}
		if(roleProcRela.getRole_id()==30){
			//学生轮转计划不再绑定教秘id
//			for (TrainPlan trainPlans : dataList) {
//						if(auth_id!=""){
//							auth_id=auth_id+";"+trainPlans.getSecretary_auth_id();
//							name=name+";"+trainPlans.getSecretary_name();
//						}else{
//							auth_id=auth_id+trainPlans.getSecretary_auth_id();
//							name=name+trainPlans.getSecretary_name();
//						}
//			}
			UserInfo userInfo = new UserInfo();
			userInfo.setState("Y");
			userInfo.setOrga_id(orga_id);
			userInfo.setRole_id(30);
			List<UserInfo> userInfoList = userInfoDao.selectOrgaTea(userInfo);
			for (UserInfo userInfo2 : userInfoList) {
				if(auth_id!=""){
					auth_id=auth_id+";"+userInfo2.getAuth_id();
					name=name+";"+userInfo2.getUser_name();
				}else{
					auth_id=auth_id+userInfo2.getAuth_id();
					name=name+userInfo2.getUser_name();
				}
			}
		}
		if(roleProcRela.getRole_id()==50){
			UserInfo userInfo = new UserInfo();
			userInfo.setState("Y");
			userInfo.setRole_id(50);
			List<UserInfo> userInfoList = userInfoDao.selectOrgaTea(userInfo);
			for (UserInfo userInfo2 : userInfoList) {
				if(auth_id!=""){
					auth_id=auth_id+";"+userInfo2.getAuth_id();
					name=name+";"+userInfo2.getUser_name();
				}else{
					auth_id=auth_id+userInfo2.getAuth_id();
					name=name+userInfo2.getUser_name();
				}
			}
		}
		}
		trainPlan.setName(name);
		trainPlan.setAuth_ids(auth_id);
		return trainPlan;
	}
	
	
	  /***
     * 提交请假申请
     * @param req
     * @return
     * @author hezx
     * @date 2017年2月6日
     */
	public Object addVacateInfo(HttpServletRequest req) {
		String vacate_type_code = req.getParameter("vacate_type_code");
		String vacate_type_name = req.getParameter("vacate_type_name");
		String start_time = req.getParameter("start_time");
		String end_time = req.getParameter("end_time");
		String content = req.getParameter("content");
		String state = req.getParameter("state");
		String day = req.getParameter("day");
		String id = req.getParameter("id");
		String auth_ids = req.getParameter("auth_ids");
		String[] auth_ids_str = auth_ids.split(";");
		Date date = new Date();       
		Timestamp create_time = new Timestamp(date.getTime());
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		String orga_name = loginBean.getvUserDetailInfo().getOrga_name();
		Integer auth_id = loginBean.getvUserDetailInfo().getAuth_id();
		String user_name = loginBean.getvUserDetailInfo().getUser_name();
		Integer orga_id = loginBean.getvUserDetailInfo().getOrga_id();
		StuVacateInfo stuVacateInfo = new StuVacateInfo();
		stuVacateInfo.setVacate_type_code(vacate_type_code);
		stuVacateInfo.setVacate_type_name(vacate_type_name);
		stuVacateInfo.setStart_time(Timestamp.valueOf(start_time+" 00:00:00")); //转换时间字符串为Timestamp
		stuVacateInfo.setEnd_time(Timestamp.valueOf(end_time+" 00:00:00"));
		int start_date=Integer.parseInt(start_time.replace("-","").substring(0, 8));
		stuVacateInfo.setStart_date(start_date);
		int end_date=Integer.parseInt(end_time.replace("-","").substring(0, 8));
		stuVacateInfo.setEnd_date(end_date);
		if(day!=""&&day!=null) {
			stuVacateInfo.setVacate_date_num(Integer.parseInt(day));
		}
		stuVacateInfo.setContent(content);
		int num = 0;
		//判断当id==null是新增 否则 更新
		if(id==null || id.equals("")){
			//查询请假天数对应的流程，根据审批权限天数排序
//			List<RoleProcRela> roleProc = roleProcRelaDao.selectRoleProc();
			//请假天数与最大权限比较,若大于最大天数返回提示
//			if(Integer.parseInt(day)>roleProc.get(roleProc.size()-1).getRequire_datenum()){
//				return StringUtil.returns(false, roleProc.get(roleProc.size()-1).getRequire_datenum()+"天以上请通过学校相关部门申请!");
//			}
//			if(day != null && (day.equals("-10") || day.equals("-20"))) {
//				day = "1";
//			}
//			for (RoleProcRela roleProcRela : roleProc) {
//					if(Integer.parseInt(day)<=roleProcRela.getRequire_datenum()){
//						roleProcList.add(roleProcRela);
//						break;
//					}else{
//						roleProcList.add(roleProcRela);
//					}
//			}
//			if(roleProcList.get(0).getEnd_proc_id()!=null && !roleProcList.get(0).getEnd_proc_id().equals("")){
//				stuVacateInfo.setVacate_status(roleProcList.get(0).getEnd_proc_id());
//			}
			stuVacateInfo.setCreate_auth_id(auth_id);
			stuVacateInfo.setCreate_time(create_time);
			stuVacateInfo.setOrga_id(orga_id);
			stuVacateInfo.setState(state);
			stuVacateInfo.setVacate_status(61);
			stuVacateInfo.setStu_auth_id(auth_id);
			int stuVacateInfoId = stuVacateInfoDao.getSequence();
			stuVacateInfo.setId(stuVacateInfoId);
			num=stuVacateInfoDao.add(stuVacateInfo);
			if(num>0){
				ProcessRecord processRecord = new ProcessRecord();
				processRecord.setType_code(2);
				processRecord.setOrga_id(orga_id);
				ProcrecordUserRela procrecordUserRela = new ProcrecordUserRela();
				procrecordUserRela.setState("Y");
				List<RoleProcRela> roleProcList = new ArrayList<RoleProcRela>();
				RoleProcRela roleProcRela1 = new RoleProcRela();
				roleProcRela1.setStart_proc_id(65);
				roleProcRela1.setStart_name("驳回申请");
				roleProcRela1.setStart_num(-1);
				roleProcRela1.setEnd_proc_id(61);
				roleProcRela1.setEnd_name("学生申请");
				roleProcRela1.setEnd_num(0);
				roleProcList.add(roleProcRela1);
				RoleProcRela roleProcRela2 = new RoleProcRela();
				roleProcRela2.setStart_proc_id(61);
				roleProcRela2.setStart_name("学生申请");
				roleProcRela2.setStart_num(0);
				roleProcRela2.setEnd_proc_id(63);
				roleProcRela2.setEnd_name("教学秘书审核");
				roleProcRela2.setEnd_num(2);
				roleProcList.add(roleProcRela2);
				RoleProcRela roleProcRela3 = new RoleProcRela();
				roleProcRela3.setStart_proc_id(63);
				roleProcRela3.setStart_name("教学秘书审核");
				roleProcRela3.setStart_num(2);
				roleProcRela3.setEnd_proc_id(64);
				roleProcRela3.setEnd_name("科教科审核");
				roleProcRela3.setEnd_num(3);
				roleProcList.add(roleProcRela3);
				//遍历ProcessInfoList 创建各个步骤
				for(int i = 0 ;i<roleProcList.size();i++){
					int processRecordId=processRecordDao.getSequence();
					//processRecord放入参数，为了新增
					processRecord.setId(processRecordId);
					processRecord.setStart_proc_id(roleProcList.get(i).getStart_proc_id());
					processRecord.setStart_proc_num(roleProcList.get(i).getStart_num());
					processRecord.setStart_proc_name(roleProcList.get(i).getStart_name());
					processRecord.setEnd_proc_id(roleProcList.get(i).getEnd_proc_id());
					processRecord.setEnd_proc_num(roleProcList.get(i).getEnd_num());
					processRecord.setEnd_proc_name(roleProcList.get(i).getEnd_name());
					processRecord.setRelated_id(stuVacateInfoId);
					//不再绑定请假的审批人
					//创建子表对应的审批人
//					TrainPlan trainPlan=new TrainPlan();
//					trainPlan.setStu_auth_id(auth_id);
//					trainPlan.setTrain_dept_id(orga_id+"");
//					//在科
//					String trainStatusStrZ = Consts.ProcessInfo.TRAINPROCESSZ + "";
//					trainPlan.setTrainStatusStrZ(trainStatusStrZ);
//					List<TrainPlan> dataList=trainPlanDao.selectByAuthOrga(trainPlan);
//					for (TrainPlan trainPlans : dataList) {
//						if(roleProcList.get(i).getRole_id()==20){
//								//加入记录表
//								processRecord.setCreate_auth_id(trainPlans.getTeacher_auth_id());
//								procrecordUserRela.setUser_auth_id(trainPlans.getTeacher_auth_id());
//								
//						}
//						if(roleProcList.get(i).getRole_id()==30){
//								//加入记录表
//								processRecord.setCreate_auth_id(trainPlans.getSecretary_auth_id());
//								procrecordUserRela.setUser_auth_id(trainPlans.getSecretary_auth_id());
//						}
//						if(roleProcList.get(i).getRole_id()==50){
//							UserInfo userInfo = new UserInfo();
//							userInfo.setRole_id(50);
//							List<UserInfo> userInfoList = userInfoDao.selectOrgaTea(userInfo);
//							for (UserInfo userInfo2 : userInfoList) {
//								//加入记录表
//								processRecord.setCreate_auth_id(userInfo2.getAuth_id());
//								procrecordUserRela.setUser_auth_id(userInfo2.getAuth_id());
//								}
//							}
//						}
					processRecordDao.add(processRecord);
					procrecordUserRela.setPr_id(processRecordId);
					procrecordUserRela.setId(procrecordUserRelaDao.getSequence());
					procrecordUserRelaDao.add(procrecordUserRela);
			}
			//发送消息
			int mpId = 0;
			String title = user_name  + "【" + orga_name + "】" +  "发起了请假申请，请及时审批！";
			// 1、往消息发布表插入数据（MESSAGE_PUBLISH）
			MessagePublish mp = new MessagePublish();
			mpId = messagePublishDao.getSequence();
			mp.setId(mpId);
			mp.setTitle(title);
			mp.setContent(content);
			mp.setSend_time(create_time);
			mp.setSender_auth_id(auth_id);
			mp.setState("Y");
			mp.setType_id(2);
			messagePublishDao.add(mp);// 发布表
			MessageReceive mr = new MessageReceive();
			mr.setProgress_state(0);
			mr.setState("Y");
			mr.setMp_id(mpId);
			for(int i = 0; i < auth_ids_str.length; i++){
				mr.setReceiver_auth_id(Integer.parseInt(auth_ids_str[i]));
				mr.setId(messageReceiveDao.getSequence());
				messageReceiveDao.add(mr);// 接收表
			}
				return StringUtil.returns(true, "操作成功");
			}else{
				return StringUtil.returns(false, "操作异常");
			}
		}else{
//			List<RoleProcRela> roleProcList = new ArrayList<RoleProcRela>();
//			//查询请假天数对应的流程，根据审批权限天数排序
//			List<RoleProcRela> roleProc = roleProcRelaDao.selectRoleProc();
//			//请假天数与最大权限比较,若大于最大天数返回提示
//			if(Integer.parseInt(day)>roleProc.get(roleProc.size()-1).getRequire_datenum()){
//				return StringUtil.returns(false, roleProc.get(roleProc.size()-1).getRequire_datenum()+"天以上请通过学校相关部门申请!");
//			}
//			if(day != null && (day.equals("-10") || day.equals("-20"))) {
//				day = "1";
//			}
//			for (RoleProcRela roleProcRela : roleProc) {
//					if(Integer.parseInt(day)<=roleProcRela.getRequire_datenum()){
//						roleProcList.add(roleProcRela);
//						break;
//					}else{
//						roleProcList.add(roleProcRela);
//					}
//			}
			//更新请假申请
			stuVacateInfo.setId(Integer.parseInt(id));
			int nub = stuVacateInfoDao.update(stuVacateInfo);
			if(nub>0){
				//删除PROCRECORD_USER_RELA表相关数据和删除请假审核记录
//				ProcessRecord processR = new ProcessRecord();
//				processR.setRelated_id(Integer.parseInt(id));
//				List<ProcessRecord> processRList = processRecordDao.selectList(processR);
//				for (ProcessRecord processRGet : processRList) {
//					ProcrecordUserRela procrecordUserR = new ProcrecordUserRela();
//					procrecordUserR.setPr_id(processRGet.getId());
//					procrecordUserRelaDao.delete(procrecordUserR);
//				}
//				for (ProcessRecord processRGet : processRList) {
//					processRecordDao.delete(processRGet);
//				}
//				ProcessRecord processRecord = new ProcessRecord();
//				ProcrecordUserRela procrecordUserRela = new ProcrecordUserRela();
//				processRecord.setType_code(2);
//				processRecord.setOrga_id(orga_id);
//				//遍历ProcessInfoList 创建各个步骤
//				for(int i = 0 ;i<roleProcList.size();i++){
//					int processRecordId=processRecordDao.getSequence();
//					//processRecord放入参数，为了新增
//					processRecord.setId(processRecordId);
//					processRecord.setStart_proc_id(roleProcList.get(i).getStart_proc_id());
//					processRecord.setStart_proc_num(roleProcList.get(i).getStart_num());
//					processRecord.setStart_proc_name(roleProcList.get(i).getStart_name());
//					processRecord.setEnd_proc_id(roleProcList.get(i).getEnd_proc_id());
//					processRecord.setEnd_proc_num(roleProcList.get(i).getEnd_num());
//					processRecord.setEnd_proc_name(roleProcList.get(i).getEnd_name());
//					processRecord.setRelated_id(Integer.parseInt(id));
//					//不再绑定审批人
//					//创建子表对应的审批人
////					TrainPlan trainPlan=new TrainPlan();
////					trainPlan.setStu_auth_id(auth_id);
////					trainPlan.setTrain_dept_id(orga_id+"");
////					//在科
////					String trainStatusStrZ = Consts.ProcessInfo.TRAINPROCESSZ + "";
////					trainPlan.setTrainStatusStrZ(trainStatusStrZ);
////					List<TrainPlan> dataList=trainPlanDao.selectByAuthOrga(trainPlan);
////					for (TrainPlan trainPlans : dataList) {
////						if(roleProcList.get(i).getRole_id()==20){
////								//加入记录表
////								if (teacher_auth_id != null && !teacher_auth_id.equals("")) {
////									processRecord.setCreate_auth_id(Integer.parseInt(teacher_auth_id));
////									procrecordUserRela.setUser_auth_id(Integer.parseInt(teacher_auth_id));
////								}else{
////									processRecord.setCreate_auth_id(trainPlans.getTeacher_auth_id());
////									procrecordUserRela.setUser_auth_id(trainPlans.getTeacher_auth_id());
////								}
////								
////						}
////						if(roleProcList.get(i).getRole_id()==30){
////								//加入记录表
////								processRecord.setCreate_auth_id(trainPlans.getSecretary_auth_id());
////								procrecordUserRela.setUser_auth_id(trainPlans.getSecretary_auth_id());
////						}
////						if(roleProcList.get(i).getRole_id()==50){
////							UserInfo userInfo = new UserInfo();
////							userInfo.setRole_id(50);
////							List<UserInfo> userInfoList = userInfoDao.selectOrgaTea(userInfo);
////							for (UserInfo userInfo2 : userInfoList) {
////								//加入记录表
////								processRecord.setCreate_auth_id(userInfo2.getAuth_id());
////								procrecordUserRela.setUser_auth_id(userInfo2.getAuth_id());
////								}
////							}
////						}
//					processRecordDao.add(processRecord);
//					procrecordUserRela.setPr_id(processRecordId);
//					procrecordUserRela.setState("Y");
//					procrecordUserRela.setId(procrecordUserRelaDao.getSequence());
//					procrecordUserRelaDao.add(procrecordUserRela);
//					}
				if (auth_ids_str != null) {
					//发送消息
					int mpId = 0;
					String title = user_name  + "【" + orga_name + "】" +  "发起了请假申请，请及时审批！";
					// 1、往消息发布表插入数据（MESSAGE_PUBLISH）
					MessagePublish mp = new MessagePublish();
					mpId = messagePublishDao.getSequence();
					mp.setId(mpId);
					mp.setTitle(title);
					mp.setContent(content);
					mp.setSend_time(create_time);
					mp.setSender_auth_id(auth_id);
					mp.setState("Y");
					mp.setType_id(2);
					messagePublishDao.add(mp);// 发布表
					MessageReceive mr = new MessageReceive();
					mr.setProgress_state(0);
					mr.setState("Y");
					mr.setMp_id(mpId);
					for(int i = 0; i < auth_ids_str.length; i++){
						mr.setReceiver_auth_id(Integer.parseInt(auth_ids_str[i]));
						mr.setId(messageReceiveDao.getSequence());
						messageReceiveDao.add(mr);// 接收表
					}
				}
				return StringUtil.returns(true, "操作成功");
			}else{
				return StringUtil.returns(false, "操作异常");
			}
		}
	}

	/**
	 * 分页查询请假申请
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年2月8日
	 */
	public JSONObject selectVacateInfo(int pageIndex, int rows,StuVacateInfo stuVacateInfo) {
		JSONObject jobj = new JSONObject();
		List<StuVacateInfo> dataList = stuVacateInfoDao.selectVacateInfo(
				new RowBounds((pageIndex - 1) * rows, pageIndex * rows),
				stuVacateInfo);
		for(int i=0;i<dataList.size();i++){
			dataList.get(i).setVacate_time(dataList.get(i).getStart_time_str()+" ~ "+dataList.get(i).getEnd_time_str());
			if (dataList.get(i).getVacate_date_num() == -10 || dataList.get(i).getVacate_date_num() == -20) {
				dataList.get(i).setVacate_date_num_str("0.5");
			}else{
				dataList.get(i).setVacate_date_num_str(dataList.get(i).getVacate_date_num().toString());
			}
		}
		int totalCount = stuVacateInfoDao.selectCount(stuVacateInfo);
		jobj.put("total", StringUtil.getMaxInt(totalCount, rows));// 总页数
		jobj.put("page", pageIndex);// 当前页码
		jobj.put("records", totalCount);// 总的记录数
		jobj.put("rows", dataList);// 显示的具体数据，jsonarray格式。
		return jobj;
	}

	/***
     * 根据ID查询申请详情
     * @param req
     * @return
     * @author hezx
     * @date 2017年2月8日
     */
	public Object selectVacateById(HttpServletRequest req) {
		String id = req.getParameter("id");
		StuVacateInfo stuVacate = stuVacateInfoDao.selectVacateById(Integer.parseInt(id));
		return StringUtil.returns(true, stuVacate);
	}

	/***
     * 删除
     * @param req
     * @return
     * @author hezx
     * @date 2017年2月8日
     */
	public Object delVacateInfo(HttpServletRequest req) {
		String id = req.getParameter("id");
		String state = req.getParameter("state");
		StuVacateInfo stuVacateInfo = new StuVacateInfo();
		if(id!=null&&!id.equals("")){
			stuVacateInfo.setId(Integer.parseInt(id));
			stuVacateInfo.setState(state);
			int nub = stuVacateInfoDao.update(stuVacateInfo);
			if(nub>0){
				ProcessRecord processRecord = new ProcessRecord();
				processRecord.setRelated_id(Integer.parseInt(id));
				List<ProcessRecord> list =processRecordDao.selectList(processRecord);
				ProcrecordUserRela procrecordUserRela = new ProcrecordUserRela();
				for (ProcessRecord processRecord2 : list) {
					procrecordUserRela.setPr_id(processRecord2.getId());
					procrecordUserRelaDao.delete(procrecordUserRela);
				}
				processRecordDao.delete(processRecord);
			}
			return StringUtil.returns(true, "操作成功!");
		}else{
			return StringUtil.returns(false, "操作异常!");
		}
	}

	/***
	 * 查询请假流程审批详细信息
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年2月13日
	 */
	public Object selectProcessInfo(HttpServletRequest req) {
		String id = req.getParameter("id");
		ProcessRecord processRecord = new ProcessRecord();
		if(id!=null&&!id.equals("")){
			processRecord.setRelated_id(Integer.parseInt(id));
			List<ProcessRecord> list=processRecordDao.selectProcessInfo(processRecord);
				return StringUtil.returns(true, list);
		}else{
			return StringUtil.returns(false, "操作异常!");
	}
   }
	public JSONObject selectVacateList(int page, int rows,StuVacateInfo stuVacateInfo) {
		JSONObject jobj = new JSONObject();
		List<StuVacateInfo> dataList = stuVacateInfoDao.selectVacateList(
				new RowBounds((page - 1) * rows, page * rows),
				stuVacateInfo);
		int totalCount = stuVacateInfoDao.selectVacateListCount(stuVacateInfo);
		//查询待销假的申请，科教科才有销假的权限
		if (stuVacateInfo.getProc_result() == null || "".equals(stuVacateInfo.getProc_result())) {
			if (stuVacateInfo.getRole_id() == 50){
				StuVacateInfo stuVacateI = new StuVacateInfo();
				stuVacateI.setVacate_status(4);
				List<StuVacateInfo> stuVacateInfoListGet = stuVacateInfoDao.selectVacateListByStatus(stuVacateI);
				dataList.addAll(stuVacateInfoListGet);
				totalCount = totalCount + stuVacateInfoListGet.size();
			}
			//销假权限暂时只给老师
//			else if (stuVacateInfo.getRole_id() == 30) {
//				trainPlan.setSecretary_auth_id(stuVacateInfo.getCreate_auth_id());
//				trainPlan.setHos_id(stuVacateInfo.getHos_id());
//				List<TrainPlan> trainPlanList = trainPlanDao.selectList(trainPlan);
//				int countOfBack = 0;
//				List<StuVacateInfo> stuVacateInfoList = new ArrayList<StuVacateInfo>();
//				StuVacateInfo stuVacateI = new StuVacateInfo();
//				stuVacateI.setVacate_status(4);
//				for (TrainPlan tp : trainPlanList) {
//					stuVacateI.setStu_auth_id(tp.getStu_auth_id());
//					stuVacateI.setOrga_id(Integer.parseInt(tp.getTrain_dept_id()));
//					List<StuVacateInfo> stuVacateInfoListGet = stuVacateInfoDao.selectVacateListByStatus(stuVacateI);
//					if (stuVacateInfoListGet != null && !stuVacateInfoListGet.isEmpty()) {
//						countOfBack += stuVacateInfoListGet.size();
//						stuVacateInfoList.addAll(stuVacateInfoListGet);
//					}
//				}
//				dataList.addAll(stuVacateInfoList);
//				totalCount = totalCount + countOfBack;
//			}
			
			for(int i=0;i<dataList.size();i++){
				dataList.get(i).setVacate_time(dataList.get(i).getStart_time_str()+" ~ "+dataList.get(i).getEnd_time_str());
				if (dataList.get(i).getVacate_date_num() == -10 || dataList.get(i).getVacate_date_num() == -20) {
					dataList.get(i).setVacate_date_num_str("0.5");
				}else{
					dataList.get(i).setVacate_date_num_str(dataList.get(i).getVacate_date_num().toString());
				}
			}
			jobj.put("total", StringUtil.getMaxInt(totalCount, rows));// 总页数
			jobj.put("page", page);// 当前页码
			jobj.put("records", totalCount);// 总的记录数
			jobj.put("rows", dataList);// 显示的具体数据，jsonarray格式。
			return jobj;
		}
		for(int i=0;i<dataList.size();i++){
			dataList.get(i).setVacate_time(dataList.get(i).getStart_time_str()+" ~ "+dataList.get(i).getEnd_time_str());
			if (dataList.get(i).getVacate_date_num() == -10 || dataList.get(i).getVacate_date_num() == -20) {
				dataList.get(i).setVacate_date_num_str("0.5");
			}else{
				dataList.get(i).setVacate_date_num_str(dataList.get(i).getVacate_date_num().toString());
			}
		}
		jobj.put("total", StringUtil.getMaxInt(totalCount, rows));// 总页数
		jobj.put("page", page);// 当前页码
		jobj.put("records", totalCount);// 总的记录数
		jobj.put("rows", dataList);// 显示的具体数据，jsonarray格式。
		return jobj;
	}
	
	/**
	 * 发起销假申请
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年6月2日
	 */
	public Object reportBackAfteLeave(HttpServletRequest req) {
		String id = req.getParameter("id");
		String vacate_status = req.getParameter("vacate_status");
		if (id != null && !id.equals("") && vacate_status != null && !vacate_status.equals("")) {
			LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
			String user_name = loginBean.getvUserDetailInfo().getUser_name();
			String orga_name = loginBean.getvUserDetailInfo().getOrga_name();
			Integer auth_id = loginBean.getvUserDetailInfo().getAuth_id();
			Integer orga_id = loginBean.getvUserDetailInfo().getOrga_id();
			StuVacateInfo stuVacateInfo = new StuVacateInfo();
			stuVacateInfo.setId(Integer.parseInt(id));
			stuVacateInfo.setVacate_status(Integer.parseInt(vacate_status));
			int num = stuVacateInfoDao.update(stuVacateInfo);
			if (num > 0) {
				int mpId = 0;
				String title = user_name  + "【" + orga_name + "】" +  "发起了销假申请，请及时审批！";
				// 1、往消息发布表插入数据（MESSAGE_PUBLISH）
				MessagePublish mp = new MessagePublish();
				mpId = messagePublishDao.getSequence();
				mp.setId(mpId);
				mp.setTitle(title);
				mp.setContent(title);
				Date date = new Date();       
				Timestamp send_time = new Timestamp(date.getTime());
				mp.setSend_time(send_time);
				mp.setSender_auth_id(auth_id);
				mp.setState("Y");
				mp.setType_id(2);
				mp.setRelated_table("MESSAGE_RECEIVE");
				messagePublishDao.add(mp);                     //发布表
				// 2、往消息接收表插入数据（MESSAGE_RECEIVE）
				MessageReceive mr = new MessageReceive();
				mr.setProgress_state(0);
				mr.setState("Y");
				mr.setMp_id(mpId);
				UserInfo userInfo = new UserInfo();
				userInfo.setState("Y");
				userInfo.setRole_id(50);
				List<UserInfo> userInfoList = userInfoDao.selectOrgaTea(userInfo);
				for (UserInfo tp : userInfoList) {
					mr.setReceiver_auth_id(tp.getAuth_id());
					mr.setId(messageReceiveDao.getSequence());
					messageReceiveDao.add(mr);            //老师接收
				}
				return StringUtil.returns(true, "操作成功！");
			}else{
				return StringUtil.returns(false, "操作失败！");
			}
		}else{
			return StringUtil.returns(false, "操作失败！");
		}
	}
	
	/**
	 * 销假申请审核
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年6月2日
	 */
	public Object checkForReportBack(HttpServletRequest req) {
		String id = req.getParameter("id");
		String vacate_status = req.getParameter("vacate_status");
		String stu_auth_id = req.getParameter("stu_auth_id");
		if (id != null && !id.equals("") && vacate_status != null && !vacate_status.equals("") && stu_auth_id != null && !stu_auth_id.equals("")) {
			LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
			String user_name = loginBean.getvUserDetailInfo().getUser_name();
			String orga_name = loginBean.getvUserDetailInfo().getOrga_name();
			Integer auth_id = loginBean.getvUserDetailInfo().getAuth_id();
			StuVacateInfo stuVacateInfo = new StuVacateInfo();
			stuVacateInfo.setId(Integer.parseInt(id));
			stuVacateInfo.setVacate_status(Integer.parseInt(vacate_status));
			int num = stuVacateInfoDao.update(stuVacateInfo);
			if (num > 0) {
				int mpId = 0;
				String title = "";
				if (vacate_status.equals("5")) {
					title = user_name  + "【" + orga_name + "】" +  "批准了你的销假申请！";
				}else if (vacate_status.equals("6")) {
					title = user_name  + "【" + orga_name + "】" +  "驳回了你的销假申请！";
				}
				// 1、往消息发布表插入数据（MESSAGE_PUBLISH）
				MessagePublish mp = new MessagePublish();
				mpId = messagePublishDao.getSequence();
				mp.setId(mpId);
				mp.setTitle(title);
				mp.setContent(title);
				Date date = new Date();       
				Timestamp send_time = new Timestamp(date.getTime());
				mp.setSend_time(send_time);
				mp.setSender_auth_id(auth_id);
				mp.setState("Y");
				mp.setType_id(2);
				mp.setRelated_table("MESSAGE_RECEIVE");
				messagePublishDao.add(mp);                     //发布表
				// 2、往消息接收表插入数据（MESSAGE_RECEIVE）
				MessageReceive mr = new MessageReceive();
				mr.setProgress_state(0);
				mr.setState("Y");
				mr.setMp_id(mpId);
				mr.setReceiver_auth_id(Integer.parseInt(stu_auth_id));
				mr.setId(messageReceiveDao.getSequence());
				messageReceiveDao.add(mr);            //接收表
				return StringUtil.returns(true, "操作成功！");
			}else{
				return StringUtil.returns(false, "操作失败！");
			}
		}else{
			return StringUtil.returns(false, "操作失败！");
		}
	}


	public Object getVacateApprover(HttpServletRequest req) {
		StringBuilder authIds = new StringBuilder();
		StringBuilder names = new StringBuilder();
		UserInfo userInfo = new UserInfo();
		userInfo.setState("Y");
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer orgaId = loginBean.getvUserDetailInfo().getOrga_id();
		userInfo.setOrga_id(orgaId);
		userInfo.setRole_id(30);
		List<UserInfo> userInfoList = userInfoDao.selectOrgaTea(userInfo);
		for (UserInfo userInfo2 : userInfoList) {
			if ("".equals(authIds.toString())) {
				authIds.append(userInfo2.getAuth_id());
				names.append(userInfo2.getUser_name());
			} else {
				authIds.append(";").append(userInfo2.getAuth_id());
				names.append(";").append(userInfo2.getUser_name());
			}
		}
		TrainPlan trainPlan = new TrainPlan();
		trainPlan.setName(names.toString());
		trainPlan.setAuth_ids(authIds.toString());
		return trainPlan;
	}
}