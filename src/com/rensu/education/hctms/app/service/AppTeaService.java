package com.rensu.education.hctms.app.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.RowBounds;
import org.apache.poi2.hssf.record.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.config.bean.TrainTeachOrder;
import com.rensu.education.hctms.config.dao.TrainTeachOrderDao;
import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.log.bean.ProcessRecord;
import com.rensu.education.hctms.log.dao.ProcessRecordDao;
import com.rensu.education.hctms.message.bean.MessagePublish;
import com.rensu.education.hctms.message.bean.MessageReceive;
import com.rensu.education.hctms.message.dao.MessagePublishDao;
import com.rensu.education.hctms.message.dao.MessageReceiveDao;
import com.rensu.education.hctms.teach.bean.StuAttendanceInfo;
import com.rensu.education.hctms.teach.bean.StuDailyRecord;
import com.rensu.education.hctms.teach.bean.StuTeachOrder;
import com.rensu.education.hctms.teach.bean.StuVacateInfo;
import com.rensu.education.hctms.teach.bean.TrainPlan;
import com.rensu.education.hctms.teach.dao.StuAttendanceInfoDao;
import com.rensu.education.hctms.teach.dao.StuDailyRecordDao;
import com.rensu.education.hctms.teach.dao.StuTeachOrderDao;
import com.rensu.education.hctms.teach.dao.StuVacateInfoDao;
import com.rensu.education.hctms.teach.dao.TrainPlanDao;
import com.rensu.education.hctms.userauth.bean.UserInfo;
import com.rensu.education.hctms.userauth.dao.UserInfoDao;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.DateUtil;
import com.rensu.education.hctms.util.DesUtils;
import com.rensu.education.hctms.util.StringUtil;

@Service("appTeaService")
public class AppTeaService extends BaseService<T> {
	
	
	@Autowired
	protected TrainPlanDao trainPlanDao;
	@Autowired
	protected TrainTeachOrderDao trainTeachOrderDao;
	@Autowired
	protected StuTeachOrderDao stuTeachOrderDao;
	@Autowired
	protected StuAttendanceInfoDao stuAttendanceInfoDao;
	@Autowired
	protected StuDailyRecordDao stuDailyRecordDao;
	@Autowired
	protected StuVacateInfoDao stuVacateInfoDao;
	@Autowired
	protected ProcessRecordDao processRecordDao;
	@Autowired
	protected UserInfoDao userInfoDao;
	@Autowired
	protected MessagePublishDao messagePublishDao;
	@Autowired
	protected MessageReceiveDao messageReceiveDao;
	
	
	public String getAttQrCode(String userOrgaId){
		//得到服务器的当前日期
		String nowDate = DateUtil.getCurrDate();
		//组成格式：“标识:考勤日期-科室ID” 这种字符形式
		String qrStr = "attendance:" + nowDate + "-" + userOrgaId;
		//进行Des加密
		//1.首先得到秘钥
		String key = DesUtils.getStrDefaultKey();
		//2.进行加密处理
		String rtn = "";
		try {
			//3.得到加密后的字符串
			DesUtils des= new DesUtils(key);
			rtn = des.encrypt(qrStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String rtnJson = "{\"success\":true,"
				  + "\"QrCode\":\""+rtn+"\""
				  + "}";
		return rtnJson;
	}
	
	public String getStuListInfo(String userAuthId,String userOrgaId){
		//根据老师AuthId查找出所有的学生详细信息
		TrainPlan _tp = new TrainPlan();
		_tp.setTrain_dept_id(userOrgaId);
		_tp.setTeacher_auth_id(Integer.parseInt(userAuthId));
		_tp.setTrainStatus(Consts.ProcessInfo.TRAINPROCESSInAll);
		List<TrainPlan> tpList = trainPlanDao.getPendingAudit(new RowBounds(),_tp);
		//组成返回前台的json格式
		StringBuffer rtnJson = new StringBuffer();
		rtnJson.append("{\"success\":true,");
		rtnJson.append("\"stuList\":[");
		for(int i=0;tpList!=null&&i<tpList.size();i++){
			//查询出每个学生的大纲吻合度百分比（总）与轮转剩余天数与总天数的比例
			TrainPlan trianPlan = new TrainPlan();
			trianPlan.setStu_auth_id(tpList.get(i).getStu_auth_id());
			trianPlan.setTrain_dept_id(userOrgaId);
			List<TrainPlan> dataList = trainPlanDao.selectList(trianPlan);
			//肯定是唯一的行
			int stuPro = 0;
			int trainPro = 0;
			if(dataList!=null&&dataList.size()>0){
				stuPro = getSumPro(tpList.get(i).getStu_auth_id(),userOrgaId,dataList.get(0).getTpc_id());
				//计算到今日是整个科室培训时长的百分比
				try {
					int sumDate = DateUtil.daysBetween(dataList.get(0).getTrain_start_time(),dataList.get(0).getTrain_end_time())+1;
					int pastDate = DateUtil.daysBetween(dataList.get(0).getTrain_start_time(),new java.sql.Timestamp(new java.util.Date().getTime()))+1;
					if(pastDate>=sumDate){
						trainPro = 100;
					}else{
						trainPro = (int)((float)pastDate/(float)sumDate*100);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
			}
			if(i==tpList.size()-1){
				rtnJson.append("{\"name\":\""+tpList.get(i).getName()+"\","+
							   "\"sex\":\""+tpList.get(i).getSex()+"\","+
							   "\"stu_age\":\""+tpList.get(i).getStu_age_str()+"岁\","+
							   "\"train_status_str\":\""+tpList.get(i).getTrain_status_str()+"\","+
							   "\"typeName\":\""+tpList.get(i).getTypeName()+"\","+
							   "\"stu_major_name\":\""+tpList.get(i).getStu_major_name()+"\","+
							   "\"mobile\":\""+tpList.get(i).getMobile()+"\","+
							   "\"tpId\":"+dataList.get(0).getId()+","+
							   "\"stuAuthId\":"+tpList.get(i).getStu_auth_id()+","+
							   "\"stuOrgaId\":"+tpList.get(i).getTrain_dept_id()+","+
							   "\"trainPro\":"+trainPro+","+
							   "\"stuPro\":"+stuPro+""+
							   "}");
			}else{
				rtnJson.append("{\"name\":\""+tpList.get(i).getName()+"\","+
						   "\"sex\":\""+tpList.get(i).getSex()+"\","+
						   "\"stu_age\":\""+tpList.get(i).getStu_age_str()+"岁\","+
						   "\"train_status_str\":\""+tpList.get(i).getTrain_status_str()+"\","+
						   "\"typeName\":\""+tpList.get(i).getTypeName()+"\","+
						   "\"stu_major_name\":\""+tpList.get(i).getStu_major_name()+"\","+
						   "\"mobile\":\""+tpList.get(i).getMobile()+"\","+
						   "\"tpId\":"+dataList.get(0).getId()+","+
						   "\"stuAuthId\":"+tpList.get(i).getStu_auth_id()+","+
						   "\"stuOrgaId\":"+tpList.get(i).getTrain_dept_id()+","+
						   "\"trainPro\":"+trainPro+","+
						   "\"stuPro\":"+stuPro+""+
						   "},");
			}
		}
		rtnJson.append("]");
		rtnJson.append("}");
		return rtnJson.toString();
	}
	
	public int getSumPro(int stuAuthId,String orgaId,int tpcId){
		int num = 0;
		//查询完成的百分比
		TrainPlan _tp = new TrainPlan();
		_tp.setStu_auth_id(stuAuthId);
		_tp.setTrain_dept_id(orgaId);
		_tp.setTpc_id(tpcId);
		List<TrainTeachOrder> trainTeachOrderList = trainTeachOrderDao.selectTrainTeachOrderList(_tp);
		int i = 0;//  计数
		float completion_sum = 0;//  各项完成率相加的和
		for (TrainTeachOrder trainTeachOrder : trainTeachOrderList) {
			if(trainTeachOrder.getOrder_num()!=null){
				i++;
				float completion_rate1 = 0;
				if (trainTeachOrder.getFinnish_num()!=null) {
					completion_rate1 = (float)trainTeachOrder.getFinnish_num()/(float)trainTeachOrder.getOrder_num();
				}
				if(completion_rate1<=1){
					completion_sum=completion_sum+completion_rate1;
				}else{
					completion_sum=completion_sum+1;
				}
			}
		}
		num =(int)(completion_sum/i*100);
		if(num>100){
			num = 100;
		}
		return num;
	}
	
	
	@SuppressWarnings("unchecked")
	public String getStuInfo(String tpId,String stuAuthId,String stuOrgaId){
		//根据tpId查找出大纲完成度
		TrainPlan trainPlan = trainPlanDao.selectOneById(Integer.parseInt(tpId));
		List<TrainTeachOrder> trainTeachOrderList = new ArrayList<TrainTeachOrder>();
		if (trainPlan != null) {
			TrainTeachOrder trainTeachOrder = new TrainTeachOrder();
			trainTeachOrder.setState("Y");
			trainTeachOrder.setTpc_id(trainPlan.getTpc_id());
			trainTeachOrder.setOrderCondition("sort_num asc");
			trainTeachOrderList = trainTeachOrderDao.selectList(trainTeachOrder);
		}
		TrainTeachOrder tto = new TrainTeachOrder();
		tto.setState("Y");
		StuTeachOrder sto = new StuTeachOrder();
		sto.setStu_auth_id(Integer.parseInt(stuAuthId));
		sto.setDept_id(Integer.parseInt(stuOrgaId));
		for (TrainTeachOrder ttoGet : trainTeachOrderList) {
			if (ttoGet.getType_id() == 1) {
				tto.setSup_id(ttoGet.getId());
				List<TrainTeachOrder> trainTeachOrderSubList = trainTeachOrderDao.selectList(tto);
				for (TrainTeachOrder ttoGet1 : trainTeachOrderSubList) {
					sto.setTto_id(ttoGet1.getId());
					List<StuTeachOrder> stoList = stuTeachOrderDao.selectList(sto);
					if (stoList != null && !stoList.isEmpty()) {
						float completion_rate=(float)stoList.get(0).getFinnish_num()/(float)stoList.get(0).getOrder_num();
						if((int)(completion_rate*100)>100){
							ttoGet1.setCompletion_rate_str("100");
						}else{
							ttoGet1.setCompletion_rate_str((int)(completion_rate*100)+"");
						}
						ttoGet1.setFinnish_num(stoList.get(0).getFinnish_num());
					}
				}
				ttoGet.setTrainTeachOrderSubList(trainTeachOrderSubList);
			}else if (ttoGet.getType_id() == 0 || ttoGet.getType_id() == 2) {
				if(ttoGet.getType_id() == 2){
					ttoGet.setOrder_name("┗ "+ttoGet.getOrder_name());
				}
				sto.setTto_id(ttoGet.getId());
				List<StuTeachOrder> stoList = stuTeachOrderDao.selectList(sto);
				if (stoList != null && !stoList.isEmpty()) {
					float completion_rate=(float)stoList.get(0).getFinnish_num()/(float)stoList.get(0).getOrder_num();
					if((int)(completion_rate*100)>100){
						ttoGet.setCompletion_rate_str("100");
					}else{
						ttoGet.setCompletion_rate_str((int)(completion_rate*100)+"");
					}
					ttoGet.setFinnish_num(stoList.get(0).getFinnish_num());
				}
			}
		}
		//查询此学生在此科室的考勤情况
		Map<String,Object> map = stuAttendanceInfoDao.getStuAttListByOrgaId(Integer.parseInt(stuOrgaId), Integer.parseInt(stuAuthId));
		List<StuAttendanceInfo> attList = (List<StuAttendanceInfo>) map.get("attList");
		String sDate = (String) map.get("sDate");
		String eDate = (String) map.get("eDate");
		String attNum = (String) map.get("attNum");
		String attNotNum = (String) map.get("attNotNum");
		String vNum = (String) map.get("vNum");
		//组成返回前台的json格式
		StringBuffer rtnJson = new StringBuffer();
		rtnJson.append("{\"success\":true,");
		rtnJson.append("\"ttoList\":[");
		for(int i=0;trainTeachOrderList!=null&&i<trainTeachOrderList.size();i++){
			String order_num = "0";
			if(trainTeachOrderList.get(i).getOrder_num()!=null){
				order_num = trainTeachOrderList.get(i).getOrder_num()+"";
			}
			String order_num_unit = "";
			if(trainTeachOrderList.get(i).getOrder_num()!=null){
				order_num_unit = trainTeachOrderList.get(i).getOrder_num_unit();
			}
			String finnish_num = "0";
			if(trainTeachOrderList.get(i).getFinnish_num()!=null){
				finnish_num = trainTeachOrderList.get(i).getFinnish_num()+"";
			}
			String completion_rate = "0";
			if(trainTeachOrderList.get(i).getCompletion_rate_str()!=null){
				completion_rate = trainTeachOrderList.get(i).getCompletion_rate_str();
			}
			if(i==trainTeachOrderList.size()-1){
				rtnJson.append("{\"order_name\":\""+trainTeachOrderList.get(i).getOrder_name()+"\","+
							   "\"order_num\":\""+order_num+"\","+
							   "\"order_num_unit\":\""+order_num_unit+"\","+
							   "\"finnish_num\":"+finnish_num+","+
							   "\"completion_rate\":"+completion_rate+","+
							   "\"type_id\":"+trainTeachOrderList.get(i).getType_id()+""+
							   "}");
			}else{
				rtnJson.append("{\"order_name\":\""+trainTeachOrderList.get(i).getOrder_name()+"\","+
						   "\"order_num\":\""+order_num+"\","+
						   "\"order_num_unit\":\""+order_num_unit+"\","+
						   "\"finnish_num\":"+finnish_num+","+
						   "\"completion_rate\":"+completion_rate+","+
						   "\"type_id\":"+trainTeachOrderList.get(i).getType_id()+""+
						   "},");
			}
		}
		rtnJson.append("],");
		rtnJson.append("\"sDate\":\"" + "入科："+ sDate + "\",");
		rtnJson.append("\"eDate\":\"" + "出科："+ eDate + "\",");
		rtnJson.append("\"attNum\":\"" + "到勤："+ attNum + "天" + "\",");
		rtnJson.append("\"attNotNum\":\"" + "缺勤："+ attNotNum + "天" + "\",");
		rtnJson.append("\"vNum\":\"" + "请假："+ vNum + "天" + "\",");
		rtnJson.append("\"attList\":[");
		for(int i=0;attList!=null&&i<attList.size();i++){
			if(i==attList.size()-1){
				rtnJson.append("{\"attDate\":\""+attList.get(i).getAttend_time_str()+"\","+
							   "\"attState\":\""+attList.get(i).getAttend_state_str()+"\""+
							   "}");
			}else{
				rtnJson.append("{\"attDate\":\""+attList.get(i).getAttend_time_str()+"\","+
						   	   "\"attState\":\""+attList.get(i).getAttend_state_str()+"\""+
						   	   "},");
			}
		}
		rtnJson.append("]");
		rtnJson.append("}");
		return rtnJson.toString();
	}
	
	public String getStuDRList(String userAuthId){
		//根据老师权限id找到所带学生的日志记录
		StuDailyRecord stuDailyRecord = new StuDailyRecord();
		stuDailyRecord.setState("N");
		stuDailyRecord.setTeacher_auth_id(Integer.parseInt(userAuthId));
		List<StuDailyRecord> dataList = stuDailyRecordDao.selectReviewedDaily(new RowBounds(0,200),stuDailyRecord);
		//组成返回前台的json格式
		StringBuffer rtnJson = new StringBuffer();
		rtnJson.append("{\"success\":true,");
		rtnJson.append("\"stuDRList\":[");
		for(int i=0;dataList!=null&&i<dataList.size();i++){
			if(i==dataList.size()-1){
				rtnJson.append("{\"id\":"+dataList.get(i).getId()+","+
						   	   "\"stuName\":\""+dataList.get(i).getUserName()+"\","+
						       "\"drType\":\""+dataList.get(i).getType_id_str()+"\","+
						       "\"createDate\":\""+dataList.get(i).getCreate_time_dateStr()+"\""+
						       "}");
			}else{
				rtnJson.append("{\"id\":"+dataList.get(i).getId()+","+
					   	   "\"stuName\":\""+dataList.get(i).getUserName()+"\","+
					       "\"drType\":\""+dataList.get(i).getType_id_str()+"\","+
					       "\"createDate\":\""+dataList.get(i).getCreate_time_dateStr()+"\""+
					       "},");
			}
		}
		rtnJson.append("]");
		rtnJson.append("}");
		return rtnJson.toString();
	}
	
	public String updateAllStuDRState(String userAuthId){
		String rtnJson = "";
		//根据老师权限ID查找出所有的学生
		TrainPlan tp = new TrainPlan();
		tp.setTeacher_auth_id(Integer.parseInt(userAuthId));
		tp.setState("Y");
		int count = trainPlanDao.selectCount(tp);
		if(count>0){
			StuDailyRecord sdr = new StuDailyRecord();
			sdr.setTeacher_auth_id(Integer.parseInt(userAuthId));
			if(stuDailyRecordDao.updateAllStuDRState(sdr)>0){
				rtnJson = "{\"success\":true,"
						  + "\"msg\":\"操作成功！\""
						  + "}";
			}else{
				rtnJson = "{\"success\":false,"
						  + "\"msg\":\"数据处理出错...\""
						  + "}";
			}
		}
		return rtnJson;
	}
	
	
	public String getStuAttListTea(String userAuthId,String userOrgaId,String roleId){
		//根据老师权限id找到所带学生的请假申请
		StuVacateInfo stuVacateInfo = new StuVacateInfo();
		stuVacateInfo.setTrain_status_str(Consts.ProcessInfo.TRAINPROCESSInAll);
		if(roleId!=null&&"20".equals(roleId)){ //带教老师
			stuVacateInfo.setTeacher_auth_id(Integer.parseInt(userAuthId));
			stuVacateInfo.setVacate_status(61);
			stuVacateInfo.setOrga_id(Integer.parseInt(userOrgaId));
		}
		List<StuVacateInfo> sviList = stuVacateInfoDao.selectVacateList(new RowBounds(0,100),stuVacateInfo);
		//转换一些数据
		for(int i=0;i<sviList.size();i++){
			sviList.get(i).setContent(sviList.get(i).getContent().replace("<p>", "").replace("</p>", "").replace("<br/>", ""));
			sviList.get(i).setVacate_time(sviList.get(i).getStart_time_str()+"~"+sviList.get(i).getEnd_time_str());
			if (sviList.get(i).getVacate_date_num() == -10) {
				sviList.get(i).setVacate_time(sviList.get(i).getStart_time_str());
				sviList.get(i).setVacate_date_num_str("上午");
			}else if(sviList.get(i).getVacate_date_num() == -20){
				sviList.get(i).setVacate_time(sviList.get(i).getStart_time_str());
				sviList.get(i).setVacate_date_num_str("下午");
			}else if(sviList.get(i).getVacate_date_num() == 1){
				sviList.get(i).setVacate_time(sviList.get(i).getStart_time_str());
				sviList.get(i).setVacate_date_num_str("1");
			}else{
				sviList.get(i).setVacate_date_num_str(sviList.get(i).getVacate_date_num().toString());
			}
		}
		//组成返回前台的json格式
		StringBuffer rtnJson = new StringBuffer();
		rtnJson.append("{\"success\":true,");
		rtnJson.append("\"stuAttList\":[");
		for(int i=0;sviList!=null&&i<sviList.size();i++){
			if(i==sviList.size()-1){
				rtnJson.append("{\"id\":"+sviList.get(i).getId()+","+
							   "\"stu_name\":\""+sviList.get(i).getUser_name()+"\","+
						   	   "\"vacate_type_name\":\""+sviList.get(i).getVacate_type_name()+"\","+
						       "\"orga_name\":\""+sviList.get(i).getOrga_name()+"\","+
						       "\"vacate_time\":\""+sviList.get(i).getVacate_time()+"\","+
						       "\"vacate_date_num_str\":\""+sviList.get(i).getVacate_date_num_str()+"\","+
						       "\"content\":\""+sviList.get(i).getContent()+"\""+
						       "}");
			}else{
				rtnJson.append("{\"id\":"+sviList.get(i).getId()+","+
					   	   "\"vacate_type_name\":\""+sviList.get(i).getVacate_type_name()+"\","+
					       "\"orga_name\":\""+sviList.get(i).getOrga_name()+"\","+
					       "\"vacate_time\":\""+sviList.get(i).getVacate_time()+"\","+
					       "\"vacate_date_num_str\":\""+sviList.get(i).getVacate_date_num_str()+"\","+
					       "\"content\":\""+sviList.get(i).getContent()+"\""+
					       "},");
			}
		}
		rtnJson.append("]");
		rtnJson.append("}");
		return rtnJson.toString();
	}
	
	public String passStuAttInfoTea(HttpServletRequest req){
		String rtnJson = "";
		//接收参数
		String id = req.getParameter("id");
		String flag = req.getParameter("flag");
		String proc_content = req.getParameter("proc_content");
		String stu_name=req.getParameter("stu_name");
		String state_time = req.getParameter("start_time");
		String days = req.getParameter("days");
		String userAuthId = req.getParameter("userAuthId");
		String userOrgaId = req.getParameter("userOrgaId");
		String userName = req.getParameter("userName");
		String userRoleId = req.getParameter("userRoleId");
		String userOrgaName = req.getParameter("userOrgaName");
		
		StringUtil.printRequestParams(req);
		//开始进行业务处理
		if (StringUtil.isNotEmpty(id)&&StringUtil.isNotEmpty(flag)&&StringUtil.isNotEmpty(stu_name)
				&&StringUtil.isNotEmpty(state_time)&&StringUtil.isNotEmpty(days)) {
			state_time=state_time.replace("-","");
			int day =0;
			if (days.equals("-10") || days.equals("-20")) {
				day = 1;
			}else{
				day = Integer.parseInt(days);
			}
//			LoginBean loginBean = (LoginBean)req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
//			Integer auth_id=loginBean.getvUserDetailInfo().getAuth_id();// 角色权限id
//			String orga_name = loginBean.getvUserDetailInfo().getOrga_name();
//			String user_name = loginBean.getvUserDetailInfo().getUser_name();
//			Integer role_id = loginBean.getvUserDetailInfo().getRole_id();
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
			if (Integer.parseInt(userRoleId) == 20) {
				vacate_status = 62;
			}else if (Integer.parseInt(userRoleId) == 30) {
				vacate_status = 63;
			}else if (Integer.parseInt(userRoleId) == 50) {
				vacate_status = 64;
			}
			//
			StuVacateInfo stuVacateupdate = new StuVacateInfo();
			stuVacateupdate.setId(Integer.parseInt(id));
			//
			ProcessRecord processRecord1 = new ProcessRecord();
			processRecord1.setProc_content(proc_content);
			processRecord1.setCreate_auth_id(Integer.parseInt(userAuthId));
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
							for(int j = 0 ; j<day ; j++){
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
							stuAttendanceInfo.setAttend_auth_id(Integer.parseInt(userAuthId));
							if (days != null && days.equals("-10")) {
								stuAttendanceInfo.setAttend_state(-10);
							}else if (days != null && days.equals("-20")) {
								stuAttendanceInfo.setAttend_state(-20);
							}else if (days != null) {
								stuAttendanceInfo.setAttend_state(2);
							}
							for(int j = 0 ; j<day ; j++){
								stuAttendanceInfo.setId(stuAttendanceInfoDao.getSequence());
								String start_date= DateUtil.addDate(state_time+"000000", j, "yyyyMMdd");	
								stuAttendanceInfo.setAttend_date(Integer.parseInt(start_date));
								String start_date_time= DateUtil.addDate(state_time+"000000", j, "yyyy-MM-dd HH:mm:ss");
								stuAttendanceInfo.setAttend_time(Timestamp.valueOf(start_date_time));
								stuAttendanceInfoDao.add(stuAttendanceInfo);
							}
						}
						//
						title = userName  + "【" + userOrgaName + "】" +  "批准了你的请假申请！";
				}else{
					stuVacateupdate.setVacate_status(vacate_status);
					stuVacateInfoDao.update(stuVacateupdate);
					//
					processRecord1.setProc_result(1);
					processRecordDao.update(processRecord1);
					//
					title = stu_name  + "【" + userOrgaName + "】" +  "发起了请假申请，请及时审批！";
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
				title = userName  + "【" + userOrgaName + "】" +  "驳回了你的请假申请！";
			}
			// 1、往消息发布表插入数据（MESSAGE_PUBLISH）
			MessagePublish mp = new MessagePublish();
			int mpId = messagePublishDao.getSequence();
			mp.setId(mpId);
			mp.setTitle(title);
			mp.setContent(proc_content);
			mp.setSender_auth_id(Integer.parseInt(userAuthId));
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
			rtnJson = "{\"success\":true,"
					  + "\"msg\":\"操作成功！\""
					  + "}";
		}else{
			rtnJson = "{\"success\":false,"
			  + "\"msg\":\"数据处理出错...\""
			  + "}";
		}
		return rtnJson;
	}
	
	

}
